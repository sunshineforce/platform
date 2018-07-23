package com.platform.entity.material;

import java.io.Serializable;
import java.util.Date;


/**
 * 物品表实体
 * 表名 material
 *
 * @author admin
 *  
 * @date 2018-07-23 11:11:45
 */
public class MaterialEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键Id
    private Integer id;
    //二维码
    private String qrCode;
    //物品名称
    private String materialName;
    //所在位置
    private String location;
    //物品类型Id
    private Integer materialTypeId;
    //生产日期
    private Date producedDate;
    //到期时间
    private Date expireDate;
    //最近检查时间
    private Date checkDate;
    //物品状态（0：正常；1：报废；2：异常）
    private Integer materialStatus;
    //所属企业
    private String materialOwner;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //创建人Id
    private Integer creator;

    ///物品类型名称
    private String materialTypeName;

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
     * 设置：二维码
     */
    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    /**
     * 获取：二维码
     */
    public String getQrCode() {
        return qrCode;
    }
    /**
     * 设置：物品名称
     */
    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    /**
     * 获取：物品名称
     */
    public String getMaterialName() {
        return materialName;
    }
    /**
     * 设置：所在位置
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * 获取：所在位置
     */
    public String getLocation() {
        return location;
    }
    /**
     * 设置：物品类型Id
     */
    public void setMaterialTypeId(Integer materialTypeId) {
        this.materialTypeId = materialTypeId;
    }

    /**
     * 获取：物品类型Id
     */
    public Integer getMaterialTypeId() {
        return materialTypeId;
    }
    /**
     * 设置：生产日期
     */
    public void setProducedDate(Date producedDate) {
        this.producedDate = producedDate;
    }

    /**
     * 获取：生产日期
     */
    public Date getProducedDate() {
        return producedDate;
    }
    /**
     * 设置：到期时间
     */
    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    /**
     * 获取：到期时间
     */
    public Date getExpireDate() {
        return expireDate;
    }
    /**
     * 设置：最近检查时间
     */
    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    /**
     * 获取：最近检查时间
     */
    public Date getCheckDate() {
        return checkDate;
    }
    /**
     * 设置：物品状态（0：正常；1：报废；2：异常）
     */
    public void setMaterialStatus(Integer materialStatus) {
        this.materialStatus = materialStatus;
    }

    /**
     * 获取：物品状态（0：正常；1：报废；2：异常）
     */
    public Integer getMaterialStatus() {
        return materialStatus;
    }
    /**
     * 设置：所属企业
     */
    public void setMaterialOwner(String materialOwner) {
        this.materialOwner = materialOwner;
    }

    /**
     * 获取：所属企业
     */
    public String getMaterialOwner() {
        return materialOwner;
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
     * 设置：创建人Id
     */
    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    /**
     * 获取：创建人Id
     */
    public Integer getCreator() {
        return creator;
    }

    public String getMaterialTypeName() {
        return materialTypeName;
    }

    public void setMaterialTypeName(String materialTypeName) {
        this.materialTypeName = materialTypeName;
    }
}
