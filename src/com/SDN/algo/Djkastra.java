package com.SDN.algo;

import java.util.Comparator;
import java.util.PriorityQueue;

import com.SDN.nodeandedge.Edge;
import com.SDN.nodeandedge.GenerateNetwork;
import com.SDN.nodeandedge.Node;
import com.SDN.nodeandedge.NodeNotFound;

public class Djkastra {
	 public PriorityQueue<Node> q;
	 public Node nodes[];
	 public Edge edges[];
	 
	 public static void main(String [] args)
	 {
		 Djkastra d=new Djkastra();
		 d.run();
		 
	 }

	 public Node[] init (Node allnodes[], Node source)
	 {
		 
		 for(int c=0;c<allnodes.length;c++)
		 {
			 allnodes[c].setValue(100000);
			 allnodes[c].setPathcapacity(100000);
			 allnodes[c].setParent(null);
			 if(allnodes[c].equals(source))
			 {
				 
				 allnodes[c].setValue(0);
				 
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
				 v=c;
			 }
		 }
		 //System.out.println(nodes[u]);
		 if(nodes[v].getValue()>nodes[u].getValue()+w.getWight())
		 {
			 
			 nodes[v].setValue(nodes[u].getValue()+w.getWight());
			 
			 nodes[v].setPathcapacity((nodes[u].getPathcapacity()>w.getVailable_bandwidth()) ? w.getVailable_bandwidth() : nodes[u].getPathcapacity());
			 nodes[v].setAvailableFlowEntries((nodes[v].getAvailableFlowEntries()<nodes[u].getAvailableFlowEntries()) ? nodes[v].getAvailableFlowEntries() : nodes[u].getAvailableFlowEntries() );
			 nodes[v].setParent(nodes[u]);
		 }
		 else if((nodes[v].getValue()==nodes[u].getValue()+w.getWight()) && (nodes[v].getPathcapacity()<((nodes[u].getPathcapacity()>w.getVailable_bandwidth()) ? w.getVailable_bandwidth() : nodes[u].getPathcapacity())))
		 {
			 nodes[v].setPathcapacity((nodes[u].getPathcapacity()>w.getVailable_bandwidth()) ? w.getVailable_bandwidth() : nodes[u].getPathcapacity());
			 nodes[v].setAvailableFlowEntries((nodes[v].getAvailableFlowEntries()<nodes[u].getAvailableFlowEntries()) ? nodes[v].getAvailableFlowEntries() : nodes[u].getAvailableFlowEntries() );
		 }
		 else if((nodes[v].getValue()==nodes[u].getValue()+w.getWight()) && (nodes[v].getPathcapacity()==((nodes[u].getPathcapacity()>w.getVailable_bandwidth()) ? w.getVailable_bandwidth() : nodes[u].getPathcapacity())))
		 {
			 
		 }
			 
		 
		 System.out.println(nodes[v]+"---------");
		 
		 
	 }
	 
	 public void run()
	 {
		
		 
		 GenerateNetwork g=new GenerateNetwork();
		 
		 Node source=g.getSource();
		 System.out.println("Source is "+source);
		 nodes=init(g.getAllnodes(),source);
		 edges=g.getAlledges();
		 
		 
		 //initializing priority queue 
		 Comparator <Node> c=new Stringcomperator();
		 q=new PriorityQueue<Node>(nodes.length,c);
		 
		 updateQueue();
		 int testing=0;
		 while(!q.isEmpty())
		 {
			 testing++;
			 //if(testing==2) break;
				 
			 Node u=q.poll();
			 System.out.println();
			 u.setColor("Gray");
			 
			 System.out.println("-----------All vertexes adjecent to "+u.getIdname()+"----------"+u.getColor());
			 for(int i=0;i<edges.length;i++)
			 {
				 try {
					if(edges[i].getFrom().equals(u.getIdname()) && !findNodeByName(edges[i].getTo()).getColor().equals("Gray"))
					 {
						try {
							System.out.println("---------------------");
							Node v= findNodeByName(edges[i].getTo());
							System.out.println(i+"Edge is "+v.getIdname()+" Edge weight is "+edges[i].getWight());
							
							
							v.setColor("blue");
							
							relax(edges[i]);
							updateQueue();
							System.out.println("---------------------");
							
							
							
							
						} catch (NodeNotFound e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					 }
				} catch (NodeNotFound e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
			 System.out.println(" q size is "+q.size());
			 System.out.println("---------------------");
			 
			
			 
		 }
		 
		
		 
		 for(int i=0;i<nodes.length;i++)
			{
				System.out.println(nodes[i]);
			}
		  
		 
		 
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
				 if(edges[c].getFrom().equals(u.getIdname()) && edges[c].getTo().equals(v.getIdname()) && !findNodeByName(edges[c].getTo()).getColor().equals("Gray") )
				 {
					 System.out.println(findNodeByName(edges[c].getTo()).getColor());
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
        	//System.out.println("here 2");
            return 1;
        }
        
        return 0;
	}
}
