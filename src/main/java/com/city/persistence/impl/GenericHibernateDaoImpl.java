package com.city.persistence.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.city.persistence.GenericDAO;

/** 
 * Generic HibernateDao Impl.
 * @author santosh yevle
 */

@Repository
@Transactional(readOnly = true)
public abstract class GenericHibernateDaoImpl<T, PK extends Serializable> implements GenericDAO<T, PK> {
	   /** Class type. */
    Class<T> type;
    /** Sessionfactory in use. Filled in by Spring. */ 
    private SessionFactory sessionFactory = null;
    
    /**
     * Default bean constructor for spring.
     */
    public GenericHibernateDaoImpl() {
            // default constructor for spring
    }
    
    /**
     * Constructor.
     * @param type class type
     */
    public GenericHibernateDaoImpl(Class<T> type) {
        this.type = type;
    }

    /** Helper functions.
     * @return the currently set class
     */
    public Class<T> getPersistentClass() {
        return this.type;
    }
    
    /**
     * Delete persistentObject from DB.
     * @param persistentObject object to delete.
     */
    public void delete(T persistentObject) {
        getSession().delete(persistentObject);
    }

    /** Deletes an object of a given Id. Will load the object internally so consider using delete (T obj) directly
     * @param id Delete key
     */
    public void delete(PK id) {
        getSession().delete(load(id));
    }

    /**
     * Loads the given Object.
     * @param id to load
     * @return T Loaded object
     */
    @SuppressWarnings("unchecked")
    public T load(PK id) {
        return (T) getSession().load(this.type, (Serializable) id);
    }


    /**
     * Loads the given Object.
     * @param id Id to load
     * @return An object of type T
     */
    @SuppressWarnings("unchecked")
    public T get(PK id) {
        return (T) getSession().get(this.type, (Serializable) id);
    }

    /**
     * Item to save.
     * @param o object to save
     * @return PK
     */
    @SuppressWarnings("unchecked")
    public PK save(T o) {
        return (PK) getSession().save(o);
    }

   /**
     * Item to refresh.
     * @param o object to refresh
     */
    public void refresh(T o) {
        getSession().refresh(o);
    }

    /**
     * Item to saveOrUpdate.
     * @param o item to save.
     */
    public void saveOrUpdate(T o) {
        getSession().saveOrUpdate(o);
    }

    /**
     * Update object.
     * @param o object to update
     */
    public void update(T o) {
        getSession().update(o);
    }

    /**
     * Gets the current session in use (creates one if necessary).
     * @return Session object 
     */
    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * Get query.
     * @param s Query to execute.
     * @return Query object
     */
    public Query getQuery(String s) {
        return getSession().createQuery(s);
    }
    /**
     * Get query.
     * @param s Query to execute.
     * @return Query object
     */
    public List<Object> getNativeQuery(String s) {
    	SQLQuery query = getSession().createSQLQuery(s);
    	List<Object> list = query.list();
        return list;
    }
    /**
     * Get Session factory.
     * @return SessionFactory Object
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Set session factory.
     * @param sessionFactory object
     */
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    /** Delete object.
     * @param persistentObject to delete
     * @param session to use
     * 
     */
    public void delete(T persistentObject, Session session) {
        session.delete(persistentObject);
    }

    /** Deletes an object of a given Id. Will load the object internally so consider using delete (T obj) directly.
     * @param id to delete 
     * @param session to use
     */
    public void delete(PK id, Session session) {
        session.delete(load(id));
    }

    /**
     * Loads the given Object.
     * @param id to load
     * @param session to use
     * @return  an object of type T
     */
    @SuppressWarnings("unchecked")
    public T load(PK id, Session session) {
        return (T) session.load(this.type, (Serializable) id);
    }

    /**
     * Loads the given Object.
     * @param id Id to load
     * @param session Session to use
     * @return An object of type T
     */
    @SuppressWarnings("unchecked")
    public T get(PK id, Session session) {
        return (T) session.get(this.type, (Serializable) id);
    }

    /** Save object.
     * @param o to save 
     * @param session to use
     * @return the id of the saved object
     * 
     */
    @SuppressWarnings("unchecked")
    public PK save(T o, Session session) {
        return (PK) session.save(o);
    }

    /** Save Or Update object.
     * @param o to save
     * @param session to use.
     * 
     */
    public void saveOrUpdate(T o, Session session) {
        session.saveOrUpdate(o);
    }

    /** Update record.
     * @param o to update
     * @param session to use
     * 
     */
    public void update(T o, Session session) {
        session.update(o);
    }


    /**
     * GetQuery.
     * @param s to return
     * @param session  to use
     * @return Query object
     */
    public Query getQuery(String s, Session session) {
        return session.createQuery(s);
    }

    /** Wrapper around hibernate functions.
     * @param criterion to use
     * @return A list of matching objects
     */
    @SuppressWarnings("unchecked")
    public List<T> findByCriteria(Criterion... criterion) {
        Criteria crit =  getSession().createCriteria(getPersistentClass());
        
        for (Criterion c : criterion) {
            crit.add(c);
        }
        return crit.list();
    }
    /** Wrapper around hibernate functions.
     * @param criterion to use
     * @return A list of matching objects
     */
    @SuppressWarnings("unchecked")
    public List<T> findByCriteria(List<Criterion> criterion,Integer limit,Order... orders) {
        Criteria crit =  getSession().createCriteria(getPersistentClass());
        
        for (Criterion c : criterion) {
            crit.add(c);
        }
        for (Order o : orders) {
            crit.addOrder(o);
        }
        if(limit != null){
        	crit.setMaxResults(limit);
        }
        return crit.list();
    }


    
    /** FindAll.
     * @return A list of all the objects
     */
    public List<T> findAll() {
        return findByCriteria();
    }
    
    /** Flushes the cache of the currently-used session.
     * 
     */
    public void flush() {
        getSession().flush();
    }
    
    /** Object to evict from cache.
     * @param obj Object to evict
     */
    public void evict(Object obj) {
        getSession().evict(obj);
    }
    
    
    /** FindByExample.
     * @param exampleInstance to use
     * @param excludeProperty to use
     * @return List of matching objects
     */
    @SuppressWarnings("unchecked")
    public List<T> findByExample(T exampleInstance, String... excludeProperty) {
        Criteria crit = getSession().createCriteria(getPersistentClass());
        Example example = Example.create(exampleInstance);
        for (String exclude : excludeProperty) {
            example.excludeProperty(exclude);
        }
        crit.add(example);
        return crit.list();
    }
}