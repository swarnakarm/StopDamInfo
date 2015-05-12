package com.daminfo.dao;

import java.util.List;

import com.daminfo.model.Block;
import com.daminfo.service.common.Location;

public interface BlockDAO {

	List<Location> getAllBlocksByDistrictID(int districtCode);
	Block getBlockInfo(int blockCode);

}
