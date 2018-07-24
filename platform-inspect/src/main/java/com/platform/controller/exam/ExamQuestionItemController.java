package com.platform.controller.exam;

import com.platform.entity.exam.ExamQuestionItemEntity;
import com.platform.service.exam.ExamQuestionItemService;
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
 * 试题选项表Controller
 *
 * @author admin
 *  
 * @date 2018-07-24 09:45:45
 */
@Controller
@RequestMapping("examquestionitem")
public class ExamQuestionItemController {
    @Autowired
    private ExamQuestionItemService examQuestionItemService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("examquestionitem:list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<ExamQuestionItemEntity> examQuestionItemList = examQuestionItemService.queryList(query);
        int total = examQuestionItemService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(examQuestionItemList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("examquestionitem:info")
    @ResponseBody
    public R info(@PathVariable("id") Long id) {
        ExamQuestionItemEntity examQuestionItem = examQuestionItemService.queryObject(id);

        return R.ok().put("examQuestionItem", examQuestionItem);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("examquestionitem:save")
    @ResponseBody
    public R save(@RequestBody ExamQuestionItemEntity examQuestionItem) {
        examQuestionItemService.save(examQuestionItem);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("examquestionitem:update")
    @ResponseBody
    public R update(@RequestBody ExamQuestionItemEntity examQuestionItem) {
        examQuestionItemService.update(examQuestionItem);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("examquestionitem:delete")
    @ResponseBody
    public R delete(@RequestBody Long[]ids) {
        examQuestionItemService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<ExamQuestionItemEntity> list = examQuestionItemService.queryList(params);

        return R.ok().put("list", list);
    }
}
