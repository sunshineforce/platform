package com.platform.dao.task;

import com.platform.dao.BaseDao;
import com.platform.entity.dto.StatDto;
import com.platform.entity.task.TaskEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 任务表Dao
 *
 * @author admin
 *  
 * @date 2018-07-21 10:58:54
 */

@Repository
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


    /**
     * 开启单次任务
     */
    void startSingleTask();

    /**
     * 开启循环任务
     */
    void startCircleTask();

    /**
     * 单次任务超时
     */
    void singleTaskTimeOut();

    /**
     * 循环任务超时
     */
    void circleTaskTimeOut();

}
