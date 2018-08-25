package com.platform.util;

import com.alibaba.fastjson.JSON;
import com.platform.constants.CommonConstant;
import com.platform.entity.dto.AuthVo;
import com.platform.utils.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Created with IntelliJ IDEA
 * ProjectName: inspect
 * CreateUser:  lixiaopeng
 * CreateTime : 2018/8/16 18:15
 * ModifyUser: bjlixiaopeng
 * Class Description:
 * To change this template use File | Settings | File and Code Template
 */


public class MaterialBindUtils {

    public static final Logger logger = LoggerFactory.getLogger(MaterialBindUtils.class);

    public static AuthVo getLoginToken(){
        String result = HttpUtil.postJsonWithForm(CommonConstant.AUTH_URL,CommonConstant.token);
        AuthVo auth = JSON.parseObject(result,AuthVo.class);
        auth.setEffectTime(new Date().getTime());
        return auth;
    }

    //判断token是否过期
    public static boolean isExpire(AuthVo authVo){
        Long currentDate = new Date().getTime();
        if (currentDate-authVo.getEffectTime() > authVo.getExpires_in()) {
            return true;
        }else {
            return false;
        }
    }

}
