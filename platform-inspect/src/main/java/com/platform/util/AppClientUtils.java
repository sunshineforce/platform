package com.platform.util;

import com.platform.constants.CommonConstant;
import com.platform.entity.AppUserEntity;
import com.platform.entity.dto.AuthVo;
import com.platform.entity.enterprise.EnterpriseEntity;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.shiro.SecurityUtils;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * ProjectName: inspect
 * CreateUser:  lixiaopeng
 * CreateTime : 2018/8/25 11:16
 * ModifyUser: bjlixiaopeng
 * Class Description:
 * To change this template use File | Settings | File and Code Template
 */


public class AppClientUtils {
    public static void setEnterpriseDefaultValue(EnterpriseEntity enterprise){
        Date currDate = new Date();
        AppUserEntity sessionUser = (AppUserEntity) SecurityUtils.getSubject().getSession().getAttribute(CommonConstant.APP_LOGIN_USER);
        if (enterprise.getId() == null) {
            enterprise.setCreateTime(currDate);
            enterprise.setCreator(sessionUser.getUsername());
            enterprise.setEnabled(0);
        }else {
            enterprise.setUpdateTime(currDate);
            enterprise.setUpdator(sessionUser.getUsername());
        }
    }

    /**
     * 根据登录token获取数据
     * @param auth token实体
     * @param url 访问url
     * @param params 参数
     * @return
     */
    public static String sendPost(AuthVo auth, String url, List<BasicNameValuePair> params){
        HttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        String s = "";
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params,"UTF-8"));
            httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
            httpPost.setHeader("Authorization",auth.getAccess_token()+" " + auth.getToken_type());
            HttpResponse response = client.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if(statusCode == org.apache.http.HttpStatus.SC_OK){
                HttpEntity entity = response.getEntity();
                s = EntityUtils.toString(entity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }
}
