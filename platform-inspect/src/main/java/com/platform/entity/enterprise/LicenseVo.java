package com.platform.entity.enterprise;

/**
 * Created with IntelliJ IDEA
 * ProjectName: inspect
 * CreateUser:  lixiaopeng
 * CreateTime : 2018/7/21 16:08
 * ModifyUser: bjlixiaopeng
 * Class Description:
 * To change this template use File | Settings | File and Code Template
 */


public class LicenseVo extends LicenseEntity{
    private String licenseTypeName;

    public String getLicenseTypeName() {
        return licenseTypeName;
    }

    public void setLicenseTypeName(String licenseTypeName) {
        this.licenseTypeName = licenseTypeName;
    }
}
