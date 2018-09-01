package com.platform.entity.task.vo;

import java.io.Serializable;

/**
 * @author bjsonghongxu
 * @create 2018-09-01 16:02
 **/
public class TaskRelVo implements Serializable {

    private Integer regionId;

    private String name;

    private String info;

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
