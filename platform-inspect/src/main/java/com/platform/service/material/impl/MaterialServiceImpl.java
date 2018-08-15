package com.platform.service.material.impl;

import com.platform.dao.inspect.InspectOrderDao;
import com.platform.dao.material.MaterialDao;
import com.platform.dao.material.MaterialTypeDao;
import com.platform.entity.inspect.vo.AnomalyVo;
import com.platform.entity.material.MaterialEntity;
import com.platform.entity.material.MaterialTypeEntity;
import com.platform.entity.material.MaterialVo;
import com.platform.service.common.CommonService;
import com.platform.service.material.MaterialService;
import com.platform.utils.StringUtils;
import com.platform.utils.enums.MaterialStatusEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 物品表Service实现类
 * @author admin
 * @date 2018-07-23 11:11:45
 */
@Service("materialService")
public class MaterialServiceImpl implements MaterialService {
    @Autowired
    private MaterialDao materialDao;

    @Autowired
    private MaterialTypeDao materialTypeDao;

    @Autowired
    private CommonService commonService;

    @Autowired
    private InspectOrderDao inspectOrderDao;

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
    public List<MaterialEntity> queryList(Map<String, Object> map) {
        return materialDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return materialDao.queryTotal(map);
    }

    @Override
    public int save(MaterialEntity material) {
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
    public List<MaterialVo> materialHistory(Map<String, Object> map) {

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
