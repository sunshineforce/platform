package com.platform.entity.regulation;

import java.io.Serializable;
import java.util.Date;


/**
 * 实体
 * 表名 user_regulation_rel
 *
 * @author admin
 *  
 * @date 2018-08-19 17:34:20
 */
public class UserRegulationRelEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键Id
    private Integer id;
    //用户id
    private Long userId;
    //法律法规主键Id
    private Long knowledgeId;
    //收藏时间
    private Date createTime;

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
     * 设置：用户id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取：用户id
     */
    public Long getUserId() {
        return userId;
    }
    /**
     * 设置：法律法规主键Id
     */
    public void setKnowledgeId(Long knowledgeId) {
        this.knowledgeId = knowledgeId;
    }

    /**
     * 获取：法律法规主键Id
     */
    public Long getKnowledgeId() {
        return knowledgeId;
    }
    /**
     * 设置：收藏时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取：收藏时间
     */
    public Date getCreateTime() {
        return createTime;
    }
}
