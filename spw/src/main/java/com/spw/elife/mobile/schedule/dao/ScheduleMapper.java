package com.spw.elife.mobile.schedule.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spw.elife.common.DaoMapper;
import com.spw.elife.mobile.schedule.bean.Schedule;

@DaoMapper
public interface ScheduleMapper {
	
	public int countEntity(Object objt); 
	
	public int insertEntity(Schedule schedule); 
	
	public  int deleteEntity(@Param(value="scheduleid")String scheduleid,@Param(value="staffid")String staffid);
	
	public  int updateEntiy(Schedule schedule); 
	
	public List<Schedule> queryScheduleList(@Param(value="staffid")String staffid,@Param(value="date")String date); 
	
	public Schedule getById(@Param(value="staffid")String staffid,@Param (value="scheduleId")String scheduleId);
	
}
