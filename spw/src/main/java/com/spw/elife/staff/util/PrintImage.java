package com.spw.elife.staff.util;

import java.io.FileInputStream;
import java.io.IOException;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Chromaticity;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.OrientationRequested;
import javax.print.attribute.standard.PrintQuality;

/**
 * 调用打印机 打印图片
 * @author Administrator
 *
 */
public class PrintImage {
	
	
	/**
	 * 打印图片
	 * @param fileName 图片路径
	 */
	public static boolean drawImage(String fileName) {  
        try {  
            DocFlavor dof = null;  
            // 根据用户选择不同的图片格式获得不同的打印设备  
            if (fileName.endsWith(".gif")) {  
                // gif  
                dof = DocFlavor.INPUT_STREAM.GIF;  
            } else if (fileName.endsWith(".jpg")) {  
                // jpg  
                dof = DocFlavor.INPUT_STREAM.JPEG;  
            } else if (fileName.endsWith(".png")) {  
                // png  
                dof = DocFlavor.INPUT_STREAM.PNG;  
            }  
            // 字节流获取图片信息  
            FileInputStream fin = new FileInputStream(fileName);  
            // 获得打印属性  
            PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();  
            // 每一次默认打印一页  
            pras.add(new Copies(1));  
            // 获得打印设备 ，字节流方式，图片格式  
            PrintService pss[] = PrintServiceLookup.lookupPrintServices(dof,  
                    pras);  
            // 如果没有获取打印机  
            if (pss.length == 0) {
                // 终止程序  
                return false;  
            }  
            // 获取第一个打印机  
            PrintService ps = pss[0];  
            System.out.println("Printing image..........." + ps);  
            // 获得打印工作  
            DocPrintJob job = ps.createPrintJob();  
  
            // 设置打印内容  
            Doc doc = new SimpleDoc(fin, dof, null);  
            // 出现设置对话框  
            PrintService service = ServiceUI.printDialog(null, 50, 50, pss, ps,  
                    dof, pras);  
            if (service != null) {  
                // 开始打印  
                job = service.createPrintJob();  
                job.print(doc, pras);  
                fin.close();  
                return true;
            }
        } catch (IOException ie) {  
            // 捕获io异常  
            ie.printStackTrace();  
        } catch (PrintException pe) {  
            // 捕获打印异常  
            pe.printStackTrace();  
        }
        return false;
    }
	
	public void drawImage2(String fileName) {
		try {
			DocFlavor dof = null;
			// 根据用户选择不同的图片格式获得不同的打印设备
			if (fileName.endsWith(".gif")) {
				// gif
				dof = DocFlavor.INPUT_STREAM.GIF;
			} else if (fileName.endsWith(".jpg")) {
				// jpg
				dof = DocFlavor.INPUT_STREAM.JPEG;
			} else if (fileName.endsWith(".png")) {
				// png
				dof = DocFlavor.INPUT_STREAM.PNG;
			}
			// 字节流获取图片信息
			FileInputStream fin = new FileInputStream(fileName);
			// 获得打印属性
			PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
			// 每一次默认打印一页
			pras.add(new Copies(1));
			// 获得打印设备 ，字节流方式，图片格式
			PrintService pss[] = PrintServiceLookup.lookupPrintServices(dof,
					pras);
			// 如果没有获取打印机
			if (pss.length == 0) {
				// 终止程序
				return;
			}
			// 获取第一个打印机
			PrintService ps = pss[0];
			System.out.println("Printing image..........." + ps);
			// 获得打印工作
			DocPrintJob job = ps.createPrintJob();

			// 设置打印内容
			Doc doc = new SimpleDoc(fin, dof, null);
			// 出现设置对话框
			PrintService service = ServiceUI.printDialog(null, 50, 50, pss, ps,
					dof, pras);
			if (service != null) {
				// 开始打印
				job.print(doc, pras);
				fin.close();
			}
		} catch (IOException ie) {
			// 捕获io异常
			ie.printStackTrace();
		} catch (PrintException pe) {
			// 捕获打印异常
			pe.printStackTrace();
		}
	}
	
	/** 
     * 主函数 
     *  
     * @param args 
     *  
     */  
    public static void main(String args[]) {
    	printImg("D:/imgs_msgy/debit/test.jpg");  
    }
    
    
    
    public static boolean printImg(String fileName) {
        try {
            //DocFlavor dof = DocFlavor.INPUT_STREAM.AUTOSENSE;
        	System.out.println(".......开始打印.............");
            DocFlavor dof = null;
            if (fileName.endsWith(".gif")) {
                dof = DocFlavor.INPUT_STREAM.GIF;
            } else if (fileName.endsWith(".jpg")) {
                dof = DocFlavor.INPUT_STREAM.JPEG;
            } else if (fileName.endsWith(".png")) {
                dof = DocFlavor.INPUT_STREAM.PNG;
            }
            PrintService pservice  = PrintServiceLookup.lookupDefaultPrintService();
            
            //打印属性
            PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
            
            pras.add(OrientationRequested.PORTRAIT);
            pras.add(new Copies(1));
            pras.add(PrintQuality.HIGH);
            pras.add(Chromaticity.MONOCHROME);
            
            //文档属性
            DocAttributeSet das = new HashDocAttributeSet();
            FileInputStream fis = new FileInputStream(fileName);
            
            //获取图像参数
            /*BufferedImage img = ImageIO.read(fis);                            //  (3)              
            int width = (int) (img.getWidth() * 2.54f /72);
            int height = (int) (img.getHeight() * 2.54f /72);
            System.out.println("尺寸,宽："+width+"高"+height);*/
            
            // 设置打印纸张的大小（将像素转化为mm）
//            das.add(new MediaPrintableArea(0, 0, 415, 201, MediaPrintableArea.MM));
            das.add(new MediaPrintableArea(10, 10, 200, 100, MediaPrintableArea.MM));
           
            Doc doc = new SimpleDoc(fis, dof, das);

            DocPrintJob job = pservice.createPrintJob();
            job.print(doc, pras);

            fis.close();
            System.out.println(".......打印完成.............");
            return true;
        } catch (IOException ie) {
            ie.printStackTrace();
        } catch (PrintException pe) {
            pe.printStackTrace();
        }
        return false;
    }
}
