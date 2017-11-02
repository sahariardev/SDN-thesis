package com.SDN.algo;

import com.SDN.nodeandedge.Edge;
import com.SDN.nodeandedge.GenerateNetwork;
import com.SDN.nodeandedge.Node;

public class AlgoDriver {

	public static void main(String[] args) {
		
		
		
		GenerateNetwork g=new GenerateNetwork();
		 
		Node source=g.getSource();
		Node dest=g.getDest();
		Node nodes[]=g.getAllnodes();
		Edge edges[]=g.getAlledges();
		
		SPF spf=new SPF();
		
		
		BAR bar=new BAR();
		//edges=bar.run(source,nodes,edges);
 
		
	    Node [] previousnodes=nodes;
	    int numberofpacketloss=0;
	    for (int c=0;c<1;c++)
	    {
	    	previousnodes=nodes;
	    	try {
				Thread.sleep(1);
				nodes=spf.run(source,nodes,edges);
		    	if(nodes==null)
		    	{
		    		//System.out.println("Packet loss ");
		    		numberofpacketloss++;
		    		nodes=previousnodes;
		    	}
		    	else
		    	{
		    		//updating the network information
		    	
		    		Node n=dest;
		    		while(true)
		    		{
		    			if(n==null)
		    			{
		    				break;
		    			}
		    			
		    			
		    			for(int i=0;i<nodes.length;i++)
		    			{
		    				if(nodes[i].equals(n))
		    				{
		    					
		    					nodes[i].setCurrentflow(nodes[i].getCurrentflow()-1);
		    				}
		    			}
		    			n=n.getParent();
		    		}
		    		
		    	}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    }
	    
	   /* 
	    for(Node n:nodes)
	    {
	    	System.out.println(n);
	    }*/
		
	    Node n=nodes[nodes.length-1];
	    while(true)
	    {
	    	if(n==null)
	    	{
	    		break;
	    	}
	    	
	    	System.out.print(n.getIdname()+" ");
	    	n=n.getParent();
	    }
	    System.out.println();
	    
	    System.out.println("Hops are "+nodes[nodes.length-1].getHopcount()+"  Cost is "+nodes[nodes.length-1].getValue()+" Path capacity "+nodes[nodes.length-1].getPathcapacity());
		System.out.println(numberofpacketloss+" are the number of packet lost");
		
	    System.out.println(nodes[6]);
		
	

	}

}
