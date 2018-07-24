package com.platform.service.exam.impl;

import com.platform.dao.exam.ExamQuestionItemDao;
import com.platform.entity.exam.ExamQuestionItemEntity;
import com.platform.service.exam.ExamQuestionItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



/**
 * 试题选项表Service实现类
 *
 * @author admin
 *  
 * @date 2018-07-24 09:45:45
 */
@Service("examQuestionItemService")
public class ExamQuestionItemServiceImpl implements ExamQuestionItemService {
    @Autowired
    private ExamQuestionItemDao examQuestionItemDao;

    @Override
    public ExamQuestionItemEntity queryObject(Long id) {
        return examQuestionItemDao.queryObject(id);
    }

    @Override
    public List<ExamQuestionItemEntity> queryList(Map<String, Object> map) {
        return examQuestionItemDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return examQuestionItemDao.queryTotal(map);
    }

    @Override
    public int save(ExamQuestionItemEntity examQuestionItem) {
        return examQuestionItemDao.save(examQuestionItem);
    }

    @Override
    public int update(ExamQuestionItemEntity examQuestionItem) {
        return examQuestionItemDao.update(examQuestionItem);
    }

    @Override
    public int delete(Long id) {
        return examQuestionItemDao.delete(id);
    }

    @Override
    public int deleteBatch(Long[]ids) {
        return examQuestionItemDao.deleteBatch(ids);
    }
}
