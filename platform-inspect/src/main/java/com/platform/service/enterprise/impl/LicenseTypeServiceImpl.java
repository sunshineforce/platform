package com.platform.service.enterprise.impl;

import com.platform.constants.CommonConstant;
import com.platform.dao.enterprise.LicenseTypeDao;
import com.platform.entity.SysUserEntity;
import com.platform.entity.enterprise.EnterpriseEntity;
import com.platform.entity.enterprise.LicenseTypeEntity;
import com.platform.service.enterprise.ILicenseTypeService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 证照类型表Service实现类
 *
 * @author admin
 *  
 * @date 2018-07-21 15:28:08
 */
@Service("licenseTypeService")
public class LicenseTypeServiceImpl implements ILicenseTypeService {
    @Autowired
    private LicenseTypeDao licenseTypeDao;

    @Override
    public LicenseTypeEntity queryObject(Integer id) {
        return licenseTypeDao.queryObject(id);
    }

    @Override
    public List<LicenseTypeEntity> queryList(Map<String, Object> map) {
        return licenseTypeDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return licenseTypeDao.queryTotal(map);
    }

    @Override
    public int save(LicenseTypeEntity licenseType) {
        Date currDate = new Date();
        SysUserEntity sessionUser = (SysUserEntity) SecurityUtils.getSubject().getSession().getAttribute(CommonConstant.LOGIN_USER);

        licenseType.setCreateTime(currDate);
        licenseType.setCreator(sessionUser.getUsername());
        return licenseTypeDao.save(licenseType);
    }

    @Override
    public int update(LicenseTypeEntity licenseType) {
        return licenseTypeDao.update(licenseType);
    }

    @Override
    public int delete(Integer id) {
        return licenseTypeDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[]ids) {
        return licenseTypeDao.deleteBatch(ids);
    }

}
