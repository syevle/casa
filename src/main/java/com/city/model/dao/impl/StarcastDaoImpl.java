package com.city.model.dao.impl;

import org.springframework.stereotype.Service;

import com.city.model.dao.StarcastDao;
import com.city.model.obj.Starcast;
import com.city.persistence.impl.GenericHibernateDaoImpl;
/**
 * DAO implement for table: Site.
 * @author santosh yevle
 */
@Service
public class StarcastDaoImpl extends GenericHibernateDaoImpl<Starcast, String> implements StarcastDao  {
	
	StarcastDaoImpl() {
		super(Starcast.class);
	}
	
}
