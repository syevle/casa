package com.city.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.ValidationUtils;

import com.city.model.obj.MovieDetails;

/**
 * SiteValidator
 * 
 * @author santosh yevle
 */

@Component
public class MovieDetailsValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return MovieDetails.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils
				.rejectIfEmptyOrWhitespace(errors, "id", "error.movCode");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "movName",
				"error.movName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "avalStatus",
				"error.avalStatus");
		
		MovieDetails movieDetails = (MovieDetails)target;
		Date releaseDate = movieDetails.getRelDate();
		/*if(movieDetails != null && isValid(releaseDate.toString())){
			errors.rejectValue("relDate","error.date");
		}*/
		
	}
	
	/*private static final ThreadLocal<SimpleDateFormat> format = new ThreadLocal<SimpleDateFormat>() {
	    @Override
	    protected SimpleDateFormat initialValue() {
	        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	        df.setLenient(false);
	        return df;
	    }
	};

	public static boolean isValid(String text) {
	    if (!text.matches("\\d{4}-[01]\\d-[0-3]\\d"))
	        return false;
	    try {
	        format.get().parse(text);
	        return true;
	    } catch (ParseException ex) {
	        return false;
	    }
	}*/

}