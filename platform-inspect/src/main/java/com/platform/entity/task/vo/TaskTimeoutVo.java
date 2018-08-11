package com.platform.entity.task.vo;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA
 * ProjectName: inspect
 * CreateUser:  lixiaopeng
 * CreateTime : 2018/8/11 15:50
 * ModifyUser: bjlixiaopeng
 * Class Description:
 * To change this template use File | Settings | File and Code Template
 */


public class TaskTimeoutVo implements Serializable {

    private static final long serialVersionUID = 7551559114340814126L;

    private Integer taskId;
    private String reason;

    public TaskTimeoutVo() {
    }

    public TaskTimeoutVo(Integer taskId, String reason) {
        this.taskId = taskId;
        this.reason = reason;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "TaskTimeoutVo{" +
                "taskId=" + taskId +
                ", reason='" + reason + '\'' +
                '}';
    }
}
