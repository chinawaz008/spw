/**
 * 
 */
package com.spw.elife.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * @author minsheng
 *
 */
public class CreateTxt {
	private static final Logger log = Logger.getLogger(CreateTxt.class);
	public static BufferedReader bufread;
    private static  File filename =null;
    /**
     * 创建文本文件.
     * @throws IOException 
     * 
     */
    public static File creatTxtFile(String path) throws IOException{
    	SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = sf.format(new Date());
		File file =new File(path);
		if (!file.exists()) {
			file.mkdirs();
        }
		String name= "淘融e店"+date+"卡单分配列表.txt";
		path = path +""+name;
    	filename = new File(path);
        if (!filename.exists()) {
            filename.createNewFile();
            log.info(filename + "已创建！");
        }
        return filename;
    }
    
    
	public static void writeTxtFile(String content,File fileName) throws IOException{
		RandomAccessFile mm=null;  
		  FileOutputStream o=null;  
		  try {  
		   o = new FileOutputStream(fileName);  
		      o.write(content.getBytes("UTF-8"));  
		      o.close();  
		  } catch (Exception e) {  
		   e.printStackTrace();  
		  }finally{  
		   if(mm!=null){  
		    mm.close();  
		   }  
		 }  
    }
}
