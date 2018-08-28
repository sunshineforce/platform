package com.platform.entity.material.vo;

import com.platform.vo.SelectVo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * ProjectName: inspect
 * CreateUser:  lixiaopeng
 * CreateTime : 2018/8/28 20:33
 * ModifyUser: bjlixiaopeng
 * Class Description:
 * To change this template use File | Settings | File and Code Template
 */


public class MaterialCheckVo implements Serializable {

    private static final long serialVersionUID = -2013341668455294475L;

    private Long id;

    private String qrCode;

    private String materialName;

    private String materialUrl;

    private Long regionId;

    private String region;

    private String location;

    //生产日期
    private Date producedDate;
    //到期时间
    private Date expireDate;

    private Integer checkSpecificId;

    //物品类型Id
    private Integer materialTypeId;

    private List<SelectVo> checkSpecificItems;

    private List<SelectVo> cheifs;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialUrl() {
        return materialUrl;
    }

    public void setMaterialUrl(String materialUrl) {
        this.materialUrl = materialUrl;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getProducedDate() {
        return producedDate;
    }

    public void setProducedDate(Date producedDate) {
        this.producedDate = producedDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public Integer getCheckSpecificId() {
        return checkSpecificId;
    }

    public void setCheckSpecificId(Integer checkSpecificId) {
        this.checkSpecificId = checkSpecificId;
    }

    public Integer getMaterialTypeId() {
        return materialTypeId;
    }

    public void setMaterialTypeId(Integer materialTypeId) {
        this.materialTypeId = materialTypeId;
    }

    public List<SelectVo> getCheckSpecificItems() {
        return checkSpecificItems;
    }

    public void setCheckSpecificItems(List<SelectVo> checkSpecificItems) {
        this.checkSpecificItems = checkSpecificItems;
    }

    public List<SelectVo> getCheifs() {
        return cheifs;
    }

    public void setCheifs(List<SelectVo> cheifs) {
        this.cheifs = cheifs;
    }
}
