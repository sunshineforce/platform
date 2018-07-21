package com.platform.entity.task;

import java.io.Serializable;
import java.util.Date;


/**
 * 任务表实体
 * 表名 task
 *
 * @author admin
 *  
 * @date 2018-07-21 10:58:54
 */
public class TaskEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键id
    private Integer id;
    //任务组id
    private Integer taskGroupId;
    //任务名称
    private String name;
    //备注
    private String remark;
    //任务类型 0 单次任务 1 循环任务
    private Integer type;
    //任务状态  0 
    private Integer status;
    //人员ids
    private String userIds;
    //人员姓名
    private String userNames;
    //开始时间
    private Date startTime;
    //截止时间
    private Date endTime;
    //执行时限(单位天)
    private Integer schedule;
    //循环周期 每天 1 每周 2 每月 3 每年 4
    private Integer scheduleCycle;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //数据状态 0  正常 1 删除
    private Integer dataStatus;


    ////业务属性
    //任务组名称
    private String taskGroupName;

    //检查区域
    private String chekArea;
    //检查企业
    private String checkCompany;
    //最后执行时间
    private Date lastCheckTime;



    /**
     * 设置：主键id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取：主键id
     */
    public Integer getId() {
        return id;
    }
    /**
     * 设置：任务组id
     */
    public void setTaskGroupId(Integer taskGroupId) {
        this.taskGroupId = taskGroupId;
    }

    /**
     * 获取：任务组id
     */
    public Integer getTaskGroupId() {
        return taskGroupId;
    }
    /**
     * 设置：任务类型 0 单次任务 1 循环任务
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取：任务类型 0 单次任务 1 循环任务
     */
    public Integer getType() {
        return type;
    }
    /**
     * 设置：任务状态  0 
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取：任务状态  0 
     */
    public Integer getStatus() {
        return status;
    }
    /**
     * 设置：人员ids
     */
    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    /**
     * 获取：人员ids
     */
    public String getUserIds() {
        return userIds;
    }
    /**
     * 设置：人员姓名
     */
    public void setUserNames(String userNames) {
        this.userNames = userNames;
    }

    /**
     * 获取：人员姓名
     */
    public String getUserNames() {
        return userNames;
    }
    /**
     * 设置：开始时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取：开始时间
     */
    public Date getStartTime() {
        return startTime;
    }
    /**
     * 设置：截止时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取：截止时间
     */
    public Date getEndTime() {
        return endTime;
    }
    /**
     * 设置：执行时限(单位天)
     */
    public void setSchedule(Integer schedule) {
        this.schedule = schedule;
    }

    /**
     * 获取：执行时限(单位天)
     */
    public Integer getSchedule() {
        return schedule;
    }
    /**
     * 设置：循环周期 每天 1 每周 2 每月 3 每年 4
     */
    public void setScheduleCycle(Integer scheduleCycle) {
        this.scheduleCycle = scheduleCycle;
    }

    /**
     * 获取：循环周期 每天 1 每周 2 每月 3 每年 4
     */
    public Integer getScheduleCycle() {
        return scheduleCycle;
    }
    /**
     * 设置：创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取：创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }
    /**
     * 设置：更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取：更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }
    /**
     * 设置：数据状态 0  正常 1 删除
     */
    public void setDataStatus(Integer dataStatus) {
        this.dataStatus = dataStatus;
    }

    /**
     * 获取：数据状态 0  正常 1 删除
     */
    public Integer getDataStatus() {
        return dataStatus;
    }

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

    public String getTaskGroupName() {
        return taskGroupName;
    }

    public void setTaskGroupName(String taskGroupName) {
        this.taskGroupName = taskGroupName;
    }

    public String getChekArea() {
        return chekArea;
    }

    public void setChekArea(String chekArea) {
        this.chekArea = chekArea;
    }

    public String getCheckCompany() {
        return checkCompany;
    }

    public void setCheckCompany(String checkCompany) {
        this.checkCompany = checkCompany;
    }

    public Date getLastCheckTime() {
        return lastCheckTime;
    }

    public void setLastCheckTime(Date lastCheckTime) {
        this.lastCheckTime = lastCheckTime;
    }
}
