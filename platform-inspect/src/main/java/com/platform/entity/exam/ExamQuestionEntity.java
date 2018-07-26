package com.platform.entity.exam;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 试题题目表实体
 * 表名 exam_question
 *
 * @author admin
 *  
 * @date 2018-07-24 09:45:45
 */
public class ExamQuestionEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Long id;
    //试题Id
    private Long examId;
    //题目分值
    private Double score;
    //题目
    private String question;
    //答案
    private String answer;
    //题目选项
    private Integer item;
    //创建时间
    private Date createTime;
    //创建人
    private Integer creator;

    private List<ExamQuestionItemEntity> questionItems;

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

    public List<ExamQuestionItemEntity> getQuestionItems() {
        return questionItems;
    }

    public void setQuestionItems(List<ExamQuestionItemEntity> questionItems) {
        this.questionItems = questionItems;
    }

    /**
     * 设置：题目分值
     */
    public void setScore(Double score) {
        this.score = score;
    }

    /**
     * 获取：题目分值
     */
    public Double getScore() {
        return score;
    }
    /**
     * 设置：题目
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * 获取：题目
     */
    public String getQuestion() {
        return question;
    }
    /**
     * 设置：答案
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * 获取：答案
     */
    public String getAnswer() {
        return answer;
    }
    /**
     * 设置：题目选项
     */
    public void setItem(Integer item) {
        this.item = item;
    }

    /**
     * 获取：题目选项
     */
    public Integer getItem() {
        return item;
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


}
