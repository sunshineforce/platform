package com.platform.service.task.impl;

import com.platform.dao.task.TaskDao;
import com.platform.entity.SysRegionEntity;
import com.platform.entity.SysUserEntity;
import com.platform.entity.notice.NoticeEntity;
import com.platform.entity.task.TaskEntity;
import com.platform.entity.task.vo.TaskStatisticsVo;
import com.platform.entity.task.vo.TaskUserVo;
import com.platform.entity.task.vo.TaskVo;
import com.platform.service.SysRegionService;
import com.platform.service.SysUserService;
import com.platform.service.notice.INoticeService;
import com.platform.service.task.TaskGroupMaterialService;
import com.platform.service.task.TaskService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.enums.NoticeStatusEnum;
import com.platform.vo.SelectVo;
import com.platform.vo.TreeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        if (effectRows > 0) {
            String[] userIds = task.getUserIds().split(",");
            String content;
            for (int i = 0; i < userIds.length; i++) {
                NoticeEntity notice = new NoticeEntity();
                content = "您收到一个任务：" + task.getName()+", 截止时间 " + task.getEndTime() + " 请尽快开始执行";
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

    /********************************** APP 接口部分  ******************************/



    @Override
    public PageUtils queryListForApp(Map<String, Object> map) {
//        SysUserEntity sysUser = (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
        SysUserEntity sysUser = new SysUserEntity();
        sysUser.setUserId(11L);
        map.put("userId",sysUser.getUserId());

        Query query  = new Query(map);
        String status = String.valueOf(map.get("status"));
        List<TaskVo> taskList = taskDao.queryListSimple(map);
        List<TaskStatisticsVo> statistics;
        for (TaskVo taskVo : taskList) {
            taskVo.setProgressRate(calcProgressRate(taskVo.getId(),Integer.valueOf(status)));
            statistics = taskGroupMaterialService.queryMaterialStatisticsByTaskId(Long.valueOf(taskVo.getId()));
            taskVo.setStatistics(statistics);
        }
        int total = taskDao.queryListSimpleTotal(query);

        return new PageUtils(taskList, total, query.getLimit(), query.getPage());
    }



    /**
     * 计算任务完成进度
     * @return
     */
    private String calcProgressRate(Long taskId,Integer type){
        String progressRate = "0/0";
        if (taskId == null) {
            return progressRate;
        }
        TaskStatisticsVo statisticsVo = taskDao.selectTaskProgressRate(taskId);
        Integer total;
        Integer unfinished;
        Integer finish;
        Integer timeout;
        if (statisticsVo != null) {
            total = statisticsVo.getTotal() == null ? 0 : statisticsVo.getTotal();
            unfinished = statisticsVo.getUnfinished() == null ? 0 : statisticsVo.getUnfinished();
            finish = statisticsVo.getFinish() == null ? 0 : statisticsVo.getFinish();
            timeout = statisticsVo.getTimeout() == null ? 0 : statisticsVo.getTimeout();
            if (type == 0) { //未完成进度
                progressRate =  unfinished + "/" + total;
            }else if (type == 1) {//已完成进度
                progressRate =  finish + "/" + total;
            }else  if (type == 2) {//已超时进度
                progressRate =  timeout + "/" + total;
            }
        }
        return progressRate;
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
