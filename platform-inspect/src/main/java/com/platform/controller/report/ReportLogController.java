package com.platform.controller.report;

import com.platform.entity.report.ReportLogEntity;
import com.platform.service.report.ReportLogService;
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
 * 上报记录表Controller
 *
 * @author admin
 *  
 * @date 2018-07-24 10:14:47
 */
@Controller
@RequestMapping("reportlog")
public class ReportLogController {
    @Autowired
    private ReportLogService reportLogService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("reportlog:list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<ReportLogEntity> reportLogList = reportLogService.queryList(query);
        int total = reportLogService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(reportLogList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("reportlog:info")
    @ResponseBody
    public R info(@PathVariable("id") Long id) {
        ReportLogEntity reportLog = reportLogService.queryObject(id);

        return R.ok().put("reportLog", reportLog);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("reportlog:save")
    @ResponseBody
    public R save(@RequestBody ReportLogEntity reportLog) {
        reportLogService.save(reportLog);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("reportlog:update")
    @ResponseBody
    public R update(@RequestBody ReportLogEntity reportLog) {
        reportLogService.update(reportLog);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("reportlog:delete")
    @ResponseBody
    public R delete(@RequestBody Long[]ids) {
        reportLogService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<ReportLogEntity> list = reportLogService.queryList(params);

        return R.ok().put("list", list);
    }
}
