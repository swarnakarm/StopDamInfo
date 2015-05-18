package com.daminfo.dao;

import java.util.List;

import com.daminfo.common.json.Location;
import com.daminfo.model.District;

public interface DistrictDAO {
	List<Location> getAllDistrictsByStateID(long stateCode);
	District getDistrictInfo(long districtCode);
}
