package com.platform.entity.exam;

import java.io.Serializable;
import java.util.Date;


/**
 * 试题选项表实体
 * 表名 exam_question_item
 *
 * @author admin
 *  
 * @date 2018-07-24 09:45:45
 */
public class ExamQuestionItemEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键Id
    private Long id;
    //题目Id
    private Long questionId;
    //题目选项
    private String item;

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
     * 设置：题目Id
     */
    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    /**
     * 获取：题目Id
     */
    public Long getQuestionId() {
        return questionId;
    }
    /**
     * 设置：题目选项
     */
    public void setItem(String item) {
        this.item = item;
    }

    /**
     * 获取：题目选项
     */
    public String getItem() {
        return item;
    }
}
