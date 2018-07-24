package com.platform.controller.material;

import com.platform.entity.material.QrCodeApplyEntity;
import com.platform.service.material.QrCodeApplyService;
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
 * 二维码申请表Controller
 *
 * @author admin
 *  
 * @date 2018-07-24 10:41:09
 */
@Controller
@RequestMapping("qrcodeapply")
public class QrCodeApplyController {
    @Autowired
    private QrCodeApplyService qrCodeApplyService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("qrcodeapply:list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<QrCodeApplyEntity> qrCodeApplyList = qrCodeApplyService.queryList(query);
        int total = qrCodeApplyService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(qrCodeApplyList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("qrcodeapply:info")
    @ResponseBody
    public R info(@PathVariable("id") Integer id) {
        QrCodeApplyEntity qrCodeApply = qrCodeApplyService.queryObject(id);

        return R.ok().put("qrCodeApply", qrCodeApply);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("qrcodeapply:save")
    @ResponseBody
    public R save(@RequestBody QrCodeApplyEntity qrCodeApply) {
        qrCodeApplyService.save(qrCodeApply);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("qrcodeapply:update")
    @ResponseBody
    public R update(@RequestBody QrCodeApplyEntity qrCodeApply) {
        qrCodeApplyService.update(qrCodeApply);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("qrcodeapply:delete")
    @ResponseBody
    public R delete(@RequestBody Integer[]ids) {
        qrCodeApplyService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<QrCodeApplyEntity> list = qrCodeApplyService.queryList(params);

        return R.ok().put("list", list);
    }
}
