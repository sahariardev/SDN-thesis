package com.SDN.test;

import java.util.Comparator;
import java.util.PriorityQueue;

import com.SDN.nodeandedge.Node;

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
         
		Comparator <Node> c=new Stringcomperator();
		
		
		
		PriorityQueue<Node> q=new PriorityQueue<Node>(3,c);
		Node n=new Node("A",10);
		n.setValue(30);
		Node n2=new Node("B",30);
		n2.setValue(20);
		Node n3=new Node("C",50);
		n3.setValue(10);
		
		
		q.add(n);
		q.add(n2);
		q.add(n3);
        
		
		n3.setValue(200);
		n2.setValue(150);
		n.setValue(100);
		
		
		
		while(!q.isEmpty())
		{
			Node t=q.poll();
			System.out.println(t);
		}
		
		
		
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
