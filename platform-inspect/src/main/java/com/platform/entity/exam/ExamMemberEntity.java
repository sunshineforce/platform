package com.platform.entity.exam;

import java.io.Serializable;
import java.util.Date;


/**
 * 考试参考人员实体
 * 表名 exam_member
 *
 * @author admin
 *  
 * @date 2018-07-24 09:45:45
 */
public class ExamMemberEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Long id;
    //试题Id
    private Integer examId;
    //参考人员Id
    private Integer memberId;

    /**
     * 设置：主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取：主键
     */
    public Long getId() {
        return id;
    }
    /**
     * 设置：试题Id
     */
    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    /**
     * 获取：试题Id
     */
    public Integer getExamId() {
        return examId;
    }
    /**
     * 设置：参考人员Id
     */
    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    /**
     * 获取：参考人员Id
     */
    public Integer getMemberId() {
        return memberId;
    }
}
