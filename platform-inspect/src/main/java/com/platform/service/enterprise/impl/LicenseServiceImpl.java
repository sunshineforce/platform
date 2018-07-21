package com.platform.service.enterprise.impl;

import com.platform.constants.CommonConstant;
import com.platform.dao.enterprise.LicenseDao;
import com.platform.dao.enterprise.LicenseTypeDao;
import com.platform.entity.SysUserEntity;
import com.platform.entity.enterprise.LicenseEntity;
import com.platform.entity.enterprise.LicenseTypeEntity;
import com.platform.entity.enterprise.LicenseVo;
import com.platform.service.enterprise.ILicenseService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 证照表Service实现类
 *
 * @author admin
 *  
 * @date 2018-07-21 15:28:08
 */
@Service("licenseService")
public class LicenseServiceImpl implements ILicenseService {
    @Autowired
    private LicenseDao licenseDao;

    @Autowired
    private LicenseTypeDao licenseTypeDao;

    @Override
    public LicenseEntity queryObject(Integer id) {
        return licenseDao.queryObject(id);
    }

    @Override
    public List<LicenseEntity> queryList(Map<String, Object> map) {
        return licenseDao.queryList(map);
    }

    @Override
    public List<LicenseVo> selectList(Map<String, Object> map) {
        return licenseDao.selectList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return licenseDao.queryTotal(map);
    }

    @Override
    public int save(LicenseEntity license) {
        SysUserEntity sessionUser = (SysUserEntity) SecurityUtils.getSubject().getSession().getAttribute(CommonConstant.LOGIN_USER);
        license.setCreator(sessionUser.getUsername());
        return licenseDao.save(license);
    }

    @Override
    public int update(LicenseEntity license) {
        return licenseDao.update(license);
    }

    @Override
    public int delete(Integer id) {
        return licenseDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[]ids) {
        return licenseDao.deleteBatch(ids);
    }

}
