package com.platform.service.task.impl;

import com.platform.dao.task.TaskGroupMaterialDao;
import com.platform.entity.task.TaskGroupMaterialEntity;
import com.platform.service.task.TaskGroupMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



/**
 * 任务组物料信息表Service实现类
 *
 * @author admin
 *  
 * @date 2018-07-21 10:58:54
 */
@Service("taskGroupMaterialService")
public class TaskGroupMaterialServiceImpl implements TaskGroupMaterialService {
    @Autowired
    private TaskGroupMaterialDao taskGroupMaterialDao;

    @Override
    public TaskGroupMaterialEntity queryObject(Integer id) {
        return taskGroupMaterialDao.queryObject(id);
    }

    @Override
    public List<TaskGroupMaterialEntity> queryList(Map<String, Object> map) {
        return taskGroupMaterialDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return taskGroupMaterialDao.queryTotal(map);
    }

    @Override
    public int save(TaskGroupMaterialEntity taskGroupMaterial) {
        return taskGroupMaterialDao.save(taskGroupMaterial);
    }

    @Override
    public int update(TaskGroupMaterialEntity taskGroupMaterial) {
        return taskGroupMaterialDao.update(taskGroupMaterial);
    }

    @Override
    public int delete(Integer id) {
        return taskGroupMaterialDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[]ids) {
        return taskGroupMaterialDao.deleteBatch(ids);
    }
}
