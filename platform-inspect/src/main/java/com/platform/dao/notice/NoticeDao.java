package com.platform.dao.notice;

import com.platform.dao.BaseDao;
import com.platform.entity.notice.NoticeEntity;
import org.springframework.stereotype.Repository;

/**
 * 消息通知表Dao
 * @author admin
 * @date 2018-07-28 10:46:23
 */

@Repository
public interface NoticeDao extends BaseDao<NoticeEntity> {

    NoticeEntity queryObjectByCondition(NoticeEntity queryObj);
}
