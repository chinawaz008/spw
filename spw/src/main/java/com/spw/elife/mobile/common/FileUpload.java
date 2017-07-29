package com.spw.elife.mobile.common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.spw.elife.util.Utils;


/**
 * 
 * 文件上传工具
 * 
 * @author Administrator
 *
 */
public class FileUpload {
		
	public static final String Code_20="上传目录不存在";
	
	public static final String Code_21="上传目录没有写权限";
	
	public static final String Code_22="目录名不正确";
	
	public static final String Code_23="上传文件的大小超过限制";
	
	public static final String Code_24="上传文件扩展名不正确";
	
	public static final String Code_25="上传文件失败";
	
	public static final String Code_26="上传文件成功";
	
	
	/**
	 * 
	 * 上传照片
	 * 
	 * @param photo
	 * @return
	 */
	public static Map<String, Object> fileUpload(HttpServletRequest request,MultipartFile photo) {

		// 文件的url地址
		String url = "";

		// 响应的map
		Map<String, Object> map = new HashMap<String, Object>();

		// 文件保存目录路径
		String savePath =request.getSession().getServletContext().getRealPath("/")+ "directory/";

		// 文件保存目录URL
		String saveUrl = "/directory/";
				
				//request.getContextPath() + "/directory/";

		// 定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");

		// 文件目录
		File uploadDir = new File(savePath);

		// 检查目录是否存在
		if (!uploadDir.isDirectory()) {
			map.put("result", "20");
			map.put("message", Code_20);
			return map;
		}
		
		// 检查目录写权限
		if (!uploadDir.canWrite()) {
			map.put("result", "21");
			map.put("message", Code_21);
			return map;
		}

		String dirName = "image";
		if (!extMap.containsKey(dirName)) {
			map.put("result", "22");
			map.put("message", Code_22);
			return map;
		}

		// 创建文件夹
		savePath += dirName + "/";
		saveUrl += dirName + "/";
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		savePath += ymd + "/";
		saveUrl += ymd + "/";
		File dirFile = new File(savePath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}

		String fileName = photo.getOriginalFilename();

		// 检查扩展名
		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
		if (!Arrays.<String> asList(extMap.get(dirName).split(",")).contains(fileExt)) {
			map.put("result", "24");
			map.put("message", Code_24 );
			return map;
		}

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String newFileName = df.format(new Date()) + "_"+ new Random().nextInt(1000) + "." + fileExt;
		try {
			File uploadedFile = new File(savePath, newFileName);
			photo.transferTo(uploadedFile);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("result", "25");
			map.put("message", Code_25);
			return map;
		}

		int index1 = fileName.lastIndexOf("\\");
		if (index1 != -1) {
			fileName = fileName.substring(index1 + 1);
		}

		int index2 = fileName.lastIndexOf("/");
		if (index2 != -1) {
			fileName = fileName.substring(index2 + 1);
		}

		url = saveUrl + newFileName;
		map.put("result", "26");
		map.put("message", Code_26);
		map.put("url", url);

		return map;
	}
	
