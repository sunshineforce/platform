package com.platform.entity.dto;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA
 * ProjectName: inspect
 * CreateUser:  lixiaopeng
 * CreateTime : 2018/8/24 16:34
 * ModifyUser: bjlixiaopeng
 * Class Description:
 * To change this template use File | Settings | File and Code Template
 */


public class EnterpriseMaterialsVo implements Serializable {

    private static final long serialVersionUID = 4060555747033077318L;

    //客户名称
    private String customerName;
    //客户电话
    private String phone;
    //客户区域
    private String addrName;
    //客户详细地址
    private String addrDetail;
}
