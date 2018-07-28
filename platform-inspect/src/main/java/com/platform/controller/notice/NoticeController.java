package com.platform.controller.notice;

import java.util.List;
import java.util.Map;

import com.platform.entity.notice.NoticeEntity;
import com.platform.service.notice.INoticeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * 消息通知表Controller
 *
 * @author admin
 *  
 * @date 2018-07-28 10:46:23
 */
@Controller
@RequestMapping("notice")
public class NoticeController {
    @Autowired
    private INoticeService noticeService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("notice:list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<NoticeEntity> noticeList = noticeService.queryList(query);
        int total = noticeService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(noticeList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("notice:info")
    @ResponseBody
    public R info(@PathVariable("id") Long id) {
        NoticeEntity notice = noticeService.queryObject(id);

        return R.ok().put("notice", notice);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("notice:save")
    @ResponseBody
    public R save(@RequestBody NoticeEntity notice) {
        noticeService.save(notice);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("notice:update")
    @ResponseBody
    public R update(@RequestBody NoticeEntity notice) {
        noticeService.update(notice);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("notice:delete")
    @ResponseBody
    public R delete(@RequestBody Long[]ids) {
        noticeService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<NoticeEntity> list = noticeService.queryList(params);

        return R.ok().put("list", list);
    }
}
