package com.spw.elife.basics.service;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.spw.elife.staff.dao.StaffMapper;


/**
 * 共通方法service
 *
 * @author lip
 */
@Service
public class GeneralService {
	private static final Logger log = Logger.getLogger(GeneralService.class);
	@Resource
	private StaffMapper staffMapper;
	
}
