package com.spw.elife.mobile.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/api")
public class ApiController {
	
	/**
	 * 登录
	 * @param mobile
	 * @param password
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/user/login", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject login(String mobile,String password,HttpServletRequest request,HttpServletResponse response) {
		JSONObject jo = new JSONObject();
		return jo;
	}
	
	/**
	 * 取得验证码
	 * @param mobile
	 * @param password
	 * @param request
	 * @param response
	 * @return 验证码字符串
	 */
	@RequestMapping(value = "/user/veriCode", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject veriCode(String mobile,HttpServletRequest request,HttpServletResponse response) {
		JSONObject jo = new JSONObject();
		return jo;
	}
	
	
	/**
	 * 重置密码
	 * @param mobile
	 * @param password
	 * @param request
	 * @param response
	 * @return 成功、失败
	 */
	@RequestMapping(value = "/user/modifyPasswd", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject modifyPasswd(String mobile,HttpServletRequest request,HttpServletResponse response) {
		JSONObject jo = new JSONObject();
		return jo;
	}
	
	
	
	/**
	 * 主页资料构造
	 * @param userid
	 * @param time  当前时间
	 * @param request
	 * @param response
	 * @return 成功、失败
	 */
	@RequestMapping(value = "/homepage/draw", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject homepageDraw(String userid,String time,HttpServletRequest request,HttpServletResponse response) {
		JSONObject jo = new JSONObject();
		return jo;
	}
	
	/**
	 * 签到画面
	 * @param userid
	 * @param time  当前时间
	 * @param request
	 * @param response
	 * @return 返回待签到班次
	 */
	@RequestMapping(value = "/signpage/draw", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject signDraw(String userid,String time,HttpServletRequest request,HttpServletResponse response) {
		JSONObject jo = new JSONObject();
		return jo;
	}
	
	/**
	 * 根据NFC码取得点位名称
	 * @param NFCCODE
	 * @param request
	 * @param response
	 * @return 返回点位名称
	 */
	@RequestMapping(value = "/signpage/namebycode", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject pointNameByNFCCode(String nfcCode,HttpServletRequest request,HttpServletResponse response) {
		JSONObject jo = new JSONObject();
		return jo;
	}
	
	
	/**
	 * 巡检主画面构造
	 * @param NFCCODE
	 * @param request
	 * @param response
	 * @return 返回当前班次当前点的任务list,当前第几个巡检点
	 */
	@RequestMapping(value = "/ritaskpage/draw", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject ritaskpagedraw(String shift_id,HttpServletRequest request,HttpServletResponse response) {
		JSONObject jo = new JSONObject();
		return jo;
	}
	
	
}
