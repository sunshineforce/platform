package com.platform.controller.telephone;

import java.util.List;
import java.util.Map;

import com.platform.entity.telephone.AddressBookEntity;
import com.platform.service.telephone.IAddressBookService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * 部门通讯录Controller
 *
 * @author admin
 *  
 * @date 2018-07-31 22:38:00
 */
@Controller
@RequestMapping("addressbook")
public class AddressBookController {
    @Autowired
    private IAddressBookService addressBookService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("addressbook:list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<AddressBookEntity> addressBookList = addressBookService.queryList(query);
        int total = addressBookService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(addressBookList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("addressbook:info")
    @ResponseBody
    public R info(@PathVariable("id") Integer id) {
        AddressBookEntity addressBook = addressBookService.queryObject(id);

        return R.ok().put("addressBook", addressBook);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("addressbook:save")
    @ResponseBody
    public R save(@RequestBody AddressBookEntity addressBook) {
        addressBookService.save(addressBook);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("addressbook:update")
    @ResponseBody
    public R update(@RequestBody AddressBookEntity addressBook) {
        addressBookService.update(addressBook);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("addressbook:delete")
    @ResponseBody
    public R delete(@RequestBody Integer[]ids) {
        addressBookService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<AddressBookEntity> list = addressBookService.queryList(params);

        return R.ok().put("list", list);
    }
}
