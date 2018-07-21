package com.platform.entity.task;

import java.io.Serializable;
import java.util.Date;


/**
 * 任务组表实体
 * 表名 task_group
 *
 * @author admin
 *  
 * @date 2018-07-21 10:58:54
 */
public class TaskGroupEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //id
    private Integer id;
    //任务组名称
    private String name;
    //备注
    private String remark;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //数据状态 0 正常 1 删除
    private Integer dataStatus;

    /**
     * 设置：id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取：id
     */
    public Integer getId() {
        return id;
    }
    /**
     * 设置：任务组名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：任务组名称
     */
    public String getName() {
        return name;
    }
    /**
     * 设置：备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取：备注
     */
    public String getRemark() {
        return remark;
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
     * 设置：数据状态 0 正常 1 删除
     */
    public void setDataStatus(Integer dataStatus) {
        this.dataStatus = dataStatus;
    }

    /**
     * 获取：数据状态 0 正常 1 删除
     */
    public Integer getDataStatus() {
        return dataStatus;
    }
}
