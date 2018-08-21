package com.platform.util;

import com.platform.utils.DateUtils;

import java.util.Date;

/**
 * 任务工具类
 *
 * @author bjsonghongxu
 * @create 2018-08-21 17:03
 **/
public class TaskUtils {

    private TaskUtils(){}

    /**
     * 计算循环任务的执行截止时间
     * @param startTime 开始时间
     * @param schedule 时限
     * @return 执行截止时间
     */
    public static Date calCircleTaskEndTime(Date startTime , Integer schedule ){
        if (null == startTime) return null;
        Date endTime = DateUtils.parseDate(DateUtils.format(startTime) + " 23:59:59",null);
        if (schedule != null){
            endTime = DateUtils.getAfterOneDay(startTime,schedule);
        }
        return endTime;
    }

    /**
     * 计算循环任务的执行下次开始时间
     * @param startTime 开始时间
     * @param scheduleCycle  每天 1 每周 2 每月 3 每年 4
     * @return 下次开始时间
     */
    public static Date calNexStartTime(Date startTime ,Integer scheduleCycle){
        if (null == startTime) return null;
        int num = 1;
        switch (scheduleCycle.intValue()){
            case 2 : num = 7; break;
            case 3 : num = 30; break;
            case 4 : num = 365; break;
            default:
                num  = 1;
        }
        startTime = DateUtils.getAfterOneDay(startTime,num);
        return startTime;
    }

    public static void main(String[] args) {
        System.out.println(calCircleTaskEndTime(new Date(),null));
        System.out.println(calNexStartTime(new Date(),3));
    }
}
