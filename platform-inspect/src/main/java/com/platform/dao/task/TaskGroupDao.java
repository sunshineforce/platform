package com.platform.dao.task;

import com.platform.dao.BaseDao;
import com.platform.entity.task.TaskGroupEntity;
import com.platform.entity.task.vo.TaskVo;
import com.platform.vo.SelectVo;

import java.util.List;
import java.util.Map;

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

    List<TaskVo> selectTaskGroupSimple(Map<String, Object> map);

    int selectTaskGroupSimpleTotal(Map<String, Object> map);
    List<SelectVo> selectTaskByTaskGroupId(Integer id);
}
