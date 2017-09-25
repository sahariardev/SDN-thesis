package com.SDN.nodeandedge;

public class Edge {

	
	private String from;
	private String to;
	private int wight;
	private int available_bandwidth;
	
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	@Override
	public String toString() {
		return "Edge [from=" + from + ", to=" + to + ", wight=" + wight + ", available_bandwidth=" + available_bandwidth
				+ "]";
	}
	public Edge(String from, String to, int wight, int available_bandwidth) {
		super();
		this.from = from;
		this.to = to;
		this.wight = wight;
		this.available_bandwidth = available_bandwidth;
	}
	public int getWight() {
		return wight;
	}
	public void setWight(int wight) {
		this.wight = wight;
	}
	public int getVailable_bandwidth() {
		return available_bandwidth;
	}
	public void setVailable_bandwidth(int available_bandwidth) {
		this.available_bandwidth = available_bandwidth;
	}
}
