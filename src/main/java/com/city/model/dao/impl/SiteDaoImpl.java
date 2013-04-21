package com.city.model.dao.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.city.model.dao.SiteDao;
import com.city.model.obj.Site;
import com.city.persistence.impl.GenericHibernateDaoImpl;
/**
 * DAO implement for table: Site.
 * @author santosh yevle
 */
@Service
public class SiteDaoImpl extends GenericHibernateDaoImpl<Site, BigDecimal> implements SiteDao  {
	
	SiteDaoImpl() {
		super(Site.class);
	}
	
}
