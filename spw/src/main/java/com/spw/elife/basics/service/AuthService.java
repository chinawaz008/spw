package com.spw.elife.basics.service;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.spw.elife.basics.bean.Menu;
import com.spw.elife.basics.dao.AuthMapper;
import com.spw.elife.staff.bean.AuthorityEntity;

/**
 * 权限相关服务.
 *
 * @author Administrator
 */
@Service
public class AuthService {
	
	private static final Logger log = Logger.getLogger(AuthService.class);
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Resource
    private AuthMapper authMapper;
    
    /**
     * 构建个人权限菜单.
     *
     * @return 根菜单列表. 包含全部树结构。
     */
//    public List<Menu> buildPrincipalAuthority(String principal,String personType,String companyType) {
//        return buildAuthorityMenu(authMapper.selectPrincipalMenus(principal,personType,companyType));
//    }
//
//    public List<Menu> getAllMenu(String principal,String personType,String companyType){
//    	return authMapper.selectPrincipalMenus(principal,personType,companyType);
//    }
//    
//    public List<Menu> selectAllMenus(){
//    	return authMapper.selectAllMenus();
//    }
    

//    
//    /**
//     * 平台菜单
//     * @return
//     */
//    public List<Menu> buildPtAuthority() {
//        return buildAuthorityMenu(authMapper.selectPtMenus());
//    }
//    
//    /**
//     * 财务菜单
//     * @return
//     */
//    public List<Menu> buildREAuthority() {
//        return buildAuthorityMenu(authMapper.selectREMenus());
//    }

//    
//    /**
//     * 设置权限
//     */
//    @Transactional(rollbackFor=Exception.class)
//    public boolean updateAuth(String position,String auths){
//    	try{
//			String [] ids = position.split("@");
//			String roleId =  position;//ids[ids.length-1];//岗位id
//			String personType = ids[0];//人员类型1业务人员 2后援人员
//			String companyType = "";//1总公司 2分公司
////			if("2".equals(personType)){//后援人员
////				companyType = ids[1];
////				personType = Constant.PERSON_TYPE_1;
////			}else{
////				personType = Constant.PERSON_TYPE_0;
////			}
//			
//			//查询岗位功能模块
//			List<Menu> roleAuth = authMapper.selectPrincipalMenus(roleId,personType,companyType);
//			List<Integer> list = new ArrayList<Integer>();
//			for(Menu m : roleAuth){
//				list.add(m.getId());
//			}
//			
//			//先删除该岗位所有权限
//			if(list.size()>0){
//				authMapper.deleteEntity(list,roleId,personType,companyType);
//			}
//			
//			//再添加所有权限
//			List<Authority> au = new ArrayList<Authority>();
//			String [] authAll = auths.split(",");
//			for(String at : authAll){
//				Authority a = new Authority();
//				a.setPersonType(personType);
//				a.setMenuId(at);
//				a.setRoleId(roleId);
//				a.setCompanyType(companyType);
//				au.add(a);
//			}
//			authMapper.createEntity(au);
//    		return true;
//		} catch (Exception e) {
//			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//			log.error("设置权限异常"+e);
//			return false;
//		}
//   }
//    
    
//    /**
//     * 获取右边所有模块menu
//     * @param roleId
//     * @param personType
//     * @return
//     */
//    public List<Map<String,Object>> getRightMenu(String roleId,String personType,String companyType){
//    	//查询所有功能模块
//		List<Menu> authority = authMapper.selectAllMenus();
//					
//		//查询岗位功能模块
//		List<Menu> roleAuth = authMapper.selectPrincipalMenus(roleId,personType,companyType);
//		
//		List<Map<String,Object>> lmap = new ArrayList<Map<String,Object>>();
//		for(Menu m : authority){
//			Map<String,Object> map = new HashMap<String, Object>();
//			map.put("id", m.getId());
//			map.put("pId", m.getParentId());
//			map.put("name", m.getName());
//			if(m.getParentId() == 0){
//				map.put("iconSkin", "authset");
//			}else{
//				map.put("iconSkin", "auth");
//			}
//			for(Menu r : roleAuth){
//				if(m.getId() == r.getId()){
//					map.put("checked", "true");
//					break;
//				}
//			}
//			lmap.add(map);
//		}
//		return lmap;
//    }
    /******************************************************************************* 二次开发  *************************************************************/
    
