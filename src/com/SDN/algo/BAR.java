package com.SDN.algo;

import java.util.Comparator;
import java.util.PriorityQueue;

import com.SDN.nodeandedge.Edge;
import com.SDN.nodeandedge.GenerateNetwork;
import com.SDN.nodeandedge.Node;
import com.SDN.nodeandedge.NodeNotFound;

public class BAR {
	
	public Node nodes[];
	public int infinte=10000;
	public PriorityQueue <Node> q;
	public Edge [] edges;
	// edges after filtered out 
	public Edge [] newEdges;
	
	

	
	public void filteredges(Edge w)
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
				 v=c;
			 }
		 }
		
		 if(nodes[v].getPathcapacity()<(nodes[u].getPathcapacity()>w.getVailable_bandwidth() ? w.getVailable_bandwidth() : nodes[u].getPathcapacity()))
		 {
			nodes[v].setPathcapacity(nodes[u].getPathcapacity()>w.getVailable_bandwidth() ? w.getVailable_bandwidth() : nodes[u].getPathcapacity()); 
		 }
		 
		 
		
		
	}
	
	
	public Edge[] run(Node s,Node graphallnodes[],Edge graphalledges[])
	{
		
		  Node source=s;
		  System.out.println("Source is "+source);
		 nodes=init(graphallnodes,source);
		 edges=graphalledges;

		 
		 Comparator <Node> c=new  PathcapacityComperator(); 		
		 q=new PriorityQueue<Node>(nodes.length,c);
		 updateQueue();
		 
		 while(!q.isEmpty())
		 {
			 Node u=q.poll();
			 u.setColor("Gray");
			 
			 for(int i=0;i<edges.length;i++)
			 {
				 try {
					if(edges[i].getFrom().equals(u.getIdname()) && !findNodeByName(edges[i].getTo()).getColor().equals("Gray"))
					 {
						 
						Node v=findNodeByName(edges[i].getTo());
						
						//System.out.println(i+"Edge is "+v.getIdname()+" Edge weight is "+edges[i].getWight());
						v.setColor("blue");
						filteredges(edges[i]);
						updateQueue();
						
						
					 }
				} catch (NodeNotFound e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
			 
		 }
		 
		 int min=source.getPathcapacity();
		 for(int i=0;i<nodes.length;i++)
			{
			 if(nodes[i].getPathcapacity()<min)
			 {
				 min=nodes[i].getPathcapacity();
			 }
				//System.out.println(nodes[i]);
			}
		 
		 System.out.println(min);
		 
		 int i2=0;
		 for(Edge e: edges)
		 {
			 if(e.getVailable_bandwidth()>=min)
			 {
				 i2++;
			 }
		 }
		 
		 newEdges=new Edge[i2];
		 
		 i2=0;
		 for(Edge e: edges)
		 {
			 if(e.getVailable_bandwidth()>=min)
			 {
				 newEdges[i2]=e;
				 i2++;
			 }
		 }
		 
		 for(Edge e: edges)
		 {
			 System.out.println(e);
		 }
		 System.out.println("-----------------------------------------------");
		 System.out.println("-----------------------------------------------");
		 System.out.println("-----------------------------------------------");
		 for(Edge e: newEdges)
		 {
			 System.out.println(e);
		 }
		 
		 
		 
		 
		  return newEdges;
		 
     }
	
	 public void updateQueue()
	 {
		 q.clear();
		 for(int c=0;c<nodes.length;c++)
		 {
			 if(nodes[c].getColor().equals("Gray"))
			 {
				
				 
			 }
			 else
			 {
				 q.add(nodes[c]);	 
			 }
			 
		 }
		 
	 }
	 
	 
	public Node[] init(Node n[], Node s)
	{
		for(Node nde :n )
		{
			nde.setPathcapacity(0);
			if(nde.getIdname().equals(s.getIdname()))
			{
				nde.setPathcapacity(infinte);
			}
		}
		return n;
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
	
	

}



class PathcapacityComperator implements Comparator<Node>
{

	@Override
	public int compare(Node x, Node y) {
		
		
		if(x.getPathcapacity()>y.getPathcapacity())
		{
			return -1;
		}
		else if(x.getPathcapacity()<y.getPathcapacity())
		{
			return 1;
		}
		return 0;
		
	}
}
