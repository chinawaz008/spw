package com.spw.elife.staff.service;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.spw.elife.basics.bean.Lookup;
import com.spw.elife.basics.service.ImportService;
import com.spw.elife.common.Pager;
import com.spw.elife.staff.bean.Staff;
import com.spw.elife.staff.dao.StaffMapper;

/**
 * @author Administrator
 *
 */
@Service
public class StaffService  extends ImportService{
	private static final Logger log = Logger.getLogger(StaffService.class);
	@Resource
	private StaffMapper staffmapper;
	
	public static final String COST_FAIL ="0";
	public static final String COST_SUCCESS ="1";
	public static final String COST_Middle ="2";
	public static final String COST_RETURN ="3";//退款
	
	/**
     * 分页查询用户列表.
     *
     * @param lookup 用户查询参数.
     * @return 用户列表
     */
    public Pager<Staff> queryEntityList(Lookup lookup) {
        int total = staffmapper.countBusEntity(lookup);
        Pager<Staff> pager = new Pager<Staff>();
        pager.setPage(lookup.getPage());
        pager.setSize(lookup.getSize());
        pager.setTotal(total);
        @SuppressWarnings("unchecked")
		List<Staff> entities = pager.isOverflowed() ? Collections.EMPTY_LIST : staffmapper.selectBusEntityList(lookup);
        for (int i = 0; i < entities.size(); i++) {
		}
        pager.setElements(entities);
        return pager;
    }
    
    public Pager<Staff> queryEntityListNew(Lookup lookup) {
    	int total = staffmapper.countBusEntityNew(lookup);
    	Pager<Staff> pager = new Pager<Staff>();
    	pager.setPage(lookup.getPage());
    	pager.setSize(lookup.getSize());
    	pager.setTotal(total);
    	@SuppressWarnings("unchecked")
    	List<Staff> entities = pager.isOverflowed() ? Collections.EMPTY_LIST : staffmapper.selectBusEntityListNew(lookup);
    	for (int i = 0; i < entities.size(); i++) {
    	}
    	pager.setElements(entities);
    	return pager;
    }
    
}
