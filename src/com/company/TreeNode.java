package com.company;

import java.util.ArrayList;

public class TreeNode {
    private double heurstic;
    private ArrayList<TreeNode> treeNodes=new ArrayList<TreeNode>();


    public double getHeurstic() {
        return heurstic;
    }

    public void setHeurstic(int heurstic) {
        this.heurstic = heurstic;
    }

    public ArrayList<TreeNode> getTreeNodes() {
        return treeNodes;
    }

    public void setTreeNodes(ArrayList<TreeNode> treeNodes) {
        this.treeNodes = treeNodes;
    }
}
