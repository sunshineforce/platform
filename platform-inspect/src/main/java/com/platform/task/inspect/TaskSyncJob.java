package com.platform.task.inspect;

import com.platform.service.task.TaskDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *  任务状态同步
 *
 * @author bjsonghongxu
 * @create 2018-08-19 14:52
 **/

@Component("taskSyncJob")
public class TaskSyncJob {

    @Autowired
    private TaskDetailService taskDetailService;

    public void execute(){
        taskDetailService.syncTask();
    }
}
