package com.platform.service.task;


import com.platform.entity.task.TaskGroupMaterialEntity;

import java.util.List;
import java.util.Map;

/**
 * 任务组物料信息表Service接口
 *
 * @author admin
 *  
 * @date 2018-07-21 10:58:54
 */
public interface TaskGroupMaterialService {

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    TaskGroupMaterialEntity queryObject(Integer id);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<TaskGroupMaterialEntity> queryList(Map<String, Object> map);

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
     * @param taskGroupMaterial 实体
     * @return 保存条数
     */
    int save(TaskGroupMaterialEntity taskGroupMaterial);

    /**
     * 根据主键更新实体
     *
     * @param taskGroupMaterial 实体
     * @return 更新条数
     */
    int update(TaskGroupMaterialEntity taskGroupMaterial);

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
