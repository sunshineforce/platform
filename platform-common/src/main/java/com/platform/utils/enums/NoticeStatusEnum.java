package com.platform.utils.enums;

/**
 * Created with IntelliJ IDEA
 * ProjectName: inspect
 * CreateUser:  lixiaopeng
 * CreateTime : 2018/7/28 10:55
 * ModifyUser: bjlixiaopeng
 * Class Description:
 * To change this template use File | Settings | File and Code Template
 */


public enum NoticeStatusEnum {

    UNREAD(0,"未读"),
    READ(1,"已读");

    private Integer code;
    private String desc;

    NoticeStatusEnum(Integer code, String desc) {
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
