package com.city.action.impl;

/** 
 * All crud operation implement here.
 * following coding stander maintain transeciton in appliction.  
 * @author santosh yevle
 */

import org.springframework.stereotype.Service;

import com.city.action.IMovieDetailsAction;
import com.city.model.obj.Creator;
import com.city.model.obj.Director;
import com.city.model.obj.DvdLocation;
import com.city.model.obj.Genre;
import com.city.model.obj.MovieDetails;
import com.city.model.obj.Starcast;
import com.city.services.data.impl.DataLayerImpl;

@Service
public class MovieDetailsActionImpl implements IMovieDetailsAction {
	@Override
	public void delete(String id) {
		MovieDetails frmObject = DataLayerImpl.getInstance().getMovieDetails(id);
    	DataLayerImpl.getInstance().delete(frmObject);
		DataLayerImpl.getInstance().flushSession(); // flush+evict from cache to make sure we hit the DB next 
		DataLayerImpl.getInstance().evict(frmObject);
	}
	@Override
	public MovieDetails saveOrUpdate(MovieDetails frmObject) {
		saveCreator(frmObject);
		saveGenre(frmObject);
		saveDvdLocation(frmObject);
		saveDirector(frmObject);
		saveStarcast(frmObject);
    	DataLayerImpl.getInstance().saveOrUpdate(frmObject);
		DataLayerImpl.getInstance().flushSession(); // flush+evict from cache to make sure we hit the DB next 
		DataLayerImpl.getInstance().evict(frmObject);
		
		return frmObject;
	}
	@Override
	public MovieDetails save(MovieDetails frmObject) {
		saveCreator(frmObject);
		saveGenre(frmObject);
		saveDvdLocation(frmObject);
		saveDirector(frmObject);
		saveStarcast(frmObject);
    	DataLayerImpl.getInstance().save(frmObject);
		DataLayerImpl.getInstance().flushSession(); // flush+evict from cache to make sure we hit the DB next
		DataLayerImpl.getInstance().evict(frmObject);
		
		return frmObject;     
	}
	private void saveStarcast(MovieDetails frmObject) {
		if(frmObject.getStarcastName1() != null){
			Starcast starcast1 = (Starcast) DataLayerImpl.getInstance().get(Starcast.class,frmObject.getStarcastName1());
			if(starcast1 == null && frmObject.getStarcastName1() !=null && frmObject.getStarcastName1().trim().length()>0){
				starcast1 = new Starcast();
				starcast1.setStarcastName(frmObject.getStarcastName1().trim());
				DataLayerImpl.getInstance().save(starcast1);
			}
		}
		
		if(frmObject.getStarcastName2() != null){
			Starcast starcast2 = (Starcast) DataLayerImpl.getInstance().get(Starcast.class,frmObject.getStarcastName2());
			if(starcast2 == null && frmObject.getStarcastName2() !=null && frmObject.getStarcastName2().trim().length()>0){
				starcast2 = new Starcast();
				starcast2.setStarcastName(frmObject.getStarcastName2().trim());
				DataLayerImpl.getInstance().save(starcast2);
			}
		}
		
		if(frmObject.getStarcastName3() != null){
			Starcast starcast3 = (Starcast) DataLayerImpl.getInstance().get(Starcast.class,frmObject.getStarcastName3());
			if(starcast3 == null && frmObject.getStarcastName3() !=null && frmObject.getStarcastName3().trim().length()>0){
				starcast3 = new Starcast();
				starcast3.setStarcastName(frmObject.getStarcastName3().trim());
				DataLayerImpl.getInstance().save(starcast3);
			}
		}
		
		if(frmObject.getStarcastName4() != null){
			Starcast starcast4 = (Starcast) DataLayerImpl.getInstance().get(Starcast.class,frmObject.getStarcastName4());
			if(starcast4 == null && frmObject.getStarcastName4() !=null && frmObject.getStarcastName4().trim().length()>0){
				starcast4 = new Starcast();
				starcast4.setStarcastName(frmObject.getStarcastName4().trim());
				DataLayerImpl.getInstance().save(starcast4);
			}
		}
		
		if(frmObject.getStarcastName5() != null){
			Starcast starcast5 = (Starcast) DataLayerImpl.getInstance().get(Starcast.class,frmObject.getStarcastName5());
			if(starcast5 == null && frmObject.getStarcastName5() !=null && frmObject.getStarcastName5().trim().length()>0){
				starcast5 = new Starcast();
				starcast5.setStarcastName(frmObject.getStarcastName5().trim());
				DataLayerImpl.getInstance().save(starcast5);
			}
		}
		
		if(frmObject.getStarcastName6() != null){
			Starcast starcast6 = (Starcast) DataLayerImpl.getInstance().get(Starcast.class,frmObject.getStarcastName6());
			if(starcast6 == null && frmObject.getStarcastName6() !=null && frmObject.getStarcastName6().trim().length()>0){
				starcast6 = new Starcast();
				starcast6.setStarcastName(frmObject.getStarcastName6().trim());
				DataLayerImpl.getInstance().save(starcast6);
			}
		}
	}
	
