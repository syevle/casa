package com.city.model.dao.impl;

import org.springframework.stereotype.Service;

import com.city.model.dao.CreatorDao;
import com.city.model.obj.Creator;
import com.city.persistence.impl.GenericHibernateDaoImpl;
/**
 * DAO implement for table: Site.
 * @author santosh yevle
 */
@Service
public class CreatorDaoImpl extends GenericHibernateDaoImpl<Creator, String> implements CreatorDao  {
	
	CreatorDaoImpl() {
		super(Creator.class);
	}
	
}
