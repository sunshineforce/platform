package com.platform.controller.enterprise;

import java.util.List;
import java.util.Map;

import com.platform.entity.enterprise.EnterpriseEntity;
import com.platform.service.enterprise.IEnterpriseService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * 企业信息表Controller
 *
 * @author admin
 *  
 * @date 2018-07-21 14:32:11
 */
@Controller
@RequestMapping("enterprise")
public class EnterpriseController {
    
    @Autowired
    private IEnterpriseService enterpriseService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("enterprise:list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<EnterpriseEntity> enterpriseList = enterpriseService.queryList(query);
        int total = enterpriseService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(enterpriseList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("enterprise:info")
    @ResponseBody
    public R info(@PathVariable("id") Long id) {
        EnterpriseEntity enterprise = enterpriseService.queryObject(id);

        return R.ok().put("enterprise", enterprise);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("enterprise:save")
    @ResponseBody
    public R save(@RequestBody EnterpriseEntity enterprise) {
        enterpriseService.save(enterprise);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("enterprise:update")
    @ResponseBody
    public R update(@RequestBody EnterpriseEntity enterprise) {
        enterpriseService.update(enterprise);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("enterprise:delete")
    @ResponseBody
    public R delete(@RequestBody Long[]ids) {
        enterpriseService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<EnterpriseEntity> list = enterpriseService.queryList(params);

        return R.ok().put("list", list);
    }
}
