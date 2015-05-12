package com.daminfo.service.common;

import java.util.Date;

import com.daminfo.model.GateInfo;

public class Gate {

	private Date openDate;
	private Date closeDate;
	public Date getOpenDate() {
		return openDate;
	}
	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}
	public Date getCloseDate() {
		return closeDate;
	}
	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}
	
	public Gate(GateInfo gateInfo){
		this.openDate = gateInfo.getOpenDate();
		this.closeDate = gateInfo.getCloseDate();
	}
	
}
