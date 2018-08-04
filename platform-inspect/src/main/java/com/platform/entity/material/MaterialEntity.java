package com.platform.entity.material;

import java.io.Serializable;
import java.util.Date;

/**
 * 物品表实体
 * 表名 material
 * @author admin
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
    //区域Id
    private Long regionId;
    //企业Id
    private Long enterpriseId;
    //所在位置
    private String location;
    //物品类型Id
    private Integer materialTypeId;
    //规范Id
    private Integer checkSpecificId;
    //生产日期
    private Date producedDate;
    //到期时间
    private Date expireDate;
    //最近检查时间
    private Date checkDate;
    //物品状态（0：未检查；1：正常；2：异常；3：报废）
    private Integer materialStatus;
    //物品url
    private String materialUrl;

    //创建时间
    private Date createTime;
    //创建人
    private Long creatorId;

    private String creator;
    //更改时间
    private Date updateTime;
    //修改人
    private Long updatorId;

    private String updator;

    ///物品类型名称
    private String materialTypeName;

    private String remark;

    private Integer taskGroupStatus;

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

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
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

    public Integer getCheckSpecificId() {
        return checkSpecificId;
    }

    public void setCheckSpecificId(Integer checkSpecificId) {
        this.checkSpecificId = checkSpecificId;
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

    public String getMaterialUrl() {
        return materialUrl;
    }

    public void setMaterialUrl(String materialUrl) {
        this.materialUrl = materialUrl;
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


    public String getMaterialTypeName() {
        return materialTypeName;
    }

    public void setMaterialTypeName(String materialTypeName) {
        this.materialTypeName = materialTypeName;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Long getUpdatorId() {
        return updatorId;
    }

    public void setUpdatorId(Long updatorId) {
        this.updatorId = updatorId;
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator;
    }

    public Integer getTaskGroupStatus() {
        return taskGroupStatus;
    }

    public void setTaskGroupStatus(Integer taskGroupStatus) {
        this.taskGroupStatus = taskGroupStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
