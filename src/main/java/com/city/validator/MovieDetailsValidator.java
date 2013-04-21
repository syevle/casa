package com.city.validator;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

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

		MovieDetails movieDetails = (MovieDetails) target;

		Set<String> creatorSet = new HashSet<String>();


		if (!validStarcast(movieDetails)) {
			errors.rejectValue("starcastName1", "error.Starcast");
		}
		if (!validDirector(movieDetails)) {
			errors.rejectValue("director1", "error.Director");
		}
		if (!validCreator(movieDetails)) {
			errors.rejectValue("creator1", "error.Creator");
		}
	}
	private boolean validCreator(MovieDetails movieDetails) {
		Set<String> creatorSet = new HashSet<String>();
		if (movieDetails.getCreator1() != null && movieDetails.getCreator1().trim().length() > 0) {
			if (creatorSet.contains(movieDetails.getCreator1())) {
				return false;
			}
			creatorSet.add(movieDetails.getCreator1());
		}

		if (movieDetails.getCreator2() != null && movieDetails.getCreator2().trim().length() > 0) {
			if (creatorSet.contains(movieDetails.getCreator2())) {
				return false;
			}
			creatorSet.add(movieDetails.getCreator2());
		}

		if (movieDetails.getCreator3() != null && movieDetails.getCreator3().trim().length() > 0) {
			if (creatorSet.contains(movieDetails.getCreator3())) {
				return false;
			}
			creatorSet.add(movieDetails.getCreator3());
		}

		return true;
	}
	private boolean validDirector(MovieDetails movieDetails) {
		Set<String> directorSet = new HashSet<String>();
		if (movieDetails.getDirector1() != null && movieDetails.getDirector1().trim().length() > 0) {
			if (directorSet.contains(movieDetails.getDirector1())) {
				return false;
			}
			directorSet.add(movieDetails.getDirector1());
		}

		if (movieDetails.getDirector2() != null && movieDetails.getDirector2().trim().length() > 0) {
			if (directorSet.contains(movieDetails.getDirector2())) {
				return false;
			}
			directorSet.add(movieDetails.getDirector2());
		}

		if (movieDetails.getDirector3() != null && movieDetails.getDirector3().trim().length() > 0) {
			if (directorSet.contains(movieDetails.getDirector3())) {
				return false;
			}
			directorSet.add(movieDetails.getDirector3());
		}

		return true;
	}
	private boolean validStarcast(MovieDetails movieDetails) {
		Set<String> starcastSet = new HashSet<String>();
		if (movieDetails.getStarcastName1() != null && movieDetails.getStarcastName1().trim().length() > 0) {
			if (starcastSet.contains(movieDetails.getStarcastName1())) {
				return false;
			}
			starcastSet.add(movieDetails.getStarcastName1());
		}

		if (movieDetails.getStarcastName2() != null && movieDetails.getStarcastName2().trim().length() > 0) {
			if (starcastSet.contains(movieDetails.getStarcastName2())) {
				return false;
			}
			starcastSet.add(movieDetails.getStarcastName2());
		}

		if (movieDetails.getStarcastName3() != null && movieDetails.getStarcastName3().trim().length() > 0) {
			if (starcastSet.contains(movieDetails.getStarcastName3())) {
				return false;
			}
			starcastSet.add(movieDetails.getStarcastName3());
		}

		if (movieDetails.getStarcastName4() != null && movieDetails.getStarcastName4().trim().length() > 0) {
			if (starcastSet.contains(movieDetails.getStarcastName4())) {
				return false;
			}
			starcastSet.add(movieDetails.getStarcastName4());
		}

		if (movieDetails.getStarcastName5() != null && movieDetails.getStarcastName4().trim().length() > 0) {
			if (starcastSet.contains(movieDetails.getStarcastName5())) {
				return false;
			}
			starcastSet.add(movieDetails.getStarcastName5());
		}
		if (movieDetails.getStarcastName6() != null && movieDetails.getStarcastName6().trim().length() > 0) {
			if (starcastSet.contains(movieDetails.getStarcastName6())) {
				return false;
			}
			starcastSet.add(movieDetails.getStarcastName6());
		}
		return true;
	}

}