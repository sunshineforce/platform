package com.platform.entity.specific;

import java.io.Serializable;
import java.util.Date;


/**
 * 规范项条目表实体
 * 表名 check_specific_item
 *
 * @author admin
 *  
 * @date 2018-07-23 20:03:40
 */
public class CheckSpecificItemEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键id
    private Integer id;
    //规范id
    private Integer specificId;
    //规范项名称
    private String name;
    //数据状态 0 正常 1删除
    private Integer dataStatus;

    /**
     * 设置：主键id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取：主键id
     */
    public Integer getId() {
        return id;
    }
    /**
     * 设置：规范id
     */
    public void setSpecificId(Integer specificId) {
        this.specificId = specificId;
    }

    /**
     * 获取：规范id
     */
    public Integer getSpecificId() {
        return specificId;
    }
    /**
     * 设置：规范项名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：规范项名称
     */
    public String getName() {
        return name;
    }
    /**
     * 设置：数据状态 0 正常 1删除
     */
    public void setDataStatus(Integer dataStatus) {
        this.dataStatus = dataStatus;
    }

    /**
     * 获取：数据状态 0 正常 1删除
     */
    public Integer getDataStatus() {
        return dataStatus;
    }
}
