package com.platform.controller.material;

import com.platform.entity.material.QrCodeDetailEntity;
import com.platform.service.material.QrCodeDetailService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 二维码详情表Controller
 *
 * @author admin
 *  
 * @date 2018-08-04 10:17:20
 */
@Controller
@RequestMapping("qrcodedetail")
public class QrCodeDetailController {
    @Autowired
    private QrCodeDetailService qrCodeDetailService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<QrCodeDetailEntity> qrCodeDetailList = qrCodeDetailService.queryQrCodeDetails(query);
        int total = qrCodeDetailService.queryQrCodeDetailsTotal(query);

        PageUtils pageUtil = new PageUtils(qrCodeDetailList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @ResponseBody
    public R info(@PathVariable("id") Integer id) {
        QrCodeDetailEntity qrCodeDetail = qrCodeDetailService.queryObject(id);

        return R.ok().put("qrCodeDetail", qrCodeDetail);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @ResponseBody
    public R save(@RequestBody QrCodeDetailEntity qrCodeDetail) {
        qrCodeDetailService.save(qrCodeDetail);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @ResponseBody
    public R update(@RequestBody QrCodeDetailEntity qrCodeDetail) {
        qrCodeDetailService.update(qrCodeDetail);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @ResponseBody
    public R delete(@RequestBody Integer[]ids) {
        qrCodeDetailService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<QrCodeDetailEntity> list = qrCodeDetailService.queryList(params);

        return R.ok().put("list", list);
    }
}
