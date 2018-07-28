package com.platform.service.notice.impl;

import com.platform.dao.notice.NoticeDao;
import com.platform.entity.notice.NoticeEntity;
import com.platform.service.notice.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 消息通知表Service实现类
 *
 * @author admin
 *  
 * @date 2018-07-28 10:46:23
 */
@Service("noticeService")
public class NoticeServiceImpl implements INoticeService {
    @Autowired
    private NoticeDao noticeDao;

    @Override
    public NoticeEntity queryObject(Long id) {
        return noticeDao.queryObject(id);
    }

    @Override
    public NoticeEntity queryObjectByCondition(NoticeEntity condition) {
        return noticeDao.queryObjectByCondition(condition);
    }

    @Override
    public List<NoticeEntity> queryList(Map<String, Object> map) {
        return noticeDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return noticeDao.queryTotal(map);
    }

    @Override
    public int save(NoticeEntity notice) {
        return noticeDao.save(notice);
    }

    @Override
    public int update(NoticeEntity notice) {
        return noticeDao.update(notice);
    }

    @Override
    public int delete(Long id) {
        return noticeDao.delete(id);
    }

    @Override
    public int deleteBatch(Long[]ids) {
        return noticeDao.deleteBatch(ids);
    }
}
