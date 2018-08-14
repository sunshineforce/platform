package com.platform.entity.material;

import java.io.Serializable;
import java.util.Date;

public class MaterialVo implements Serializable{

    private static final long serialVersionUID = -2640171786304408447L;

    private Long id;

    //物品url
    private String materialUrl;

    //检查规范Id
    private Long checkSpecificId;

    //识别码
    private String qrCode;

    //物品名称
    private String materialName;

    //位置
    private String location;

    //生产日期
    private Date produceDate;

    //过期日期
    private Date expireDate;

    //检查时间
    private Date checkDate;

    //检查人
    private String checkUser;

    //检查结果
    private String checkResult;

    //上报上级
    private String superiors;

    //检查描述
    private String remark;

    //物品现场照片集体（最多9张）
    private String urls;

    //异常原因
    private String anomalyReasons;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaterialUrl() {
        return materialUrl;
    }

    public void setMaterialUrl(String materialUrl) {
        this.materialUrl = materialUrl;
    }

    public Long getCheckSpecificId() {
        return checkSpecificId;
    }

    public void setCheckSpecificId(Long checkSpecificId) {
        this.checkSpecificId = checkSpecificId;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getProduceDate() {
        return produceDate;
    }

    public void setProduceDate(Date produceDate) {
        this.produceDate = produceDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public String getCheckUser() {
        return checkUser;
    }

    public void setCheckUser(String checkUser) {
        this.checkUser = checkUser;
    }

    public String getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult;
    }

    public String getSuperiors() {
        return superiors;
    }

    public void setSuperiors(String superiors) {
        this.superiors = superiors;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUrls() {
        return urls;
    }

    public void setUrls(String urls) {
        this.urls = urls;
    }

    public String getAnomalyReasons() {
        return anomalyReasons;
    }

    public void setAnomalyReasons(String anomalyReasons) {
        this.anomalyReasons = anomalyReasons;
    }

    @Override
    public String toString() {
        return "MaterialVo{" +
                "id=" + id +
                ", materialUrl='" + materialUrl + '\'' +
                ", checkSpecificId=" + checkSpecificId +
                ", qrCode='" + qrCode + '\'' +
                ", materialName='" + materialName + '\'' +
                ", location='" + location + '\'' +
                ", produceDate=" + produceDate +
                ", expireDate=" + expireDate +
                ", checkDate=" + checkDate +
                ", checkUser='" + checkUser + '\'' +
                ", checkResult='" + checkResult + '\'' +
                ", superiors='" + superiors + '\'' +
                ", remark='" + remark + '\'' +
                ", urls='" + urls + '\'' +
                ", anomalyReasons='" + anomalyReasons + '\'' +
                '}';
    }
}
