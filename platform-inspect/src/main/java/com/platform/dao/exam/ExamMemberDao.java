package com.platform.dao.exam;

import com.platform.dao.BaseDao;
import com.platform.entity.exam.ExamMemberEntity;

import java.util.List;
import java.util.Map;

/**
 * 考试参考人员Dao
 *
 * @author admin
 *  
 * @date 2018-07-24 09:45:45
 */
public interface ExamMemberDao extends BaseDao<ExamMemberEntity> {

    /**
     * 查找考试人员考试信息
     * @param map
     * @return
     */
    List<ExamMemberEntity> selectExamMembers(Map<String, Object> map);

    /**
     * 总条数
     * @param map
     * @return
     */
    int selectExamMembersTotal(Map<String, Object> map);
}