	/**
	 * 
	 * 上传照片
	 * 
	 * @param photo
	 * @return
	 */
	public static Map<String, Object> fileUpload2(HttpServletRequest request,MultipartFile photo,String savePath,String saveUrl) {

		// 文件的url地址
		String url = "";

		// 响应的map
		Map<String, Object> map = new HashMap<String, Object>();


		// 定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");

		// 文件目录
		File uploadDir = new File(savePath);

		// 检查目录是否存在
		if (!uploadDir.isDirectory()) {
			uploadDir.mkdirs();
		}
		
		// 检查目录写权限
		if (!uploadDir.canWrite()) {
			map.put("result", "21");
			map.put("message", Code_21);
			return map;
		}

		String dirName = "image";
		if (!extMap.containsKey(dirName)) {
			map.put("result", "22");
			map.put("message", Code_22);
			return map;
		}

		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		savePath += ymd + "/";
		saveUrl += ymd + "/";
		File dirFile = new File(savePath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}

		String fileName = photo.getOriginalFilename();

		// 检查扩展名
		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
		if (!Arrays.<String> asList(extMap.get(dirName).split(",")).contains(fileExt)) {
			map.put("result", "24");
			map.put("message", Code_24 );
			return map;
		}

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String newFileName = df.format(new Date()) + "_"+ getRandomCharAndNumr(8) + "." + fileExt;
		try {
			File uploadedFile = new File(savePath, newFileName);
			photo.transferTo(uploadedFile);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("result", "25");
			map.put("message", Code_25);
			return map;
		}

		int index1 = fileName.lastIndexOf("\\");
		if (index1 != -1) {
			fileName = fileName.substring(index1 + 1);
		}

		int index2 = fileName.lastIndexOf("/");
		if (index2 != -1) {
			fileName = fileName.substring(index2 + 1);
		}

		url = saveUrl + newFileName;
		map.put("result", "26");
		map.put("message", Code_26);
		map.put("url", url);

		return map;
	}
	
	
	  /** 
     * 获取随机字母数字组合 
     *  
     * @param length 
     *            字符串长度 
     * @return 
     */  
	public static String getRandomCharAndNumr(Integer length) {  
	  String str = "";  
	  Random random = new Random();  
	  for (int i = 0; i < length; i++) {  
	      boolean b = random.nextBoolean();  
	      if (b) { // 字符串  
	          // int choice = random.nextBoolean() ? 65 : 97; 取得65大写字母还是97小写字母  
	          str += (char) (97 + random.nextInt(26));// 取得大写字母  
	      } else { // 数字  
	          str += String.valueOf(random.nextInt(10));  
	      }  
	  }
	  return str;
	}
	
	/**
	 * 
	 * 上传照片 迅车贷
	 * 
	 * @param photo
	 * @return
	 */
	public static Map<String, Object> fileUploadFastcarLoan(HttpServletRequest request,MultipartFile photo,StaffRank sr,String savePath,String saveUrl) {

		// 文件的url地址
		String url = "";

		// 响应的map
		Map<String, Object> map = new HashMap<String, Object>();
	    SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

		// 定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");

		// 文件目录
		File uploadDir = new File(savePath);
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}
		
		// 检查目录是否存在
		if (!uploadDir.isDirectory()) {
			map.put("result", "20");
			map.put("message", Code_20);
			return map;
		}
		
		// 检查目录写权限
		if (!uploadDir.canWrite()) {
			map.put("result", "21");
			map.put("message", Code_21);
			return map;
		}

		// 创建文件夹
		savePath += sr.getLineName()+"/";
		saveUrl += sr.getLineName()+"/";
		
		//判断该条线 文件夹是否存在
		File lineNameFile = new File(savePath);
		if (!lineNameFile.exists()) {
			lineNameFile.mkdirs();
		}
		savePath += sr.getCompanyName()+"/";
		saveUrl += sr.getCompanyName()+"/";
		//判断该省公司 文件夹是否存在
		File companyNameFile = new File(savePath);
		if(!companyNameFile.exists()){
			companyNameFile.mkdirs();
		}
		savePath += sr.getOrganizationName()+"/";
		saveUrl += sr.getOrganizationName()+"/";
		//判断该县区机构 文件夹是否存在
		File organNameFile = new File(savePath);
		if(!organNameFile.exists()){
			organNameFile.mkdirs();
		}
		
		savePath += sr.getStoreName()+"/";
		saveUrl += sr.getStoreName()+"/";
		//判断分部文件夹是否存在
		File storeNameFile = new File(savePath);
		if(!storeNameFile.exists()){
			storeNameFile.mkdirs();
		}
		
		savePath += sr.getStaffName()+"/";
		saveUrl += sr.getStaffName()+"/";
		//判断该人员文件夹是否存在
		File staffNameFile = new File(savePath);
		if(!staffNameFile.exists()){
			staffNameFile.mkdirs();
		}
		
//		savePath += "name_"+df.format(new Date())+"/";
//		saveUrl += "name_"+df.format(new Date())+"/";
//		//判断该文件夹是否存在
//		File name = new File(savePath);
//		if(!name.exists()){
//			name.mkdirs();
//		}
		
		String fileName = photo.getOriginalFilename();

		// 检查扩展名
		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
		if (!Arrays.<String> asList(extMap.get("image").split(",")).contains(fileExt)) {
			map.put("result", "24");
			map.put("message", Code_24 );
			return map;
		}
		
		String newFileName = df.format(new Date()) + "_"+ new Random().nextInt(1000) + "." + fileExt;
		try {
			File uploadedFile = new File(savePath, newFileName);
			photo.transferTo(uploadedFile);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("result", "25");
			map.put("message", Code_25);
			return map;
		}

