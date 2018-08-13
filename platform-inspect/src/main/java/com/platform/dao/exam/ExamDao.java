package com.platform.dao.exam;

import com.platform.dao.BaseDao;
import com.platform.entity.exam.ExamEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 试题管理表Dao
 *
 * @author admin
 *  
 * @date 2018-07-24 09:45:45
 */

@Repository
public interface ExamDao extends BaseDao<ExamEntity> {

    /**
     * 通过考试人员查找考试信息
     * @param map
     * @return
     */
    List<ExamEntity> queryExamListByUserId( Map<String, Object> map);

    /**
     * 通过考试人员查找考试信息总条数
     * @param map
     * @return
     */
    int queryExamListByUserIdTotal(Map<String, Object> map);

}
