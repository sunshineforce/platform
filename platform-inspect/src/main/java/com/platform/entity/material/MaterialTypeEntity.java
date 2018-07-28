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
    private String name;
    //上级类目Id
    private Integer parentId;
    //上级类目
    private String parentName;
    //备注
    private String remark;
    //创建时间
    private Date createTime;
    //创建人
    private Long creatorId;

    private String creator;
    //最后修改时间
    private Date updateTime;
    //修改人
    private Long updatorId;

    private String updator;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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


    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Long getUpdatorId() {
        return updatorId;
    }

    public void setUpdatorId(Long updatorId) {
        this.updatorId = updatorId;
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator;
    }
}
