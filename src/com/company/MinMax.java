package com.company;

import javafx.util.Pair;

public class MinMax {
    private Heuristics heuristics;
    public int numberOfnodes;
    public MinMax() {
        this.heuristics = new Heuristics();
    }

    public Pair<Board, Double> Maximize(Board boardState, TreeNode parent,int Maxdepth){
        if(boardState.getDepth()==Maxdepth)
        {   Pair<Board, Double> node = new Pair<Board, Double>(boardState, (double) this.heuristics.eval(boardState));
            numberOfnodes++;
            return node;
        }
        Board maxChild=null;
        double maxUtility=Double.NEGATIVE_INFINITY;
        double utility;
        //System.out.println("max");
        for(int i=0 ;i<7;i++)
        {

            if(boardState.isColumnFull(i))
                continue;
            //System.out.print(i);
            Board currentChild = new Board(boardState.insert(i,'r'),false);
            currentChild.setParent(boardState);
            TreeNode childTreeNode=new TreeNode();
            currentChild.setDepth(currentChild.getParent().getDepth()+1);
            Pair<Board, Double> currentPair= Minimize(currentChild,childTreeNode,Maxdepth);
            utility=currentPair.getValue();
            childTreeNode.setHeurstic((int)utility);
            childTreeNode.setNodeType("Min");
            parent.getTreeNodes().add(childTreeNode);

            if(utility>maxUtility)
            {
                maxChild=currentChild;
                maxUtility=utility;
                parent.setHeurstic((int)utility);

            }


        }
        //System.out.println();
        //System.out.println(boardState.getState());
        numberOfnodes++;
        Pair<Board, Double> maxPair=new Pair<Board, Double>(maxChild,maxUtility);
        return maxPair;

    }
    public Pair<Board, Double> Minimize(Board boardState, TreeNode parent,int Maxdepth){

        if(boardState.getDepth()==Maxdepth)
        {   Pair<Board, Double> node = new Pair<Board, Double>(boardState,(double)this.heuristics.eval(boardState));
            numberOfnodes++;
            return node;

        }
        Board minChild=null;
        double minUtility=Double.POSITIVE_INFINITY;
        double utility;
        //System.out.println("min");
        for(int i=0 ;i<7;i++)
        {
            if(boardState.isColumnFull(i))
                continue;
            //System.out.print(i);
            Board currentChild = new Board(boardState.insert(i,'y'),true);
            currentChild.setParent(boardState);
            TreeNode childTreeNode=new TreeNode();
            currentChild.setDepth(currentChild.getParent().getDepth()+1);
            Pair<Board, Double> currentPair= Maximize(currentChild,childTreeNode,Maxdepth);
            utility=currentPair.getValue();
            childTreeNode.setHeurstic((int)utility);
            childTreeNode.setNodeType("Max");
            parent.getTreeNodes().add(childTreeNode);

            if(utility<minUtility)
            {
                minChild=currentChild;
                minUtility=utility;
                parent.setHeurstic((int) utility);
            }


        }
        //System.out.println(boardState.getState());
        numberOfnodes++;
        Pair<Board, Double> minPair=new Pair<Board, Double>(minChild,minUtility);
        return minPair;


    }
    public Pair<Board,Integer> Decide(Board board,TreeNode parent,int maxDepth){
        parent.setNodeType("Max");
        Pair<Board, Double> decidedPair=Maximize(board,parent,maxDepth);
        String state=decidedPair.getKey().getState();
        String parentState=decidedPair.getKey().getParent().getState();
        int i;
        for (i=0;i<42;i++)
        {
            if(state.charAt(i)!=parentState.charAt(i))
            {
                break;
            }
        }
        int columnToBePlayed=i/6;
        Pair<Board,Integer> resultedMove=new Pair<Board,Integer>(decidedPair.getKey(),columnToBePlayed);
        return resultedMove;
    }
}
