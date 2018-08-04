package com.platform.dao.material;

import com.platform.dao.BaseDao;
import com.platform.entity.material.QrCodeDetailEntity;

import java.util.List;
import java.util.Map;

/**
 * 二维码详情表Dao
 *
 * @author admin
 *  
 * @date 2018-08-04 10:17:20
 */
public interface QrCodeDetailDao extends BaseDao<QrCodeDetailEntity> {

    /**
     * 查找二维码详情
     * @param map
     * @return
     */
    List<QrCodeDetailEntity> selectQrCodeDetails(Map<String,Object> map);

    /**
     * 条数
     * @param map
     * @return
     */
    int selectQrCodeDetailsTotal(Map<String,Object> map);

}
