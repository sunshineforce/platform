package com.platform.utils.enums;

/**
 * Created with IntelliJ IDEA
 * ProjectName: inspect
 * CreateUser:  lixiaopeng
 * CreateTime : 2018/7/28 12:00
 * ModifyUser: bjlixiaopeng
 * Class Description: 物料类型枚举
 * To change this template use File | Settings | File and Code Template
 */


public enum MaterialStatusEnum {
    UNCHECK(0,"未检查"),
    NORMAL(1,"正常"),
    ANOMALY(2,"异常"),
    BROKEN(3,"报废"),
    FINISHED(4,"完成");

    private Integer code;
    private String desc;

    MaterialStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
