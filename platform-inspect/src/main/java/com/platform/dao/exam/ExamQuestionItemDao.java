package com.platform.dao.exam;

import com.platform.dao.BaseDao;
import com.platform.entity.exam.ExamQuestionItemEntity;

/**
 * 试题选项表Dao
 *
 * @author admin
 *  
 * @date 2018-07-24 09:45:45
 */
public interface ExamQuestionItemDao extends BaseDao<ExamQuestionItemEntity> {

    /**
     * 通过题目删除选项
     * @param questionId
     * @return
     */
    int deleteByQid(Long questionId);
}
