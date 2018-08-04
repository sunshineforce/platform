package com.platform.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * 随机数帮助类
 *
 * @author bjsonghongxu
 * @create 2018-08-04 15:21
 **/
public class RandomUtils {


    /**
     * 生成唯一编号
     */
    public static String generateSNNumber() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        String timeStr = DateUtils.format(cal.getTime(), DateUtils.DATE_TIME_PATTERN_YYYY_MM_DD_HH_MM_SS_SSS);
        return timeStr + CharUtil.getRandomNum(6);
    }

}
