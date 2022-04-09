package com.company;

import com.company.Board;
import com.company.Heuristics;
import javafx.util.Pair;

public class MinMaxAlphaBeta {
    private Heuristics heuristics;
    public int numberOfnodes;
    public MinMaxAlphaBeta() {
        this.heuristics = new Heuristics();
    }

    public Pair<Board, Double> Maximize(Board boardState,TreeNode parent,double alpha,double beta, int Maxdepth){
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
            Pair<Board, Double> currentPair= Minimize(currentChild,childTreeNode,alpha,beta,Maxdepth);
            utility=currentPair.getValue();
            childTreeNode.setHeurstic((int)utility);
            parent.getTreeNodes().add(childTreeNode);
            if(utility>maxUtility)
            {
                maxChild=currentChild;
                maxUtility=utility;
                parent.setHeurstic((int)utility);

            }

            if (maxUtility>=beta)
            {
                break;
            }
            if (maxUtility>alpha)
            {
                alpha=maxUtility;
            }
        }
        //System.out.println();
        //System.out.println(boardState.getState());
        numberOfnodes++;
        Pair<Board, Double> maxPair=new Pair<Board, Double>(maxChild,maxUtility);
        return maxPair;

    }
    public Pair<Board, Double> Minimize(Board boardState, TreeNode parent,double alpha,double beta,int Maxdepth){

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
            Pair<Board, Double> currentPair= Maximize(currentChild,childTreeNode,alpha,beta,Maxdepth);
            utility=currentPair.getValue();
            childTreeNode.setHeurstic((int)utility);
            parent.getTreeNodes().add(childTreeNode);
            if(utility<minUtility)
            {
                minChild=currentChild;
                minUtility=utility;
                parent.setHeurstic((int) utility);
            }
            if (minUtility<=alpha)
            {
                break;
            }
            if (minUtility<beta)
            {
                beta=minUtility;
            }

        }
        //System.out.println(boardState.getState());
        numberOfnodes++;
        Pair<Board, Double> minPair=new Pair<Board, Double>(minChild,minUtility);
        return minPair;


    }
    public Pair<Board,Integer> Decide(Board board,TreeNode parent ,int maxDepth){

        Pair<Board, Double> decidedPair=Maximize(board,parent,Double.NEGATIVE_INFINITY,Double.POSITIVE_INFINITY,maxDepth);
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
