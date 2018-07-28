package com.platform.service.notice;

import com.platform.entity.notice.NoticeEntity;

import java.util.List;
import java.util.Map;

/**
 * 消息通知表Service接口
 *
 * @author admin
 *  
 * @date 2018-07-28 10:46:23
 */
public interface INoticeService {

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    NoticeEntity queryObject(Long id);

    /**
     * 根据条件查询实体
     * @param condition
     * @return
     */
    NoticeEntity queryObjectByCondition(NoticeEntity condition);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<NoticeEntity> queryList(Map<String, Object> map);

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
     * @param notice 实体
     * @return 保存条数
     */
    int save(NoticeEntity notice);

    /**
     * 根据主键更新实体
     *
     * @param notice 实体
     * @return 更新条数
     */
    int update(NoticeEntity notice);

    /**
     * 根据主键删除
     *
     * @param id
     * @return 删除条数
     */
    int delete(Long id);

    /**
     * 根据主键批量删除
     *
     * @param ids
     * @return 删除条数
     */
    int deleteBatch(Long[] ids);
}
