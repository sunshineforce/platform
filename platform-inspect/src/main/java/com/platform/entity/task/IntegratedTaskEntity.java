package com.platform.entity.task;

import java.io.Serializable;
import java.util.Date;


/**
 * 综合任务实体
 * 表名 integrated_task
 *
 * @author admin
 *  
 * @date 2018-09-01 10:42:27
 */
public class IntegratedTaskEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键id
    private Integer id;
    //区域id
    private Integer regionId;
    //名称
    private String name;
    //要求
    private String requires;
    //备注
    private String bake;
    //发布时间
    private Date publishTime;
    //数据状态 0 正常  1 删除
    private Integer dataStatus;

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
     * 设置：区域id
     */
    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    /**
     * 获取：区域id
     */
    public Integer getRegionId() {
        return regionId;
    }
    /**
     * 设置：名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：名称
     */
    public String getName() {
        return name;
    }
    /**
     * 设置：要求
     */
    public void setRequires(String requires) {
        this.requires = requires;
    }

    /**
     * 获取：要求
     */
    public String getRequires() {
        return requires;
    }
    /**
     * 设置：备注
     */
    public void setBake(String bake) {
        this.bake = bake;
    }

    /**
     * 获取：备注
     */
    public String getBake() {
        return bake;
    }
    /**
     * 设置：发布时间
     */
    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    /**
     * 获取：发布时间
     */
    public Date getPublishTime() {
        return publishTime;
    }
    /**
     * 设置：数据状态 0 正常  1 删除
     */
    public void setDataStatus(Integer dataStatus) {
        this.dataStatus = dataStatus;
    }

    /**
     * 获取：数据状态 0 正常  1 删除
     */
    public Integer getDataStatus() {
        return dataStatus;
    }
}
