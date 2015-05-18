package com.daminfo.service.impl;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daminfo.common.json.Location;
import com.daminfo.common.json.Report;
import com.daminfo.common.json.ReportList;
import com.daminfo.dao.BlockDAO;
import com.daminfo.dao.GateDAO;
import com.daminfo.dao.PanchayatDAO;
import com.daminfo.dao.VillageDAO;
import com.daminfo.model.Block;
import com.daminfo.model.GateInfo;
import com.daminfo.model.Panchayat;
import com.daminfo.model.Village;
import com.daminfo.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService{

	private PanchayatDAO panchayatDAO;
	
	private BlockDAO blockDAO;
	
	private VillageDAO villageDAO;
	
	private GateDAO gateDAO;
	
	public void setPanchayatDAO(PanchayatDAO panchayatDAO) {
		this.panchayatDAO = panchayatDAO;
	}

	public void setBlockDAO(BlockDAO blockDAO) {
		this.blockDAO = blockDAO;
	}

	public void setVillageDAO(VillageDAO villageDAO) {
		this.villageDAO = villageDAO;
	}
	
	public void setGateDAO(GateDAO gateDAO) {
		this.gateDAO = gateDAO;
	}

	@Override
	@Transactional
	public ReportList getBlockReport(long blockCode) {
		Set<Report> reportSet = new LinkedHashSet<Report>();
		int totalPanchayatGate = 0;
		int totalOpenGates = 0;
		
		Block block = blockDAO.getBlockInfo(blockCode);
		Set<Panchayat> panchayats = block.getPanchayats();
		for(Panchayat panchayat : panchayats ){
			List<Location> villages = villageDAO.getAllVillagesByPanchayatID(panchayat.getPanchayatCode());
			int openGates = 0;
			for(Location village : villages){
				GateInfo gateInfo = gateDAO.getLastUpdateGateInfo(village.getLoc_code());
				
				if(gateInfo !=null && gateInfo.getCloseDate()==null && gateInfo.getOpenDate()!=null){
					openGates++;
				}
			}
			Report report = new Report();
			report.setLocation(new Location(panchayat));
			report.setNoOfGates(villages.size());
			report.setOpenGates(openGates);
			reportSet.add(report);
			totalPanchayatGate += villages.size();
			totalOpenGates += openGates;
		}
		
		ReportList reportList = new ReportList();
		reportList.setOpenGate(totalOpenGates);
		reportList.setTotalGate(totalPanchayatGate);
		reportList.setReportSet(reportSet);
		return reportList;
	}

	@Override
	@Transactional
	public ReportList getPanchayatReport(long panchayatCode) {
		Set<Report> reportSet = new LinkedHashSet<Report>();
		Panchayat panchayat = panchayatDAO.getPanchayatInfo(panchayatCode);
		Set<Village> villages = panchayat.getVillages();
		int openGates = 0;
		for(Village village : villages ){
			GateInfo gateInfo = gateDAO.getLastUpdateGateInfo(village.getVillageCode());
			boolean flag = false;
			if(gateInfo !=null && gateInfo.getCloseDate()==null && gateInfo.getOpenDate()!=null){
				openGates++;
				flag = true;
			}
			Report report = new Report();
			report.setNoOfGates(1);
			report.setOpenGates(flag?1:0);
			report.setLocation(new Location(village));
			reportSet.add(report);
		}
		ReportList reportList = new ReportList();
		reportList.setOpenGate(openGates);
		reportList.setTotalGate(villages.size());
		reportList.setReportSet(reportSet);
		return reportList;
	}
	
}
