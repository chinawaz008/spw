package com.spw.elife.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

public class DateUtil {
	
	/**
	 * 将PHP时间戳转换成字符串时间
	 * 
	 * @param timestamp
	 *            时间戳
	 * @param partten
	 *            时间格式
	 * @return
	 */
	public static String changeTimestampToStrDate(String timestamp, String partten) {
		String strDate = null;
		long time = Long.valueOf(timestamp) * 1000L;
		Date date = new Date(time);
		if (StringUtils.isBlank(partten)) { // 默认格式
			partten = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat format = new SimpleDateFormat(partten);
		strDate = format.format(date);
		return strDate;
	}

	/**
	 * 将字符串时间转换成PHP时间戳
	 * @param strDate 字符串时间
	 * @return
	 */
	public static String changeStrDateToTimestamp(String strDate) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timestamp = null;
		Date date;
		try {
			date = format.parse(strDate);
			timestamp = String.valueOf(date.getTime());
			timestamp = timestamp.substring(0, 10);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return timestamp;
	}
	/**
	 * 将当前的时间转换为php时间戳
	 * @param date
	 * @return
	 */
	public static long GenerateTimestamp(Date date){
	    try{
	        return (date.getTime()/1000);
	    }
	    catch(Exception ex){
	        return 0;
	    }
	}
	
	/**
	 * 根据给定的格式，格式化时间
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String format(Date date, String pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		if(null != date){
			return sdf.format(date);
		}else{
			return null;
		}
	}
	
	/**
	 * 获取当年年份
	 * @return
	 */
	public static String getCurYear(){
		Calendar c = Calendar.getInstance();
		String year = String.valueOf(c.get(Calendar.YEAR));
		return year;
	} 
	 
	/**
	 * 获取当月月份
	 * @return
	 */
	public static String getCurMonth(){
		Calendar c = Calendar.getInstance();
		String month = String.valueOf(c.get(Calendar.MONTH)+1);
		return month;
	}
	
	/**
	 * 格式化日期 为"yyyy'年'MM'月'dd'日'"
	 * @param date
	 * @return
	 */
	public static String getFormatDate(String date){
		String year = date.substring(0,4);
		String month = date.substring(5,7);
		String day = date.substring(8,10);
		date = year+"年"+month+"月"+day+"日";
		return date;
	}
	
	/** 
     * 根据年 月 获取对应的月份 天数 
     * */  
    public static int getDaysByYearMonth(String date) {
    	String years = date.substring(0,4);
    	int year = Integer.parseInt(years);
    	String months = date.substring(5,7);
    	int month = Integer.parseInt(months);
        Calendar a = Calendar.getInstance();  
        a.set(Calendar.YEAR, year);  
        a.set(Calendar.MONTH, month - 1);  
        a.set(Calendar.DATE, 1);  
        a.roll(Calendar.DATE, -1);  
        int maxDate = a.get(Calendar.DATE);  
        return maxDate;  
    }  
    
    /** 
     * 获取当月的 天数 
     * */  
    public static int getCurrentMonthDay() {  
          
        Calendar a = Calendar.getInstance();  
        a.set(Calendar.DATE, 1);  
        a.roll(Calendar.DATE, -1);  
        int maxDate = a.get(Calendar.DATE);  
        return maxDate;  
    }  
	
    /** 
     * 根据年月获得当月的天数集合
     * */  
    public static List<String> getCurrentMonthDayList(String date) {  
    	List<String> list = new ArrayList<String>();
    	int maxDate = getDaysByYearMonth(date);
    	for (int i = 1; i <= maxDate; i++) {
    		String dates ="";
    		if(i<10){
    			 dates = date +"-0"+ i;
    		}else{
    			 dates = date +"-"+ i;
    		}
    		list.add(dates);
		}
    	return list;  
    }  
    
    /** 
     * 根据日期 找到对应日期的 星期 
     */  
    public static String getDayOfWeekByDate(String date) {  
        String dayOfweek = "-1";  
        try {  
            SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");  
            Date myDate = myFormatter.parse(date);  
            SimpleDateFormat formatter = new SimpleDateFormat("E");  
            String str = formatter.format(myDate);  
            dayOfweek = str;  
              
        } catch (Exception e) {  
            System.out.println("错误!");  
        }  
        return dayOfweek;  
    }  
    
	/** 
    * 得到1年后的时间 
    *  
    * @param d 
    * @param day 
    * @return 
    */  
   public static String getDateAfter(Date d) {  
	   SimpleDateFormat sdf= new SimpleDateFormat("yyyy年MM月dd日");
       Date myDate = null;
       String result = null;
       if(d!=null){
    	   Calendar   rightNow   =   Calendar.getInstance(); 
    	   rightNow.setTime(d); 
    	   rightNow.add(Calendar.YEAR,1); 
    	   myDate= rightNow.getTime();
    	   result = sdf.format(myDate);
       }
       return result;  
   }  
   /**
    * 推某个月后的时间
    */
   public static String getOneMonth(Date d,int num) {
		SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Calendar c = Calendar.getInstance();
		c.setTime(d); 
		c.add(Calendar.MONTH, num);
		return s.format(c.getTime());
   }
   /**
    * 两个日期相减获得天数
    */
   public static long getBetweenDays(Date date1,Date date2) {
	   long day = (date1.getTime()-date2.getTime())/(24*60*60*1000)>0 ? (date1.getTime()-date2.getTime())/(24*60*60*1000):
		   (date2.getTime()-date1.getTime())/(24*60*60*1000);
		return day;
   }
   
   /**
    * 格式化数字
    * @param d
    * @return
    */
   public static double getFormatData(double d){
	  DecimalFormat df=new DecimalFormat("#.00"); 
	  String date = df.format(d);
	  double result = Double.parseDouble(date);
	  return result;
   }
   
   /**
    * 四舍五入格式化数字,两位小数
    * * @param f
    * * @param num
    */
   public static double getFourFiveData(double f,int num){
	    BigDecimal   b   =   new   BigDecimal(f);  
	    return   b.setScale(num,RoundingMode.HALF_UP).doubleValue();  
   }
   
   
   /**
    * 两个数字相除
    * @param one
    * @param two
    * @param num 保留位数
    * @return
    */
   public static double getFormatRate(double one,double two,int num){
	   BigDecimal a= new BigDecimal(one);
	   BigDecimal b= new BigDecimal(two);
	   BigDecimal c = a.divide(b,num,BigDecimal.ROUND_HALF_UP);
	   return c.doubleValue();
   } 
   
   /**
    * 比较一个时间是否比当前时间小
    * @param time
    * @return
    */
   public static boolean compareTime(String time){
	    Date bt = new Date(); //第一个时间
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date et;
		try {
			et = f.parse(time);
			if (bt.before(et)){
				return false;
			}else{
				return true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
   }
   
   /**
    * 比较一个时间是否比当前日期小
    * @param date
    * @return
    */
   public static boolean compareDate(String date){
	   Date bt = new Date(); //第一个时间
	   SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
	   Date et;
	   try {
		   et = f.parse(date);
		   if (bt.before(et)){
			   return false;
		   }else{
			   return true;
		   }
	   } catch (ParseException e) {
		   e.printStackTrace();
	   }
	   return false;
   }
   
   /**
    * 根据季度查出所在月份
    * @param quarter
    * @return
    */
   public static String[] getMonthByQuarter(String quarter){
	   String arg[] = new String[3];
	   int num = Integer.parseInt(quarter);
	   switch (num) {
		case 1:
			arg[0] ="01";
			arg[1] ="02";
			arg[2] ="03";
			break;
		case 2:
			arg[0] ="04";
			arg[1] ="05";
			arg[2] ="06";
			break;
		case 3:
			arg[0] ="07";
			arg[1] ="08";
			arg[2] ="09";
			break;
		case 4:
			arg[0] ="10";
			arg[1] ="11";
			arg[2] ="12";
			break;
		default:
			break;
		}
	   return arg;
   }
   
   /**
   * 字符串转换成日期
   * @param str
   * @return date
   */
   public static Date StrToDate(String str) {
     
      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      Date date = null;
      try {
       date = format.parse(str);
      } catch (ParseException e) {
       e.printStackTrace();
      }
      return date;
   }
   
   /**
   * 字符串转换成日期判断 年月日
   * @param str
   * @return true 日期 false 非日期
   */
	public static boolean CheckStrToDate(String str) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			format.parse(str);
		} catch (ParseException e) {
			return false;
		}
		return true;
   }
	/**
	 * 字符串转换成日期判断 年月日 时分
	 * @param str
	 * @return true 日期 false 非日期
	 */
	public static boolean CheckStrToDateTime(String str) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			format.parse(str);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}
	
