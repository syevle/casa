package com.city.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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
import com.city.model.obj.DvdLocation;
import com.city.model.obj.Genre;
import com.city.model.obj.MovieDetails;
import com.city.model.obj.Starcast;
import com.city.util.ApplicationUtil;
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
		SimpleDateFormat dateFormat = new SimpleDateFormat(Constant.DATEFORMAT);
		dateFormat.setLenient(false);

		// true passed to CustomDateEditor constructor means convert empty
		// String to null
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

	@RequestMapping(value = Constant.UPDATE, method = RequestMethod.POST)
	public String update(
			@ModelAttribute("frmObject") @Valid MovieDetails frmObject,
			BindingResult result, ModelMap map,HttpServletRequest request) {
		
		String h_starcastName1 = request.getParameter("h_starcastName1");
		String h_starcastName2 = request.getParameter("h_starcastName2");
		String h_starcastName3 = request.getParameter("h_starcastName3");
		String h_starcastName4 = request.getParameter("h_starcastName4");
		String h_starcastName5 = request.getParameter("h_starcastName5");
		String h_starcastName6 = request.getParameter("h_starcastName6");
		String h_director1 = request.getParameter("h_director1");
		String h_director2 = request.getParameter("h_director2");
		String h_director3 = request.getParameter("h_director3");
		String h_creator1 = request.getParameter("h_creator1");
		String h_creator2 = request.getParameter("h_creator2");
		String h_creator3 = request.getParameter("h_creator3");
		String h_genre1 = request.getParameter("h_genre1");
		String h_genre2 = request.getParameter("h_genre2");
		String h_genre3 = request.getParameter("h_genre3");
		String h_genreExtra = request.getParameter("h_genreExtra");
		String h_dvdLocation = request.getParameter("h_dvdLocation");
		
		
		
		frmObject.setStarcastName1(h_starcastName1);
		frmObject.setStarcastName2(h_starcastName2);
		frmObject.setStarcastName3(h_starcastName3);
		frmObject.setStarcastName4(h_starcastName4);
		frmObject.setStarcastName5(h_starcastName5);
		frmObject.setStarcastName6(h_starcastName6);
		frmObject.setDirector1(h_director1);
		frmObject.setDirector2(h_director2);
		frmObject.setDirector3(h_director3);
		frmObject.setCreator1(h_creator1);
		frmObject.setCreator2(h_creator2);
		frmObject.setCreator3(h_creator3);
		frmObject.setGenre1(h_genre1);
		frmObject.setGenre1(h_genre2);
		frmObject.setGenre1(h_genre3);
		frmObject.setGenreExtra(h_genreExtra);
		frmObject.setDvdLocation(h_dvdLocation);
		
		validator.validate(frmObject, result);
		if (result.hasErrors()) {
			map.put("requestMapping", REQUESTMAPPING);
			map.put("countryList", countryData());
			map.put("genreList", genreList());
			map.put("dvdLocationList", dvdLocationList());
			map.put("languageList", languagesData());
			map.put("dvdLocationList", dvdLocationList());
			map.put("command", "add");
			map.put("creatorList", creatorList());
			map.put("directorList", directorList());
			map.put("starcastList", starcastList());
			map.put("command", "update");
			map.put("readonly", "false");
			return PATH + Constant.ACTION;
		}
		frmObject.setMovPath(frmObject.getMovName()+".jpg")	;	
		movieDetailsAction.saveOrUpdate(frmObject);

		
		
		map.put("movieCodeList", movieCodeList());
		map.put("movieNameList", movieNameList());
		map.put("starcastList", starcastList());
		map.put("genreList", genreList());
		map.put("dvdLocationList", dvdLocationList());
		
		map.put("frmObject", frmObject);
		map.put("requestMapping", REQUESTMAPPING);
		
		
		return Constant.REDIRECT + PATH + Constant.LIST+"?id="+frmObject.getId();
	}

	@RequestMapping(value = Constant.ADD, method = RequestMethod.POST)
	public String add(
			@ModelAttribute("frmObject") @Valid MovieDetails frmObject,
			BindingResult result, ModelMap map,HttpServletRequest request) throws RuntimeException {
		String movId = frmObject.getId();
		MovieDetails movieDetails = HibernateDaoFactory.getMovieDetailsDao().get(movId);
		
		String h_starcastName1 = request.getParameter("h_starcastName1");
		String h_starcastName2 = request.getParameter("h_starcastName2");
		String h_starcastName3 = request.getParameter("h_starcastName3");
		String h_starcastName4 = request.getParameter("h_starcastName4");
		String h_starcastName5 = request.getParameter("h_starcastName5");
		String h_starcastName6 = request.getParameter("h_starcastName6");
		String h_director1 = request.getParameter("h_director1");
		String h_director2 = request.getParameter("h_director2");
		String h_director3 = request.getParameter("h_director3");
		String h_creator1 = request.getParameter("h_creator1");
		String h_creator2 = request.getParameter("h_creator2");
		String h_creator3 = request.getParameter("h_creator3");
		String h_genre1 = request.getParameter("h_genre1");
		String h_genre2 = request.getParameter("h_genre2");
		String h_genre3 = request.getParameter("h_genre3");
		String h_genreExtra = request.getParameter("h_genreExtra");
		String h_dvdLocation = request.getParameter("h_dvdLocation");
		
		
		
		frmObject.setStarcastName1(h_starcastName1);
		frmObject.setStarcastName2(h_starcastName2);
		frmObject.setStarcastName3(h_starcastName3);
		frmObject.setStarcastName4(h_starcastName4);
		frmObject.setStarcastName5(h_starcastName5);
		frmObject.setStarcastName6(h_starcastName6);
		frmObject.setDirector1(h_director1);
		frmObject.setDirector2(h_director2);
		frmObject.setDirector3(h_director3);
		frmObject.setCreator1(h_creator1);
		frmObject.setCreator2(h_creator2);
		frmObject.setCreator3(h_creator3);
		frmObject.setGenre1(h_genre1);
		frmObject.setGenre1(h_genre2);
		frmObject.setGenre1(h_genre3);
		frmObject.setGenreExtra(h_genreExtra);
		frmObject.setDvdLocation(h_dvdLocation);
		
		
		
		
		frmObject.setAvalStatus('Y');
		frmObject.setUplodReq('Y');
		
		
		
		validator.validate(frmObject, result);
		
		if(movieDetails != null){
			ObjectError error = new ObjectError("MovieDetails", "Movie must not be added. Moview Id already exit in library.");
			result.addError(error);
		}
		
		
		if (result.hasErrors()) {
			
			fillViewUpdateMap( map,frmObject );
			
			map.put("action", Constant.ROOTPATH + PATH + Constant.ADD);
			map.put("fromName", "To Add New Movie");
			map.put("countryList", countryData());
			map.put("genreList", genreList());
			map.put("dvdLocationList", dvdLocationList());
			map.put("languageList", languagesData());
			map.put("dvdLocationList", dvdLocationList());
			map.put("todayDate", ApplicationUtil.getTodayDate());
			map.put("creatorList", creatorList());
			map.put("directorList", directorList());
			map.put("starcastList", starcastList());
			map.put("disabled", "true");
			
			map.put("requestMapping", REQUESTMAPPING);
			map.put("command", "add");
			map.put("readonly", "false");
			return PATH + Constant.ACTION;
		}
		frmObject.setMovPath(frmObject.getMovName()+".jpg")	;
		movieDetailsAction.save(frmObject);
		return Constant.REDIRECT + PATH + Constant.ADD+"?id="+frmObject.getId();
	}
	
	
	// -----------------------------------------------------------------
	@RequestMapping(value = Constant.STARCASTNAMECHILD, method = RequestMethod.GET)
	public String starCastChild() {
		
		return PATH + Constant.STARCASTNAMECHILD;
	}
	@RequestMapping(value = Constant.CREATORCHILD, method = RequestMethod.GET)
	public String creatorChild() {
		
		return PATH + Constant.CREATORCHILD;
	}
	@RequestMapping(value = Constant.DIRECTORCHILD, method = RequestMethod.GET)
	public String directorChild() {
		
		return PATH + Constant.DIRECTORCHILD;
	}
	@RequestMapping(value = Constant.GENERCHILD, method = RequestMethod.GET)
	public String generChild() {
		
		return PATH + Constant.GENERCHILD;
	}
	@RequestMapping(value = Constant.SERCH, method = RequestMethod.GET)
	public String serch(Map<String, Object> map,ModelMap model,@ModelAttribute("frmObject") MovieDetails frmObject) {
		
		// below line important it.
		map.put("movieCodeList", movieCodeList());
		map.put("movieNameList", movieNameList());
		map.put("starcastList", starcastList());
		map.put("genreList", genreList());
		map.put("creatorList", creatorList());
		map.put("languageList", languagesData());
		map.put("dvdLocationList", dvdLocationList());
		
		map.put("frmObject", frmObject);
		map.put("requestMapping", REQUESTMAPPING);
		
		return PATH + Constant.SERCH;
	}
	@RequestMapping(value = Constant.LIST, method = RequestMethod.POST)
	public String list(Map<String, Object> map,ModelMap model,@ModelAttribute("frmObject") MovieDetails frmObject,HttpServletRequest request) {
		String id = request.getParameter("h_id");
		String movName = request.getParameter("h_movName");
		String starcastName = request.getParameter("h_starcastName");
		String genre = request.getParameter("h_genre");
		String creator = request.getParameter("h_creator");
		String languages = request.getParameter("h_languages");
		
		
		// below line important it.
				if(id !=null && id.length() > 0)
					map.put("id", id.trim());
				if(movName != null && movName.length() > 0)
					map.put("movName", movName.trim());
				if(starcastName !=null && starcastName.length() > 0)
					map.put("starcastName1", starcastName.trim());
				if(genre != null && genre.length() > 0)
					map.put("genre1", genre.trim());
				if(creator != null && creator.length() > 0)
					map.put("creator1", creator.trim());
				if(languages != null && languages.length() > 0)
					map.put("languages", languages.trim());				
		
		return Constant.REDIRECT + PATH + Constant.LIST;
	}
	@RequestMapping(value = Constant.LIST, method = RequestMethod.GET)
	public String filterList(Map<String, Object> map,ModelMap model,@ModelAttribute("frmObject") MovieDetails frmObject) {
		boolean readOnly = true;
		String movieCode = frmObject.getId();
		String movieName = frmObject.getMovName();
		String starcastName = frmObject.getStarcastName1();
		String genre = frmObject.getGenre1();
		String languages = frmObject.getLanguages();
		String creator = frmObject.getCreator1();
		List<MovieDetails> movieDetailsList = null;
		List<Criterion> criterionList = new ArrayList<Criterion>();
		
		if(movieCode != null && movieCode.trim().length() > 0 ){
			Criterion id = Restrictions.like("id", movieCode,MatchMode.START).ignoreCase();
			criterionList.add(id);
		} 
		if(movieName != null && movieName.trim().length() > 0 ){
			Criterion name = Restrictions.like("movName",movieName,MatchMode.ANYWHERE).ignoreCase();
			criterionList.add(name);
		}
		if(languages != null && languages.trim().length() > 0 ){
			Criterion movielanguages = Restrictions.like("languages",languages,MatchMode.ANYWHERE).ignoreCase();
			criterionList.add(movielanguages);
		}
		if(creator != null && creator.trim().length() > 0 ){
			Disjunction disjunction = Restrictions.disjunction();
			disjunction.add(Restrictions.like("creator1", creator,MatchMode.ANYWHERE).ignoreCase());
			disjunction.add(Restrictions.like("creator2", creator,MatchMode.ANYWHERE).ignoreCase());
			disjunction.add(Restrictions.like("creator3", creator,MatchMode.ANYWHERE).ignoreCase());
			
			criterionList.add(disjunction);
		}
		if(starcastName != null && starcastName.trim().length() > 0 ){
			Disjunction disjunction = Restrictions.disjunction();
			disjunction.add(Restrictions.like("starcastName1", starcastName,MatchMode.ANYWHERE).ignoreCase());
			disjunction.add(Restrictions.like("starcastName2", starcastName,MatchMode.ANYWHERE).ignoreCase());
			disjunction.add(Restrictions.like("starcastName3", starcastName,MatchMode.ANYWHERE).ignoreCase());
			disjunction.add(Restrictions.like("starcastName4", starcastName,MatchMode.ANYWHERE).ignoreCase());
			disjunction.add(Restrictions.like("starcastName5", starcastName,MatchMode.ANYWHERE).ignoreCase());
			disjunction.add(Restrictions.like("starcastName6", starcastName,MatchMode.ANYWHERE).ignoreCase());
			
			criterionList.add(disjunction);
		}
		if(genre != null && genre.trim().length() > 0 ){
			Disjunction disjunction = Restrictions.disjunction();
			disjunction.add(Restrictions.like("genre1", genre,MatchMode.ANYWHERE).ignoreCase());
			disjunction.add(Restrictions.like("genre2", genre,MatchMode.ANYWHERE).ignoreCase());
			disjunction.add(Restrictions.like("genre3", genre,MatchMode.ANYWHERE).ignoreCase());
			disjunction.add(Restrictions.like("genreExtra", genre,MatchMode.ANYWHERE).ignoreCase());
			criterionList.add(disjunction);
		}
		if(criterionList.size() == 0){
			readOnly = false;
			movieDetailsList = new ArrayList<MovieDetails>();
		}else{
			movieDetailsList = HibernateDaoFactory.getMovieDetailsDao().findByCriteria(criterionList,null,Order.desc("movName"));
		}
		//map.put("id", movieCode);
		map.put("movieCodeList", movieCodeList());
		map.put("movieNameList", movieNameList());
		map.put("starcastList", starcastList());
		map.put("genreList", genreList());
		map.put("dvdLocationList", dvdLocationList());
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
	public String add(Map<String, Object> map,HttpServletRequest request) {
		String movId = request.getParameter("id");
		if(movId !=null && movId.length() > 0){
			frmObject   = HibernateDaoFactory.getMovieDetailsDao().get(movId);
			map.put("movId", frmObject.getId());
		}else{
			frmObject = getFromObject();
			frmObject.setId("D"+nextMovieId().toString());
			frmObject.setAvalStatus('Y');
			frmObject.setUplodReq('Y');
		}
		
		map.put("frmObject", frmObject);
		map.put("requestMapping", REQUESTMAPPING);
		map.put("action", Constant.ROOTPATH + PATH + Constant.ADD);
		map.put("fromName", "To Add New Movie");
		map.put("countryList", countryData());
		map.put("genreList", genreList());
		map.put("dvdLocationList", dvdLocationList());
		map.put("languageList", languagesData());
		map.put("dvdLocationList", dvdLocationList());
		map.put("command", "add");
		map.put("todayDate", ApplicationUtil.getTodayDate());
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
				map.put("genreList", genreList());
				map.put("dvdLocationList", dvdLocationList());
				map.put("languageList", languagesData());
				map.put("dvdLocationList", dvdLocationList());
				map.put("creatorList", creatorList());
				map.put("directorList", directorList());
				map.put("starcastList", starcastList());
				
				fillViewUpdateMap( map,frmObject );
				
			} else if (command.equalsIgnoreCase("delete")) {
				map.put("requestMapping", REQUESTMAPPING);
				map.put("fromName", "To Delete Movie");
				map.put("command", "delete");
				map.put("readonly", "true");
				map.put("countryList", countryData());
				map.put("genreList", genreList());
				map.put("dvdLocationList", dvdLocationList());
				map.put("languageList", languagesData());
				map.put("dvdLocationList", dvdLocationList());
				
				
				
				fillViewUpdateMap( map,frmObject );
			} else {
				map.put("fromName", "Movie Details");
				map.put("requestMapping", REQUESTMAPPING);
				map.put("countryList", countryData());
				map.put("genreList", genreList());
				map.put("dvdLocationList", dvdLocationList());
				map.put("languageList", languagesData());
				map.put("dvdLocationList", dvdLocationList());
				map.put("command", "get");
				map.put("readonly", "true");
				map.put("disabled", "true");
				map.put("movId", frmObject.getId());
				
				
				
				
				fillViewUpdateMap( map,frmObject );
			}
			return PATH + Constant.ACTION;

		} else {
			return Constant.REDIRECT + PATH + Constant.LIST;
		}
	}
	public void fillViewUpdateMap(Map<String, Object> map,MovieDetails frmObject ) {
		map.put("h_starcastName1", frmObject.getStarcastName1());
		map.put("h_starcastName2", frmObject.getStarcastName2());
		map.put("h_starcastName3", frmObject.getStarcastName3());
		map.put("h_starcastName4", frmObject.getStarcastName4());
		map.put("h_starcastName5", frmObject.getStarcastName5());
		map.put("h_starcastName6", frmObject.getStarcastName6());
		
		map.put("h_director1", frmObject.getDirector1());
		map.put("h_director2",  frmObject.getDirector2());
		map.put("h_director3",  frmObject.getDirector3());
		
		map.put("h_creator1", frmObject.getCreator1());
		map.put("h_creator2", frmObject.getCreator2());
		map.put("h_creator3", frmObject.getCreator3());
		
		map.put("h_genre1", frmObject.getGenre1());
		map.put("h_genre2", frmObject.getGenre2());
		map.put("h_genre3", frmObject.getGenre3());
		map.put("h_genreExtra", frmObject.getGenreExtra());
		
		map.put("h_dvdLocation", frmObject.getDvdLocation());
		
	
	}
	protected List<String> countryData() {
		List<String> list = new LinkedList<String>();
		list.add("");
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
		list.addAll(countryList);
		return list;
	}
	protected List<String> languagesData() {
		List<String> list = new LinkedList<String>();
		list.add("");
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
		list.addAll(languages);
		return list;
	}
	
	private Map<String,String> genreList() {
		Map<String,String> mySortedMap = new TreeMap<String,String>();
		List<Criterion> criterionList = new ArrayList<Criterion>();
		List<Genre> genreList = HibernateDaoFactory.getGenreDao().findByCriteria(criterionList,null,Order.asc("genreName"));
		Map<String,String> map = new LinkedHashMap<String,String>();
		map.put("", "");
			for (Genre genre : genreList) {
				if(genre.getGenreName() != null)
				mySortedMap.put(genre.getGenreName().toLowerCase(), genre.getGenreName());
			}
			map.putAll(mySortedMap);
		return map;
	}
	
	private  Map<String,String> dvdLocationList() {
		List<Criterion> criterionList = new ArrayList<Criterion>();
		List<DvdLocation> dvdLocationList = HibernateDaoFactory.getDvdLocationDao().findByCriteria(criterionList,null,Order.asc("dvdLocation"));
		Map<String,String> mySortedMap = new TreeMap<String,String>();
		Map<String,String> map = new LinkedHashMap<String,String>();
		map.put("", "");
		for (DvdLocation dvdLocation : dvdLocationList) {
			if(dvdLocation.getDvdLocation() != null)
				mySortedMap.put(dvdLocation.getDvdLocation().toLowerCase(), dvdLocation.getDvdLocation());
			}
		map.putAll(mySortedMap);
		return map;
	}
	
	private  Map<String,String> creatorList() {
		List<Criterion> criterionList = new ArrayList<Criterion>();
		List<Creator> creatorList   = HibernateDaoFactory.getCreatorDao().findByCriteria(criterionList,null,Order.asc("creatorName"));
		Map<String,String> mySortedMap = new TreeMap<String,String>();
		Map<String,String> map = new LinkedHashMap<String,String>();
		map.put("", "");
			for (Creator creator : creatorList) {
				if(creator.getCreatorName() != null)
				mySortedMap.put(creator.getCreatorName().toLowerCase(), creator.getCreatorName());
			}
			map.putAll(mySortedMap);
			return map;
	}
	
	private  Map<String,String> directorList() {
		List<Criterion> criterionList = new ArrayList<Criterion>();
		List<Director> directorList = HibernateDaoFactory.getDirectorDao().findByCriteria(criterionList,null,Order.asc("directorName"));
		Map<String,String> mySortedMap = new TreeMap<String,String>();
		Map<String,String> map = new LinkedHashMap<String,String>();
		map.put("", "");
			for (Director director : directorList) {
				if(director.getDirectorName() != null)
				//list.add(director.getDirectorName().trim());
				mySortedMap.put(director.getDirectorName().toLowerCase(), director.getDirectorName());
			}
			map.putAll(mySortedMap);
		return map;
	}
	
	private  Map<String,String> starcastList() {
		List<Criterion> criterionList = new ArrayList<Criterion>();
		List<Starcast> starcastList = HibernateDaoFactory.getStarcastDao().findByCriteria(criterionList,null,Order.asc("starcastName"));
		Map<String,String> mySortedMap = new TreeMap<String,String>();
		Map<String,String> map = new LinkedHashMap<String,String>();
		map.put("", "");
			for (Starcast starcast : starcastList) {
				if(starcast.getStarcastName() != null)
					mySortedMap.put(starcast.getStarcastName().toLowerCase(), starcast.getStarcastName());
			}
			map.putAll(mySortedMap);
		return map;
	}
	
	
	private  Map<String,String> movieCodeList() {
		List<Criterion> criterionList = new ArrayList<Criterion>();
		List<MovieDetails> movieDetailsList = HibernateDaoFactory.getMovieDetailsDao().findByCriteria(criterionList,null,Order.asc("id"));
		Map<String,String> mySortedMap = new TreeMap<String,String>();
		Map<String,String> map = new LinkedHashMap<String,String>();
		map.put("", "");
			for (MovieDetails movieDetails : movieDetailsList) {
				if(movieDetails.getId() != null)
					mySortedMap.put(movieDetails.getId().toLowerCase(), movieDetails.getId());
			}
			map.putAll(mySortedMap);
		return map;
	}
	
	private  Map<String,String> movieNameList() {
		List<Criterion> criterionList = new ArrayList<Criterion>();
		List<MovieDetails> movieDetailsList = HibernateDaoFactory.getMovieDetailsDao().findByCriteria(criterionList,null,Order.asc("movName"));
		Map<String,String> mySortedMap = new TreeMap<String,String>();
		Map<String,String> map = new LinkedHashMap<String,String>();
		map.put("", "");
			for (MovieDetails movieDetails : movieDetailsList) {
				if(movieDetails.getMovName() != null)
					mySortedMap.put(movieDetails.getMovName().toLowerCase(), movieDetails.getMovName());
			}
			map.putAll(mySortedMap);
			return map;
	}

	public Integer nextMovieId(){
		Integer id = null;
		List<Object> list = HibernateDaoFactory.getMovieDetailsDao().getNativeQuery("select TRAN_SEQ.nextval as IDs from dual");
		for(Object row : list){ 
			id = new Integer(row.toString());
		}
		return id;
	}
	
}
