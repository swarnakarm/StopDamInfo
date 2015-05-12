package com.daminfo.service.impl;

import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daminfo.dao.GateDAO;
import com.daminfo.model.GateInfo;
import com.daminfo.service.common.Gate;
import com.daminfo.service.common.GateInfoService;
import com.daminfo.service.common.GateList;

@Service
public class GateInfoServiceImpl implements GateInfoService{

	private GateDAO gateDAO;

	public GateDAO getGateDAO() {
		return gateDAO;
	}

	public void setGateDAO(GateDAO gateDAO) {
		this.gateDAO = gateDAO;
	}

	@Override
	@Transactional
	public Gate getLastUpdateGate(long villageCode) {
		GateInfo gateInfo = gateDAO.getLastUpdateGateInfo(villageCode);
		return new Gate(gateInfo);
	
	}

	@Override
	@Transactional
	public GateList getGateInfoList(long villageCode) {
		Set<GateInfo> gateInfoList = gateDAO.getGateInfoList(villageCode);
		return new GateList(gateInfoList);
	}
	
}
