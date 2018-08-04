package com.platform.entity.notice.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA
 * ProjectName: inspect
 * CreateUser:  lixiaopeng
 * CreateTime : 2018/8/4 18:20
 * ModifyUser: bjlixiaopeng
 * Class Description: 消息通知返回对象
 * To change this template use File | Settings | File and Code Template
 */

public class NoticeVo implements Serializable {
    private static final long serialVersionUID = 4551583204453904260L;

    //发送内容
    private String content;
    //发送时间
    private Date createTime;

    public NoticeVo() {
    }

    public NoticeVo(String content, Date createTime) {
        this.content = content;
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
