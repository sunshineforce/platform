package com.platform.entity.task.vo;

import java.io.Serializable;

/**
 * 任务统计Vo
 *
 * @author bjsonghongxu
 * @create 2018-08-29 15:45
 **/
public class TaskDetailStatVo implements Serializable {

    //物品类型名称
    private String name;

    //物品类型id
    private Integer materialTypeId;

    //进度
    private String progress;

    //正常数量
    private Integer nomarlNum;

    //异常数量
    private Integer exceptNum;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMaterialTypeId() {
        return materialTypeId;
    }

    public void setMaterialTypeId(Integer materialTypeId) {
        this.materialTypeId = materialTypeId;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public Integer getNomarlNum() {
        return nomarlNum;
    }

    public void setNomarlNum(Integer nomarlNum) {
        this.nomarlNum = nomarlNum;
    }

    public Integer getExceptNum() {
        return exceptNum;
    }

    public void setExceptNum(Integer exceptNum) {
        this.exceptNum = exceptNum;
    }
}
