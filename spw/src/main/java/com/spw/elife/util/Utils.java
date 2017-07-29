package com.spw.elife.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.http.HttpResponse;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.InputSource;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.spw.elife.basics.bean.SelectEntity;
import com.spw.elife.common.Principal;

/**
 * 
 * @author lip
 */
public class Utils {
	private static final String FIRL_ADDRESS = "uploadAll.address";  
	private static final Logger logger = LoggerFactory.getLogger(Utils.class);
	public static final String SESSIONKEY_PRINCIPAL = "principal";
	public static final String LAST_PAGE = "";
	public static final String REDIRECT_HOME = "/";
	// 短信请求地址
	private final static String url = "http://v.juhe.cn/sms/send";
	// 短信的key
	private final static String key = "bf7b52e0bf3bc831300964dbda11d409";
	private static final Base64 base64 = new Base64(true);

	/**
	 * 获取编码字符集
	 * @param request
	 * @param response
	 * @return String
	 */
	public static String getCharacterEncoding(HttpServletRequest request,
			HttpServletResponse response) {
		
		if(null == request || null == response) {
			return "gbk";
		}
		
		String enc = request.getCharacterEncoding();
		if(null == enc || "".equals(enc)) {
			enc = response.getCharacterEncoding();
		}
		
		if(null == enc || "".equals(enc)) {
			enc = "gbk";
		}
		
		return enc;
	}

	/**
	 * 
	 * @param date1 <String>
	 * @param date2 <String>
	 * @return int
	 */
	public static int getMonthSpace(String date1, String date2){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal1 = new GregorianCalendar();
		int c = 0;
		try {
			cal1.setTime(sdf.parse(date1));
			Calendar cal2 = new GregorianCalendar();
			cal2.setTime(sdf.parse(date2));
			c =
			(cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR)) * 12 + cal1.get(Calendar.MONTH)
			- cal2.get(Calendar.MONTH);
		} catch (ParseException e) {
			logger.error("两日期相减异常");
		}
		return c;
	}

	/**
	 * 根据当前月份，获得某个月第一天
	 * len :往前或者后推迟len个月
	 */
	public static String getBeforeFirstMonthdate(String date, int len) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String arg[] = new String[2];
		if (date != null && date != "") {
			arg = date.split("-");
		} else {
			return null;
		}
		// 当前年份
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, Integer.parseInt(arg[0]));
		calendar.set(Calendar.MONTH, Integer.parseInt(arg[1]) - 1);
		calendar.add(Calendar.MONTH, len);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
//		logger.info(format.format(calendar.getTime()));
		return format.format(calendar.getTime());
	}

	/**
	 * 根据当前月份，获得某个月最后一天
	 * len :往前或者后推迟len个月
	 */
	public static String getBeforeLastMonthdate(String date, int len) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String arg[] = new String[2];
		if (date != null && date != "") {
			arg = date.split("-");
		} else {
			return null;
		}
		// 当前年份
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, Integer.parseInt(arg[0]));
		int month = Integer.parseInt(arg[1]) - 1;
		calendar.set(Calendar.MONTH, month + len);
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
//		logger.info(sf.format(calendar.getTime()));
		return sf.format(calendar.getTime());
	}

	/**
	 * 获得49滚动的日期始末
	 * 
	 * @return
	 */
	public static String[] getFortyNineRollTime(String date) {
		String[] args = new String[4];
		args[0] = getBeforeFirstMonthdate(date, -61);
		args[1] = getBeforeLastMonthdate(date, -50);
		logger.info("承保日期："+args[0] + ":::" + args[1]);
		args[2] = getBeforeFirstMonthdate(args[0], 48);
		args[3] = getBeforeLastMonthdate(args[1], 48);
		logger.info("缴费日期："+args[2] + ":::" + args[3]);
		return args;
	}
	
	/**
	 * 获得37滚动的日期始末
	 * 
	 * @return
	 */
	public static String[] getThirtySevenRollTime(String date) {
		String[] args = new String[4];
		args[0] = getBeforeFirstMonthdate(date, -49);
		args[1] = getBeforeLastMonthdate(date, -38);
		logger.info("承保日期："+args[0] + ":::" + args[1]);
		args[2] = getBeforeFirstMonthdate(args[0], 36);
		args[3] = getBeforeLastMonthdate(args[1], 36);
		logger.info("缴费日期："+args[2] + ":::" + args[3]);
		return args;
	}

	/**
	 * 获得25滚动的日期始末
	 * 
	 * @return
	 */
	public static String[] getTwentyFiveRollTime(String date) {
		String[] args = new String[4];
		args[0] = getBeforeFirstMonthdate(date, -37);
		args[1] = getBeforeLastMonthdate(date, -26);
		logger.info("承保日期："+args[0] + ":::" + args[1]);
		args[2] = getBeforeFirstMonthdate(args[0], 24);
		args[3] = getBeforeLastMonthdate(args[1], 24);
		logger.info("缴费日期："+args[2] + ":::" + args[3]);
		return args;
	}

	/**
	 * 获得13滚动的日期始末（固定）
	 * 
	 * @return
	 */
	public static String[] getRollTime(String date) {
		String[] args = new String[4];
		args[0] = getBeforeFirstMonthdate(date, -25);
		args[1] = getBeforeLastMonthdate(date, -14);
		logger.info("承保日期："+args[0] + ":::" + args[1]);
		args[2] = getBeforeFirstMonthdate(args[0], 12);
		args[3] = getBeforeLastMonthdate(args[1], 12);
		logger.info("缴费日期："+args[2] + ":::" + args[3]);
		return args;
	}

	/**
	 * 获得当月续期的日期始末
	 * 
	 * @return
	 */
	public static String[] getKuanZeroTime(String date) {
		String[] args = new String[4];
		args[0] = getBeforeFirstMonthdate(date, -12);
		args[1] = getBeforeLastMonthdate(date, -12);
		logger.info("承保日期："+args[0] + ":::" + args[1]);
		args[2] = getBeforeFirstMonthdate(date, 0);
		args[3] = getBeforeLastMonthdate(date, 0);
		logger.info("缴费日期："+args[2] + ":::" + args[3]);
		return args;
	}

	/**
	 * 获得宽一的日期始末
	 * 
	 * @return
	 */ 
	public static String[] getKuanOneTime(String date) {
//		String[] args = getDelayDay(date, -30);
		String[] arg = new String[4];
		String[] args = getKuanZeroTime(date);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		arg[0] = args[0];
		arg[1] = args[1];
		arg[2] = args[2];
		try {
			Calendar theCa = Calendar.getInstance();
			theCa.setTime(sdf.parse(args[3]));
			theCa.add(Calendar.DATE, 30);
			Date dates = theCa.getTime();
			arg[3] = sdf.format(dates);
		} catch (Exception e) {
			logger.error("获得日期往前后推迟天数失败"+e);
		}
		logger.info(arg[0] + ":::" + arg[1]);
		logger.info(arg[2] + ":::" + arg[3]);
//		String[] args = new String[2];
//		args[0] = getBeforeFirstMonthdate(date, -24);
//		args[1] = getBeforeLastMonthdate(date, -13);
//		logger.info(args[0] + ":::" + args[1]);
		return arg;
	}
	/**
	 * 获得宽末的日期始末
	 * 
	 * @return
	 */
	public static String[] getKuanTwoTime(String date) {
//		String[] args = getDelayDay(date, 60);
//		logger.info(args[0] + ":::" + args[1]);
//		String[] args = new String[2];
//		args[0] = getBeforeFirstMonthdate(date, -25);
//		args[1] = getBeforeLastMonthdate(date, -14);
		String[] arg = new String[4];
		String[] args = getKuanZeroTime(date);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		arg[0] = args[0];
		arg[1] = args[1];
		arg[2] = args[2];
		try {
			Calendar theCa = Calendar.getInstance();
			theCa.setTime(sdf.parse(args[3]));
			theCa.add(Calendar.DATE, 60);
			Date dates = theCa.getTime();
			arg[3] = sdf.format(dates);
		} catch (Exception e) {
			logger.error("获得日期往前后推迟天数失败"+e);
		}
		logger.info(arg[0] + ":::" + arg[1]);
		logger.info(arg[2] + ":::" + arg[3]);
		return arg;
	}
	
	/**
	 * 日期往前后推迟天数
	 * @param date
	 * @param days
	 * @return
	 */
