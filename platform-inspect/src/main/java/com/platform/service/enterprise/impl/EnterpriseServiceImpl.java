package com.platform.service.enterprise.impl;

import com.platform.dao.enterprise.EnterpriseDao;
import com.platform.dao.enterprise.LicenseDao;
import com.platform.entity.dto.CustomerVo;
import com.platform.entity.enterprise.EnterpriseEntity;
import com.platform.entity.enterprise.EnterpriseVo;
import com.platform.entity.enterprise.LicenseEntity;
import com.platform.entity.enterprise.LicenseVo;
import com.platform.service.enterprise.IEnterpriseService;
import com.platform.util.AppClientUtils;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.vo.SelectVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 企业管理Service实现类
 *
 * @author admin
 *  
 * @date 2018-07-21 14:32:11
 */
@Service("enterpriseService")
public class EnterpriseServiceImpl implements IEnterpriseService {

    @Autowired
    private EnterpriseDao enterpriseDao;

    @Autowired
    private LicenseDao licenseDao;

    @Override
    public EnterpriseEntity queryObject(Integer id) {
        return enterpriseDao.queryObject(id);
    }

    @Override
    public List<EnterpriseEntity> queryList(Map<String, Object> map) {
        return enterpriseDao.queryList(map);
    }

    @Override
    public List<SelectVo> loadEnterpriseByRegionId(Integer regionId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("regionId",regionId);
        List<EnterpriseEntity> list = queryList(params);

        SelectVo selectVo;
        List<SelectVo> resultList = new ArrayList<SelectVo>(list.size());
        for (EnterpriseEntity enterprise : list) {
            selectVo = new SelectVo(enterprise.getId(),enterprise.getEnterpriseName());
            resultList.add(selectVo);
        }
        return resultList;
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return enterpriseDao.queryTotal(map);
    }

    @Override
    public int save(EnterpriseEntity enterprise) {
//        AppClientUtils.setEnterpriseDefaultValue(enterprise);
        return enterpriseDao.save(enterprise);
    }

    @Override
    public int update(EnterpriseEntity enterprise) {
    //    AppClientUtils.setEnterpriseDefaultValue(enterprise);
        return enterpriseDao.update(enterprise);
    }

    @Override
    public int delete(Long id) {
        return enterpriseDao.delete(id);
    }

    @Override
    public int deleteBatch(Long[]ids) {
        return enterpriseDao.deleteBatch(ids);
    }

    @Override
    public PageUtils search(Map<String, Object> params) {
        Query query = new Query(params);
        List<EnterpriseEntity> enterpriseList = queryList(query);
        int total=0;
        List<EnterpriseVo> list = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(enterpriseList)) {
            for (EnterpriseEntity enterpriseEntity : enterpriseList) {
                EnterpriseVo vo = new EnterpriseVo(enterpriseEntity.getId(),enterpriseEntity.getEnterpriseName());
                vo.setOwner(enterpriseEntity.getOwner());
                vo.setMobile(enterpriseEntity.getMobile());
                vo.setAddress(enterpriseEntity.getAddress());
                vo.setCreateTime(enterpriseEntity.getCreateTime());

                Map<String,Object> map = new HashMap<String, Object>();
                map.put("enterpriseId",enterpriseEntity.getId());
                List<LicenseVo> licenseList = licenseDao.selectList(map);
                vo.setLicenses(licenseList);

                list.add(vo);
            }
            total = queryTotal(query);
        }
        return new PageUtils(list, total, query.getLimit(), query.getPage());
    }

    @Override
    public int enterpriseBind(CustomerVo params) {
        EnterpriseEntity enterprise = enterpriseDao.queryEnterpriseByName(params);
        if (enterprise == null) {
            enterprise = new EnterpriseEntity();
            enterprise.setEnterpriseName(params.getCustomerName());
            enterprise.setMobile(params.getPhone());
            enterprise.setAddress(params.getAddrName()+params.getAddrDetail());
            AppClientUtils.setEnterpriseDefaultValue(enterprise);

            return enterpriseDao.save(enterprise);
        }
        return 0;
    }

}
