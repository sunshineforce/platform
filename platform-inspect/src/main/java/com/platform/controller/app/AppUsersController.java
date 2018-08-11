package com.platform.controller.app;

import com.platform.annotation.SysLog;
import com.platform.constants.CommonConstant;
import com.platform.controller.AbstractController;
import com.platform.entity.AppUserEntity;
import com.platform.entity.AppUserVo;
import com.platform.entity.SysRegionEntity;
import com.platform.service.AppUserService;
import com.platform.service.SysRegionService;
import com.platform.utils.BeanUtils;
import com.platform.utils.DateUtils;
import com.platform.utils.R;
import com.platform.utils.ShiroUtils;
import com.platform.utils.enums.SystemCode;
import com.platform.validator.Assert;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

@RestController
@RequestMapping("/app")
public class AppUsersController extends AbstractController{

    public static final Logger logger = LoggerFactory.getLogger(AppUsersController.class);

    public static final String RESET_PASSOWRD_SMS = "";

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private SysRegionService regionService;

    @ResponseBody
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
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
            logger.info(appUser.getUsername() +" login system at :" + DateUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
            subject.getSession().setAttribute(CommonConstant.APP_LOGIN_USER,appUser);
            return R.succeed();
        } catch (AuthenticationException e) {
            return R.error();
        } catch (InvalidSessionException e) {
            return R.error();
        }
    }

    /**
     * 修改登录用户密码
     */
    @SysLog("修改密码")
    @RequestMapping(value="/user/password/reset",method = RequestMethod.POST)
    public R password(String password, String newPassword) {
        Assert.isBlank(newPassword, "新密码不为能空");

        //sha256加密
        password = new Sha256Hash(password).toHex();
        //sha256加密
        newPassword = new Sha256Hash(newPassword).toHex();

        //更新密码
        int count = appUserService.resetPassword(getUserId(), password, newPassword);
        if (count == 0) {
            return R.error(SystemCode.PASSWORD_VALID.getCode(),"原密码不正确");
        }
        return R.ok();
    }

    /**
     * 获取登录的用户信息
     */
    @RequestMapping(value="/user/info", method = RequestMethod.POST)
    public R info() {
        AppUserEntity appUserEntity = appUserService.queryObject(11L);
        appUserEntity.setRegionName(getRegionName(appUserEntity.getRegionId()));

        AppUserVo appUser = new AppUserVo();
        BeanUtils.copyProperties(appUserEntity,appUser);

        return R.ok().put("data", appUser);
    }

    /**
     * 用户信息
     */
    @RequestMapping(value="/user/info/{userId}", method = RequestMethod.GET)
    public R info(@PathVariable("userId") Long userId) {
        AppUserEntity user = appUserService.queryObject(userId);
        return R.ok().put("user", user);
    }

    /**
     * 退出
     */
    @RequestMapping(value = "/user/logout", method = RequestMethod.GET)
    public String logout() {
        ShiroUtils.logout();
        return "redirect:/";
    }

    /**
     * 获取某个区域的全名，自动拼接上上级区域名称
     * @return
     */
    public String getRegionName(Integer regionId) {
        String regionName = "";
        if (regionId == null) {
            return regionName;
        }
        SysRegionEntity region = regionService.queryObject(regionId);

        if(region != null) {
            if (region.getParentId() != 0) {
                regionName =  getRegionName(region.getParentId()) + region.getName();  //  递归调用方法getRegionString(Long regionId)，停止条件设为regionId==null为真
            }
        }
        regionName = regionName.replace("市辖区","");
        return regionName;
    }

}
