package com.platform.controller.exam;

import com.alibaba.fastjson.JSON;
import com.platform.entity.exam.ExamEntity;
import com.platform.entity.exam.ExamQuestionEntity;
import com.platform.entity.exam.ExamQuestionItemEntity;
import com.platform.service.exam.ExamQuestionItemService;
import com.platform.service.exam.ExamQuestionService;
import com.platform.service.exam.ExamService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;
import org.apache.commons.collections.map.HashedMap;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(ExamController.class);

    @Autowired
    private ExamService examService;

    @Autowired
    private ExamQuestionService examQuestionService;

    @Autowired
    private ExamQuestionItemService examQuestionItemService;



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
        Map<String, Object> params = new HashedMap();
        params.put("examId",exam.getId());
        List<ExamQuestionEntity> examQuestionEntities = examQuestionService.queryList(params);
        if (null != examQuestionEntities && examQuestionEntities.size() > 0){
            for (ExamQuestionEntity question : examQuestionEntities) {
                Map<String, Object> params1 = new HashedMap();
                params1.put("questionId",question.getId());
                List<ExamQuestionItemEntity> questionItemEntities = examQuestionItemService.queryList(params1);
                question.setQuestionItems(questionItemEntities);
            }
        }
        exam.setQuestionList(examQuestionEntities);
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
        logger.debug("getQuestionJson -------" + exam.getQuestionJson());
        List<ExamQuestionEntity> list = JSON.parseArray(exam.getQuestionJson(), ExamQuestionEntity.class);
        if (null != list && list.size() > 0){
            for (ExamQuestionEntity question : list) {
                question.setCreateTime(time);
                question.setExamId(exam.getId());
                examQuestionService.save(question);
                List<ExamQuestionItemEntity> questionItems = question.getQuestionItems();
                if (null != questionItems && questionItems.size() > 0){
                    for (ExamQuestionItemEntity questionItem : questionItems) {
                        questionItem.setQuestionId(question.getId());
                        examQuestionItemService.save(questionItem);
                    }
                }
            }
        }
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
        logger.debug("getQuestionJson -------" + exam.getQuestionJson());
        List<ExamQuestionEntity> list = JSON.parseArray(exam.getQuestionJson(), ExamQuestionEntity.class);
        if (null != list && list.size() > 0){
            for (ExamQuestionEntity question : list) {
                question.setExamId(exam.getId());
                examQuestionService.update(question);
                List<ExamQuestionItemEntity> questionItems = question.getQuestionItems();
                if (null != questionItems && questionItems.size() > 0){
                    examQuestionItemService.deleteByQid(question.getId());
                    for (ExamQuestionItemEntity questionItem : questionItems) {
                        questionItem.setQuestionId(question.getId());
                        examQuestionItemService.save(questionItem);
                    }
                }
            }
        }
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("exam:delete")
    @ResponseBody
    public R delete(@RequestBody Long[]ids) {
        //examService.deleteBatch(ids);
        if (null != ids && ids.length > 0){
            for (Long id : ids) {
                ExamEntity exam = new ExamEntity();
                exam.setId(id);
                Date time = new Date();
                exam.setUpdateTime(time);
                exam.setEnabled(1); //删除态
                examService.update(exam);
            }
        }

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
