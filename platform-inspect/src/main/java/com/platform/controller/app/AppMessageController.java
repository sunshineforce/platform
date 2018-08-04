package com.platform.controller.app;

import com.platform.constants.CommonConstant;
import com.platform.entity.notice.NoticeEntity;
import com.platform.entity.notice.vo.NoticeVo;
import com.platform.service.notice.INoticeService;
import com.platform.utils.R;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * ProjectName: inspect
 * CreateUser:  lixiaopeng
 * CreateTime : 2018/7/24 20:30
 * ModifyUser: bjlixiaopeng
 * Class Description:消息通知
 * To change this template use File | Settings | File and Code Template
 */

@RequestMapping("/app")
public class AppMessageController {

    @Autowired
    private INoticeService noticeService;

    @RequestMapping("/notice/list")
    @ResponseBody
    public R noticeList(@RequestParam Map<String,Object> params){
        List<NoticeEntity> entityList = noticeService.queryList(params);
        List<NoticeVo> resultList = new ArrayList<NoticeVo>(entityList.size());
        if (CollectionUtils.isNotEmpty(entityList)) {
            for (NoticeEntity notice : entityList) {
                NoticeVo noticeVo = new NoticeVo(notice.getName(),notice.getCreateTime());
                resultList.add(noticeVo);
            }
        }

        return R.succeed().put(CommonConstant.DATA_KEY,resultList);
    }
}
