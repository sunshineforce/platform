package com.platform.controller.app;

import com.platform.controller.AbstractController;
import com.platform.entity.SysUserEntity;
import com.platform.entity.notice.NoticeEntity;
import com.platform.service.notice.INoticeService;
import com.platform.utils.R;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * ProjectName: inspect
 * CreateUser:  lixiaopeng
 * CreateTime : 2018/7/28 18:27
 * ModifyUser: bjlixiaopeng
 * Class Description:
 * To change this template use File | Settings | File and Code Template
 */

@RestController
@RequestMapping("/app")
public class NoticeController extends AbstractController {

    @Autowired
    private INoticeService noticeService;

    @RequestMapping("/notice/list")
    public R queryAllNotice(){
        HashMap<String,Object> params = new HashMap<String,Object>();
        SysUserEntity user = (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
        params.put("userId",user.getUserId());
        List<NoticeEntity> list = noticeService.queryList(params);
        return R.succeed().put("list",list);
    }
}
