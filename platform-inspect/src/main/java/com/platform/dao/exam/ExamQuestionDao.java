package com.platform.dao.exam;

import com.platform.dao.BaseDao;
import com.platform.entity.exam.ExamQuestionEntity;

/**
 * 试题题目表Dao
 *
 * @author admin
 *  
 * @date 2018-07-24 09:45:45
 */
public interface ExamQuestionDao extends BaseDao<ExamQuestionEntity> {

    /**
     * 根据考试删除所有题目
     * @param examId
     * @return
     */
    int deleteByExamId(Long examId);
}
