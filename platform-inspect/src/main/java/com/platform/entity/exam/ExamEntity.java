package com.platform.entity.exam;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


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
    private String member;
    //创建时间
    private Date createTime;
    //创建人
    private String creator;


    private Long creatorId;

    private Long updatorId;

    //修改时间
    private Date updateTime;
    //修改人
    private String updator;
    //是否启用（0：启用；1：禁用）
    private Integer enabled;

    private Double totalScore;

    private Integer  questionNum;


    private String questionJson;

    private List<ExamQuestionEntity> questionList;

    //考试状态  0 未开始 1 进行中 2 已完成 3 已结束
    private Integer status;

    //当前人分数
    private Double score = 0.0;


    //已答题数量
    private Integer answerNum;


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

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
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

    public String getQuestionJson() {
        return questionJson;
    }

    public void setQuestionJson(String questionJson) {
        this.questionJson = questionJson;
    }

    public List<ExamQuestionEntity> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<ExamQuestionEntity> questionList) {
        this.questionList = questionList;
    }

    public Double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Double totalScore) {
        this.totalScore = totalScore;
    }

    public Integer getQuestionNum() {
        return questionNum;
    }

    public void setQuestionNum(Integer questionNum) {
        this.questionNum = questionNum;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getAnswerNum() {
        return answerNum;
    }

    public void setAnswerNum(Integer answerNum) {
        this.answerNum = answerNum;
    }
}
