package com.platform.service;

import com.platform.entity.AppUserEntity;

import java.util.List;
import java.util.Map;

/**
 * app用户表Service接口
 *
 * @author admin
 *  
 * @date 2018-08-11 14:33:44
 */
public interface AppUserService {

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    AppUserEntity queryObject(Long id);

    AppUserEntity queryAppUser(Map<String, Object> map);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<AppUserEntity> queryList(Map<String, Object> map);

    /**
     * 分页统计总数
     *
     * @param map 参数
     * @return 总数
     */
    int queryTotal(Map<String, Object> map);

    /**
     * 保存实体
     *
     * @param appUser 实体
     * @return 保存条数
     */
    int save(AppUserEntity appUser);

    /**
     * 根据主键更新实体
     *
     * @param appUser 实体
     * @return 更新条数
     */
    int update(AppUserEntity appUser);

    /**
     * 根据主键删除
     *
     * @param id
     * @return 删除条数
     */
    int delete(Long id);

    /**
     * 根据主键批量删除
     *
     * @param ids
     * @return 删除条数
     */
    int deleteBatch(Long[] ids);

    int resetPassword(Long userId, String password, String newPassword);
}
