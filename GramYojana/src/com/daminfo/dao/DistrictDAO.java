package com.daminfo.dao;

import java.util.List;

import com.daminfo.model.District;
import com.daminfo.service.common.Location;

public interface DistrictDAO {
	List<Location> getAllDistrictsByStateID(int stateCode);
	District getDistrictInfo(int districtCode);
}
