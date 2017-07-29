package com.spw.elife.basics.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spw.elife.basics.bean.Menu;
import com.spw.elife.common.DaoMapper;
import com.spw.elife.staff.bean.AuthorityEntity;


@DaoMapper
public interface AuthMapper {
//
//    public List<Menu> selectPrincipalMenus(@Param("roleId")String roleId,@Param("personType")String personType,@Param("companyType")String companyType);
//
//    public List<Menu> selectSuperMenus();
//    
//    public List<Menu> selectPtMenus();
//    
//    public List<Menu> selectREMenus();
//    
//    public void createEntity(List<Authority> list);
//    
//    public void deleteEntity(@Param("list")List<Integer> list,@Param("roleId")String roleId,@Param("personType")String personType,@Param("companyType")String companyType);
//    
//    public List<Menu> selectAllMenus();
//
//	public List<Menu> selectRedevelopeMenus(@Param("departMentId")String departMentId,@Param("roleId")String roleId,@Param("personType") String personType);
//
//	public List<Menu> selectRedevelopeAllMenus();
//
//	public RoleLevelEntity selectPositionAuthByPositionId(@Param("departMentId")String departMentId, @Param("roleId")String positionId,@Param("personType") String personType);
//
//	public void insertRoleLevel(RoleLevelEntity head);
//
//	public void deleteRoleLineByParentId(String id);
//
//	public void insertRoleLines(List<RoleLevelLineEntity> lines);
//
//	public List<String> selectRolesByurl(@Param("url")String url);
//
////	public List<RoleEntity> getDataRoles(@Param("ids")List<String> ids, @Param("params")List<Staff> params);
//
//	public int countEntity(Object obj);
//
//	public List<DataAuthorityEntity> queryDataList(Object obj);
//
//	public void insertAuthorityEntitys(@Param("list")List<AuthorityEntity> insertList);
//
//	public List<AuthorityEntity> getAuthortyByStaffId(@Param("staffId")String staffId);
//
//	public void deleteAuthority(@Param("staffId")String staffId);
//	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public List<Menu> selectSuperMenus();
	
	public List<Menu> selectRedevelopePrincipalMenus(@Param("employeeId")String employeeId,@Param("roleId") String roleId);

	public List<String> getButtonAuthorization(@Param("employeeId")String employeeId,@Param("roleId") String roleId);

	public List<String> getAllButtons();
	
	public List<AuthorityEntity> getAuthortyByStaffId(@Param("staffId")String staffId);
}
