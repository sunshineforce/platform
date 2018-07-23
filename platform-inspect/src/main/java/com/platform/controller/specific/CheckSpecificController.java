package com.platform.controller.specific;

import com.platform.entity.specific.CheckSpecificEntity;
import com.platform.service.specific.CheckSpecificService;
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
 * 检查规范表Controller
 *
 * @author admin
 *  
 * @date 2018-07-23 20:03:40
 */
@Controller
@RequestMapping("checkspecific")
public class CheckSpecificController {
    @Autowired
    private CheckSpecificService checkSpecificService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("checkspecific:list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<CheckSpecificEntity> checkSpecificList = checkSpecificService.queryList(query);
        int total = checkSpecificService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(checkSpecificList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("checkspecific:info")
    @ResponseBody
    public R info(@PathVariable("id") Integer id) {
        CheckSpecificEntity checkSpecific = checkSpecificService.queryObject(id);

        return R.ok().put("checkSpecific", checkSpecific);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("checkspecific:save")
    @ResponseBody
    public R save(@RequestBody CheckSpecificEntity checkSpecific) {
        checkSpecificService.save(checkSpecific);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("checkspecific:update")
    @ResponseBody
    public R update(@RequestBody CheckSpecificEntity checkSpecific) {
        checkSpecificService.update(checkSpecific);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("checkspecific:delete")
    @ResponseBody
    public R delete(@RequestBody Integer[]ids) {
        checkSpecificService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<CheckSpecificEntity> list = checkSpecificService.queryList(params);

        return R.ok().put("list", list);
    }
}
