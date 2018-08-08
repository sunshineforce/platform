package com.platform.entity.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * ProjectName: inspect
 * CreateUser:  lixiaopeng
 * CreateTime : 2018/8/8 19:16
 * ModifyUser: bjlixiaopeng
 * Class Description:
 * To change this template use File | Settings | File and Code Template
 */


public class CommonDto implements Serializable {

    private static final long serialVersionUID = -8915860463929860935L;

    //是否成功
    private boolean success;
    //返回数据
    private List data;
    //返回状态码 (200：请求成功 401：未授权，需重新登录 500：请求失败)
    private Integer code;
    //
    private boolean onlyData;
    //请求失败的原因，若请求失败且无该字段则提示“服务器繁忙，请稍后再试”
    private String message;

    public CommonDto() {
    }

    public CommonDto(boolean success, List data, Integer code) {
        this.success = success;
        this.data = data;
        this.code = code;
    }

    public CommonDto(boolean success, List data, Integer code, boolean onlyData) {
        this.success = success;
        this.data = data;
        this.code = code;
        this.onlyData = onlyData;
    }

    public CommonDto(boolean success, List data, Integer code, boolean onlyData, String message) {
        this.success = success;
        this.data = data;
        this.code = code;
        this.onlyData = onlyData;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public boolean isOnlyData() {
        return onlyData;
    }

    public void setOnlyData(boolean onlyData) {
        this.onlyData = onlyData;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "CommonDto{" +
                "success=" + success +
                ", data=" + data +
                ", code=" + code +
                ", onlyData=" + onlyData +
                ", message='" + message + '\'' +
                '}';
    }
}
