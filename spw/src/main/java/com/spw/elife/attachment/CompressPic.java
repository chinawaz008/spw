package com.spw.elife.attachment;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.coobird.thumbnailator.Thumbnails;

import com.spw.elife.attachment.web.AttachmentController;
import com.spw.elife.common.AbstractComponent;
/**
 * 压缩图片
 * @author Administrator
 */
public class CompressPic  extends AbstractComponent{

	
	  private final Logger logger = LoggerFactory.getLogger(AttachmentController.class);
	
	/***
     * 缩减图片
     * @param thumb 图片相对路径
     * @return
     * @throws IOException
     */
    public  String getSmallThumb(String thumb,int width){
    	String filePath = getResourceFilepath() + File.separatorChar + thumb; // 图片绝对路径
    	String imgName = thumb.substring(thumb.lastIndexOf("/")); // 图片名称
    	String newImgName = imgName.substring(0, imgName.lastIndexOf(".")).concat("_sm_"+width).concat(imgName.substring(imgName.lastIndexOf("."))); // 缩减的图片名称
    	String newThumbPath = thumb.substring(0, thumb.lastIndexOf("/")).concat(newImgName); // 缩减图片的相对地址
    	String destFilePath = getResourceFilepath() + newThumbPath; // 缩减图片的绝对地址
    	try {
			Thumbnails.of(filePath).size(width, getImgHeight(width,filePath)).toFile(destFilePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("getSmallThumb-> 缩减图片异常"+e);
			e.printStackTrace();
		} //　缩减图片
    	return newThumbPath;
    }
    /***
     * 缩减图片
     * @param images 图片s相对路径
     * @return
     * @throws IOException
     */
    public  String getSmallThumbs(String images,int width){
    	if(images==null){
    		return null;
    	}
    	String thumbs[] = images.split(",");
    	StringBuffer newThumbsPath = new StringBuffer();
    	for(String thumb : thumbs){
	    	String filePath = getResourceFilepath() + File.separatorChar + thumb; // 图片绝对路径
	    	String imgName = thumb.substring(thumb.lastIndexOf("/")); // 图片名称
	    	String newImgName = imgName.substring(0, imgName.lastIndexOf(".")).concat("_sm_"+width).concat(imgName.substring(imgName.lastIndexOf("."))); // 缩减的图片名称
	    	String newThumbPath = thumb.substring(0, thumb.lastIndexOf("/")).concat(newImgName); // 缩减图片的相对地址
	    	String destFilePath = getResourceFilepath() + newThumbPath; // 缩减图片的绝对地址
	    	try {
				Thumbnails.of(filePath).size(width, getImgHeight(width,filePath)).toFile(destFilePath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error("getSmallThumbs -> 缩减图片异常"+e);
				e.printStackTrace();
			} //　缩减图片
	    	newThumbsPath.append(newThumbPath).append(",");
    	}
    	return newThumbsPath.toString();
    }
    private int getImgHeight(int width,String filePath)throws IOException{
        BufferedImage sourceImg =ImageIO.read(new FileInputStream(new File(filePath)));
        double xs=(double)width/sourceImg.getWidth();
        return (int) Math.round(xs*sourceImg.getHeight());
    }
}
