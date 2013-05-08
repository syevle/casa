package com.city.model.dao.impl;

import org.springframework.stereotype.Service;

import com.city.model.dao.GenreDao;
import com.city.model.obj.Genre;
import com.city.persistence.impl.GenericHibernateDaoImpl;
/**
 * DAO implement for table: Site.
 * @author santosh yevle
 */
@Service
public class GenreDaoImpl extends GenericHibernateDaoImpl<Genre, String> implements GenreDao  {
	
	GenreDaoImpl() {
		super(Genre.class);
	}
	
}
