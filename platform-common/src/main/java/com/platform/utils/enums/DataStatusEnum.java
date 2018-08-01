package com.platform.utils.enums;

/**
 * Created with IntelliJ IDEA
 * ProjectName: inspect
 * CreateUser:  lixiaopeng
 * CreateTime : 2018/8/1 20:26
 * ModifyUser: bjlixiaopeng
 * Class Description: 数据状态枚举
 * To change this template use File | Settings | File and Code Template
 */


public enum DataStatusEnum {
    UNREAD(0,"正常"),
    READ(1,"删除");

    private Integer code;
    private String desc;

    DataStatusEnum(Integer code, String desc) {
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
