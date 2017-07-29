package com.spw.elife.staff.bean;

public class DataAuthorityEntity {
	/**
	 * 主键
	 */
	private String id;

	/**
	 * 人员id
	 */
	private String staffId;

	/**
	 * 类型
	 */
	private String type;

	/**
	 * 所属分公司
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
	 * 姓名
	 */
	private String staffName;

	/**
	 * 岗位id
	 */
	private String position;

	/**
	 * 岗位名称
	 */
	private String positionName;

	/**
	 * 部门名称
	 */
	private String departMentName;

	/**
	 * 省分
	 */
	private String branchCompanyId;

	/**
	 * 督训区
	 */
	private String countyFranchiseesId;

	/**
	 * 分部
	 */
	private String storeId;

	/**
	 * 省分名称
	 */
	private String branchCompanyName;

	/**
	 * 督训区名称
	 */
	private String countyFranchiseesName;

	/**
	 * 分部名称
	 */
	private String storeName;

	/**
	 * 岗位号
	 */
	private String worknum;
	
	/**
	 * 渠道
	 */
	private String lineType;

	/**
	 * 渠道获取
	 * 
	 * @return 渠道
	 */
	public String getLineType() {
		return lineType;
	}

	/**
	 * 渠道设定
	 * 
	 * @param lineType 渠道
	 */
	public void setLineType(String lineType) {
		this.lineType = lineType;
	}

	/**
	 * 岗位号获取
	 * 
	 * @return 岗位号
	 */
	public String getWorknum() {
		return worknum;
	}

	/**
	 * 岗位号设定
	 * 
	 * @param worknum 岗位号
	 */
	public void setWorknum(String worknum) {
		this.worknum = worknum;
	}

	/**
	 * 省分名称获取
	 * 
	 * @return 省分名称
	 */
	public String getBranchCompanyName() {
		return branchCompanyName;
	}

	/**
	 * 省分名称设定
	 * 
	 * @param branchCompanyName 省分名称
	 */
	public void setBranchCompanyName(String branchCompanyName) {
		this.branchCompanyName = branchCompanyName;
	}

	/**
	 * 督训区名称获取
	 * 
	 * @return 督训区名称
	 */
	public String getCountyFranchiseesName() {
		return countyFranchiseesName;
	}

	/**
	 * 督训区名称设定
	 * 
	 * @param countyFranchiseesName 督训区名称
	 */
	public void setCountyFranchiseesName(String countyFranchiseesName) {
		this.countyFranchiseesName = countyFranchiseesName;
	}

	/**
	 * 分部名称获取
	 * 
	 * @return 分部名称
	 */
	public String getStoreName() {
		return storeName;
	}

	/**
	 * 分部名称设定
	 * 
	 * @param storeName 分部名称
	 */
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	/**
	 * 省分获取
	 * 
	 * @return 省分
	 */
	public String getBranchCompanyId() {
		return branchCompanyId;
	}

	/**
	 * 省分设定
	 * 
	 * @param branchCompanyId 省分
	 */
	public void setBranchCompanyId(String branchCompanyId) {
		this.branchCompanyId = branchCompanyId;
	}

	/**
	 * 督训区获取
	 * 
	 * @return 督训区
	 */
	public String getCountyFranchiseesId() {
		return countyFranchiseesId;
	}

	/**
	 * 督训区设定
	 * 
	 * @param countyFranchiseesId 督训区
	 */
	public void setCountyFranchiseesId(String countyFranchiseesId) {
		this.countyFranchiseesId = countyFranchiseesId;
	}

	/**
	 * 分部获取
	 * 
	 * @return 分部
	 */
	public String getStoreId() {
		return storeId;
	}

	/**
	 * 分部设定
	 * 
	 * @param storeId 分部
	 */
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	/**
	 * 姓名获取
	 * 
	 * @return 姓名
	 */
	public String getStaffName() {
		return staffName;
	}

	/**
	 * 姓名设定
	 * 
	 * @param staffName 姓名
	 */
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	/**
	 * 岗位id获取
	 * 
	 * @return 岗位id
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * 岗位id设定
	 * 
	 * @param position 岗位id
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * 岗位名称获取
	 * 
	 * @return 岗位名称
	 */
	public String getPositionName() {
		return positionName;
	}

	/**
	 * 岗位名称设定
	 * 
	 * @param positionName 岗位名称
	 */
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	/**
	 * 部门名称获取
	 * 
	 * @return 部门名称
	 */
	public String getDepartMentName() {
		return departMentName;
	}

	/**
	 * 部门名称设定
	 * 
	 * @param departMentName 部门名称
	 */
	public void setDepartMentName(String departMentName) {
		this.departMentName = departMentName;
	}

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
	 * 类型获取
	 * 
	 * @return 类型
	 */
	public String getType() {
		return type;
	}

	/**
	 * 类型设定
	 * 
	 * @param type 类型
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 所属分公司获取
	 * 
	 * @return 所属分公司
	 */
	public String getProvinceId() {
		return provinceId;
	}

	/**
	 * 所属分公司设定
	 * 
	 * @param provinceId 所属分公司
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
