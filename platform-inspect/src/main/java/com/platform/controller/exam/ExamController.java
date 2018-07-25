package com.platform.controller.exam;

import com.platform.entity.exam.ExamEntity;
import com.platform.service.exam.ExamService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 试题管理表Controller
 *
 * @author admin
 *  
 * @date 2018-07-24 09:45:45
 */
@Controller
@RequestMapping("exam")
public class ExamController {
    @Autowired
    private ExamService examService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("exam:list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<ExamEntity> examList = examService.queryList(query);
        int total = examService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(examList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("exam:info")
    @ResponseBody
    public R info(@PathVariable("id") Long id) {
        ExamEntity exam = examService.queryObject(id);

        return R.ok().put("exam", exam);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("exam:save")
    @ResponseBody
    public R save(@RequestBody ExamEntity exam) {
        Date time = new Date();
        exam.setCreateTime(time);
        exam.setUpdateTime(time);
        examService.save(exam);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("exam:update")
    @ResponseBody
    public R update(@RequestBody ExamEntity exam) {
        Date time = new Date();
        exam.setUpdateTime(time);
        examService.update(exam);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("exam:delete")
    @ResponseBody
    public R delete(@RequestBody Long[]ids) {
        examService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<ExamEntity> list = examService.queryList(params);

        return R.ok().put("list", list);
    }
}
