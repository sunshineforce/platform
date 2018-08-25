package com.platform.vo;

import java.io.Serializable;
import java.util.List;

public class WeixinTreeVo implements Serializable{

    private static final long serialVersionUID = -1690963112181802516L;

    private String label;
    private Integer value;
    private Integer pid;
    private List<WeixinTreeVo> children;

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
