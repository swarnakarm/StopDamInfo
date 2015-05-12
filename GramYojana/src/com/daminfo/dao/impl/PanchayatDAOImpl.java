package com.daminfo.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.daminfo.dao.PanchayatDAO;
import com.daminfo.model.Block;
import com.daminfo.model.Panchayat;
import com.daminfo.service.common.Location;
@Repository
public class PanchayatDAOImpl implements PanchayatDAO {

	private SessionFactory sessionFactory;
    
    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }
    
	@Override
	public List<Location> getAllPanchayatsByBlockID(int blockCode) {
		ArrayList<Location> blockList =  new ArrayList<Location>();
		Session session = sessionFactory.getCurrentSession();
		Block block = (Block) session.load(Block.class, new Long(blockCode));
		Set<Panchayat> panchayatL = block.getPanchayats();
		for(Panchayat panchayat:panchayatL){
		     Location loc = new Location(panchayat);
		     blockList.add(loc);
		}
		return blockList;
	}

	@Override
	public Panchayat getPanchayatInfo(int panchayatCode) {
		Session session = this.sessionFactory.getCurrentSession();      
		Panchayat panchayat = (Panchayat) session.load(Panchayat.class, new Integer(panchayatCode));
		return panchayat;
	}

}
