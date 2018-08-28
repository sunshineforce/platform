package com.platform.service.common;

import com.platform.constants.CommonConstant;
import com.platform.dao.AppUserDao;
import com.platform.dao.SysRegionDao;
import com.platform.entity.AppUserEntity;
import com.platform.entity.SysRegionEntity;
import com.platform.utils.ShiroUtils;
import com.platform.utils.StringUtils;
import com.platform.vo.SelectVo;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("commonService")
public class CommonService {

    @Autowired
    private AppUserDao appUserDao;

    @Autowired
    private SysRegionDao regionDao;

    /**
     * 获取某个区域的全名，自动拼接上上级区域名称
     * @return
     */
    public String getRegionName(Integer regionId) {
        String regionName = "";
        if (regionId == null) {
            return regionName;
        }
        SysRegionEntity region = regionDao.queryObject(regionId);

        if(region != null) {
            if (region.getParentId() != 0) {
                regionName =  getRegionName(region.getParentId()) + region.getName();  //  递归调用方法getRegionString(Long regionId)，停止条件设为regionId==null为真
            }
        }
        regionName = regionName.replace("市辖区","");
        return regionName;
    }

    /**
     * 获取上级名称，逗号分隔
     * @param chiefIds
     * @return
     */
    public String getChiefNames(String chiefIds){
        if (StringUtils.isNullOrEmpty(chiefIds)) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        String[] ids = chiefIds.split(",");

        for (String id : ids) {
            AppUserEntity appUser = appUserDao.queryObject(Long.valueOf(id));
            builder.append(appUser.getRealname());
            builder.append(",");
        }
        if (builder.length()>0) {
            builder.setLength(builder.length()-1);
        }

        return builder.toString();
    }

    /**
     * 获取当前登录用户信息
     * @return
     */
    public AppUserEntity getCurrentLoginUser(){
        Subject subject = ShiroUtils.getSubject();
        AppUserEntity appUser = (AppUserEntity) subject.getSession().getAttribute(CommonConstant.APP_LOGIN_USER);
        return appUser;
    }

    public List<SelectVo> getSuperior(){
        List<SelectVo> userList = null;
//        AppUserEntity appUser = getCurrentLoginUser();
        AppUserEntity appUser = appUserDao.queryObject(11L);
        if (appUser != null && StringUtils.isNotEmpty(appUser.getSuperior())) {
            String[] arr = appUser.getSuperior().split(",");
            userList = new ArrayList<SelectVo>(arr.length);
            for (String s : arr) {
                AppUserEntity user = appUserDao.queryObject(Long.valueOf(s));
                SelectVo selectVo = new SelectVo(Integer.valueOf(String.valueOf(user.getId())),user.getRealname());
                userList.add(selectVo);
            }
        }
        return userList;
    }

}
