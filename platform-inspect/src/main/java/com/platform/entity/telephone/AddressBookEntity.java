package com.platform.entity.telephone;

import java.io.Serializable;
import java.util.Date;


/**
 * 部门通讯录实体
 * 表名 address_book
 * @author admin
 * @date 2018-07-31 22:38:00
 */
public class AddressBookEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Integer id;
    //姓名
    private String name;
    //电话
    private String mobile;
    //职位
    private String position;
    //创建时间
    private Date createTime;

    /**
     * 设置：主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取：主键
     */
    public Integer getId() {
        return id;
    }
    /**
     * 设置：姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：姓名
     */
    public String getName() {
        return name;
    }
    /**
     * 设置：电话
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取：电话
     */
    public String getMobile() {
        return mobile;
    }
    /**
     * 设置：职位
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * 获取：职位
     */
    public String getPosition() {
        return position;
    }
    /**
     * 设置：创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取：创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }
}