//	public static String[] getDelayDay(String date,int days){
//		String[] args = getKuanZeroTime(date);
//		String[] arg = new String[2];
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//		Calendar theCa = Calendar.getInstance();
//		for (int i = 0; i < arg.length; i++) {
//			try {
//				theCa.setTime(sdf.parse(args[i]));
//				theCa.add(Calendar.DATE, -days);
//				Date dates = theCa.getTime();
//				arg[i] = sdf.format(dates);
//			} catch (ParseException e) {
//				logger.error("获得日期往前后推迟天数失败"+e);
//			}
//		}
//		return arg;
//	}
	
	
	/**
	 * 获得续期状态
	 * @param time
	 * @param count
	 * @return
	 */
	public static String getInsuranceStatus(String time, int count){
		long begin = 0;
		long end = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(sdf.parse(time));
			cal.add(Calendar.MONTH, count * 12);

			
			long date = sdf.parse(sdf.format(cal.getTime())).getTime();
			
			cal.setTime(new Date());
			cal.add(Calendar.MONTH, -12);
			Date times = cal.getTime();
			long today = sdf.parse(sdf.format(cal.getTime())).getTime();
			if(date==today) return "缴费日";
			begin = times.getTime();//开始
//			logger.info(sdf.format(cal.getTime()));
			cal.setTime(times);
			cal.add(Calendar.DAY_OF_MONTH, 60);
//			logger.info(sdf.format(cal.getTime()));
			end = cal.getTime().getTime();
			if (date > begin && date <= end) {
				return "提醒期";
			}
			
			end = begin;
//			logger.info(sdf.format(cal.getTime()));
			cal.setTime(times);
			cal.add(Calendar.DAY_OF_MONTH, -30);
			begin = sdf.parse(sdf.format(cal.getTime())).getTime();
//			logger.info(sdf.format(cal.getTime()));
			if (date > begin && date <= end) {
				return "宽一";
			}
			
			end = begin;
//			logger.info(sdf.format(cal.getTime()));
			cal.setTime(times);
			cal.add(Calendar.DAY_OF_MONTH, -60);
			begin = sdf.parse(sdf.format(cal.getTime())).getTime();
//			logger.info(sdf.format(cal.getTime()));
			if (date >= begin && date < end) {
				return "宽二";
			}
			
			cal.setTime(new Date());
			cal.add(Calendar.MONTH, -24);
			begin = sdf.parse(sdf.format(cal.getTime())).getTime();
//			logger.info(sdf.format(cal.getTime()));
			
			cal.setTime(times);
			cal.add(Calendar.DAY_OF_MONTH, -61);
			end = sdf.parse(sdf.format(cal.getTime())).getTime();
//			logger.info(sdf.format(cal.getTime()));
			if (date >= begin && date <= end) {
				return "复效管理期";
			}
			
			cal.setTime(new Date());
			cal.add(Calendar.MONTH, -24);
			cal.add(Calendar.DAY_OF_MONTH, -1);
			end = sdf.parse(sdf.format(cal.getTime())).getTime();
//			logger.info(sdf.format(cal.getTime()));
			if (date <= end) {
				return "退保期";
			}
			
		} catch (Exception e) {
			logger.error("获得续期状态"+Constant.getTrace(e));
		}
		return "已缴";
    }
		
	
	public static String[] getDistinctAboutRenewal(int type) throws ParseException{
		String[]args =new String[2];
		String begin = "";
		String end ="";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		switch (type) {
		case 1:
			cal.setTime(new Date());
			cal.add(Calendar.MONTH, -12);
			//提醒期
			Date times = cal.getTime();
			cal.setTime(times);
			cal.add(Calendar.DAY_OF_MONTH, 60);
			end = sdf.format(cal.getTime());
//			logger.info(end);
			cal.setTime(times);
			cal.add(Calendar.DAY_OF_MONTH, -60);
			begin = sdf.format(cal.getTime());
//			logger.info(begin);
//			begin = sdf.format(cal.getTime());
//			logger.info(sdf.format(cal.getTime()));
//			cal.setTime(new Date());
//			cal.add(Calendar.MONTH, -10);
//			end = sdf.format(cal.getTime());
			args[1] = end;
//			end = begin;
//			cal.setTime(times);
//			cal.add(Calendar.MONTH, -1);
//			begin = sdf.format(cal.getTime());
//			logger.info(sdf.format(cal.getTime()));
//			
//			end = begin;
//			cal.setTime(cal.getTime());
//			cal.add(Calendar.MONTH, -1);
//			begin = sdf.format(cal.getTime());
//			logger.info(sdf.format(cal.getTime()));
			args[0] = begin;
			logger.info(args[0]+"---"+args[1]);
			break;
		case 2:
			cal.setTime(new Date());
			cal.add(Calendar.MONTH, -24);
			begin = sdf.format(cal.getTime());
			args[0] = begin;
//			logger.info(sdf.format(cal.getTime()));
			String arg[] = getDistinctAboutRenewal(1);
			Date time = sdf.parse(arg[0]);
			cal.setTime(time);
			cal.add(Calendar.DAY_OF_MONTH, -1);
			end = sdf.format(cal.getTime());
			args[1] = end;
//			cal.setTime(new Date());
//			cal.add(Calendar.MONTH, -14);
//			cal.add(Calendar.DAY_OF_MONTH, -1);
//			end = sdf.format(cal.getTime());
//			args[1] = end;
			logger.info(args[0]+"---"+args[1]);
			break;
		case 3:
			cal.setTime(new Date());
			cal.add(Calendar.MONTH, -24);
			cal.add(Calendar.DAY_OF_MONTH, -1);
			end =sdf.format(cal.getTime());
			args[0] = end;
			logger.info(sdf.format(cal.getTime()));
			break;
		case 4://所有提醒（两个月前到过期两年）//未使用：待定
			begin =  getDistinctAboutRenewal(2)[0];
			args[0] = begin;
			logger.info(begin);
			end = getDistinctAboutRenewal(1)[1];
			args[1] = end;
			logger.info(end);
			break;
		default:
			break;
		}
		return args;
	}
    public static void main(String[] args) throws Exception {
//    	getKuanZeroTime("2017-06");
//    	getKuanOneTime("2017-06");
//    	getKuanTwoTime("2017-06");
//    	getRollTime("2017-06");
//      getTwentyFiveRollTime("2017-06");
//        getThirtySevenRollTime("2017-05");
//      getFortyNineRollTime("2017-05");
//    	getDistinctAboutRenewal(3);
//    	System.out.println(getInsuranceStatus("2016-04-06", 0));
    }
