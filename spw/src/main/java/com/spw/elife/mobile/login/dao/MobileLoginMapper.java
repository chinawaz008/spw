package com.spw.elife.mobile.login.dao;

import org.apache.ibatis.annotations.Param;

import com.spw.elife.common.DaoMapper;

/**
 * 
 * 用户登录Mapper
 * 
 * @author Administrator
 *
 */
@DaoMapper
public interface MobileLoginMapper {
	
	// 判断用户是否重复登录
	public int checkUserByStaffid(@Param(value = "staffid")int staffid,@Param(value = "type")String type);
	
	// 判断该用户名是否存在
	public int checkUserByUsername(@Param(value = "username")String username);
	
	// 判断密码是否正确
	public int checkUserByUsernameAndPassword(@Param(value = "username")String username,@Param(value = "password")String password);
	
	// 判断mac是否正确
	public int checkMacAddress(@Param(value = "staffid")int staffid,@Param(value="mac")String mac,@Param(value = "type")String type);
	
	// 判断是否是已签名
	public int checkIsAgree(@Param(value = "staffid")int staffid,@Param(value = "type")String type);
	
	// 获取用户的staffid
	public int getUserStaffidByUsernameAndPassword(@Param(value = "username")String username,@Param(value = "password")String password);
	
	// 保存用户的登录信息
	public int insertUserAccessToken(@Param(value = "mobilekey")String mobilekey,@Param(value = "staffid")int staffid);
	
	// 保存车贷的用户登录
	public int insertLoanUserAccessToken(@Param(value = "mobilekey")String mobilekey,@Param(value = "staffid")int staffid,@Param(value = "type")String type);
	
	// 用户退出
	public int loginOutByStaffid(@Param(value = "staffid")String staffid);
	
	//检查旧密码是否正确
	public int checkOldPwdByStaffidAndOldPwd(@Param(value = "oldpassword")String oldpassword,@Param(value = "staffid")String staffid);
	
	//修改密码
	public int updatePwd(@Param(value = "password")String password,@Param(value = "staffid")String staffid); 	
	
	//忘记密码
	public int updatePwdByPhone(@Param(value = "password")String password,@Param(value = "phoneNum")String phoneNum); 	
	
	//更新签名
	public int updateSign(@Param(value = "staffid")String staffid,@Param(value = "type")String type,
			@Param(value = "signAddress")String signAddress);
	
	//根据mac地址获得当前用户
//	public Staff getStaffByMac(@Param(value = "mobilekey")String mobilekey,@Param(value = "type")String type);
}

