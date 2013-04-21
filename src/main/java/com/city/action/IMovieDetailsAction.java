package com.city.action;

import org.springframework.transaction.annotation.Transactional;

import com.city.model.obj.MovieDetails;

/** 
 * ISiteAction maintain transaction in application.
 * @author santosh yevle
 */


public interface IMovieDetailsAction {
	// The @Transactional signals spring to initialise a session and a 
	// transaction and close them at the end of this method. 
	@Transactional
	MovieDetails saveOrUpdate(MovieDetails frmObject);
	// The @Transactional signals spring to initialise a session and a 
	// transaction and close them at the end of this method. 
	@Transactional
	MovieDetails save(MovieDetails frmObject);
	// The @Transactional signals spring to initialise a session and a 
	// transaction and close them at the end of this method. 
	@Transactional
	void delete(String movCode);
}
