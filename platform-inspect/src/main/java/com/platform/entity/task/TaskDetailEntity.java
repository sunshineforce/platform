package com.platform.entity.task;

import java.io.Serializable;
import java.util.Date;


/**
 * 任务详情表实体
 * 表名 task_detail
 *
 * @author bjsonghognxu
 *  
 * @date 2018-08-21 16:48:06
 */
public class TaskDetailEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键id
    private Long id;
    //任务id
    private Integer taskId;
    //区域id
    private Integer regionId;
    //0：待执行；1：执行中；2：已完成；3：已超时
    private Integer status;
    //超时原因
    private String delayReason;
    //开始时间
    private Date startTime;
    //结束时间
    private Date endTime;



    public TaskDetailEntity() {
    }



    public TaskDetailEntity(Integer taskId,Integer regionId, Integer status, Date startTime, Date endTime) {
        this.taskId = taskId;
        this.regionId = regionId;
        this.status = status;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * 设置：主键id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取：主键id
     */
    public Long getId() {
        return id;
    }
    /**
     * 设置：任务id
     */
    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    /**
     * 获取：任务id
     */
    public Integer getTaskId() {
        return taskId;
    }
    /**
     * 设置：0：待执行；1：执行中；2：已完成；3：已超时
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取：0：待执行；1：执行中；2：已完成；3：已超时
     */
    public Integer getStatus() {
        return status;
    }
    /**
     * 设置：
     */
    public void setDelayReason(String delayReason) {
        this.delayReason = delayReason;
    }

    /**
     * 获取：
     */
    public String getDelayReason() {
        return delayReason;
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
     * 设置：结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取：结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }
}
