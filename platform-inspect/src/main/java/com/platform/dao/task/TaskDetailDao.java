package com.platform.dao.task;

import com.platform.dao.BaseDao;
import com.platform.entity.task.TaskDetailEntity;

/**
 * 任务详情表Dao
 *
 * @author admin
 *  
 * @date 2018-08-21 16:48:06
 */
public interface TaskDetailDao extends BaseDao<TaskDetailEntity> {

    /**
     * 通过taskid 删除
     * @param taskId
     * @return
     */
    int  deleteByTaskId(Integer taskId);
}
