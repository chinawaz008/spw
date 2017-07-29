package com.spw.elife.staff.bean;

public class AuthorityEntity {
	/**
	 * 主键
	 */
	private String id;

	/**
	 * 人员id
	 */
	private String staffId;

	/**
	 * 类型 1总公司权限 2 分公司权限 3 行政权限
	 */
	private String type;

	/**
	 * 省
	 */
	private String provinceId;

	/**
	 * 部门
	 */
	private String departmentId;

	/**
	 * 所属督训区
	 */
	private String organizationId;

	/**
	 * 主键获取
	 * 
	 * @return 主键
	 */
	public String getId() {
		return id;
	}

	/**
	 * 主键设定
	 * 
	 * @param id 主键
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 人员id获取
	 * 
	 * @return 人员id
	 */
	public String getStaffId() {
		return staffId;
	}

	/**
	 * 人员id设定
	 * 
	 * @param staffId 人员id
	 */
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	/**
	 * 类型 1总公司权限 2 分公司权限 3 行政权限获取
	 * 
	 * @return 类型 1总公司权限 2 分公司权限 3 行政权限
	 */
	public String getType() {
		return type;
	}

	/**
	 * 类型 1总公司权限 2 分公司权限 3 行政权限设定
	 * 
	 * @param type 类型 1总公司权限 2 分公司权限 3 行政权限
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 省获取
	 * 
	 * @return 省
	 */
	public String getProvinceId() {
		return provinceId;
	}

	/**
	 * 省设定
	 * 
	 * @param provinceId 省
	 */
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	/**
	 * 部门获取
	 * 
	 * @return 部门
	 */
	public String getDepartmentId() {
		return departmentId;
	}

	/**
	 * 部门设定
	 * 
	 * @param departmentId 部门
	 */
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	/**
	 * 所属督训区获取
	 * 
	 * @return 所属督训区
	 */
	public String getOrganizationId() {
		return organizationId;
	}

	/**
	 * 所属督训区设定
	 * 
	 * @param organizationId 所属督训区
	 */
	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}
}
