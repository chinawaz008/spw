package com.spw.elife.mobile.version.dao;

import org.apache.ibatis.annotations.Param;

import com.spw.elife.common.DaoMapper;
import com.spw.elife.mobile.version.bean.MVersion;


/**
 * 
 * 版本号Mapper
 * 
 * @author Administrator
 *
 */
@DaoMapper
public interface VersionMapper {
	
	//添加最新的版本号信息
	public int saveBasicVersion(@Param(value = "version")MVersion version);
	
	//查找当前最大的版本号
	public MVersion findLastVersion();
	
}
