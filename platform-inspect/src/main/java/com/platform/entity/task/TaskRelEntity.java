package com.platform.entity.task;

import java.io.Serializable;
import java.util.Date;


/**
 * 实体
 * 表名 task_rel
 *
 * @author admin
 *  
 * @date 2018-09-01 10:28:46
 */
public class TaskRelEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键id
    private Integer id;
    //综合任务id
    private Integer itId;
    //任务id
    private Integer taskId;

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
     * 设置：综合任务id
     */
    public void setItId(Integer itId) {
        this.itId = itId;
    }

    /**
     * 获取：综合任务id
     */
    public Integer getItId() {
        return itId;
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
}