	  /**
	  * 构建超级权限菜单.
	  *
	  * @return 根菜单列表. 包含全部树结构。
	  */
	 public List<Menu> buildSuperAuthority() {
	     return buildAuthorityMenu(authMapper.selectSuperMenus());
	 }
	 
    /**
     * 构建个人权限菜单.
     * @param employeeId 
     * @param roleId 
     *
     * @return 根菜单列表. 包含全部树结构。
     */
    public List<Menu> buildRedevelopePrincipalAuthority(String employeeId, String roleId) {
    	List<Menu> menus = new ArrayList<>();
    	if (StringUtils.isNotEmpty(employeeId)) {
    			menus = authMapper.selectRedevelopePrincipalMenus(employeeId,roleId);
    	}
        return buildAuthorityMenu(menus);
    }

    private List<Menu> buildAuthorityMenu(List<Menu> menus) {
    	Map<Integer,Menu> menuMap = new LinkedHashMap<Integer,Menu>();
    	for(Menu m:menus){
    		menuMap.put(m.getId(), m);
    	}
        ArrayList<Menu> roots = new ArrayList<Menu>();
        for (Map.Entry<Integer, Menu> entry : menuMap.entrySet()) {
            //Integer id = entry.getKey();
            Menu menu = entry.getValue();

            if (0 == menu.getParentId()) {
                roots.add(menu);
            } else {
                Menu parent = menuMap.get(menu.getParentId());
                if(parent!=null)
                parent.addChildren(menu);
            }
        }
        return roots;
    }
    /**
     * 由岗位获取按钮权限
     * 
     * @param position
     * @param string 
     * @return
     */
	public List<String> getButtonAuthorization(String employeeId, String roleId) {
		List<String> buttons = new ArrayList<>();
		if (StringUtils.isNotEmpty(employeeId)) {
    		buttons = authMapper.getButtonAuthorization(employeeId, roleId);
    	}
		return buttons;
	}
	
