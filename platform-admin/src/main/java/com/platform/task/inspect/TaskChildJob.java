package com.platform.task.inspect;

import com.platform.service.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *  动态创建子任务
 *
 * @author bjsonghongxu
 * @create 2018-08-19 14:52
 **/

@Component("taskChildJob")
public class TaskChildJob {

    @Autowired
    private TaskService taskService;

    public void execute(){
        taskService.createCircleChildTask();
    }
}
