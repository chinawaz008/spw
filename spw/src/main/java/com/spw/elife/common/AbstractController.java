package com.spw.elife.common;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.WebUtils;

import toonasofts.conductor.util.Tools;

import com.spw.elife.basics.bean.CommonLookup;
import com.spw.elife.basics.bean.Lookup;
import com.spw.elife.basics.service.AuthService;
import com.spw.elife.common.exception.UnauthenticationException;
import com.spw.elife.staff.bean.AuthorityEntity;
import com.spw.elife.staff.bean.Staff;
import com.spw.elife.staff.service.StaffService;
import com.spw.elife.util.Constant;


@Controller
public abstract class AbstractController extends AbstractComponent{
    public static final String SESSIONKEY_PRINCIPAL = "principal";
    public static final String SESSIONKEY_SUFFIX_LOOKUP = "lookup";
    public Logger logger = Logger.getLogger(AbstractController.class);
	@Resource
	private AuthService authService;
	@Resource
	private StaffService staffService;

     
    /**
     * 获取当前用户主体对象.
     *
     * @return 当前用户主体对象
     * @throws IllegalStateException 当前线程不是web请求抛出此异常.
     */
    protected Principal getPrincipal() throws IllegalStateException {
        return getSessionAttribute(SESSIONKEY_PRINCIPAL);
    }

    /**
     * 获取用户最后一次在当前模块（Controller）提交的查询信息. 不会返回{@code null}.
     *
     * @return 用户最后一次在当前模块提交的查询信息
     * @throws IllegalStateException 当前线程不是web请求抛出此异常.
     */
    protected Lookup getLookup() throws IllegalStateException {
        Lookup lookup = getSessionAttribute(getClass().getName() + "." + SESSIONKEY_SUFFIX_LOOKUP);
        if (lookup == null) {
            lookup = instanceLookup();
            setSessionAttribute(getClass().getName() + "." + SESSIONKEY_SUFFIX_LOOKUP, lookup);
        }
        setSessionAttribute(SESSIONKEY_SUFFIX_LOOKUP, lookup);
        return lookup;
    }

    /**
     * 实例化查询信息. 在通用查询条件不满足时，子类应覆盖本方法提供具体查询信息实现类。
     *
     * @return 实例化查询信息
     */
    protected Lookup instanceLookup() {
        return new CommonLookup();
    }

    /**
     * 保存用户提交的查询信息.
     *
     * @param lookup 查询信息
     * @throws IllegalStateException 当前线程不是web请求抛出此异常.
     */
    protected void setLookup(Lookup lookup) throws IllegalStateException {
        setSessionAttribute(getClass().getName() + "." + SESSIONKEY_SUFFIX_LOOKUP, lookup);
    }

    /**
     * 获取当前Session中对象. 若当前无{@code session}则返回{@code null}且不会创建{@code session}.
     *
     * @param <T> 保存的对象类型.
     * @param key 对象保存键值.
     * @return 保存的对象或{@code null}.
     * @throws IllegalStateException 当前线程不是web请求抛出此异常.
     */
    @SuppressWarnings("unchecked")
	protected <T> T getSessionAttribute(String key) throws IllegalStateException {
        return (T) WebUtils.getSessionAttribute(currentRequest(), key);
    }

    /**
     * 获取当前Session中对象. 若当前无{@code session}则创建一个.
     *
     * @param <T> 保存的对象类型.
     * @param key 对象保存键值.
     * @param clazz 保存的对象类型.
     * @return 保存的对象.
     * @throws IllegalStateException 当前线程不是web请求抛出此异常.
     */
    @SuppressWarnings("unchecked")
	protected <T> T getOrCreateSessionAttribute(String key, Class<T> clazz) throws IllegalStateException {
        HttpSession session = currentRequest().getSession();
        return (T) WebUtils.getOrCreateSessionAttribute(session, key, clazz);
    }

    /**
     * 保存变量到当前Session. 若保存的对象为{@code null}，则移除该键值所保存的对象。
     *
     * @param key 键值.
     * @param obj 要保存的对象或{@code null}.
     * @throws IllegalStateException 当前线程不是web请求抛出此异常.
     */
    protected void setSessionAttribute(String key, Object obj) throws IllegalStateException {
        WebUtils.setSessionAttribute(currentRequest(), key, obj);
    }

