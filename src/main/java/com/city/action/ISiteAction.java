package com.city.action;

import java.math.BigDecimal;

import org.springframework.transaction.annotation.Transactional;

import com.city.model.obj.Site;

/** 
 * ISiteAction maintain transaction in application.
 * @author santosh yevle
 */


public interface ISiteAction {
	// The @Transactional signals spring to initialise a session and a 
	// transaction and close them at the end of this method. 
	@Transactional
	Site saveOrUpdate(Site frmObject);
	// The @Transactional signals spring to initialise a session and a 
	// transaction and close them at the end of this method. 
	@Transactional
	Site save(Site frmObject);
	// The @Transactional signals spring to initialise a session and a 
	// transaction and close them at the end of this method. 
	@Transactional
	void delete(BigDecimal id);
}
