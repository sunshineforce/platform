package com.platform.service.enterprise;

import com.platform.entity.enterprise.EnterpriseEntity;
import com.platform.vo.SelectVo;

import java.util.List;
import java.util.Map;

/**
 * 企业信息表Service接口
 *
 * @author admin
 *  
 * @date 2018-07-21 14:32:11
 */
public interface IEnterpriseService {

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    EnterpriseEntity queryObject(Integer id);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<EnterpriseEntity> queryList(Map<String, Object> map);

    /**
     * 根据区域Id加载所有企业
     * @param regionId
     * @return
     */
    List<SelectVo> loadEnterpriseByRegionId(Integer regionId);

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
     * @param enterprice 实体
     * @return 保存条数
     */
    int save(EnterpriseEntity enterprice);

    /**
     * 根据主键更新实体
     *
     * @param enterprice 实体
     * @return 更新条数
     */
    int update(EnterpriseEntity enterprice);

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
    int deleteBatch(Long[]ids);
}
