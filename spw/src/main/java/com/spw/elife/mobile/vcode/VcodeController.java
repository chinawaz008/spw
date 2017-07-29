package com.spw.elife.mobile.vcode;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spw.elife.util.Utils;

/**
 * 
 * 发送短息验证码接口
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping
public class VcodeController {
	private static final Logger log = Logger.getLogger(VcodeController.class);
	/**
	 * 获取短信验证码
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/vcode/sendSMS", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject sendSMS(HttpServletRequest request, HttpServletResponse response) {
		// 返回处理结果
		JSONObject jo = new JSONObject();
		try {
			String phone = request.getParameter("phone");
			if (StringUtils.isBlank(phone)) {
				jo.accumulate("result", "0");
				jo.accumulate("message", "手机号码为空！");
				return jo;
			}
			String nums = Utils.getRandomString(6);
			String content = "#code#=" + nums;
			// 调用发短信接口
			String code = Utils.sendType(1, phone, content);
			if (code.equals("0")) {
				jo.accumulate("result", "1");
				jo.accumulate("message", "成功");
				jo.accumulate("code", nums);
			} else {
				jo.accumulate("result", "0");
				jo.accumulate("message", code);
			}
		} catch (Exception e) {
			jo.accumulate("result", "0");
			jo.accumulate("message", "发送短信异常！");
			log.error("发送短信异常！");
			e.printStackTrace();
		}
		return jo;
	}
}
