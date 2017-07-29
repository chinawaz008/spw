package com.spw.elife.staff.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spw.elife.basics.bean.CommonLookup;
import com.spw.elife.basics.service.AuthService;
import com.spw.elife.common.AbstractController;
import com.spw.elife.common.Pager;
import com.spw.elife.mobile.common.Response;
import com.spw.elife.staff.bean.AuthorityEntity;
import com.spw.elife.staff.bean.DataAuthorityEntity;
import com.spw.elife.staff.service.StaffService;
import com.spw.elife.util.Constant;

@Controller
@RequestMapping
public class AuthorityController extends AbstractController{
	
	private static final Logger log = Logger.getLogger(AuthorityController.class);
	
	@Resource
	private AuthService authService;
    @Resource
    private StaffService staffService;
/********************************************************************* 二次开发start  *******************************************************************/
//	/**
//	 * 菜单跳转到权限管理
//	 * @param model
//	 * @return
//	 */
//	@RequestMapping(value = "/authority/redevelope/manage", method = RequestMethod.GET)
//	public String list_redevelope(Model model) { 
//		return "staff/authority_redevelope_manage";
//	}
//	
//	
//	/**
//	 * 获取左边 公司架构
//	 * @throws IOException 
//	 */
//	@RequestMapping(value = "/authority/redevelope/framework", method = RequestMethod.POST)
//	@ResponseBody
//	public void redevelope_framework(HttpServletRequest request,HttpServletResponse response) throws IOException{
//		Response resp = Response.SUCCESS;
//		response.setContentType("text/html;charset=GBK");//解决中文乱码
//		response.setCharacterEncoding("utf-8");  
//		JSONObject jo = new JSONObject();
//		try {
////			List<Map<String,Object>> left = departMentService.getDepartmentMenu(null,true,true);
////			JSONArray jmap = JSONArray.fromObject(left);
////			jo.accumulate("zNodesPosition", jmap);
//		} catch (Exception e) {
//			e.printStackTrace();
//			log.info("..........获取组织架构出错：" + e);
//			resp = Response.FAIL;
//			resp.setMessage("获取组织架构异常");
//		}
//		jo.accumulate("result", resp.getCode());
//		jo.accumulate("message", resp.getMessage());
//		response.getWriter().print(jo);
//	}
//	
//	/**
//	 * 获取左边 公司架构
//	 * @throws IOException 
//	 */
//	@RequestMapping(value = "/authority/redevelope/all_departMent", method = RequestMethod.POST)
//	@ResponseBody
//	public void all_departMent(HttpServletRequest request,HttpServletResponse response) throws IOException{
//		Response resp = Response.SUCCESS;
//		response.setContentType("text/html;charset=GBK");//解决中文乱码
//		response.setCharacterEncoding("utf-8");  
//		JSONObject jo = new JSONObject();
//		try {
////			List<Map<String,Object>> left = departMentService.getDepartmentMenu(null,false,false);
////			JSONArray jmap = JSONArray.fromObject(left);
////			jo.accumulate("zNodesPosition", jmap);
//		} catch (Exception e) {
//			e.printStackTrace();
//			log.info("..........获取组织架构出错：" + e);
//			resp = Response.FAIL;
//			resp.setMessage("获取组织架构异常");
//		}
//		jo.accumulate("result", resp.getCode());
//		jo.accumulate("message", resp.getMessage());
//		response.getWriter().print(jo);
//	}
//
//	/**
//	 * 获取功能模块
//	 * @throws IOException 
//	 */
//	@RequestMapping(value = "/authority/redevelope/menu", method = RequestMethod.POST)
//	@ResponseBody
//	public void redevelope_menu(HttpServletRequest request,HttpServletResponse response) throws IOException{
//		Response resp = Response.SUCCESS;
//		response.setContentType("text/html;charset=GBK");//解决中文乱码
//		response.setCharacterEncoding("utf-8");  
//		JSONObject jo = new JSONObject();
//		try {
//			String id = request.getParameter("id");
//			String personType = request.getParameter("personType");
//			String departMentId = request.getParameter("departMentId");
//			if(StringUtils.isNotBlank(id)){
////				String [] ids = id.split("@");
//				String roleId = id; //ids[ids.length-1];//岗位id
////				String personType = ids[0];//人员类型0业务人员 1后援人员
////				String companyType = "";//1总公司 2分公司
////				if(personType.equals("2")){//后援人员
////					companyType = ids[1];
////					personType = Constant.PERSON_TYPE_1;
////				}else{
////					personType = Constant.PERSON_TYPE_0;
////				}
//				
//				List<Map<String,Object>> right = authService.getRedevelopeRightMenu(departMentId,roleId,personType);
//				JSONArray jmap2 = JSONArray.fromObject(right);
//				jo.accumulate("zNodesAuth", jmap2);
//			}else{
//				resp = Response.FAIL;
//				resp.setMessage("id不能为空");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			log.info("..........获取人员权限出错：" + e);
//			resp = Response.FAIL;
//			resp.setMessage("获取人员权限异常");
//		}
//		jo.accumulate("result", resp.getCode());
//		jo.accumulate("message", resp.getMessage());
//		response.getWriter().print(jo);
//	}
//	
//	/**
//	 * 设置权限
//	 * @throws IOException 
//	 */
//	@RequestMapping(value = "/authority/redevelope/save", method = RequestMethod.POST)
//	@ResponseBody
//	public void redevelope_save(HttpServletRequest request,HttpServletResponse response) throws IOException{
//		Response resp = Response.SUCCESS;
//		response.setContentType("text/html;charset=GBK");//解决中文乱码
//		response.setCharacterEncoding("utf-8");  
//		JSONObject jo = new JSONObject();
//		try {
//			String position = request.getParameter("position");
//			String auths = request.getParameter("auths");
//			String personType = request.getParameter("personType");
//			String departMentId = request.getParameter("departMentId");
//			boolean tf = authService.updateRedevelopeAuth(departMentId, position, auths,personType);
//			if(!tf){
//				resp = Response.FAIL;
//				resp.setMessage("设置权限出错");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			log.info("..........设置权限出错：" + e);
//			resp = Response.FAIL;
//			resp.setMessage("设置权限异常");
//		}
//		jo.accumulate("result", resp.getCode());
//		jo.accumulate("message", resp.getMessage());
//		response.getWriter().print(jo);
//	}
//	
//	/**
//	 * 数据权限配置列表
//	 * 
//	 * @param model
//	 * @return
//	 */
//	@RequestMapping(value="/authority/data_list",method = RequestMethod.GET) 
//	public String quertEntityList(Model model){ 
////		model.addAttribute("lineType", Utils.getLineType());
//		return "staff/list_data";
//	}
//	
//
//	/**
//	 * 列表页展示和查询
//	 * @param page
//	 * @param size
//	 * @param filter
//	 * @return
//	 */
//	@RequestMapping(value = "/authority/data_list", method = RequestMethod.POST)
//	@ResponseBody
//	public Map<String,Object> list(String data) {
//		Map<String,Object> map = new HashMap<String,Object>();
//        try {
//        	JSONObject json_look = JSONObject.fromObject(data); 
//        	CommonLookup lookup = (CommonLookup)JSONObject.toBean(json_look, CommonLookup.class);
//        	setDefultLookup(getUserId(), lookup);
//    		Pager<DataAuthorityEntity> pager = authService.queryDataList(lookup);
//    		map.put("result", 1);
//    		map.put("total", pager.getTotal());
//			map.put("list",pager.getElements());
//		} catch (Exception e) {
//			map.put("result", 0);
//			map.put("message","系统异常");
//			log.error("系统异常，查询出错"+e);
//		}
//        return map;
//	}
//	
//	/**
//	 * 数据权限add
//	 * 
//	 * @param model
//	 * @return
//	 */
//	@RequestMapping(value="/authority/add_data",method = RequestMethod.GET) 
//	public String add_data(Model model){
////		model.addAttribute("lineType", Utils.getLineType());
////		model.addAttribute("lineData", getLines());
////		List<DepartMent> list = departMentService.getChildDepartmentByDepartmenType(Constant.DEPARTMENT_TYPE_DEPARTMENT);
////		model.addAttribute("departments", list);
////		model.addAttribute("modifyFlag", "");
////		model.addAttribute("dataType", Constant.DATA_TYPE1);// 权限类型
////		model.addAttribute("selectDepartments", new ArrayList<DepartMent>());
////		model.addAttribute("dataType3", "");// 权限类型
//		return "staff/add_data";
//	}
//
//	/**
//	 * 数据权限登录
//	 * 
//	 * @throws IOException 
//	 */
//	@RequestMapping(value = "/authority/save_data", method = RequestMethod.POST)
//	@ResponseBody
//	public void save_data(HttpServletRequest request,HttpServletResponse response,String staffId, String authorityLine, String authority) throws IOException{
//		response.setContentType("text/html;charset=GBK");//解决中文乱码
//		response.setCharacterEncoding("utf-8");  
//		JSONObject jo = new JSONObject();
//		Map<String,Object> map = new HashMap<String,Object>();
//		try {
//			// 行政权限
//			AuthorityEntity entity = new AuthorityEntity();
//			entity.setType(Constant.DATA_TYPE3);
//			entity.setDepartmentId(authority);
//			// 业务权限
//			JSONObject line = JSONObject.fromObject(authorityLine);
//			AuthorityEntity entityLine = (AuthorityEntity)JSONObject.toBean(line, AuthorityEntity.class);
//			map = authService.saveData(staffId, entityLine, entity);
//			map.put("result", 1);
//			map.put("message","");
//	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	    	Date currentTime = new Date();
//			log.info("操作人： " + getUserId() + "于" + sdf.format(currentTime) + " 给： " + staffId + "编辑了数据权限");
//		} catch (Exception e) {
//			e.printStackTrace();
//			map.put("result", 0);
//			map.put("message","数据权限登录异常");
//			log.info("..........数据权限登录出错：" + e);
//		}
//		jo.accumulate("result", map.get("result"));
//		jo.accumulate("message",  map.get("message"));
//		response.getWriter().print(jo);
//	}
//	
//	/**
//	 * 数据权限add
//	 * 
//	 * @param model
//	 * @return
//	 */
//	@RequestMapping(value="/authority/update",method = RequestMethod.GET) 
//	public String update(Model model, String staffId){
////		Staff staff = staffService.getStaffById(staffId);
////		model.addAttribute("staff", staff);
////		List<AuthorityEntity> authors = authService.getAuthortyByStaffId(staffId);
////		// 总公司权限
////		List<Map<String, Object>> lines = Utils.getLineType();
////		String[] composeKeys = {"type"};
////		List<Object> objs = Tools.composeSlip(authors, composeKeys);
////		String dataType = "";
////		String dataType3 = "";
////		// 分公司权限
////		List<LineEntity> lineData = getLines();
////		List<LineEntity> newlineData = new ArrayList<>();
////		List<DepartMent> selectDepartments = new ArrayList<>();
////		for (Object obj : objs) {
////			@SuppressWarnings("unchecked")
////			List<AuthorityEntity> subList = (List<AuthorityEntity>) obj;
////			AuthorityEntity entity = subList.get(0);
////			if (Constant.DATA_TYPE1.equals(entity.getType())) {// 业务权限-总公司
////				for (Map<String, Object> map : lines) {
////					String id = (String) map.get("id");
////					for (AuthorityEntity sub : subList) {
////						if (id.equals(sub.getDepartmentId())){
////							map.put("checkFlag", "checked");
////							break;
////						}
////					}
////				}
////				dataType = Constant.DATA_TYPE1;
////			}
////			else if (Constant.DATA_TYPE2.equals(entity.getType())) {// 业务权限-分公司
////				// 按照渠道和省分公司合并
////				String[] subComposeKeys = {"provinceId"};
////				List<Object> subObjs = Tools.composeSlip(subList, subComposeKeys);
////				for (Object subObj : subObjs) {
////					List<AuthorityEntity> subList2 = (List<AuthorityEntity>) subObj;
////					AuthorityEntity subEntity = subList2.get(0);
////					// 可选状态设定
////					for (LineEntity lin : lineData) {
////						if (subEntity.getDepartmentId().equals(lin.getId())) {
////							for (LineEntity com : lin.getSubList()) {
////								if (subEntity.getProvinceId().equals(com.getId())) {
////									com.setDisabledFlag(false);
////								}
////							}
////						}
////					}
////				}
////				for (Object subObj : subObjs) {
////					List<AuthorityEntity> subList2 = (List<AuthorityEntity>) subObj;
////					AuthorityEntity subEntity = subList2.get(0);
////					String departId = subEntity.getDepartmentId();
////					String provinceId = subEntity.getProvinceId();
////					for (LineEntity lin : lineData) {
////						LineEntity head = new LineEntity();
////						head.setId(lin.getId());
////						head.setName(lin.getName());
////						List<LineEntity> subListss = new ArrayList<>();
////						boolean addFlag = false;
////						if (departId.equals(lin.getId())) {
////							for (LineEntity com : lin.getSubList()) {
////								LineEntity sds = new LineEntity();
////								sds.setId(com.getId());
////								sds.setName(com.getName());
////								sds.setDisabledFlag(com.getDisabledFlag());
////								if (provinceId.equals(com.getId())) {
////									lin.setAddFlag(false);
////									head.setCheckId(provinceId);
////									addFlag = true;
////									// 渠道加入
////									List<Organization> orgs = new ArrayList<>();
////									for (AuthorityEntity org : subList2) {
////										if (StringUtils.isNotEmpty(org.getOrganizationId())) {
////											Organization org1 = organizationService.queryEntityById(org.getOrganizationId());
////											orgs.add(org1);
////										}
////									}
////									head.setOrgList(orgs);
////								} 
////								subListss.add(sds);
////							}
////						}
////						else {
////							addFlag = false;
////						}
////						head.setSubList(subListss);
////						if (addFlag) {
////							newlineData.add(head);
////						}
////					}
////				}
////				dataType = Constant.DATA_TYPE2;
////			}
////			else if (Constant.DATA_TYPE3.equals(entity.getType())) {// 行政权限
////				dataType3 = Constant.DATA_TYPE3;
////				for (AuthorityEntity auth : subList) {
////					if (StringUtils.isNotEmpty(auth.getDepartmentId())) {
////						DepartMent dep = departMentService.getDepartment(auth.getDepartmentId());
////						selectDepartments.add(dep);
////					}
////				}
////			}
////		}
////		// 分公司数据权限处理
////		for (LineEntity line : lineData) {
////			if (line.getAddFlag()) {
////				newlineData.add(line);
////			}
////		}
////		if (!StringUtils.isNotEmpty(dataType)) {
////			dataType = Constant.DATA_TYPE1;
////		}
////		model.addAttribute("dataType", dataType);// 权限类型
////		model.addAttribute("dataType3", dataType3);// 权限类型
////		model.addAttribute("lineType", lines);
////		model.addAttribute("lineData", newlineData);
////		List<DepartMent> list = departMentService.getChildDepartmentByDepartmenType(Constant.DEPARTMENT_TYPE_DEPARTMENT);
////		model.addAttribute("departments", list);
////		model.addAttribute("selectDepartments", selectDepartments);
////		model.addAttribute("modifyFlag", "modify");
//		return "staff/add_data";
//	}
	
/********************************************************************* 二次开发end  *********************************************************************/
}
