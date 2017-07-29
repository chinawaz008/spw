package com.spw.elife.staff.controller;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spw.elife.common.AbstractController;
import com.spw.elife.mobile.login.dao.MobileLoginMapper;
import com.spw.elife.staff.dao.StaffMapper;
import com.spw.elife.staff.service.StaffService;

/**
 * 保代人员controller
 * @author Administrator
 * 
 */
@Controller
@RequestMapping
public class StaffController extends AbstractController {
	@Resource
	private StaffService staffservice;
	@Resource
	private StaffMapper staffMapper;
	@Resource
	private MobileLoginMapper mobileLoginMapper;
	
	private Logger log = Logger.getLogger(StaffController.class);

	
}
