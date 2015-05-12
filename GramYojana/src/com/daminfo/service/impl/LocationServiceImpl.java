package com.daminfo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daminfo.dao.BlockDAO;
import com.daminfo.dao.DistrictDAO;
import com.daminfo.dao.PanchayatDAO;
import com.daminfo.dao.StateDAO;
import com.daminfo.dao.VillageDAO;
import com.daminfo.service.common.LocationList;
import com.daminfo.service.common.LocationService;

@Service
public class LocationServiceImpl implements LocationService{

	private StateDAO stateDAO;
	private DistrictDAO districtDAO;
	private BlockDAO blockDAO;
	private PanchayatDAO panchayatDAO;
	private VillageDAO villageDAO;
	
	public void setStateDAO(StateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}

	public void setDistrictDAO(DistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}

	public void setBlockDAO(BlockDAO blockDAO) {
		this.blockDAO = blockDAO;
	}

	public void setPanchayatDAO(PanchayatDAO panchayatDAO) {
		this.panchayatDAO = panchayatDAO;
	}

	public void setVillageDAO(VillageDAO villageDAO) {
		this.villageDAO = villageDAO;
	}

	@Override
	@Transactional
	public LocationList getStateList() {
		LocationList locList = new LocationList();
		locList.setLocations(this.stateDAO.getAllState());
		return locList;
	}

	@Override
	@Transactional
	public LocationList getDistrictList(int stateCode) {
		LocationList locList = new LocationList();
		locList.setLocations(this.districtDAO.getAllDistrictsByStateID(stateCode));
		return locList;
	}

	@Override
	@Transactional
	public LocationList getBlockList(int districtCode) {
		LocationList locList = new LocationList();
		locList.setLocations(this.blockDAO.getAllBlocksByDistrictID(districtCode));
		return locList;
	}

	@Override
	@Transactional
	public LocationList getPanchayatList(int blockCode) {
		LocationList locList = new LocationList();
		locList.setLocations(this.panchayatDAO.getAllPanchayatsByBlockID(blockCode));
		return locList;
	}

	@Override
	@Transactional
	public LocationList getVillageList(int panchayatCode) {
		LocationList locList = new LocationList();
		locList.setLocations(this.villageDAO.getAllVillagesByPanchayatID(panchayatCode));
		return locList;
	}

}