   /**
    * 去掉list中重复的数据
    * @param list
    */
   @SuppressWarnings({ "rawtypes", "unchecked" })
   public static  void  removeDuplicateWithOrder(List list)   { 
	      Set set  =   new  HashSet(); 
	      List newList  =   new  ArrayList(); 
	   for  (Iterator iter  =  list.iterator(); iter.hasNext();)   { 
	         Object element  =  iter.next(); 
	         if  (set.add(element)) 
	            newList.add(element); 
	     } 
	     list.clear(); 
	     list.addAll(newList); 
	}  
    
   public static String curTimeByLayout(String patten){  
	   try {
		   SimpleDateFormat sdf = new SimpleDateFormat(patten); 
		   return sdf.format(new Date());
	} catch (Exception e) {
		e.fillInStackTrace();
	} 
	   return "";
   } 
    
   public static String formatTime(String time,String patten){  
	   try {
		   SimpleDateFormat sdf = new SimpleDateFormat(patten);  
		   return sdf.format(time);
	} catch (Exception e) {
		e.fillInStackTrace();
	} 
	   return "";
   } 
    
   public static Date formatDate(String date,String patten){  
	   try {
		   SimpleDateFormat sdf = new SimpleDateFormat(patten);  
		   Date dates = sdf.parse(date);
		   return sdf.parse(date); 
	} catch (Exception e) {
		e.fillInStackTrace();
	} 
	   return null;
   } 
    
   public static boolean compare(String beginDate,String endDate){  
	   boolean flag = false;
	   try {
		   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		   Date bDate;
		   bDate = sdf.parse(beginDate); 
		    Date eDate = sdf.parse(endDate);
		   if(bDate.getTime()>eDate.getTime()){ 
			   flag =  true; 
		   }
	  }catch (Exception e) { 
		throw new RuntimeException("时间工具类格式化时间异常: "+beginDate+"-"+endDate+e);
	} 
		return flag;
   }
   
//   public static void main(String[] args) {
//	List<String> list = new ArrayList<>();
//	list.add("1");
//	list.add("2");
//	list.add("1");
//	removeDuplicateWithOrder(list);
//	System.out.println(list);
//   }
   
   public static String timeFormat(long timeMillis, String pattern) {
       SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.CHINA);
       return format.format(new Date(timeMillis));
   }

}
