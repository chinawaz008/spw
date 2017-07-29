package com.spw.elife.mobile.schedule.bean;

import com.spw.elife.basics.bean.Lookup;



public class ScheduleLookUp extends Lookup{
	private String staffId;
	private String endTime; 
	private String scheduleId;
	public String getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	} 
}
