package com.spw.elife.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class MapUtils {  
    //private static double EARTH_RADIUS = 6378.137;  
    private static double EARTH_RADIUS = 6371.393;  
    private static double rad(double d)  
    {  
       return d * Math.PI / 180.0;  
    }  

    /** 
     * 计算两个经纬度之间的距离 
     * @param lat1 
     * @param lng1 
     * @param lat2 
     * @param lng2 
     * @return 
     */  
    public static double GetDistance(double lat1, double lng1, double lat2, double lng2)  
    {  
       double radLat1 = rad(lat1);  
       double radLat2 = rad(lat2);  
       double a = radLat1 - radLat2;      
       double b = rad(lng1) - rad(lng2);  
       double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +   
        Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));  
       s = s * EARTH_RADIUS;  
       s = Math.round(s * 1000);  
       return s;  
    }  
      
    /**  
     * 将一个 JavaBean 对象转化为一个  Map  
     * @param bean 要转化的JavaBean 对象  
     * @return 转化出来的  Map 对象  
     * @throws IntrospectionException 如果分析类属性失败  
     * @throws IllegalAccessException 如果实例化 JavaBean 失败  
     * @throws InvocationTargetException 如果调用属性的 setter 方法失败  
     */    
    @SuppressWarnings({ "rawtypes", "unchecked" })    
    public static Map convertBean(Object bean) {    
    	Map returnMap = new HashMap();    
    	try {
    		 Class type = bean.getClass();    
    	        BeanInfo beanInfo = Introspector.getBeanInfo(type);    
    	        
    	        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();    
    	        for (int i = 0; i< propertyDescriptors.length; i++) {    
    	            PropertyDescriptor descriptor = propertyDescriptors[i];    
    	            String propertyName = descriptor.getName();    
    	            if (!propertyName.equals("class")) {    
    	                Method readMethod = descriptor.getReadMethod();    
    	                Object result = readMethod.invoke(bean, new Object[0]);    
    	                if (result != null) {    
    	                    returnMap.put(propertyName, result);    
    	                } else {    
    	                    returnMap.put(propertyName, "");    
    	                }    
    	            }    
    	        }    
		} catch (Exception e) {
			e.printStackTrace();
		}
        return returnMap;    
    }  
  
  
  
