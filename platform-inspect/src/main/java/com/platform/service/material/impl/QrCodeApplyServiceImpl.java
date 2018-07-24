package com.platform.service.material.impl;

import com.platform.entity.material.QrCodeApplyEntity;
import com.platform.dao.material.QrCodeApplyDao;
import com.platform.service.material.QrCodeApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



/**
 * 二维码申请表Service实现类
 *
 * @author admin
 *  
 * @date 2018-07-24 10:41:09
 */
@Service("qrCodeApplyService")
public class QrCodeApplyServiceImpl implements QrCodeApplyService {
    @Autowired
    private QrCodeApplyDao qrCodeApplyDao;

    @Override
    public QrCodeApplyEntity queryObject(Integer id) {
        return qrCodeApplyDao.queryObject(id);
    }

    @Override
    public List<QrCodeApplyEntity> queryList(Map<String, Object> map) {
        return qrCodeApplyDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return qrCodeApplyDao.queryTotal(map);
    }

    @Override
    public int save(QrCodeApplyEntity qrCodeApply) {
        return qrCodeApplyDao.save(qrCodeApply);
    }

    @Override
    public int update(QrCodeApplyEntity qrCodeApply) {
        return qrCodeApplyDao.update(qrCodeApply);
    }

    @Override
    public int delete(Integer id) {
        return qrCodeApplyDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[]ids) {
        return qrCodeApplyDao.deleteBatch(ids);
    }
}
