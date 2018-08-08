package com.platform.entity.inspect.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA
 * ProjectName: inspect
 * CreateUser:  lixiaopeng
 * CreateTime : 2018/8/1 20:30
 * ModifyUser: bjlixiaopeng
 * Class Description: 异常vo
 * To change this template use File | Settings | File and Code Template
 */

public class AnomalyVo implements Serializable {

    private static final long serialVersionUID = 3893491818924432342L;

    //主键Id
    private Long id;
    //二维码
    private String qrcode;
    //物品类型
    private Integer materialTypeId;
    //物品名称
    private String materialName;
    //物品url
    private String materialUrl;
    //企业名称
    private String enterpriseName;
    //位置
    private String location;
    //检查时间
    private Date checkTime;
    //检查结果
    private String checkResult;
    //检查描述
    private String description;
    //检查用户
    private String checkUserName;
    //生产日期
    private Date producedDate;
    //过期日期
    private Date expireDate;

    //上级ids
    private String chiefIds;
    //检查上级名称
    private String chiefName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public Integer getMaterialTypeId() {
        return materialTypeId;
    }

    public void setMaterialTypeId(Integer materialTypeId) {
        this.materialTypeId = materialTypeId;
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

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public String getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getCheckUserName() {
        return checkUserName;
    }

    public void setCheckUserName(String checkUserName) {
        this.checkUserName = checkUserName;
    }

    public String getChiefIds() {
        return chiefIds;
    }

    public void setChiefIds(String chiefIds) {
        this.chiefIds = chiefIds;
    }

    public String getChiefName() {
        return chiefName;
    }

    public void setChiefName(String chiefName) {
        this.chiefName = chiefName;
    }
}
