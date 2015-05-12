package com.daminfo.dao;

import java.util.List;

import com.daminfo.model.Panchayat;
import com.daminfo.service.common.Location;

public interface PanchayatDAO {

	List<Location> getAllPanchayatsByBlockID(int blockCode);
	Panchayat getPanchayatInfo(int panchayatCode);
	
}
