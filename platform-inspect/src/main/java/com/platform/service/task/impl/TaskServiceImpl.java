package com.platform.service.task.impl;

import com.platform.dao.task.TaskDao;
import com.platform.entity.SysUserEntity;
import com.platform.entity.notice.NoticeEntity;
import com.platform.entity.task.TaskEntity;
import com.platform.service.notice.INoticeService;
import com.platform.service.task.TaskService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * 任务表Service实现类
 *
 * @author admin
 *  
 * @date 2018-07-21 10:58:54
 */
@Service("taskService")
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskDao taskDao;

    @Autowired
    private INoticeService noticeService;

    @Override
    public TaskEntity queryObject(Integer id) {
        return taskDao.queryObject(id);
    }

    @Override
    public List<TaskEntity> queryList(Map<String, Object> map) {
        SysUserEntity sysUser = (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
        map.put("userId",sysUser.getUserId());
        List<TaskEntity> taskList = taskDao.queryList(map);

        Long taskId = Long.valueOf(String.valueOf(map.get("taskId")));

        for (TaskEntity taskEntity : taskList) {
            taskEntity.setChekArea(sysUser.getRegion());
            taskEntity.setProgressRate(calcTaskProgressRate());
            taskEntity.setNoticeStatus(getNoticeStatus(sysUser.getUserId(),taskId));
        }
        return taskDao.queryList(map);
    }

    /**
     * 计算任务完成进度
     * @return
     */
    private String calcTaskProgressRate(){
        return "2/10";
    }

    /**
     * 查询任务消息的状态名称
     * @param userId 当前登录用户Id
     * @param taskId 任务Id
     * @return
     */
    private String getNoticeStatus(Long userId,Long taskId){
        NoticeEntity condition = new NoticeEntity();
        condition.setUserId(userId);
        condition.setTaskId(taskId);
        NoticeEntity notice = noticeService.queryObjectByCondition(condition);
        return notice.getName();
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return taskDao.queryTotal(map);
    }

    @Override
    public List<TaskEntity> queryTaskList(Map<String, Object> map) {
        return taskDao.selectTaskList(map);
    }

    @Override
    public int queryTaskTotal(Map<String, Object> map) {
        return taskDao.selectTaskTotal(map);
    }

    @Override
    public int save(TaskEntity task) {
        return taskDao.save(task);
    }

    @Override
    public int update(TaskEntity task) {
        return taskDao.update(task);
    }

    @Override
    public int delete(Integer id) {
        return taskDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[]ids) {
        return taskDao.deleteBatch(ids);
    }
}
