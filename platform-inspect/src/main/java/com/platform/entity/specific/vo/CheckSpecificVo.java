package com.platform.entity.specific.vo;

import com.platform.vo.SelectVo;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * ProjectName: inspect
 * CreateUser:  lixiaopeng
 * CreateTime : 2018/8/4 15:46
 * ModifyUser: bjlixiaopeng
 * Class Description:检查规范APP端vo
 * To change this template use File | Settings | File and Code Template
 */


public class CheckSpecificVo implements Serializable {
    private static final long serialVersionUID = 4213623044103228937L;

    private Long id;

    //检查规范名称
    private String name;
    //检查规范备注
    private String remark;
    //检查项
    List<SelectVo> itemList;

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<SelectVo> getItemList() {
        return itemList;
    }

    public void setItemList(List<SelectVo> itemList) {
        this.itemList = itemList;
    }
}
