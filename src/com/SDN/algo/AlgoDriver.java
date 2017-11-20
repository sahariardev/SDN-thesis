package com.SDN.algo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.SDN.nodeandedge.Edge;
import com.SDN.nodeandedge.GenerateNetwork;
import com.SDN.nodeandedge.Node;

public class AlgoDriver {

	public static void main(String[] args) {

		GenerateNetwork g = new GenerateNetwork();

		Node source = g.getSource();
		Node dest = g.getDest();
		Node nodes[] = g.getAllnodes();
		Edge edges[] = g.getAlledges();

		SPF spf = new SPF();

		BAR bar = new BAR();
		edges=bar.run(source,nodes,edges);
        
		JSONObject jsarr=new JSONObject();
		JSONArray  jr1=new JSONArray();
		
		Edge edges2[]=new Edge[edges.length];
		
		for(int c=0;c<edges.length;c++)
		{
			//Edge edg=new Edge(edges[c].getFrom(),edges[c].getTo(),edges[c].getWight(),edges[c].getVailable_bandwidth());
			
			Edge edg=edges[c].makeClone();
			
			if(edg==edges[c])
			{
				System.out.println("true");
			}
			edges2[c]=edg;
		}
		
		
		Node[] previousnodes = nodes;
		int numberofpacketloss = 0;
		for (int c = 0; c < 100; c++) {
			previousnodes = nodes;
			try {
                
				
				edges=spf.makeRandom(edges2);
				if(edges==edges2) System.out.println("true");
				
				Thread.sleep(1);
				nodes = spf.run(source, nodes, edges);
				
				
				
				JSONObject obj = new JSONObject();
				obj.put("Packet No", (c+1));
				if (nodes == null) {
					// System.out.println("Packet loss ");
					numberofpacketloss++;
					nodes = previousnodes;
                    obj.put("packet transfer", "false");
                    jr1.add(obj);
					 
					//jsarr.put("packet "+(c+1),obj);
				} else {
					// updating the network information

					
					//JSONArray obj2 = new JSONArray();
					double avghop = 0;
					for (int i = 0; i < nodes.length; i++) {
						nodes[i].setHopcount();
						avghop = avghop + nodes[i].getHopcount();
                         if(nodes[i].getIdname().equals("n25"))
                          {
						      //obj2.add(nodes[i]);
                        	   
                        	 obj.put("Node name : ",nodes[i].getIdname());
                        	 obj.put("Cost from source : ",nodes[i].getValue());
                        	 obj.put("Minimum available flow entries : ",nodes[i].getMin_availableFlowEntries());
                        	 obj.put("Path capacity : ",nodes[i].getPathcapacity());
                        	 obj.put("Hop Count : ",nodes[i].getHopcount());
                        	 obj.put("Remaining flows: ",nodes[i].getCurrentflow());
                        	 obj.put("Maximum flow entries: ",nodes[i].getAvailableFlowEntries());
                        	 
                           }
						// System.out.println(nodes[i]);
					}

					avghop = Math.ceil(avghop / nodes.length);
					//obj.put("Nodes", obj2);
					
					obj.put("Average hop count", avghop);
					obj.put("packet transfer", "true");
					
					//jsarr.put("packet "+(c+1),obj);
					jr1.add(obj);

					Node n = dest;
					while (true) {
						if (n == null) {
							break;
						}

						for (int i = 0; i < nodes.length; i++) {
							if (nodes[i].equals(n)) {

								nodes[i].setCurrentflow(nodes[i].getCurrentflow() - 1);
							}
						}
						n = n.getParent();
					}

				}
				
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			

		}

		/*
		 * for(Node n:nodes) { System.out.println(n); }
		 */
		jsarr.put("packets ",jr1);
		try (FileWriter file = new FileWriter("F:\\output2.json")) {

			file.write(jsarr.toJSONString());
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(numberofpacketloss + " are the number of packet lost");

	}

}
