package com.daminfo.service.common;

public interface GateInfoService {

	Gate getLastUpdateGate(long villageCode);
	GateList getGateInfoList(long villageCode);
}
