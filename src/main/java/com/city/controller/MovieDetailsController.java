package com.city.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.validation.Valid;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
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
			map.put("countryList", countryData());
			map.put("genreList", genreData());
			map.put("languageList", languagesData());
			map.put("command", "add");
			map.put("creatorList", creatorList());
			map.put("directorList", directorList());
			map.put("starcastList", starcastList());
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
		frmObject.setAvalStatus('Y');
		frmObject.setUplodReq('Y');
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
	private String genreAllData() {
		StringBuffer generString = new StringBuffer();
		for (String gener : genreData()) {
			generString.append("\"").append(gener.toString())
					.append("\"").append(",");
		}
		if (generString.length() == 0) {
			return generString.toString();
		} else {
			return generString.substring(0, generString.length() - 1)
					.toString();
		}
	}
/*protected String genreAllData() {
		
		StringBuffer generString = new StringBuffer();
		for (String gener : genreData()) {
			generString.append("\"").append(gener).append("\"").append(",");
		}
		if (generString.length() == 0) {
			return generString.toString();
		} else {
			return generString.substring(0, generString.length() - 1)
					.toString();
		}
	}*/
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
		if(frmObject.getId() !=null)
			map.put("id", frmObject.getId());
		if(frmObject.getMovName() != null)
			map.put("movName", frmObject.getMovName());
		if(frmObject.getStarcastName1() !=null)
			map.put("starcastName1", frmObject.getStarcastName1());
		if(frmObject.getGenre1() != null)
			map.put("genre1", frmObject.getGenre1());
		return Constant.REDIRECT + PATH + Constant.LIST;
	}
	@RequestMapping(value = Constant.LIST, method = RequestMethod.GET)
	public String filterList(Map<String, Object> map,ModelMap model,@ModelAttribute("frmObject") MovieDetails frmObject) {
		boolean readOnly = true;
		String movieCode = frmObject.getId();
		String movieName = frmObject.getMovName();
		String starcastName = frmObject.getStarcastName1();
		String genre = frmObject.getGenre1();
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
		if(starcastName != null && starcastName.trim().length() > 0 ){
			Disjunction disjunction = Restrictions.disjunction();
			disjunction.add(Restrictions.like("starcastName1", starcastName+"%").ignoreCase());
			disjunction.add(Restrictions.like("starcastName2", starcastName+"%").ignoreCase());
			disjunction.add(Restrictions.like("starcastName3", starcastName+"%").ignoreCase());
			disjunction.add(Restrictions.like("starcastName4", starcastName+"%").ignoreCase());
			disjunction.add(Restrictions.like("starcastName5", starcastName+"%").ignoreCase());
			disjunction.add(Restrictions.like("starcastName6", starcastName+"%").ignoreCase());
			criterionList.add(disjunction);
		}
		if(genre != null && genre.trim().length() > 0 ){
			Disjunction disjunction = Restrictions.disjunction();
			disjunction.add(Restrictions.like("genre1", genre+"%").ignoreCase());
			disjunction.add(Restrictions.like("genre2", genre+"%").ignoreCase());
			disjunction.add(Restrictions.like("genre3", genre+"%").ignoreCase());
			disjunction.add(Restrictions.like("genreExtra", genre+"%").ignoreCase());
			criterionList.add(disjunction);
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
		map.put("starcastList", starcastList());
		map.put("genreAllList", genreAllData());
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
		map.put("countryList", countryData());
		map.put("genreList", genreData());
		map.put("languageList", languagesData());
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
				map.put("countryList", countryData());
				map.put("genreList", genreData());
				map.put("languageList", languagesData());
				map.put("creatorList", creatorList());
				map.put("directorList", directorList());
				map.put("starcastList", starcastList());
			} else if (command.equalsIgnoreCase("delete")) {
				map.put("requestMapping", REQUESTMAPPING);
				map.put("fromName", "To Delete Movie");
				map.put("command", "delete");
				map.put("readonly", "true");
				map.put("countryList", countryData());
				map.put("genreList", genreData());
				map.put("languageList", languagesData());
			} else {
				map.put("fromName", "Movie Details");
				map.put("countryList", countryData());
				map.put("genreList", genreData());
				map.put("languageList", languagesData());
				map.put("command", "get");
				map.put("readonly", "true");
				map.put("disabled", "true");
			}
			return PATH + Constant.ACTION;

		} else {
			return Constant.REDIRECT + PATH + Constant.LIST;
		}
	}
	protected List<String> countryData() {
		List<String> list = new LinkedList<String>();
		list.add("SELECT");
		Set<String> countryList = new TreeSet<String>();
		countryList.add("Afghanistan");
		countryList.add("Italy");
		countryList.add("Argentina");
		countryList.add("Austria");
		countryList.add("Belqium");
		countryList.add("Bolivia");
		countryList.add("Bosnia");
		countryList.add("Brazil");
		countryList.add("Canada");
		countryList.add("China");
		countryList.add("Colombia");
		countryList.add("Czech Republic");
		countryList.add("Denmark");
		countryList.add("Finland");
		countryList.add("France");
		countryList.add("Germany");
		countryList.add("Greece");
		countryList.add("Hong Kong");
		countryList.add("Hungary");
		countryList.add("India");
		countryList.add("Iran");
		countryList.add("Iraq");
		countryList.add("Ireland");
		countryList.add("Israel");
		countryList.add("Italy");
		countryList.add("Japan");
		countryList.add("Korea");
		countryList.add("Mexico");
		countryList.add("Netherlands");
		countryList.add("New Zealand");
		countryList.add("Norway");
		countryList.add("Palestine");
		countryList.add("Peru");
		countryList.add("Philippines");
		countryList.add("Poland");
		countryList.add("Romania");
		countryList.add("Russia");
		countryList.add("South Africa");
		countryList.add("South Korea");
		countryList.add("Spain");
		countryList.add("Sweden");
		countryList.add("Taiwan");
		countryList.add("Thailand");
		countryList.add("Turkey");
		countryList.add("UK");
		countryList.add("USA");
		//countryList.add("-----------------------------------------------");
		list.addAll(countryList);
		return list;
	}
	
	protected List<String> genreData() {
		List<String> list = new LinkedList<String>();
		list.add("SELECT");
		Set<String> genreList = new TreeSet<String>();
		genreList.add("Action");
		genreList.add("Adventure");
		genreList.add("Drama");
		genreList.add("History");
		genreList.add("Romance");
		genreList.add("Thriller");
		genreList.add("War");
		genreList.add("Family");
		genreList.add("Fantasy");
		genreList.add("Comedy");
		genreList.add("Animation");
		genreList.add("Horror");
		genreList.add("Sci-Fi");
		genreList.add("Musical");
		genreList.add("Western");
		genreList.add("Sport");
		genreList.add("Biography");
		genreList.add("Mystery");
		genreList.add("Documentary");
		genreList.add("Film-Noir");
		genreList.add("TV Series");
		genreList.add("Ray Lawrence");
		genreList.add("Reality-TV");
		genreList.add("TV mini-series");
		genreList.add("Talk Show");
		genreList.add("Independent");
		//genreList.add("-----------------------------------------------");
		list.addAll(genreList);
		return list;
	}
	protected List<String> languagesData() {
		List<String> list = new LinkedList<String>();
		list.add("SELECT");
		Set<String> languages = new TreeSet<String>();
		languages.add("Afrikaans");
		languages.add("Arabic");
		languages.add("Bosnian");
		languages.add("Cantonese");
		languages.add("Czech");
		languages.add("Danish");
		languages.add("Dutch");
		languages.add("English");
		languages.add("Filipino");
		languages.add("Finnish");
		languages.add("French");
		languages.add("Georgian");
		languages.add("German");
		languages.add("Greek");
		languages.add("Hebrew");
		languages.add("Hindi");
		languages.add("Hungarian");
		languages.add("Italian");
		languages.add("Japanese");
		languages.add("Korean");
		languages.add("Kurdish");
		languages.add("Mandarin");
		languages.add("Marathi");
		languages.add("Maya");
		languages.add("Norwegian");
		languages.add("Pashtu");
		languages.add("Persian");
		languages.add("Polish");
		languages.add("Portuguese");
		languages.add("Romanian");
		languages.add("Russian");
		languages.add("Spanish");
		languages.add("Swedish");
		languages.add("Taiwanese");
		languages.add("Thai");
		languages.add("Turkish");
		//languages.add("-----------------------------------------------");
		list.addAll(languages);
		return list;
	}
	
	/*protected List<String> movieCodeList() {
		List<String> list = new LinkedList<String>();
		list.add("SELECT");
		Set<String> languages = new TreeSet<String>();
		List<MovieDetails> movieDetailsList = HibernateDaoFactory.getMovieDetailsDao().findAll();	
		for (MovieDetails movieDetails : movieDetailsList) {
			languages.add(movieDetails.getId());
		}
		list.addAll(languages);
		return list;
	}
	
	protected List<String> movieNameList() {
		List<String> list = new LinkedList<String>();
		list.add("SELECT");
		Set<String> languages = new TreeSet<String>();
		List<MovieDetails> movieDetailsList = HibernateDaoFactory.getMovieDetailsDao().findAll();	
		for (MovieDetails movieDetails : movieDetailsList) {
			languages.add(movieDetails.getMovName());
		}
		list.addAll(languages);
		return list;
	}*/
	/*private List<String> starcastAllList() {
		
		List<String> list = new LinkedList<String>();
		list.add("SELECT");
		Set<String> languages = new TreeSet<String>();
		List<Starcast> starcastList = HibernateDaoFactory.getStarcastDao().findAll();
		for (Starcast starcast : starcastList) {
			languages.add(starcast.getStarcastName());
		}
		list.addAll(languages);
		return list;
	}*/
}
