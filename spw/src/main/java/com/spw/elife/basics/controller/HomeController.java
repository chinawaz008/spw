package com.spw.elife.basics.controller;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spw.elife.basics.bean.Menu;
import com.spw.elife.basics.bean.UserInfo;
import com.spw.elife.basics.service.AuthService;
import com.spw.elife.basics.service.GeneralService;
import com.spw.elife.basics.service.UserInfoService;
import com.spw.elife.common.AbstractController;
import com.spw.elife.common.Principal;
import com.spw.elife.company.bean.CompanyEntity;
import com.spw.elife.company.bean.EmployeeEntity;
import com.spw.elife.company.service.CompanyService;
import com.spw.elife.company.service.EmployeeService;
import com.spw.elife.staff.dao.StaffMapper;
import com.spw.elife.staff.service.StaffService;
import com.spw.elife.util.Utils;

@Controller
@RequestMapping
public class HomeController extends AbstractController {
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private AuthService authService;
    @Resource
    private StaffService staffservice;
    @Resource
    private StaffMapper staffMapper;
    @Resource
    private GeneralService generalService;
    @Resource
    private CompanyService companyService;
    @Resource 
    private EmployeeService employeeService;
    private Logger log = Logger.getLogger(HomeController.class);
    /**
     * 跳转到首页
     * @throws JSONException 
     * @throws IOException 
     * @throws ParseException 
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model) throws JSONException, IOException, ParseException {
    	Principal principal = getSessionAttribute(SESSIONKEY_PRINCIPAL);
    	UserInfo userInfo = principal.getUserInfo();
    	try {
    		if(userInfo!=null){
      	}
		} catch (Exception e) {
			e.printStackTrace();
		}

        return "index3";
    }

	@RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> login(String username, String password) {
		String roleType = "";
    	Map<String,Object>  map = new HashMap<>();
    	UserInfo userInfo = new UserInfo();
    	CompanyEntity company = companyService.getCompanyByPhone(username);
    	if (company != null) {
    		roleType = "admin";
    	}
    	List<EmployeeEntity> slist = employeeService.findEmployeeByName(username);
//        List<Staff> slist = userInfoService.findUserByUsername(username);
        if (slist!=null && slist.size()==0) {
        	map.put("result", "0");
        	map.put("message", "用户名不存在");
        	return map;
        }else if (slist!=null && slist.size()>1){
        	map.put("result", "0");
        	map.put("message", "用户名重复太多，请用手机号码登录");
        	return map;
        }else if (!slist.get(0).getPassWord().equals(DigestUtils.md5Hex(password))) {
        	map.put("result", "0");
        	map.put("message", "用户名或密码不正确");
        	return map;
        }
        EmployeeEntity employee = slist.get(0);
        
        userInfo.setId(Integer.parseInt(employee.getId()));
        userInfo.setUsername(employee.getName());
        setSessionAttribute("userinfo", userInfo);
        Principal principal = new Principal();
        List<Menu> authority =null;
        Integer timeOut = 60; // 默认60分钟
        //权限控制菜单
        List<String> buttons = new ArrayList<>();
        if(!StringUtils.isNotBlank(roleType)){
        	authority = authService.buildRedevelopePrincipalAuthority(employee.getId(),employee.getRoleId());
        	buttons = authService.getButtonAuthorization(employee.getId(),employee.getRoleId());
        }else{//如果是超级管理员 就查询所有menu
        	authority = authService.buildSuperAuthority();
        	buttons = authService.getAllButtons();
        }
        if (authority.size() < 1) {
        	map.put("result", "0");
        	map.put("message", "您没有权限登录！");
        	return map;
        }
        principal.setMenus(authority);
        principal.setUserInfo(userInfo);
        setSessionAttribute(SESSIONKEY_PRINCIPAL, principal);
        setSessionAttribute("buttonAuthorization", buttons);
        currentSession().setMaxInactiveInterval(timeOut*60);
        map.put("result", "1");
        return map;
    }
    
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(){
    	setSessionAttribute(SESSIONKEY_PRINCIPAL, null);
    	return "redirect:" + Utils.retrieveSavedRequest();
    }
    /**
	 * 跳转到资料修改页
	 * @param staffId
	 * @return
	 */
    @RequestMapping(value = "/staff/edit_info", method = RequestMethod.GET)
    public String editInfo(Model model){
    	UserInfo userInfo = getSessionAttribute("userinfo");
		try {
//			Staff st =  staffservice.queryEntityById(userInfo.getId());
//			//所在地：
//			//省份
//			List<Province> plist = webServiceAreaService.queryProvince();
//			model.addAttribute("plist", plist);
//			//市区
//			if(st.getProvinceId()!=null){
//				List<Region> relist = webServiceAreaService.queryRegion(st.getProvinceId());
//				model.addAttribute("relist", relist);
//			}
//			//县
//			if(st.getRegionId()!=null){
//			List<County> clist = webServiceAreaService.queryCounty(st.getRegionId());
//			model.addAttribute("clist", clist);
//			}
//			model.addAttribute("staff", st);
		} catch (Exception e) {
			log.error("跳到更新页面错误！"+e);
			e.printStackTrace();
		}
		return "edit";
    }
    
    /**
	 * 修改实体
	 * @param id
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/staff/updateInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> updateEntity(int staffId,String staff,String oldPasswd,String newPasswd1) {
		Map<String,Object> map = new HashMap<String, Object>();
		try {
//			JSONObject json_staff = JSONObject.fromObject(staff);
//			Staff entity = (Staff)JSONObject.toBean(json_staff, Staff.class);
//			entity.setId(staffId);
//			Staff st =  staffservice.queryEntityById(staffId);
//			if(StringUtils.isNotBlank(oldPasswd)){
//				if(DigestUtils.md5Hex(oldPasswd.trim()).equals(st.getPassword())){
//					if(StringUtils.isNotBlank(newPasswd1))
//					entity.setPassword(DigestUtils.md5Hex(newPasswd1.trim()));
//				}else{
//					map.put("result", "0");
//					map.put("message", "旧密码不正确！");
//					return map;
//				}
//			}
//			// 手机号码唯一
//			String phone = staffservice.checkMobile(entity.getPhoneNum());
//			if (StringUtils.isNotBlank(phone) && !(st.getPhoneNum().equals(phone))) {
//				map.put("result", "0");
//				map.put("message", "该手机号码已经存在！");
//				return map;
//			}
//			staffservice.updateEntity(entity);
		    log.info("修改人员基本信息成功，人员编号"+staffId);
	    	map.put("result", "1");
			map.put("message", "修改成功！");
		} catch (Exception e) {
			 map.put("result", "0");
			 map.put("message", "系统异常！");
			 log.error("修改基本信息异常！"+e);
		}
		return map;
	}

	/**
	 * 查看版本号
	 * @return
	 */
	@RequestMapping(value = "/api/selectVersion", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject selectVersion() {
		JSONObject jo = new JSONObject();
		try {
			jo.accumulate("result", "1");
			String versionNo = staffMapper.selectVersion();
			jo.accumulate("version", versionNo);
		} catch (Exception e) {
			jo.accumulate("result", "0");
			jo.accumulate("message", "查看版本号异常！");
		}
		return jo;
	}
	
	/**
	 * 首页top跳转
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
    @RequestMapping(value = "/manage/{id}", method = RequestMethod.GET)
    public String index_manage(@PathVariable int id,Model model) {
    	model.addAttribute("id",id);
    	return "index/manage";
    }
	
}