package com.platform.service.task;


import com.platform.entity.task.TaskEntity;
import com.platform.entity.task.vo.TaskUserVo;
import com.platform.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * 任务表Service接口
 *
 * @author admin
 *  
 * @date 2018-07-21 10:58:54
 */
public interface TaskService {

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    TaskEntity queryObject(Integer id);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<TaskEntity> queryList(Map<String, Object> map);

    PageUtils queryListForApp(Map<String, Object> map);

    /**
     * 分页统计总数
     *
     * @param map 参数
     * @return 总数
     */
    int queryTotal(Map<String, Object> map);


    /**
     * 查询任务信息
     * @param map
     * @return
     */
    List<TaskEntity> queryTaskList(Map<String, Object> map);

    /**
     * 总条数
     * @param map
     * @return
     */
    int queryTaskTotal(Map<String, Object> map);

    /**
     * 保存实体
     *
     * @param task 实体
     * @return 保存条数
     */
    int save(TaskEntity task);

    /**
     * 根据主键更新实体
     *
     * @param task 实体
     * @return 更新条数
     */
    int update(TaskEntity task);

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


    /**********************************APP 接口 ********************************/
    TaskUserVo choiceUser(Integer regionId);
}
