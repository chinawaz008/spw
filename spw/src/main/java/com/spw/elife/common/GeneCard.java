package com.spw.elife.common;
import java.awt.Graphics; 
import java.awt.Image; 
import java.awt.image.BufferedImage; 
import java.io.File; 
import java.io.FileOutputStream; 
import java.io.IOException;

import javax.imageio.ImageIO; 

import org.apache.log4j.Logger;

import com.sun.image.codec.jpeg.JPEGCodec; 
import com.sun.image.codec.jpeg.JPEGImageEncoder; 


public class GeneCard {
	
	private static Logger log = Logger.getLogger(GeneCard.class);
	
	public static boolean makeVipCard(String vipCardTmp, String qrCodePicUrl, String outPicUrl) {
		File formerFile = new File(vipCardTmp); 
		Image formerImage = null;
		try {
			formerImage = ImageIO.read(formerFile);
			
		} catch (IOException e) {
			log.error(e.getMessage());
			return false;
		}
		int width = formerImage.getWidth(null); 
        int height = formerImage.getHeight(null); 
        BufferedImage image = new BufferedImage(width, height, 
                BufferedImage.TYPE_INT_RGB); 
        Graphics g = image.createGraphics();
        g.drawImage(formerImage, 0, 0, width, height, null);
        File waterMarkFile = new File(qrCodePicUrl);
        Image waterMarkImage = null;
        try {
			waterMarkImage = ImageIO.read(waterMarkFile);
		} catch (IOException e) {
			log.error(e.getMessage());
			return false;
		}
        int widthWMI = waterMarkImage.getWidth(null); 
        int heightWMI = waterMarkImage.getHeight(null);
        g.drawImage(waterMarkImage, 335, 175, widthWMI, 
                heightWMI, null);
        g.dispose();
        FileOutputStream out = null;
        try {
			out = new FileOutputStream(outPicUrl);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out); 
	        encoder.encode(image); 
	        out.close(); 
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
		return true;
	}

	 /** 
     * 删除单个文件 
     * @param   sPath    被删除文件的文件名 
     * @return 单个文件删除成功返回true，否则返回false 
     */  
    public static boolean deleteFile(String sPath) {  
        boolean  flag = false;  
        File file = new File(sPath);  
        // 路径为文件且不为空则进行删除  
        if (file.isFile() && file.exists()) {  
            file.delete();  
            flag = true;  
        }  
        return flag;  
    }  
	
	public static void main(String[] args) {
		//makeVipCard ("E://yellow.jpg", "E://1372217.jpg", "E://new1.jpg");
		deleteFile("E:/70749349.jpg");
	}
} 