	/**
	 * 获取所有按钮
	 * 
	 * @return
	 */
	public List<String> getAllButtons() {
		return authMapper.getAllButtons();
	}
//	
//    /**
//     * 获取功能模块以及相关按钮
//     * 
//     * @param departMentId 
//     * @param roleId
//     * @param personType 
//     * @return
//     */
//	public List<Map<String, Object>> getRedevelopeRightMenu( String departMentId, String roleId, String personType) {
//		//查询所有功能模块
//		List<Menu> authority = authMapper.selectRedevelopeAllMenus();
//					
//		//查询岗位功能模块
//		List<Menu> roleAuth = new ArrayList<>();
////		if (Constant.FRONT_LINE.equals(personType)) {
//			roleAuth = authMapper.selectRedevelopeMenus(departMentId,roleId,null);
////		}
////		else {
////			roleAuth = authMapper.selectRedevelopeMenus(null,roleId,personType);
////		}
//		List<Map<String,Object>> lmap = new ArrayList<Map<String,Object>>();
//		for(Menu m : authority){
//			Map<String,Object> map = new HashMap<String, Object>();
//			map.put("id", m.getId());
//			map.put("pId", m.getParentId());
//			map.put("name", m.getName());
//			map.put("type", m.getType());
//			if(m.getParentId() == 0){
//				map.put("iconSkin", "authset");
//			}else{
//				map.put("iconSkin", "auth");
//			}
//			for(Menu r : roleAuth){
//				if(m.getId() == r.getId() && m.getType().equals(r.getType())){
//					map.put("checked", "true");
//					break;
//				}
//			}
//			lmap.add(map);
//		}
//		return lmap;
//	}
//
//	/**
//	 * 菜单以及按钮权限追加修改
//	 * 
//	 * @param departMentId
//	 * @param positionId
//	 * @param auths
//	 * @param personType
//	 * @return
//	 */
//    @Transactional(rollbackFor=Exception.class)
//	public boolean updateRedevelopeAuth(String departMentId, String positionId, String auths, String personType) {
//    	try {
//        	Date currentTime = new Date();
//        	String currentDate = sdf.format(currentTime);
//        	String userId = Utils.getUserId();
//    		RoleLevelEntity head = authMapper.selectPositionAuthByPositionId(departMentId, positionId,null);
//    		if (head == null) {
//    			head = new RoleLevelEntity();
//    			head.setDepartMentId(departMentId);
//    			head.setPositionId(positionId);
////    			head.setPersonType(personType);
//    			authMapper.insertRoleLevel(head);
//    			log.info(userId + "于" + currentDate + "增加岗位权限，id：" + positionId);
//    		}
//    		else {
//    			log.info(userId + "于" + currentDate + "修改岗位权限，id：" + positionId);
//    		}
//    		if (head != null && StringUtils.isNotBlank(head.getId())) {// 权限详情
//    			List<RoleLevelLineEntity> lines = new ArrayList<>();
//    			String [] authAll = auths.split(",");
//    			for (String auth : authAll) {
//    				if (StringUtils.isNotEmpty(auth)) {
//    					String[] values = auth.split("@");
//    					if (values.length ==2) {
//    						RoleLevelLineEntity line = new RoleLevelLineEntity();
//    						line.setParentId(head.getId());
//    						line.setRelationId(values[0]);
//    						line.setType(values[1]);
//    						lines.add(line);
//    					}
//    				}
//    			}
//    			// 删除原来权限
//    			authMapper.deleteRoleLineByParentId(head.getId());
//    			authMapper.insertRoleLines(lines);
//    		}
//    		return true;
//		} catch (Exception e) {
//			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//			log.error("设置权限异常"+e);
//			return false;
//		}
//	}
//
//
//	/**
//	 * 数据权限数据 获取
//	 * @param lookup
//	 * @return
//	 */
//	public Pager<DataAuthorityEntity> queryDataList(CommonLookup lookup) {
//		Pager<DataAuthorityEntity> pager = new Pager<DataAuthorityEntity>();
//		int total = authMapper.countEntity(lookup);
//		pager.setPage(lookup.getPage());
//		pager.setSize(lookup.getSize());
//		pager.setTotal(total);
//		@SuppressWarnings("unchecked")
//		List<DataAuthorityEntity> entities = pager.isOverflowed() ? Collections.EMPTY_LIST : authMapper.queryDataList(lookup);
//		for (DataAuthorityEntity entity : entities) {
////			StringBuilder positionName = new StringBuilder();
////			if (StringUtils.isNotEmpty(entity.getLineType())) {
////				DepartMent depart = departMentService.getDepartment(entity.getLineType());
////				if (depart != null) {
////					if (StringUtils.isNotEmpty(depart.getName())) {
////						String[] sds = depart.getName().split(Constant.CONSTANT_SP);
////						positionName.append(sds[0]).append(Constant.CONSTANT_SAPCE);
////					}
////				}
////			}
////			if (StringUtils.isNotEmpty(entity.getBranchCompanyName())){
////				positionName.append(entity.getBranchCompanyName()).append(" ");
////			}
////			if (StringUtils.isNotEmpty(entity.getCountyFranchiseesName())) {
////				positionName.append(entity.getCountyFranchiseesName()).append("督训区 ");
////			}
////			if (StringUtils.isNotEmpty(entity.getStoreName())) {
////				positionName.append(entity.getStoreName()).append("分部 ");
////			}
////			if (StringUtils.isNotEmpty(entity.getPosition())) {
////				String positions[] = entity.getPosition().split(Constant.CONSTANT_SPLIT);
////				for (String pos : positions) {
////					if (StringUtils.isNotEmpty(pos)) {
////						String name = "";
////						if (StringUtils.isNotEmpty(entity.getBranchCompanyName())){
////							PositionStation poentity = departMentService.getDepartmentPosition(pos);
////							if (poentity != null) {
////								name = poentity.getName();
////							}
////						}
////						else {
////							name = departMentService.getWholeDepartment(pos, null, true);
////						}
////						positionName.append(name);
////					}
////				}
////			}
////			entity.setPositionName(positionName.toString());
//		}
//		pager.setElements(entities);
//		return pager;
//	}
//
//	/**
//	 * 数据权限登录
//	 * @param staffId 
//	 * 
//	 * @param entityLine 业务权限
//	 * @param entity 行政权限
//	 * @return 
//	 */
//    @Transactional(rollbackFor=Exception.class)
//	public Map<String, Object> saveData(String staffId, AuthorityEntity entityLine, AuthorityEntity entity) {
//		Map<String,Object> map = new HashMap<String,Object>();
//		try {
//			List<AuthorityEntity> insertList = new ArrayList<>();
//			if (!StringUtils.isNotEmpty(staffId)) {
//				map.put("result", 0);
//				map.put("message","请选择分配人员！");
//				return map;
//			}
//			// 业务权限数据
//			if (Constant.DATA_TYPE1.equals(entityLine.getType())) {
//				String department = entityLine.getDepartmentId();
//				if (StringUtils.isNotEmpty(department)) {
//					String[] deArray = department.split(Constant.CONSTANT_SPLIT);
//					for (String de : deArray) {
//						AuthorityEntity line = new AuthorityEntity();
//						line.setStaffId(staffId);
//						line.setType(entityLine.getType());
//						line.setDepartmentId(de);
//						insertList.add(line);
//					}
//				}
//			}
//			else if (Constant.DATA_TYPE2.equals(entityLine.getType())) {
//				String department = entityLine.getDepartmentId();// 渠道
//				String province = entityLine.getProvinceId();// 省分公司
//				String orgzation = entityLine.getOrganizationId();// 督训区
//				if (StringUtils.isNotEmpty(department) && StringUtils.isNotEmpty(province)) {
//					String[] deArray = department.split(Constant.CONSTANT_SEMI);
//					String[] proArray = province.split(Constant.CONSTANT_SEMI);
//					String[] orgArray = orgzation.split(Constant.CONSTANT_SEMI);
//						for (int i = 0; i < deArray.length; i++) {
//							String deId = deArray[i];
//							if (StringUtils.isNotEmpty(deId) &&  proArray.length > i) {
//								String proId = proArray[i];
//								if (StringUtils.isNotEmpty(proId) && orgArray.length > i) {
//									String org = orgArray[i];
//									if (StringUtils.isNotEmpty(org)) {
//										// 分配到督训区
//										String[] orgs = org.split(Constant.CONSTANT_SPLIT);
//										for (String du : orgs) {
//											AuthorityEntity line = new AuthorityEntity();
//											line.setStaffId(staffId);
//											line.setType(entityLine.getType());
//											line.setDepartmentId(deId);
//											line.setProvinceId(proId);
//											line.setOrganizationId(du);
//											insertList.add(line);
//										}
//									}
//									else {
//										// 分配到省分
//										AuthorityEntity line = new AuthorityEntity();
//										line.setStaffId(staffId);
//										line.setType(entityLine.getType());
//										line.setDepartmentId(deId);
//										line.setProvinceId(proId);
//										insertList.add(line);
//									}
//								} else if (StringUtils.isNotEmpty(proId)){
//									// 分配到省分
//									AuthorityEntity line = new AuthorityEntity();
//									line.setStaffId(staffId);
//									line.setType(entityLine.getType());
//									line.setDepartmentId(deId);
//									line.setProvinceId(proId);
//									insertList.add(line);
//								}
//							}
//						}
//				}
//			}
//			
//			// 行政数据权限
//			if (Constant.DATA_TYPE3.equals(entity.getType())) {
//				String department = entity.getDepartmentId();
//				if (StringUtils.isNotEmpty(department)) {
//					String[] deArray = department.split(Constant.CONSTANT_SPLIT);
//					for (String de : deArray) {
//						if (StringUtils.isNotEmpty(de)) {
//							AuthorityEntity line = new AuthorityEntity();
//							line.setStaffId(staffId);
//							line.setType(entity.getType());
//							line.setDepartmentId(de);
//							insertList.add(line);
//						}
//					}
//				}
//			}
//			// 删除原来权限
//			authMapper.deleteAuthority(staffId);
//			if (insertList.size() > 0) {
//				authMapper.insertAuthorityEntitys(insertList);
//			}
//		} catch (Exception e) {
//			map.put("result", 0);
//			map.put("message","数据权限登录异常");
//			log.error("数据权限登录异常:" + e);
//			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//			e.printStackTrace();
//		}
//		
//		return map;
//	}
//
    /**
     * 查询数据权限
     * @param staffId
     * @return
     */
	public List<AuthorityEntity> getAuthortyByStaffId(String staffId) {
		List<AuthorityEntity> returnList = authMapper.getAuthortyByStaffId(staffId);
		return returnList;
	}
}
