package com.platform.service.task.impl;

import com.platform.dao.task.TaskRelDao;
import com.platform.entity.task.TaskRelEntity;
import com.platform.service.task.TaskRelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * Service实现类
 *
 * @author admin
 *  
 * @date 2018-09-01 10:28:46
 */
@Service("taskRelService")
public class TaskRelServiceImpl implements TaskRelService {
    @Autowired
    private TaskRelDao taskRelDao;

    @Override
    public TaskRelEntity queryObject(Integer id) {
        return taskRelDao.queryObject(id);
    }

    @Override
    public List<TaskRelEntity> queryList(Map<String, Object> map) {
        return taskRelDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return taskRelDao.queryTotal(map);
    }

    @Override
    public int save(TaskRelEntity taskRel) {
        return taskRelDao.save(taskRel);
    }

    @Override
    public int update(TaskRelEntity taskRel) {
        return taskRelDao.update(taskRel);
    }

    @Override
    public int delete(Integer id) {
        return taskRelDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[]ids) {
        return taskRelDao.deleteBatch(ids);
    }
}
