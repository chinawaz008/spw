package com.spw.elife.basics.bean;

import java.util.List;

public class SelectEntity extends Lookup{
	/**
	 * id
	 */
	private String id;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 组织类型
	 */
	private String orgType;

	/**
	 * 人员Id
	 */
	private String staffId;
	
	/**
	 * 部门id
	 */
	private String departmentId;

	/**
	 * 检索项
	 */
	private List<String> keys;

	/**
	 * 检索项获取
	 * 
	 * @return 检索项
	 */
	public List<String> getKeys() {
		return keys;
	}

	/**
	 * 检索项设定
	 * 
	 * @param keys 检索项
	 */
	public void setKeys(List<String> keys) {
		this.keys = keys;
	}

	/**
	 * 部门id获取
	 * 
	 * @return 部门id
	 */
	public String getDepartmentId() {
		return departmentId;
	}

	/**
	 * 部门id设定
	 * 
	 * @param departmentId 部门id
	 */
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	/**
	 * id获取
	 * 
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * id设定
	 * 
	 * @param id id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 名称获取
	 * 
	 * @return 名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 名称设定
	 * 
	 * @param name 名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 组织类型获取
	 * 
	 * @return 组织类型
	 */
	public String getOrgType() {
		return orgType;
	}

	/**
	 * 组织类型设定
	 * 
	 * @param orgType 组织类型
	 */
	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	/**
	 * 人员Id获取
	 * 
	 * @return 人员Id
	 */
	public String getStaffId() {
		return staffId;
	}

	/**
	 * 人员Id设定
	 * 
	 * @param staffId 人员Id
	 */
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

}
