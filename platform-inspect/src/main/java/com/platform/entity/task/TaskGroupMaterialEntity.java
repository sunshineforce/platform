package com.platform.entity.task;

import java.io.Serializable;
import java.util.Date;


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

    ///业务属性

    //任务组
    private String groupName;
    //物料名称
    private String materialName;
    //二维码
    private String qrCode;
    //位置
    private String location;
    //物料状态
    private Integer materialStatus;
    //物料更新时间
    private Date updateTime;
    //物料类型名称
    private String materialTypeName;

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

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getMaterialStatus() {
        return materialStatus;
    }

    public void setMaterialStatus(Integer materialStatus) {
        this.materialStatus = materialStatus;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getMaterialTypeName() {
        return materialTypeName;
    }

    public void setMaterialTypeName(String materialTypeName) {
        this.materialTypeName = materialTypeName;
    }
}
