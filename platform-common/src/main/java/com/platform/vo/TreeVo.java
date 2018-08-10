package com.platform.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * ProjectName: inspect
 * CreateUser:  lixiaopeng
 * CreateTime : 2018/7/28 15:39
 * ModifyUser: bjlixiaopeng
 * Class Description:
 * To change this template use File | Settings | File and Code Template
 */

public class TreeVo implements Serializable {
    private static final long serialVersionUID = 6417017624744971017L;

    private Long id;
    private String name;
    private Long pid;
    List<TreeVo> children;

    public TreeVo() {
    }

    public TreeVo(Long id, String name, Long pid) {
        this.id = id;
        this.name = name;
        this.pid = pid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public List<TreeVo> getChildren() {
        return children;
    }

    public void setChildren(List<TreeVo> children) {
        this.children = children;
    }
}
