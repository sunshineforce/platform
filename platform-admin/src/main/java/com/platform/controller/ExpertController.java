package com.platform.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.platform.entity.ExpertEntity;
import com.platform.service.IExpertService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * 专家库Controller *
 * @author admin
 * @date 2018-07-20 18:08:44
 */
@Controller
@RequestMapping("texpert")
public class ExpertController {
    @Autowired
    private IExpertService expertService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("texpert:list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<ExpertEntity> tExpertList = expertService.queryList(query);
        int total = expertService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(tExpertList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("texpert:info")
    @ResponseBody
    public R info(@PathVariable("id") Integer id) {
        ExpertEntity tExpert = expertService.queryObject(id);

        return R.ok().put("tExpert", tExpert);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("texpert:save")
    @ResponseBody
    public R save(@RequestBody ExpertEntity tExpert) {
        expertService.save(tExpert);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("texpert:update")
    @ResponseBody
    public R update(@RequestBody ExpertEntity tExpert) {
        expertService.update(tExpert);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("texpert:delete")
    @ResponseBody
    public R delete(@RequestBody Integer[]ids) {
        expertService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<ExpertEntity> list = expertService.queryList(params);

        return R.ok().put("list", list);
    }
}
