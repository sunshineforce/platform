package com.platform.controller.expert;

import com.platform.entity.expert.ExpertEntity;
import com.platform.service.expert.IExpertService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 专家库Controller *
 * @author admin
 * @date 2018-07-20 18:08:44
 */
@Controller
@RequestMapping("expert")
public class ExpertController {
    @Autowired
    private IExpertService expertService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("expert:list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<ExpertEntity> expertList = expertService.queryList(query);
        int total = expertService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(expertList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("expert:info")
    @ResponseBody
    public R info(@PathVariable("id") Integer id) {
        ExpertEntity expert = expertService.queryObject(id);

        return R.ok().put("expert", expert);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("expert:save")
    @ResponseBody
    public R save(@RequestBody ExpertEntity expert) {
        expertService.save(expert);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("expert:update")
    @ResponseBody
    public R update(@RequestBody ExpertEntity expert) {
        expertService.update(expert);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("expert:delete")
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
