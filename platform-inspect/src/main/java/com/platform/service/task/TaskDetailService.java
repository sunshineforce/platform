package com.platform.service.task;


import com.platform.entity.task.TaskDetailEntity;

import java.util.List;
import java.util.Map;

/**
 * 任务详情表Service接口
 *
 * @author admin
 *  
 * @date 2018-08-21 16:48:06
 */
public interface TaskDetailService {

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    TaskDetailEntity queryObject(Long id);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<TaskDetailEntity> queryList(Map<String, Object> map);

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
     * @param taskDetail 实体
     * @return 保存条数
     */
    int save(TaskDetailEntity taskDetail);

    /**
     * 根据主键更新实体
     *
     * @param taskDetail 实体
     * @return 更新条数
     */
    int update(TaskDetailEntity taskDetail);

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

    /**
     * 通过taskid 删除
     * @param taskId
     * @return
     */
    int  deleteByTaskId(Integer taskId);
}
