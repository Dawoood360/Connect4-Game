package com.company;

import com.Tree.JGraphAdapterDemo;import java.util.ArrayList;

public class TreeNode {
    private double heurstic;
    private ArrayList<JGraphAdapterDemo.TreeNode> treeNodes=new ArrayList<JGraphAdapterDemo.TreeNode>();


    public double getHeurstic() {
        return heurstic;
    }

    public void setHeurstic(int heurstic) {
        this.heurstic = heurstic;
    }

    public ArrayList<JGraphAdapterDemo.TreeNode> getTreeNodes() {
        return treeNodes;
    }

    public void setTreeNodes(ArrayList<JGraphAdapterDemo.TreeNode> treeNodes) {
        this.treeNodes = treeNodes;
    }
}
