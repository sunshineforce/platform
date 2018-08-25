package com.platform.controller.exam;

import com.platform.controller.AbstractController;
import com.platform.entity.SysUserEntity;
import com.platform.entity.exam.ExamMemberEntity;
import com.platform.service.exam.ExamMemberService;
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
 * 考试参考人员Controller
 *
 * @author admin
 *  
 * @date 2018-07-24 09:45:45
 */
@Controller
@RequestMapping("exammember")
public class ExamMemberController  extends AbstractController {
    @Autowired
    private ExamMemberService examMemberService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        SysUserEntity user = getUser();
        if (user != null && user.getEnterpriseId() != null){
            params.put("enterpriseId",user.getEnterpriseId());
        }
        //查询列表数据
        Query query = new Query(params);

        List<ExamMemberEntity> examMemberList = examMemberService.queryExamMembers(query);
        int total = examMemberService.queryExamMembersTotal(query);

        PageUtils pageUtil = new PageUtils(examMemberList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @ResponseBody
    public R info(@PathVariable("id") Long id) {
        ExamMemberEntity examMember = examMemberService.queryObject(id);

        return R.ok().put("examMember", examMember);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("exammember:save")
    @ResponseBody
    public R save(@RequestBody ExamMemberEntity examMember) {
        examMemberService.save(examMember);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("exammember:update")
    @ResponseBody
    public R update(@RequestBody ExamMemberEntity examMember) {
        examMemberService.update(examMember);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("exammember:delete")
    @ResponseBody
    public R delete(@RequestBody Long[]ids) {
        examMemberService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<ExamMemberEntity> list = examMemberService.queryList(params);

        return R.ok().put("list", list);
    }
}
