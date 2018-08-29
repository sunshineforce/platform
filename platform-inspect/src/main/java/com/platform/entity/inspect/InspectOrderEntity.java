package com.platform.entity.inspect;

import com.platform.entity.material.MaterialEntity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 安全巡检异常工单表实体
 * 表名 inspect_order
 *
 * @author admin
 *  
 * @date 2018-07-22 21:48:11
 */
public class InspectOrderEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键ID
    private Integer id;
    //物料ID
    private Integer materialId;

    //物品类型Id
    private Integer materialTypeId;

    //任务组id
    private Integer taskDetailId;

    //任务组id
    private Integer taskGroupId;

    //物品所属区域Id
    private Long regionId;

    /** 用户所属企业 */
    private Integer enterpriseId;

    //工单状态 0 正常  1 异常
    private Integer status;
    //检查时间
    private Date inspectTime;
    //检查人
    private Integer userId;
    //检查人
    private String userName;
    //检查结果 0 正常  1 异常
    private Integer inspectStatus;
    //上级id
    private String chiefIds;
    //上级姓名
    private String chiefName;
    //现场照片
    private String photos;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //数据状态 0 正常 1 删除
    private Integer dataStatus;

    private String descr;


    /////业务属性

    //物品信息
    private MaterialEntity material;

    //异常处理信息
    private List<InspectOrderFlowEntity> orderFlows;

    //检查项信息
    private List<InspectOrderRelSpecificEntity> specificList;

    //物品名称
    private String  materialName;

    /**
     * 设置：主键ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取：主键ID
     */
    public Integer getId() {
        return id;
    }
    /**
     * 设置：物料ID
     */
    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    /**
     * 获取：物料ID
     */
    public Integer getMaterialId() {
        return materialId;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    /**
     * 设置：工单状态 0 正常  1 异常
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取：工单状态 0 正常  1 异常
     */
    public Integer getStatus() {
        return status;
    }
    /**
     * 设置：检查时间
     */
    public void setInspectTime(Date inspectTime) {
        this.inspectTime = inspectTime;
    }

    /**
     * 获取：检查时间
     */
    public Date getInspectTime() {
        return inspectTime;
    }
    /**
     * 设置：检查人
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取：检查人
     */
    public Integer getUserId() {
        return userId;
    }
    /**
     * 设置：检查人
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取：检查人
     */
    public String getUserName() {
        return userName;
    }
    /**
     * 设置：检查结果 0 正常  1 异常
     */
    public void setInspectStatus(Integer inspectStatus) {
        this.inspectStatus = inspectStatus;
    }

    /**
     * 获取：检查结果 0 正常  1 异常
     */
    public Integer getInspectStatus() {
        return inspectStatus;
    }

    /**
     * 设置：上级姓名
     */
    public void setChiefName(String chiefName) {
        this.chiefName = chiefName;
    }

    /**
     * 获取：上级姓名
     */
    public String getChiefName() {
        return chiefName;
    }
    /**
     * 设置：现场照片
     */
    public void setPhotos(String photos) {
        this.photos = photos;
    }

    /**
     * 获取：现场照片
     */
    public String getPhotos() {
        return photos;
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
     * 设置：数据状态 0 正常 1 删除
     */
    public void setDataStatus(Integer dataStatus) {
        this.dataStatus = dataStatus;
    }

    /**
     * 获取：数据状态 0 正常 1 删除
     */
    public Integer getDataStatus() {
        return dataStatus;
    }

    public MaterialEntity getMaterial() {
        return material;
    }

    public void setMaterial(MaterialEntity material) {
        this.material = material;
    }

    public List<InspectOrderFlowEntity> getOrderFlows() {
        return orderFlows;
    }

    public void setOrderFlows(List<InspectOrderFlowEntity> orderFlows) {
        this.orderFlows = orderFlows;
    }

    public List<InspectOrderRelSpecificEntity> getSpecificList() {
        return specificList;
    }

    public void setSpecificList(List<InspectOrderRelSpecificEntity> specificList) {
        this.specificList = specificList;
    }

    public String getChiefIds() {
        return chiefIds;
    }

    public void setChiefIds(String chiefIds) {
        this.chiefIds = chiefIds;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Integer getMaterialTypeId() {
        return materialTypeId;
    }

    public void setMaterialTypeId(Integer materialTypeId) {
        this.materialTypeId = materialTypeId;
    }

    public Integer getTaskDetailId() {
        return taskDetailId;
    }

    public void setTaskDetailId(Integer taskDetailId) {
        this.taskDetailId = taskDetailId;
    }

    public Integer getTaskGroupId() {
        return taskGroupId;
    }

    public void setTaskGroupId(Integer taskGroupId) {
        this.taskGroupId = taskGroupId;
    }
}
