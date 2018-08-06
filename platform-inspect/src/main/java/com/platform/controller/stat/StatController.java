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
        String startTime = "2018-08-05";//String.valueOf(params.get("startTime"));
        String endTime = "2018-08-07";//String.valueOf(params.get("endTime"));
        Date sTime = DateUtils.convertStringToDate(startTime);
        Date eTime = DateUtils.convertStringToDate(endTime);
        List<String> dateList = DateUtils.getDateList(sTime, eTime, DateUtils.DATE_PATTERN);

        params.put("startTime",null);
        params.put("endTime",null);

        List<StaExceptionDayEntity> orders = staExceptionDayService.queryList(params);
        List<StaTaskDayEntity> tasks = staTaskDayService.queryList(params);

        List<Map<String,Object>> tasksSeries = new ArrayList<>();
        Map<String,Object> orderMap = new HashMap<>();
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
                    if (dateList.get(i).equals("2018-08-05")|| dateList.get(i).equals("2018-08-06") ){
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
//            for (StaExceptionDayEntity  order : orders) {
//
//            }
        }

        return R.ok().put("tasksSeries", tasksSeries).put("order",orderMap).put("xData",dateList);
    }


}
