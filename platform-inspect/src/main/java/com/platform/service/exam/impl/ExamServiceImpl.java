package com.platform.service.exam.impl;

import com.platform.dao.exam.ExamDao;
import com.platform.entity.exam.ExamEntity;
import com.platform.service.exam.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



/**
 * 试题管理表Service实现类
 *
 * @author admin
 *  
 * @date 2018-07-24 09:45:45
 */
@Service("examService")
public class ExamServiceImpl implements ExamService {
    @Autowired
    private ExamDao examDao;

    @Override
    public ExamEntity queryObject(Long id) {
        return examDao.queryObject(id);
    }

    @Override
    public List<ExamEntity> queryList(Map<String, Object> map) {
        return examDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return examDao.queryTotal(map);
    }

    @Override
    public int save(ExamEntity exam) {
        return examDao.save(exam);
    }

    @Override
    public int update(ExamEntity exam) {
        return examDao.update(exam);
    }

    @Override
    public int delete(Long id) {
        return examDao.delete(id);
    }

    @Override
    public int deleteBatch(Long[]ids) {
        return examDao.deleteBatch(ids);
    }

    @Override
    public List<ExamEntity> queryExamListByUserId(Map<String, Object> map) {
        return examDao.queryExamListByUserId(map);
    }

    @Override
    public int queryExamListByUserIdTotal(Map<String, Object> map) {
        return examDao.queryExamListByUserIdTotal(map);
    }


}
