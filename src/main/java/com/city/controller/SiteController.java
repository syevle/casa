package com.city.controller;

import java.math.BigDecimal;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.city.action.ISiteAction;
import com.city.factories.HibernateDaoFactory;
import com.city.model.obj.Site;
import com.city.util.Constant;
import com.city.validator.SiteValidator;

/** 
 * SiteController Controller all request and response in web application.
 * @author santosh yevle
 */

@Controller
@RequestMapping(Constant.SITE)
public class SiteController {
	// BELOW CODE CHAGE IN EACH MODLE
	// Just Replace Site string with your Object name.
	String PATH = Constant.SITE;
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
			model.put("requestMapping", Constant.SITE);
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
			model.put("requestMapping", Constant.SITE);
			model.put("command", "add");
			model.put("readonly", "false");
			return PATH + Constant.ACTION;
		}

		
		siteAction.save(frmObject);    

		return Constant.REDIRECT + PATH + Constant.LIST;
	}

	// -----------------------------------------------------------------
	@RequestMapping(value = Constant.LIST, method = RequestMethod.GET)
	public String list(Map<String, Object> map) {
		map.put("list", HibernateDaoFactory.getSiteDao().findByCriteria());
		map.put("requestMapping", Constant.SITE);
		return PATH + Constant.LIST;
	}

	@RequestMapping(Constant.DELETED_BY_ID + "/{id}")
	public String delete(@PathVariable("id") BigDecimal id) {
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
		map.put("requestMapping", Constant.SITE);
		map.put("action", Constant.ROOTPATH + PATH + Constant.ADD);
		map.put("command", "add");
		map.put("readonly", "false");
		return PATH + Constant.ACTION;
	}

	@RequestMapping(value = Constant.FIND + "/{command}/{id}", method = RequestMethod.GET)
	public String find(Map<String, Object> map, @PathVariable("id") BigDecimal id,
			@PathVariable("command") String command) {
		frmObject = getFromObject();
		frmObject = HibernateDaoFactory.getSiteDao().get(id);
		if (frmObject != null) {
			map.put("frmObject", frmObject);
			map.put("action", Constant.ROOTPATH + PATH + Constant.UPDATE);

			if (command.equalsIgnoreCase("update")) {
				map.put("requestMapping", Constant.SITE);
				map.put("command", "update");
				map.put("readonly", "false");
			} else if (command.equalsIgnoreCase("delete")) {
				map.put("requestMapping", Constant.SITE);
				map.put("command", "delete");
				map.put("readonly", "true");
			} else {
				map.put("command", "get");
				map.put("readonly", "true");
			}
			return PATH + Constant.ACTION;

		} else {
			return Constant.REDIRECT + PATH + Constant.LIST;
		}
	}

}
