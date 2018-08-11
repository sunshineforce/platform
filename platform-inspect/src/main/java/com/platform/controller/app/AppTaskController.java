package com.platform.controller.app;

import com.platform.entity.task.TaskEntity;
import com.platform.entity.task.vo.TaskUserVo;
import com.platform.service.task.TaskGroupService;
import com.platform.service.task.TaskService;
import com.platform.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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

    @Resource
    private TaskService taskService;

    @Autowired
    private TaskGroupService taskGroupService;

    @RequestMapping(value = "/list")
    public R taskList(@RequestParam HashMap<String,Object> params){
        //查询列表数据
        if (params == null) {
            return R.paramsIllegal();
        }
        if (StringUtils.isEmpty(String.valueOf(params.get("status")))) {
            return R.paramsIllegal();
        }

        return R.succeed().put("page", taskService.queryListForApp(params));
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public R createTask(@RequestBody TaskEntity taskEntity){
        if (taskEntity == null) {
            return R.succeed();
        }
        taskService.save(taskEntity);
        return R.succeed();
    }

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
}