//    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//    	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
//    	Calendar cal = Calendar.getInstance();
//    	Date daystart = sdf2.parse("2017-1-11");
//    	cal.setTime(daystart);
//    	cal.add(Calendar.HOUR, 23);
//    	cal.add(Calendar.MINUTE, 59);
//    	cal.add(Calendar.SECOND, 59);
////    	System.err.println(sdf.format(cal.getTime()));
////    	RequestGroupBody dd = new RequestGroupBody();
////		List<String> ss = new ArrayList<>();
////		ss.add("4526");
////		ss.add("4562");
////		dd.setMembers(ss);
////		dd.setApproval(false);
////		dd.setGroupname("督训区-放松放松工作群");
////		dd.setMaxusers(2000);
////		dd.setOwner("lns");
////    	JSONObject jsonObject = JSONObject.fromObject(dd);
////    	String jsonObjectss = jsonObject.toString();
////    	jsonObjectss = jsonObjectss.replace("}", ",\"public\":false}");
////
////		Map<String, String> headers = new HashMap<String, String>();
////		headers.put("Authorization", "Bearer " + "YWMt3YizkPHBEeaLFnETIozEnQAAAAAAAAAAAAAAAAAAAAGBw-RAYDkR5qb0RWWuVqD-AgMAAAFaNnit3ABPGgBszhpV5fb9-zjcijfodbCjnPOFL5Dd4netr-ZIpeXqKA");
////    	JSONObject ds = PublicUtil.getHttpsData("/chatgroups", "post", headers, null, jsonObjectss);
////    	JSONObject gro = JSONObject.fromObject(ds.getString("data"));
////    	System.err.println(gro);
//    	
////    	Map<String, String> headers = new HashMap<String, String>();
////		headers.put("Authorization", "Bearer " + "YWMt3YizkPHBEeaLFnETIozEnQAAAAAAAAAAAAAAAAAAAAGBw-RAYDkR5qb0RWWuVqD-AgMAAAFaNnit3ABPGgBszhpV5fb9-zjcijfodbCjnPOFL5Dd4netr-ZIpeXqKA");
////		String path = "/chatgroups/" + "8197624627202" + "/users";
////		JSONObject ds = PublicUtil.getHttpsData(path, "get", headers, null, null);
////
////		String entities = ds.getString("data");
////		JSONArray json = JSONArray.fromObject(entities);
////		List<ResponseMember> list = (List<ResponseMember>) JSONArray.toList(json, ResponseMember.class);
////    	System.err.println(list);
////		List<Staff> staffs = new ArrayList<>();
////		for (int i = 10;i> 0 ;i--) {
////			Staff ss = new Staff();
////			if (i != 5) {
////				
////				ss.setPersonType(i+"");
////			}
////			staffs.add(ss);
////		}
////		Staff ss = new Staff();
////		ss.setPersonType("11");
////		staffs.add(ss);
////		String[] splitIds = {"personType"};
////		List<Object> composeSlips = Tools.composeSlip(staffs, splitIds);
////		// 后援人员
////		List<Staff> backList = new ArrayList<>();
////		// 前线人员
////		List<Staff> frontList = new ArrayList<>();
////		for (Object obj : composeSlips) {
////			List<Staff> subList = (List<Staff>) obj;
////			Staff staff = subList.get(0);
////			System.err.println(staff.getPersonType());
////			if (PublicUtil.PERSON_TYPE.equals(staff.getPersonType())) {// 后援
////				backList = subList;
////			}
////			else if (PublicUtil.PERSON_TYPE_LINE.equals(staff.getPersonType())) {
////				frontList = subList;
////			}
////		}
//    	List<RequestUserBody> users = new ArrayList<>();
////    	for (int i = 0;i< 10 ;i++) {
////    		RequestUserBody user = new RequestUserBody();
////    		user.setUsername(83401000 + i + "");
////    		user.setNickname("业务员" + i);
////    		user.setPassword(PublicUtil.password);
////    		users.add(user);
////    	}
////    	RequestUserBody user = new RequestUserBody();
////    	user.setUsername(83401000 +"代售点");
////    	user.setNickname("业务员0");
////    	user.setPassword(PublicUtil.password);
////    	users.add(user);
////    	user = new RequestUserBody();
////    	user.setUsername(83411000 +"");
////    	user.setNickname("业务员0");
////    	user.setPassword(PublicUtil.password);
////    	users.add(user);
////		JSONArray jsonarray = JSONArray.fromObject(users);
////		Map<String, String> headers = new HashMap<String, String>();
////		headers.put("Authorization", "Bearer " + "YWMt3YizkPHBEeaLFnETIozEnQAAAAAAAAAAAAAAAAAAAAGBw-RAYDkR5qb0RWWuVqD-AgMAAAFaNnit3ABPGgBszhpV5fb9-zjcijfodbCjnPOFL5Dd4netr-ZIpeXqKA");
////		String path = "/users";
////		JSONObject jsonObject = PublicUtil.getHttpsData(path, "POST", headers, null, jsonarray.toString());
//
////		ErrorEntity error = (ErrorEntity)JSONObject.toBean(jsonObject, ErrorEntity.class);
////		System.err.println(error);
////    	String sdsd = "Application null Entity user requires that property named username be unique, value of 83401000 exists";
////    	System.err.println(sdsd.substring(sdsd.lastIndexOf("value of") + 9,sdsd.lastIndexOf("exists") - 1));
////    	String sdssd = "username [83401000代售点] is not legal";
////    	if ( sdssd.contains("is not legal") && sdssd.contains("username")){
////    		System.err.println("我在里面");
////    		System.err.println(sdssd.substring(sdssd.lastIndexOf("username ") + 10,sdssd.lastIndexOf("is not legal") - 2));
////    	}
////    	
////    	Map<String, String> headers = new HashMap<String, String>();
////		headers.put("Authorization", "Bearer " + "YWMt3YizkPHBEeaLFnETIozEnQAAAAAAAAAAAAAAAAAAAAGBw-RAYDkR5qb0RWWuVqD-AgMAAAFaNnit3ABPGgBszhpV5fb9-zjcijfodbCjnPOFL5Dd4netr-ZIpeXqKA");
////		
////    	List<String> userss = new ArrayList<>();
////    	userss.add("1");
////    	userss.add("2");
////    	userss.add("3");
////		Iterator<String> sListIterator = userss.iterator();
////		while(sListIterator.hasNext()){  
////			String e = sListIterator.next();
////			if (e.equals("1")) {
////				sListIterator.remove();
////			}
////		}
////		System.err.println();
////		List<String> members = new ArrayList<>();
////		members.add("83401000");
////		members.add("83401001");
////		members.add("83401002");
////		members.add("83401003");
////		RequestGroupBody group = new RequestGroupBody();
////		group.setMembers(members);
////		group.setApproval(false);
////		group.setGroupname("督训区");
////		group.setMaxusers(2000);
////		group.setDesc("44554545454");
////		group.setOwner(Utils.getMaterialByPname("hx.owner"));
////		JSONObject jsonObject = JSONObject.fromObject(group);
////		String jsonObjectss = jsonObject.toString();
////		jsonObjectss = jsonObjectss.replace("}", ",\"public\":false}");
////		JSONObject ds = PublicUtil.getHttpsData("/chatgroups", "post", headers, null, jsonObjectss);
////
////		ErrorEntity error = (ErrorEntity)JSONObject.toBean(ds, ErrorEntity.class);
//    	
//    	List<String> sdsds = new ArrayList<>();
//    	for (int i = 0 ;i<40;i++) {
//    		sdsds.add(i+ "");
//    	}
//    	for (int j= 0;j< sdsds.size()/20;j++) {
//    		
//    		List<String> sdsd = sdsds.subList(j*20, j*20 + 20) ;
////    		System.err.println("size: " + sdsd.size());
//    		for (String ss : sdsd) {
////    			System.err.println(ss);
//    			
//    		}
//    	}
//    	List<String> sdsd = sdsds.subList(sdsds.size()/20*20, sdsds.size()) ;
//    	for (String ss : sdsd) {
//    		System.err.println(ss );
//    	}
//    	
//    	System.err.println(getFixLenthString(4));
//    	
//    	JSONObject jsonObject = JSONObject.fromObject("{\"timestamp\":1487729476324,\"organization\":\"lns-163\",\"duration\":0,\"application\":\"81c3e440-6039-11e6-a6f4-4565ae56a0fe\",\"applicationName\":\"18626333lhy\",\"data\":{\"groupid\":\"8735766413313\"},\"action\":\"post\",\"entities\":[],\"uri\":\"http://a1.easemob.com/lns-163/18626333lhy/chatgroups\"}");
//    	ErrorEntitySub error = (ErrorEntitySub)JSONObject.toBean(jsonObject, ErrorEntitySub.class);
//    	System.out.println(Utils.getBranchBackCompany("2@2")[1]);
//    	
//    }
//    public static void main(String[] args) throws Exception {
////    	getBeforeFirstMonthdate("2015-5", -1);
////        getBeforeLastMonthdate("2015-5", -1);
////    	getRollTime("2016-5");
////    	getKuanZeroTime("2016-5");
////    	getKuanZeroTime("2016-5");
////    	getKuanOneTime("2016-5");
////    	getKuanTwoTime("2016-5");
////        getTwentyFiveRollTime("2016-5");
////    	System.out.println(getLineType());
//    }
    
    /**
     * 返回长度为【strLength】的随机数，在前面补0 
     */  
    public static String getFixLenthString(int strLength) {  
          
        Random rm = new Random();  
          
        // 获得随机数  
        double pross = (1 + rm.nextDouble()) * Math.pow(10, strLength);  
      
        // 将获得的获得随机数转化为字符串  
        String fixLenthString = String.valueOf(pross);  
      
        // 返回固定的长度的随机数  
        return fixLenthString.substring(1, strLength + 1);  
    }

	public static double getHalfUp(double f) {
		BigDecimal b = new BigDecimal(f);
		return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public static Base64 getBase64() {
		return base64;
	}

	/**
	 * 判断字符串是否为数字
	 * 
	 * @param str
	 * @return
	 */

	public static boolean isNumeric(String str) {
		for (int i = str.length(); --i >= 0;) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 字符转ASC
	 * 
	 * @param st
	 * @return
	 */
	public static int getAsc(String st) {
		byte[] gc = st.getBytes();
		return (int) gc[0];
	}

	/**
	 * ASC转字符
	 * 
	 * @param backnum
	 * @return
	 */
	public static char backchar(int backnum) {
		return (char) backnum;
	}

	/**
	 * 获取当前Request对象.
	 * 
	 * @return 当前Request对象或{@code null}.
	 * @throws IllegalStateException
	 *             当前线程不是web请求抛出此异常.
	 */
	public static HttpServletRequest currentRequest()
			throws IllegalStateException {
		ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		if (attrs == null) {
			throw new IllegalStateException("当前线程中不存在 Request 上下文");
		}
		return attrs.getRequest();
	}

	/**
	 * 获取当前session对象. 若当前线程不是web请求或当前尚未创建{@code session}则返回{@code null}.
	 * 
	 * @return 当前session对象或{@code null}.
	 */
	public static HttpSession currentSession() {
		ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		if (attrs == null) {
			return null;
		}
		return attrs.getRequest().getSession(false);
	}

	public static String getRequestUrl(HttpServletRequest request) {
		String contextPath = request.getContextPath();
		String requestURI = request.getRequestURI();
		int i = requestURI.indexOf("?");
		if (i < 0) {
			i = requestURI.length();
		}
		return requestURI.substring(contextPath.length(), i);
	}

	public static void saveRequest() {
		HttpServletRequest request = currentRequest();
		request.getSession().setAttribute(LAST_PAGE,
				Utils.hashRequestPage(request));
		logger.debug("save request for {}", request.getRequestURI());
	}

	public static String retrieveSavedRequest() {
		HttpSession session = currentSession();
		if (session == null) {
			return REDIRECT_HOME;
		}
		String HashedlastPage = (String) session.getAttribute(LAST_PAGE);
		if (HashedlastPage == null) {
			return REDIRECT_HOME;
		} else {
			return retrieve(HashedlastPage);
		}
	}

	public static String retrieve(String targetPage) {
		byte[] decode = base64.decode(targetPage);
		try {
			String requestUri = new String(decode, "UTF-8");
			int i = requestUri.indexOf("/", 1);
			return requestUri.substring(i);
		} catch (UnsupportedEncodingException ex) {
			// this does not happen
			return null;
		}
	}

	private static String hashRequestPage(HttpServletRequest request) {
		String reqUri = request.getRequestURI();
		String query = request.getQueryString();
		if (query != null) {
			reqUri += "?" + query;
		}
		String targetPage = null;
		try {
			targetPage = base64.encodeAsString(reqUri.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException ex) {
			// this does not happen
		}
		return targetPage;
	}


	/**
	 * 保存文件
	 * 
	 * @param file
	 * @param request
	 * @param destPlace
	 * @return
	 */
	public static String saveFile(MultipartFile file,
			HttpServletRequest request, String destPlace) {
		String filePath = null;
		// 判断文件是否为空
		if (!file.isEmpty()) {
			try {
				String basePath = getProp().getProperty(destPlace);
				SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
				String date = sf.format(new Date());
				// 文件保存路径
				filePath = basePath + date + file.getOriginalFilename();
				File filedir = new File(filePath);
				if (!filedir.exists()) {
					filedir.mkdirs();
				}
				// 转存文件
				file.transferTo(new File(filePath));
			} catch (Exception e) {
				logger.error("保存失败" + e);
			}
		}
		return filePath;
	}

	/**
	 * 静态读配置文件内容
	 * 
	 * @return
	 */

	public static Properties getProp() {
		InputStream inputStream = Utils.class.getClassLoader()
				.getResourceAsStream("configuration.properties");
		Properties p = new Properties();
		try {
			p.load(inputStream);
		} catch (IOException e1) {
			e1.printStackTrace();
			logger.error("getProp->获取连接信息异常" + e1);
		}
		return p;
	}

	/**
	 * md5加密
	 * 
	 * @param str
	 * @return
	 */
	public static String getMD5Str(String str) {
		MessageDigest messageDigest = null;

		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			System.out.println("NoSuchAlgorithmException caught!");
			System.exit(-1);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		byte[] byteArray = messageDigest.digest();

		StringBuffer md5StrBuff = new StringBuffer();

		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
				md5StrBuff.append("0").append(
						Integer.toHexString(0xFF & byteArray[i]));
			} else {
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
			}
		}
		return md5StrBuff.toString();
	}

	/**
	 * 获取交易号
	 * 
	 * @return
	 */
	public static String getTransCode() {
		Date now = new Date();
		return DateUtil.format(now, "yyyyMMddHHmmss")
				.concat(getRandomString(4));
	}

	/**
	 * 生成随机字符串
	 * 
	 * @param length
	 * @return
	 */
	public static String getRandomString(int length) { // length表示生成字符串的长度
		String base = "0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * 根据时间 ： 年月日时分秒毫秒 + 随机个位数 生成18位订单号
	 * 
	 * @return
	 */
	public static String generatorOrderno() {
		Date date = new Date();
		String prefix = DateFormatUtils.format(date, "yyyyMMddHHmmssSSS");
		int suffix = new Random().nextInt(10); // 最后一位暂时不确定
		return prefix + suffix;
	}

	/**
	 * 发送短信
	 * 
	 * @throws IOException
	 */
	public static String sendSMS(String mobile, String content)
			throws IOException {
		mobile = java.net.URLEncoder.encode(mobile, "utf-8");
		content = java.net.URLEncoder.encode(content, "utf-8");
		return com.spw.elife.util.HttpRequest
				.get("http://114.215.140.42:8866/sms.aspx?userid=135&account=hfkmqc_yx&password=zhang1234&mobile="
						+ mobile + "&content=" + content + "&action=send");
	}

	/**
	 * 
	 * Description: 发送短信接口
	 * 
	 * @param type
	 *            发送类型
	 * @param phone
	 *            电话号码
	 * @param content
	 *            发送内容
	 * @return
	 * @throws UnsupportedEncodingException
	 *             抛出异常
	 * @see
	 */
	public static String sendType(Integer type, String phone, String content)
			throws UnsupportedEncodingException {
		String ret = "";
		try {
			String urlAll = "";
			if (type == 1) {
				String tpl_id = "5567";
				urlAll = sedLogin(phone, content, tpl_id);
			} else if (type == 2) {
				String tpl_id = "5568";
				urlAll = sedLogin(phone, content, tpl_id);
			} else if (type == 3) {
				String tpl_id = "7449";
				urlAll = sedLogin(phone, content, tpl_id);
			} else if (type == 4) {
				String tpl_id = "7712";
				urlAll = sedLogin(phone, content, tpl_id);
			} else if (type == 5) {
				String tpl_id = "9539";
				urlAll = sedLogin(phone, content, tpl_id);
			}

			String charset = "UTF-8";
			String jsonResult = get(urlAll, charset);// 得到JSON字符串

			net.sf.json.JSONObject object = net.sf.json.JSONObject
					.fromObject(jsonResult);// 转化为JSON类
			String code = object.getString("error_code");// 得到错误码

			// 错误码判断
			if (code.equals("0")) {
				// 根据需要取得数据
				// JSONObject jsonObject =
				// (JSONObject)object.getJSONArray("result").get(0);
				// System.out.println(jsonObject.getJSONObject("citynow").get("AQI"));
				logger.info("........短信发送成功!");
				ret = code;
			} else {
				logger.info("error_code:" + code + ",reason:"
						+ object.getString("reason"));
				ret = object.getString("reason");
			}
		} catch (Exception e) {
			logger.info("..............短信接口报错：" + e.getMessage());
		}
		return ret;
	}

	/**
	 * 发送短信接口 阿里云
	 * @param ParamString短信验证码
	 * @param RecNum 手机号,多条记录可以英文逗号分隔
	 * @param SignName签名名称
	 * @param TemplateCode模板CODE
	 * @return
	 */
	public static String sendALiMessage(String ParamString, String RecNum, String SignName,String TemplateCode){
		String host = "http://sms.market.alicloudapi.com";
	    String path = "/singleSendSms";
	    String method = "GET";
	    Map<String, String> headers = new HashMap<String, String>();
	    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
	    headers.put("Authorization", "APPCODE 你自己的AppCode");
	    Map<String, String> querys = new HashMap<String, String>();
	    querys.put("ParamString", "{\"no\":\"123456\"}");
	    querys.put("RecNum", "RecNum");
	    querys.put("SignName", "SignName");
	    querys.put("TemplateCode", "TemplateCode");
	    try {
	    	HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
	    	System.out.println(response.toString());
	    	//获取response的body
	    	//System.out.println(EntityUtils.toString(response.getEntity()));
	    	//判断成功或失败
//	    	{
//	    		"message": "The specified templateCode is wrongly formed.",
//	    		"success": false
//	    	}
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
		return method;
	}
	
	
	
	
	
	/**
	 * Description: 用户登陆短信验证接口
	 * 
	 * @param phone
	 * @return
	 * @throws UnsupportedEncodingException
	 * @see
	 */
	public static String sedLogin(String phone, String content, String tpl_id)
			throws UnsupportedEncodingException {
		StringBuffer sb = new StringBuffer();
		sb.append(url);
		sb.append("?mobile=" + phone);
		sb.append("&tpl_id=" + tpl_id);
		sb.append("&tpl_value=" + URLEncoder.encode(content, "UTF-8"));
		sb.append("&dtype=json");
		sb.append("&key=" + key);
		String urlAll = new StringBuffer(sb).toString();
		logger.info("........登陆验证短信：" + urlAll);
		return urlAll;
	}

	/**
	 * @param urlAll
	 *            :请求接口
	 * @param charset
	 *            :字符编码
	 * @return 返回json结果
	 */
	public static String get(String urlAll, String charset) {
		BufferedReader reader = null;
		String result = null;
		StringBuffer sbf = new StringBuffer();
		String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";// 模拟浏览器
		try {
			URL url = new URL(urlAll);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestMethod("GET");
			connection.setReadTimeout(30000);
			connection.setConnectTimeout(30000);
			connection.setRequestProperty("User-agent", userAgent);
			connection.connect();
			InputStream is = connection.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is, charset));
			String strRead = null;
			while ((strRead = reader.readLine()) != null) {
				sbf.append(strRead);
				sbf.append("\r\n");
			}
			reader.close();
			result = sbf.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 
	 * @param 生成随机数
	 * @return
	 */
	public static String RandomNumber(int length) {
		char[] chr = { '1', '2', '3', '5', '6', '7', '8', '9', 'A', 'B', 'C',
				'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
				'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
		Random random = new Random();
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < length; i++) {
			buffer.append(chr[random.nextInt(34)]);
		}
		return buffer.toString();
	}

	/**
	 * 获得大类 小类
	 * 
	 * @param bigId
	 * @return
	 * @throws DocumentException
	 *             当bigId为空的时候返回所有的大类，当输入bigId时候返回该大类下面所有小类
	 * @throws UnsupportedEncodingException
	 */
	public List<Map<String, Object>> getBigAndSmall(String bigId) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			String path = this.getClass().getClassLoader().getResource("")
					.getPath();
			InputSource is = new InputSource(path + "productData.xml");
			SAXReader reader = new SAXReader();
			reader.setEncoding("utf-8");
			Document doc = reader.read(is);
			Element rootElt = doc.getRootElement(); // 获取根节点
			// System.out.println("根节点：" + rootElt.getName()); // 拿到根节点的名称
			Iterator<?> eleIt = rootElt.elementIterator("BIG");
			while (eleIt.hasNext()) {
				Element e = (Element) eleIt.next();
				// System.out.println("子节点："+e.getName());
				Iterator<?> content = e.elementIterator("CONTENT"); // /获取子节点下的子节点
				String bigTitle = e.elementTextTrim("TITLE"); // /获取子节点下的子节点
				String bigCode = e.elementTextTrim("CODE"); // /获取子节点下的子节点
				if (bigId == "" || bigId == null) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("code", bigCode);
					map.put("title", bigTitle);
					list.add(map);
				} else if (bigCode.equals(bigId)) {
					while (content.hasNext()) {
						Element con = (Element) content.next();
						// System.out.println("子子节点：" + con.getName()); //
						// 拿到子子节点的名称
						Iterator<?> small = con.elementIterator("SMALL"); // /获取子节点下的子节点
						while (small.hasNext()) {
							Map<String, Object> map = new HashMap<String, Object>();
							Element sm = (Element) small.next();
							String title = sm.elementTextTrim("TITLE");
							String code = sm.elementTextTrim("CODE");
							map.put("code", code);
							map.put("title", title);
							list.add(map);
							// System.out.println("子子子节点：" + sm.getName()); //
							// 拿到子子节点的名称
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("查询产品大类小类报错" + e);
		}
		return list;
	}

	/**
	 * 根据大类id获得该大类的名称
	 * 
	 * @param bigId
	 * @return
	 */
	public String getBigName(String bigId) {
		String name = null;
		List<Map<String, Object>> list = getBigAndSmall("");
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			if (map.get("code").equals(bigId)) {
				name = (String) map.get("title");
			}
		}
		return name;
	}

	/**
	 * 根据大类、小类id获得该小类的名称
	 * 
	 * @param bigId
	 * @param smallId
	 * @return
	 */
	public String getSmallName(String bigId, String smallId) {
		String name = null;
		List<Map<String, Object>> list = getBigAndSmall(bigId);
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			if (map.get("code").equals(smallId)) {
				name = (String) map.get("title");
			}
		}
		return name;
	}
	/**
	 * 根据配置文件的名字，获得配置文件内容列表
	 * @param pName
	 * @return
	 */
	public static List<SelectEntity> getMaterialByName(String pName){
		Properties p = Utils.getProp();
		List<SelectEntity> returnList = new ArrayList<>();
		String name = p.getProperty(pName);
		String[] str = name.split(";");
		for (String a : str) {
			String str1[] = a.split(":");
			if (str1.length > 1) {
				SelectEntity entity = new SelectEntity();
				entity.setId(str1[0]);
				entity.setName(str1[1]);
				returnList.add(entity);
			}
		}
		return returnList;
	}
	
	
	/**
	 * 根据配置文件的名字，获得配置文件内容列表
	 * @param pName
	 * @return
	 */
	public static Map<String, String> getMaterial(String pName){
		Properties p = Utils.getProp();
		Map<String,String> m = new  LinkedHashMap<String, String>();
		String name = p.getProperty(pName);
		String[] str = name.split(";");
		for (String a : str) {
			String str1[] = a.split(":");
			for (int i = 0; i < str1.length; i++) {
				m.put(str1[0], str1[1]);
			}
		}
		return m;
	}
	
	/**
	 * 根据配置文件里的的id，获得name
	 * @param pName
	 * @return
	 */
	public static String getMaterialNameById(String pName,String id){
		String result = "";
		if (StringUtils.isNotEmpty(id)) {
			Map<String, String> map = getMaterial(pName);
			for (String key : map.keySet()) {
				if(key!=null && key.equals(id))
					result = map.get(key);
			}
		}
		return result;
	}
	
	/**
	 * 根据配置文件里的的name，获得id
	 * @param pName
	 * @return
	 */
	public static String getMaterialIdByName(String pName,String name){
		String result = "";
		Map<String, String> map = getMaterial(pName);
		for (String key : map.keySet()) {
	       if(key!=null && map.get(key).equals(name))
	        result = key;
		}
		return result;
	}
	
	/**
	 * 根据分公司后援组织类型获得分公司名称
	 * @param orgType
	 * @return
	 */
	public static String getBranchCompanyName(String orgType){
//		if(mlist==null){
//			List<Map<String,Object>> list = getBranchCompanyName();
//			mlist = list;
//		}
//		for (int i = 0; i < mlist.size(); i++) {
//			Map<String,Object> map = mlist.get(i);
//			for (String key : map.keySet()) {
//				System.out.println("key= "+ key + " and value= " + map.get(key));
//			}
//		}
		//暂定
		String result = "";
		if(orgType.equals("2@2") || orgType.equals("2@3"))
			result = "安徽省分公司";
		return result;
	}
	
	public static List<Map<String,String>> getBranchCompanyName(){
    	List<Map<String,String>> list= new ArrayList<Map<String,String>>();
    	String path =Utils.class.getClassLoader().getResource("").getPath();
    	InputSource is = null;
        is = new InputSource(path+"branch_company_org.xml");
		SAXReader reader = new SAXReader();
		reader.setEncoding("utf-8");
		try {
			Document doc = reader.read(is);
			Element rootElt = doc.getRootElement(); // 获取根节点
			Iterator<?> first = rootElt.elementIterator("DEPT_FIRST");
			 while(first.hasNext()) 
			 { 
				 Map<String,String> map = new HashMap<String, String>();
				 Element e = (Element) first.next();
				 String branchId = e.elementTextTrim("BRNACH_COMPANY_ID"); // /获取子节点下的子节点
				 String lineType = e.elementTextTrim("LINE_TYPE"); // /获取子节点下的子节点
				 String id = e.elementTextTrim("ID"); // /获取子节点下的子节点
				 String name = e.elementTextTrim("NAME"); // /获取子节点下的子节点
				 String hasNext = e.elementTextTrim("HAVE_NEXT"); // /获取子节点下的子节点
				 map.put("branchId", branchId);
				 map.put("lineType", lineType);
				 map.put("id", id);
				 map.put("name", name);
				 map.put("hasNext", hasNext);
				 list.add(map);
			 }
		} catch (DocumentException e) {
			logger.error("查询组织机构报错" + e);
		}
		return list;
	}
	
	/**
	 * 根据配置文件的名和里面配置的id获得对应的name
	 * @param num
	 * @param pName
	 * @return
	 */
	public static String getNameByPnameAndId(String num,String pName){
		Map<String, String> map = getMaterial(pName);
		String result = null;
		for (String key : map.keySet()) {
			if(key.equals(num)) 
				result = map.get(key);
        }
		return result;
	}
	
	
	//json解析  
	public static String[] parseJson(String json){ 
		String [] parse = null;
			String [] str =  json.split(";"); 
			String [] str2 = new String [str.length*2];   
			int l=0;
				for(int j=0;j<str.length;j++){  
					String [] str1 = str[j].split(":"); 
					str2[l] = str1[0]; 
					str2[l+1] = str1[1]; 
					l+=2;
				} 
			parse = str2;
		return parse;
	}
	//计算多点之间的距离公里
	public static String calc(List<String> list){ 
		double d = 0; 
		for(int i=0;i<list.size()-1;i++){ 
			String lngAndLat [] = list.get(i).split(",");  
			String lngAndLat1 [] = list.get(i+1).split(",");
			d+=Distance(Double.parseDouble(lngAndLat[0]), Double.parseDouble(lngAndLat[1]), Double.parseDouble(lngAndLat1[0]), Double.parseDouble(lngAndLat1[1]));
		}  
		return String.valueOf(Math.ceil(d/1000)).substring(0,String.valueOf(Math.ceil(d/1000)).length()-2);
	}  
	//计算两点之间的距离米（lng,lat）  
	public static double Distance(double long1, double lat1, double long2,  
	        double lat2) {  
	    double a, b, R;  
	    R = 6378137; // 地球半径  
	    lat1 = lat1 * Math.PI / 180.0;  
	    lat2 = lat2 * Math.PI / 180.0;  
	    a = lat1 - lat2;  
	    b = (long1 - long2) * Math.PI / 180.0;  
	    double d;  
	    double sa2, sb2;  
	    sa2 = Math.sin(a / 2.0);  
	    sb2 = Math.sin(b / 2.0);  
	    d = 2  
	            * R  
	            * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1)  
	                    * Math.cos(lat2) * sb2 * sb2));  
	    return d;  
	}   
	
	/**
	 * 登录着Id获取
	 * @return
	 */
	public static String getUserId(){
		String name = "";
		HttpSession session = currentSession();
		if (session == null) {
			return name;
		}
		Principal principal = (Principal) session.getAttribute(SESSIONKEY_PRINCIPAL);
		if(principal!=null && principal.getUserInfo()!=null) 
		 name = String.valueOf(principal.getUserInfo().getId());
		return name;
	 }
	
	//上传
		public static String uploadFile(MultipartFile formFile,String pubPath) throws FileNotFoundException,
				IOException {
			// 如果目录不存在，创建目录  
			new File(pubPath).mkdirs();  
			String type = formFile.getOriginalFilename().substring(formFile.getOriginalFilename().length()-4,formFile.getOriginalFilename().length());
			String path =  new SimpleDateFormat("yyyyMMddhhmmssSSS").format(new Date())+ 
					String.valueOf(Math.round(Math.random()*10))+ 
						"."+type; 
			path = path.replace("..",".");
			final File uploadFile = new File(pubPath,path);  
			/*System.out.println(uploadFile.getAbsolutePath());
			// 如果文件已存在，将旧文件删除 
			if(uploadFile.exists()){ 
				uploadFile.delete();
			} 
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
			}*/
			final OutputStream outputStream = new FileOutputStream(uploadFile);
			BufferedInputStream bufferedInputStream = null;
			BufferedOutputStream bufferedOutputStream = null;
			try {
				bufferedInputStream = new BufferedInputStream(
						formFile.getInputStream());
				bufferedOutputStream = new BufferedOutputStream(outputStream);

				final byte temp[] = new byte[(int) formFile.getSize()];
				int readBytes = 0;
				while ((readBytes = bufferedInputStream.read(temp)) != -1) {
					bufferedOutputStream.write(temp, 0, readBytes);
				}
				bufferedOutputStream.flush();

			} finally {
				if (bufferedOutputStream != null) {
					bufferedOutputStream.close();
				}
				if (bufferedInputStream != null) {
					bufferedInputStream.close();
				}
			}
			return uploadFile.getAbsolutePath().substring(2);
		}
		public static String uploadFile(MultipartFile formFile) throws FileNotFoundException,
		IOException {
			Properties p = Utils.getProp();
			// 如果目录不存在，创建目录  
			String s = 	p.getProperty(FIRL_ADDRESS); 
			new File(s).mkdirs();  
			String type = formFile.getOriginalFilename().substring(formFile.getOriginalFilename().length()-4,formFile.getOriginalFilename().length());
			String path =  new SimpleDateFormat("yyyyMMddhhmmssSSS").format(new Date())+ 
					String.valueOf(Math.round(Math.random()*10))+ 
					"."+type; 
			if(".apk".equals(type)){
				path = "ms.apk";
				File f = new File(s,path); 
				if(f.exists())
				{
					f.delete();
				}
			}
			path = path.replace("..",".");
			final File uploadFile = new File(s,path);  
			/*System.out.println(uploadFile.getAbsolutePath());
			// 如果文件已存在，将旧文件删除 
			if(uploadFile.exists()){ 
				uploadFile.delete();
			} 
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
			}*/
			final OutputStream outputStream = new FileOutputStream(uploadFile);
			BufferedInputStream bufferedInputStream = null;
			BufferedOutputStream bufferedOutputStream = null;
			try {
				bufferedInputStream = new BufferedInputStream(
						formFile.getInputStream());
				bufferedOutputStream = new BufferedOutputStream(outputStream);
				
				final byte temp[] = new byte[(int) formFile.getSize()];
				int readBytes = 0;
				while ((readBytes = bufferedInputStream.read(temp)) != -1) {
					bufferedOutputStream.write(temp, 0, readBytes);
				}
				bufferedOutputStream.flush();
				
			} finally {
				if (bufferedOutputStream != null) {
					bufferedOutputStream.close();
				}
				if (bufferedInputStream != null) {
					bufferedInputStream.close();
				}
			} 
			if(uploadFile.getAbsolutePath().substring(0,1).equals("D")){ 
				return uploadFile.getAbsolutePath().substring(2);
			}else{ 
				return uploadFile.getAbsolutePath().substring(5);
			}
		}
		
		/**
		 * 购置价区间名称获取
		 *  专用
		 * @param pName
		 * @param anId
		 * @return
		 */
		public static String getRegionMaterialName(String pName,String anId){
			Properties p = Utils.getProp();
			String name = p.getProperty(pName);
			String[] str = name.split("@");
			for (String a : str) {
				String str1[] = a.split(",");
				for (int i = 0; i < str1.length; i++) {
					String[] str2 = str1[1].split(";");
					for (String str22 : str2) {
						String[] str3 = str22.split(":");
						if (StringUtils.isNotEmpty(anId) && anId.equals(str3[0])) {
							return str3[1];
						}
					}
				}
			}
			return "";
		}
			
		 /** 
	     * @param class1 用于赋值的实体类  
	     * @param class2 需要待赋值的实体类 
	     * @author 
	     * 描述：反射实体类赋值 
	     */  
		public static void reflectionAttr(Object class1,Object class2) throws Exception{
			Class clazz1 = Class.forName(class1.getClass().getName());
			Class clazz2 = Class.forName(class2.getClass().getName());
			// 获取两个实体类的所有属性  
			Field[] fields1 = clazz1.getDeclaredFields();
			Field[] fields2 = clazz2.getDeclaredFields();
			// 遍历class1Bean，获取逐个属性值，然后遍历class2Bean查找是否有相同的属性，如有相同则赋值  
			for (Field f1 : fields1) {
				if(f1.getName().equals("id"))// 主键不赋值
					continue;
				Object value = invokeGetMethod(class1 ,f1.getName(),null);  
				for (Field f2 : fields2) {
					if(f1.getName().equals(f2.getName())){
						Object[] obj = new Object[1];
						obj[0] = value;
						invokeSetMethod(class2, f2.getName(), obj);
					}
				}
			}
		}

	    /** 
	     *  
	     * 执行某个Field的getField方法 
	     *  
	     * @param clazz 类 
	     * @param fieldName 类的属性名称 
	     * @param args 参数，默认为null 
	     * @return  
	     */  
		private static Object invokeGetMethod(Object clazz, String fieldName, Object[] args)
		{
			String methodName = fieldName.substring(0, 1).toUpperCase()+ fieldName.substring(1);
			Method method = null;
			try
			{
				method = Class.forName(clazz.getClass().getName()).getDeclaredMethod("get" + methodName);
				return method.invoke(clazz);
			}
			catch (Exception e)
			{
				e.printStackTrace();
				return "";
			}
		}
	
	    /** 
	     *  
	     * 执行某个Field的setField方法 
	     *  
	     * @param clazz 类 
	     * @param fieldName 类的属性名称 
	     * @param args 参数，默认为null 
	     * @return  
	     */  
		private static Object invokeSetMethod(Object clazz, String fieldName, Object[] args)
		{
			String methodName = fieldName.substring(0, 1).toUpperCase()+ fieldName.substring(1);
			Method method = null;
			try
			{
				Class[] parameterTypes = new Class[1];
				Class c = Class.forName(clazz.getClass().getName());
				Field field = c.getDeclaredField(fieldName);
				parameterTypes[0] = field.getType();
				method = c.getDeclaredMethod("set" + methodName,parameterTypes);
				return method.invoke(clazz,args);
			}
			catch (Exception e)
			{
				e.printStackTrace();
				return "";
			}
		}
		
		public static String changeExcelParam(Object obj){
			JSONObject jsonObject = JSONObject.fromObject(obj);
			return jsonObject.toString().replaceAll("\"", "'");
		}
