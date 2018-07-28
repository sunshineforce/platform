package com.platform.service.specific;

import com.platform.entity.specific.CheckSpecificEntity;
import com.platform.vo.SelectVo;

import java.util.List;
import java.util.Map;

/**
 * 检查规范表Service接口
 * @author admin
 * @date 2018-07-23 20:03:40
 */
public interface CheckSpecificService {

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    CheckSpecificEntity queryObject(Integer id);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<CheckSpecificEntity> queryList(Map<String, Object> map);

    /**
     * 加载所有的检查规范
     * @return
     */
    List<SelectVo> loadAllCheckSpecific();

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
     * @param checkSpecific 实体
     * @return 保存条数
     */
    int save(CheckSpecificEntity checkSpecific);

    /**
     * 根据主键更新实体
     *
     * @param checkSpecific 实体
     * @return 更新条数
     */
    int update(CheckSpecificEntity checkSpecific);

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
