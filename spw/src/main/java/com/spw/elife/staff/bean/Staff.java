package com.spw.elife.staff.bean;

import java.util.List;


/**
 * @author Administrator
 *
 */
public class Staff{
	
	private int id;
	//用户密码
	private String password;
	//姓名/title
	private String name;
	//姓别
	private String sex;
	//民族
	private String nation;
	//照片
	private String photo;
	//婚姻状况
	private String maritalStatus;
	//身份证号码
	private String idCard;
	//生日
	private String birthDate;
	//政治面貌
	private String politicalStatus;
	//户籍所在省
	private String domicilePlaceProvince;
	//户籍所在市
	private String domicilePlaceRegion;
	//户籍所在县
	private String domicilePlaceCounty;
	//户籍所在镇
	private String domicilePlaceTown;
	//户籍所在村
	private String domicilePlaceVillage;
	//户籍详细地址
	private String domicilePlaceDetail;
	//座机号码
	private String telephone;
	//手机号码
	private String phoneNum;
	//私人号码
	private String personalPhone;
	//微信号
	private String wechatId;
	//qq
	private String qq;
	//学历
	private String education;
	//毕业院校
	private String graduateInstitutions;
	//专业
	private String major;
	//健康状况
	private String physicalCondition;
	//健康备注
	private String physicalRemark;
	//公司名称
	private String companyName;
	//组织类型
    private String orgType;
	//岗位
	private String position;
	//岗位号
	private String workNum;
	//g岗位名称
	private String positionName;
	//支付密码
	private String payPassword;
	//余额
	private Double remainingSum;
	//迅车贷用户余额
	private Double fastCarLoanAmount;
	
	//家庭住址
	private Integer provinceId;
	private String provinceName;
	private Integer regionId;
	private String regionName;
	private Integer countyId;
	private String countyName;
	private String townId;
	private String villageId;
	//详细地址
    private String address;
    private String isPrint;
	
	
	//推荐人
	private String localName;
	private String referrerType;
	private String referrerName;
	private String referrerPhone;
	private String referrerBankNum;
	private String referrerBank;
	private String referrerIdCard;	
	private String referrerIdCardName;
	private String directGrowingIdCard;	//直接育成人身份证
	
	//入职日期
	private String entryDate;
	//离职日期
	private String quitDate;
	//状态：0：离职；1：在职；
	private Integer status;
	//是否删除  1：已删除   0：未删除
	private Integer isdel;
	
	//所属分公司名称
	private String branchCompanyName;
	private String branchCompanyId;
	//区县加盟商主键     == 督训区
	private String countyFranchiseesId;
	//区县加盟商名称
	private String countyFranchiseesName;
	//门店ID    == 分部
	private String storeId;
	//督训id
	private String parentId;
	//督训名称
    private String parentName;
	//门店名称
    private String storeName;
    //银行卡所属银行
  	private String bankName;
  	//银行卡号
  	private String bankCardNum;
	
	//条线类型  0保代条线 1门店条线
	private Integer lineType;
	
	//合同
	private String contractType;
	private String contractPic;
	private String contractNum;
	//是否有分部
	private String havePartId;
	//直属分部职级
	private String partIdPosition;
	
	//亲属工号
	private String relativesWorking;
	//担保人
	private String bondsman;
	
	//身份证复印件
	private String idCardCopy;
	//最高学历证明
	private String qualificationCopy;
	//一寸彩色照片
	private String oneInchPhoto;
	//银行卡复印件
	private String bankCardCopy;
	//保证金收据
	private String depositReceipt;
	private String depositReceipt1;
	private String depositReceipt2;
	private String depositReceipt3;
	//岗前培训结业证书复印件
	private String certificateCopy;
	//离职证明
	private String leavingCertificate;
	//代理人承诺书
	private String agentCommitment;
	//代理人自动扣款授权书
	private String agentAutoDeduction;
	//代理人担保书
	private String agentGuarantee;
	//代理人合同书
	private String agentContract;
	//综合考试合格证书
	private String testCertificate;
	//晋级申请书
	private String promotionApplication;
	
	private double basePay;//基本工资
	private double socialPay;//社保+公积金
	private double floatPay;//浮动工资
	private String personType;//人员类型  0业务人员  1后援人员
	
	private String isStandard;//专员是否达标
	
	private String cashDeposit;//保证金数额
	private String depositCode;//押金编码
	private String accountDate;//到账日期
	private String isSubmit;//小票是否上传
	private String generationCertification;//代资证号
	private String generationCertificationCompany;//职业证书所属公司
	private String replacementTime;//换证时间
	private String haveCar;//是否有车
	private String remark;//备注
	private String createTime;//创建时间
	private String companyId;//车辆信息
	
	private int fenbu;
	private int zhizeng;
	private int personCount;
	private double achie;
	
