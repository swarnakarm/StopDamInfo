package com.daminfo.service;

import com.daminfo.common.json.LocationList;

public interface LocationService {

	LocationList getStateList();
	LocationList getDistrictList(long stateCode);
	LocationList getBlockList(long districtCode);
	LocationList getPanchayatList(long blockCode);
	LocationList getVillageList(long panchayatCode);
}
