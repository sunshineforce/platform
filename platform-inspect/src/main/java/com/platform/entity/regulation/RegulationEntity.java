package com.platform.entity.regulation;

import java.io.Serializable;
import java.util.Date;


/**
 * 法规管理表实体
 * 表名 regulation
 *
 * @author admin
 *  
 * @date 2018-07-24 10:28:40
 */
public class RegulationEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键Id
    private Long id;
    //知识名称
    private String knowledgeName;
    //描述
    private String description;
    //预览图片url
    private String url;
    //知识内容方式(0：自主编辑；1：外部链接)
    private Integer type;
    //自主编辑知识内容
    private String content;
    //知识外部链接地址
    private String link;
    //关联考试
    private String relation;
    //创建时间
    private Date createTime;
    //创建人
    private Long creatorId;

    private String creator;
    //更改时间
    private Date updateTime;
    //修改人
    private Long updatorId;

    private String updator;

    /**
     * 设置：主键Id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取：主键Id
     */
    public Long getId() {
        return id;
    }
    /**
     * 设置：知识名称
     */
    public void setKnowledgeName(String knowledgeName) {
        this.knowledgeName = knowledgeName;
    }

    /**
     * 获取：知识名称
     */
    public String getKnowledgeName() {
        return knowledgeName;
    }
    /**
     * 设置：描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取：描述
     */
    public String getDescription() {
        return description;
    }
    /**
     * 设置：预览图片url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取：预览图片url
     */
    public String getUrl() {
        return url;
    }
    /**
     * 设置：知识内容方式(0：自主编辑；1：外部链接)
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取：知识内容方式(0：自主编辑；1：外部链接)
     */
    public Integer getType() {
        return type;
    }
    /**
     * 设置：自主编辑知识内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取：自主编辑知识内容
     */
    public String getContent() {
        return content;
    }
    /**
     * 设置：知识外部链接地址
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * 获取：知识外部链接地址
     */
    public String getLink() {
        return link;
    }
    /**
     * 设置：关联考试
     */
    public void setRelation(String relation) {
        this.relation = relation;
    }

    /**
     * 获取：关联考试
     */
    public String getRelation() {
        return relation;
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
     * 设置：更改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取：更改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }


    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }


    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
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
