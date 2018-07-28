package com.platform.dao.notice;

import com.platform.dao.BaseDao;
import com.platform.entity.notice.NoticeEntity;

/**
 * 消息通知表Dao
 * @author admin
 * @date 2018-07-28 10:46:23
 */
public interface NoticeDao extends BaseDao<NoticeEntity> {

    NoticeEntity queryObjectByCondition(NoticeEntity queryObj);
}
