package com.platform.service.material;


import com.platform.entity.material.QrCodeApplyEntity;

import java.util.List;
import java.util.Map;

/**
 * 二维码申请表Service接口
 *
 * @author admin
 *  
 * @date 2018-07-24 10:41:09
 */
public interface QrCodeApplyService {

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    QrCodeApplyEntity queryObject(Integer id);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<QrCodeApplyEntity> queryList(Map<String, Object> map);

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
     * @param qrCodeApply 实体
     * @return 保存条数
     */
    int save(QrCodeApplyEntity qrCodeApply);

    /**
     * 根据主键更新实体
     *
     * @param qrCodeApply 实体
     * @return 更新条数
     */
    int update(QrCodeApplyEntity qrCodeApply);

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
}