/**  
     * 将一个 Map 对象转化为一个 JavaBean  
     * @param type 要转化的类型  
     * @param map 包含属性值的 map  
     * @return 转化出来的 JavaBean 对象  
     * @throws IntrospectionException 如果分析类属性失败  
     * @throws IllegalAccessException 如果实例化 JavaBean 失败  
     * @throws InstantiationException 如果实例化 JavaBean 失败  
     * @throws InvocationTargetException 如果调用属性的 setter 方法失败  
     */    
    @SuppressWarnings("rawtypes")
	public static Object convertMap(Class  type, Map  map) {
    	Object obj = new Object();
    	try {
    		BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性    
            obj = type.newInstance(); // 创建 JavaBean 对象    
            // 给 JavaBean 对象的属性赋值    
            PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();    
            for (int i = 0; i< propertyDescriptors.length; i++) {    
                PropertyDescriptor descriptor = propertyDescriptors[i];    
                String propertyName = descriptor.getName();    
        
                if (map.containsKey(propertyName)) {    
                    // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。    
                    Object value = map.get(propertyName);    
                    Object[] args = new Object[1];    
                    args[0] = value;    
                    descriptor.getWriteMethod().invoke(obj, args);    
                }    
            }   
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
    }  
    
    /**
     * 把一个老对象赋值给一个新对象，结果是新对象的内容替换到老对象里了
     * @param oldUser
     * @param newUser
     */
	public static void copyProperty(Object oldUser,Object newUser){
		try {
			//新的class
	        Class<?> newClass = newUser.getClass();
	        //老的class
	        Class<?> oldClass = oldUser.getClass();
	        //该类所有的属性
	        Field[] newFields = newClass.getDeclaredFields();
	        //新的属性
	        Field newField = null;
	        //老的属性
	        Field oldField = null;
	        for(Field f : newFields){
	            //类中的属性名称
	            String fieldName = f.getName();
	            //通过属性名称获取属性
	            newField = newClass.getDeclaredField(fieldName);
	            //获取属性的值时需要设置为 true 则指示反射的对象在使用时应该取消 Java 语言访问检查。
	            //值为 false 则指示反射的对象应该实施 Java 语言访问检查。 
	            newField.setAccessible(true);
	            //根据属性获取对象上的值
	            Object newObject = newField.get(newUser);
	            //过滤空的属性或者一些默认值
	            if (isContinue(newObject)) {
	                continue;
	            }
	            oldField = oldClass.getDeclaredField(fieldName);
	            oldField.setAccessible(true);
	            oldField.set(oldUser, newObject);
	        }
		} catch (Exception e) {
			System.out.println("赋值对象异常"+Constant.getTrace(e));
		}
        
    }
    /**
     *  是否跳出本次循环
      * @author 2014-11-6 上午11:37:22
      * @param object
      * @return true 是 有null或者默认值
      *         false 否 有默认值
     */
    private static boolean isContinue(Object object){
        if (object == null || "".equals(object)) {
            return true;
        }
        String valueStr = object.toString();
        if ("0".equals(valueStr) || "0.0".equals(valueStr)) {
            return true;
        }
        return false;
    }
    
    
    /** 
     * set属性的值到Bean 
     * @param bean 
     * @param valMap 
     */ 
    public static void setFieldValue(Object bean, Map<String, String> valMap) {  
        Class<?> cls = bean.getClass();  
        // 取出bean里的所有方法  
        Method[] methods = cls.getDeclaredMethods();  
        Field[] fields = cls.getDeclaredFields();  
   
        for (Field field : fields) {  
            try {  
                String fieldSetName = parSetName(field.getName());  
                if (!checkSetMet(methods, fieldSetName)) {  
                    continue;  
                }  
                Method fieldSetMet = cls.getMethod(fieldSetName, field.getType());  
                String value = valMap.get(field.getName());
                System.out.println(fieldSetName+"=="+value);
                if (null != value && !"".equals(value)) {  
                    String fieldType = field.getType().getSimpleName();  
                    if ("String".equals(fieldType)) {  
                        fieldSetMet.invoke(bean, value);  
                    } else if ("Date".equals(fieldType)) {  
                        Date temp = parseDate(value);  
                        fieldSetMet.invoke(bean, temp);  
                    } else if ("Integer".equals(fieldType) || "int".equals(fieldType)) {  
                        Integer intval = Integer.parseInt(value);  
                        fieldSetMet.invoke(bean, intval);  
                    } else if ("Long".equalsIgnoreCase(fieldType)) {  
                        Long temp = Long.parseLong(value);  
                        fieldSetMet.invoke(bean, temp);  
                    } else if ("Double".equalsIgnoreCase(fieldType)) {  
                        Double temp = Double.parseDouble(value);  
                        fieldSetMet.invoke(bean, temp);  
                    } else if ("Boolean".equalsIgnoreCase(fieldType)) {  
                        Boolean temp = Boolean.parseBoolean(value);  
                        fieldSetMet.invoke(bean, temp);  
                    } else {  
                        System.out.println("not supper type" + fieldType);  
                    }  
                }  
            } catch (Exception e) {  
            	   continue;  
            }  
        }  
   
    }
    
    
    /** 
     * 格式化string为Date 
     * @param datestr 
     * @return date 
     */ 
    private static Date parseDate(String datestr) {  
        if (null == datestr || "".equals(datestr)) {  
            return null;  
        }  
        try {  
            String fmtstr = null;  
            if (datestr.indexOf(':') > 0) {  
                fmtstr = "yyyy-MM-dd HH:mm:ss";  
            } else {  
                fmtstr = "yyyy-MM-dd";  
            }  
            SimpleDateFormat sdf = new SimpleDateFormat(fmtstr, Locale.UK);  
            return sdf.parse(datestr);  
        } catch (Exception e) {  
            return null;  
        }  
    }  
    /** 
     * 拼接在某属性的 set方法 
     * @param fieldName 
     * @return String 
     */ 
    private static String parSetName(String fieldName) {  
        if (null == fieldName || "".equals(fieldName)) {  
            return null;  
        }  
        return "set" + fieldName.substring(0, 1).toUpperCase()  
                + fieldName.substring(1);  
    } 
    /** 
     * 判断是否存在某属性的 set方法 
     * @param methods 
     * @param fieldSetMet 
     * @return boolean 
     */ 
    private static boolean checkSetMet(Method[] methods, String fieldSetMet) {  
        for (Method met : methods) {  
            if (fieldSetMet.equals(met.getName())) {  
                return true;  
            }  
        }  
        return false;  
    }  
    //参考文献http://www.open-open.com/code/view/1448191996149
    public static void main(String[] args) throws Exception {
	}
}  