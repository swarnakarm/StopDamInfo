package com.daminfo.dao;

import java.util.List;

import com.daminfo.common.json.Location;
import com.daminfo.model.Village;

public interface VillageDAO {
	List<Location> getAllVillagesByPanchayatID(long panchayatCode);
	Village getVillageInfo(long villageCode);
}
