package com.platform.entity.enterprise;

import java.io.Serializable;
import java.util.Date;


/**
 * 证照表实体
 * 表名 license
 *
 * @author admin
 *  
 * @date 2018-07-21 15:28:08
 */
public class LicenseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键Id
    private Integer id;

    //证照类型Id
    private Integer licenseTypeId;
    //证照类型名称
    private String licenseType;
    //证件名称
    private String licenseName;
    //证件编号
    private String number;
    //证件到期时间
    private Date expireDate;
    //证件url地址
    private String url;
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
     * 设置：证照类型Id
     */
    public void setLicenseTypeId(Integer licenseTypeId) {
        this.licenseTypeId = licenseTypeId;
    }

    /**
     * 获取：证照类型Id
     */
    public Integer getLicenseTypeId() {
        return licenseTypeId;
    }

    public String getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(String licenseType) {
        this.licenseType = licenseType;
    }
    /**
     * 设置：证件名称
     */
    public void setLicenseName(String licenseName) {
        this.licenseName = licenseName;
    }

    /**
     * 获取：证件名称
     */
    public String getLicenseName() {
        return licenseName;
    }
    /**
     * 设置：证件编号
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * 获取：证件编号
     */
    public String getNumber() {
        return number;
    }
    /**
     * 设置：证件到期时间
     */
    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    /**
     * 获取：证件到期时间
     */
    public Date getExpireDate() {
        return expireDate;
    }
    /**
     * 设置：证件url地址
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取：证件url地址
     */
    public String getUrl() {
        return url;
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
