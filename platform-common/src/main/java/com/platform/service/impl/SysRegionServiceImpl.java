package com.platform.service.impl;

import com.alibaba.fastjson.JSON;
import com.platform.dao.SysRegionDao;
import com.platform.entity.SysRegionEntity;
import com.platform.entity.Tree;
import com.platform.service.SysRegionService;
import com.platform.utils.TreeBuilder;
import com.platform.utils.TreeUtils;
import com.platform.vo.TreeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Service实现类
 *
 * @author admin
 *
 * @date 2017-11-04 11:19:31
 */
@Service("regionService")
public class SysRegionServiceImpl implements SysRegionService {
    @Autowired
    private SysRegionDao sysRegionDao;

    @Override
    public SysRegionEntity queryObject(Integer id) {
        return sysRegionDao.queryObject(id);
    }

    @Override
    public List<SysRegionEntity> queryList(Map<String, Object> map) {
        return sysRegionDao.queryList(map);
    }

    @Override
    public List<TreeVo> queryAllRegion() {
        return sysRegionDao.queryAllRegion();
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return sysRegionDao.queryTotal(map);
    }

    @Override
    public int save(SysRegionEntity region) {
        Integer parentId = region.getParentId()==null?0:region.getParentId();
        region.setParentId(parentId);
        return sysRegionDao.save(region);
    }

    @Override
    public int update(SysRegionEntity region) {
        return sysRegionDao.update(region);
    }

    @Override
    public int delete(Integer id) {
        return sysRegionDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return sysRegionDao.deleteBatch(ids);
    }

    @Override
    public List<TreeVo> buildRegionTree() {
        List<TreeVo> rootList = sysRegionDao.queryRegionSimple();
        TreeBuilder treeBuilder = new TreeBuilder(rootList);
        List<TreeVo> list = treeBuilder.buildTree();
        return list;
    }
}
