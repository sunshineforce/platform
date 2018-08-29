package com.platform.service.material.impl;

import com.alibaba.fastjson.JSON;
import com.platform.constants.CommonConstant;
import com.platform.dao.inspect.InspectOrderDao;
import com.platform.dao.material.MaterialDao;
import com.platform.dao.material.MaterialTypeDao;
import com.platform.dao.specific.CheckSpecificItemDao;
import com.platform.entity.AppUserEntity;
import com.platform.entity.dto.AuthVo;
import com.platform.entity.dto.CustomerVo;
import com.platform.entity.dto.DeviceListVo;
import com.platform.entity.dto.DeviceVo;
import com.platform.entity.inspect.vo.AnomalyVo;
import com.platform.entity.material.MaterialEntity;
import com.platform.entity.material.MaterialTypeEntity;
import com.platform.entity.material.MaterialVo;
import com.platform.entity.material.vo.MaterialCheckVo;
import com.platform.entity.specific.CheckSpecificItemEntity;
import com.platform.service.common.CommonService;
import com.platform.service.material.MaterialService;
import com.platform.util.AppClientUtils;
import com.platform.util.MaterialBindUtils;
import com.platform.utils.StringUtils;
import com.platform.utils.enums.MaterialStatusEnum;
import com.platform.vo.SelectVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 物品表Service实现类
 * @author admin
 * @date 2018-07-23 11:11:45
 */
@Service("materialService")
public class MaterialServiceImpl implements MaterialService {

    public static final Logger logger = LoggerFactory.getLogger(MaterialServiceImpl.class);

    @Autowired
    private MaterialDao materialDao;

    @Autowired
    private MaterialTypeDao materialTypeDao;

    @Autowired
    private CommonService commonService;

    @Autowired
    private CheckSpecificItemDao checkSpecificItemDao;

    @Autowired
    private InspectOrderDao inspectOrderDao;

    //客户设备列表页索引
    private static Integer pageIndex = 1;

    private static ExecutorService threadPool = Executors.newFixedThreadPool(5);

    @Override
    public MaterialEntity queryObject(Integer id) {
        MaterialEntity materialEntity = materialDao.queryObject(id);
        if (null != materialEntity){
            MaterialTypeEntity materialTypeEntity = materialTypeDao.queryObject(materialEntity.getMaterialTypeId());
            if (null != materialTypeEntity){
                materialEntity.setMaterialTypeName(materialTypeEntity.getName());
            }
        }
        return materialEntity;
    }

    @Override
    public MaterialEntity queryMaterialByQrCode(String qrCode) {
        return materialDao.queryMaterialByQrCode(qrCode);
    }

    @Override
    public MaterialCheckVo materialInfo(String qrCode) {
        MaterialEntity materialEntity = queryMaterialByQrCode(qrCode);
        MaterialCheckVo result = new MaterialCheckVo();

        BeanUtils.copyProperties(materialEntity,result);
        result.setRegion(commonService.getRegionName(Integer.valueOf(String.valueOf(materialEntity.getRegionId()))));
        List<SelectVo> list = checkSpecificItemDao.queryListSimple(Long.valueOf(String.valueOf(materialEntity.getCheckSpecificId())));
        result.setCheckSpecificItems(list);
        List<SelectVo> chiefs = commonService.getSuperior();
        result.setCheifs(chiefs);
        return result;
    }

    @Override
    public List<MaterialEntity> queryList(Map<String, Object> map) {
        return materialDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return materialDao.queryTotal(map);
    }

    @Override
    public int save(MaterialEntity material) {
        material.setCreateTime(new Date());
//        AppUserEntity appUser = commonService.getCurrentLoginUser();
//        material.setCreatorId(appUser.getId());
//        material.setCreator(appUser.getRealname());

        material.setCreatorId(11L);
        material.setCreator("小六");

        return materialDao.save(material);
    }

    @Override
    public int update(MaterialEntity material) {
        return materialDao.update(material);
    }

    @Override
    public int delete(Integer id) {
        return materialDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[]ids) {
        return materialDao.deleteBatch(ids);
    }

    @Override
    public MaterialVo queryMaterialById(Integer id) {
        MaterialEntity materialEntity = materialDao.queryObject(id);
        MaterialVo materialVo = new MaterialVo();
        if (materialEntity != null) {
            BeanUtils.copyProperties(materialEntity,materialVo);
            materialVo.setId(Long.valueOf(materialEntity.getId()));
            Integer regionId = Integer.valueOf(String.valueOf(materialEntity.getRegionId()));
            materialVo.setLocation(commonService.getRegionName(regionId));
            if (StringUtils.isNotEmpty(materialEntity.getMaterialUrl())) {
                String[] materialUrl = materialEntity.getMaterialUrl().split(",");
                materialVo.setMaterialUrl(materialUrl[0]);
                materialVo.setUrls(materialEntity.getMaterialUrl());
            }
            if (materialEntity.getMaterialStatus().equals(MaterialStatusEnum.ANOMALY.getCode())) {
                materialVo.setAnomalyReasons(getAnomalyReason(materialEntity.getId()));

            }
        }
        return materialVo;
    }

