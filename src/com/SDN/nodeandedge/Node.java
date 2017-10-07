package com.SDN.nodeandedge;

public class Node {
	
	
	 
	private Node parent;
	private Node prev;
	private Node next;
	private String color;
	private  int value;
	private int totalValue;
	private String idname;
	private int availableFlowEntries;
	private int min_availableFlowEntries;
    private int pathcapacity;
	
    
    
	
	
	
	public int getMin_availableFlowEntries() {
		return min_availableFlowEntries;
	}
	public void setMin_availableFlowEntries(int min_availableFlowEntries) {
		this.min_availableFlowEntries = min_availableFlowEntries;
	}
	public int getPathcapacity() {
		return pathcapacity;
	}
	public void setPathcapacity(int pathcapacity) {
		this.pathcapacity = pathcapacity;
	}
	public Node getParent() {
		return parent;
	}
	public void setParent(Node parent) {
		this.parent = parent;
	}
	public Node getPrev() {
		return prev;
	}
	public void setPrev(Node prev) {
		this.prev = prev;
	}
	public Node getNext() {
		return next;
	}
	public void setNext(Node next) {
		this.next = next;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public int getTotalValue() {
		return totalValue;
	}
	public void setTotalValue(int totalValue) {
		this.totalValue = totalValue;
	}
	public String getIdname() {
		return idname;
	}
	public void setIdname(String idname) {
		this.idname = idname;
	}
	public int getAvailableFlowEntries() {
		return availableFlowEntries;
	}
	public void setAvailableFlowEntries(int availableFlowEntries) {
		this.availableFlowEntries = availableFlowEntries;
	}
	
	
	
	
	
	
	
	
	@Override
	public String toString() {
		return "Node [parent=" + parent + ", prev=" + prev + ", next=" + next + ", color=" + color + ", Cost=" + value
				+ ", idname=" + idname
				+ ", min_availableFlowEntries=" + min_availableFlowEntries + ", pathcapacity=" + pathcapacity + "]";
	}
	public Node(String idname, int availableFlowEntries) {
		
		
		this.color="white";
		this.idname = idname;
		this.availableFlowEntries = availableFlowEntries;
	}
	
	

	
	
	
	

}
