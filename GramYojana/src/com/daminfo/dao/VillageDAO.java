package com.daminfo.dao;

import java.util.List;

import com.daminfo.model.Village;
import com.daminfo.service.common.Location;

public interface VillageDAO {
	List<Location> getAllVillagesByPanchayatID(int panchayatCode);
	Village getVillageInfo(int villageCode);
}
