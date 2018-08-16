package com.platform.constants;

import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2018/2/13.
 * 通用常量声明
 */
public class CommonConstant {

    //正常状态
    public static final  int  USEABLE_STATUS = 0;
    //不可用状态
    public static final  int  UN_USEABLE_STATUS = 1;
    //删除状态
    public static final  int  DELETE_STATUS = 1;

    //男
    public static final  int  GENDER_MAN = 0;
    //女
    public static final  int  GENDER_WOMAN = 1;


    public static final String LOGIN_USER = "";

    public static final String APP_LOGIN_USER="";

    public static final String DATA_KEY = "list";

    /** 企业账户角色Id */
    public static final long COMPANY_ROLE_ID =  3;

    public static final String AUTH_URL="http://runyapp.s1.natapp.cc/authz/oauth/token";

    public static final String AUTH_TYPE="ex";

    public static List<BasicNameValuePair> token = new ArrayList<BasicNameValuePair>(4);

    public CommonConstant() {
        token.add(new BasicNameValuePair("client_id","vQzpPTDRs0fi7pNGmnv4"));
        token.add(new BasicNameValuePair("client_secret","xVfxCsvtp2HKUGcxEQrh"));
        token.add(new BasicNameValuePair("grant_type","client_credentials"));
        token.add(new BasicNameValuePair("scope","read write"));
    }
}
