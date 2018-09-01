package com.platform.utils.enums;

/**
 * Created with IntelliJ IDEA
 * ProjectName: inspect
 * CreateUser:  lixiaopeng
 * CreateTime : 2018/8/11 17:10
 * ModifyUser: bjlixiaopeng
 * Class Description:
 * To change this template use File | Settings | File and Code Template
 */


public enum TaskStatusEnum {
    PENDING(0,"待执行"),
    EXECUTING(1,"执行中"),
    FINISHED(2,"已完成"),
    TIMEOUT(3,"已超时");

    private Integer code;
    private String desc;

    TaskStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static String getDesc(Integer value){
        for (TaskStatusEnum c : TaskStatusEnum.values()) {
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
