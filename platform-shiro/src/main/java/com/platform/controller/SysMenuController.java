package com.platform.controller;

import com.platform.annotation.SysLog;
import com.platform.constants.CommonConstant;
import com.platform.entity.SysMenuEntity;
import com.platform.entity.SysUserEntity;
import com.platform.service.SysMenuService;
import com.platform.utils.*;
import com.platform.utils.Constant.MenuType;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统菜单
 * @author admin
 * @date 2016年10月27日 下午9:58:15
 */
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController extends AbstractController {
    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 所有菜单列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<SysMenuEntity> menuList = sysMenuService.queryList(query);
        int total = sysMenuService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(menuList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 所有菜单列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {
        //查询列表数据
        List<SysMenuEntity> menuList = sysMenuService.queryList(params);
        return R.ok().put("list", menuList);
    }

    /**
     * 选择菜单(添加、修改菜单)
     */
    @RequestMapping("/select")
    public R select() {
        //查询列表数据
        List<SysMenuEntity> menuList = sysMenuService.queryNotButtonList();

        //添加顶级菜单
        SysMenuEntity root = new SysMenuEntity();
        root.setMenuId(0L);
        root.setName("一级菜单");
        root.setParentId(-1L);
        root.setOpen(true);
        menuList.add(root);

        return R.ok().put("menuList", menuList);
    }

    /**
     * 角色授权菜单
     */
    @RequestMapping("/perms")
    public R perms() {
        //查询列表数据
        List<SysMenuEntity> menuList = null;
        SysUserEntity user = getUser();
        if (null == user){
            return R.error("用户未登录！");
        }
        if (user.getUserId() == Constant.SUPER_ADMIN) { // 超级管理员可以看到所有的菜单
            menuList = sysMenuService.queryList(new HashMap<String, Object>());
        } else {
            if (null != user.getEnterpriseId()){ // 企业用户
                menuList = sysMenuService.queryMenuListByRoleId(CommonConstant.COMPANY_ROLE_ID); // 企业角色
            }else{ //总控用户
                menuList = sysMenuService.queryMenuListByRoleId(CommonConstant.SYSTEM_ROLE_ID); // 总控
            }

        }
        return R.ok().put("menuList", menuList);
    }

    /**
     * 菜单信息
     */
    @RequestMapping("/info/{menuId}")
    public R info(@PathVariable("menuId") Long menuId) {
        SysMenuEntity menu = sysMenuService.queryObject(menuId);
        return R.ok().put("menu", menu);
    }

    /**
     * 保存
     */
    @SysLog("保存菜单")
    @RequestMapping("/save")
    @RequiresPermissions("sys:menu:save")
    public R save(@RequestBody SysMenuEntity menu) {
        //数据校验
        verifyForm(menu);
        sysMenuService.save(menu);
        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("修改菜单")
    @RequestMapping("/update")
    @RequiresPermissions("sys:menu:update")
    public R update(@RequestBody SysMenuEntity menu) {
        //数据校验
        verifyForm(menu);
        sysMenuService.update(menu);
        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除菜单")
    @RequestMapping("/delete")
    @RequiresPermissions("sys:menu:delete")
    public R delete(@RequestBody Long[] menuIds) {
        for (Long menuId : menuIds) {
            if (menuId.longValue() <= 30) {
                return R.error("系统菜单，不能删除");
            }
        }
        Long menuId = menuIds[0];
        List<Long> ids = new ArrayList<Long>();
        ids.add(menuId);

        List<SysMenuEntity> menuList = sysMenuService.queryListParentId(menuId,null);
        List<SysMenuEntity> menuTreeList = getMenuTreeList(menuList, null);
        for (SysMenuEntity menuEntity : menuTreeList) {
            ids.add(menuEntity.getMenuId());
            List<SysMenuEntity> sub = menuEntity.getList();
            if (CollectionUtils.isNotEmpty(sub)) {
                for (SysMenuEntity entity : sub) {
                    ids.add(entity.getMenuId());
                }
            }
        }

        Long[] temp = new Long[ids.size()];
        for (int i = 0; i < ids.size(); i++) {
            temp[i] = ids.get(i);
        }
        menuIds = temp;
       //删除级联菜单
        sysMenuService.deleteBatch(menuIds);
        return R.ok();
    }

    /**
     * 递归
     */
    private List<SysMenuEntity> getMenuTreeList(List<SysMenuEntity> menuList, List<Long> menuIdList){
        List<SysMenuEntity> subMenuList = new ArrayList<SysMenuEntity>();

        for(SysMenuEntity entity : menuList){
            if(entity.getType() == MenuType.CATALOG.getValue()){//目录
                entity.setList(getMenuTreeList(sysMenuService.queryListParentId(entity.getMenuId(), menuIdList), menuIdList));
            }
            subMenuList.add(entity);
        }

        return subMenuList;
    }

    /**
     * 用户菜单列表
     */
    @RequestMapping("/user")
    public R user() {
        List<SysMenuEntity> menuList = sysMenuService.getUserMenuList(getUserId());
        return R.ok().put("menuList", menuList);
    }

    /**
     * 验证参数是否正确
     */
    private void verifyForm(SysMenuEntity menu) {
        if (StringUtils.isBlank(menu.getName())) {
            throw new RRException("菜单名称不能为空");
        }

        if (menu.getParentId() == null) {
            throw new RRException("上级菜单不能为空");
        }

        //菜单
        if (menu.getType() == MenuType.MENU.getValue()) {
            if (StringUtils.isBlank(menu.getUrl())) {
                throw new RRException("菜单URL不能为空");
            }
        }

        //上级菜单类型
        int parentType = MenuType.CATALOG.getValue();
        if (menu.getParentId() != 0) {
            SysMenuEntity parentMenu = sysMenuService.queryObject(menu.getParentId());
            parentType = parentMenu.getType();
        }

        //目录、菜单
        if (menu.getType() == MenuType.CATALOG.getValue() ||
                menu.getType() == MenuType.MENU.getValue()) {
            if (parentType != MenuType.CATALOG.getValue()) {
                throw new RRException("上级菜单只能为目录类型");
            }
            return;
        }

        //按钮
        if (menu.getType() == MenuType.BUTTON.getValue()) {
            if (parentType != MenuType.MENU.getValue()) {
                throw new RRException("上级菜单只能为菜单类型");
            }
            return;
        }
    }
}
