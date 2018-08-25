package com.platform.util;

import com.alibaba.fastjson.JSON;
import com.platform.constants.CommonConstant;
import com.platform.entity.dto.AuthVo;
import com.platform.utils.HttpUtil;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

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

    public static AuthVo getLoginToken(){
        List<BasicNameValuePair> authParams = new ArrayList<BasicNameValuePair>(4);
        authParams.add(new BasicNameValuePair("client_id",CommonConstant.client_id));
        authParams.add(new BasicNameValuePair("client_secret",CommonConstant.client_secret));
        authParams.add(new BasicNameValuePair("grant_type",CommonConstant.grant_type));
        authParams.add(new BasicNameValuePair("scope",CommonConstant.scope));

        String result = HttpUtil.postJsonWithForm(CommonConstant.AUTH_URL,authParams);
        AuthVo auth = JSON.parseObject(result,AuthVo.class);
        auth.setEffectTime((System.currentTimeMillis()/1000));
        return auth;
    }

    //判断token是否过期
    public static boolean isExpire(AuthVo authVo){
        Long currentDate = System.currentTimeMillis()/1000;
        if (currentDate-authVo.getEffectTime() > authVo.getExpires_in()) {
            return true;
        }else {
            return false;
        }
    }

}
