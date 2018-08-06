package com.platform.service.task.impl;

import com.platform.dao.task.TaskDao;
import com.platform.dao.task.TaskGroupDao;
import com.platform.entity.task.TaskGroupEntity;
import com.platform.entity.task.vo.TaskGroupVo;
import com.platform.service.task.TaskGroupService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.vo.SelectVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
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

    @Autowired
    private TaskDao taskDao;

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

    @Override
    public PageUtils queryAllTaskGroupForApp() {
        Query query = new Query(new HashMap<String,Object>());
        List<SelectVo> list = queryAllTaskGroup();
        List<SelectVo> taskList;
        List<TaskGroupVo> taskGroupList = new ArrayList<TaskGroupVo>();
        for (SelectVo selectVo : list) {
            TaskGroupVo taskGroupVo = new TaskGroupVo();
            BeanUtils.copyProperties(selectVo,taskGroupVo);

            taskList = taskGroupDao.selectTaskByTaskGroupId(taskGroupVo.getId());
            taskGroupVo.setTaskList(taskList);

            taskGroupList.add(taskGroupVo);
        }
        int total = taskGroupDao.queryTotal();
        return new PageUtils(taskGroupList, total, query.getLimit(), query.getPage());
    }
}
