package com.company;

import com.Tree.JGraphAdapterDemo;import java.util.ArrayList;
import java.util.Iterator;

public class TreeNode {
    private double heurstic;
    private ArrayList<TreeNode> treeNodes=new ArrayList<TreeNode>();
    private String NodeType;

    public String getNodeType() {
        return NodeType;
    }

    public void setNodeType(String nodeType) {
        NodeType = nodeType;
    }

    public double getHeurstic() {
        return heurstic;
    }

    public void setHeurstic(int heurstic) {
        this.heurstic = heurstic;
    }

    public ArrayList<TreeNode> getTreeNodes() {
        return this.treeNodes;
    }

    public void setTreeNodes(ArrayList<TreeNode> treeNodes) {
        this.treeNodes = treeNodes;
    }

    public String toString() {
        StringBuilder buffer = new StringBuilder(50);
        print(buffer, "", "");
        return buffer.toString();
    }

    private void print(StringBuilder buffer, String prefix ,String childrenPrefix) {
        buffer.append(prefix);
        buffer.append(this.heurstic);
        buffer.append(" "+this.NodeType);
        buffer.append('\n');
        for (Iterator<TreeNode> it = this.treeNodes.iterator(); it.hasNext();) {
            TreeNode next = it.next();
            if (it.hasNext()) {
                next.print(buffer, childrenPrefix + "├── " ,childrenPrefix + "│   ");
            } else {
                next.print(buffer, childrenPrefix + "└── " ,childrenPrefix + "    ");
            }
        }
    }
}
