package com.spw.elife.basics.bean;

import java.util.List;

/**
 * 查询条件. 用来封装查询条件（包括分页条件.）
 *
 * @author Administrator
 */
public class Lookup {

    /**
     * 分页页码.
     */
    private int page = 1;
    /**
     * 分页大小.
     */
    private int size = 10;
    /**
     * 总数据量.
     */
    private int total;
    /**
     * news pic adv
     */
    private String module;
    /**
     * 分类别名
     */
    private String catAlias;
    
    private int newsId;
    
    private String nexNo;
    
    public int getNewsId() {
		return newsId;
	}

	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}

	/**
     * 当前数据索引.
     * @return 数据索引
     */
    public int getIndex() {
        return (getPage() - 1) * getSize();
    }

    /**
     * 总数据量.
     *
     * @return the total
     */
    public int getTotal() {
        return total;
    }

    /**
     * 总数据量.
     *
     * @param total the total to set
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * 分页页码.
     * @return the page
     */
    public int getPage() {
        return page;
    }

    /**
     * 分页页码.
     * @param page the page to set
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * 分页大小.
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * 分页大小.
     * @param size the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getCatAlias() {
		return catAlias;
	}

	public void setCatAlias(String catAlias) {
		this.catAlias = catAlias;
	}

	public String getNexNo() {
		return nexNo;
	}

	public void setNexNo(String nexNo) {
		this.nexNo = nexNo;
	}
	/************************************************************************数据权限用默认配置不允许赋值start********************************************************/
	/**
	 * 渠道检索条件集合
	 */
	private List<String> channelList;

	/**
	 * 督训区检索条件集合
	 */
	private List<String> governorList;

	/**
	 * 部门检索条件集合
	 */
	private List<String> departmentList;

	/**
	 * 省分检索条件集合
	 */
	private List<String> companyList;
	
	/**
	 * 默认人员检索条件
	 */
	private String defultStaffId;
	
	/**
	 * 人员类型  0管理员 1 其他
	 */
	private int staffRole = 1;

	/**
	 * 人员类型  0管理员 1 其他
	 * 
	 * @return 人员类型  0管理员 1 其他
	 */
	public int getStaffRole() {
		return staffRole;
	}

	/**
	 * 人员类型  0管理员 1 其他设定
	 * 
	 * @param staffRole 人员类型  0管理员 1 其他
	 */
	public void setStaffRole(int staffRole) {
		this.staffRole = staffRole;
	}

	/**
	 * 默认人员检索条件获取
	 * 
	 * @return 默认人员检索条件
	 */
	public String getDefultStaffId() {
		return defultStaffId;
	}

	/**
	 * 默认人员检索条件设定
	 * 
	 * @param defultStaffId 默认人员检索条件
	 */
	public void setDefultStaffId(String defultStaffId) {
		this.defultStaffId = defultStaffId;
	}

	/**
	 * 渠道检索条件集合获取
	 * 
	 * @return 渠道检索条件集合
	 */
	public List<String> getChannelList() {
		return channelList;
	}

	/**
	 * 渠道检索条件集合设定
	 * 
	 * @param channelList 渠道检索条件集合
	 */
	public void setChannelList(List<String> channelList) {
		this.channelList = channelList;
	}

	/**
	 * 督训区检索条件集合获取
	 * 
	 * @return 督训区检索条件集合
	 */
	public List<String> getGovernorList() {
		return governorList;
	}

	/**
	 * 督训区检索条件集合设定
	 * 
	 * @param governorList 督训区检索条件集合
	 */
	public void setGovernorList(List<String> governorList) {
		this.governorList = governorList;
	}

	/**
	 * 部门检索条件集合获取
	 * 
	 * @return 部门检索条件集合
	 */
	public List<String> getDepartmentList() {
		return departmentList;
	}

	/**
	 * 部门检索条件集合设定
	 * 
	 * @param departmentList 部门检索条件集合
	 */
	public void setDepartmentList(List<String> departmentList) {
		this.departmentList = departmentList;
	}

	/**
	 * 省分检索条件集合获取
	 * 
	 * @return 省分检索条件集合
	 */
	public List<String> getCompanyList() {
		return companyList;
	}

	/**
	 * 省分检索条件集合设定
	 * 
	 * @param companyList 省分检索条件集合
	 */
	public void setCompanyList(List<String> companyList) {
		this.companyList = companyList;
	}
	/************************************************************************数据权限用默认配置不允许赋值end********************************************************/

}
