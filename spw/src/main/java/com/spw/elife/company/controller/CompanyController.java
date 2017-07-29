package com.spw.elife.company.controller;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spw.elife.basics.bean.CommonLookup;
import com.spw.elife.common.AbstractController;
import com.spw.elife.common.Pager;
import com.spw.elife.company.bean.CompanyEntity;
import com.spw.elife.company.service.CompanyService;

/**
 * 公司 controller
 * @author Administrator
 * 
 */
@Controller
@RequestMapping
public class CompanyController extends AbstractController {
	@Resource
	private CompanyService companyService;
	
	private Logger log = Logger.getLogger(CompanyController.class);
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@RequestMapping(value = "/company/list", method = RequestMethod.GET)
	public String List(Model model,HttpServletRequest request) {
		
		return "company/list";
	}
	
	/**
	 * 公司查询列表
	 * @param model
	 * @return
	 */ 
	@RequestMapping(value="/company/list",method = RequestMethod.POST)
	public String webList(HttpServletResponse response,String data) throws IOException{
		response.setContentType("text/html;charset=GBK");//解决中文乱码
		response.setCharacterEncoding("utf-8");  
		JSONObject jb = new JSONObject();
		try {  
        	JSONObject json_look = JSONObject.fromObject(data); 
        	CommonLookup lookup = (CommonLookup)JSONObject.toBean(json_look, CommonLookup.class);
			Pager<CompanyEntity> pager = companyService.queryEntityList(lookup);
			jb.accumulate("result", "1");
			jb.accumulate("total", pager.getTotal());
			jb.accumulate("page", pager.getPage());
			if(pager.getElements().size()>0){
				JSONArray ja = JSONArray.fromObject(pager.getElements());
				jb.accumulate("list",ja);
			}else{
				jb.accumulate("list", new JSONArray());
			}
		} catch (Exception e) {
			jb.accumulate("result", "0");
			jb.accumulate("message","系统异常");
			log.error("系统异常，查询出错"+e);
			e.printStackTrace();
		} 
		response.getWriter().print(jb);
		return null;
	}

	/**
	 * 新增公司
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/company/add_company",method = RequestMethod.GET) 
	public String addDocument(Model model){
		try {
			
			
		} catch (Exception e) {
			log.error("新增公司出错!", e);
		}
		return "/company/add_company";
	}
	

	/**
	 * 编辑公司
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/company/update",method = RequestMethod.GET) 
	public String update(Model model, String id){
		CompanyEntity entity = companyService.getCompanyById(id);
		// 
		model.addAttribute("entity", entity);// 
		return "/company/add_company";
	}
	
	/**
	 * 公司信息保存.
	 * @throws IOException 
	 */
	@RequestMapping(value = "/company/save_company", method = RequestMethod.POST)
	@ResponseBody
	public void register(HttpServletResponse response, String data) throws IOException {
		Map<String,Object> map = new HashMap<>();
		response.setContentType("text/html;charset=GBK");//解决中文乱码
		response.setCharacterEncoding("utf-8");  
		JSONObject jo = new JSONObject();
		JSONObject json_data = JSONObject.fromObject(data); 
		CompanyEntity entity = (CompanyEntity)JSONObject.toBean(json_data, CompanyEntity.class);
		map = companyService.updateCompany(entity);
		jo.accumulate("result", map.get("result"));
		jo.accumulate("message", map.get("message"));
		response.getWriter().print(jo);
	}
}
