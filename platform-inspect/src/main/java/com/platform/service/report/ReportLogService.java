package com.platform.service.report;


import com.platform.entity.report.ReportLogEntity;

import java.util.List;
import java.util.Map;

/**
 * 上报记录表Service接口
 *
 * @author admin
 *  
 * @date 2018-07-24 10:14:47
 */
public interface ReportLogService {

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    ReportLogEntity queryObject(Long id);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<ReportLogEntity> queryList(Map<String, Object> map);

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
     * @param reportLog 实体
     * @return 保存条数
     */
    int save(ReportLogEntity reportLog);

    /**
     * 根据主键更新实体
     *
     * @param reportLog 实体
     * @return 更新条数
     */
    int update(ReportLogEntity reportLog);

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
