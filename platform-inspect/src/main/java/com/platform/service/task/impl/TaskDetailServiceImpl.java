package com.platform.service.task.impl;

import com.platform.dao.task.TaskDetailDao;
import com.platform.entity.task.TaskDetailEntity;
import com.platform.service.task.TaskDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



/**
 * 任务详情表Service实现类
 *
 * @author admin
 *  
 * @date 2018-08-21 16:48:06
 */
@Service("taskDetailService")
public class TaskDetailServiceImpl implements TaskDetailService {
    @Autowired
    private TaskDetailDao taskDetailDao;

    @Override
    public TaskDetailEntity queryObject(Long id) {
        return taskDetailDao.queryObject(id);
    }

    @Override
    public List<TaskDetailEntity> queryList(Map<String, Object> map) {
        return taskDetailDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return taskDetailDao.queryTotal(map);
    }

    @Override
    public int save(TaskDetailEntity taskDetail) {
        return taskDetailDao.save(taskDetail);
    }

    @Override
    public int update(TaskDetailEntity taskDetail) {
        return taskDetailDao.update(taskDetail);
    }

    @Override
    public int delete(Long id) {
        return taskDetailDao.delete(id);
    }

    @Override
    public int deleteBatch(Long[]ids) {
        return taskDetailDao.deleteBatch(ids);
    }

    @Override
    public int deleteByTaskId(Integer taskId) {
        return taskDetailDao.deleteByTaskId(taskId);
    }
}
