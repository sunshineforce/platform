package com.platform.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * app用户表实体
 * 表名 app_user
 *
 * @author admin
 *  
 * @date 2018-08-11 14:33:44
 */
public class AppUserEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键id
    private Long id;
    //用户名
    private String username;
    //用户真实姓名
    private String realname;
    //密码
    private String password;
    //邮箱
    private String email;
    //电话
    private String mobile;
    //状态  0 正常  1 禁用
    private Integer status;
    //身份（0：安全员；1：领导）
    private Integer identify;
    //上级
    private String superior;
    //安全证
    private String certificateUrl;
    //创建人id
    private Long createUserId;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //最后修改人id
    private Long updateUserId;
    //企业id
    private Integer enterpriseId;
    //区域id
    private Integer regionId;

    private String superiorStr;

    private String enterpriseName;

    private List<Map<String,Object>> superiorList;

    /** 所属区域 */
    private String regionName;

    private String updateUserName;

    /**
     * 设置：主键id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取：主键id
     */
    public Long getId() {
        return id;
    }
    /**
     * 设置：用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取：用户名
     */
    public String getUsername() {
        return username;
    }
    /**
     * 设置：用户真实姓名
     */
    public void setRealname(String realname) {
        this.realname = realname;
    }

    /**
     * 获取：用户真实姓名
     */
    public String getRealname() {
        return realname;
    }
    /**
     * 设置：密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取：密码
     */
    public String getPassword() {
        return password;
    }
    /**
     * 设置：邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取：邮箱
     */
    public String getEmail() {
        return email;
    }
    /**
     * 设置：电话
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取：电话
     */
    public String getMobile() {
        return mobile;
    }
    /**
     * 设置：状态  0 正常  1 禁用
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取：状态  0 正常  1 禁用
     */
    public Integer getStatus() {
        return status;
    }
    /**
     * 设置：身份（0：安全员；1：领导）
     */
    public void setIdentify(Integer identify) {
        this.identify = identify;
    }

    /**
     * 获取：身份（0：安全员；1：领导）
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
     * 设置：安全证
     */
    public void setCertificateUrl(String certificateUrl) {
        this.certificateUrl = certificateUrl;
    }

    /**
     * 获取：安全证
     */
    public String getCertificateUrl() {
        return certificateUrl;
    }
    /**
     * 设置：创建人id
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 获取：创建人id
     */
    public Long getCreateUserId() {
        return createUserId;
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
     * 设置：最后修改人id
     */
    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    /**
     * 获取：最后修改人id
     */
    public Long getUpdateUserId() {
        return updateUserId;
    }
    /**
     * 设置：企业id
     */
    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    /**
     * 获取：企业id
     */
    public Integer getEnterpriseId() {
        return enterpriseId;
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

    public String getSuperiorStr() {
        return superiorStr;
    }

    public void setSuperiorStr(String superiorStr) {
        this.superiorStr = superiorStr;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public List<Map<String, Object>> getSuperiorList() {
        return superiorList;
    }

    public void setSuperiorList(List<Map<String, Object>> superiorList) {
        this.superiorList = superiorList;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }
}
