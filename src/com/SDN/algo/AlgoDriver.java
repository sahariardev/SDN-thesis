package com.SDN.algo;

import java.io.FileWriter;
import java.io.IOException;

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

		//BAR bar = new BAR();
		//edges=bar.run(source,nodes,edges);
        
		JSONArray jsarr=new JSONArray();
		
		Node[] previousnodes = nodes;
		int numberofpacketloss = 0;
		for (int c = 0; c < 1; c++) {
			previousnodes = nodes;
			try {
				Thread.sleep(1);
				nodes = spf.run(source, nodes, edges);
				
				
				
				JSONObject obj = new JSONObject();
				if (nodes == null) {
					// System.out.println("Packet loss ");
					numberofpacketloss++;
					nodes = previousnodes;
                     obj.put("packet transfer", "false");
					
					jsarr.add(obj);
				} else {
					// updating the network information

					
					JSONArray obj2 = new JSONArray();
					double avghop = 0;
					for (int i = 0; i < nodes.length; i++) {
						nodes[i].setHopcount();
						avghop = avghop + nodes[i].getHopcount();
						obj2.add(nodes[i]);
						// System.out.println(nodes[i]);
					}

					avghop = Math.ceil(avghop / nodes.length);
					obj.put("Nodes", obj2);
					obj.put("Source", source);
					obj.put("Average hop count", avghop);
					obj.put("packet transfer", "true");
					
					jsarr.add(obj);
					

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
		
		try (FileWriter file = new FileWriter("f:\\output.json")) {

			file.write(jsarr.toJSONString());
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(numberofpacketloss + " are the number of packet lost");

	}

}
