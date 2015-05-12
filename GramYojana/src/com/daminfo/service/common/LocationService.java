package com.daminfo.service.common;

public interface LocationService {

	LocationList getStateList();
	LocationList getDistrictList(int stateCode);
	LocationList getBlockList(int districtCode);
	LocationList getPanchayatList(int blockCode);
	LocationList getVillageList(int panchayatCode);
}
