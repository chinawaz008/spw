package com.spw.elife.basics.service;


import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import com.spw.elife.basics.bean.UserInfo;
import com.spw.elife.basics.dao.UserInfoMapper;
import com.spw.elife.staff.bean.Staff;


/**
 * 处理用户相关服务.
 *
 * @author lip
 */
@Service
public class UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;

    /**
     * 根据ID查询用户实体.
     *
     * @param id 用户ID
     * @return 用户实体
     */
    public UserInfo findUserById(int id) {
        return userInfoMapper.selectUserById(id);
    }

    /**
     * 根据岗位号查人
     * @param username
     * @param isDel
     * @param status
     * @return
     */
    public List<Staff> findUserByUsername(String username) {
    	//"0" 代表未删除 
        return userInfoMapper.selectUserByUsername(username,"0","1");
    }

    /**
     * 保存用户信息的修改.
     *
     * @param entity 用户信息
     */
    public void saveUser(UserInfo entity) {
        userInfoMapper.updateUser(entity);
    }

    /**
     * 保存用户配置信息.
     *
     * @param entity 用户
     */
    public void saveUserSettings(UserInfo entity) {
        userInfoMapper.updateUserSettings(entity);
    }

    /**
     * 创建新用户.
     *
     * @param entity 用户信息
     */
    public void createUser(UserInfo entity) {
        userInfoMapper.insertUser(entity);

    }

    /**
     * 设置密码.
     *
     * @param email 账号邮箱.
     * @param password 新的密码
     */
    public void passwd(int id, String password) {
        userInfoMapper.updateUserPassword(id, DigestUtils.md5Hex(password));
    }
    
    public String getUsername(String type){
    	String maxUsername = userInfoMapper.selectMaxUsername(type);//270027
    	if(null != maxUsername){
    		String numStr = maxUsername.replaceFirst(type, "");//00
    		int n = numStr.length();//取出字符串的长度 2
            int num = Integer.parseInt(numStr)+1;//将该数字加一 01
            String added = String.valueOf(num);//
            n = Math.min(n, added.length());
            //拼接字符串
            return maxUsername.subSequence(0, maxUsername.length()-n)+added;
    	}else{
    		if("BM".equals(type) || "CW".equals(type)){
    			return type.concat("001");
    		}else{
    			return type.concat("0001");
    		}
    		
    	}
    }
    
    /**
     * 更新用户状态
     * @param id
     * @param status
     */
    public void updateUserStatus(Integer id, Integer status){
    	userInfoMapper.updateUserStatus(id, status);
    }
    
    /**
     * 重置用户密码
     * @param id
     * @param status
     */
    public String updateUserPassword(Integer id){
    	String password = String.valueOf(Math.round((Math.random() * 9 + 1) * 100000));
    	userInfoMapper.updateUserPassWord(id,DigestUtils.md5Hex(password) );
    	return password;
    }

	public UserInfo findpasswordBytel(String username) {
		// TODO Auto-generated method stub
		 return userInfoMapper.queryPasswordByTel(username);
	}
	public UserInfo findFactoryBytel(String username) {
		 return userInfoMapper.queryFactoryByTel(username);
	}

	public void updatePasswd(String phone, String passwdOld) {
		// TODO Auto-generated method stub
		userInfoMapper.updatePassword(phone, DigestUtils.md5Hex(passwdOld));
	}
}
