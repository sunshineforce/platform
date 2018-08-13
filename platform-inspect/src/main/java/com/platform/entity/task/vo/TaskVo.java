package com.platform.entity.task.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA
 * ProjectName: inspect
 * CreateUser:  lixiaopeng
 * CreateTime : 2018/8/4 19:12
 * ModifyUser: bjlixiaopeng
 * Class Description:
 * To change this template use File | Settings | File and Code Template
 */


public class TaskVo implements Serializable {
    private static final long serialVersionUID = -8557678875044410963L;

    //任务组Id
    private Long taskGroupId;

    private Long taskId;
    //任务名称
    private String name;
    //完成进度
    private String progressRate;
    //开始时间
    private Date startTime;
    //结束时间
    private Date endTime;
    //区域Id
    private Integer regionId;
    //企业名称
    private String enterpriseName;
    //任务地点
    private String location;
    //任务状态
    private Integer status;
    //任务状态名称
    private String taskStatus;
    //检查人
    private String checkUser;

    public Long getTaskGroupId() {
        return taskGroupId;
    }

    public void setTaskGroupId(Long taskGroupId) {
        this.taskGroupId = taskGroupId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProgressRate() {
        return progressRate;
    }

    public void setProgressRate(String progressRate) {
        this.progressRate = progressRate;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getCheckUser() {
        return checkUser;
    }

    public void setCheckUser(String checkUser) {
        this.checkUser = checkUser;
    }

    @Override
    public String toString() {
        return "TaskVo{" +
                "taskGroupId=" + taskGroupId +
                ", taskId=" + taskId +
                ", name='" + name + '\'' +
                ", progressRate='" + progressRate + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", regionId=" + regionId +
                ", enterpriseName='" + enterpriseName + '\'' +
                ", location='" + location + '\'' +
                ", status=" + status +
                ", taskStatus='" + taskStatus + '\'' +
                ", checkUser='" + checkUser + '\'' +
                '}';
    }
}
