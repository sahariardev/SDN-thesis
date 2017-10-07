package com.SDN.algo;

import com.SDN.nodeandedge.Edge;
import com.SDN.nodeandedge.GenerateNetwork;
import com.SDN.nodeandedge.Node;

public class AlgoDriver {

	public static void main(String[] args) {
		
		
		
		GenerateNetwork g=new GenerateNetwork();
		 
		Node source=g.getSource();
		
		Node nodes[]=g.getAllnodes();
		Edge edges[]=g.getAlledges();
		
		SPF spf=new SPF();
		
		
		BAR bar=new BAR();
		edges=bar.run(source,nodes,edges);

		spf.run(source,nodes,edges);
		
		
		
		
		

	}

}
