package com.daminfo.common.json;

import com.daminfo.model.Block;
import com.daminfo.model.District;
import com.daminfo.model.Panchayat;
import com.daminfo.model.State;
import com.daminfo.model.Village;

public class Location {

	private String loc_Name;
	private Long loc_code;
	public Location(State state) {
		this.loc_code = state.getStateCode();
		this.loc_Name = state.getStateName();
	}
	
	public Location(District district) {
		this.loc_code = district.getDistrictCode();
		this.loc_Name = district.getDistrictName();
	}
	
	
	public Location(Block block) {
		this.loc_code = block.getBlockCode();
		this.loc_Name = block.getBlockName();
	}
	
	public Location(Panchayat panchayat) {
		this.loc_code = panchayat.getPanchayatCode();
		this.loc_Name = panchayat.getPanchayatName();
	}
	
	public Location(Village village) {
		this.loc_code = village.getVillageCode();
		this.loc_Name = village.getVillageName();
	}
	
	public String getLoc_Name() {
		return loc_Name;
	}
	public void setLoc_Name(String loc_Name) {
		this.loc_Name = loc_Name;
	}
	public Long getLoc_code() {
		return loc_code;
	}
	public void setLoc_code(Long loc_code) {
		this.loc_code = loc_code;
	}
	
	
}
