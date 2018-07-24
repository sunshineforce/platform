package com.platform.controller.app;

import com.platform.entity.task.TaskEntity;
import com.platform.service.task.TaskService;
import com.platform.utils.R;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * ProjectName: inspect
 * CreateUser:  lixiaopeng
 * CreateTime : 2018/7/24 20:32
 * ModifyUser: bjlixiaopeng
 * Class Description:
 * To change this template use File | Settings | File and Code Template
 */

@RequestMapping("/app")
public class TaskController {

    @Resource
    private TaskService taskService;

    @RequestMapping("/task/list")
    public R taskList(HashMap<String,Object> params){
        if (params == null) {
            return R.ok();
        }
        List<TaskEntity> taskList = taskService.queryList(params);
        return R.ok().put("data",taskList);
    }

    @RequestMapping("/task/create")
    public R createTask(TaskEntity taskEntity){
        if (taskEntity == null) {
            return R.ok();
        }
        taskService.save(taskEntity);
        return R.ok();
    }
}
