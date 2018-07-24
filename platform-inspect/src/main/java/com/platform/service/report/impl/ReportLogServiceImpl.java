package com.platform.service.report.impl;

import com.platform.dao.report.ReportLogDao;
import com.platform.entity.report.ReportLogEntity;
import com.platform.service.report.ReportLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * 上报记录表Service实现类
 *
 * @author admin
 *  
 * @date 2018-07-24 10:14:47
 */
@Service("reportLogService")
public class ReportLogServiceImpl implements ReportLogService {
    @Autowired
    private ReportLogDao reportLogDao;

    @Override
    public ReportLogEntity queryObject(Long id) {
        return reportLogDao.queryObject(id);
    }

    @Override
    public List<ReportLogEntity> queryList(Map<String, Object> map) {
        return reportLogDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return reportLogDao.queryTotal(map);
    }

    @Override
    public int save(ReportLogEntity reportLog) {
        return reportLogDao.save(reportLog);
    }

    @Override
    public int update(ReportLogEntity reportLog) {
        return reportLogDao.update(reportLog);
    }

    @Override
    public int delete(Long id) {
        return reportLogDao.delete(id);
    }

    @Override
    public int deleteBatch(Long[]ids) {
        return reportLogDao.deleteBatch(ids);
    }
}
