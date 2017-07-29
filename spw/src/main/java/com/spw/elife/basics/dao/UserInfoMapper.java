package com.spw.elife.basics.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spw.elife.basics.bean.UserInfo;
import com.spw.elife.common.DaoMapper;
import com.spw.elife.staff.bean.Staff;

@DaoMapper
public interface UserInfoMapper {

    public UserInfo selectUserById(@Param("id") int id);

    public List<Staff> selectUserByUsername(@Param("workNum") String workNum,@Param("isDel") String isDel,@Param("status") String status);
    
    /**
     * 根据类型获取当前排序最大的用户名
     * @param type
     * @return
     */
    public String selectMaxUsername(@Param("type") String type);

    public void updateUser(UserInfo entity);

    public void updateUserSettings(UserInfo entity);

    public void insertUser(UserInfo entity);

    public void updateUserPassword(@Param("id") int id, @Param("password") String password);
    
    public void updateUserStatus(@Param("id") int id, @Param("status") int status);

	public void updateUserPassWord(@Param("id")int id,@Param("password") String password);

	public UserInfo queryPasswordByTel(@Param("username") String username);

	public UserInfo queryFactoryByTel(@Param("username") String username);

	public void updatePassword(@Param("phone")String phone, @Param("password") String password);

}
