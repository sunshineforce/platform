package com.platform.utils.enums;

/**
 * Created with IntelliJ IDEA
 * ProjectName: inspect
 * CreateUser:  lixiaopeng
 * CreateTime : 2018/8/1 20:24
 * ModifyUser: bjlixiaopeng
 * Class Description: 异常枚举
 * To change this template use File | Settings | File and Code Template
 */


public enum InspectOrderStatusEnum {
    PENDING(0,"待处理"),
    REVIEW(1,"待复查"),
    FINISHED(2,"已完成");

    private Integer code;
    private String desc;

    InspectOrderStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static String getDesc(Integer value){
        for (InspectOrderStatusEnum c : InspectOrderStatusEnum.values()) {
            if (c.getCode() == value) {
                return c.desc;
            }
        }
        return "";
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
