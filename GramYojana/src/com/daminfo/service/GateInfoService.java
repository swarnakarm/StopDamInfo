package com.daminfo.service;

import java.util.Date;

import com.daminfo.common.json.Gate;
import com.daminfo.common.json.GateList;
import com.daminfo.model.Village;

public interface GateInfoService {

	Gate getLastUpdateGate(long villageCode);
	GateList getGateInfoList(long villageCode);
	void insertNewGateEntry(long villageCode,Date openData);
	void insertCloseDate(long villageCode, Date closeDate);
}