    @Override
    public int materialBind(final CustomerVo customer) {
        String url = CommonConstant.THIRD_PARTY_URL+CommonConstant.CUSTOMER_MATERIAL_URL;
        String customerId = customer.getId();
        DeviceListVo deviceListVo = setBindMaterialRequestParams(url, customerId);
        bindMaterialWithPage(deviceListVo.getRecords());
        //开始绑定设备
        threadPool.submit(new Runnable() {
            @Override
            public void run() {
                materialBindBatch(customer);
            }
        });
        return 0;
    }

    private void materialBindBatch(CustomerVo customer){
        String url = CommonConstant.THIRD_PARTY_URL+CommonConstant.CUSTOMER_MATERIAL_URL;
        String customerId = customer.getId();
        DeviceListVo deviceListVo = setBindMaterialRequestParams(url, customerId);
        Integer pages = deviceListVo.getPages();
        List<DeviceVo> list;

        for (int i = 0; i < pages; i++) {
            pageIndex++;
            deviceListVo = setBindMaterialRequestParams(url,customerId);
            list = deviceListVo.getRecords();
            if (CollectionUtils.isNotEmpty(list)) {
                bindMaterialWithPage(list);
            }
        }
    }


    private MaterialTypeEntity saveMaterialType(DeviceVo deviceVo){
        MaterialTypeEntity materialType = materialTypeDao.queryMaterialTypeByName(deviceVo.getTypeName());
        if (materialType == null) {
            materialType = new MaterialTypeEntity();
            AppUserEntity appUser = commonService.getCurrentLoginUser();
            materialType.setName(deviceVo.getTypeName());
            materialType.setEnterpriseId(appUser.getEnterpriseId());
            materialType.setCreateTime(new Date());
            materialType.setCreatorId(appUser.getId());
            materialType.setCreator(appUser.getRealname());

            materialTypeDao.save(materialType);

        }
        return materialType;
    }

    private void bindMaterialWithPage(List<DeviceVo> list){
        MaterialTypeEntity materialType;
        if (CollectionUtils.isNotEmpty(list)) {
            AppUserEntity appUser = commonService.getCurrentLoginUser();
            for (DeviceVo deviceVo : list) {
                MaterialEntity material = new MaterialEntity();
                material.setQrCode("");
                material.setMaterialName("");
                material.setLocation(deviceVo.getAddrDetail());
                material.setRegionId(0L);
                material.setEnterpriseId(11);
                //设置物料类型
                materialType = saveMaterialType(deviceVo);
                material.setMaterialTypeId(materialType.getId());
                material.setMaterialTypeName(deviceVo.getTypeName());
                material.setProducedDate(new Date());
                material.setExpireDate(new Date());
                material.setMaterialStatus(MaterialStatusEnum.UNCHECK.getCode());
                material.setMaterialUrl("");
                material.setCreateTime(new Date());
                material.setCreatorId(appUser.getId());
                material.setCreator(appUser.getRealname());

                try {
                    materialDao.save(material);
                } catch (Exception e) {
                    logger.error("Bind material exception ! details : " + e);
                }
            }
        }
    }

    private DeviceListVo setBindMaterialRequestParams(String url,String customerId){
        List<BasicNameValuePair> postParams = new ArrayList<BasicNameValuePair>();
        postParams.add(new BasicNameValuePair("customerId",customerId));
        postParams.add(new BasicNameValuePair("current",String.valueOf(pageIndex)));
        postParams.add(new BasicNameValuePair("size",String.valueOf(20)));
        AuthVo loginToken;
        do {
            loginToken = MaterialBindUtils.getLoginToken();
            String resultJson = AppClientUtils.sendPost(loginToken,url,postParams);
            if (StringUtils.isNotEmpty(resultJson)) {
                DeviceListVo listVo = JSON.parseObject(resultJson,DeviceListVo.class);
                return listVo;
            }
        }while (MaterialBindUtils.isExpire(loginToken));

        return null;
    }

    /**
     * 封装异常原因
     * @param materialId
     * @return
     */
    private String getAnomalyReason(Integer materialId){
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("inspectStatus",MaterialStatusEnum.ANOMALY.getCode());
        params.put("materialId",materialId);
        List<AnomalyVo> anomalyVoList = inspectOrderDao.search(params);
        StringBuilder reasonStr = new StringBuilder();
        for (AnomalyVo anomalyVo : anomalyVoList) {
            reasonStr.append(anomalyVo.getDescription());
            reasonStr.append(",");
        }
        if (reasonStr.length() > 0) {
            reasonStr.setLength(reasonStr.length()-1);
        }

        return reasonStr.toString();
    }

}
