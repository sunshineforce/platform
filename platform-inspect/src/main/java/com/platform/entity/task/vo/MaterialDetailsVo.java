package com.platform.entity.task.vo;

import java.io.Serializable;

public class MaterialDetailsVo implements Serializable{

    private static final long serialVersionUID = -1765749262393595802L;

    //物品主键Id
    private Long id;
    //物品名称
    private String name;
    //物品状态
    private Integer status;
    //物品状态显示
    private String materialStatus;
    //区域Id
    private Integer regionId;
    //物品位置
    private String location;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMaterialStatus() {
        return materialStatus;
    }

    public void setMaterialStatus(String materialStatus) {
        this.materialStatus = materialStatus;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
