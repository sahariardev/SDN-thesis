package com.SDN.algo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.PriorityQueue;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.SDN.nodeandedge.Edge;
import com.SDN.nodeandedge.Node;
import com.SDN.nodeandedge.NodeNotFound;
import com.SDN.nodeandedge.ResetFlow;

public class SPF {
	 //testing comment
	 public PriorityQueue<Node> q;
	 public Node nodes[];
	 public Edge edges[];
	 
	

	 public Node[] init (Node allnodes[], Node source)
	 {
		 
		 for(int c=0;c<allnodes.length;c++)
		 {
			 allnodes[c].setValue(100000);
			 allnodes[c].setPathcapacity(0);
			 allnodes[c].setMin_availableFlowEntries(0);
			 allnodes[c].setParent(null);
			 allnodes[c].setColor("white");
			 if(allnodes[c].equals(source))
			 {
				 
				 allnodes[c].setValue(0);
				 allnodes[c].setPathcapacity(100000);
				 allnodes[c].setMin_availableFlowEntries(100000);
				 
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
			 nodes[v].setMin_availableFlowEntries((nodes[v].getAvailableFlowEntries()<nodes[u].getMin_availableFlowEntries()) ? nodes[v].getAvailableFlowEntries() : nodes[u].getMin_availableFlowEntries() );
			 nodes[v].setParent(nodes[u]);
		 }
		 else if((nodes[v].getValue()==nodes[u].getValue()+w.getWight()) && (nodes[v].getPathcapacity()<((nodes[u].getPathcapacity()>w.getVailable_bandwidth()) ? w.getVailable_bandwidth() : nodes[u].getPathcapacity())))
		 {
			 nodes[v].setPathcapacity((nodes[u].getPathcapacity()>w.getVailable_bandwidth()) ? w.getVailable_bandwidth() : nodes[u].getPathcapacity());
			 nodes[v].setMin_availableFlowEntries((nodes[v].getAvailableFlowEntries()<nodes[u].getMin_availableFlowEntries()) ? nodes[v].getAvailableFlowEntries() : nodes[u].getMin_availableFlowEntries() );
		 }
		 else if((nodes[v].getValue()==nodes[u].getValue()+w.getWight()) && (nodes[v].getPathcapacity()==((nodes[u].getPathcapacity()>w.getVailable_bandwidth()) ? w.getVailable_bandwidth() : nodes[u].getPathcapacity())) && (nodes[u].getMin_availableFlowEntries()<((nodes[v].getAvailableFlowEntries()<nodes[u].getMin_availableFlowEntries()) ? nodes[v].getAvailableFlowEntries() : nodes[u].getMin_availableFlowEntries())))
		 {
			 
			 nodes[v].setPathcapacity((nodes[u].getPathcapacity()>w.getVailable_bandwidth()) ? w.getVailable_bandwidth() : nodes[u].getPathcapacity());
			 nodes[v].setMin_availableFlowEntries((nodes[v].getAvailableFlowEntries()<nodes[u].getMin_availableFlowEntries()) ? nodes[v].getAvailableFlowEntries() : nodes[u].getMin_availableFlowEntries() );
		 }
			 
		 
		// System.out.println(nodes[v]+"---------");
		 
		 
	 }
	 
	 public Node[] run(Node s,Node graphallnodes[],Edge graphalledges[])
	 {
		
		 
		 
		 
		 Node source=s;
		 //System.out.println("Source is "+source);
		 
		 nodes=init(graphallnodes,source);
		 edges=graphalledges;
		 
		 
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
			 
			 u.setColor("Gray");
			 
			 //System.out.println("-----------All vertexes adjecent to "+u.getIdname()+"----------"+u.getColor());
			 for(int i=0;i<edges.length;i++)
			 {
				 try {
					if(edges[i].getFrom().equals(u.getIdname()) && !findNodeByName(edges[i].getTo()).getColor().equals("Gray"))
					 {
						try {
							//System.out.println("-----------Here ----------");
							Node v= findNodeByName(edges[i].getTo());
							if(v.getCurrentflow()<=0)////////////////
							{
								
								
								return null;
							}
							else if(v.getCurrentflow()==1)
							{
								for(int c3=0;c3<nodes.length;c3++)
								{
									if(nodes[c3].equals(v))
									{
										ResetFlow r=new ResetFlow(nodes[c3]);
										nodes[c3]=r.clearBuffer();
										break;
									}
								}
							}
							//System.out.println(i+"Edge is "+v.getIdname()+" Edge weight is "+edges[i].getWight());
							
							
							v.setColor("blue");
							
							relax(edges[i]);
							updateQueue();
							//System.out.println("---------------------");
							
							
							
							
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
			// System.out.println(" q size is "+q.size());
			 //System.out.println("---------------------");
			 
			
			 
		 }
		 
		
		 
		 
		
		
		
		
		//System.out.println(obj); 
		  
		 return nodes;
		 
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
		 System.out.println("here the unfound node name is  "+s);
		 throw new NodeNotFound();
	 }
	 public Edge getTheEdge(Node u,Node v) throws NodeNotFound
	 {
		 System.out.println("here 2");
		 
			 for(int c=0;c<edges.length;c++)
			 {
				 if(edges[c].getFrom().equals(u.getIdname()) && edges[c].getTo().equals(v.getIdname()) && !findNodeByName(edges[c].getTo()).getColor().equals("Gray") )
				 {
					 //System.out.println(findNodeByName(edges[c].getTo()).getColor());
					 return edges[c];
				 }
			 }
			 
			 throw new NodeNotFound();
		 
	 }
        public int ran()
       {
           return 0 + (int)(Math.random()*((3-0)+1));
       }
       public Edge[] makeRandom(Edge edges2[])
       {
    	   Edge edges[]=new Edge[edges2.length];
    	   for(int c=0;c<edges.length;c++)
   		{
   			Edge edg=new Edge(edges2[c].getFrom(),edges2[c].getTo(),edges2[c].getWight(),edges2[c].getVailable_bandwidth());
   			
   			//Edge edg=edges2[c].makeClone();
   			
   			if(edg==edges2[c])
   			{
   				System.out.println("true");
   			}
   			edges[c]=edg;
   		}
   		
           
           for(int c=0;c<edges.length;c++)
           {
               //System.out.println("old value "+edges[c].getVailable_bandwidth()+"   "+edges[c].getWight());
               edges[c].setVailable_bandwidth(new Integer(edges[c].getVailable_bandwidth()+ran()));
               edges[c].setWight(new Integer(edges[c].getWight()+ran()));
               //System.out.println("new value "+edges[c].getVailable_bandwidth()+"   "+edges[c].getWight());
           }
           return edges;
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
       	//System.out.println(x);
       	//System.out.println(y);
       	//System.out.println("here 1");
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
