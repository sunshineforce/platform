package com.platform.entity.user;

import java.io.Serializable;
import java.util.Date;


/**
 * APP用户表实体
 * 表名 app_user
 * @author admin
 * @date 2018-07-23 19:34:00
 */
public class AppUserEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Integer id;
    //姓名
    private String userName;
    //手机号
    private String mobile;
    //身份
    private Integer identify;
    //上级
    private String superior;
    //帐号
    private String account;
    //初始密码
    private String password;
    //安全证url
    private String certificateUrl;
    //是否启用（0：启用；1：禁用）
    private Integer enabled;
    //创建时间
    private Date createTime;
    //创建人
    private String creator;
    //修改时间
    private Date updateTime;
    //修改人
    private String updator;

    /**
     * 设置：主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取：主键
     */
    public Integer getId() {
        return id;
    }
    /**
     * 设置：姓名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取：姓名
     */
    public String getUserName() {
        return userName;
    }
    /**
     * 设置：手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取：手机号
     */
    public String getMobile() {
        return mobile;
    }
    /**
     * 设置：身份
     */
    public void setIdentify(Integer identify) {
        this.identify = identify;
    }

    /**
     * 获取：身份
     */
    public Integer getIdentify() {
        return identify;
    }
    /**
     * 设置：上级
     */
    public void setSuperior(String superior) {
        this.superior = superior;
    }

    /**
     * 获取：上级
     */
    public String getSuperior() {
        return superior;
    }
    /**
     * 设置：帐号
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 获取：帐号
     */
    public String getAccount() {
        return account;
    }
    /**
     * 设置：初始密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取：初始密码
     */
    public String getPassword() {
        return password;
    }
    /**
     * 设置：安全证url
     */
    public void setCertificateUrl(String certificateUrl) {
        this.certificateUrl = certificateUrl;
    }

    /**
     * 获取：安全证url
     */
    public String getCertificateUrl() {
        return certificateUrl;
    }
    /**
     * 设置：是否启用（0：启用；1：禁用）
     */
    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    /**
     * 获取：是否启用（0：启用；1：禁用）
     */
    public Integer getEnabled() {
        return enabled;
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
    /**
     * 设置：修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取：修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }
    /**
     * 设置：修改人
     */
    public void setUpdator(String updator) {
        this.updator = updator;
    }

    /**
     * 获取：修改人
     */
    public String getUpdator() {
        return updator;
    }
}
