package com.platform.service.user.impl;

import com.platform.dao.user.AppUserDao;
import com.platform.entity.user.AppUserEntity;
import com.platform.service.user.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * APP用户表Service实现类
 *
 * @author admin
 *  
 * @date 2018-07-23 19:34:00
 */
@Service("appUserService")
public class AppUserServiceImpl implements IAppUserService {
    @Autowired
    private AppUserDao appUserDao;

    @Override
    public AppUserEntity queryObject(Integer id) {
        return appUserDao.queryObject(id);
    }

    @Override
    public List<AppUserEntity> queryList(Map<String, Object> map) {
        return appUserDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return appUserDao.queryTotal(map);
    }

    @Override
    public int save(AppUserEntity appUser) {
        return appUserDao.save(appUser);
    }

    @Override
    public int update(AppUserEntity appUser) {
        return appUserDao.update(appUser);
    }

    @Override
    public int delete(Integer id) {
        return appUserDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[]ids) {
        return appUserDao.deleteBatch(ids);
    }
}
