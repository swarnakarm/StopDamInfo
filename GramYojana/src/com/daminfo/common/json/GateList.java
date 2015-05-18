package com.daminfo.common.json;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.daminfo.model.GateInfo;

public class GateList {

	List<Gate> gates = null;

	public List<Gate> getGates() {
		return gates;
	}

	public void setGates(List<Gate> gates) {
		this.gates = gates;
	}
	
	public GateList(Set<GateInfo> gateInfos){
		this.gates = new ArrayList<Gate>();
		for(GateInfo gateInfo:gateInfos){
			this.gates.add(new Gate(gateInfo));
		}
	}
	
}
