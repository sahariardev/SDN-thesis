package com.SDN.fileread;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.SDN.nodeandedge.Edge;
import com.SDN.nodeandedge.Node;

public class GenerateNetwork {
	
	
	private Node allnodes[];
	private Edge alledges[];
	private Node source;
	private Node dest;
	
	
	public Node getDest() {
		return dest;
	}

	public void setDest(Node dest) {
		this.dest = dest;
	}

	public Node getSource() {
		return source;
	}

	public void setSource(Node source) {
		this.source = source;
	}

	public Node[] getAllnodes() {
		return allnodes;
	}

	public void setAllnodes(Node[] allnodes) {
		this.allnodes = allnodes;
	}

	public Edge[] getAlledges() {
		return alledges;
	}

	public void setAlledges(Edge[] alledges) {
		this.alledges = alledges;
	}

	public static void main(String [] args)
	{
		GenerateNetwork g=new GenerateNetwork();
		g.genereate();
	}
	
	public GenerateNetwork() {
		
		GenerateNetwork g=new GenerateNetwork();
		g.genereate();
	}

	public void genereate()
	{
		File f=new File("input.txt");
		
		int numberofnodes=0;
		int numberofedges=0;
		String nodesLine="";
		
		try {
			Scanner sc=new Scanner(f);
			
			if(sc.hasNextLine())
			{
				numberofnodes=sc.nextInt();
				System.out.println(numberofnodes);
				sc.nextLine();
			}
			
			
			if(sc.hasNextLine())
			{
				nodesLine=sc.nextLine();
				System.out.println(nodesLine);
			}
			
			if(sc.hasNextLine())
			{
				numberofedges=sc.nextInt();
				System.out.println(numberofedges);
				sc.nextLine();
			}
			
			
			Edge edges[]=new Edge[numberofedges];
			
			//here c is used for while loop iteration
			int c=0;
			while(sc.hasNextLine())
			{
				String edgeDetail[]=sc.nextLine().split(" ");
				
				
				//spliting using space and setting the properties of edge useing constructor 
				edges[c]=new Edge(edgeDetail[0],edgeDetail[1],Integer.parseInt(edgeDetail[3]),Integer.parseInt(edgeDetail[3]));
				
				c++;
			}
			
			Node nodes[]=new Node[numberofnodes] ;
			
			
			String singleNodeWithFlowEntries[]=nodesLine.split(" ");
			
			for(int i=0;i<nodes.length;i++)
			{
				//System.out.println(singleNodeWithFlowEntries[2]);
				String nodeAndFlowentries[]=singleNodeWithFlowEntries[i].split("@@");
				//System.out.println(nodeAndFlowentries[0]);
				nodes[i]=new Node( nodeAndFlowentries[0] , Integer.parseInt(nodeAndFlowentries[1]) );
			}
			
			
			for(int i=0;i<nodes.length;i++)
			{
				System.out.println(nodes[i]);
			}
			System.out.println("--------------------------------");
			System.out.println("--------------------------------");
			System.out.println("--------------------------------");
			System.out.println("--------------------------------");
			
			for(int i=0;i<edges.length;i++)
			{
				System.out.println(edges[i]);
			}
			allnodes=nodes;
			alledges=edges;
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
