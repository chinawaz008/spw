package com.spw.elife.mobile.schedule.controller;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spw.elife.mobile.common.Response;
import com.spw.elife.mobile.schedule.bean.Schedule;
import com.spw.elife.mobile.schedule.service.ScheduleService;
/**
 * 
 * 日程Controller
 * 
 * @author Administrator
 *
 */

@Controller 
@RequestMapping
public class ScheduleController{ 
	
	@Resource
	private ScheduleService scheduleService;
	/****************************************************APP端后台*******************************************************************************************************************/
	/**

	 * @param 日程列表查询
	 * @return
	 * @throws 
	 */
	@RequestMapping(value="/schedule/queryScheduleList" ,method = RequestMethod.POST) 
	@ResponseBody
	public void queryScheduleList(HttpServletRequest request,HttpServletResponse response) throws IOException{
				  
			String staffid  = request.getParameter("staffid");
			if(StringUtils.isEmpty(staffid)){ 
				throw new RuntimeException("员工id不能为空");
			}
        	List<Schedule> scheduleList = scheduleService.queryScheduleList(staffid); 
	
			JSONObject jb = new JSONObject();
			jb.accumulate("result", "1"); 
			jb.accumulate("message","成功");
			jb.accumulate("list",scheduleList); 	
			response.getWriter().print(jb);
	}
	
	/**
	 * 添加
	 * @param Schedule
	 * @return 
	 * @throws Exception  
	 */
	@RequestMapping(value="/schedule/saveSchedule",method = RequestMethod.POST)
	@ResponseBody
	public void saveSchedule(HttpServletResponse response,HttpServletRequest request) throws IOException{
		response.setContentType("text/html;charset=GBK");//解决中文乱码
		response.setCharacterEncoding("utf-8");  
		JSONObject jb = new JSONObject();
			String staffid  = request.getParameter("staffid");  
			if(StringUtils.isEmpty(staffid)){ 
				throw new RuntimeException("员工id不能为空");
			}
			String type = request.getParameter("type");  
			if(StringUtils.isEmpty(type)){ 
				throw new RuntimeException("日程类型不能为空");
			}
			String name = request.getParameter("name");  
			if(StringUtils.isEmpty(name)){ 
				throw new RuntimeException("日程名称不能为空");
			} 
			String startTime = request.getParameter("start_time");  
			if(StringUtils.isEmpty(startTime)){ 
				throw new RuntimeException("日程开始时间不能为空");
			}
			String endTime = request.getParameter("end_time");  
			if(StringUtils.isEmpty(endTime)){ 
				throw new RuntimeException("日程结束时间不能为空");
			}
			String partake = request.getParameter("partake");  
			if(StringUtils.isEmpty(partake)){ 
				throw new RuntimeException("参与人不能为空");
			}
			String note = request.getParameter("note"); 
			Schedule schedule = new Schedule(); 
			schedule.setStaffId(staffid); 
			schedule.setType(type); 
			schedule.setName(name); 
			schedule.setStartTime(startTime); 
			schedule.setEndTime(endTime); 
			schedule.setPartake(partake); 
			schedule.setNote(note); 
			schedule.setIsDel("0");
        	Response rp = scheduleService.saveSchedule(schedule);  
    		jb.accumulate("result", rp.getCode());
    		jb.accumulate("message", rp.getMessage()); 
    		response.getWriter().print(jb);
	}
	/**
	 * 
	 * @param staffid
	 * @param schedule
	 * @return schedule
	 * @throws Exception
	 */
	@RequestMapping(value="/schedule/queryDetail",method = RequestMethod.POST)
	@ResponseBody
	public void queryDetail(HttpServletResponse response,HttpServletRequest request) throws IOException{
		response.setContentType("text/html;charset=GBK");//解决中文乱码
		response.setCharacterEncoding("utf-8");  
		JSONObject jb = new JSONObject();
			String id = request.getParameter("scheduleid"); 
			if(StringUtils.isEmpty(id)){ 
				throw new RuntimeException("id不能为空");
			}
			String staffid  = request.getParameter("staffid");  
			if(StringUtils.isEmpty(staffid)){ 
				throw new RuntimeException("员工id不能为空");
			}
        	Response rp = scheduleService.queryDetail(staffid, id); 
			jb.accumulate("result", rp.getCode()); 
			jb.accumulate("message",rp.getMessage()); 
			jb.accumulate("schedule",rp.getData());
		response.getWriter().print(jb);
	} 
	
	/**
	 * 修改
	 * @param Schedule
	 * @return 
	 * @throws Exception  
	 */
	@RequestMapping(value="/schedule/updateSchedule",method = RequestMethod.POST)
	@ResponseBody
	public void updateSchedule(HttpServletResponse response,HttpServletRequest request) throws IOException{
		response.setContentType("text/html;charset=GBK");//解决中文乱码
		response.setCharacterEncoding("utf-8");  
		JSONObject jb = new JSONObject();
			String id = request.getParameter("scheduleid"); 
			if(StringUtils.isEmpty(id)){ 
				throw new RuntimeException("id不能为空");
			}
			String staffid  = request.getParameter("staffid");  
			if(StringUtils.isEmpty(staffid)){ 
				throw new RuntimeException("员工id不能为空");
			}
			String type = request.getParameter("type");  
			if(StringUtils.isEmpty(type)){ 
				throw new RuntimeException("日程类型不能为空");
			}
			String name = request.getParameter("name");  
			if(StringUtils.isEmpty(name)){ 
				throw new RuntimeException("日程名称不能为空");
			} 
			String startTime = request.getParameter("start_time");  
			if(StringUtils.isEmpty(startTime)){ 
				throw new RuntimeException("日程开始时间不能为空");
			}
			String endTime = request.getParameter("end_time");  
			if(StringUtils.isEmpty(endTime)){ 
				throw new RuntimeException("日程结束时间不能为空");
			}
			String partake = request.getParameter("partake");  
			if(StringUtils.isEmpty(partake)){ 
				throw new RuntimeException("参与人不能为空");
			}
			String note = request.getParameter("note"); 
			Schedule schedule = new Schedule();  
			schedule.setId(id);
			schedule.setStaffId(staffid);
			schedule.setType(type); 
			schedule.setName(name); 
			schedule.setStartTime(startTime); 
			schedule.setEndTime(endTime); 
			schedule.setPartake(partake); 
			schedule.setNote(note); 
        	Response rp  = scheduleService.updateSchedule(schedule); 
			jb.accumulate("result", rp.getCode()); 
			jb.accumulate("message",rp.getMessage()); 
		response.getWriter().print(jb);
	}
	
	
	/**
	 * 删除
	 * @param Schedule
	 * @return 
	 * @throws Exception  
	 */
	@RequestMapping(value="/schedule/deleteSchedule",method = RequestMethod.POST)
	@ResponseBody
	public void deleteSchedule(HttpServletResponse response,HttpServletRequest request) throws IOException{
		response.setContentType("text/html;charset=GBK");//解决中文乱码
		response.setCharacterEncoding("utf-8");  
		JSONObject jb = new JSONObject();
			String scheduleid = request.getParameter("scheduleid"); 
			if(StringUtils.isEmpty(scheduleid)){ 
				throw new RuntimeException("id不能为空");
			}
			String staffid  = request.getParameter("staffid");  
			if(StringUtils.isEmpty(staffid)){ 
				throw new RuntimeException("员工id不能为空");
			}
        	Response rp = scheduleService.deleteSchedule(scheduleid,staffid); 
			jb.accumulate("result", rp.getCode()); 
			jb.accumulate("message",rp.getMessage()); 
		response.getWriter().print(jb);
	}

}
