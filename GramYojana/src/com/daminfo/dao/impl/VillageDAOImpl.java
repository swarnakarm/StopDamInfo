package com.daminfo.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.daminfo.common.json.Location;
import com.daminfo.dao.VillageDAO;
import com.daminfo.model.Panchayat;
import com.daminfo.model.Village;
@Repository
public class VillageDAOImpl implements VillageDAO{

	private SessionFactory sessionFactory;
    
    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }
	
	@Override
	public List<Location> getAllVillagesByPanchayatID(long panchayatCode) {
		ArrayList<Location> villageList =  new ArrayList<Location>();
		Session session = sessionFactory.getCurrentSession();
		Panchayat panchayat = (Panchayat) session.load(Panchayat.class, new Long(panchayatCode));
		Set<Village> villageL = panchayat.getVillages();
		for(Village village:villageL){
		     Location loc = new Location(village);
		     villageList.add(loc);
		}
		return villageList;
	}

	@Override
	public Village getVillageInfo(long villageCode) {
		Session session = this.sessionFactory.getCurrentSession();      
		Village village = (Village) session.load(Village.class, new Long(villageCode));
		return village;
	}

}
