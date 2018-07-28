package com.platform.dao.task;

import com.platform.dao.BaseDao;
import com.platform.entity.task.TaskGroupEntity;
import com.platform.vo.SelectVo;

import java.util.List;

/**
 * 任务组表Dao
 *
 * @author admin
 *  
 * @date 2018-07-21 10:58:54
 */
public interface TaskGroupDao extends BaseDao<TaskGroupEntity> {
    List<SelectVo> queryAllTaskGroup();

    List<SelectVo> queryAllTaskGroupMembers();
}
