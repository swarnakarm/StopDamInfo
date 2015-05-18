package com.daminfo.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
//import org.slf4j.Logger;
import org.springframework.stereotype.Repository;

import com.daminfo.common.json.Location;
import com.daminfo.dao.BlockDAO;
import com.daminfo.model.Block;
import com.daminfo.model.District;

@Repository
public class BlockDAOImpl implements BlockDAO{

//	private static final Logger logger = LoggerFactory.getLogger(BlockDAOImpl.class);
	 
    private SessionFactory sessionFactory;
     
    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }
	
	
	@Override
	public List<Location> getAllBlocksByDistrictID(long districtCode) {
		ArrayList<Location> blockList =  new ArrayList<Location>();
		Session session = sessionFactory.getCurrentSession();
		District district = (District) session.load(District.class, new Long(districtCode));
		Set<Block> blockL = district.getBlocks();
		for(Block block:blockL){
		     Location loc = new Location(block);
		     blockList.add(loc);
		}
		return blockList;
	}

	@Override
	public Block getBlockInfo(long blockCode) {
		Session session = this.sessionFactory.getCurrentSession();      
        Block block = (Block) session.load(Block.class, new Long(blockCode));
        return block;
	}

}
