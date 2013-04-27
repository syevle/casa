package com.city.action.impl;

/** 
 * All crud operation implement here.
 * following coding stander maintain transeciton in appliction.  
 * @author santosh yevle
 */


import org.springframework.stereotype.Service;

import com.city.action.ISiteAction;
import com.city.model.obj.Site;
import com.city.services.data.impl.DataLayerImpl;

@Service
public class SiteActionImpl implements ISiteAction {
	@Override
	public void delete(Long id) {
    	Site frmObject = DataLayerImpl.getInstance().getSite(id);
    	DataLayerImpl.getInstance().delete(frmObject);
		DataLayerImpl.getInstance().flushSession(); // flush+evict from cache to make sure we hit the DB next 
		DataLayerImpl.getInstance().evict(frmObject);
	}
	@Override
	public Site saveOrUpdate(Site frmObject) {
    	
    	DataLayerImpl.getInstance().saveOrUpdate(frmObject);
		DataLayerImpl.getInstance().flushSession(); // flush+evict from cache to make sure we hit the DB next 
		DataLayerImpl.getInstance().evict(frmObject);
		
		return frmObject;
	}
	@Override
	public Site save(Site frmObject) {
    	DataLayerImpl.getInstance().save(frmObject);
		DataLayerImpl.getInstance().flushSession(); // flush+evict from cache to make sure we hit the DB next 
		DataLayerImpl.getInstance().evict(frmObject);
		
		return frmObject;     
	}

}
