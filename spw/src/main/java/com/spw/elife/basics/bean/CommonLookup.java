package com.spw.elife.basics.bean;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class CommonLookup extends Lookup {
	
    private String name;
    
    private Integer status;
    
    private String title;
    
    private String type;
    
    private String storeName;
    
    private String code;
    
    private String workNum;
    
    private String value;
    
    private String gysName;
    
    private String address;
    
    private String phone;
    
    private String orderId;  
    
    private String villagesName; 
    
    private String villagesNo;
    
    private Integer compStatus;
    
    private String organ;
    
    private String company;
    
    private String dxq;
    
    private String dxs;
    
    private String townName; 
    
    private String townNo;
    
    private String downLoadParam;
    
    private String branchCompanyId;
    private String countyFranchiseesId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;
    private String serialNumber;
    private String serialNumberEn; 
    private String bx; 
    private String icpBeginTime; 
    private String icpEndTime;
    private String isDel;
    private String currentTime;
    private String claimsName;
    private String insuranceNo;
    private String report;
    private List<Integer> list;
	private int lineType; 
	private String lineType1; 
	private String category;
    private String provinceId;
    private String regionId;
    private String countyId;
    private String state;
    private int orgType;
    private String small;
    private String big;
    private String dxqStatus;
    private String position;
    private List<Integer> listposition;
    private String isNew;
	private int rollRate;
	private String checkingStatus;
    private String date;
    private String TDdate;
    private String beginDate;
    private String endDate;
    private String added;
    private String reporterId;
    private String staffId;
    private List<String> fastState;
    private List<String> orgList;
    private String precollectedBegin;//寿险预收开始
    private String precollectedEnd;//寿险预收结束
    private String year;
    private String month;
    private String day; 
	private String dep_first; //一级部门
	private String dep_second;//二级部门
	private String dep_third; //三级部门  
	private String org_type;//组织 
    private String companyType; 
    
    private String brandId;
    private String typeId;
    private String gysId;
    private String isShow; 
     
    private List<String> orgTypeList; //多部门
    
	public List<String> getOrgTypeList() {
		return orgTypeList;
	}

	public void setOrgTypeList(List<String> orgTypeList) {
		this.orgTypeList = orgTypeList;
	}

	/**
	 * 部门id
	 */
	private String departmentId;

	/**
	 * 部门id获取
	 * 
	 * @return 部门id
	 */
	public String getDepartmentId() {
		return departmentId;
	}

	/**
	 * 部门id设定
	 * 
	 * @param departmentId 部门id
	 */
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

    private String lcnNo;//车牌号
    private String order_status;
	public String getCompanyType() {
		return companyType;
	}
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}
	public String getOrg_type() {
		return org_type;
	}
	public void setOrg_type(String org_type) {
		this.org_type = org_type;
	}
	public String getDep_first() {
		return dep_first;
	}
	public void setDep_first(String dep_first) {
		this.dep_first = dep_first;
	}
	public String getDep_second() {
		return dep_second;
	}
	public void setDep_second(String dep_second) {
		this.dep_second = dep_second;
	}
	public String getDep_third() {
		return dep_third;
	}
	public void setDep_third(String dep_third) {
		this.dep_third = dep_third;
	}
	public String getLineType1() {
		return lineType1;
	}
	public void setLineType1(String lineType1) {
		this.lineType1 = lineType1;
	}
    public List<Integer> getList() {
		return list;
	}
	public void setList(List<Integer> list) {
		this.list = list;
	}
	public String getIcpBeginTime() {
		return icpBeginTime;
	}
	public void setIcpBeginTime(String icpBeginTime) {
		this.icpBeginTime = icpBeginTime;
	}
	public String getIcpEndTime() {
		return icpEndTime;
	}
	public void setIcpEndTime(String icpEndTime) {
		this.icpEndTime = icpEndTime;
	}
	public String getBx() {
		return bx;
	}
	public void setBx(String bx) {
		this.bx = bx;
	}
	public String getSerialNumberEn() {
		return serialNumberEn;
	}
	public void setSerialNumberEn(String serialNumberEn) {
		this.serialNumberEn = serialNumberEn;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
    public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getVillagesName() {
		return villagesName;
	}
	public void setVillagesName(String villagesName) {
		this.villagesName = villagesName;
	}
	public String getVillagesNo() {
		return villagesNo;
	}
	public void setVillagesNo(String villagesNo) {
		this.villagesNo = villagesNo;
	}
	public String getTownName() {
		return townName;
	}
	public void setTownName(String townName) {
		this.townName = townName;
	}
	public String getTownNo() {
		return townNo;
	}
	public void setTownNo(String townNo) {
		this.townNo = townNo;
	}
	public String getPrecollectedBegin() {
		return precollectedBegin;
	}
	public void setPrecollectedBegin(String precollectedBegin) {
		this.precollectedBegin = precollectedBegin;
	}
	public String getPrecollectedEnd() {
		return precollectedEnd;
	}
	public void setPrecollectedEnd(String precollectedEnd) {
		this.precollectedEnd = precollectedEnd;
	}
	public List<String> getFastState() {
		return fastState;
	}
	public void setFastState(List<String> fastState) {
		this.fastState = fastState;
	}
	public String getAdded() {
		return added;
	}
	public void setAdded(String added) {
		this.added = added;
	}
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getWorkNum() {
		return workNum;
	}
	public void setWorkNum(String workNum) {
		this.workNum = workNum;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getGysName() {
		return gysName;
	}
	public void setGysName(String gysName) {
		this.gysName = gysName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public int getLineType() {
		return lineType;
	}
	public void setLineType(int lineType) {
		this.lineType = lineType;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	public String getRegionId() {
		return regionId;
	}
	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}
	public String getCountyId() {
		return countyId;
	}
	public void setCountyId(String countyId) {
		this.countyId = countyId;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getOrgType() {
		return orgType;
	}
	public void setOrgType(int orgType) {
		this.orgType = orgType;
	}
	public Integer getCompStatus() {
		return compStatus;
	}
	public void setCompStatus(Integer compStatus) {
		this.compStatus = compStatus;
	}
	public String getOrgan() {
		return organ;
	}
	public void setOrgan(String organ) {
		this.organ = organ;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getDxq() {
		return dxq;
	}
	public void setDxq(String dxq) {
		this.dxq = dxq;
	}
	public String getSmall() {
		return small;
	}
	public String getBig() {
		return big;
	}
	public void setBig(String big) {
		this.big = big;
	}
	public void setSmall(String small) {
		this.small = small;
	}
	public String getDxqStatus() {
		return dxqStatus;
	}
	public void setDxqStatus(String dxqStatus) {
		this.dxqStatus = dxqStatus;
	}
	public List<Integer> getListposition() {
		return listposition;
	}
	public void setListposition(List<Integer> listposition) {
		this.listposition = listposition;
	}
	public String getIsNew() {
		return isNew;
	}
	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}
	public String getIsDel() {
		return isDel;
	}
	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}
	public String getClaimsName() {
		return claimsName;
	}
	public void setClaimsName(String claimsName) {
		this.claimsName = claimsName;
	}
	public String getInsuranceNo() {
		return insuranceNo;
	}
	public void setInsuranceNo(String insuranceNo) {
		this.insuranceNo = insuranceNo;
	}
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
	}
	public int getRollRate() {
		return rollRate;
	}
	public void setRollRate(int rollRate) {
		this.rollRate = rollRate;
	}
	public String getTDdate() {
		return TDdate;
	}
	public void setTDdate(String tDdate) {
		TDdate = tDdate;
	}
	public String getCheckingStatus() {
		return checkingStatus;
	}
	public void setCheckingStatus(String checkingStatus) {
		this.checkingStatus = checkingStatus;
	}
	public String getDxs() {
		return dxs;
	}
	public void setDxs(String dxs) {
		this.dxs = dxs;
	}
	public String getCurrentTime() {
		return currentTime;
	}
	public void setCurrentTime(String currentTime) {
		this.currentTime = currentTime;
	}
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public String getBranchCompanyId() {
		return branchCompanyId;
	}
	public void setBranchCompanyId(String branchCompanyId) {
		this.branchCompanyId = branchCompanyId;
	}
	public String getReporterId() {
		return reporterId;
	}
	public void setReporterId(String reporterId) {
		this.reporterId = reporterId;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getLcnNo() {
		return lcnNo;
	}
	public void setLcnNo(String lcnNo) {
		this.lcnNo = lcnNo;
	}
	public String getOrder_status() {
		return order_status;
	}
	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getGysId() {
		return gysId;
	}

	public void setGysId(String gysId) {
		this.gysId = gysId;
	}

	public String getIsShow() {
		return isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}

	public List<String> getOrgList() {
		return orgList;
	}

	public void setOrgList(List<String> orgList) {
		this.orgList = orgList;
	}

	public String getDownLoadParam() {
		return downLoadParam;
	}

	public void setDownLoadParam(String downLoadParam) {
		this.downLoadParam = downLoadParam;
	}

	public String getCountyFranchiseesId() {
		return countyFranchiseesId;
	}

	public void setCountyFranchiseesId(String countyFranchiseesId) {
		this.countyFranchiseesId = countyFranchiseesId;
	}
	
}
