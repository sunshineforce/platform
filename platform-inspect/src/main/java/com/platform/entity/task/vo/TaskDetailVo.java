package com.platform.entity.task.vo;

import com.platform.entity.task.TaskDetailEntity;

import java.util.Date;

/**
 * 任务详情Vo
 *
 * @author bjsonghongxu
 * @create 2018-08-29 16:07
 **/
public class TaskDetailVo extends TaskDetailEntity {


    //任务名称
    private String name;
    //备注
    private String remark;

    //区域名称
    private String regionName;

    //企业名称
    private String enterpriseName;

    ////业务属性
    //任务组名称
    private String taskGroupName;

    //检查人
    private String userNames;

    //最后检查时间
    private Date inspectTime;

    //人员ids
    private String userIds;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getTaskGroupName() {
        return taskGroupName;
    }

    public void setTaskGroupName(String taskGroupName) {
        this.taskGroupName = taskGroupName;
    }

    public String getUserNames() {
        return userNames;
    }

    public void setUserNames(String userNames) {
        this.userNames = userNames;
    }

    public Date getInspectTime() {
        return inspectTime;
    }

    public void setInspectTime(Date inspectTime) {
        this.inspectTime = inspectTime;
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }
}
