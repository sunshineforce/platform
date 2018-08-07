package com.platform.controller.stat;

import com.platform.entity.stat.StaExceptionDayEntity;
import com.platform.entity.stat.StaTaskDayEntity;
import com.platform.service.stat.StaExceptionDayService;
import com.platform.service.stat.StaTaskDayService;
import com.platform.utils.DateUtils;
import com.platform.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

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
        String startTime = String.valueOf(params.get("startTime"));
        String endTime = String.valueOf(params.get("endTime"));
        Date sTime = DateUtils.convertStringToDate(startTime);
        Date eTime = DateUtils.convertStringToDate(endTime);
        List<String> dateList = DateUtils.getDateList(sTime, eTime, DateUtils.DATE_PATTERN);

        params.put("startTime",DateUtils.dateToIntStr(startTime));
        params.put("endTime",DateUtils.dateToIntStr(endTime));

        List<StaExceptionDayEntity> orders = staExceptionDayService.queryList(params);
        List<StaTaskDayEntity> tasks = staTaskDayService.queryList(params);

        List<Map<String,Object>> tasksSeries = new ArrayList<>();
        List<Map<String,Object>> orderSeries = new ArrayList<>();
        if (null != tasks && tasks.size() > 0){
            Map<String,Object> m1 = new HashMap<>();
            Map<String,Object> m2 = new HashMap<>();
            Map<String,Object> m3 = new HashMap<>();
            Map<String,Object> m4 = new HashMap<>();

            int []d1 = new int[dateList.size()];
            int []d2 = new int[dateList.size()];
            int []d3 = new int[dateList.size()];
            int []d4 = new int[dateList.size()];

            for (int i = 0 ; i < dateList.size(); i++) {
                boolean has = false;
                for (StaTaskDayEntity  task : tasks) {
                    if (dateList.get(i).equals(DateUtils.subDate(String.valueOf(task.getStatDate()),0)) ){
                        d1[i] = task.getPendingNum() != null ? task.getPendingNum() : 0 ;
                        d2[i] = task.getExecutingNum() != null ? task.getExecutingNum() : 0 ;
                        d3[i] = task.getFinishNum() != null ? task.getFinishNum() : 0;
                        d4[i] = task.getTimeoutNum() != null ? task.getTimeoutNum() : 0;
                        has = true;
                        break;
                    }

                }

                if (!has){
                    d1[i] = 0;
                    d2[i] = 0;
                    d3[i] = 0;
                    d4[i] = 0;
                }
            }

            //'待执行', '执行中', '已完成', '已超时'
            m1.put("name","待执行");
            m1.put("type","bar");
            m1.put("data",d1);

            m2.put("name","执行中");
            m2.put("type","bar");
            m2.put("data",d2);

            m3.put("name","已完成");
            m3.put("type","bar");
            m3.put("data",d3);

            m4.put("name","已超时");
            m4.put("type","bar");
            m4.put("data",d4);

            tasksSeries.add(m1);
            tasksSeries.add(m2);
            tasksSeries.add(m3);
            tasksSeries.add(m4);

        }

        if (null != orders && orders.size() > 0){
            Map<String,Object> m1 = new HashMap<>();
            Map<String,Object> m2 = new HashMap<>();
            Map<String,Object> m3 = new HashMap<>();
            int pendingNum = 0;
            int reviewNum = 0;
            int finishNum = 0;
            for (StaExceptionDayEntity  order : orders) {
                 pendingNum += order.getPendingNum() != null ? order.getPendingNum() : 0;
                 reviewNum += order.getReviewNum() != null ? order.getReviewNum() : 0;
                 finishNum += order.getFinishNum() != null ? order.getFinishNum() : 0;
            }
           /* {value:335, name:'待处理'},
            {value:310, name:'待复查'},
            {value:234, name:'已完成'}*/
            m1.put("name","待处理");
            m1.put("value",pendingNum);

            m2.put("name","待复查");
            m2.put("value",reviewNum);

            m3.put("name","已完成");
            m3.put("value",finishNum);

            orderSeries.add(m1);
            orderSeries.add(m2);
            orderSeries.add(m3);
        }

        return R.ok().put("tasksSeries", tasksSeries).put("orderSeries",orderSeries).put("xData",dateList);
    }


}
