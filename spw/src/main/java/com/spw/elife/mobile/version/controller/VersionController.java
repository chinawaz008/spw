package com.spw.elife.mobile.version.controller;

import java.io.IOException;
import java.text.ParseException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spw.elife.mobile.version.bean.MVersion;
import com.spw.elife.mobile.version.dao.VersionMapper;
import com.spw.elife.mobile.version.service.VersionService;
import com.spw.elife.util.DateUtil;
import com.spw.elife.util.Utils;

/**
 * 
 * 版本号Controller
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping
public class VersionController {

	@Resource
	private VersionService versionService;
	@Resource
	private VersionMapper versionMapper;

	private Logger log = Logger.getLogger(VersionController.class);

	/**************************************************** WEB端后台 *******************************************************************************************************************/

	/**
	 * 
	 * 进入版本新增页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/mversion/version", method = RequestMethod.GET)
	public String LoadAttendancePage(Model model) {
		MVersion version = versionService.findLastVersion();
		if (version != null) {
			model.addAttribute("version", version);
		} else {
			model.addAttribute("version", "版本获取出错了");
		}
		return "mobile/version/version_add";
	}
	
	/**
	 * 
	 * 进入版本新增页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/mversion/version", method = RequestMethod.POST)
	public void LoadAttendancePage_2(Model model) {

	}

	/**
	 * 
	 * 保存版本号信息
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ParseException
	 */
	@RequestMapping(value = "/version/saveVersion", method = RequestMethod.POST)
	public String saveAttendance(HttpServletRequest request,
			HttpServletResponse response,Model model) throws IOException, ParseException {

		// 解决中文乱码
		response.setContentType("text/html;charset=GBK");
		response.setCharacterEncoding("utf-8");

		MVersion version = new MVersion();
		String url="";

		try {
			String versionName = request.getParameter("version");
			// 当前版本号
			if (StringUtils.isEmpty(versionName)) {
				throw new RuntimeException("版本号不能为空");
			}

			version.setVersion(versionName);
			// 获得当前日期
			String date = DateUtil.timeFormat(System.currentTimeMillis(),
					"yyyy-MM-dd");
			version.setDate(date);

			// 上传apk
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile apkFile = multipartRequest.getFile("apkFile");
			if (apkFile == null) {
				throw new RuntimeException("apk不能为空");
			}
			url = Utils.uploadFile(apkFile);
			int flag=versionService.saveBasicAttendance(version);
			return "redirect:/mversion/version";
			
		} catch (Exception e) {
			model.addAttribute("message","apk版本更新失败");
			e.printStackTrace();
			log.info("apk版本更新上传出错", e);
			return "mobile/version/version_add";
			
		}
		
		
	}

	/**
	 * 获得ios版本
	 * @return
	 */
	@RequestMapping(value = "/app/getIOSVersion", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getIOSVersion(){
		JSONObject jo = new JSONObject();
		String result =	Utils.getProp().getProperty("app.ios.version");
		jo.accumulate("result", result);
		return jo;
	}
	
}
