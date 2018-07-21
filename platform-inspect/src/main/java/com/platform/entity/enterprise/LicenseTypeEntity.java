package com.platform.entity.enterprise;

import java.io.Serializable;
import java.util.Date;


/**
 * 证照类型表实体
 * 表名 license_type
 * @author admin
 * @date 2018-07-21 15:28:08
 */
public class LicenseTypeEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键Id
    private Integer id;
    //证照类型名称
    private String licenseType;
    //备注
    private String remark;
    //创建时间
    private Date createTime;
    //创建人
    private String creator;

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
     * 设置：证照类型名称
     */
    public void setLicenseType(String licenseType) {
        this.licenseType = licenseType;
    }

    /**
     * 获取：证照类型名称
     */
    public String getLicenseType() {
        return licenseType;
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
     * 设置：创建人
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * 获取：创建人
     */
    public String getCreator() {
        return creator;
    }
}
