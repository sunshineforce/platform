package com.platform.controller;

import com.platform.entity.SysUserEntity;
import com.platform.utils.ShiroUtils;

/**
 * Controller公共组件
 *
 * @author admin
 *
 * @date 2016年11月9日 下午9:42:26
 */
public abstract class AbstractController {
    protected SysUserEntity getUser() {
        return ShiroUtils.getUserEntity();
    }

    protected Long getUserId() {
        return getUser().getUserId();
    }

    protected Long getDeptId() {
        return getUser().getDeptId();
    }
}
