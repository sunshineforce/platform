package com.platform.service.specific.impl;

import com.platform.constants.CommonConstant;
import com.platform.dao.specific.CheckSpecificDao;
import com.platform.dao.specific.CheckSpecificItemDao;
import com.platform.entity.AppUserEntity;
import com.platform.entity.specific.CheckSpecificEntity;
import com.platform.entity.specific.vo.CheckSpecificVo;
import com.platform.service.specific.CheckSpecificService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.ShiroUtils;
import com.platform.vo.SelectVo;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 检查规范表Service实现类
 * @author admin
 * @date 2018-07-23 20:03:40
 */
@Service("checkSpecificService")
public class CheckSpecificServiceImpl implements CheckSpecificService {

    @Autowired
    private CheckSpecificDao checkSpecificDao;

    @Autowired
    private CheckSpecificItemDao checkSpecificItemDao;

    @Override
    public CheckSpecificEntity queryObject(Integer id) {
        return checkSpecificDao.queryObject(id);
    }

    @Override
    public List<CheckSpecificEntity> queryList(Map<String, Object> map) {
        return checkSpecificDao.queryList(map);
    }

    @Override
    public PageUtils search(Map<String, Object> map) {
        Query query = new Query(map);
        List<CheckSpecificVo> resultList = queryListSimple(map);
        List<SelectVo> checkItems;
        Long specificId;
        for (CheckSpecificVo checkSpecific : resultList) {
            specificId = checkSpecific.getId();
            checkItems = checkSpecificItemDao.queryListSimple(specificId);
            checkSpecific.setItemList(checkItems);
        }
        Integer total = checkSpecificDao.queryTotalSimple(map);
        if (total == null) {
            total = 0;
        }

        return new PageUtils(resultList, total, query.getLimit(), query.getPage());
    }

    @Override
    public List<CheckSpecificVo> queryListSimple(Map<String, Object> map) {
        return checkSpecificDao.queryListSimple(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return checkSpecificDao.queryTotal(map);
    }

    @Override
    public int save(CheckSpecificEntity checkSpecific) {
        checkSpecific.setCreateTime(new Date());
//        Subject subject = ShiroUtils.getSubject();
//        AppUserEntity appUser = (AppUserEntity) subject.getSession().getAttribute(CommonConstant.APP_LOGIN_USER);
//        checkSpecific.setCreator(appUser.getRealname());
        return checkSpecificDao.save(checkSpecific);
    }

    @Override
    public int update(CheckSpecificEntity checkSpecific) {
        return checkSpecificDao.update(checkSpecific);
    }

    @Override
    public int delete(Integer id) {
        return checkSpecificDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[]ids) {
        return checkSpecificDao.deleteBatch(ids);
    }

    @Override
    public CheckSpecificVo queryCheckSpecificAndItem(Integer id) {
        CheckSpecificEntity checkSpecific = checkSpecificDao.queryObject(id);
        List<SelectVo> items = checkSpecificItemDao.queryListSimple(Long.valueOf(id));
        CheckSpecificVo result = new CheckSpecificVo();
        result.setId(Long.valueOf(checkSpecific.getId()));
        result.setName(checkSpecific.getSpecificName());
        result.setItemList(items);
        return result;
    }
}
