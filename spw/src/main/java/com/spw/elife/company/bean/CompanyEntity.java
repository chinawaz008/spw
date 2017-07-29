package com.spw.elife.company.bean;

import com.spw.elife.basics.bean.BasicEntity;

/**
 * 公司 实体类
 * @author Administrator
 *
 */
public class CompanyEntity extends BasicEntity{
	/**
	 * 主键
	 */
	private String id;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * logo
	 */
	private String logo;

	/**
	 * 后台背景图片
	 */
	private String backImg;

	/**
	 * 移动端图片
	 */
	private String appImgs;

	/**
	 * 管理员手机号码
	 */
	private String managerPhone;

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
	 * logo获取
	 * 
	 * @return logo
	 */
	public String getLogo() {
		return logo;
	}

	/**
	 * logo设定
	 * 
	 * @param logo logo
	 */
	public void setLogo(String logo) {
		this.logo = logo;
	}

	/**
	 * 后台背景图片获取
	 * 
	 * @return 后台背景图片
	 */
	public String getBackImg() {
		return backImg;
	}

	/**
	 * 后台背景图片设定
	 * 
	 * @param backImg 后台背景图片
	 */
	public void setBackImg(String backImg) {
		this.backImg = backImg;
	}

	/**
	 * 移动端图片获取
	 * 
	 * @return 移动端图片
	 */
	public String getAppImgs() {
		return appImgs;
	}

	/**
	 * 移动端图片设定
	 * 
	 * @param appImgs 移动端图片
	 */
	public void setAppImgs(String appImgs) {
		this.appImgs = appImgs;
	}

	/**
	 * 管理员手机号码获取
	 * 
	 * @return 管理员手机号码
	 */
	public String getManagerPhone() {
		return managerPhone;
	}

	/**
	 * 管理员手机号码设定
	 * 
	 * @param managerPhone 管理员手机号码
	 */
	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
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
