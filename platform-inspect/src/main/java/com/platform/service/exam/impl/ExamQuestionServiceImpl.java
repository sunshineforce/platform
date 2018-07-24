package com.platform.service.exam.impl;

import com.platform.dao.exam.ExamQuestionDao;
import com.platform.entity.exam.ExamQuestionEntity;
import com.platform.service.exam.ExamQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



/**
 * 试题题目表Service实现类
 *
 * @author admin
 *  
 * @date 2018-07-24 09:45:45
 */
@Service("examQuestionService")
public class ExamQuestionServiceImpl implements ExamQuestionService {
    @Autowired
    private ExamQuestionDao examQuestionDao;

    @Override
    public ExamQuestionEntity queryObject(Long id) {
        return examQuestionDao.queryObject(id);
    }

    @Override
    public List<ExamQuestionEntity> queryList(Map<String, Object> map) {
        return examQuestionDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return examQuestionDao.queryTotal(map);
    }

    @Override
    public int save(ExamQuestionEntity examQuestion) {
        return examQuestionDao.save(examQuestion);
    }

    @Override
    public int update(ExamQuestionEntity examQuestion) {
        return examQuestionDao.update(examQuestion);
    }

    @Override
    public int delete(Long id) {
        return examQuestionDao.delete(id);
    }

    @Override
    public int deleteBatch(Long[]ids) {
        return examQuestionDao.deleteBatch(ids);
    }
}
