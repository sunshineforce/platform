package com.platform.entity.task.vo;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA
 * ProjectName: inspect
 * CreateUser:  lixiaopeng
 * CreateTime : 2018/7/30 20:29
 * ModifyUser: bjlixiaopeng
 * Class Description:
 * To change this template use File | Settings | File and Code Template
 */


public class TaskStatisticsVo implements Serializable {

    private static final long serialVersionUID = -9161908361298897190L;

    private Integer id;
    //物料类型名称
    private String materialType;
    //区域名称
    private String region;
    //总数量
    private Integer total;
    //完成数量
    private Integer finish;
    //异常数量
    private Integer anomaly;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getFinish() {
        return finish;
    }

    public void setFinish(Integer finish) {
        this.finish = finish;
    }

    public Integer getAnomaly() {
        return anomaly;
    }

    public void setAnomaly(Integer anomaly) {
        this.anomaly = anomaly;
    }
}
