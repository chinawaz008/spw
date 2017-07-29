package com.spw.elife.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;



/**
 * 中华车险配置解析excel
 * @author Administrator
 *
 */
public class GetSQL {
    
	private Logger log = Logger.getLogger(GetSQL.class);
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
    /**
     * Read the Excel 2003-2007
     * @param path the path of the Excel
     * @return
     * @throws IOException
     */
    public List<Staff> readXls(String path) throws IOException {
        InputStream is = new FileInputStream(path);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        Staff staff = null;
        List<Staff> list = new ArrayList<Staff>();
        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
        	XSSFSheet hssfSheet = xssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
            	XSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow != null && StringUtils.isNotBlank(getValue(hssfRow.getCell(0)))) {
                    staff = new Staff();
                    XSSFCell name = hssfRow.getCell(0);
                    XSSFCell idCard = hssfRow.getCell(1);
                    XSSFCell dxq = hssfRow.getCell(2);
                    staff.setName(getValue(name));
                    staff.setIdCard(getValue(idCard));
                    staff.setDxq(getValue(dxq));
                    list.add(staff);
                }
            }
        }
        return list;
    }
    
    class Staff {
    	private String name;
    	private String idCard;
    	private String fgs;
    	private String dxq;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getIdCard() {
			return idCard;
		}
		public void setIdCard(String idCard) {
			this.idCard = idCard;
		}
		public String getFgs() {
			return fgs;
		}
		public void setFgs(String fgs) {
			this.fgs = fgs;
		}
		public String getDxq() {
			return dxq;
		}
		public void setDxq(String dxq) {
			this.dxq = dxq;
		}
    	
    }

    public Properties getProp(){
    	  InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("configuration.properties");
    	  Properties p = new Properties();
    	  try {
    	  p.load(inputStream);
    	  } catch (IOException e1) {
    		  e1.printStackTrace();
    	  }
  		return p;
    }
    
    
  	public GetSQL()  { 
  	 	try {
			List<Staff> list = readXls("D:/离职人员信息.xlsx");
			String DB_URL = getProp().getProperty("jdbc.url");
			String USER = getProp().getProperty("jdbc.username");
			String PASS = getProp().getProperty("jdbc.password");
			
			Connection conn = null;
			Statement stmt = null;
			  Class.forName("com.mysql.jdbc.Driver");
			  conn =   (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
  			  conn.setAutoCommit(false);   
              stmt =  (Statement) conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);  
          	List<String> lists = new ArrayList<>();
              for(int i = 0; i < list.size();i++){   
            	String id = null;
            	String name = null;
            	String status =null;
            	String personType = null;
            	String phone_num = null;
            
         		stmt =  (Statement) conn.createStatement();
				String sql = "SELECT  s.status from ms_staff s LEFT JOIN organization o on s.countyFranchisees_id = o.id ";
					   sql  += "LEFT JOIN organization f on s.store_id = f.id  where s.name = '"+list.get(i).getName()+"'";
					   sql 	+= " or (s.name ='"+list.get(i).getName()+"' and o.name ='"+list.get(i).getIdCard()+"' )";
					   sql 	+= " or (s.name ='"+list.get(i).getName()+"' and f.name ='"+list.get(i).getDxq()+"' )";
//				String sql = "SELECT  f.name from ms_staff_apply s LEFT JOIN ms_staff f  on f.id = s.refer_id where s.name = '"+list.get(i).getName()+"' "
//						+ "and s.id_card ='"+list.get(i).getIdCard()+"'";
				ResultSet rs = stmt.executeQuery(sql);
//				rs.last(); //移到最后一行   
//				int rowCount = rs.getRow(); //得到当前行号，也就是记录数   
//				rs.beforeFirst(); //如果还要用结果集，就把指针再移到初始化的位置
//				if(rowCount==1 && rs.next()){
				if(rs.next()){
					id = rs.getString(1);
//					System.out.println("订单号:"+name+"状态："+status+"人员类型："+personType+"手机号："+phone_num);
				} 
//				System.out.println("--------------------");
//				if(id!=null || id.contains(list.get(i).getDxq())){
				if("0".equals(id)){
					log.info(list.get(i).getName()+"-xxxxxxxxx-"+id);
				}else{
//					log.info(list.get(i).getName()+"-xxxxxxxxx-"+id);
				}
//				if(id!=null){
//					String sql2 = "update ms_staff set work_num = '"+workNum+"'  where id = '"+id+"'";
//					System.out.println(sql2+";");
//					
//				}else{
//					log.info(list.get(i).getName()+"-xxxxxxxxx-"+list.get(i).getIdCard());
//				}
//                stmt.execute(sql2);   
              }  
              conn.commit();   
			  stmt.close();
			  conn.close();
  			} catch (Exception e) {
  				e.printStackTrace();
  			}  
  	}
  	
    public static void main(String[] args){
    	new GetSQL();
	}
}