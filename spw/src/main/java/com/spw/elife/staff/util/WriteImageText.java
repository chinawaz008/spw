package com.spw.elife.staff.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 图片写入文字
 * @author Administrator
 *
 */
public class WriteImageText {
    
	public static void main(String[] args) throws IOException {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    	Map<String,Object> mp = new HashMap<String, Object>();//年
    	mp.put("content", "2017");
    	mp.put("position_x", 140);
    	mp.put("position_y", 150);
    	list.add(mp);
    	Map<String,Object> mp2 = new HashMap<String, Object>();//月
    	mp2.put("content", "05");
    	mp2.put("position_x", 300);
    	mp2.put("position_y", 150);
    	list.add(mp2);
    	Map<String,Object> mp3 = new HashMap<String, Object>();//日
    	mp3.put("content", "10");
    	mp3.put("position_x", 420);
    	mp3.put("position_y", 150);
    	list.add(mp3);
    	Map<String,Object> mp4 = new HashMap<String, Object>();//渠道
    	mp4.put("content", "个代");
    	mp4.put("position_x", 350);
    	mp4.put("position_y", 220);
    	list.add(mp4);
    	Map<String,Object> mp5 = new HashMap<String, Object>();//分公司
    	mp5.put("content", "安徽省分公司");
    	mp5.put("position_x", 740);
    	mp5.put("position_y", 220);
    	list.add(mp5);
    	Map<String,Object> mp6 = new HashMap<String, Object>();//督训区
    	mp6.put("content", "堕落阿文");
    	mp6.put("position_x", 1120);
    	mp6.put("position_y", 220);
    	list.add(mp6);
    	Map<String,Object> mp7 = new HashMap<String, Object>();//职级
    	mp7.put("content", "分部经理");
    	mp7.put("position_x", 350);
    	mp7.put("position_y", 305);
    	list.add(mp7);
    	Map<String,Object> mp8 = new HashMap<String, Object>();//人员名称
    	mp8.put("content", "强成文");
    	mp8.put("position_x", 710);
    	mp8.put("position_y", 305);
    	list.add(mp8);
    	Map<String,Object> mp9 = new HashMap<String, Object>();//身份照号
    	mp9.put("content", "340222199504273538");
    	mp9.put("position_x", 1120);
    	mp9.put("position_y", 305);
    	list.add(mp9);
    	Map<String,Object> mp10 = new HashMap<String, Object>();//合计金额大写
    	mp10.put("content", NumberToCN.number2CNMontrayUnit(Double.parseDouble("1000")));
    	mp10.put("position_x", 520);
    	mp10.put("position_y", 570);
    	list.add(mp10);
    	Map<String,Object> mp11 = new HashMap<String, Object>();//合计金额
    	mp11.put("content", "1000");
    	mp11.put("position_x", 1240);
    	mp11.put("position_y", 570);
    	list.add(mp11);
    	String img_src = "D:/imgs_msgy/debit/";
    	boolean flag = WriteImageText.pressText(img_src+"20170510.jpg", img_src+"收据.png", list);
//    	resizeImage(img_src+"20170510.jpg");
    	
    	
//    	String inputFoler = "E:\\新建文件夹\\7.jpg" ;   
//        /*这儿填写你存放要缩小图片的文件夹全地址*/  
//       String outputFolder = "E:\\新建文件夹\\07.jpg";    
       /*这儿填写你转化后的图片存放的文件夹*/  
       
        
        
        writeHighQuality(zoomImage(img_src+"20170510.jpg"), img_src+"20170510.jpg");
	}
	
	
	 public static BufferedImage zoomImage(String src) {  
         
	        BufferedImage result = null;  
	  
	        try {  
	            File srcfile = new File(src);  
	            if (!srcfile.exists()) {  
	                System.out.println("文件不存在");  
	                  
	            }  
	            BufferedImage im = ImageIO.read(srcfile);  
	  
	            /* 原始图像的宽度和高度 */  
	            int width = im.getWidth();  
	            int height = im.getHeight();  
	              
	            //压缩计算  
	            float resizeTimes = 0.368f;  /*这个参数是要转化成的倍数,如果是1就是转化成1倍*/  
	              
	            /* 调整后的图片的宽度和高度 */  
	            int toWidth = (int) (width * resizeTimes);  
	            int toHeight = (int) (height * resizeTimes);  
	  
	            /* 新生成结果图片 */  
	            result = new BufferedImage(toWidth, toHeight,  
	                    BufferedImage.TYPE_INT_RGB);  
	  
	            result.getGraphics().drawImage(  
	                    im.getScaledInstance(toWidth, toHeight,  
	                            java.awt.Image.SCALE_SMOOTH), 0, 0, null);  
	              
	  
	        } catch (Exception e) {  
	            System.out.println("创建缩略图发生异常" + e.getMessage());  
	        }  
	          
	        return result;  
	  
	    } 
	
	/**
	 * 将素材缩小尺寸 打印小图片
	 * @param im
	 * @param fileFullPath
	 * @return
	 */
	public static boolean writeHighQuality(BufferedImage im, String fileFullPath) {  
        try {  
            /*输出到文件流*/  
            FileOutputStream newimage = new FileOutputStream(fileFullPath);  
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(newimage);  
            JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(im);  
            /* 压缩质量 */  
            jep.setQuality(0.9f, true);  
            encoder.encode(im, jep);  
           /*近JPEG编码*/  
            newimage.close();  
            return true;  
        } catch (Exception e) {  
            return false;  
        }  
    }
	
	
	
	
    /**
	 * 文字水印
	 * @param srcImg 保存图片路径
	 * @param targetImg 目标图片
	 * @param list 添加文字集合
	 */
	public static boolean pressText(String srcImg,String targetImg,List<Map<String,Object>> list) {
		try {
			String fontName = "宋体";//字体
			int fontStyle = 0;//字体样式
			Color color = Color.BLACK;//字体颜色
			int fontSize = 30;//字体大小
			float alpha = 1.0f;//透明度
			
			File img = new File(targetImg);
			Image src = ImageIO.read(img);
			int width = src.getWidth(null);
			int height = src.getHeight(null);
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = image.createGraphics();
			g.drawImage(src, 0, 0, width, height, null);
			g.setColor(color);
			g.setFont(new Font(fontName, fontStyle, fontSize));
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
			for(Map<String,Object> mp : list){
				String content = (String)mp.get("content");
				if (StringUtils.isBlank(content)) {
					content = "";
				}
				g.drawString(content, (int)mp.get("position_x"), (int)mp.get("position_y"));
			}
			g.dispose();
			
			FileOutputStream outImgStream = new FileOutputStream(srcImg);  
            ImageIO.write(image, "png", outImgStream);
            System.out.println("添加水印完成");  
            outImgStream.flush();  
            outImgStream.close();  
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
