package com.spw.elife.basics.bean;

/**
 * 公共
 * @author Administrator
 *
 */
public class BasicEntity {
	/**
	 * 创建人姓名
	 */
	private String createName;

	/**
	 * 更新人姓名
	 */
	private String lastCreateName;

	/**
	 * 创建人姓名获取
	 * 
	 * @return 创建人姓名
	 */
	public String getCreateName() {
		return createName;
	}

	/**
	 * 创建人姓名设定
	 * 
	 * @param createName 创建人姓名
	 */
	public void setCreateName(String createName) {
		this.createName = createName;
	}

	/**
	 * 更新人姓名获取
	 * 
	 * @return 更新人姓名
	 */
	public String getLastCreateName() {
		return lastCreateName;
	}

	/**
	 * 更新人姓名设定
	 * 
	 * @param lastCreateName 更新人姓名
	 */
	public void setLastCreateName(String lastCreateName) {
		this.lastCreateName = lastCreateName;
	}
}
