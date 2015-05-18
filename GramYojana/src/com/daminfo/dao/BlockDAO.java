package com.daminfo.dao;

import java.util.List;

import com.daminfo.common.json.Location;
import com.daminfo.model.Block;

public interface BlockDAO {

	List<Location> getAllBlocksByDistrictID(long districtCode);
	Block getBlockInfo(long blockCode);

}
