package com.city.validator;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.ValidationUtils;

import com.city.model.obj.Site;
/** 
 * SiteValidator
 * @author santosh yevle
 */

@Component
public class SiteValidator implements Validator {


	@Override
	public boolean supports(Class<?> clazz) {
		 return Site.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
			 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.siteName");
			 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "error.siteAddress");
	}
}