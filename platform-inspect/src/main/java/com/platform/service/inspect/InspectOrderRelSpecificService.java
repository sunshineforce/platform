package com.platform.service.inspect;


import com.platform.entity.inspect.InspectOrderRelSpecificEntity;

import java.util.List;
import java.util.Map;

/**
 * 工单管理检查项表Service接口
 *
 * @author admin
 *  
 * @date 2018-07-22 21:48:11
 */
public interface InspectOrderRelSpecificService {

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    InspectOrderRelSpecificEntity queryObject(Integer id);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<InspectOrderRelSpecificEntity> queryList(Map<String, Object> map);

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
     * @param inspectOrderRelSpecific 实体
     * @return 保存条数
     */
    int save(InspectOrderRelSpecificEntity inspectOrderRelSpecific);

    /**
     * 根据主键更新实体
     *
     * @param inspectOrderRelSpecific 实体
     * @return 更新条数
     */
    int update(InspectOrderRelSpecificEntity inspectOrderRelSpecific);

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
     * 查询工单检查项
     * @param orderId
     * @return
     */
    List<InspectOrderRelSpecificEntity> queryOrderSpecific(Integer orderId);
}
