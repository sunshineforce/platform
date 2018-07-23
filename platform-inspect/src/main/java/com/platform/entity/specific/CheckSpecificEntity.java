package com.platform.entity.specific;

import java.io.Serializable;
import java.util.Date;


/**
 * 检查规范表实体
 * 表名 check_specific
 *
 * @author admin
 *  
 * @date 2018-07-23 20:03:40
 */
public class CheckSpecificEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键Id
    private Integer id;
    //规范名称
    private String specificName;
    //备注
    private String remark;
    //创建时间
    private Date createTime;
    //创建者
    private Integer creator;
    //最后修改人
    private Date updateTime;
    //最后修改人
    private Integer updator;

    /**
     * 设置：主键Id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取：主键Id
     */
    public Integer getId() {
        return id;
    }
    /**
     * 设置：规范名称
     */
    public void setSpecificName(String specificName) {
        this.specificName = specificName;
    }

    /**
     * 获取：规范名称
     */
    public String getSpecificName() {
        return specificName;
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
     * 设置：创建者
     */
    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    /**
     * 获取：创建者
     */
    public Integer getCreator() {
        return creator;
    }
    /**
     * 设置：最后修改人
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取：最后修改人
     */
    public Date getUpdateTime() {
        return updateTime;
    }
    /**
     * 设置：最后修改人
     */
    public void setUpdator(Integer updator) {
        this.updator = updator;
    }

    /**
     * 获取：最后修改人
     */
    public Integer getUpdator() {
        return updator;
    }
}
