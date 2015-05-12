package com.daminfo.dao;

import java.util.List;

import com.daminfo.model.State;
import com.daminfo.service.common.Location;

public interface StateDAO {
	List<Location> getAllState();
	State getStateById(int id);
}
