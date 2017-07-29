package com.spw.elife.company.service;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.spw.elife.basics.service.ImportService;
import com.spw.elife.company.bean.EmployeeEntity;
import com.spw.elife.company.dao.EmployeeMapper;

/**
 * @author Administrator
 *
 */
@Service
public class EmployeeService  extends ImportService{
	private static final Logger log = Logger.getLogger(EmployeeService.class);
	@Resource
	private EmployeeMapper employeeMapper;
	public List<EmployeeEntity> findEmployeeByName(String username) {
		
		return employeeMapper.findEmployeeByName(username);
	}
	
    
}
