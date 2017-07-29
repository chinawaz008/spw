package com.spw.elife.basics.service;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.springframework.stereotype.Service;

import toonasofts.conductor.util.CExcelConfig;
import toonasofts.conductor.util.ExcelCreater;
import toonasofts.conductor.util.ExcelParameter;
import toonasofts.conductor.util.ServiceManager;
import toonasofts.conductor.util.Tools;

import com.spw.elife.util.Constant;


/**
 * 共通方法service
 *
 * @author wpf
 */
@Service
public class ImportService {
	
	private final static Logger log = Logger.getLogger(ImportService.class);
	 /**
     * read the Excel file
     * @param path the path of the Excel file
     * @return
     * @throws IOException
     */
    public  String readExcel(String path) throws IOException {
    	String result = null;
        if (path == null || Constant.EMPTY.equals(path)) {
            return result;
        } else {
            String postfix = getPostfix(path);
            if (!Constant.EMPTY.equals(postfix)) {
            	result = postfix;
            } else {
                log.error(path + Constant.NOT_EXCEL_FILE);
            }
        }
        return result;
    }
    /**
     * get postfix of the path
     * @param path
     * @return
     */
    public static String getPostfix(String path) {
        if (path == null || Constant.EMPTY.equals(path.trim())) {
            return Constant.EMPTY;
        }
        if (path.contains(Constant.POINT)) {
            return path.substring(path.lastIndexOf(Constant.POINT) + 1, path.length());
        }
        return Constant.EMPTY;
    }


	@SuppressWarnings("static-access")
	public  String getValue(XSSFCell xssfRow) {
		if(xssfRow==null){
			return "";
		}
        if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
            return String.valueOf(xssfRow.getBooleanCellValue());
        } else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
        	    short format = xssfRow.getCellStyle().getDataFormat();  
        	    SimpleDateFormat sdf = null;  
        	    if(HSSFDateUtil.isCellDateFormatted(xssfRow)||format == 14 || format == 31 || format == 57 || format == 58){  
        	        //转化成日期  
        	        sdf = new SimpleDateFormat("yyyy-MM-dd");  
        	        double value = xssfRow.getNumericCellValue();  
        	        Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);  
        	        return sdf.format(date);  
        	    } 
        	    BigDecimal bd = new BigDecimal(xssfRow.getNumericCellValue()); 
                return bd.toPlainString();
        } else if(xssfRow.getCellType() == xssfRow.CELL_TYPE_BLANK ||xssfRow.getCellType() == xssfRow.CELL_TYPE_ERROR){
        	return "";
        } else if(xssfRow.getCellType() == xssfRow.CELL_TYPE_FORMULA){
        	Workbook wb = xssfRow.getSheet().getWorkbook();

			CreationHelper crateHelper = wb.getCreationHelper();

			FormulaEvaluator evaluator = crateHelper.createFormulaEvaluator();

			return getValue((XSSFCell)evaluator.evaluateInCell(xssfRow));
        } else {
            return String.valueOf(xssfRow.getStringCellValue()).trim();
        }
    }

    @SuppressWarnings("static-access")
	public  String getValue(HSSFCell hssfCell) {
    	if(hssfCell==null){
			return "";
		}
        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
        	short format = hssfCell.getCellStyle().getDataFormat();  
     	    SimpleDateFormat sdf = null;  
     	    if(HSSFDateUtil.isCellDateFormatted(hssfCell)||format == 14 || format == 31 || format == 57 || format == 58){
     	        //转化成日期  
     	        sdf = new SimpleDateFormat("yyyy-MM-dd");  
     	        double value = hssfCell.getNumericCellValue();  
     	        Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);  
     	        return sdf.format(date);  
     	    }  
     	    BigDecimal bd = new BigDecimal(hssfCell.getNumericCellValue()); 
     	   return bd.toPlainString();
        } else if(hssfCell.getCellType() == hssfCell.CELL_TYPE_BLANK ||hssfCell.getCellType() == hssfCell.CELL_TYPE_ERROR){
        	return "";
        } else if(hssfCell.getCellType() == hssfCell.CELL_TYPE_FORMULA){
        	Workbook wb = hssfCell.getSheet().getWorkbook();

			CreationHelper crateHelper = wb.getCreationHelper();

			FormulaEvaluator evaluator = crateHelper.createFormulaEvaluator();

			return getValue((HSSFCell)evaluator.evaluateInCell(hssfCell));
        } else {
            return String.valueOf(hssfCell.getStringCellValue());
        }
    }
    
    /**
     * 格式验证
     * @param data
     * @param type
     * @return
     */
	public static boolean regexData(String data,String type){
		if(StringUtils.isBlank(data)) return false;
		data = data.trim();
    	if("num".equals(type)){
    		//正整数
    		Pattern pattern = Pattern.compile("^[1-9]\\d*|0$");
    		Matcher matcher = pattern.matcher(data);
    		if(matcher.matches()) return true;
    	}else if("phone".equals(type)){
    		//手机号
    		Pattern pattern = Pattern.compile("^((14[7])|(17[0-9])|(13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
    		Matcher matcher = pattern.matcher(data);
    		//电话
    		Pattern pattern2 = Pattern.compile("^0(10|2[0-5789]|\\d{3})\\d{7,8}$");
    		Matcher matcher2 = pattern2.matcher(data);
    		if(matcher.matches() || matcher2.matches() ) return true;
    	}else if("date".equals(type)){
//    		return isValidDate(data);
    		if(data.contains(".")){
    			data = data.replace(".", "-");
    		}
    		//日期
    		Pattern pattern = Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))");
    		Matcher matcher = pattern.matcher(data);
    		if(matcher.matches()) return true;
    	}else if("sex".equals(type)){
    		if("0".equals(data)||"1".equals(data))	return true;
    	}else if("idCard".equals(type)){
    		//身份证号
    		Pattern pattern = Pattern.compile("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])");
    		Matcher matcher = pattern.matcher(data);
    		if(matcher.matches()) return true;
    	} else if ("double".equals(type)) {
    		try {
				Double.parseDouble(data);
				return true;
			} catch (Exception e) { }
    	}
		return false;
    }
	
