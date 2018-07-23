package com.platform.controller.user;

import java.util.List;
import java.util.Map;

import com.platform.entity.user.AppUserEntity;
import com.platform.service.user.IAppUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * APP用户表Controller
 *
 * @author admin
 *  
 * @date 2018-07-23 19:34:00
 */
@Controller
@RequestMapping("appUser")
public class AppUserController {
    @Autowired
    private IAppUserService appUserService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("appUser:list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<AppUserEntity> appUserList = appUserService.queryList(query);
        int total = appUserService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(appUserList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("appUser:info")
    @ResponseBody
    public R info(@PathVariable("id") Integer id) {
        AppUserEntity appUser = appUserService.queryObject(id);

        return R.ok().put("appUser", appUser);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("appUser:save")
    @ResponseBody
    public R save(@RequestBody AppUserEntity appUser) {
        appUserService.save(appUser);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("appUser:update")
    @ResponseBody
    public R update(@RequestBody AppUserEntity appUser) {
        appUserService.update(appUser);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("appUser:delete")
    @ResponseBody
    public R delete(@RequestBody Integer[]ids) {
        appUserService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<AppUserEntity> list = appUserService.queryList(params);

        return R.ok().put("list", list);
    }
}
