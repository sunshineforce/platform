package com.platform.utils.enums;

/**
 * Created with IntelliJ IDEA
 * ProjectName: inspect
 * CreateUser:  lixiaopeng
 * CreateTime : 2018/8/1 20:26
 * ModifyUser: bjlixiaopeng
 * Class Description: 二维码相关枚举
 * To change this template use File | Settings | File and Code Template
 */


public enum QrCodeEnum {

    ///生成状态
    UNGENERATED(0,"未生成"),
    GENERATED(1,"已生成"),


    PENDING(0,"待审核"),
    GRANTED(1,"已发放"),
    REJECT(2,"已驳回");

    private Integer code;
    private String desc;

    QrCodeEnum(Integer code, String desc) {
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
