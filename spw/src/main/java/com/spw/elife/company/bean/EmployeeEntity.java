package com.spw.elife.company.bean;

/**
 * 人员 实体类
 * @author Administrator
 *
 */
public class EmployeeEntity {
	/**
	 * 主键
	 */
	private String id;

	/**
	 * 姓名
	 */
	private String name;

	/**
	 * 手机号码
	 */
	private String phone;

	/**
	 * 用户角色
	 */
	private String roleId;

	/**
	 * 登录密码
	 */
	private String passWord;

	/**
	 * 状态
	 */
	private String states;

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
	 * 角色名
	 */
	private String roleName;

	/**
	 * 公司
	 */
	private String companyName;

	/**
	 * 角色名获取
	 * 
	 * @return 角色名
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * 角色名设定
	 * 
	 * @param roleName 角色名
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * 公司获取
	 * 
	 * @return 公司
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * 公司设定
	 * 
	 * @param companyName 公司
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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
	 * 姓名获取
	 * 
	 * @return 姓名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 姓名设定
	 * 
	 * @param name 姓名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 手机号码获取
	 * 
	 * @return 手机号码
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * 手机号码设定
	 * 
	 * @param phone 手机号码
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 用户角色获取
	 * 
	 * @return 用户角色
	 */
	public String getRoleId() {
		return roleId;
	}

	/**
	 * 用户角色设定
	 * 
	 * @param role 用户角色
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	/**
	 * 登录密码获取
	 * 
	 * @return 登录密码
	 */
	public String getPassWord() {
		return passWord;
	}

	/**
	 * 登录密码设定
	 * 
	 * @param passWord 登录密码
	 */
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	/**
	 * 状态获取
	 * 
	 * @return 状态
	 */
	public String getStates() {
		return states;
	}

	/**
	 * 状态设定
	 * 
	 * @param states 状态
	 */
	public void setStates(String states) {
		this.states = states;
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
