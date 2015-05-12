package com.daminfo.dao.impl;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

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
		Village village = (Village)session.load(Village.class, villageCode);
		Set<GateInfo> gateInfoSet = village.getGateInfos();
		for(GateInfo gateInfo : gateInfoSet){
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

}