	private int isSpecialTraining;//是否专训
	private int isNew;//是否新筹
	private String newDateBegin ;//新筹日期
	private String newDateEnd ;//新筹日期
	private String preparationStatus;//筹建督训状态  1筹建中 2筹建成功 3筹建失败
	private String applicationId;//督训晋升外键
	private String openid;//
	
	private List<String> carList;//押金集合
	
	
	public String getPreparationStatus() {
		return preparationStatus;
	}
	public void setPreparationStatus(String preparationStatus) {
		this.preparationStatus = preparationStatus;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getPoliticalStatus() {
		return politicalStatus;
	}
	public void setPoliticalStatus(String politicalStatus) {
		this.politicalStatus = politicalStatus;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getWechatId() {
		return wechatId;
	}
	public void setWechatId(String wechatId) {
		this.wechatId = wechatId;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getGraduateInstitutions() {
		return graduateInstitutions;
	}
	public void setGraduateInstitutions(String graduateInstitutions) {
		this.graduateInstitutions = graduateInstitutions;
	}
	public String getPhysicalCondition() {
		return physicalCondition;
	}
	public void setPhysicalCondition(String physicalCondition) {
		this.physicalCondition = physicalCondition;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getOrgType() {
		return orgType;
	}
	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}
	public String getWorkNum() {
		return workNum;
	}
	public void setWorkNum(String workNum) {
		this.workNum = workNum;
	}
	public String getPayPassword() {
		return payPassword;
	}
	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}
	public Double getRemainingSum() {
		return remainingSum;
	}
	public void setRemainingSum(Double remainingSum) {
		this.remainingSum = remainingSum;
	}
	public Integer getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public Integer getRegionId() {
		return regionId;
	}
	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}
	public Integer getCountyId() {
		return countyId;
	}
	public void setCountyId(Integer countyId) {
		this.countyId = countyId;
	}
	public String getCountyName() {
		return countyName;
	}
	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}
	public String getTownId() {
		return townId;
	}
	public void setTownId(String townId) {
		this.townId = townId;
	}
	public String getVillageId() {
		return villageId;
	}
	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLocalName() {
		return localName;
	}
	public void setLocalName(String localName) {
		this.localName = localName;
	}
	public String getReferrerType() {
		return referrerType;
	}
	public void setReferrerType(String referrerType) {
		this.referrerType = referrerType;
	}
	public String getReferrerName() {
		return referrerName;
	}
	public void setReferrerName(String referrerName) {
		this.referrerName = referrerName;
	}
	public String getReferrerPhone() {
		return referrerPhone;
	}
	public void setReferrerPhone(String referrerPhone) {
		this.referrerPhone = referrerPhone;
	}
	public String getReferrerBankNum() {
		return referrerBankNum;
	}
	public void setReferrerBankNum(String referrerBankNum) {
		this.referrerBankNum = referrerBankNum;
	}
	public String getReferrerBank() {
		return referrerBank;
	}
	public void setReferrerBank(String referrerBank) {
		this.referrerBank = referrerBank;
	}
	public String getReferrerIdCard() {
		return referrerIdCard;
	}
	public void setReferrerIdCard(String referrerIdCard) {
		this.referrerIdCard = referrerIdCard;
	}
	
