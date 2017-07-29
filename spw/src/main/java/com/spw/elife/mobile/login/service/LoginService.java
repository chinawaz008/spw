package com.spw.elife.mobile.login.service;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.spw.elife.basics.service.UserInfoService;
import com.spw.elife.mobile.common.Response;
import com.spw.elife.mobile.login.dao.MobileLoginMapper;
import com.spw.elife.staff.bean.Staff;

/**
 * 
 * 用户登录Service
 * 
 * @author Administrator
 * 
 */
@Service
public class LoginService {

	@Resource
	private MobileLoginMapper mobileLoginMapper;
	@Resource
	private UserInfoService userInfoService;

	private Logger log = Logger.getLogger(LoginService.class);

	// 登录名错误
	private static final int Error_Username = 0;

	// 密码错误
	private static final int Error_Password = 0;

	// 重复登录
	private static final int Repeat_Login = 1;

	// 操作成功
	private static final int Opration_Success = 1;
	
	//民盛App
	private static final String MINSHENG_APP = "0";

	/**
	 * 
	 * 验证用户登录请求信息
	 * 
	 * @param loginInfo
	 * @return
	 */
	public JSONObject checkUser(String username, String password, String mobilekey) {
		JSONObject jo = new JSONObject();
		try {
			int flag;

			// 判断该用户名是否存在
			log.info("检查用户名:" + username + "是否存在");
//			flag = mobileLoginMapper.checkUserByUsername(username);
			List<Staff> slist = userInfoService.findUserByUsername(username);
			if (slist!=null && slist.size()==0) {
				jo.accumulate("message", Response.USERNAME_ERROR.getMessage());
				jo.accumulate("result", "0");
				return jo;
			}else if (slist!=null && slist.size()>1){
				jo.accumulate("message", Response.USERNAME_TOO_MANY.getMessage());
				jo.accumulate("result", "0");
	        	return jo;
			}else if (!slist.get(0).getPassword().equals(password)) {
				jo.accumulate("result", "0");
				jo.accumulate("message", Response.PASSWORD_ERROR.getMessage());
				return jo;
	        }
//			// 判断密码是否正确
//			log.info("检查密码:" + password + "是否正确");
//			flag = mobileLoginMapper.checkUserByUsernameAndPassword(username,password);
//			if (flag == Error_Password) {
//				return Response.PASSWORD_ERROR;
//			}

			// 获取用户的staffid
//			int staffid = mobileLoginMapper.getUserStaffidByUsernameAndPassword(username, password);
			int staffid = slist.get(0).getId();
			// 判断用户是否已经登录过
			log.info("根据当前用户的staffid:" + staffid + ",判断用户是否重复登录");
			flag = mobileLoginMapper.checkUserByStaffid(staffid,MINSHENG_APP);
			if (flag >= Repeat_Login) {
				 //判断是否是上次登录的手机
				int result = mobileLoginMapper.checkMacAddress(staffid, mobilekey, MINSHENG_APP);
				if(result==0){
					jo.accumulate("message", Response.Error_Bind_Phone.getMessage());
					jo.accumulate("result", "0");
					return jo;
				}else{
					jo.accumulate("data", staffid);
				}
			}else{
				// 保存用户的登录信息
				log.info("根据手机识别码:" + mobilekey + "和当前用户的staffid:" + staffid + ",向ms_staff_token表中添加一条登录令牌信息");
				flag = mobileLoginMapper.insertUserAccessToken(mobilekey, staffid);
				if (flag == Opration_Success) {
					jo.accumulate("data", staffid);
				}
			}
			jo.accumulate("result", "1");
			return jo;
		} catch (Exception e) {
			log.error("用户登录发生了错误");
			e.printStackTrace();
			jo.accumulate("result", "0");
			jo.accumulate("message", Response.SERVER_ERROR.getMessage());
			return jo;
		}
	}
	
	/**
	 * 
	 * 用户退出 
	 * @param staffid
	 * @return
	 */
	public Response loginOut(String staffid) {

		Response resp = Response.SUCCESS;
		try {
			log.info("ID号为:" + staffid + "的员工退出了登录");
			int flag=mobileLoginMapper.loginOutByStaffid(staffid);
			if(flag == Opration_Success){
				return resp;
			}
			return Response.FAIL;
		} catch (Exception e) {
			log.error("用户退出发生了错误");
			e.printStackTrace();
			return Response.SERVER_ERROR;
		}
	}
	
	/**
	 * 修改密码
	 * @param staffid
	 * @param password
	 * @param oldpassword
	 * @return
	 */
	public Response updatePwd(String staffid,String password,String oldpassword) {

		Response resp = Response.SUCCESS;
		
		int flag;
		
		try {
			
			//检查旧密码是否正确
			log.info("检查ID号为:" + staffid + "的员工输入的旧密码是否正确");
			flag=mobileLoginMapper.checkOldPwdByStaffidAndOldPwd(oldpassword, staffid);
			if(flag == Error_Password){
				return Response.OLD_PASSWORD_ERROR;
			}
			
			//设置新密码
			log.info("设置ID号为:" + staffid + "的员工的新密码");
			flag=mobileLoginMapper.updatePwd(password, staffid);
			if(flag == Opration_Success){
				return resp;
			}
			
			return Response.FAIL;
		} catch (Exception e) {
			log.error("用户修改密码发生了错误");
			e.printStackTrace();
			return Response.SERVER_ERROR;
		}
	}
}
