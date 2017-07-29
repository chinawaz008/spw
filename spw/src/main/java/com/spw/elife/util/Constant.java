package com.spw.elife.util;

import java.io.PrintWriter;
import java.io.StringWriter;


/**
 * 常量定义
 * @author Administrator
 *
 */
public class Constant {
    /**
     * 请求返回结果,1表示成功
     */
    public static final String RESULT_TRUE="1";
    
    /**
     * 请求返回结果,0表示失败
     */
    public static final String RESULT_FALSE="0";
    
	/**
	 * 竖线分隔符
	 */
	public static final String CONSTANT_SP = "\\|";
	
	/**
	 * 逗号分隔符
	 */
	public static final String CONSTANT_SPLIT = ",";
	
	/**
	 * @ 分隔符
	 */
	public static final String CONSTANT_AT = "@";

	/**
	 * - 分隔符
	 */
	public static final String CONSTANT_ = "-";
	
	/**
	 * ; 分隔符
	 */
	public static final String CONSTANT_SEMI = ";";
	
	/**
	 * 空格 分隔符
	 */
	public static final String CONSTANT_SAPCE = " ";

	/**
	 * 业务权限-总公司权限类型1
	 */
	public static final String DATA_TYPE1 = "1";
	
	/**
	 * 业务权限-分公司权限类型2
	 */
	public static final String DATA_TYPE2 = "2";
	
	/**
	 * 行政权限类型3
	 */
	public static final String DATA_TYPE3 = "3";
	
	/**
	 * 教育经历类型 0
	 */
	public static String APPLY_EDUCATION = "0";
	
	public static String getTrace(Throwable t) {
        StringWriter stringWriter= new StringWriter();
        PrintWriter writer= new PrintWriter(stringWriter);
        t.printStackTrace(writer);
        StringBuffer buffer= stringWriter.getBuffer();
        return buffer.toString();
    }
	public static final String OFFICE_EXCEL_2003_POSTFIX = "xls";
    public static final String OFFICE_EXCEL_2010_POSTFIX = "xlsx";
    public static final String EMPTY = "";
    public static final String POINT = ".";
    public static final String NOT_EXCEL_FILE = " : 未找到文件!";
    public static final String PROCESSING = "执行中..."; 
    public static final String NOT_STAFF_ID = "系统异常: 员工id为空！"; 
	public static final String NOT_ID="系统异常：id为空!"; 
	public static final String SUCSESS="成功";   
	public static final String ERROR_STATUS="0";
	public static final String SUCSESS_STATUS="1"; 
	public static final String NOT_FIND_PHOTO="系统异常: 图片为空！"; 
	public static final String DATA_EXCEPTION="图片过大";  
	public static final String EMPTY_LIST="当前未查询到数据"; 
	public static final String EMPTY_DATE="日期不能为空"; 
	public static final String ERROR_DATE_FORMAT="日期格式不正确"; 
	public static final String ERROR_TIMES="该员工未分配考勤规则";
	public static final String ERROR_DETAIL="未查询到打卡记录";
}
