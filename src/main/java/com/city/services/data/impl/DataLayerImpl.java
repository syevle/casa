package com.city.services.data.impl;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.ReplicationMode;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.city.factories.HibernateDaoFactory;
import com.city.model.obj.Creator;
import com.city.model.obj.Director;
import com.city.model.obj.MovieDetails;
import com.city.model.obj.Site;
import com.city.model.obj.Starcast;
import com.city.persistence.GenericDAO;
import com.city.services.data.DataLayer;
/** 
 * Data layer.
 * @author santosh yevle
 */
@Component
public class DataLayerImpl implements DataLayer {
	/** Singleton reference to this class. */
	private static DataLayer instance;
	/** map lock. */
	private static Object daoMapLock = new Object();

	/** Internal handle. */
	private static Map<Class<?>, GenericDAO<?, ?>> daoMap = null; 
	
		/** Inner handle. */
	private static ApplicationContext context;
 /** Sessionfactory in use. Filled in by Spring. */ 
    private SessionFactory sessionFactory = null;
	
	/** Handle to get back ourselves and perform correct injection. 
	 * @param ctxt filled by spring
	 */
	@Autowired
	public void setApplicationContext(ApplicationContext ctxt) {
		DataLayerImpl.context = ctxt;
	}
	
	
		
 	/**
     * Set session factory.
     * @param sessionFactory sessionfactory to use. 
     */
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    /** 
	* Returns a DAO instance of the given type.
	* @param <T> Type
	* @param persistentObject to get
	* @return GenericDAO<T, ?> object
     */
    @SuppressWarnings("unchecked")
    private <T> GenericDAO<T, ?> getDAO(final T persistentObject) {
		T persistent = persistentObject;

		synchronized (daoMapLock) {
    		if (daoMap == null) {
    			daoMap = new ConcurrentHashMap<Class<?>, GenericDAO<?, ?>>(); 
	 	   		daoMap.put(Site.class, HibernateDaoFactory.getSiteDao());
	 	   		daoMap.put(MovieDetails.class, HibernateDaoFactory.getMovieDetailsDao());
	 	   		daoMap.put(Creator.class, HibernateDaoFactory.getCreatorDao());
	 	   		daoMap.put(Director.class, HibernateDaoFactory.getDirectorDao());
	 	   		daoMap.put(Starcast.class, HibernateDaoFactory.getStarcastDao());
    		}
		 }
		if (persistentObject instanceof HibernateProxy) {
			persistent = (T) ((HibernateProxy) persistentObject).getHibernateLazyInitializer().getImplementation();
		} 
		
		GenericDAO<T, ?> result = (GenericDAO<T, ?>) daoMap.get(persistent.getClass());
		if (result == null) {
			throw new IllegalAccessError("The given object is of an incorrect type. ");
		}
		return result;
    }

    /**
     * Deletes the given object from disk.
     * @param <T> A DataLayerObject-derived type
     * @param persistentObject Object to delete
     */
    public <T> void delete(T persistentObject) {
    	    	getDAO(persistentObject).delete(persistentObject);
    }
    /**
     * Refresh the object $class.className from disk.
     * @param <T> A DataLayerObject-derived type
     * @param persistentObject Object to reload
     */
    public <T> void refresh(T persistentObject) {
	    	getDAO(persistentObject).refresh(persistentObject);
    }

    /**
     * Saves the given object to disk.
     * @param persistentObject Object to save
	 * @param <T> A DataLayerObject-derived type
     * @return Identifier of saved object 
     */
    public <T> Serializable save(T persistentObject) {
        return getDAO(persistentObject).save(persistentObject);
    }
    /**
     * Saves or updates the given $class.className object to disk.
	 * @param <T> A DataLayerObject-derived type
     * @param persistentObject Object to save 
     */
    public <T> void saveOrUpdate(T persistentObject) {
        getDAO(persistentObject).saveOrUpdate(persistentObject);
    }
    /**
     * Updates the given object to disk.
	 * @param <T> A DataLayerObject-derived type
     * @param persistentObject Object to update 
     */
    public <T> void update(T persistentObject) {
        getDAO(persistentObject).update(persistentObject);
    }


    /** Deletes an object of a given Id. 
     * Will load the object internally so consider using delete (Site obj) directly
     * @param id Identifier to delete
     */
    public void deleteSite(final BigDecimal id)  {
        HibernateDaoFactory.getSiteDao().delete(loadSite(id));
    }
	
