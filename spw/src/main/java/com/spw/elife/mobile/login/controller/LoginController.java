package com.spw.elife.mobile.login.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spw.elife.mobile.common.Response;
import com.spw.elife.mobile.login.dao.MobileLoginMapper;
import com.spw.elife.mobile.login.service.LoginService;
import com.spw.elife.staff.bean.Staff;
import com.spw.elife.staff.dao.StaffMapper;

/**
 * 用户登录接口Controller
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping
public class LoginController {
	private static final Logger log = Logger.getLogger(LoginController.class);
	@Resource
	private LoginService loginService;
	@Resource
	private StaffMapper staffMapper;
	@Resource
	private MobileLoginMapper mobileLoginMapper;
	/**
	 * 处理用户登录请求
	 * 
	 * @param response
	 * @param request
	 * @throws IOException 
	 */
	@RequestMapping(value = "/login/checkuser", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject checkUser(HttpServletRequest request,HttpServletResponse response) {
		JSONObject jo = new JSONObject();
		String username = request.getParameter("username");
		if(StringUtils.isBlank(username))
		jo.accumulate("message", "登录名不能为空");
		
		String password =request.getParameter("password");
		if(StringUtils.isBlank(password))
		jo.accumulate("message", "密码不能为空");
		else password=DigestUtils.md5Hex(password);
		
		String mobilekey = request.getParameter("mobilekey");
		if(StringUtils.isBlank(mobilekey))
		jo.accumulate("message", "手机识别码不能为空");
		if(jo.containsKey("message")){
			jo.accumulate("result", "0");
			return jo;
		}
		JSONObject json = loginService.checkUser(username,password,mobilekey);
		
		jo.accumulate("result", json.getString("result"));
		if(json.containsKey("message")){
			jo.accumulate("message", json.getString("message"));
		}else{
			jo.accumulate("message", "");
			jo.accumulate("staffid", json.getString("data"));
		}
		return jo;
	}
	
	/**
	 * 用户退出
	 * 
	 * @param response
	 * @param request
	 * @throws IOException 
	 */
	@RequestMapping(value = "/login/loginOut", method = RequestMethod.POST)
	@ResponseBody
	public void loginOut(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		String staffid = request.getParameter("staffid");
		Assert.notNull(staffid, "用户的ID不能为空");
		
		Response resp = loginService.loginOut(staffid);
		
		JSONObject jo = new JSONObject();
		jo.accumulate("result", resp.getCode());
		jo.accumulate("message", resp.getMessage());
		response.getWriter().print(jo);
	}
	
	/**
	 * 修改密码
	 * 
	 * @param response
	 * @param request
	 * @throws IOException 
	 */
	@RequestMapping(value = "/login/updatePwd", method = RequestMethod.POST)
	@ResponseBody
	public void updatePwd(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		String staffid = request.getParameter("staffid");
		Assert.notNull(staffid, "用户的ID不能为空");
		
		String password =request.getParameter("password");
		Assert.notNull(password, "新密码不能为空");
		password=DigestUtils.md5Hex(password);
		
		String oldpassword =request.getParameter("oldpassword");
		Assert.notNull(oldpassword, "旧密码不能为空");
		oldpassword=DigestUtils.md5Hex(oldpassword);
		
		Response resp = loginService.updatePwd(staffid, password, oldpassword);
		
		JSONObject jo = new JSONObject();
		jo.accumulate("result", resp.getCode());
		jo.accumulate("message", resp.getMessage());
		response.getWriter().print(jo);
	}	
	
	/**
	 * 忘记密码
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/login/forgetPsd", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject forgetPsd(HttpServletRequest request, HttpServletResponse response){
		log.info("-------忘记密码修改-----");
		JSONObject jo = new JSONObject();
		try {
			String phone = request.getParameter("phone");
			if (StringUtils.isBlank(phone)) {
				jo.accumulate("result", "0");
				jo.accumulate("message", "手机号码为空！");
				return jo;
			}
//			String falg = staffMapper.checkMobile(phone);
//			if (StringUtils.isBlank(falg)) {
//				jo.accumulate("result", "0");
//				jo.accumulate("message", "手机号码不存在！");
//				return jo;
//			}
			String password = request.getParameter("password");
			if (StringUtils.isBlank(password)) {
				jo.accumulate("result", "0");
				jo.accumulate("message", "密码为空！");
				return jo;
			}
			password = DigestUtils.md5Hex(password);
			int result = mobileLoginMapper.updatePwdByPhone(password, phone);
			if(result == 0){
				jo.accumulate("result", "0");
				jo.accumulate("message", "修改密码失败！");
				return jo;
			}
			jo.accumulate("result", "1");
			jo.accumulate("message", "成功");
		} catch (Exception e) {
			jo.accumulate("result", "0");
			jo.accumulate("message", "发送短信异常！");
			log.error("忘记密码修改异常");
			e.printStackTrace();
		}
		return jo;
	}
}