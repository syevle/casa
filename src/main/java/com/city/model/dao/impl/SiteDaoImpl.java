package com.city.model.dao.impl;


import org.springframework.stereotype.Service;

import com.city.model.dao.SiteDao;
import com.city.model.obj.Site;
import com.city.persistence.impl.GenericHibernateDaoImpl;
/**
 * DAO implement for table: Site.
 * @author santosh yevle
 */
@Service
public class SiteDaoImpl extends GenericHibernateDaoImpl<Site, Long> implements SiteDao  {
	
	SiteDaoImpl() {
		super(Site.class);
	}
	
}
