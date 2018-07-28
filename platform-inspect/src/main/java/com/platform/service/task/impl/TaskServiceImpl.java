package com.platform.service.task.impl;

import com.platform.dao.task.TaskDao;
import com.platform.entity.SysUserEntity;
import com.platform.entity.notice.NoticeEntity;
import com.platform.entity.task.TaskEntity;
import com.platform.entity.task.TaskGroupMaterialEntity;
import com.platform.service.notice.INoticeService;
import com.platform.service.task.TaskGroupMaterialService;
import com.platform.service.task.TaskService;
import com.platform.utils.enums.MaterialStatusEnum;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private TaskGroupMaterialService taskGroupMaterialService;

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

        Map<String,Object> paramsMap = new HashMap<String,Object>();
        List<TaskGroupMaterialEntity> taskGroupMaterialList;
        for (TaskEntity taskEntity : taskList) {
            taskEntity.setChekArea(sysUser.getRegion());
            taskEntity.setProgressRate(String.valueOf(calcTaskProgressRate(taskEntity.getTaskGroupId()).get("finished")));
            taskEntity.setNoticeStatus(getNoticeStatus(sysUser.getUserId(),taskId));

            paramsMap.put("taskGroupId",taskEntity.getTaskGroupId());
            taskGroupMaterialList = taskGroupMaterialService.queryTaskGroupMaterialList(paramsMap);

            taskEntity.setMaterialList(taskGroupMaterialList);
        }
        return taskDao.queryList(map);
    }

    /**
     * 计算任务完成进度
     * @return
     */
    private Map<String,Object> calcTaskProgressRate(Integer taskGroupId){
        Map<String,Object> resultMap = new HashMap<String,Object>();
        Map<String,Object> paramsMap = new HashMap<String,Object>();
        paramsMap.put("taskGroupId",taskGroupId);
        List<TaskGroupMaterialEntity> taskGroupMaterialList = taskGroupMaterialService.queryTaskGroupMaterialList(paramsMap);
        Integer finishedCount=0;
        Integer normalCount=0;
        Integer anomalyCount=0;

        Integer total = taskGroupMaterialList.size();

        resultMap.put("finished",finishedCount+"/"+total);
        resultMap.put("normal",normalCount);
        resultMap.put("anomaly",anomalyCount);

        for (TaskGroupMaterialEntity entity : taskGroupMaterialList) {
            if (entity.getMaterialStatus().equals(MaterialStatusEnum.NORMAL.getCode())) {
                normalCount++;
            }else if (entity.getMaterialStatus().equals(MaterialStatusEnum.ANOMALY.getCode())) {
                anomalyCount++;
            }else if (entity.getMaterialStatus().equals(MaterialStatusEnum.FINISHED.getCode())) {
                finishedCount++;
            }
            resultMap.put("finished",finishedCount+"/"+total);
            resultMap.put("normal",normalCount);
            resultMap.put("anomaly",anomalyCount);
        }
        return resultMap;
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
        if (notice != null) {
            return notice.getName().equals("")?"":notice.getName();
        }
        return "";
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
