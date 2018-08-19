package com.platform.task.stat;

import com.platform.service.stat.StaTaskDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *  更新任务状态
 *
 * @author bjsonghongxu
 * @create 2018-08-19 14:52
 **/

@Component("taskExecJob")
public class TaskExecJob {

    @Autowired
    private StaTaskDayService staTaskDayService;

    public void run(){

    }
}
