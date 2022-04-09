package com.Tree;

import com.company.TreeNode;
import org.jgraph.JGraph;
import org.jgraph.graph.AttributeMap;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;
import org.jgrapht.Graph;
import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.ListenableDirectedGraph;
import org.jgrapht.graph.SimpleGraph;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;


public class Treetest {

    public void draw(TreeNode treeNode, boolean turn)
    {
        Graph<String, DefaultEdge> g
                = new SimpleGraph<>(DefaultEdge.class);
        // create a visualization using JGraph, via the adapter
        JGraphModelAdapter adapterDemo=new JGraphModelAdapter( g );

        g.addVertex("20.0");
        g.addVertex("21.0");
        g.addVertex("22.0");
        g.addEdge("20.0","21.0");
        this.positionVertexAt(adapterDemo,true,"20.0",100,100);
        JGraph jgraph = new JGraph( adapterDemo );
        JFrame frame = new JFrame();
        frame.getContentPane().add(new JScrollPane(jgraph));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    private void positionVertexAt( JGraphModelAdapter adapterDemo,boolean color,Object vertex, int x, int y ) {
        DefaultGraphCell cell = adapterDemo.getVertexCell( vertex );
        Map attr = cell.getAttributes(  );
        AttributeMap attributeMap= new AttributeMap();
        Color colorAi= Color.RED;
        Color colorPerson = Color.YELLOW;
        if(color)
        GraphConstants.setBackground(attr,colorAi);
        else
            GraphConstants.setBackground(attr,colorPerson);
        Rectangle2D b    = GraphConstants.getBounds( attr );
        Rectangle rect=new Rectangle( x, y, (int)b.getWidth(), (int)b.getHeight() );

        GraphConstants.setBounds( attr, rect );
        Map cellAttr = new HashMap(  );
        cellAttr.put( cell, attr );
        adapterDemo.edit(cellAttr,null,null,null);
    }


}