		int index1 = fileName.lastIndexOf("\\");
		if (index1 != -1) {
			fileName = fileName.substring(index1 + 1);
		}

		int index2 = fileName.lastIndexOf("/");
		if (index2 != -1) {
			fileName = fileName.substring(index2 + 1);
		}

		url = saveUrl + newFileName;
		map.put("result", "26");
		map.put("message", Code_26);
		map.put("url", url);

		return map;
	}
	
	/**
	 * 
	 * 上传照片 民盛出行
	 * 
	 * @param photo
	 * @return
	 */
	public static Map<String, Object> fileUploadTravel(HttpServletRequest request,MultipartFile photo,String savePath,String saveUrl) {

		// 文件的url地址
		String url = "";

		// 响应的map
		Map<String, Object> map = new HashMap<String, Object>();
	    SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

		// 定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");

		// 文件目录
		File uploadDir = new File(savePath);
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}
		
		// 检查目录是否存在
		if (!uploadDir.isDirectory()) {
			map.put("result", "20");
			map.put("message", Code_20);
			return map;
		}
		
		// 检查目录写权限
		if (!uploadDir.canWrite()) {
			map.put("result", "21");
			map.put("message", Code_21);
			return map;
		}
		String workNum = request.getParameter("workNum");
    	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    	String date = sf.format(new Date());
		String date2 = df.format(new Date());

		// 创建文件夹
		savePath += date+"/";
		saveUrl += date+"/";
		
		//判断当前日期文件夹是否存在
		File lineNameFile = new File(savePath);
		if (!lineNameFile.exists()) {
			lineNameFile.mkdirs();
		}
		
		if(StringUtils.isBlank(workNum)){
			map.put("result", "24");
			map.put("message", "岗位号不存在！" );
			return map;
		}
		
		savePath += workNum+"/";
		saveUrl += workNum+"/";
		//判断该用户是否存在
		File nameFile = new File(savePath);
		if(!nameFile.exists()){
			nameFile.mkdirs();
		}
		
		String fileName = photo.getOriginalFilename();

		// 检查扩展名
		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
		if (!Arrays.<String> asList(extMap.get("image").split(",")).contains(fileExt)) {
			map.put("result", "24");
			map.put("message", Code_24 );
			return map;
		}
		
		String newFileName = date2 + "_"+ new Random().nextInt(1000) + "." + fileExt;
		try {
			File uploadedFile = new File(savePath, newFileName);
			photo.transferTo(uploadedFile);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("result", "25");
			map.put("message", Code_25);
			return map;
		}

		int index1 = fileName.lastIndexOf("\\");
		if (index1 != -1) {
			fileName = fileName.substring(index1 + 1);
		}

		int index2 = fileName.lastIndexOf("/");
		if (index2 != -1) {
			fileName = fileName.substring(index2 + 1);
		}

		url = saveUrl + newFileName;
		map.put("result", "26");
		map.put("message", Code_26);
		map.put("url", url);

		return map;
	}
	
	
	/**
	 * 
	 * 上传语音
	 * 
	 * @param photo
	 * @return
	 */
	public static Map<String, Object> fileUpload3(HttpServletRequest request,MultipartFile photo,String savePath,String saveUrl) {

		// 文件的url地址
		String url = "";

		// 响应的map
		Map<String, Object> map = new HashMap<String, Object>();


		// 定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "ogg,ape,mp3,wav,wma,wmv,mid");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");

		// 文件目录
		File uploadDir = new File(savePath);

		// 检查目录是否存在
		if (!uploadDir.isDirectory()) {
			uploadDir.mkdirs();
		}
		
		// 检查目录写权限
		if (!uploadDir.canWrite()) {
			map.put("result", "21");
			map.put("message", Code_21);
			return map;
		}

		String dirName = "media";
		if (!extMap.containsKey(dirName)) {
			map.put("result", "22");
			map.put("message", Code_22);
			return map;
		}

		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		savePath += ymd + "/";
		saveUrl += ymd + "/";
		File dirFile = new File(savePath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}

		String fileName = photo.getOriginalFilename();

		// 检查扩展名
		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
		if (!Arrays.<String> asList(extMap.get(dirName).split(",")).contains(fileExt)) {
			map.put("result", "24");
			map.put("message", Code_24 );
			return map;
		}

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String newFileName = df.format(new Date()) + "_"+ getRandomCharAndNumr(8) + "." + fileExt;
		try {
			File uploadedFile = new File(savePath, newFileName);
			photo.transferTo(uploadedFile);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("result", "25");
			map.put("message", Code_25);
			return map;
		}

		int index1 = fileName.lastIndexOf("\\");
		if (index1 != -1) {
			fileName = fileName.substring(index1 + 1);
		}

		int index2 = fileName.lastIndexOf("/");
		if (index2 != -1) {
			fileName = fileName.substring(index2 + 1);
		}

		url = saveUrl + newFileName;
		map.put("result", "26");
		map.put("message", Code_26);
		map.put("url", url);

		return map;
	}

	public static Map<String, Object> fileUploadFastcarLoan(
			HttpServletRequest request, String photo, StaffRank sr,
			String savePath, String saveUrl) {

		// 文件的url地址
		String url = "";

		// 响应的map
		Map<String, Object> map = new HashMap<String, Object>();
	    SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

		// 定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");

		// 文件目录
		File uploadDir = new File(savePath);
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}
		
		// 检查目录是否存在
		if (!uploadDir.isDirectory()) {
			map.put("result", "20");
			map.put("message", Code_20);
			return map;
		}
		
		// 检查目录写权限
		if (!uploadDir.canWrite()) {
			map.put("result", "21");
			map.put("message", Code_21);
			return map;
		}

		// 创建文件夹
		savePath += sr.getLineName()+"/";
		saveUrl += sr.getLineName()+"/";
		
		//判断该条线 文件夹是否存在
		File lineNameFile = new File(savePath);
		if (!lineNameFile.exists()) {
			lineNameFile.mkdirs();
		}
		savePath += sr.getCompanyName()+"/";
		saveUrl += sr.getCompanyName()+"/";
		//判断该省公司 文件夹是否存在
		File companyNameFile = new File(savePath);
		if(!companyNameFile.exists()){
			companyNameFile.mkdirs();
		}
		savePath += sr.getOrganizationName()+"/";
		saveUrl += sr.getOrganizationName()+"/";
		//判断该县区机构 文件夹是否存在
		File organNameFile = new File(savePath);
		if(!organNameFile.exists()){
			organNameFile.mkdirs();
		}
		
		savePath += sr.getStoreName()+"/";
		saveUrl += sr.getStoreName()+"/";
		//判断分部文件夹是否存在
		File storeNameFile = new File(savePath);
		if(!storeNameFile.exists()){
			storeNameFile.mkdirs();
		}
		
		savePath += sr.getStaffName()+"/";
		saveUrl += sr.getStaffName()+"/";
		//判断该人员文件夹是否存在
		File staffNameFile = new File(savePath);
		if(!staffNameFile.exists()){
			staffNameFile.mkdirs();
		}
		
