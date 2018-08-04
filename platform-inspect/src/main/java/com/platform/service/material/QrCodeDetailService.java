package com.platform.service.material;


import com.platform.entity.material.QrCodeDetailEntity;

import java.util.List;
import java.util.Map;

/**
 * 二维码详情表Service接口
 *
 * @author admin
 *  
 * @date 2018-08-04 10:17:20
 */
public interface QrCodeDetailService {

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    QrCodeDetailEntity queryObject(Integer id);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<QrCodeDetailEntity> queryList(Map<String, Object> map);

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
     * @param qrCodeDetail 实体
     * @return 保存条数
     */
    int save(QrCodeDetailEntity qrCodeDetail);

    /**
     * 根据主键更新实体
     *
     * @param qrCodeDetail 实体
     * @return 更新条数
     */
    int update(QrCodeDetailEntity qrCodeDetail);

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
     * 查找二维码详情
     * @param map
     * @return
     */
    List<QrCodeDetailEntity> queryQrCodeDetails(Map<String,Object> map);

    /**
     * 条数
     * @param map
     * @return
     */
    int queryQrCodeDetailsTotal(Map<String,Object> map);
}
