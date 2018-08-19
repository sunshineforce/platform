package com.platform.controller.app;

import com.platform.annotation.SysLog;
import com.platform.constants.CommonConstant;
import com.platform.controller.AbstractController;
import com.platform.entity.AppUserEntity;
import com.platform.entity.AppUserVo;
import com.platform.entity.regulation.vo.KnowledgeCollectVo;
import com.platform.service.AppUserService;
import com.platform.service.common.CommonService;
import com.platform.service.regulation.UserRegulationRelService;
import com.platform.utils.*;
import com.platform.utils.enums.SystemCode;
import com.platform.validator.Assert;
import com.platform.vo.SelectVo;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    private CommonService commonService;

    @Autowired
    private UserRegulationRelService knowledgeService;

    @ResponseBody
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public R login(String username, String password){
        Subject subject = ShiroUtils.getSubject();
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
        appUserEntity.setRegionName(commonService.getRegionName(appUserEntity.getRegionId()));

        AppUserVo appUser = new AppUserVo();
        BeanUtils.copyProperties(appUserEntity,appUser);
        appUser.setUserId(appUserEntity.getId());

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

    @RequestMapping(value = "/user/superior")
    public List<SelectVo> querySuperior(){
        Subject subject = ShiroUtils.getSubject();
        AppUserEntity appUser = (AppUserEntity) subject.getSession().getAttribute(CommonConstant.APP_LOGIN_USER);
        if (appUser != null && StringUtils.isNotEmpty(appUser.getSuperior())) {
            String[] arr = appUser.getSuperior().split(",");
            List<SelectVo> userList = new ArrayList<SelectVo>(arr.length);
            for (String s : arr) {
                AppUserEntity user = appUserService.queryObject(Long.valueOf(s));
                SelectVo selectVo = new SelectVo(Integer.valueOf(String.valueOf(user.getId())),user.getRealname());
                userList.add(selectVo);
            }
            return userList;
        }
        return null;
    }

    @RequestMapping(value = "/user/knowledge")
    public R queryKnowledgeList(@RequestParam Map<String, Object> params){
        PageUtils pageUtils = null;
        try {
            pageUtils = knowledgeService.queryKnowledgeList(params);
            return R.succeed().put("page",pageUtils);
        } catch (Exception e) {
            return R.failure();
        }
    }

}
