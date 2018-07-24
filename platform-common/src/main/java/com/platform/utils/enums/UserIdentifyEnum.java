package com.platform.utils.enums;

/**
 * Created with IntelliJ IDEA
 * ProjectName: inspect
 * CreateUser:  lixiaopeng
 * CreateTime : 2018/7/24 20:08
 * ModifyUser: bjlixiaopeng
 * Class Description:
 * To change this template use File | Settings | File and Code Template
 */


public enum UserIdentifyEnum {
    SAFETY_OFFICER(0,"安全员"),
    LEADER(1,"领导");

    private Integer code;
    private String identify;

    UserIdentifyEnum(Integer code, String identify) {
        this.code = code;
        this.identify = identify;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getIdentify() {
        return identify;
    }

    public void setIdentify(String identify) {
        this.identify = identify;
    }
}
