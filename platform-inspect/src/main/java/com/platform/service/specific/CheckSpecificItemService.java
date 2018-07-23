package com.platform.service.specific;


import com.platform.entity.specific.CheckSpecificItemEntity;

import java.util.List;
import java.util.Map;

/**
 * 规范项条目表Service接口
 *
 * @author admin
 *  
 * @date 2018-07-23 20:03:40
 */
public interface CheckSpecificItemService {

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    CheckSpecificItemEntity queryObject(Integer id);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<CheckSpecificItemEntity> queryList(Map<String, Object> map);

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
     * @param checkSpecificItem 实体
     * @return 保存条数
     */
    int save(CheckSpecificItemEntity checkSpecificItem);

    /**
     * 根据主键更新实体
     *
     * @param checkSpecificItem 实体
     * @return 更新条数
     */
    int update(CheckSpecificItemEntity checkSpecificItem);

    /**
     * 根据主键删除
     *
     * @param id
     * @return 删除条数
     */
    int delete(Integer id);

    /**
     * 根据主键批量删除
     *
     * @param ids
     * @return 删除条数
     */
    int deleteBatch(Integer[] ids);
}
