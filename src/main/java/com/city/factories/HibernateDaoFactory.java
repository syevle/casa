package com.city.factories;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.city.model.dao.CreatorDao;
import com.city.model.dao.DirectorDao;
import com.city.model.dao.MovieDetailsDao;
import com.city.model.dao.SiteDao;
import com.city.model.dao.StarcastDao;


/** 
 * DAO factory implementation.
 * @author santosh yevle
 */
@Component
public class HibernateDaoFactory implements ApplicationContextAware, BeanNameAware {
	/** Placeholder for an instance. */
	private static HibernateDaoFactory instance;
	/** Internal state. */
	private static ApplicationContext context = null;
	/** Bean Name. */
	private static String beanName;
	

	/** Return an instance of this class.
	 * @return an instance of this class
	 */
	public static synchronized HibernateDaoFactory getInstance() {
		if (instance == null) {
			instance = (HibernateDaoFactory) context.getBean(beanName);
		}
		return instance;
	}

	/**
	 * Sets a Spring Application Context object.
  	 * @param ctxt ApplicationContext to set
	 * @throws BeansException on spring error
	 */
	@Autowired
	public void setApplicationContext(ApplicationContext ctxt)
			throws BeansException 
	{
		context = ctxt;
	}

	/**
	* 
	* {@inheritDoc}
	* 
	* @see org.springframework.beans.factory.BeanNameAware#setBeanName(java.lang.String)
	*/
	public void setBeanName(String name) {
		beanName = name;
	}

	/**
	 * Return the requested bean from the context, throwing a RuntimeException on error.
	 *
	 * @param beanName to return
	 * @return the bean requested.
	 */
	public static Object getBean(String beanName) {
		Object result = context.getBean(beanName);
		if (result == null) { 
			throw new IllegalStateException(String.format("Could not find bean '%s'. Did you set the right component scanning?", beanName));
		} 
		
		return result;
	}

	
	/**
	 * Returns a SiteDao instance.
	 * 
	 * @return a SiteDao instance
	 */
	public static SiteDao  getSiteDao() {
		return (SiteDao) getBean("siteDaoImpl"); 
	}
	
	/**
	 * Returns a SiteDao instance.
	 * 
	 * @return a SiteDao instance
	 */
	public static MovieDetailsDao  getMovieDetailsDao() {
		return (MovieDetailsDao) getBean("movieDetailsDaoImpl"); 
	}
	
	/**
	 * Returns a SiteDao instance.
	 * 
	 * @return a SiteDao instance
	 */
	public static CreatorDao  getCreatorDao() {
		return (CreatorDao) getBean("creatorDaoImpl"); 
	}
	/**
	 * Returns a SiteDao instance.
	 * 
	 * @return a SiteDao instance
	 */
	public static DirectorDao  getDirectorDao() {
		return (DirectorDao) getBean("directorDaoImpl"); 
	}
	/**
	 * Returns a SiteDao instance.
	 * 
	 * @return a SiteDao instance
	 */
	public static StarcastDao  getStarcastDao() {
		return (StarcastDao) getBean("starcastDaoImpl"); 
	}	
}
