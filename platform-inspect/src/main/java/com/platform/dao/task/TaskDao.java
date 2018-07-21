package com.platform.dao.task;

import com.platform.dao.BaseDao;
import com.platform.entity.task.TaskEntity;

import java.util.List;
import java.util.Map;

/**
 * 任务表Dao
 *
 * @author admin
 *  
 * @date 2018-07-21 10:58:54
 */
public interface TaskDao extends BaseDao<TaskEntity> {

    /**
     * 查询任务信息
     * @param map
     * @return
     */
    List<TaskEntity> selectTaskList(Map<String, Object> map);

    /**
     * 总条数
     * @param map
     * @return
     */
    int selectTaskTotal(Map<String, Object> map);
}
