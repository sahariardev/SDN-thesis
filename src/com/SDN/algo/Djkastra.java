package com.SDN.algo;

import java.util.Comparator;
import java.util.PriorityQueue;


import com.SDN.fileread.GenerateNetwork;
import com.SDN.nodeandedge.Edge;
import com.SDN.nodeandedge.Node;
import com.SDN.nodeandedge.NodeNotFound;

public class Djkastra {
	 public PriorityQueue<Node> q;
	 public Node nodes[];
	 public Edge edges[];

	 public Node[] init (Node allnodes[], Node source)
	 {
		 
		 for(int c=0;c<allnodes.length;c++)
		 {
			 allnodes[c].setTotalValue(100000);
			 allnodes[c].setParent(null);
			 if(allnodes[c].equals(source))
			 {
				 allnodes[c].setTotalValue(0);
			 }
			 
		 }
		 
		 
		 
		 return allnodes;
		 
	 }
	 
	 public void relax(Edge  w)
	 {
		 int u=0;
		 int v=0;
		 
		 for(int c=0;c<nodes.length;c++)
		 {
			 if(nodes[c].getIdname().equals(w.getFrom()))
			 {
				 u=c;
			 }
			 if(nodes[c].getIdname().equals(w.getTo()))
			 {
				 u=c;
			 }
		 }
		 
		 if(nodes[v].getValue()>nodes[u].getValue()+w.getWight())
		 {
			 nodes[v].setValue(nodes[u].getValue()+w.getWight());
			 nodes[v].setParent(nodes[u]);
		 }
		 
		 
		 
	 }
	 
	 public void run()
	 {
		 Djkastra djkastra=new Djkastra();
		 
		 GenerateNetwork g=new GenerateNetwork();
		 Node source=g.getSource();
		 nodes=djkastra.init(g.getAllnodes(),source);
		 edges=g.getAlledges();
		 
		 
		 //initializing priority queue 
		 Comparator <Node> c=new Stringcomperator();
		 q=new PriorityQueue<Node>(nodes.length,c);
		 
		 updateQueue();
		 
		 while(!q.isEmpty())
		 {
			 Node u=q.poll();
			 u.setColor("Gray");
			 for(int i=0;i<edges.length;i++)
			 {
				 if(edges[i].getFrom().equals(u.getIdname()))
				 {
					try {
						Node v= findNodeByName(edges[i].getTo());
						
						v.setColor("blue");
						Edge w=getTheEdge(u,v);
						
						
						
						
					} catch (NodeNotFound e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 }
			 }
			 
		 }
		 
		
		 
	    
		  
		 
		 
	 }
	 public void updateQueue()
	 {
		 q.clear();
		 for(int c=0;c<nodes.length;c++)
		 {
			 if(nodes[c].getColor().equals("Gray"))
			 {
				 continue;
			 }
			 q.add(nodes[c]);
		 }
	 }
	 public Node findNodeByName(String s) throws NodeNotFound
	 {
		 for(int c=0;c<nodes.length;c++)
		 {
			 if(nodes[c].getIdname().equals(s))
			 {
				 return nodes[c]; 
			 }
		 }
		 throw new NodeNotFound();
	 }
	 public Edge getTheEdge(Node u,Node v) throws NodeNotFound
	 {
		 
			 for(int c=0;c<edges.length;c++)
			 {
				 if(edges[c].getFrom().equals(u.getIdname()) && edges[c].getTo().equals(v.getIdname()))
				 {
					 return edges[c];
				 }
			 }
			 
			 throw new NodeNotFound();
		 
	 }
	 
}
class Stringcomperator implements Comparator<Node>
{

	@Override
	public int compare(Node x, Node  y) {
		// Assume neither string is null. Real code should
        // probably be more robust
        // You could also just return x.length() - y.length(),
        // which would be more efficient.
        if (x.getValue()< y.getValue())
        {
        	System.out.println(x);
        	System.out.println(y);
        	System.out.println("here 1");
        	return -1;
        }
        if (x.getValue() > y.getValue())
        {
        	System.out.println("here 2");
            return 1;
        }
        System.out.println("here 3");
        return 0;
	}
}
