package com.platform.service.telephone.impl;

import com.platform.dao.telephone.AddressBookDao;
import com.platform.entity.telephone.AddressBookEntity;
import com.platform.service.telephone.IAddressBookService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 部门通讯录Service实现类
 *
 * @author admin
 *  
 * @date 2018-07-31 22:38:00
 */
@Service("addressBookService")
public class AddressBookServiceImpl implements IAddressBookService {
    @Autowired
    private AddressBookDao addressBookDao;

    @Override
    public AddressBookEntity queryObject(Integer id) {
        return addressBookDao.queryObject(id);
    }

    @Override
    public List<AddressBookEntity> queryList(Map<String, Object> map) {
        return addressBookDao.queryList(map);
    }

    @Override
    public PageUtils search(Map<String, Object> map) {
        //查询列表数据
        Query query = new Query(map);
        List<AddressBookEntity> resultList = addressBookDao.queryList(map);
        int total = addressBookDao.queryTotal(query);
        PageUtils pageUtil = new PageUtils(resultList, total, query.getLimit(), query.getPage());
        return pageUtil;
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return addressBookDao.queryTotal(map);
    }

    @Override
    public int save(AddressBookEntity addressBook) {
        return addressBookDao.save(addressBook);
    }

    @Override
    public int update(AddressBookEntity addressBook) {
        return addressBookDao.update(addressBook);
    }

    @Override
    public int delete(Integer id) {
        return addressBookDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[]ids) {
        return addressBookDao.deleteBatch(ids);
    }
}
