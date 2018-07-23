package com.platform.entity.material;

import java.io.Serializable;
import java.util.Date;


/**
 * 物品类型表实体
 * 表名 material_type
 *
 * @author admin
 *  
 * @date 2018-07-23 11:11:45
 */
public class MaterialTypeEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键Id
    private Integer id;
    //物品名称
    private String materialTypeName;
    //上级类目Id
    private Integer parentId;
    //备注
    private String remark;
    //创建时间
    private Date createTime;
    //创建人
    private Integer creator;
    //最后修改时间
    private Date updateTime;
    //最后修改人
    private Integer updator;

    /**
     * 设置：主键Id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取：主键Id
     */
    public Integer getId() {
        return id;
    }
    /**
     * 设置：物品名称
     */
    public void setMaterialTypeName(String materialTypeName) {
        this.materialTypeName = materialTypeName;
    }

    /**
     * 获取：物品名称
     */
    public String getMaterialTypeName() {
        return materialTypeName;
    }
    /**
     * 设置：上级类目Id
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取：上级类目Id
     */
    public Integer getParentId() {
        return parentId;
    }
    /**
     * 设置：备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取：备注
     */
    public String getRemark() {
        return remark;
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
    /**
     * 设置：创建人
     */
    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    /**
     * 获取：创建人
     */
    public Integer getCreator() {
        return creator;
    }
    /**
     * 设置：最后修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取：最后修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }
    /**
     * 设置：最后修改人
     */
    public void setUpdator(Integer updator) {
        this.updator = updator;
    }

    /**
     * 获取：最后修改人
     */
    public Integer getUpdator() {
        return updator;
    }
}
