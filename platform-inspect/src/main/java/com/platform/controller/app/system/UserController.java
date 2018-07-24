package com.platform.controller.app.system;

import com.platform.annotation.SysLog;
import com.platform.constants.CommonConstant;
import com.platform.controller.AbstractController;
import com.platform.entity.AppUserEntity;
import com.platform.service.SysUserService;
import com.platform.utils.DateUtils;
import com.platform.utils.R;
import com.platform.utils.ShiroUtils;
import com.platform.utils.SystemCode;
import com.platform.validator.Assert;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Created with IntelliJ IDEA
 * ProjectName: inspect
 * CreateUser:  lixiaopeng
 * CreateTime : 2018/7/24 10:16
 * ModifyUser: bjlixiaopeng
 * Class Description:
 * To change this template use File | Settings | File and Code Template
 */

@Controller
@RequestMapping("/app")
public class UserController extends AbstractController {

    public static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private SysUserService sysUserService;

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public R login(String username, String password){
        Subject subject = ShiroUtils.getSubject();
        password = new Sha256Hash(password).toHex();
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            if (token == null) {
                R.loginError();
            }
            subject.login(token);
            AppUserEntity appUser = (AppUserEntity) subject.getPrincipal();
            logger.info(appUser.getUserName() +" login system at :" + DateUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
            subject.getSession().setAttribute(CommonConstant.APP_LOGIN_USER,appUser);
            return R.ok();
        } catch (AuthenticationException e) {
            return R.failure();
        } catch (InvalidSessionException e) {
            return R.failure();
        }
    }

    /**
     * 修改登录用户密码
     */
    @SysLog("修改密码")
    @RequestMapping("/password")
    public R password(String password, String newPassword) {
        Assert.isBlank(newPassword, "新密码不为能空");

        //sha256加密
        password = new Sha256Hash(password).toHex();
        //sha256加密
        newPassword = new Sha256Hash(newPassword).toHex();

        //更新密码
        int count = sysUserService.updatePassword(getUserId(), password, newPassword);
        if (count == 0) {
            return R.error(SystemCode.PASSWORD_VALID.getCode(),"原密码不正确");
        }
        return R.ok();
    }

    /**
     * 获取登录的用户信息
     */
    @RequestMapping("/info")
    public R info() {
        return R.ok().put("data", getUser());
    }

    /**
     * 退出
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        ShiroUtils.logout();
        return "redirect:/";
    }

}
