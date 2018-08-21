package com.platform.entity.notice;

import java.io.Serializable;
import java.util.Date;


/**
 * 消息通知表实体
 * 表名 notice
 * @author admin
 * @date 2018-07-28 10:46:23
 */
public class NoticeEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键Id
    private Long id;
    //消息通知内容
    private String name;
    //通知状态(0:未读;1:已读)
    private Integer status;
    //任务Id
    private Long taskId;
    //登录用户
    private Integer userId;
    //用户Id集合
    private String userIds;
    //创建时间
    private Date createTime;

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
     * 设置：消息通知名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：消息通知名称
     */
    public String getName() {
        return name;
    }
    /**
     * 设置：通知状态(0:未读;1:已读)
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取：通知状态(0:未读;1:已读)
     */
    public Integer getStatus() {
        return status;
    }
    /**
     * 设置：任务Id
     */
    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取：任务Id
     */
    public Long getTaskId() {
        return taskId;
    }
    /**
     * 设置：用户Id
     */
    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    /**
     * 获取：用户Id
     */
    public String getUserIds() {
        return userIds;
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
