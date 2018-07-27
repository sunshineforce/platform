package com.platform.service.exam;


import com.platform.entity.exam.ExamQuestionItemEntity;

import java.util.List;
import java.util.Map;

/**
 * 试题选项表Service接口
 *
 * @author admin
 *  
 * @date 2018-07-24 09:45:45
 */
public interface ExamQuestionItemService {

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    ExamQuestionItemEntity queryObject(Long id);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<ExamQuestionItemEntity> queryList(Map<String, Object> map);

    /**
     * 分页统计总数
     *
     * @param map 参数
     * @return 总数
     */
    int queryTotal(Map<String, Object> map);

    /**
     * 保存实体
     *
     * @param examQuestionItem 实体
     * @return 保存条数
     */
    int save(ExamQuestionItemEntity examQuestionItem);

    /**
     * 根据主键更新实体
     *
     * @param examQuestionItem 实体
     * @return 更新条数
     */
    int update(ExamQuestionItemEntity examQuestionItem);

    /**
     * 根据主键删除
     *
     * @param id
     * @return 删除条数
     */
    int delete(Long id);

    /**
     * 根据主键批量删除
     *
     * @param ids
     * @return 删除条数
     */
    int deleteBatch(Long[] ids);

    /**
     * 通过题目删除选项
     * @param questionId
     * @return
     */
    int deleteByQid(Long questionId);
}
