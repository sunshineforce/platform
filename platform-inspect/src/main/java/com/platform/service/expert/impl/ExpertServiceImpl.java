package com.platform.service.expert.impl;

import com.platform.constants.CommonConstant;
import com.platform.dao.expert.ExpertDao;
import com.platform.entity.SysUserEntity;
import com.platform.entity.expert.ExpertEntity;
import com.platform.service.expert.IExpertService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 专家库表Service实现类
 *
 * @author admin
 *  
 * @date 2018-07-20 18:08:44
 */
@Service("expertService")
public class ExpertServiceImpl implements IExpertService {
    @Autowired
    private ExpertDao expertDao;

    @Override
    public ExpertEntity queryObject(Integer id) {
        return expertDao.queryObject(id);
    }

    @Override
    public List<ExpertEntity> queryList(Map<String, Object> map) {
        return expertDao.queryList(map);
    }

    @Override
    public PageUtils search(Map<String, Object> map) {
        //查询列表数据
        Query query = new Query(map);

        List<ExpertEntity> expertList = expertDao.queryList(query);
        int total = expertDao.queryTotal(query);

        PageUtils pageUtil = new PageUtils(expertList, total, query.getLimit(), query.getPage());
        return pageUtil;
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return expertDao.queryTotal(map);
    }

    @Override
    public int save(ExpertEntity expert) {
        setDefaultValue(expert);
        return expertDao.save(expert);
    }

    @Override
    public int update(ExpertEntity expert) {
        setDefaultValue(expert);
        return expertDao.update(expert);
    }

    @Override
    public int delete(Integer id) {
        return expertDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[]ids) {
        return expertDao.deleteBatch(ids);
    }

    private void setDefaultValue(ExpertEntity entity){
        Date currDate = new Date();
        SysUserEntity sessionUser = (SysUserEntity) SecurityUtils.getSubject().getSession().getAttribute(CommonConstant.LOGIN_USER);
        if (entity.getId() == null) {
            entity.setCreateTime(currDate);
            entity.setCreator(sessionUser.getUsername());
        }
        entity.setUpdateTime(currDate);
        entity.setUpdator(sessionUser.getUsername());
    }
}
