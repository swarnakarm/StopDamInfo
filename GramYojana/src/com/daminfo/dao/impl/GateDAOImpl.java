package com.daminfo.dao.impl;

import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.daminfo.common.util.AppLogger;
import com.daminfo.dao.GateDAO;
import com.daminfo.model.GateInfo;
import com.daminfo.model.Village;

@Repository
public class GateDAOImpl implements GateDAO{

	private SessionFactory sessionFactory;
    
    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }
	
	@Override
	public GateInfo getLastUpdateGateInfo(long villageCode) {
		GateInfo lastGateInfo = null;
		Session session = this.sessionFactory.getCurrentSession();
		
		String hql = "FROM GateInfo gInfo where village.villageCode = :villageCode ORDER BY gInfo.openDate ASC";
		Query query = session.createQuery(hql);
		query.setParameter("villageCode",villageCode);
		List<GateInfo> gateInfoList = (List<GateInfo>) query.list();
		
		for(GateInfo gateInfo : gateInfoList){
			lastGateInfo = gateInfo;
		}
		return lastGateInfo;
	}

	@Override
	public Set<GateInfo> getGateInfoList(long villageCode) {
		Session session = this.sessionFactory.getCurrentSession();
		Village village = (Village)session.load(Village.class, villageCode);
		Set<GateInfo> gateInfoSet = village.getGateInfos();
		return gateInfoSet;
	}
	
	@Override
	public void insertNewGateInfoEntry(GateInfo gateInfo){
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(gateInfo);
		AppLogger.getLogger().debug("GateInfo for "+gateInfo.getId()+" at village "+gateInfo.getVillage().getVillageName()+" stored.");
	}
	
	@Override
	public void insertCloseDate(GateInfo gateInfo){
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(gateInfo);
		AppLogger.getLogger().debug("GateInfo for "+gateInfo.getId()+" at village "+gateInfo.getVillage().getVillageName()+" updated with close date.");
	}

}
