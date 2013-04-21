package com.city.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.city.action.IMovieDetailsAction;
import com.city.factories.HibernateDaoFactory;
import com.city.model.obj.Creator;
import com.city.model.obj.Director;
import com.city.model.obj.MovieDetails;
import com.city.model.obj.Starcast;
import com.city.util.Constant;
import com.city.validator.MovieDetailsValidator;

/**
 * SiteController Controller all request and response in web application.
 * 
 * @author santosh yevle
 */

@Controller
@SessionAttributes({"movieDetailsList"})
@RequestMapping(Constant.MOVIEDETAILS)
public class MovieDetailsController {
	private static final String REQUESTMAPPING = Constant.MOVIEDETAILS;
	// BELOW CODE CHAGE IN EACH MODLE
	// Just Replace Site string with your Object name.
	String PATH = REQUESTMAPPING;
	MovieDetails frmObject = null;

	MovieDetailsValidator validator = null;

	@Autowired
	IMovieDetailsAction movieDetailsAction;

	@Autowired
	public void setValidator(MovieDetailsValidator validator) {
		this.validator = validator;
	}

	MovieDetails getFromObject() {
		return new MovieDetails();
	}

	public MovieDetailsValidator getValidator() {
		return validator;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dateFormat.setLenient(false);

		// true passed to CustomDateEditor constructor means convert empty
		// String to null
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

	@RequestMapping(value = Constant.UPDATE, method = RequestMethod.POST)
	public String update(
			@ModelAttribute("frmObject") @Valid MovieDetails frmObject,
			BindingResult result, ModelMap map) {
		validator.validate(frmObject, result);
		if (result.hasErrors()) {
			map.put("requestMapping", REQUESTMAPPING);
			map.put("command", "update");
			map.put("readonly", "false");
			return PATH + Constant.ACTION;
		}

		movieDetailsAction.saveOrUpdate(frmObject);

		return Constant.REDIRECT + PATH + Constant.LIST;
	}

	@RequestMapping(value = Constant.ADD, method = RequestMethod.POST)
	public String add(
			@ModelAttribute("frmObject") @Valid MovieDetails frmObject,
			BindingResult result, ModelMap map) {

		validator.validate(frmObject, result);
		if (result.hasErrors()) {
			map.put("requestMapping", REQUESTMAPPING);
			map.put("command", "add");
			map.put("readonly", "false");
			return PATH + Constant.ACTION;
		}

		movieDetailsAction.save(frmObject);

		return Constant.REDIRECT + PATH + Constant.LIST;
	}

	private String creatorList() {
		List<Creator> creatorList = HibernateDaoFactory.getCreatorDao()
				.findAll();
		StringBuffer creatorString = new StringBuffer();
		for (Creator creator : creatorList) {
			creatorString.append("\"").append(creator.getCreatorName())
					.append("\"").append(",");
		}
		if (creatorString.length() == 0) {
			return creatorString.toString();
		} else {
			return creatorString.substring(0, creatorString.length() - 1)
					.toString();
		}
	}
	
	private String directorList() {
		List<Director> directorList = HibernateDaoFactory.getDirectorDao().findAll();
		StringBuffer directorString = new StringBuffer();
		for (Director director : directorList) {
			directorString.append("\"").append(director.getDirectorName())
					.append("\"").append(",");
		}
		if (directorString.length() == 0) {
			return directorString.toString();
		} else {
			return directorString.substring(0, directorString.length() - 1)
					.toString();
		}
	}
	
	private String starcastList() {
		List<Starcast> starcastList = HibernateDaoFactory.getStarcastDao().findAll();
		StringBuffer starcastString = new StringBuffer();
		for (Starcast starcast : starcastList) {
			starcastString.append("\"").append(starcast.getStarcastName())
					.append("\"").append(",");
		}
		if (starcastString.length() == 0) {
			return starcastString.toString();
		} else {
			return starcastString.substring(0, starcastString.length() - 1)
					.toString();
		}
	}
	private String movieCodeList() {
		List<MovieDetails> movieDetailsList = HibernateDaoFactory.getMovieDetailsDao().findAll();
		StringBuffer movieDetailsString = new StringBuffer();
		for (MovieDetails movieDetails : movieDetailsList) {
			movieDetailsString.append("\"").append(movieDetails.getId())
					.append("\"").append(",");
		}
		if (movieDetailsString.length() == 0) {
			return movieDetailsString.toString();
		} else {
			return movieDetailsString.substring(0, movieDetailsString.length() - 1)
					.toString();
		}
	}
	private String movieNameList() {
		List<MovieDetails> movieDetailsList = HibernateDaoFactory.getMovieDetailsDao().findAll();
		StringBuffer movieDetailsString = new StringBuffer();
		for (MovieDetails movieDetails : movieDetailsList) {
			movieDetailsString.append("\"").append(movieDetails.getMovName())
					.append("\"").append(",");
		}
		if (movieDetailsString.length() == 0) {
			return movieDetailsString.toString();
		} else {
			return movieDetailsString.substring(0, movieDetailsString.length() - 1)
					.toString();
		}
	}
	public Integer nextMovieId(){
		Integer id = null;
		List<Object> list = HibernateDaoFactory.getMovieDetailsDao().getNativeQuery("select TRAN_SEQ.nextval as IDs from dual");
		for(Object row : list){ 
			id = new Integer(row.toString());
		}
		return id;
	}
	// -----------------------------------------------------------------
	@RequestMapping(value = Constant.LIST, method = RequestMethod.POST)
	public String list(Map<String, Object> map,ModelMap model,@ModelAttribute("frmObject") MovieDetails frmObject) {
		// below line important it.
		map.put("id", frmObject.getId());
		map.put("movName", frmObject.getMovName());
		
		return Constant.REDIRECT + PATH + Constant.LIST;
	}
	@RequestMapping(value = Constant.LIST, method = RequestMethod.GET)
	public String filterList(Map<String, Object> map,ModelMap model,@ModelAttribute("frmObject") MovieDetails frmObject) {
		boolean readOnly = true;
		String movieCode = frmObject.getId();
		String movieName = frmObject.getMovName();
		List<MovieDetails> movieDetailsList = null;
		List<Criterion> criterionList = new ArrayList<Criterion>();
		
		if(movieCode != null && movieCode.trim().length() > 0 ){
			Criterion id = Restrictions.like("id", movieCode+"%").ignoreCase();
			criterionList.add(id);
		} 
		if(movieName != null && movieName.trim().length() > 0 ){
			Criterion name = Restrictions.like("movName", movieName+"%").ignoreCase();
			criterionList.add(name);
		}
		if(criterionList.size() == 0){
			readOnly = false;
			movieDetailsList = HibernateDaoFactory.getMovieDetailsDao().findByCriteria();
		}else{
			movieDetailsList = HibernateDaoFactory.getMovieDetailsDao().findByCriteria(criterionList);
		}
		//map.put("id", movieCode);
		map.put("movieCodeList", movieCodeList());
		map.put("movieNameList", movieNameList());
		map.put("list", movieDetailsList);
		map.put("frmObject", frmObject);
		map.put("readonly", readOnly);
		map.put("requestMapping", REQUESTMAPPING);
		
		model.addAttribute("movieDetailsList", movieDetailsList);
		
		return PATH + Constant.LIST;
	}
	@RequestMapping(value = Constant.REPORT)
	public String report(Map<String, Object> map,@ModelAttribute("movieDetailsList") List<MovieDetails> movieDetailsList,SessionStatus status) {
		map.put("list", movieDetailsList);
		//don't uncomment below line because it clear session for movieDetailsList.
		//status.setComplete();
		return PATH + Constant.REPORT;
	}
	@RequestMapping(Constant.DELETED_BY_ID + "/{id}")
	public String delete(@PathVariable("id") String id) {
		frmObject = getFromObject();

		movieDetailsAction.delete(id);

		return Constant.REDIRECT + PATH + Constant.LIST;
	}

	/*
	 * This method added new records.
	 */
	@RequestMapping(value = Constant.ADD, method = RequestMethod.GET)
	public String add(Map<String, Object> map) {
		frmObject = getFromObject();
		frmObject.setId("DVD"+nextMovieId().toString());
		frmObject.setAvalStatus('Y');
		frmObject.setUplodReq('Y');
		map.put("frmObject", frmObject);
		map.put("requestMapping", REQUESTMAPPING);
		map.put("action", Constant.ROOTPATH + PATH + Constant.ADD);
		map.put("fromName", "To Add New Movie");
		map.put("command", "add");
		map.put("creatorList", creatorList());
		map.put("directorList", directorList());
		map.put("starcastList", starcastList());
		map.put("readonly", "false");
		map.put("disabled", "true");
		return PATH + Constant.ACTION;
	}

	@RequestMapping(value = Constant.FIND + "/{command}/{id}", method = RequestMethod.GET)
	public String find(Map<String, Object> map, @PathVariable("id") String id,
			@PathVariable("command") String command) {
		frmObject = getFromObject();
		frmObject = HibernateDaoFactory.getMovieDetailsDao().get(id);
		if (frmObject != null) {
			map.put("frmObject", frmObject);
			map.put("action", Constant.ROOTPATH + PATH + Constant.UPDATE);

			if (command.equalsIgnoreCase("update")) {
				map.put("requestMapping", REQUESTMAPPING);
				map.put("command", "update");
				map.put("readonly", "false");
				map.put("fromName", "To Update Movie");
				map.put("creatorList", creatorList());
				map.put("directorList", directorList());
				map.put("starcastList", starcastList());
			} else if (command.equalsIgnoreCase("delete")) {
				map.put("requestMapping", REQUESTMAPPING);
				map.put("fromName", "To Delete Movie");
				map.put("command", "delete");
				map.put("readonly", "true");
			} else {
				map.put("fromName", "Movie Details");
				map.put("command", "get");
				map.put("readonly", "true");
			}
			return PATH + Constant.ACTION;

		} else {
			return Constant.REDIRECT + PATH + Constant.LIST;
		}
	}

}
