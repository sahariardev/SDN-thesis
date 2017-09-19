package com.SDN.algo;

import com.SDN.nodeandedge.Node;

public class Djkastra {

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
	 
	 
}
