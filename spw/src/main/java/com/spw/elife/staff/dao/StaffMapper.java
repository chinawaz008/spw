package com.spw.elife.staff.dao;

import java.util.List;

import com.spw.elife.basics.bean.Lookup;
import com.spw.elife.staff.bean.Staff;
import com.spw.elife.common.DaoMapper;

/**
 * @author Administrator
 * 
 */
@DaoMapper
public interface StaffMapper {
	/**
	 * 查询业务员相关信息
	 * @param lookup
	 * @return
	 */
	public int countBusEntity(Lookup lookup);
	public List<Staff> selectBusEntityList(Lookup lookup);
	public int countBusEntityNew(Object obj);

	public List<Staff> selectBusEntityListNew(Object obj);	
	//获得版本号app
	public String selectVersion();
}
