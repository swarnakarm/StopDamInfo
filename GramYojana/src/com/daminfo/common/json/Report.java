package com.daminfo.common.json;

public class Report {

	private Location location;
	private int noOfGates;
	private int openGates;
	
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public int getNoOfGates() {
		return noOfGates;
	}
	public void setNoOfGates(int noOfGates) {
		this.noOfGates = noOfGates;
	}
	public int getOpenGates() {
		return openGates;
	}
	public void setOpenGates(int openGates) {
		this.openGates = openGates;
	}
}
