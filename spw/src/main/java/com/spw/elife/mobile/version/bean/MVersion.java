package com.spw.elife.mobile.version.bean;

import java.io.Serializable;

import com.spw.elife.basics.bean.Lookup;

/**
 * 
 * 版本号实体类
 * 
 * @author Administrator
 *
 */
public class MVersion implements Serializable{
	private static final long serialVersionUID = -3602029078955984483L;
	
	/** 主键id**/
	private int id;
	
	/** 更新日期**/
	private String date;
	
	/** 当前版本号**/
	private String version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	
	
}