	public String getReferrerIdCardName() {
		return referrerIdCardName;
	}
	public void setReferrerIdCardName(String referrerIdCardName) {
		this.referrerIdCardName = referrerIdCardName;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getQuitDate() {
		return quitDate;
	}
	public void setQuitDate(String quitDate) {
		this.quitDate = quitDate;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getIsdel() {
		return isdel;
	}
	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}
	public String getBranchCompanyName() {
		return branchCompanyName;
	}
	public void setBranchCompanyName(String branchCompanyName) {
		this.branchCompanyName = branchCompanyName;
	}
	public String getBranchCompanyId() {
		return branchCompanyId;
	}
	public void setBranchCompanyId(String branchCompanyId) {
		this.branchCompanyId = branchCompanyId;
	}
	public String getCountyFranchiseesId() {
		return countyFranchiseesId;
	}
	public void setCountyFranchiseesId(String countyFranchiseesId) {
		this.countyFranchiseesId = countyFranchiseesId;
	}
	public String getCountyFranchiseesName() {
		return countyFranchiseesName;
	}
	public void setCountyFranchiseesName(String countyFranchiseesName) {
		this.countyFranchiseesName = countyFranchiseesName;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankCardNum() {
		return bankCardNum;
	}
	public void setBankCardNum(String bankCardNum) {
		this.bankCardNum = bankCardNum;
	}
	public Integer getLineType() {
		return lineType;
	}
	public void setLineType(Integer lineType) {
		this.lineType = lineType;
	}
	public String getContractType() {
		return contractType;
	}
	public void setContractType(String contractType) {
		this.contractType = contractType;
	}
	public String getContractPic() {
		return contractPic;
	}
	public void setContractPic(String contractPic) {
		this.contractPic = contractPic;
	}
	public String getContractNum() {
		return contractNum;
	}
	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}
	public String getHavePartId() {
		return havePartId;
	}
	public void setHavePartId(String havePartId) {
		this.havePartId = havePartId;
	}
	public String getRelativesWorking() {
		return relativesWorking;
	}
	public void setRelativesWorking(String relativesWorking) {
		this.relativesWorking = relativesWorking;
	}
	public String getBondsman() {
		return bondsman;
	}
	public void setBondsman(String bondsman) {
		this.bondsman = bondsman;
	}
	public String getIdCardCopy() {
		return idCardCopy;
	}
	public void setIdCardCopy(String idCardCopy) {
		this.idCardCopy = idCardCopy;
	}
	public String getQualificationCopy() {
		return qualificationCopy;
	}
	public void setQualificationCopy(String qualificationCopy) {
		this.qualificationCopy = qualificationCopy;
	}
	public String getOneInchPhoto() {
		return oneInchPhoto;
	}
	public void setOneInchPhoto(String oneInchPhoto) {
		this.oneInchPhoto = oneInchPhoto;
	}
	public String getBankCardCopy() {
		return bankCardCopy;
	}
	public void setBankCardCopy(String bankCardCopy) {
		this.bankCardCopy = bankCardCopy;
	}
	public String getCertificateCopy() {
		return certificateCopy;
	}
	public void setCertificateCopy(String certificateCopy) {
		this.certificateCopy = certificateCopy;
	}
	public String getLeavingCertificate() {
		return leavingCertificate;
	}
	public void setLeavingCertificate(String leavingCertificate) {
		this.leavingCertificate = leavingCertificate;
	}
	public String getAgentCommitment() {
		return agentCommitment;
	}
	public void setAgentCommitment(String agentCommitment) {
		this.agentCommitment = agentCommitment;
	}
	public String getAgentAutoDeduction() {
		return agentAutoDeduction;
	}
	public void setAgentAutoDeduction(String agentAutoDeduction) {
		this.agentAutoDeduction = agentAutoDeduction;
	}
	public String getAgentGuarantee() {
		return agentGuarantee;
	}
	public void setAgentGuarantee(String agentGuarantee) {
		this.agentGuarantee = agentGuarantee;
	}
	public String getAgentContract() {
		return agentContract;
	}
	public void setAgentContract(String agentContract) {
		this.agentContract = agentContract;
	}
	public String getTestCertificate() {
		return testCertificate;
	}
	public void setTestCertificate(String testCertificate) {
		this.testCertificate = testCertificate;
	}
	public String getPromotionApplication() {
		return promotionApplication;
	}
	public void setPromotionApplication(String promotionApplication) {
		this.promotionApplication = promotionApplication;
	}
	public String getDomicilePlaceDetail() {
		return domicilePlaceDetail;
	}
	public void setDomicilePlaceDetail(String domicilePlaceDetail) {
		this.domicilePlaceDetail = domicilePlaceDetail;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getPhysicalRemark() {
		return physicalRemark;
	}
	public void setPhysicalRemark(String physicalRemark) {
		this.physicalRemark = physicalRemark;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public double getBasePay() {
		return basePay;
	}
	public void setBasePay(double basePay) {
		this.basePay = basePay;
	}
	public double getSocialPay() {
		return socialPay;
	}
	public void setSocialPay(double socialPay) {
		this.socialPay = socialPay;
	}
	public double getFloatPay() {
		return floatPay;
	}
	public void setFloatPay(double floatPay) {
		this.floatPay = floatPay;
	}
	public String getPersonType() {
		return personType;
	}
	public void setPersonType(String personType) {
		this.personType = personType;
	}
	public String getIsStandard() {
		return isStandard;
	}
	public void setIsStandard(String isStandard) {
		this.isStandard = isStandard;
	}
	public String getCashDeposit() {
		return cashDeposit;
	}
	public void setCashDeposit(String cashDeposit) {
		this.cashDeposit = cashDeposit;
	}
	public String getDepositCode() {
		return depositCode;
	}
	public void setDepositCode(String depositCode) {
		this.depositCode = depositCode;
	}
	public String getAccountDate() {
		return accountDate;
	}
	public void setAccountDate(String accountDate) {
		this.accountDate = accountDate;
	}
	public String getIsSubmit() {
		return isSubmit;
	}
	public void setIsSubmit(String isSubmit) {
		this.isSubmit = isSubmit;
	}
	public String getGenerationCertification() {
		return generationCertification;
	}
	public void setGenerationCertification(String generationCertification) {
		this.generationCertification = generationCertification;
	}
	public String getGenerationCertificationCompany() {
		return generationCertificationCompany;
	}
	public void setGenerationCertificationCompany(
			String generationCertificationCompany) {
		this.generationCertificationCompany = generationCertificationCompany;
	}
	public String getReplacementTime() {
		return replacementTime;
	}
	public void setReplacementTime(String replacementTime) {
		this.replacementTime = replacementTime;
	}
	public String getHaveCar() {
		return haveCar;
	}
	public void setHaveCar(String haveCar) {
		this.haveCar = haveCar;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public int getIsSpecialTraining() {
		return isSpecialTraining;
	}
	public void setIsSpecialTraining(int isSpecialTraining) {
		this.isSpecialTraining = isSpecialTraining;
	}
	public int getIsNew() {
		return isNew;
	}
	public void setIsNew(int isNew) {
		this.isNew = isNew;
	}
	public String getNewDateBegin() {
		return newDateBegin;
	}
	public void setNewDateBegin(String newDateBegin) {
		this.newDateBegin = newDateBegin;
	}
	public String getNewDateEnd() {
		return newDateEnd;
	}
	public void setNewDateEnd(String newDateEnd) {
		this.newDateEnd = newDateEnd;
	}
	public int getFenbu() {
		return fenbu;
	}
	public void setFenbu(int fenbu) {
		this.fenbu = fenbu;
	}
	public int getZhizeng() {
		return zhizeng;
	}
	public void setZhizeng(int zhizeng) {
		this.zhizeng = zhizeng;
	}
	public String getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	public int getPersonCount() {
		return personCount;
	}
	public void setPersonCount(int personCount) {
		this.personCount = personCount;
	}
	public double getAchie() {
		return achie;
	}
	public void setAchie(double achie) {
		this.achie = achie;
	}
	public String getDomicilePlaceProvince() {
		return domicilePlaceProvince;
	}
	public void setDomicilePlaceProvince(String domicilePlaceProvince) {
		this.domicilePlaceProvince = domicilePlaceProvince;
	}
	public String getDomicilePlaceRegion() {
		return domicilePlaceRegion;
	}
	public void setDomicilePlaceRegion(String domicilePlaceRegion) {
		this.domicilePlaceRegion = domicilePlaceRegion;
	}
	public String getDomicilePlaceCounty() {
		return domicilePlaceCounty;
	}
	public void setDomicilePlaceCounty(String domicilePlaceCounty) {
		this.domicilePlaceCounty = domicilePlaceCounty;
	}
	public String getDomicilePlaceTown() {
		return domicilePlaceTown;
	}
	public void setDomicilePlaceTown(String domicilePlaceTown) {
		this.domicilePlaceTown = domicilePlaceTown;
	}
	public String getDomicilePlaceVillage() {
		return domicilePlaceVillage;
	}
	public void setDomicilePlaceVillage(String domicilePlaceVillage) {
		this.domicilePlaceVillage = domicilePlaceVillage;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public String getDirectGrowingIdCard() {
		return directGrowingIdCard;
	}
	public void setDirectGrowingIdCard(String directGrowingIdCard) {
		this.directGrowingIdCard = directGrowingIdCard;
	}
	public String getDepositReceipt1() {
		return depositReceipt1;
	}
	public void setDepositReceipt1(String depositReceipt1) {
		this.depositReceipt1 = depositReceipt1;
	}
	public String getDepositReceipt2() {
		return depositReceipt2;
	}
	public void setDepositReceipt2(String depositReceipt2) {
		this.depositReceipt2 = depositReceipt2;
	}
	public String getDepositReceipt3() {
		return depositReceipt3;
	}
	public void setDepositReceipt3(String depositReceipt3) {
		this.depositReceipt3 = depositReceipt3;
	}
	public String getDepositReceipt() {
		return depositReceipt;
	}
	public void setDepositReceipt(String depositReceipt) {
		this.depositReceipt = depositReceipt;
	}
	public Double getFastCarLoanAmount() {
		return fastCarLoanAmount;
	}
	public void setFastCarLoanAmount(Double fastCarLoanAmount) {
		this.fastCarLoanAmount = fastCarLoanAmount;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public String getPersonalPhone() {
		return personalPhone;
	}
	public void setPersonalPhone(String personalPhone) {
		this.personalPhone = personalPhone;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public List<String> getCarList() {
		return carList;
	}
	public void setCarList(List<String> carList) {
		this.carList = carList;
	}
	public String getIsPrint() {
		return isPrint;
	}
	public void setIsPrint(String isPrint) {
		this.isPrint = isPrint;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getPartIdPosition() {
		return partIdPosition;
	}
	public void setPartIdPosition(String partIdPosition) {
		this.partIdPosition = partIdPosition;
	}
}