//		 public static void main(String[] args) {
//			System.out.println(Utils.getBackJobName("1@1@5", "1"));  
//			System.out.println(Utils.getBackCompany("1@1@2")[0]); 
//			System.out.println(Utils.getJobName("2"));
//			System.out.println(Utils.getBranchBackCompany("2@2")[1]);
//			System.out.println(Utils.getJobId("分部经理"));
//			System.out.println(Utils.getBackJobId("战略总监"));
//			System.out.println(getBranchCompanyName("106","8"));   
//			System.out.println(Utils.getJob("", "","", ""));
//			System.out.println(getMaterialIdByName("linetype","淘融"));
//		}  
		
		
		/**
		 * Base 64 转 图片
		 * @param imgStr
		 * @param path
		 * @return
		 */
		public static boolean generateImage(String imgStr, String path) {
			if (imgStr == null)	return false;
		        BASE64Decoder decoder = new BASE64Decoder();
				try {
					// 解密
					 byte[]	b = decoder.decodeBuffer(imgStr);
					  // 处理数据
					  for (int i = 0; i < b.length; ++i) {
						 if (b[i] < 0) {
							 b[i] += 256;
						 }
					  }
		            OutputStream out = new FileOutputStream(path);
					out.write(b);
					out.flush();
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}
				return true;
			}
		
		/**
		 * @Description: 根据图片地址转换为base64编码字符串
		 * @Author: 
		 * @CreateTime: 
		 * @return
		 */
		public static String getImageStr(String imgFile) {
		    InputStream inputStream = null;
		    byte[] data = null;
		    try {
		        inputStream = new FileInputStream(imgFile);
		        data = new byte[inputStream.available()];
		        inputStream.read(data);
		        inputStream.close();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		    // 加密
		    BASE64Encoder encoder = new BASE64Encoder();
		    return encoder.encode(data);
		}
		
		/**
		 * 根据配置文件的名字，获得配置文件内容
		 * @param pName
		 * @return
		 */
		public static String getMaterialByPname(String pName){
			Properties p = Utils.getProp();
			return p.getProperty(pName);
		}
}
