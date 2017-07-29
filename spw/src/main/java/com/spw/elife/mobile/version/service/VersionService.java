package com.spw.elife.mobile.version.service;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.spw.elife.mobile.version.bean.MVersion;
import com.spw.elife.mobile.version.dao.VersionMapper;

/**
 * 
 * 版本号Service
 * 
 * @author Administrator
 * 
 */
@Service
public class VersionService {

	@Resource
	private VersionMapper versionMapper;

	private Logger log = Logger.getLogger(VersionService.class);
	// 操作成功
	private static final int Opration_Success = 1;
	
	/**
	 * 
	 * 保存版本号
	 * 
	 * @param attendance
	 * @return
	 */
	public int saveBasicAttendance(MVersion version) {
		int flag=-1;
		try {
			log.info("保存ID为:" + version.getId() + "的版本号信息");
			flag = versionMapper.saveBasicVersion(version);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("保存ID为:" + version.getId()+ "的版本号信息出现了错误");
		}
		return flag;
	}
	
	/**
	 * 
	 * 查找当前最大版本号
	 * 
	 * @param attendance
	 * @return
	 */
	public MVersion findLastVersion()
	{
		MVersion version = versionMapper.findLastVersion();
		return version;
	}


}