    /**
     * 获取当前Request对象.
     *
     * @return 当前Request对象或{@code null}.
     * @throws IllegalStateException 当前线程不是web请求抛出此异常.
     */
    protected HttpServletRequest currentRequest() throws IllegalStateException {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attrs == null) {
            throw new IllegalStateException("当前线程中不存在 Request 上下文");
        }
        return attrs.getRequest();
    }

    /**
     * 获取当前session对象. 若当前线程不是web请求或当前尚未创建{@code session}则返回{@code null}.
     *
     * @return 当前session对象或{@code null}.
     */
    protected HttpSession currentSession() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attrs == null) {
            return null;
        }
        return attrs.getRequest().getSession(false);
    }
    
    public Properties getProp(){
    	  InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("configuration.properties");
    	  Properties p = new Properties();
    	  try {
    	  p.load(inputStream);
    	  } catch (IOException e1) {
    		  System.out.println("getProp->获取连接信息异常"+Constant.getTrace(e1));
    	  }
		return p;
    }
    public  String getUser(){
    	 String name = "";
		 HttpSession session = currentSession();
		 if (session == null) {
			throw new UnauthenticationException();
		 }
		 Principal principal = (Principal) session.getAttribute(SESSIONKEY_PRINCIPAL);
		 if(principal!=null && principal.getUserInfo()!=null) 
			 name = principal.getUserInfo().getUsername();
		 return name;
   } 
     
    public  String getUserId(){
   	 String name = "";
		 HttpSession session = currentSession();
		 if (session == null) {
			return name;
		 }
		 Principal principal = (Principal) session.getAttribute(SESSIONKEY_PRINCIPAL);
		 if(principal!=null && principal.getUserInfo()!=null) 
			 name = String.valueOf(principal.getUserInfo().getId());
		 return name;
   }
   /**
    * 打印登录操作轨迹
    */
   public void printStackTrace(String operation){
	   //登录人+时间
	   logger.info(
			   "当前登录人"+getUser()+
			   "时间："+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+
			   "操作："+operation);
   }
   
   
