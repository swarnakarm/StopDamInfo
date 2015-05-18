package com.daminfo.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.daminfo.common.json.Location;
import com.daminfo.dao.DistrictDAO;
import com.daminfo.model.Block;
import com.daminfo.model.District;
import com.daminfo.model.State;
@Repository
public class DistrictDAOImpl implements DistrictDAO{

	private SessionFactory sessionFactory;
     
    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }
		
	@Override
	public List<Location> getAllDistrictsByStateID(long stateCode) {
		ArrayList<Location> districtList =  new ArrayList<Location>();
		Session session = sessionFactory.getCurrentSession();
		State state = (State) session.load(State.class, new Long(stateCode));
		Set<District> districtL = state.getDistricts();
		for(District district:districtL){
		     Location loc = new Location(district);
		     districtList.add(loc);
		}
		return districtList;
	}

	@Override
	public District getDistrictInfo(long districtCode) {
		Session session = this.sessionFactory.getCurrentSession();      
		District district = (District) session.load(Block.class, new Long(districtCode));
		return district;
	}

}
