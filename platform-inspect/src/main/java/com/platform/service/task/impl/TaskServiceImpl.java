package com.platform.service.task.impl;

import com.platform.dao.task.TaskDao;
import com.platform.entity.SysRegionEntity;
import com.platform.entity.SysUserEntity;
import com.platform.entity.notice.NoticeEntity;
import com.platform.entity.task.TaskEntity;
import com.platform.entity.task.vo.TaskUserVo;
import com.platform.service.SysRegionService;
import com.platform.service.SysUserService;
import com.platform.service.notice.INoticeService;
import com.platform.service.task.TaskService;
import com.platform.utils.DateUtils;
import com.platform.utils.enums.NoticeStatusEnum;
import com.platform.vo.SelectVo;
import com.platform.vo.TreeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
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

    @Autowired
    private SysRegionService sysRegionService;

    @Autowired
    private SysUserService userService;

    @Override
    public TaskEntity queryObject(Integer id) {
        return taskDao.queryObject(id);
    }

    @Override
    public List<TaskEntity> queryList(Map<String, Object> map) {
        return taskDao.queryList(map);
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
        int effectRows = taskDao.save(task);
        if (effectRows > 0 && null != task.getUserIds()) {
            String[] userIds = task.getUserIds().split(",");
            String content;
            for (int i = 0; i < userIds.length; i++) {
                NoticeEntity notice = new NoticeEntity();
                content = "您收到一个任务：" + task.getName()+", 截止时间 " + DateUtils.format(task.getEndTime(),DateUtils.DATE_PATTERN) + " 请尽快开始执行";
                notice.setName(content);
                notice.setCreateTime(new Date());
                notice.setStatus(NoticeStatusEnum.UNREAD.getCode());
                notice.setTaskId(Long.valueOf(task.getId()));
                notice.setUserId(Long.valueOf(userIds[i]));


                effectRows+=noticeService.save(notice);
            }

        }
        return effectRows;
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

    @Override
    public void updateTaskStatus() {
        taskDao.startSingleTask();
        taskDao.singleTaskTimeOut();
    }

    @Override
    public void createCircleChildTask() {
        Map<String, Object> params = new HashMap<>();
        params.put("outNexTime",DateUtils.format(new Date(),DateUtils.DATE_PATTERN) + " 00:00:00");
        params.put("statusList",null);
        taskDao.selectTaskList(params);
    }

    @Override
    public TaskUserVo choiceUser(Integer regionId) {
//        Subject subject = SecurityUtils.getSubject();
//        SysUserEntity user = (SysUserEntity) subject.getPrincipal();
        SysUserEntity user = userService.queryObject(11L);
        TaskUserVo taskUser = new TaskUserVo();
        //获取区域名称
        SysRegionEntity regionEntity = sysRegionService.queryObject(regionId);
        taskUser.setRegionName(regionEntity.getName());

        //获取区域下的人员
        List<SelectVo> userList = userService.queryUsersByRegionId(user.getRegionId());
        taskUser.setMembers(userList);
        SysRegionEntity regionQuery = new SysRegionEntity();
        regionQuery.setParentId(regionId);

        //获取区域下的子节点
        List<TreeVo> children = sysRegionService.queryRegionSimple(regionQuery);
        taskUser.setChildren(children);
        return taskUser;
    }

}
