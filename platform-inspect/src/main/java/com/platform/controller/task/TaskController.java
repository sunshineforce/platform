package com.platform.controller.task;

import com.platform.constants.CommonConstant;
import com.platform.controller.AbstractController;
import com.platform.entity.AppUserEntity;
import com.platform.entity.SysUserEntity;
import com.platform.entity.task.TaskDetailEntity;
import com.platform.entity.task.TaskEntity;
import com.platform.service.AppUserService;
import com.platform.service.task.TaskDetailService;
import com.platform.service.task.TaskService;
import com.platform.util.TaskUtils;
import com.platform.utils.DateUtils;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;
import com.platform.utils.enums.TaskStatusEnum;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 任务表Controller
 *
 * @author admin
 *  
 * @date 2018-07-21 10:58:54
 */
@Controller
@RequestMapping("task")
public class TaskController extends AbstractController {
    @Autowired
    private TaskService taskService;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private TaskDetailService taskDetailService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("task:list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        params.put("dataStatus",CommonConstant.USEABLE_STATUS);


        Map<String, Object> params1 = new HashMap<>();
        SysUserEntity user = getUser();
        ///如果是企业用户登录，查询该企业下的用户
        if (null != user && null != user.getEnterpriseId()){
            params.put("enterpriseId",user.getEnterpriseId());
            params1.put("enterpriseId",user.getEnterpriseId());
        }
        params1.put("identify",0);
        List<AppUserEntity> userList = appUserService.queryList(params1); //查询安全列表

        //查询列表数据
        Query query = new Query(params);
        List<TaskEntity> taskList = taskService.queryTaskList(query);
        int total = taskService.queryTaskTotal(query);

        setAppuserNames(taskList,userList);
        PageUtils pageUtil = new PageUtils(taskList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    private void setAppuserNames(List<TaskEntity> taskList,List<AppUserEntity> userList){
        if (taskList == null || userList == null) {
           return;
        }
        for (TaskEntity taskEntity : taskList) {
            String userIds  =taskEntity.getUserIds();
            if (StringUtils.isNotBlank(userIds)){
                String [] ids = userIds.split(",");
                StringBuffer sb = new StringBuffer("");
                for (String id : ids) {
                    for (AppUserEntity user : userList) {
                       if (user.getId().intValue() == Integer.parseInt(id)){
                           if (sb.length() > 0){
                               sb.append(",").append(user.getRealname());
                           }else {
                               sb.append(user.getRealname());
                           }

                       }
                    }
                }
                taskEntity.setUserNames(sb.toString());
            }

        }
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("task:info")
    @ResponseBody
    public R info(@PathVariable("id") Integer id) {
        TaskEntity task = taskService.queryObject(id);

        return R.ok().put("task", task);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("task:save")
    @ResponseBody
    public R save(@RequestBody TaskEntity task) {
        int status = TaskStatusEnum.PENDING.getCode();
        if (task.getStartTime() != null){
            String now = DateUtils.format(new Date());
            String startTime = DateUtils.format(task.getStartTime());
            if( DateUtils.parseDate(startTime,DateUtils.DATE_PATTERN).getTime() - DateUtils.parseDate(now,DateUtils.DATE_PATTERN).getTime()  < 0){
               return R.error("开始时间必须大于或等于今天");
            }
            if( DateUtils.parseDate(startTime,DateUtils.DATE_PATTERN).getTime()
                    - DateUtils.parseDate(now,DateUtils.DATE_PATTERN).getTime()  == 0){
                status = TaskStatusEnum.EXECUTING.getCode();
            }
        }else{
            return R.paramsIllegal();
        }
        Date time = new Date();
        task.setCreateTime(time);
        task.setStatus(status);
        task.setDataStatus(CommonConstant.USEABLE_STATUS);
        if (CommonConstant.TASK_CIRCLE_TYPE == task.getType().intValue()){ //循环任务
            task.setNextTime(TaskUtils.calNexStartTime(task.getStartTime(),task.getScheduleCycle()));
        }
        taskService.save(task);
        Date endTime = task.getEndTime();
        if (CommonConstant.TASK_CIRCLE_TYPE == task.getType().intValue()){ //循环任务
            endTime = TaskUtils.calCircleTaskEndTime(task.getStartTime(),task.getSchedule());
        }
        TaskDetailEntity detail = new TaskDetailEntity(task.getId(),task.getTaskGroupId(), task.getRegionId() ,task.getEnterpriseId(), status,task.getStartTime(),endTime,new Date());
        taskDetailService.save(detail);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("task:update")
    @ResponseBody
    public R update(@RequestBody TaskEntity task) {
        Date time = new Date();
        //task.setUpdateTime(time);
        task.setDataStatus(CommonConstant.USEABLE_STATUS);
        taskService.update(task);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("task:delete")
    @ResponseBody
    public R delete(@RequestBody Integer[]ids) {
        if (ids.length > 0 ){
            TaskEntity task = null;
            for (Integer id : ids) {
                TaskEntity taskEntity = taskService.queryObject(id);
                if (taskEntity.getLastCheckTime() == null){
                    taskService.delete(id);
                    //删除详情中的任务
                    taskDetailService.deleteByTaskId(id);
                }
            }
        }

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<TaskEntity> list = taskService.queryList(params);

        return R.ok().put("list", list);
    }
}
