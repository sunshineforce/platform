package com.platform.entity.exam;

import java.io.Serializable;


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
    private Long examId;
    //参考人员Id
    private Integer memberId;

    //已答题数量
    private Integer answerNum;

    //得分
    private Double score;

    //姓名
    private String userName;

    //手机号
    private String mobile;

    private String answerProcess;

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

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
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

    public Integer getAnswerNum() {
        return answerNum;
    }

    public void setAnswerNum(Integer answerNum) {
        this.answerNum = answerNum;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAnswerProcess() {
        return answerProcess;
    }

    public void setAnswerProcess(String answerProcess) {
        this.answerProcess = answerProcess;
    }
}
