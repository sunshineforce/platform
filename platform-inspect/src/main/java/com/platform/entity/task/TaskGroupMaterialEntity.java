package com.platform.entity.task;

import java.io.Serializable;


/**
 * 任务组物料信息表实体
 * 表名 task_group_material
 *
 * @author admin
 *  
 * @date 2018-07-21 10:58:54
 */
public class TaskGroupMaterialEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键id
    private Integer id;
    //任务组id
    private Integer taskGroupId;
    //物料id
    private Integer materialId;

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
     * 设置：物料id
     */
    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    /**
     * 获取：物料id
     */
    public Integer getMaterialId() {
        return materialId;
    }
}
