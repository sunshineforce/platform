package com.platform.service.material.impl;

import com.platform.dao.material.QrCodeDetailDao;
import com.platform.entity.material.QrCodeDetailEntity;
import com.platform.service.material.QrCodeDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * 二维码详情表Service实现类
 *
 * @author admin
 *  
 * @date 2018-08-04 10:17:20
 */
@Service("qrCodeDetailService")
public class QrCodeDetailServiceImpl implements QrCodeDetailService {
    @Autowired
    private QrCodeDetailDao qrCodeDetailDao;

    @Override
    public QrCodeDetailEntity queryObject(Integer id) {
        return qrCodeDetailDao.queryObject(id);
    }

    @Override
    public List<QrCodeDetailEntity> queryList(Map<String, Object> map) {
        return qrCodeDetailDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return qrCodeDetailDao.queryTotal(map);
    }

    @Override
    public int save(QrCodeDetailEntity qrCodeDetail) {
        return qrCodeDetailDao.save(qrCodeDetail);
    }

    @Override
    public int update(QrCodeDetailEntity qrCodeDetail) {
        return qrCodeDetailDao.update(qrCodeDetail);
    }

    @Override
    public int delete(Integer id) {
        return qrCodeDetailDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[]ids) {
        return qrCodeDetailDao.deleteBatch(ids);
    }

    @Override
    public List<QrCodeDetailEntity> queryQrCodeDetails(Map<String, Object> map) {
        return qrCodeDetailDao.selectQrCodeDetails(map);
    }

    @Override
    public int queryQrCodeDetailsTotal(Map<String, Object> map) {
        return qrCodeDetailDao.selectQrCodeDetailsTotal(map);
    }
}