	/**
	 * 
	 * @param frmObject
	 */
	private void saveDirector(MovieDetails frmObject) {
		if(frmObject.getDirector1() != null){
			Director director1 = (Director) DataLayerImpl.getInstance().get(Director.class,frmObject.getDirector1());
			if(director1 == null && frmObject.getDirector1() !=null && frmObject.getDirector1().trim().length()>0){
				director1 = new Director();
				director1.setDirectorName(frmObject.getDirector1().trim());
				DataLayerImpl.getInstance().save(director1);
			}
		}
		if(frmObject.getDirector2() != null){
			Director director2 = (Director) DataLayerImpl.getInstance().get(Director.class,frmObject.getDirector2());
			if(director2 == null && frmObject.getDirector2() !=null && frmObject.getDirector2().trim().length()>0){
				director2 = new Director();
				director2.setDirectorName(frmObject.getDirector2().trim());
				DataLayerImpl.getInstance().save(director2);
			}
		}
		if(frmObject.getDirector3() != null){
			Director director3 = (Director) DataLayerImpl.getInstance().get(Director.class,frmObject.getDirector3());
			if(director3 == null && frmObject.getDirector3() !=null && frmObject.getDirector3().trim().length()>0){
				director3 = new Director();
				director3.setDirectorName(frmObject.getDirector3().trim());
				DataLayerImpl.getInstance().save(director3);
			}
		}
	}
	
	
	/**
	 * 
	 * @param frmObject
	 */
	private void saveCreator(MovieDetails frmObject) {
		if(frmObject.getCreator1() != null){
			Creator creator1 = (Creator) DataLayerImpl.getInstance().get(Creator.class,frmObject.getCreator1());
			if(creator1 == null && frmObject.getCreator1() !=null && frmObject.getCreator1().trim().length()>0){
				creator1 = new Creator();
				creator1.setCreatorName(frmObject.getCreator1().trim());
				DataLayerImpl.getInstance().save(creator1);
			}
		}
		
		if(frmObject.getCreator2() != null && frmObject.getCreator2() !=null && frmObject.getCreator2().trim().length()>0){
			Creator creator2 = (Creator) DataLayerImpl.getInstance().get(Creator.class,frmObject.getCreator2());
			if(creator2 == null){
				creator2 = new Creator();
				creator2.setCreatorName(frmObject.getCreator2().trim());
				DataLayerImpl.getInstance().save(creator2);
			}
		}
		if(frmObject.getCreator3() != null && frmObject.getCreator3() !=null && frmObject.getCreator3().trim().length()>0){
			Creator creator3 = (Creator) DataLayerImpl.getInstance().get(Creator.class,frmObject.getCreator3());
			if(creator3 == null){
				creator3 = new Creator();
				creator3.setCreatorName(frmObject.getCreator3().trim());
				DataLayerImpl.getInstance().save(creator3);
			}
		}
	}
	/**
	 * 
	 * @param frmObject
	 */
	private void saveGenre(MovieDetails frmObject) {
		if(frmObject.getCreator1() != null){
			Genre genre1 = (Genre) DataLayerImpl.getInstance().get(Genre.class,frmObject.getGenre1());
			if(genre1 == null && frmObject.getGenre1() !=null && frmObject.getGenre1().trim().length()>0){
				genre1 = new Genre();
				genre1.setGenreName(frmObject.getGenre1().trim());
				DataLayerImpl.getInstance().save(genre1);
			}
		}
		
		if(frmObject.getGenre2() != null && frmObject.getGenre2() !=null && frmObject.getGenre2().trim().length()>0){
			Genre genre2 = (Genre) DataLayerImpl.getInstance().get(Genre.class,frmObject.getGenre2());
			if(genre2 == null){
				genre2 = new Genre();
				genre2.setGenreName(frmObject.getGenre2().trim());
				DataLayerImpl.getInstance().save(genre2);
			}
		}
		if(frmObject.getGenre3() != null && frmObject.getGenre3() !=null && frmObject.getGenre3().trim().length()>0){
			Genre genre3 = (Genre) DataLayerImpl.getInstance().get(Genre.class,frmObject.getGenre3());
			if(genre3 == null){
				genre3 = new Genre();
				genre3.setGenreName(frmObject.getGenre3().trim());
				DataLayerImpl.getInstance().save(genre3);
			}
		}
		if(frmObject.getGenreExtra() != null && frmObject.getGenreExtra() !=null && frmObject.getGenreExtra().trim().length()>0){
			Genre genreExtra = (Genre) DataLayerImpl.getInstance().get(Genre.class,frmObject.getGenreExtra());
			if(genreExtra == null){
				genreExtra = new Genre();
				genreExtra.setGenreName(frmObject.getGenreExtra().trim());
				DataLayerImpl.getInstance().save(genreExtra);
			}
		}
	}
	/**
	 * 
	 * @param frmObject
	 */
	private void saveDvdLocation(MovieDetails frmObject) {
		if(frmObject.getDvdLocation() != null){
			DvdLocation dvdLocation = (DvdLocation) DataLayerImpl.getInstance().get(DvdLocation.class,frmObject.getDvdLocation());
			if(dvdLocation == null && frmObject.getDvdLocation() !=null && frmObject.getDvdLocation().trim().length()>0){
				dvdLocation = new DvdLocation();
				dvdLocation.setDvdLocation(frmObject.getDvdLocation().trim());
				DataLayerImpl.getInstance().save(dvdLocation);
			}
		}
		
	}
}
