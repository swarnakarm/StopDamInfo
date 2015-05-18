package com.daminfo.common.json;

import java.util.Set;

public class ReportList {

	private int openGate;
	private int totalGate;
	
	private Set<Report> reportSet = null;

	public Set<Report> getReportSet() {
		return reportSet;
	}

	public void setReportSet(Set<Report> reportSet) {
		this.reportSet = reportSet;
	}

	public int getOpenGate() {
		return openGate;
	}

	public void setOpenGate(int openGate) {
		this.openGate = openGate;
	}

	public int getTotalGate() {
		return totalGate;
	}

	public void setTotalGate(int totalGate) {
		this.totalGate = totalGate;
	}
	
}
