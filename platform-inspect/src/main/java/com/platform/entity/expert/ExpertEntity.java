package com.platform.entity.expert;

import java.io.Serializable;
import java.util.Date;


/**
 * 专家库表实体
 * 表名 t_expert
 *
 * @author admin
 *  
 * @date 2018-07-20 18:08:44
 */
public class ExpertEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Integer id;
    //专家名称
    private String expertName;
    //职称
    private String jobTitle;
    //手机号
    private String mobile;
    //擅长领域
    private String skill;
    //头像
    private String avatar;
    //创建时间
    private Date createTime;
    //创建人
    private String creator;
    //修改时间
    private Date updateTime;
    //修改人
    private String updator;

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
     * 设置：专家名称
     */
    public void setExpertName(String expertName) {
        this.expertName = expertName;
    }

    /**
     * 获取：专家名称
     */
    public String getExpertName() {
        return expertName;
    }
    /**
     * 设置：职称
     */
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    /**
     * 获取：职称
     */
    public String getJobTitle() {
        return jobTitle;
    }
    /**
     * 设置：手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取：手机号
     */
    public String getMobile() {
        return mobile;
    }
    /**
     * 设置：擅长领域
     */
    public void setSkill(String skill) {
        this.skill = skill;
    }

    /**
     * 获取：擅长领域
     */
    public String getSkill() {
        return skill;
    }
    /**
     * 设置：头像
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 获取：头像
     */
    public String getAvatar() {
        return avatar;
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
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * 获取：创建人
     */
    public String getCreator() {
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
    public void setUpdator(String updator) {
        this.updator = updator;
    }

    /**
     * 获取：修改人
     */
    public String getUpdator() {
        return updator;
    }

    @Override
    public String toString() {
        return "ExpertEntity{" +
                "id=" + id +
                ", expertName='" + expertName + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", mobile='" + mobile + '\'' +
                ", skill='" + skill + '\'' +
                ", avatar='" + avatar + '\'' +
                ", createTime=" + createTime +
                ", creator=" + creator +
                ", updateTime=" + updateTime +
                ", updator=" + updator +
                '}';
    }
}
