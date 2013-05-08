package com.city.model.dao.impl;

import org.springframework.stereotype.Service;

import com.city.model.dao.CreatorDao;
import com.city.model.dao.DvdLocationDao;
import com.city.model.obj.Creator;
import com.city.model.obj.DvdLocation;
import com.city.persistence.impl.GenericHibernateDaoImpl;
/**
 * DAO implement for table: Site.
 * @author santosh yevle
 */
@Service
public class DvdLocationDaoImpl extends GenericHibernateDaoImpl<DvdLocation, String> implements DvdLocationDao  {
	
	DvdLocationDaoImpl() {
		super(DvdLocation.class);
	}
	
}
