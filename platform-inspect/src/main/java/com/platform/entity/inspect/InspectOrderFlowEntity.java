package com.platform.entity.inspect;

import java.io.Serializable;
import java.util.Date;


/**
 * 巡检工单处理流程实体
 * 表名 inspect_order_flow
 *
 * @author admin
 *  
 * @date 2018-07-22 21:48:11
 */
public class InspectOrderFlowEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键id
    private Integer id;
    //工单id
    private Integer orderId;
    //处理类型  0 处理   1 报告上级  2 复查
    private Integer type;
    //操作人id
    private Integer userId;
    //操作人
    private String userName;
    //时间
    private Date createTime;
    //描述
    private String descr;

    //现场照片
    private String photos;

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
     * 设置：工单id
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取：工单id
     */
    public Integer getOrderId() {
        return orderId;
    }
    /**
     * 设置：处理类型  0 处理   1 报告上级  2 复查
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取：处理类型  0 处理   1 报告上级  2 复查
     */
    public Integer getType() {
        return type;
    }
    /**
     * 设置：操作人id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取：操作人id
     */
    public Integer getUserId() {
        return userId;
    }
    /**
     * 设置：操作人
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取：操作人
     */
    public String getUserName() {
        return userName;
    }
    /**
     * 设置：时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取：时间
     */
    public Date getCreateTime() {
        return createTime;
    }
    /**
     * 设置：描述
     */
    public void setDescr(String descr) {
        this.descr = descr;
    }

    /**
     * 获取：描述
     */
    public String getDescr() {
        return descr;
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

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }
}
