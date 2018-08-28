package com.platform.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 级联区域Vo
 *
 * @author bjsonghongxu
 * @create 2018-08-28 18:03
 **/
public class RegionVo implements Serializable {
    private String label;
    private String value;
    private List<RegionVo> children;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<RegionVo> getChildren() {
        return children;
    }

    public void setChildren(List<RegionVo> children) {
        this.children = children;
    }
}
