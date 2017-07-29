package com.spw.elife.company.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spw.elife.common.DaoMapper;
import com.spw.elife.company.bean.EmployeeEntity;

/**
 * @author Administrator
 * 
 */
@DaoMapper
public interface EmployeeMapper {

	List<EmployeeEntity> findEmployeeByName(@Param("username")String username);
}
