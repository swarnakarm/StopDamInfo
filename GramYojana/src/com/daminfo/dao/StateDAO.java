package com.daminfo.dao;

import java.util.List;

import com.daminfo.common.json.Location;
import com.daminfo.model.State;

public interface StateDAO {
	List<Location> getAllState();
	State getStateById(int id);
}
