package com.spw.elife.mobile.schedule.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.spw.elife.mobile.common.Response;
import com.spw.elife.mobile.schedule.bean.Schedule;
import com.spw.elife.mobile.schedule.dao.ScheduleMapper;


@Service
public class ScheduleService {
	private static final int Opration_Success = 1;
	@Resource
	private ScheduleMapper scheduleMapper; 
	   
	private Logger log = Logger.getLogger(ScheduleService.class); 
	
	public Response queryDetail(String staffid,String scheduleId){ 
		try{  
			Schedule schedule = scheduleMapper.getById(staffid, scheduleId);   
			if(schedule != null){   
				Response rp  = Response.SUCCESS;
				rp.setData(schedule); 
				return rp;
			} else { 
				return Response.FAIL;
			}
		}catch(Exception e){ 
			log.error("查询日程细节异常:"+e);
			return Response.SERVER_ERROR;
		}
	} 
	
	public Response saveSchedule(Schedule schedule){  
		try{  
			log.info("员工ID号为:" + schedule.getStaffId() + "的员工增加了一个日程");
			int flag = scheduleMapper.insertEntity(schedule); 
			if(flag == Opration_Success){ 
				return Response.SUCCESS;
			} else { 
				return Response.FAIL;
			}
		}catch(Exception e){ 
			log.error("增加日程异常:"+e);
			return Response.SERVER_ERROR;
		}
	}  
	
	public Response deleteSchedule(String scheduleid,String staffid){ 
		try{  
			log.info("员工ID号为:" + staffid + "的员工删除了一个日程");
			int flag = scheduleMapper.deleteEntity(scheduleid,staffid);  
			if(flag == Opration_Success){ 
				return Response.SUCCESS;
			}else{ 
				return Response.FAIL;
			}
		}catch(Exception e){ 
			log.error("remove-> 日程删除异常："+e);  
			return Response.SERVER_ERROR;
		}
	} 
	 
	public Response updateSchedule(Schedule schedule){  
		try{  
			log.info("员工ID号为:" + schedule.getStaffId() + "的员工修改了一个日程");
			int flag = scheduleMapper.updateEntiy(schedule); 
			if(flag == Opration_Success){ 
				return Response.SUCCESS;
			}else{ 
				return Response.FAIL;
			}
		}catch(Exception e){ 
			log.error("remove-> 日程修改异常："+e);  
			return Response.SERVER_ERROR;
		}
	}
	
    public List<Schedule> queryScheduleList(String staffid) { 
    	Date d = new Date(); 
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
    	String date = sdf.format(d); 
    	List<Schedule> entities = scheduleMapper.queryScheduleList(staffid,date); 
        return entities;
    } 

}
