package com.platform.service.regulation;


import com.platform.entity.regulation.RegulationEntity;

import java.util.List;
import java.util.Map;

/**
 * 法规管理表Service接口
 *
 * @author admin
 *  
 * @date 2018-07-24 10:28:40
 */
public interface RegulationService {

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    RegulationEntity queryObject(Long id);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<RegulationEntity> queryList(Map<String, Object> map);

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
     * @param regulation 实体
     * @return 保存条数
     */
    int save(RegulationEntity regulation);

    /**
     * 根据主键更新实体
     *
     * @param regulation 实体
     * @return 更新条数
     */
    int update(RegulationEntity regulation);

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
}
