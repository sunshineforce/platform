package com.platform.service.material;


import com.platform.entity.dto.CustomerVo;
import com.platform.entity.material.MaterialEntity;
import com.platform.entity.material.MaterialVo;

import java.util.List;
import java.util.Map;

/**
 * 物品表Service接口
 *
 * @author admin
 *  
 * @date 2018-07-23 11:11:45
 */
public interface MaterialService {

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    MaterialEntity queryObject(Integer id);

    /**
     * 根据物品识别码查询实体
     * @param qrCode 物品识别码
     * @return 实体
     */
    MaterialEntity queryMaterialByQrCode(String qrCode);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<MaterialEntity> queryList(Map<String, Object> map);

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
     * @param material 实体
     * @return 保存条数
     */
    int save(MaterialEntity material);

    /**
     * 根据主键更新实体
     *
     * @param material 实体
     * @return 更新条数
     */
    int update(MaterialEntity material);

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

    //APP接口
    MaterialVo queryMaterialById(Integer id);

    /**
     * 批量绑定设备
     * @param customer
     * @return
     */
    int materialBindBatch(CustomerVo customer);
}
