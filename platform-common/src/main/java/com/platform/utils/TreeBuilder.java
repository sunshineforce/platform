package com.platform.utils;

import com.platform.vo.TreeVo;

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

public class TreeBuilder implements Serializable {

    private static final long serialVersionUID = 1553518155468957634L;

    static List<TreeVo> nodes = new ArrayList<TreeVo>();

    public TreeBuilder(List<TreeVo> nodes) {
        this.nodes = nodes;
    }

    /**
     * 构造树状结构
     * @return
     */
    public static List<TreeVo> buildTree() {
        List<TreeVo> treeNodes = new ArrayList<TreeVo>();
        List<TreeVo>rootNodes = getRootNodes();
        for (TreeVo rootNode : rootNodes) {
            buildChildNodes(rootNode);
            treeNodes.add(rootNode);
        }
        return treeNodes;
    }

    /**
     * 递归子节点
     * @param node
     */
    public static void buildChildNodes(TreeVo node) {
        List<TreeVo> children = getChildNodes(node);
        if (!children.isEmpty()) {
            for(TreeVo child : children) {
                buildChildNodes(child);
            }
            node.setChildren(children == null ? new ArrayList<TreeVo>() : children);
        }
    }

    /**
     * 获取父节点下所有的子节点
     * @param parentNode
     * @return
     */
    public static List<TreeVo> getChildNodes(TreeVo parentNode) {
        List<TreeVo> childNodes = new ArrayList<TreeVo>();
        for (TreeVo n : nodes){
            if (parentNode.getId().equals(n.getPid())) {
                childNodes.add(n);
            }
        }
        return childNodes;
    }

    /**
     * 获取所有的根节点
     * @return
     */
    public static List<TreeVo> getRootNodes() {
        List<TreeVo> rootNodes = new ArrayList<TreeVo>();
        for (TreeVo n : nodes){
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
    public static boolean isRootNode(TreeVo node) {
        boolean isRootNode = true;
        for (TreeVo n : nodes){
            if (node.getPid().equals(n.getId())) {
                isRootNode= false;
                break;
            }
        }
        return isRootNode;
    }

    public List<TreeVo> getNodes() {
        return nodes;
    }

    public void setNodes(List<TreeVo> nodes) {
        this.nodes = nodes;
    }
}
