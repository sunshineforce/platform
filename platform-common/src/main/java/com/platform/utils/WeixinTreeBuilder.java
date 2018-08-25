package com.platform.utils;

import com.platform.vo.WeixinTreeVo;
import com.platform.vo.WeixinTreeVo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * ProjectName: inspect
 * CreateUser:  lixiaopeng
 * CreateTime : 2018/8/10 17:29
 * ModifyUser: bjlixiaopeng
 * Class Description: 构造树状结构
 * To change this template use File | Settings | File and Code Template
 */

public class WeixinTreeBuilder implements Serializable {

    private static final long serialVersionUID = 1553518155468957634L;

    static List<WeixinTreeVo> nodes = new ArrayList<WeixinTreeVo>();

    public WeixinTreeBuilder(List<WeixinTreeVo> nodes) {
        this.nodes = nodes;
    }

    /**
     * 构造树状结构
     * @return
     */
    public static List<WeixinTreeVo> buildTree() {
        List<WeixinTreeVo>treeNodes = new ArrayList<WeixinTreeVo>();
        List<WeixinTreeVo>rootNodes = getRootNodes();
        for (WeixinTreeVo rootNode : rootNodes) {
            buildChildNodes(rootNode);
            treeNodes.add(rootNode);
        }
        return treeNodes;
    }

    /**
     * 递归子节点
     * @param node
     */
    public static void buildChildNodes(WeixinTreeVo node) {
        List<WeixinTreeVo> children = getChildNodes(node);
        if (!children.isEmpty()) {
            for(WeixinTreeVo child : children) {
                buildChildNodes(child);
            }
            node.setChildren(children);
        }
    }

    /**
     * 获取父节点下所有的子节点
     * @param parentNode
     * @return
     */
    public static List<WeixinTreeVo> getChildNodes(WeixinTreeVo parentNode) {
        List<WeixinTreeVo>childNodes = new ArrayList<WeixinTreeVo>();
        for (WeixinTreeVo n : nodes){
            if (parentNode.getValue().equals(n.getPid())) {
                childNodes.add(n);
            }
        }
        return childNodes;
    }

    /**
     * 获取所有的根节点
     * @return
     */
    public static List<WeixinTreeVo> getRootNodes() {
        List<WeixinTreeVo> rootNodes = new ArrayList<WeixinTreeVo>();
        for (WeixinTreeVo n : nodes){
            if (isRootNode(n)) {
                rootNodes.add(n);
            }
        }
        return rootNodes;
    }

    /**
     * 判断是否为根节点
     * @return
     */
    public static boolean isRootNode(WeixinTreeVo node) {
        boolean isRootNode = true;
        for (WeixinTreeVo n : nodes){
            if (node.getPid().equals(n.getValue())) {
                isRootNode= false;
                break;
            }
        }
        return isRootNode;
    }

    public List<WeixinTreeVo> getNodes() {
        return nodes;
    }

    public void setNodes(List<WeixinTreeVo> nodes) {
        this.nodes = nodes;
    }
}
