package com.daminfo.dao;

import java.util.Set;

import com.daminfo.model.GateInfo;

public interface GateDAO {

	GateInfo getLastUpdateGateInfo(long villageCode);
	Set<GateInfo> getGateInfoList(long villageCode);
	
}
