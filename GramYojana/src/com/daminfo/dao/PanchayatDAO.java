package com.daminfo.dao;

import java.util.List;

import com.daminfo.common.json.Location;
import com.daminfo.model.Panchayat;

public interface PanchayatDAO {

	List<Location> getAllPanchayatsByBlockID(long blockCode);
	Panchayat getPanchayatInfo(long panchayatCode);
	
}
