package com.daminfo.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.daminfo.common.util.AppLogger;
import com.daminfo.dao.StateDAO;
import com.daminfo.model.State;
import com.daminfo.service.common.Location;
@Repository
public class StateDAOImpl implements StateDAO{

	private SessionFactory sessionFactory;
    
    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }
	
	@Override
	public List<Location> getAllState() {
		ArrayList<Location> stateList = new ArrayList<Location>();
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM State");
		List<State> results = query.list();
		for (State state: results){
			AppLogger.getLogger().debug(state.getStateName() +" - "+state.getStateCode()); 
//			System.out.print("State Name: " + state.getStateName()); 
//		    System.out.print("State Code: " + state.getStateCode()); 
		     Location loc = new Location(state);
		     stateList.add(loc);
		}
		return stateList;
	}

	@Override
	public State getStateById(int id) {
		Session session = this.sessionFactory.getCurrentSession();      
		State state = (State) session.load(State.class, new Integer(id));
		return state;
	}

}
