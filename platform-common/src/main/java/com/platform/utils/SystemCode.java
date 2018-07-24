package com.platform.utils;

/**
 * Created with IntelliJ IDEA
 * ProjectName: inspect
 * CreateUser:  lixiaopeng
 * CreateTime : 2018/7/24 19:39
 * ModifyUser: bjlixiaopeng
 * Class Description:
 * To change this template use File | Settings | File and Code Template
 */


public enum SystemCode {
    LOGIN_VALID(202,"登录验证失败"),
    PASSWORD_VALID(202,"登录密码不正确"),
    PARAM_ILLEGAL(203,"非法参数");

    private Integer code;
    private String desc;

    SystemCode(Integer code, String desc) {
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
