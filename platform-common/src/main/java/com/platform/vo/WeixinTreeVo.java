package com.platform.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WeixinTreeVo implements Serializable{

    private static final long serialVersionUID = -1690963112181802516L;

    private String label;
    private Integer value;
    private Integer pid;

    private List<WeixinTreeVo> children;

    public WeixinTreeVo() {
        children = new ArrayList<WeixinTreeVo>();
    }

    public WeixinTreeVo(String label, Integer value, Integer pid, List<WeixinTreeVo> children) {
        this.label = label;
        this.value = value;
        this.pid = pid;
        this.children = children;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public List<WeixinTreeVo> getChildren() {
        return children;
    }

    public void setChildren(List<WeixinTreeVo> children) {
        this.children = children;
    }
}
