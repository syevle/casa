package com.city.model.dao.impl;

import org.springframework.stereotype.Service;

import com.city.model.dao.DirectorDao;
import com.city.model.obj.Director;
import com.city.persistence.impl.GenericHibernateDaoImpl;
/**
 * DAO implement for table: Site.
 * @author santosh yevle
 */
@Service
public class DirectorDaoImpl extends GenericHibernateDaoImpl<Director, String> implements DirectorDao  {
	
	DirectorDaoImpl() {
		super(Director.class);
	}
	
}
