package com.platform.entity.enterprise;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * ProjectName: inspect
 * CreateUser:  lixiaopeng
 * CreateTime : 2018/8/19 14:29
 * ModifyUser: bjlixiaopeng
 * Class Description:
 * To change this template use File | Settings | File and Code Template
 */


public class EnterpriseVo implements Serializable {

    private static final long serialVersionUID = -2479898062291705657L;

    private Integer id;
    private String enterpriseName;
    private String owner;
    private String mobile;
    private String address;
    private Date createTime;
    private List<LicenseVo> licenses;

    public EnterpriseVo() {
    }

    public EnterpriseVo(Integer id, String enterpriseName) {
        this.id = id;
        this.enterpriseName = enterpriseName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<LicenseVo> getLicenses() {
        return licenses;
    }

    public void setLicenses(List<LicenseVo> licenses) {
        this.licenses = licenses;
    }
}
