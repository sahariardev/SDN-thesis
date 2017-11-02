package com.SDN.nodeandedge;

public class ResetFlow extends Thread {

	public Node n;
	
	public ResetFlow(Node n)
	{
		this.n=n;
	}
	
	public void run()
	{
		try
		{
			Thread.sleep(n.getCurrentflow()*2);

			n.setCurrentflow(n.getAvailableFlowEntries());
	
		}
		catch (Exception e)
		{
			System.out.println("Exception in Resetflow");
		}
	}
	
	public Node clearBuffer()
	{
		start();
		return n;
	}
	

}
