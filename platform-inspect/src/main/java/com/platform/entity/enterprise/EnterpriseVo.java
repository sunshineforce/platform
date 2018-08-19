package com.platform.entity.enterprise;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA
 * ProjectName: inspect
 * CreateUser:  lixiaopeng
 * CreateTime : 2018/8/19 14:29
 * ModifyUser: bjlixiaopeng
 * Class Description:
 * To change this template use File | Settings | File and Code Template
 */


public class EnterpriseVo implements Serializable {

    private static final long serialVersionUID = -2479898062291705657L;

    private Integer id;
    private String enterpriseName;

    public EnterpriseVo() {
    }

    public EnterpriseVo(Integer id, String enterpriseName) {
        this.id = id;
        this.enterpriseName = enterpriseName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }
}
