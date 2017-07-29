package com.spw.elife.company.bean;

/**
 * 角色 实体类
 * @author Administrator
 *
 */
public class RoleEntity {
	/**
	 * 主键
	 */
	private String id;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 公司id
	 */
	private String companyId;

	/**
	 * 创建人编号
	 */
	private String createId;

	/**
	 * 创建时间
	 */
	private String createTime;

	/**
	 * 修改人编号
	 */
	private String lastCreateId;

	/**
	 * 修改时间
	 */
	private String lastCreateTime;

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
	 * 公司id获取
	 * 
	 * @return 公司id
	 */
	public String getCompanyId() {
		return companyId;
	}

	/**
	 * 公司id设定
	 * 
	 * @param companyId 公司id
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	/**
	 * 创建人编号获取
	 * 
	 * @return 创建人编号
	 */
	public String getCreateId() {
		return createId;
	}

	/**
	 * 创建人编号设定
	 * 
	 * @param createId 创建人编号
	 */
	public void setCreateId(String createId) {
		this.createId = createId;
	}

	/**
	 * 创建时间获取
	 * 
	 * @return 创建时间
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间设定
	 * 
	 * @param createTime 创建时间
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * 修改人编号获取
	 * 
	 * @return 修改人编号
	 */
	public String getLastCreateId() {
		return lastCreateId;
	}

	/**
	 * 修改人编号设定
	 * 
	 * @param lastCreateId 修改人编号
	 */
	public void setLastCreateId(String lastCreateId) {
		this.lastCreateId = lastCreateId;
	}

	/**
	 * 修改时间获取
	 * 
	 * @return 修改时间
	 */
	public String getLastCreateTime() {
		return lastCreateTime;
	}

	/**
	 * 修改时间设定
	 * 
	 * @param lastCreateTime 修改时间
	 */
	public void setLastCreateTime(String lastCreateTime) {
		this.lastCreateTime = lastCreateTime;
	}
}
