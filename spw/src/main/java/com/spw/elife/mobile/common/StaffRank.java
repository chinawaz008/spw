package com.spw.elife.mobile.common;


/**
 * 人员职级 迅车贷上传路径使用实体
 * @author Administrator
 *
 */
public class StaffRank {
	
	/**
	 * 条线名称
	 */
	private String lineName;
	/**
	 * 省公司名称
	 */
	private String companyName;
	/**
	 * 县区机构
	 */
	private String organizationName;
	/**
	 * 分部
	 */
	private String storeName;
	/**
	 * 业务员
	*/
	private String staffName;
	
	public String getLineName() {
		return lineName;
	}
	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

}
