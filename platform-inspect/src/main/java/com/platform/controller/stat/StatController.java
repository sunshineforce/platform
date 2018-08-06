package com.platform.controller.stat;

import com.platform.entity.stat.StaExceptionDayEntity;
import com.platform.entity.stat.StaTaskDayEntity;
import com.platform.service.stat.StaExceptionDayService;
import com.platform.service.stat.StaTaskDayService;
import com.platform.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 异常处理统计Controller
 *
 * @author admin
 *  
 * @date 2018-08-06 19:30:48
 */
@Controller
@RequestMapping("stat")
public class StatController {

    @Autowired
    private StaExceptionDayService staExceptionDayService;

    @Autowired
    private StaTaskDayService staTaskDayService;

    /**
     *  统计获取统计工单和异常数据
     */
    @RequestMapping("/statTaskAndOrder")
    @ResponseBody
    public R statTaskAndOrder(@RequestParam Map<String, Object> params) {
        List<StaExceptionDayEntity> order = staExceptionDayService.queryList(params);
        List<StaTaskDayEntity> task = staTaskDayService.queryList(params);
        return R.ok().put("tasks", task).put("order",order);
    }


}
