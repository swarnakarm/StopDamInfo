package com.daminfo.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.daminfo.common.json.ReportList;
import com.daminfo.dao.ReportDAO;

@Repository
public class ReportDAOImpl implements ReportDAO{

	private SessionFactory sessionFactory;
    
    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }
	
	@Override
	public ReportList getBlockReport(long blockCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReportList getPanchayatReport(long panchayatCode) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