    /**
     * Loads the given Object.
     * @param id Identifier to load
     * @return a Site object
     */
    public Site loadSite(final BigDecimal id) {
        return HibernateDaoFactory.getSiteDao().load(id);
    }
    /**
     * Loads the given Object.
     * @param id Id to load
     * @return An object of type T
     */
     public Site getSite(final BigDecimal id) {
        return HibernateDaoFactory.getSiteDao().get(id);
    }  
    /** Returns a singleton instance of this class.
     * @return an singleton instance
     */
    public static synchronized DataLayer getInstance() {
        
        if (instance == null) {
        	if (context == null) {
        		throw new IllegalStateException("Context is null. Make sure Spring is initialized.");
        	}
     		instance = (DataLayer) context.getBean("dataLayerImpl");
        }
        
        return instance; 
    }
    /** Returns a query handle.
     * @param query Query to use
     * @return A query instance
     */
     public Query createQuery(final String query) {
        return this.sessionFactory.getCurrentSession().createQuery(query);
    }
    /** Returns a criteria handle.
     * @param criteria Criteria to use
     * @return A criteria instance
     */
     public Criteria createCriteria(final String criteria) {
        return this.sessionFactory.getCurrentSession().createCriteria(criteria);
    }
    /** Returns a Query handle based on your package-level named query.
     * @param query Query to use
     * @return A query instance
     */
     public Query getNamedQuery(final String query) {
        return this.sessionFactory.getCurrentSession().getNamedQuery(query);
    }
    /** Create a new Criteria instance, for the given entity class, or a superclass of an entity class.
	* @param persistentObject a class, which is persistent, or has persistent subclasses 
	* @return Criteria instance
	*/
	@SuppressWarnings("unchecked")
	public Criteria createCriteria(Class persistentObject) {
        return this.sessionFactory.getCurrentSession().createCriteria(persistentObject);
    }
    /** Flushes the currently open session.
	*/
	public void flushSession() {
        this.sessionFactory.getCurrentSession().flush();
    }
    /** Clears the currently open session.
	*/
	public void clearSession() {
        this.sessionFactory.getCurrentSession().clear();
    }
    /** Flushes and clears the currently open session.
	*/
	public void flushAndClearSession() {
		flushSession();
		clearSession();
    }
	/** Call currentSession.replicate.
	 * @param obj to replicate
	 * @param replicationMode mode
	 */ 
	public void replicate(Object obj, ReplicationMode replicationMode) {
		this.sessionFactory.getCurrentSession().replicate(obj, replicationMode);
	}
	/** Hibernate Merge. 
	 * @param obj to merge
	 * @return obj merged.
	 */
	public Object merge(Object obj) {
		return this.sessionFactory.getCurrentSession().merge(obj);
	}
	/** Returns the current session.
	 * @return the currently active session
	 */
	public Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}
	/** Returns a query handle.
     * @param query Query to use
     * @return A query instance
     */
     public SQLQuery createSQLQuery(final String query) {
        return this.sessionFactory.getCurrentSession().createSQLQuery(query);
    }
    /** Remove this instance from the session cache. 
	 * Changes to the instance will not be synchronized with the database
	 * @param obj object to evict
	 */
	public void evict(Object obj) {
        this.sessionFactory.getCurrentSession().evict(obj);
    }
    /**
     * Return the persistent instance of the given entity class with the given 
     * identifier, or null if there is no such persistent instance. 
     * (If the instance, or a proxy for the instance, is already 
     * associated with the session, return that instance or proxy)
     *
     * @param clazz a persistent class
     * @param id a valid identifier of an existing persistent instance of the class
     * @return a persistent instance or null
     * @throws HibernateException
     */
	public Object get(Class<?> clazz, Serializable id) throws HibernateException {
        return this.sessionFactory.getCurrentSession().get(clazz, id);
    }	


    /**
     * Return the persistent instance of the given entity class with the given identifier, assuming that the instance exists.
     *You should not use this method to determine if an instance exists (use get() instead). Use this only to retrieve an instance that you assume exists, where non-existence would be an actual error.
     *
     * @param clazz a persistent class
     * @param id a valid identifier of an existing persistent instance of the class
     * @return the persistent instance or proxy
     * @throws HibernateException
     */
	public Object load(Class<?> clazz, Serializable id) throws HibernateException {
        return this.sessionFactory.getCurrentSession().load(clazz, id);  
    }

	@Override
	public void deleteMovieDetails(String id) {
		HibernateDaoFactory.getMovieDetailsDao().delete(loadMovieDetails(id));
		
	}

	@Override
	public MovieDetails loadMovieDetails(String id) {
		return HibernateDaoFactory.getMovieDetailsDao().load(id);
	}
	@Override
	public MovieDetails getMovieDetails(String id) {
		return HibernateDaoFactory.getMovieDetailsDao().get(id);
	}
	@Override
	public void deleteCreator(String id) {
		HibernateDaoFactory.getCreatorDao().delete(loadCreator(id));
	}

	@Override
	public Creator loadCreator(String id) {
		return HibernateDaoFactory.getCreatorDao().load(id);
	}

	@Override
	public Creator getCreator(String id) {
		return HibernateDaoFactory.getCreatorDao().get(id);
	}
	@Override
	public void deleteDirector(String id) {
		HibernateDaoFactory.getDirectorDao().delete(loadDirector(id));
	}
	@Override
	public Director loadDirector(String id) {
		return HibernateDaoFactory.getDirectorDao().load(id);
	}

	@Override
	public Director getDirector(String id) {
		return HibernateDaoFactory.getDirectorDao().get(id);
	}
	
	@Override
	public void deleteStarcast(String id) {
		HibernateDaoFactory.getStarcastDao().delete(loadStarcast(id));
	}
	@Override
	public Starcast loadStarcast(String id) {
		return HibernateDaoFactory.getStarcastDao().load(id);
	}
	@Override
	public Starcast getStarcast(String id) {
		return HibernateDaoFactory.getStarcastDao().get(id);
	}

}