//		savePath += "name_"+df.format(new Date())+"/";
//		saveUrl += "name_"+df.format(new Date())+"/";
//		//判断该文件夹是否存在
//		File name = new File(savePath);
//		if(!name.exists()){
//			name.mkdirs();
//		}
//		String fileName = photo.getOriginalFilename();
		String photos[] = photo.split(",");
		// 检查扩展名
//		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
//		if (!Arrays.<String> asList(extMap.get("image").split(",")).contains(fileExt)) {
//			map.put("result", "24");
//			map.put("message", Code_24 );
//			return map;
//		}
//		
		String newFileName = df.format(new Date()) + "_"+ new Random().nextInt(1000) + "." + "jpg";
		try {

			Utils.generateImage(photos[1], savePath+newFileName);
//			File uploadedFile = new File(savePath, newFileName);
//			photo.transferTo(uploadedFile);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("result", "25");
			map.put("message", Code_25);
			return map;
		}

//		int index1 = fileName.lastIndexOf("\\");
//		if (index1 != -1) {
//			fileName = fileName.substring(index1 + 1);
//		}
//
//		int index2 = fileName.lastIndexOf("/");
//		if (index2 != -1) {
//			fileName = fileName.substring(index2 + 1);
//		}

		url = saveUrl + newFileName;
		map.put("result", "26");
		map.put("message", Code_26);
		map.put("url", url);

		return map;
	}
}
