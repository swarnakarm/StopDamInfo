package com.daminfo.service.impl;

import java.util.Date;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daminfo.common.json.Gate;
import com.daminfo.common.json.GateList;
import com.daminfo.dao.GateDAO;
import com.daminfo.dao.VillageDAO;
import com.daminfo.model.GateInfo;
import com.daminfo.model.Village;
import com.daminfo.service.GateInfoService;

@Service
public class GateInfoServiceImpl implements GateInfoService{

	private GateDAO gateDAO;
	private VillageDAO villageDAO;

	public void setGateDAO(GateDAO gateDAO) {
		this.gateDAO = gateDAO;
	}

	public void setVillageDAO(VillageDAO villageDAO) {
		this.villageDAO = villageDAO;
	}
	
	@Override
	@Transactional
	public Gate getLastUpdateGate(long villageCode) {
		GateInfo gateInfo = gateDAO.getLastUpdateGateInfo(villageCode);
		Gate gate = null;
		if(gateInfo!=null){
			gate = new Gate(gateInfo);
		}
		return gate;
	
	}

	@Override
	@Transactional
	public GateList getGateInfoList(long villageCode) {
		Set<GateInfo> gateInfoList = gateDAO.getGateInfoList(villageCode);
		return new GateList(gateInfoList);
	}

	@Override
	@Transactional
	public void insertNewGateEntry(long villageCode, Date openDate) {
		Village village = villageDAO.getVillageInfo(villageCode);
		GateInfo gateInfo = new GateInfo();
		gateInfo.setOpenDate(openDate);
		gateInfo.setVillage(village);
		gateDAO.insertNewGateInfoEntry(gateInfo);
	}

	@Override
	@Transactional
	public void insertCloseDate(long villageCode, Date closeDate) {
		GateInfo gateInfo = gateDAO.getLastUpdateGateInfo(villageCode);
		gateInfo.setCloseDate(closeDate);
		gateDAO.insertCloseDate(gateInfo);
	}
	
}
