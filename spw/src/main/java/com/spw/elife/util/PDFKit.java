package com.spw.elife.util;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.spw.elife.staff.util.WriteImageText;

public class PDFKit {
	
	/**
	 * 画图
	 * @param content
	 * @param img
	 */
	public static String drawImg(String content){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> mp = new HashMap<String, Object>();//年
		mp.put("content", content);
		mp.put("position_x", 50);
		mp.put("position_y", 50);
		list.add(mp);
		String path = Utils.getMaterialByPname("debit.address");
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String temp = df.format(new Date()) + "_"+ new Random().nextInt(1000);//待完成，如果文件名称一样，一天就创建一个日期
		String img_src = path+"/contract/"+temp+".jpg";//临时文件
		WriteImageText.pressText(img_src, path+"contract/date.png", list);
//		FileUtil.delFile(img_src);//删除临时文件
		return img_src;
	}
	
	public static void compositePDF(String res,String dst,String date,String name,int type){
		try {
			// 循环对每页插入水印
//			for (int i = 1; i < total; i++) {
//				 PdfContentByte under = stamper.getUnderContent(i);  
//			     under.addImage(img);  
//			}
			// 待加水印的文件
			PdfReader reader = new PdfReader(res);
			// 加完水印的文件
			PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dst));
			int total = reader.getNumberOfPages() + 1;
			switch (type) {
			case 0://代理合同书
				Image image = Image.getInstance(name);// 插入水印
			    image.setAbsolutePosition(190, 652);//坐标  
//	         	image.setRotation(-20);//旋转 弧度  
//	            image.setRotationDegrees(90);//旋转 角度  
		        image.scaleAbsolute(150,100);//自定义大小  
	            image.scalePercent(10);//依照比例缩放  
	            PdfContentByte under_name = stamper.getUnderContent(1);  
	            under_name.addImage(image);
	            
	            image = Image.getInstance(name);// 插入水印
			    image.setAbsolutePosition(390, 80);//坐标  
		        image.scaleAbsolute(80,80);//自定义大小  
	            image.scalePercent(10);//依照比例缩放 
				PdfContentByte under_last = stamper.getUnderContent(total-1);  
				under_last.addImage(image);
				
				image = Image.getInstance(date);// 插入水印
			    image.setAbsolutePosition(110, 55);//坐标  
		        image.scaleAbsolute(150,100);//自定义大小  
	            image.scalePercent(50);//依照比例缩放 
				PdfContentByte under_first = stamper.getUnderContent(total-1);  
				under_first.addImage(image);
				
				image = Image.getInstance(date);// 插入水印
			    image.setAbsolutePosition(370, 55);//坐标  
		        image.scaleAbsolute(150,100);//自定义大小  
	            image.scalePercent(50);//依照比例缩放 
				under_first = stamper.getUnderContent(total-1);  
				under_first.addImage(image);
				break;
			case 1://代理人行为承诺书				
				image = Image.getInstance(name);// 插入水印
			    image.setAbsolutePosition(170, 15);//坐标  
		        image.scaleAbsolute(150,100);//自定义大小  
	            image.scalePercent(10);//依照比例缩放 
				under_last = stamper.getUnderContent(total-1);  
				under_last.addImage(image);
				
				image = Image.getInstance(date);// 插入水印
			    image.setAbsolutePosition(400, 25);//坐标  
		        image.scaleAbsolute(150,100);//自定义大小  
	            image.scalePercent(50);//依照比例缩放 
				under_first = stamper.getUnderContent(total-1);  
				under_first.addImage(image);				
				break;
			case 2://代理人行为承诺书				
				image = Image.getInstance(name);// 插入水印
			    image.setAbsolutePosition(120, 150);//坐标  
		        image.scaleAbsolute(150,100);//自定义大小  
	            image.scalePercent(10);//依照比例缩放 
				under_last = stamper.getUnderContent(total-1);  
				under_last.addImage(image);
				
				image = Image.getInstance(date);// 插入水印
			    image.setAbsolutePosition(350, 155);//坐标  
		        image.scaleAbsolute(150,100);//自定义大小  
	            image.scalePercent(50);//依照比例缩放 
				under_first = stamper.getUnderContent(total-1);  
				under_first.addImage(image);				
				break;
			case 3://民盛代理人行为规范				
				image = Image.getInstance(name);// 插入水印
			    image.setAbsolutePosition(115, 590);//坐标  
		        image.scaleAbsolute(150,100);//自定义大小  
	            image.scalePercent(10);//依照比例缩放 
				under_last = stamper.getUnderContent(total-1);  
				under_last.addImage(image);
				
				image = Image.getInstance(date);// 插入水印
			    image.setAbsolutePosition(360, 600);//坐标  
		        image.scaleAbsolute(150,100);//自定义大小  
	            image.scalePercent(50);//依照比例缩放 
				under_first = stamper.getUnderContent(total-1);  
				under_first.addImage(image);				
				break;
			case 4://代理人自动扣款授权书				
				image = Image.getInstance(name);// 插入水印
				image.setAbsolutePosition(130, 230);//坐标  
				image.scaleAbsolute(150,100);//自定义大小  
				image.scalePercent(10);//依照比例缩放 
				under_last = stamper.getUnderContent(total-1);  
				under_last.addImage(image);
				
				image = Image.getInstance(date);// 插入水印
				image.setAbsolutePosition(345, 250);//坐标  
				image.scaleAbsolute(150,100);//自定义大小  
				image.scalePercent(50);//依照比例缩放 
				under_first = stamper.getUnderContent(total-1);  
				under_first.addImage(image);				
				break;
			default:
				break;
			}
			stamper.close();
			reader.close();
		} catch (Exception e) {
			System.out.println("pdf签名失败！");
			e.printStackTrace();
		} 
	} 
	 
	public static void main(String[] args) {
		String date = drawImg("2017年05月12日");
		compositePDF("D:/imgs_msgy/debit/contract/代理合同书.pdf", 
				"D:/imgs_msgy/debit/contract/IKAnalyzer_water.pdf",date,"D:/imgs_msgy/debit/contract/sign.jpg",0);
		
	}
}
