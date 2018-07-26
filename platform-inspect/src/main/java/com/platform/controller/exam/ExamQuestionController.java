package com.platform.controller.exam;

import com.platform.entity.exam.ExamQuestionEntity;
import com.platform.service.exam.ExamQuestionService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 试题题目表Controller
 *
 * @author admin
 *  
 * @date 2018-07-24 09:45:45
 */
@Controller
@RequestMapping("examquestion")
public class ExamQuestionController {
    @Autowired
    private ExamQuestionService examQuestionService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<ExamQuestionEntity> examQuestionList = examQuestionService.queryList(query);
        int total = examQuestionService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(examQuestionList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @ResponseBody
    public R info(@PathVariable("id") Long id) {
        ExamQuestionEntity examQuestion = examQuestionService.queryObject(id);

        return R.ok().put("examQuestion", examQuestion);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @ResponseBody
    public R save(@RequestBody ExamQuestionEntity examQuestion) {
        examQuestionService.save(examQuestion);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @ResponseBody
    public R update(@RequestBody ExamQuestionEntity examQuestion) {
        examQuestionService.update(examQuestion);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @ResponseBody
    public R delete(@RequestBody Long[]ids) {
        examQuestionService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<ExamQuestionEntity> list = examQuestionService.queryList(params);

        return R.ok().put("list", list);
    }
}
