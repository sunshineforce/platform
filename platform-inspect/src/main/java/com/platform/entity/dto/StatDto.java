package com.platform.entity.dto;

/**
 * @author bjsonghongxu
 * @create 2018-08-19 15:54
 **/
public class StatDto {

    private Integer regionId; // 区域Id

    //状态
    private Integer status;

    //数量
    private int num = 0;


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }
}
