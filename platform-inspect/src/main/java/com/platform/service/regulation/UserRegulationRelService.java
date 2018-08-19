package com.platform.service.regulation;

import com.platform.entity.regulation.UserRegulationRelEntity;
import com.platform.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * Service接口
 *
 * @author admin
 *  
 * @date 2018-08-19 17:34:20
 */
public interface UserRegulationRelService {

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    UserRegulationRelEntity queryObject(Integer id);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<UserRegulationRelEntity> queryList(Map<String, Object> map);

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
     * @param userRegulationRel 实体
     * @return 保存条数
     */
    int save(UserRegulationRelEntity userRegulationRel);

    /**
     * 根据主键更新实体
     *
     * @param userRegulationRel 实体
     * @return 更新条数
     */
    int update(UserRegulationRelEntity userRegulationRel);

    /**
     * 根据主键删除
     *
     * @param id
     * @return 删除条数
     */
    int delete(Integer id);

    /**
     * 根据主键批量删除
     *
     * @param ids
     * @return 删除条数
     */
    int deleteBatch(Integer[] ids);

    /**
     * 查询用户知识收藏夹
     * @param params
     * @return
     */
    PageUtils queryKnowledgeList(Map<String,Object> params);
}
