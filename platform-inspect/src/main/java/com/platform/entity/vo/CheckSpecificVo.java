package com.platform.entity.vo;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA
 * ProjectName: inspect
 * CreateUser:  lixiaopeng
 * CreateTime : 2018/7/28 15:26
 * ModifyUser: bjlixiaopeng
 * Class Description:
 * To change this template use File | Settings | File and Code Template
 */


public class CheckSpecificVo implements Serializable {
    private static final long serialVersionUID = 4869912229957397175L;

    private Long id;
    private String name;

    public CheckSpecificVo() {
    }

    public CheckSpecificVo(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
