package com.spw.elife.company.service;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.alibaba.druid.sql.visitor.functions.Concat;
import com.spw.elife.basics.bean.Lookup;
import com.spw.elife.basics.service.ImportService;
import com.spw.elife.common.Pager;
import com.spw.elife.company.bean.CompanyEntity;
import com.spw.elife.company.dao.CompanyMapper;
import com.spw.elife.util.Constant;

/**
 * @author Administrator
 *
 */
@Service
public class CompanyService  extends ImportService{
	private static final Logger log = Logger.getLogger(CompanyService.class);
	@Resource
	private CompanyMapper companyMapper;
	
	/**
	 * 由手机号码获取公司
	 * 
	 * @param username
	 * @return
	 */
	public CompanyEntity getCompanyByPhone(String phone) {
		CompanyEntity company = companyMapper.getCompanyByPhone(phone);
		return company;
	}

	/**
	 * 分页查询街道
	 * 
	 * @param lookup
	 *            用户查询参数.
	 * @return 用户列表
	 */
	public Pager<CompanyEntity> queryEntityList(Lookup lookup) {
		int total = companyMapper.countEntity(lookup);
		Pager<CompanyEntity> pager = new Pager<CompanyEntity>();
		pager.setPage(lookup.getPage());
		pager.setSize(lookup.getSize());
		pager.setTotal(total);
		@SuppressWarnings("unchecked")
		List<CompanyEntity> entities = pager.isOverflowed() ? Collections.EMPTY_LIST : companyMapper.selectEntityList(lookup);
		pager.setElements(entities);
		return pager;
	}

	@Transactional(rollbackFor=Exception.class)
	public Map<String, Object> updateCompany(CompanyEntity entity) {
		Map<String,Object> map = new HashMap<>();
		try {
			if (StringUtils.isNotEmpty(entity.getId())) {// 更新
				companyMapper.update(entity);
			}
			else {// 新增
				companyMapper.insert(entity);
			}
			map.put("result", Constant.RESULT_TRUE);
		} catch (Exception e) {
			map.put("message", "更新公司异常");
			map.put("result", Constant.RESULT_FALSE);
			log.error("更新公司异常", e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
		return map;
	}

	public CompanyEntity getCompanyById(String id) {
		return companyMapper.getCompanyById(id);
	}
	
    
}
