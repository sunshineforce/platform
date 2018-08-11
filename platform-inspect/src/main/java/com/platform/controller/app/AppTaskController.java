package com.platform.controller.app;

import com.platform.entity.task.TaskEntity;
import com.platform.entity.task.vo.TaskTimeoutVo;
import com.platform.entity.task.vo.TaskUserVo;
import com.platform.service.task.TaskGroupService;
import com.platform.service.task.TaskService;
import com.platform.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA
 * ProjectName: inspect
 * CreateUser:  lixiaopeng
 * CreateTime : 2018/7/24 20:32
 * ModifyUser: bjlixiaopeng
 * Class Description:
 * To change this template use File | Settings | File and Code Template
 */

@RestController
@RequestMapping("/app/task")
public class AppTaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskGroupService taskGroupService;

    /**
     * 任务列表查询
     * @param params
     * @return
     */
    @RequestMapping(value = "/list")
    public R taskList(@RequestParam HashMap<String,Object> params){
        //查询列表数据
        if (params == null) {
            return R.paramsIllegal();
        }
        if (StringUtils.isEmpty(String.valueOf(params.get("status")))) {
            return R.paramsIllegal();
        }

        return R.succeed().put("page", new ArrayList<>());
    }

    /**
     * 下发任务
     * @param taskEntity
     * @return
     */
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public R createTask(@RequestBody TaskEntity taskEntity){
        if (taskEntity == null) {
            return R.paramsIllegal();
        }
        taskService.save(taskEntity);
        return R.succeed();
    }

    /**
     * 创建任务选择人员
     * @param params
     * @return
     */
    @RequestMapping("/choiceUser")
    public R choiceUser(@RequestParam HashMap<String,Object> params){
        Integer regionId = Integer.valueOf(String.valueOf(params.get("regionId")));
        if (regionId == null || regionId==0) {
            return R.paramsIllegal();
        }
        TaskUserVo taskUser = taskService.choiceUser(regionId);
        return R.succeed().put("list",taskUser);
    }

    /**
     * 加载所有的任务组
     * @return
     */
    @RequestMapping("/group/list")
    public R queryAllTaskGroup(){
        return R.succeed().put("list",taskGroupService.queryAllTaskGroupForApp());
    }

    /**
     * 加载任务组人员
     * @return
     */
    @RequestMapping("/group/members")
    public R queryTaskGroupMembers(){
        return R.succeed().put("list",taskGroupService.queryAllTaskGroupMembers());
    }

    /**
     * 任务超时原因填写
     * @param taskTimeout
     * @return
     */
    @RequestMapping(value = "/timeout",method = RequestMethod.POST)
    public R taskTimeout(@RequestBody TaskTimeoutVo taskTimeout){
        if (taskTimeout == null) {
            return R.paramsIllegal();
        }else {
            if (taskTimeout.getTaskId() == null || taskTimeout.getTaskId()==0) {
                return R.paramsIllegal();
            }
        }
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setId(taskTimeout.getTaskId());
        taskEntity.setDelayReason(taskTimeout.getReason());

        taskService.update(taskEntity);
        return R.succeed();
    }
}
