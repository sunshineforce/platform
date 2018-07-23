package com.platform.controller.specific;

import com.platform.entity.specific.CheckSpecificItemEntity;
import com.platform.service.specific.CheckSpecificItemService;
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
 * 规范项条目表Controller
 *
 * @author admin
 *  
 * @date 2018-07-23 20:03:40
 */
@Controller
@RequestMapping("checkspecificitem")
public class CheckSpecificItemController {
    @Autowired
    private CheckSpecificItemService checkSpecificItemService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("checkspecificitem:list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<CheckSpecificItemEntity> checkSpecificItemList = checkSpecificItemService.queryList(query);
        int total = checkSpecificItemService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(checkSpecificItemList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("checkspecificitem:info")
    @ResponseBody
    public R info(@PathVariable("id") Integer id) {
        CheckSpecificItemEntity checkSpecificItem = checkSpecificItemService.queryObject(id);

        return R.ok().put("checkSpecificItem", checkSpecificItem);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("checkspecificitem:save")
    @ResponseBody
    public R save(@RequestBody CheckSpecificItemEntity checkSpecificItem) {
        checkSpecificItemService.save(checkSpecificItem);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("checkspecificitem:update")
    @ResponseBody
    public R update(@RequestBody CheckSpecificItemEntity checkSpecificItem) {
        checkSpecificItemService.update(checkSpecificItem);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("checkspecificitem:delete")
    @ResponseBody
    public R delete(@RequestBody Integer[]ids) {
        checkSpecificItemService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<CheckSpecificItemEntity> list = checkSpecificItemService.queryList(params);

        return R.ok().put("list", list);
    }
}
