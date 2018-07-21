package com.platform.entity.enterprise;

import java.io.Serializable;
import java.util.Date;


/**
 * 企业信息表实体
 * 表名 enterprise
 * @author admin
 * @date 2018-07-21 14:32:11
 */
public class EnterpriseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键Id
    private Long id;
    //企业名称
    private String enterpriseName;
    //联系电话
    private String mobile;
    //负责人
    private String owner;
    //地址
    private String address;
    //账号
    private String account;
    //密码
    private String password;
    //创建时间
    private Date createTime;
    //创建人
    private String creator;
    //更新时间
    private Date updateTime;
    //更新人
    private String updator;
    //是否启用（0：启用；1：禁用）
    private Integer enabled;

    /**
     * 设置：主键Id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取：主键Id
     */
    public Long getId() {
        return id;
    }
    /**
     * 设置：企业名称
     */
    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    /**
     * 获取：企业名称
     */
    public String getEnterpriseName() {
        return enterpriseName;
    }
    /**
     * 设置：联系电话
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取：联系电话
     */
    public String getMobile() {
        return mobile;
    }
    /**
     * 设置：负责人
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * 获取：负责人
     */
    public String getOwner() {
        return owner;
    }
    /**
     * 设置：地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取：地址
     */
    public String getAddress() {
        return address;
    }
    /**
     * 设置：账号
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 获取：账号
     */
    public String getAccount() {
        return account;
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
     * 设置：更新人
     */
    public void setUpdator(String updator) {
        this.updator = updator;
    }

    /**
     * 获取：更新人
     */
    public String getUpdator() {
        return updator;
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

    @Override
    public String toString() {
        return "EnterpriseEntity{" +
                "id=" + id +
                ", enterpriseName='" + enterpriseName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", owner='" + owner + '\'' +
                ", address='" + address + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", createTime=" + createTime +
                ", creator='" + creator + '\'' +
                ", updateTime=" + updateTime +
                ", updator='" + updator + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
