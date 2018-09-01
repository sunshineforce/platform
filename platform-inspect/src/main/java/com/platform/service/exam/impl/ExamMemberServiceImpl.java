package com.platform.service.exam.impl;


import com.platform.dao.exam.ExamMemberDao;
import com.platform.entity.AppUserEntity;
import com.platform.entity.exam.ExamMemberEntity;
import com.platform.service.common.CommonService;
import com.platform.service.exam.ExamMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 考试参考人员Service实现类
 *
 * @author admin
 *  
 * @date 2018-07-24 09:45:45
 */
@Service("examMemberService")
public class ExamMemberServiceImpl implements ExamMemberService {
    @Autowired
    private ExamMemberDao examMemberDao;

    @Autowired
    private CommonService commonService;

    @Override
    public ExamMemberEntity queryObject(Long id) {
        return examMemberDao.queryObject(id);
    }

    @Override
    public List<ExamMemberEntity> queryList(Map<String, Object> map) {
        return examMemberDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return examMemberDao.queryTotal(map);
    }

    @Override
    public int save(ExamMemberEntity examMember) {
        return examMemberDao.save(examMember);
    }

    @Override
    public int update(ExamMemberEntity examMember) {
        return examMemberDao.update(examMember);
    }

    @Override
    public int delete(Long id) {
        return examMemberDao.delete(id);
    }

    @Override
    public int deleteBatch(Long[]ids) {
        return examMemberDao.deleteBatch(ids);
    }

    @Override
    public List<ExamMemberEntity> queryExamMembers(Map<String, Object> map) {
        return examMemberDao.selectExamMembers(map);
    }

    @Override
    public int queryExamMembersTotal(Map<String, Object> map) {
        return examMemberDao.selectExamMembersTotal(map);
    }

    @Override
    public ExamMemberEntity queryExamMember(ExamMemberEntity examMemberEntity) {
        return examMemberDao.queryExamMember(examMemberEntity);
    }

    @Override
    public List<ExamMemberEntity> queryExamTodoList() {
        AppUserEntity appUser = commonService.getCurrentLoginUser();
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("memberId",appUser.getId());
        return examMemberDao.queryList(params);
    }
}
