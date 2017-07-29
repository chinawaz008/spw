package com.spw.elife.mobile.common;


/**
 * 接口请求响应类
 * 
 * @author Administrator
 *
 */
public enum Response {
	
	SUCCESS(1, "成功"),

	FAIL(0, "失败"),

	USERNAME_ERROR(3, "用户名不存在"),

	PASSWORD_ERROR(4, "密码不正确"),
	
	OLD_PASSWORD_ERROR(7, "输入的旧密码不正确"),

	RELOGIN_ERROR(5, "请勿重复登录"),
	
	SERVER_ERROR(500, "服务器发生了未知错误"),

	NOLOGIN_ERROR(9, "会话已过期,请检查是否已登录或登录已超时"),
	
	Error_Bind_Phone(10,"该用户已经绑定别的手机"),

	USERNAME_TOO_MANY(11,"用户名重复太多，请用岗位号或身份证登录"),
	
	ERROR_BIND_POSITION(12,"改用户在当前时间段内已经存在班次规则"),
	 
	ERROR_RULE(13,"修改的规则起始时间必须大于今天"),
	 
	ERROR_JOB(14,"规则应对的岗位不唯一"),
	 
	ERROR_TIME(15,"起始时间必须小于结束时间");
	
	
	/**
	 * 数据
	 */
	private Object data="";
	
	/**
	 * 代码
	 */
	private int code;

	/**
	 * 信息
	 */
	private String message;

	/**
	 * 
	 * @param code
	 *            响应码
	 * @param message
	 *            响应消息
	 */
	Response(int code, String message) {
		this.code = code;
		this.message = message;
	}

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}
	
	public void setCode(int code) {
		this.code = code;
	}
	
	
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
