package com.spw.elife.company.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spw.elife.common.DaoMapper;
import com.spw.elife.company.bean.CompanyEntity;

/**
 * @author Administrator
 * 
 */
@DaoMapper
public interface CompanyMapper {

	CompanyEntity getCompanyByPhone(@Param("phone")String phone);

	int countEntity(Object param);

	List<CompanyEntity> selectEntityList(Object param);

	void update(CompanyEntity entity);

	void insert(CompanyEntity entity);

	CompanyEntity getCompanyById(String id);
}
