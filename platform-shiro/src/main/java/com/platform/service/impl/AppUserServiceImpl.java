package com.platform.service.impl;

import com.platform.dao.AppUserDao;
import com.platform.entity.AppUserEntity;
import com.platform.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * app用户表Service实现类
 * @author admin
 * @date 2018-08-11 14:33:44
 */

@Service("appUserService")
public class AppUserServiceImpl implements AppUserService {
    @Autowired
    private AppUserDao appUserDao;

    @Override
    public AppUserEntity queryObject(Long id) {
        return appUserDao.queryObject(id);
    }

    @Override
    public AppUserEntity queryAppUser(Map<String, Object> map) {
        return appUserDao.queryObjectByUserName(map);
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
    public int delete(Long id) {
        return appUserDao.delete(id);
    }

    @Override
    public int deleteBatch(Long[]ids) {
        return appUserDao.deleteBatch(ids);
    }

    @Override
    public int resetPassword(Long userId, String password, String newPassword) {
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("userId", userId);
        paramsMap.put("password", password);
        paramsMap.put("newPassword", newPassword);

        return appUserDao.updatePassword(paramsMap);
    }
}
