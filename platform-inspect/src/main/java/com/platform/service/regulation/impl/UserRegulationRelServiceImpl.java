package com.platform.service.regulation.impl;

import com.platform.dao.regulation.UserRegulationRelDao;
import com.platform.entity.regulation.UserRegulationRelEntity;
import com.platform.entity.regulation.vo.KnowledgeCollectVo;
import com.platform.service.regulation.UserRegulationRelService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Service实现类
 *
 * @author admin
 *  
 * @date 2018-08-19 17:34:20
 */
@Service("userRegulationRelService")
public class UserRegulationRelServiceImpl implements UserRegulationRelService {
    @Autowired
    private UserRegulationRelDao userRegulationRelDao;

    @Override
    public UserRegulationRelEntity queryObject(Integer id) {
        return userRegulationRelDao.queryObject(id);
    }

    @Override
    public List<UserRegulationRelEntity> queryList(Map<String, Object> map) {
        return userRegulationRelDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return userRegulationRelDao.queryTotal(map);
    }

    @Override
    public int save(UserRegulationRelEntity userRegulationRel) {
        return userRegulationRelDao.save(userRegulationRel);
    }

    @Override
    public int update(UserRegulationRelEntity userRegulationRel) {
        return userRegulationRelDao.update(userRegulationRel);
    }

    @Override
    public int delete(Integer id) {
        return userRegulationRelDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[]ids) {
        return userRegulationRelDao.deleteBatch(ids);
    }

    @Override
    public PageUtils queryKnowledgeList(Map<String,Object> params) {
       Query query = new Query(params);
       List<KnowledgeCollectVo> list = userRegulationRelDao.selectKnowledgeCollectList(params);
       int total = userRegulationRelDao.selectKnowledgeCollectTotal(params);
       return new PageUtils(list,total,query.getLimit(), query.getPage());
    }
}