//	public static boolean isValidDate(String str) {
//	      boolean convertSuccess=true;
//	      if(str.contains(".")){
//	    	  str = str.replace(".", "/");
//  		  }
//	      if(str.contains("-")){
//	    	  str = str.replace("-", "/");
//  		  }
//	      // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
//	       SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
//	       try {
//	      // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
//	          format.setLenient(false);
//	          format.parse(str);
//	       } catch (Exception e) {
//	           convertSuccess=false;
//	       } 
//	       return convertSuccess;
//	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public  void printMonitorExcel(HttpServletResponse aResponse, HttpServletRequest aRequest, String data,Object obj) {
		String resFile = aRequest.getSession().getServletContext().getRealPath("/") + "static" + File.separator + "config";
		ServiceManager.getInstance(resFile);
		JSONObject json_look = JSONObject.fromObject(data);
		try {
			int slipType = ExcelParameter.getSlipType(json_look);
			List fileNameList = new ArrayList();
			String itemsId = ExcelParameter.getString("itemsId", json_look);
			String temp = ExcelParameter.getString("outputType", json_look);
			CExcelConfig.OutputType outputType = null;
			if ((temp != null) && (!"".equals(temp.trim()))) {
				outputType = CExcelConfig.OutputType.valueOf(temp);
			}
			String excelNameId = ExcelParameter.getString("excelNameId", json_look);

			String fileAddNameId = ExcelParameter.getString("fileAddNameId", json_look);
			switch (slipType) {
				//Excel导出执行（单个文件单个Sheet）
				case 0: {
					Object slip = obj;
	
					List excelData = convertDataList(slip, excelNameId);
					ExcelCreater excelCreater = new ExcelCreater(excelNameId, outputType);
	
					fileNameList.addAll(excelCreater.outputExcel(excelData));
					break;
				}
				//Excel导出执行（单个个文件或多个Sheet）
				case 1: {
					Object slip = obj;
					List items = (List) Tools.getCodeByColumn(slip, itemsId);
	
					List excelData = convertDataList(items, excelNameId);
	
					ExcelCreater excelCreater = new ExcelCreater(excelNameId, outputType);
	
					fileNameList.addAll(excelCreater.outputExcel(excelData));
					break;
				}
				//Excel导出执行（多个文件多个Sheet）
				case 2: {
					// 根据sessionId取得SLIP，再根据itemsId取得导出LIST对象（分文件导出）
					Object slip = obj;
					List<Object> objectLists = (List<Object>)Tools.getCodeByColumn(slip, itemsId);
					// Excel名称
					for(Object objectList : objectLists){
						String fileAddName = null;
						if(fileAddNameId != null){
							Object object = ((List)objectList).get(0);
							Object addName = Tools.getCodeByColumn(object, fileAddNameId);
							if(addName != null){
								fileAddName = addName.toString();
							}
						}
						ExcelCreater excelCreater = new ExcelCreater(excelNameId,fileAddName,outputType);
						// Excel出力
						List<String> fileNames = excelCreater.outputExcel(objectList);
						fileNameList.addAll(fileNames);
					}
					break;
				}
			}
			if (fileNameList.size() == 1) {// 单个文件直接下载
				download(aRequest,aResponse,fileNameList.get(0).toString());
			}// 多文件为对应
		} catch (Exception e) {
			log.error("系统异常，查询出错" + e);
			e.printStackTrace();
		}
	}

	/**
	 * 根据需求对数据进一步加工
	 * 
	 * @param aData
	 * @param aDataType
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<List> convertDataList(Object aData, String aDataType) {
		List returnList = new ArrayList();
		List returnDataList = new ArrayList();
		if ("model".equals(aDataType)) {
			returnDataList.add(aData);
		} else {
			returnDataList.add(aData);
		}
		returnList.add(returnDataList);

		return returnList;
	}
	
	/**
	 * 文件下载
	 * 
	 * 下载后删除
	 * @param request
	 * @param response
	 * @param url
	 * @throws Exception
	 */
	public void download(HttpServletRequest request,HttpServletResponse response,String url) throws Exception {  
        try {
        	request.setCharacterEncoding("UTF-8");
        	BufferedInputStream bis = null;
        	BufferedOutputStream bos = null;
        	
        	long fileLength = new File(url).length();
        	String filename = url.substring(url.lastIndexOf("/") + 1);
        	response.addHeader("Content-Disposition", "inline; filename=\"" + new String(filename.getBytes("utf-8"),"ISO8859-1") + "\"");  
        	response.setHeader("Content-Length", String.valueOf(fileLength));
        	response.setContentType("application/octet-stream;charset=UTF-8");
        	
        	bis = new BufferedInputStream(new FileInputStream(url));
        	bos = new BufferedOutputStream(response.getOutputStream());
        	byte[] buff = new byte[2048];
        	int bytesRead;
        	while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
        		bos.write(buff, 0, bytesRead);
        	}
        	bis.close();
        	bos.close();
        	new File(url).delete();// 文件删除
		} catch (Exception e) {
			e.printStackTrace();
		}
    }  
}
