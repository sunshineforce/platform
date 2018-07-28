package com.platform.service.task.impl;

import com.platform.dao.task.TaskGroupDao;
import com.platform.entity.task.TaskGroupEntity;
import com.platform.service.task.TaskGroupService;
import com.platform.vo.SelectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * 任务组表Service实现类
 *
 * @author admin
 *  
 * @date 2018-07-21 10:58:54
 */
@Service("taskGroupService")
public class TaskGroupServiceImpl implements TaskGroupService {
    @Autowired
    private TaskGroupDao taskGroupDao;

    @Override
    public TaskGroupEntity queryObject(Integer id) {
        return taskGroupDao.queryObject(id);
    }

    @Override
    public List<TaskGroupEntity> queryList(Map<String, Object> map) {
        return taskGroupDao.queryList(map);
    }

    @Override
    public List<SelectVo> queryAllTaskGroup() {
        return taskGroupDao.queryAllTaskGroup();
    }

    @Override
    public List<SelectVo> queryAllTaskGroupMembers() {
        return taskGroupDao.queryAllTaskGroupMembers();
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return taskGroupDao.queryTotal(map);
    }

    @Override
    public int save(TaskGroupEntity taskGroup) {
        return taskGroupDao.save(taskGroup);
    }

    @Override
    public int update(TaskGroupEntity taskGroup) {
        return taskGroupDao.update(taskGroup);
    }

    @Override
    public int delete(Integer id) {
        return taskGroupDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[]ids) {
        return taskGroupDao.deleteBatch(ids);
    }
}
