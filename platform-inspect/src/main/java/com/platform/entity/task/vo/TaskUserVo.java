package com.platform.entity.task.vo;

import com.platform.vo.SelectVo;
import com.platform.vo.TreeVo;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * ProjectName: inspect
 * CreateUser:  lixiaopeng
 * CreateTime : 2018/8/11 12:49
 * ModifyUser: bjlixiaopeng
 * Class Description: 创建任务选择人员
 * To change this template use File | Settings | File and Code Template
 */

public class TaskUserVo implements Serializable {

    private static final long serialVersionUID = 8966048138472972433L;

    //区域名称
    private String regionName;
    //人员
    private List<SelectVo> members;
    //子节点
    private List<TreeVo> children;

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public List<SelectVo> getMembers() {
        return members;
    }

    public void setMembers(List<SelectVo> members) {
        this.members = members;
    }

    public List<TreeVo> getChildren() {
        return children;
    }

    public void setChildren(List<TreeVo> children) {
        this.children = children;
    }
}
