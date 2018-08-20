package com.platform.dao;

import com.platform.entity.AppUserEntity;

import java.util.Map;

/**
 * app用户表Dao
 *
 * @author admin
 *  
 * @date 2018-08-11 14:33:44
 */
public interface AppUserDao extends BaseDao<AppUserEntity> {

    int updatePassword(Map<String, Object> map);

    AppUserEntity queryObjectByUserName(Map<String, Object> map);
}
