package com.platform.entity.exam;

import java.io.Serializable;
import java.util.Date;


/**
 * 试题管理表实体
 * 表名 exam
 *
 * @author admin
 *  
 * @date 2018-07-24 09:45:45
 */
public class ExamEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键Id
    private Long id;
    //试题名称
    private String examName;
    //试题介绍
    private String introduce;
    //开始时间
    private Date beginTime;
    //结束时间
    private Date endTime;
    //参考人员
    private Integer member;
    //创建时间
    private Date createTime;
    //创建人
    private Integer creator;
    //修改时间
    private Date updateTime;
    //修改人
    private Integer updator;
    //是否启用（0：启用；1：禁用）
    private Integer enabled;

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
     * 设置：试题名称
     */
    public void setExamName(String examName) {
        this.examName = examName;
    }

    /**
     * 获取：试题名称
     */
    public String getExamName() {
        return examName;
    }
    /**
     * 设置：试题介绍
     */
    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    /**
     * 获取：试题介绍
     */
    public String getIntroduce() {
        return introduce;
    }
    /**
     * 设置：开始时间
     */
    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    /**
     * 获取：开始时间
     */
    public Date getBeginTime() {
        return beginTime;
    }
    /**
     * 设置：结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取：结束时间
     */
    public Date getEndTime() {
        return endTime;
    }
    /**
     * 设置：参考人员
     */
    public void setMember(Integer member) {
        this.member = member;
    }

    /**
     * 获取：参考人员
     */
    public Integer getMember() {
        return member;
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
     * 设置：修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取：修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }
    /**
     * 设置：修改人
     */
    public void setUpdator(Integer updator) {
        this.updator = updator;
    }

    /**
     * 获取：修改人
     */
    public Integer getUpdator() {
        return updator;
    }
    /**
     * 设置：是否启用（0：启用；1：禁用）
     */
    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    /**
     * 获取：是否启用（0：启用；1：禁用）
     */
    public Integer getEnabled() {
        return enabled;
    }
}
