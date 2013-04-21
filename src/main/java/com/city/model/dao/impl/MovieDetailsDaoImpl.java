package com.city.model.dao.impl;

import org.springframework.stereotype.Service;

import com.city.model.dao.MovieDetailsDao;
import com.city.model.obj.MovieDetails;
import com.city.persistence.impl.GenericHibernateDaoImpl;
/**
 * DAO implement for table: Site.
 * @author santosh yevle
 */
@Service
public class MovieDetailsDaoImpl extends GenericHibernateDaoImpl<MovieDetails, String> implements MovieDetailsDao  {
	
	MovieDetailsDaoImpl() {
		super(MovieDetails.class);
	}
	
}
