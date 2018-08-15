package com.platform.service.inspect;


import com.platform.entity.inspect.InspectOrderEntity;
import com.platform.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * 安全巡检异常工单表Service接口
 *
 * @author admin
 *  
 * @date 2018-07-22 21:48:11
 */
public interface IInspectOrderService {

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    InspectOrderEntity queryObject(Integer id);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<InspectOrderEntity> queryList(Map<String, Object> map);

    /**
     * 分页查询封装
     * @param map
     * @return
     */
    PageUtils search(Map<String, Object> map);

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
     * @param inspectOrder 实体
     * @return 保存条数
     */
    int save(InspectOrderEntity inspectOrder);

    /**
     * 根据主键更新实体
     *
     * @param inspectOrder 实体
     * @return 更新条数
     */
    int update(InspectOrderEntity inspectOrder);

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
     * 异常处理
     * @param map
     * @return
     */
    int processAnomaly(Map<String, Object> map);

    /**
     * 上报上级
     * @param map
     * @return
     */
    int report(Map<String, Object> map);
}
