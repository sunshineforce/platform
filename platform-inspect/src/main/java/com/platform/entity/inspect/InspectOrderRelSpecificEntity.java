package com.platform.entity.inspect;

import java.io.Serializable;
import java.util.Date;


/**
 * 工单管理检查项表实体
 * 表名 inspect_order_rel_specific
 *
 * @author admin
 *  
 * @date 2018-07-22 21:48:11
 */
public class InspectOrderRelSpecificEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键id
    private Integer id;
    //工单id
    private Integer orderId;
    //检查项id
    private Integer specificId;
    //检查状态 0 正常  1 异常
    private Integer status;

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
     * 设置：检查项id
     */
    public void setSpecificId(Integer specificId) {
        this.specificId = specificId;
    }

    /**
     * 获取：检查项id
     */
    public Integer getSpecificId() {
        return specificId;
    }
    /**
     * 设置：检查状态 0 正常  1 异常
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取：检查状态 0 正常  1 异常
     */
    public Integer getStatus() {
        return status;
    }
}