//   /**
//	 * 根据登录人获得登录人的搜索条件
//	 */
//	public StaffLookup getLookup(UserInfo user ,StaffLookup lookup){
//		if(user!=null){//1.判断是总公司还是分公司
//			if(StringUtils.isNotBlank(user.getCompanyType()) && !"0".equals(user.getCompanyType())){//分公司
//				if(RENSHIBU_FEN.equals(user.getType())){//人事部
//					Company org = companyService.queryEntityById(user.getCompanyType());
//					if(org!=null)
//					lookup.setProvinceId(org.getProvinceId()+"");
//				}else{
//					if(user.getLineType()!=null && user.getLineType()!=0)
//						lookup.setLineType(user.getLineType());
//					if(user.getCompanyType()!=null && user.getCompanyType()!="")
//						lookup.setBranchCompanyId(user.getCompanyType());
//					if(user.getCountyFranchiseesId()!=null && user.getCountyFranchiseesId()!="")
//						lookup.setCountyFranchiseesId(user.getCountyFranchiseesId());
//				}
//			}else{//总公司
//				if(BANGFU.equals(user.getType())){//2.判断是否是帮扶人员
//					//查看帮扶人员现在正在帮扶的分公司或督训区
//					HelpPerson hp = helpPersonMapper.getPersonPlaceByWorkNum(user.getWorkNum());
//					if(hp!=null && hp.getLineType()!=null && hp.getLineType()!=""){
//						lookup.setLineType(Integer.parseInt(hp.getLineType()));
//					}else{
//						lookup.setLineType(9999);
//					}
//					if(hp!=null && hp.getBranchCompanyId()!=null && hp.getBranchCompanyId()!="")
//					lookup.setBranchCompanyId(hp.getBranchCompanyId());
//					if(hp!=null && hp.getDxqId()!=null && hp.getDxqId()!="")
//					lookup.setCountyFranchiseesId( hp.getDxqId());
//				} 
//			}
//		}
//		return lookup;
//	}
//	
//	/**
//	 * 根据登录人获得登录人的搜索条件
//	 */
//	public CommonLookup getLookup(UserInfo user ,CommonLookup lookup){
//		if(user!=null){//1.判断是总公司还是分公司
//			if(StringUtils.isNotBlank(user.getCompanyType()) && !"0".equals(user.getCompanyType())){//分公司
//					if(RENSHIBU_FEN.equals(user.getType())){//人事部
//						Company org = companyService.queryEntityById(user.getCompanyType());
//						if(org!=null)
//						lookup.setProvinceId(org.getProvinceId()+"");
//					}else{
//						if(user.getLineType()!=null && user.getLineType()!=0)
//							lookup.setLineType(user.getLineType());
//						if(user.getCompanyType()!=null && user.getCompanyType()!="")
//							lookup.setBranchCompanyId(user.getCompanyType());
//						if(user.getCountyFranchiseesId()!=null && user.getCountyFranchiseesId()!="")
//							lookup.setCountyFranchiseesId(user.getCountyFranchiseesId());
//					}
//			}else{//总公司
//				if(BANGFU.equals(user.getType())){//2.判断是否是帮扶人员
//					//查看帮扶人员现在正在帮扶的分公司或督训区
//					HelpPerson hp = helpPersonMapper.getPersonPlaceByWorkNum(user.getWorkNum());
//					if(hp!=null && hp.getLineType()!=null && hp.getLineType()!=""){
//						lookup.setLineType(Integer.parseInt(hp.getLineType()));
//					}else{
//						lookup.setLineType(9999);
//					}
//					if(hp!=null && hp.getBranchCompanyId()!=null && hp.getBranchCompanyId()!="")
//					lookup.setBranchCompanyId(hp.getBranchCompanyId());
//					if(hp!=null && hp.getDxqId()!=null && hp.getDxqId()!="")
//					lookup.setCountyFranchiseesId( hp.getDxqId());
//				} 
//			}
//		}
//		return lookup;
//	}
	
	/**
	 * 数据权限配置 look默认设置
	 * 
	 * @param userInfo
	 * @param lookup
	 * @param url
	 * @return
	 */
	public void setDefultLookup(String staffId, Lookup lookup) {
		if(staffId != null){
			List<AuthorityEntity> list = authService.getAuthortyByStaffId(staffId);
			// 根据类型分类
			List<String> channelList = new ArrayList<>();// 总公司渠道检索条件集合
			List<String> companyList = new ArrayList<>();// 省分检索条件集合
			List<String> governorList = new ArrayList<>();// 督训区检索条件集合
			List<String> departmentList = new ArrayList<>();// 部门检索条件集合
			String[] composeKeys = {"type"};
			List<Object> objs = Tools.composeSlip(list, composeKeys);
			for (Object obj : objs) {
				@SuppressWarnings("unchecked")
				List<AuthorityEntity> subList = (List<AuthorityEntity>) obj;
				AuthorityEntity entity = subList.get(0);
				for (AuthorityEntity sub : subList) {
					if (Constant.DATA_TYPE1.equals(entity.getType())) {// 业务权限-总公司
						channelList.add(sub.getDepartmentId());
						// 子部门
//						List<DepartMent> slist = new ArrayList<>();
//						slist = departMentService.getAllDepartments(slist, sub.getDepartmentId());
//						for (DepartMent depart : slist) {
//							channelList.add(depart.getId());
//						}
					}
					else if (Constant.DATA_TYPE2.equals(entity.getType())) {// 业务权限-分公司
						if (StringUtils.isNotEmpty(sub.getOrganizationId())) {
							governorList.add(sub.getOrganizationId());
						}
						else {
							if (StringUtils.isNotEmpty(sub.getDepartmentId()) && StringUtils.isNotEmpty(sub.getProvinceId())) {
								// 查询省分
								companyList.add(sub.getProvinceId());
							}
						}
					}
					else if (Constant.DATA_TYPE3.equals(entity.getType())) {// 行政权限
						departmentList.add(sub.getDepartmentId());
					}
				}
			}
			// lookup默认设置
			// 总公司权限
			if (channelList.size() < 1) {
				channelList = null;
			}
			lookup.setChannelList(channelList);// lineType
			// 分公司权限
			if (governorList.size() < 1) {
				governorList = null;
			}
			lookup.setGovernorList(governorList);// countyFranchiseesId
			if (companyList.size() < 1) {
				companyList = null;
			}
			lookup.setCompanyList(companyList);// branchCompanyId
			// 部门权限
			if (departmentList.size() < 1) {
				departmentList = null;
			}
			lookup.setDepartmentList(departmentList);// orgType
			// 默认的人员检索条件-登录人
			lookup.setDefultStaffId(staffId);
			// 设置人员角色
//			Staff staf = staffService.queryEntityById(Integer.parseInt(staffId));
//			if (staf != null && StringUtils.isEmpty(staf.getPosition())) {// 管理员
//				lookup.setStaffRole(0);
//			}
		}
	}
}
