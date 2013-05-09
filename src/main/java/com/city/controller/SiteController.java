package com.city.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.city.action.ISiteAction;
import com.city.factories.HibernateDaoFactory;
import com.city.model.obj.MovieDetails;
import com.city.model.obj.Site;
import com.city.util.Constant;
import com.city.validator.SiteValidator;

/** 
 * SiteController Controller all request and response in web application.
 * @author santosh yevle
 */

@Controller
@SessionAttributes({"siteList"})
@RequestMapping(Constant.SITE)
public class SiteController {
	private static final String REQUESTMAPPING = Constant.SITE;
	// BELOW CODE CHAGE IN EACH MODLE
	// Just Replace Site string with your Object name.
	String PATH = REQUESTMAPPING;
	Site frmObject = null;

	SiteValidator validator = null;
	
	@Autowired
	ISiteAction siteAction;
	
	@Autowired
	public void setValidator(SiteValidator validator) {
		this.validator = validator;
	}

	Site getFromObject() {
		return new Site();
	}

	public SiteValidator getValidator() {
		return validator;
	}

	@RequestMapping(value = Constant.UPDATE, method = RequestMethod.POST)
	public String update(@ModelAttribute("frmObject") @Valid Site frmObject,
			BindingResult result, ModelMap model) {
		validator.validate(frmObject, result);
		if (result.hasErrors()) {
			model.put("requestMapping", REQUESTMAPPING);
			model.put("command", "update");
			model.put("readonly", "false");
			return PATH + Constant.ACTION;
		}
		
		siteAction.saveOrUpdate(frmObject);
		
		return Constant.REDIRECT + PATH + Constant.LIST;
	}

	@RequestMapping(value = Constant.ADD, method = RequestMethod.POST)
	public String add(@ModelAttribute("frmObject") @Valid Site frmObject,
			BindingResult result, ModelMap model) {

		validator.validate(frmObject, result);
		if (result.hasErrors()) {
			model.put("requestMapping", REQUESTMAPPING);
			model.put("command", "add");
			model.put("readonly", "false");
			return PATH + Constant.ACTION;
		}

		
		siteAction.save(frmObject);    

		return Constant.REDIRECT + PATH + Constant.LIST;
	}
	private String siteIdList() {
		List<Site> movieDetailsList = HibernateDaoFactory.getSiteDao().findAll();
		StringBuffer movieDetailsString = new StringBuffer();
		for (Site movieDetails : movieDetailsList) {
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
	private String nameList() {
		List<Site> movieDetailsList = HibernateDaoFactory.getSiteDao().findAll();
		StringBuffer movieDetailsString = new StringBuffer();
		for (Site movieDetails : movieDetailsList) {
			movieDetailsString.append("\"").append(movieDetails.getName())
					.append("\"").append(",");
		}
		if (movieDetailsString.length() == 0) {
			return movieDetailsString.toString();
		} else {
			return movieDetailsString.substring(0, movieDetailsString.length() - 1)
					.toString();
		}
	}
	// -----------------------------------------------------------------
	@RequestMapping(value = Constant.REPORT)
	public String report(Map<String, Object> map,@ModelAttribute("siteList") List<Site> siteList,SessionStatus status) {
		map.put("list", siteList);
		//don't uncomment below line because it clear session for movieDetailsList.
		//status.setComplete();
		return PATH + Constant.REPORT;
	}
	@RequestMapping(value = Constant.LIST, method = RequestMethod.POST)
	public String list(Map<String, Object> map,ModelMap model,@ModelAttribute("frmObject") Site frmObject) {
		// below line important it.
		if(frmObject.getId() !=null)
			map.put("id", frmObject.getId());
		if(frmObject.getName() != null)
			map.put("name", frmObject.getName());
		return Constant.REDIRECT + PATH + Constant.LIST;
	}
	@RequestMapping(value = Constant.LIST, method = RequestMethod.GET)
	public String filterList(Map<String, Object> map,ModelMap model,@ModelAttribute("frmObject") Site frmObject) {
		boolean readOnly = true;
		Long siteId = frmObject.getId();
		String name = frmObject.getName();
		List<Site> siteList = null;
		List<Criterion> criterionList = new ArrayList<Criterion>();
		if(siteId != null && siteId > 0){
			Criterion id = Restrictions.eq("id", siteId);
			criterionList.add(id);
		} 
		if(name != null && name.trim().length() > 0 ){
			Criterion siteName = Restrictions.like("name", name+"%").ignoreCase();
			criterionList.add(siteName);
		}
		if(criterionList.size() == 0){
			readOnly = false;
			siteList = HibernateDaoFactory.getSiteDao().findByCriteria();
		}else{
			siteList = HibernateDaoFactory.getSiteDao().findByCriteria(criterionList,10);
		}
		map.put("siteIdList", siteIdList());
		map.put("nameList", nameList());
		map.put("list", siteList);
		map.put("frmObject", frmObject);
		map.put("readonly", readOnly);
		map.put("requestMapping", REQUESTMAPPING);
		
		model.addAttribute("siteList", siteList);
		return PATH + Constant.LIST;
	}

	@RequestMapping(Constant.DELETED_BY_ID + "/{id}")
	public String delete(@PathVariable("id") Long id) {
		frmObject = getFromObject();
		
		siteAction.delete(id);
		
		return Constant.REDIRECT + PATH + Constant.LIST;
	}

	/*
	 * This method added new records.
	 */
	@RequestMapping(value = Constant.ADD, method = RequestMethod.GET)
	public String add(Map<String, Object> map) {
		frmObject = getFromObject();
		map.put("frmObject", frmObject);
		map.put("requestMapping", REQUESTMAPPING);
		map.put("fromName", "To Add New Site");
		map.put("action", Constant.ROOTPATH + PATH + Constant.ADD);
		map.put("command", "add");
		map.put("readonly", "false");
		return PATH + Constant.ACTION;
	}

	@RequestMapping(value = Constant.FIND + "/{command}/{id}", method = RequestMethod.GET)
	public String find(Map<String, Object> map, @PathVariable("id") Long id,
			@PathVariable("command") String command) {
		frmObject = getFromObject();
		frmObject = HibernateDaoFactory.getSiteDao().get(id);
		if (frmObject != null) {
			map.put("frmObject", frmObject);
			map.put("action", Constant.ROOTPATH + PATH + Constant.UPDATE);

			if (command.equalsIgnoreCase("update")) {
				map.put("requestMapping", REQUESTMAPPING);
				map.put("fromName", "To Update Site");
				map.put("command", "update");
				map.put("readonly", "false");
			} else if (command.equalsIgnoreCase("delete")) {
				map.put("requestMapping", REQUESTMAPPING);
				map.put("command", "delete");
				map.put("fromName", "To Delete Site");
				map.put("readonly", "true");
			} else {
				map.put("command", "get");
				map.put("fromName", "To View Site");
				map.put("readonly", "true");
			}
			return PATH + Constant.ACTION;

		} else {
			return Constant.REDIRECT + PATH + Constant.LIST;
		}
	}

}
