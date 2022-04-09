package com.Tree;



import java.awt.*;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;

import com.company.TreeNode;
import org.jgraph.JGraph;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;

import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.ListenableDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.ListenableUndirectedGraph;

import static java.lang.Math.abs;

/**
 * A demo applet that shows how to use JGraph to visualize JGraphT graphs.
 *
 * @author Barak Naveh
 *
 * @since Aug 3, 2003
 */
public class JGraphAdapterDemo<nodeID> extends JApplet {
    private static final Color     DEFAULT_BG_COLOR = Color.decode( "#FAFBFF" );
    private static final Dimension DEFAULT_SIZE = new Dimension( 530, 320 );

    //


    /**
     * @see java.applet.Applet#init().
     */
    // create a JGraphT graph
    ListenableGraph g = new ListenableUndirectedGraph(DefaultEdge.class );

    // create a visualization using JGraph, via an adapter
    private JGraphModelAdapter m_jgAdapter=new JGraphModelAdapter( g );

    JGraph jgraph = new JGraph( m_jgAdapter );
    private static Integer nodeID=0;
    public int numberOfNodes=0;
    public void draw(TreeNode treeNode, String nodesId, boolean turn, int xPos, int yPos) {




        // add some sample data (graph manipulated via JGraphT)
//        g.addVertex( 22.0 );
//        g.addVertex( 23.0 );
//        g.addVertex( 24.0 );
//        g.addVertex( 28.0 );
//
//        g.addEdge( 22.0, 23.0 );
//        g.addEdge( 23.0, 24.0 );
//        g.addEdge( 24.0, 22.0 );
//        g.addEdge( 28.0, 24.0 );
//
//        // position vertices nicely within JGraph component
//        positionVertexAt( 22.0, 130, 40 );
//        positionVertexAt( 23.0, 60, 200 );
//        positionVertexAt( 24.0, 310, 230 );
//        positionVertexAt( 28.0, 380, 70 );
        String parent;
        if(nodesId == null)
        {
            Double parentHeurstic=treeNode.getHeurstic();
            parent=parentHeurstic.toString()+"-"+nodeID.toString();
            g.addVertex(parent);
            numberOfNodes++;
            this.positionVertexAt(turn,parent,(int)xPos,(int)yPos);
            turn=!turn;
        }
        else {
             parent = nodesId;
        }
        DefaultGraphCell cell = m_jgAdapter.getVertexCell( parent );
        nodeID++;
        Map              attr = cell.getAttributes(  );
        Rectangle2D b    = GraphConstants.getBounds( attr );
        double x=xPos;
        double y=yPos+120;
        y=y+60;

        ArrayList <String> nodeIds= new ArrayList<String>() ;
        for(TreeNode node:treeNode.getTreeNodes()) {
            Double childHeurstic=node.getHeurstic();
            String child=childHeurstic.toString()+"-"+nodeID.toString();
            nodeIds.add(child);
            g.addVertex( child);
            numberOfNodes++;
            g.addEdge(parent,child);

            x=x+80;

            this.positionVertexAt(turn,child,(int)x,(int)y);
            nodeID++;
        }
        turn=!turn;
        int i =0;
        yPos+=250;
        xPos+=400;
        for(TreeNode node:treeNode.getTreeNodes()) {
            draw(node,nodeIds.get(i),turn,(int)((xPos+300)*i),yPos+100*(i%4+1));
        i++;
        }


    }


    private void adjustDisplaySettings( JGraph jg ) {
        jg.setPreferredSize( DEFAULT_SIZE );

        Color  c        = DEFAULT_BG_COLOR;
        String colorStr = null;

        try {
            colorStr = getParameter( "bgcolor" );
        }
        catch( Exception e ) {}

        if( colorStr != null ) {
            c = Color.decode( colorStr );
        }

        jg.setBackground( c );
    }


    private void positionVertexAt( boolean turn,Object vertex, int x, int y ) {
        DefaultGraphCell cell = m_jgAdapter.getVertexCell( vertex );
        Map              attr = cell.getAttributes(  );
        Rectangle2D b    = GraphConstants.getBounds( attr );
        Color colorAi= Color.RED;
        Color colorPerson = Color.ORANGE;
        if(turn)
            GraphConstants.setBackground(attr,colorAi);
        else
            GraphConstants.setBackground(attr,colorPerson);
        GraphConstants.setBounds( attr, new Rectangle( x, y, (int)b.getWidth(), (int)b.getHeight() ) );
        Map cellAttr = new HashMap(  );
        cellAttr.put( cell, attr );
        m_jgAdapter.edit(cellAttr,null,null,null);
    }
    public void showPane()
    {
        //resize( 20000,20000 );


//        adjustDisplaySettings( jgraph );
//        getContentPane(  ).add( jgraph );
//        JFrame frame = new JFrame();
//        JScrollPane pane=new JScrollPane(jgraph);
//        pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//        pane.getHorizontalScrollBar().setEnabled(true);
//        frame.getContentPane().add(pane);
//        frame.pack();
//        frame.setSize(new Dimension(10000,10000));
//        frame.setVisible(true);

        JGraph jgraph = new JGraph( m_jgAdapter );
        JFrame frame = new JFrame();
        JScrollPane pane=new JScrollPane(jgraph);
        pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        frame.getContentPane().add(pane);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(new Dimension(2500,5000));
        frame.setVisible(true);

    }
}