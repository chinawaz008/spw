package com.spw.elife.common;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Random;

import javax.imageio.ImageIO;

import jp.sourceforge.qrcode.QRCodeDecoder;
import jp.sourceforge.qrcode.exception.DecodingFailedException;

import org.apache.log4j.Logger;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.spw.elife.util.Constant;
import com.swetake.util.Qrcode;

public class QrUtil {
	
	// LOGO宽度
    private static final int WIDTH = 40;
    // LOGO高度
    private static final int HEIGHT = 40;
	
    
	private static Logger log = Logger.getLogger(QrUtil.class);
	/**
	 * 生成二维码(QRCode)图片
	 * 
	 * @param content
	 *            存储内容
	 * @param imgPath
	 *            图片路径
	 * @param imgType
	 *            图片类型
	 * @param size
	 *            二维码尺寸
	 */
	public static boolean encoderQRCode(String content,String sNum,String imgPath, String imgType,String serialNum,
			int size,String vipCardTmp,String outPicUrl) {
		try {
			sNum = sNum.replaceAll("\\+", "@");
			BufferedImage bufImg = qRCodeCommon(content+sNum,imgType, size);
			imgPath += serialNum+".jpg";
			File imgFile = new File(imgPath);
			if(!imgFile.exists()){
				imgFile.mkdirs();
			}
			File outFile = new File(outPicUrl);
			if(!outFile.exists()){
				outFile.mkdirs();
			}
			//插入二维码中心图片
//			insertImage(bufImg,"E:/sed.jpg",67 + 12 * (size - 1), true);
			// 生成二维码图片
			ImageIO.write(bufImg, imgType, imgFile);
			return GeneCard.makeVipCard(vipCardTmp, imgPath, outPicUrl+serialNum+".jpg");
		} catch (Exception e) {
			log.error("encoderQRCode->生成二维码异常"+e.getMessage());
			return false;
		}
	}

	/**
	 * 生成二维码(QRCode)图片
	 * 
	 * @param content
	 *            存储内容
	 * @param output
	 *            输出流
	 * @param imgType
	 *            图片类型
	 * @param size
	 *            二维码尺寸
	 */
	public static boolean encoderQRCode(String content,String imgPath,String imgType, int size) {
		try {
			BufferedImage bufImg = qRCodeCommon(content, imgType, size);
			File output = new File(imgPath);
			if(!output.exists()){
				output.mkdirs();
			}
			// 生成二维码图片
			return ImageIO.write(bufImg, imgType, output);
		} catch (Exception e) {
			log.error("encoderQRCode->生成二维码异常"+Constant.getTrace(e));
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 生成二维码(QRCode)图片的公共方法
	 * 
	 * @param content
	 *            存储内容
	 * @param imgType
	 *            图片类型
	 * @param size
	 *            二维码尺寸
	 * @return
	 */
	private static BufferedImage qRCodeCommon(String content, String imgType, int size) {
		BufferedImage bufImg = null;
		try {
			Qrcode qrcodeHandler = new Qrcode();
			// 设置二维码排错率，可选L(7%)、M(15%)、Q(25%)、H(30%)，排错率越高可存储的信息越少，但对二维码清晰度的要求越小
			qrcodeHandler.setQrcodeErrorCorrect('M');
			qrcodeHandler.setQrcodeEncodeMode('B');
			// 设置设置二维码尺寸，取值范围1-40，值越大尺寸越大，可存储的信息越大
			qrcodeHandler.setQrcodeVersion(size);
			// 获得内容的字节数组，设置编码格式
			byte[] contentBytes = content.getBytes("utf-8");
			// 图片尺寸
			int imgSize = 67 + 11 * (size - 1);
			bufImg = new BufferedImage(imgSize, imgSize,
					BufferedImage.TYPE_INT_RGB);
			Graphics2D gs = bufImg.createGraphics();
			// 设置背景颜色
			gs.setBackground(Color.WHITE);
			gs.clearRect(0, 0, imgSize, imgSize);

			// 设定图像颜色> BLACK
			gs.setColor(Color.BLACK);
			// 设置偏移量，不设置可能导致解析出错
			int pixoff = 2;
			// 输出内容> 二维码
			if (contentBytes.length > 0 && contentBytes.length < 800) {
				boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);
				for (int i = 0; i < codeOut.length; i++) {
					for (int j = 0; j < codeOut.length; j++) {
						if (codeOut[j][i]) {
							gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
						}
					}
				}
			} else {
				throw new Exception("QRCode content bytes length = "+ contentBytes.length + " not in [0, 800].");
			}
			//Image img = ImageIO.read(new File("D:\\server\\apache-tomcat-7.0.55\\webapps\\chihuo-console\\static\\img\\y1.jpg"));//实例化一个Image对象。
	        // gs.drawImage(img, 44, 55, 49, 30, null);
			gs.dispose();
			bufImg.flush();
		} catch (Exception e) {
			log.error("qRCodeCommon->生成二维码异常"+e);
			e.printStackTrace();
		}
		return bufImg;
	}

	 @SuppressWarnings("unused")
	private static void insertImage(BufferedImage source, String imgPath,int imgSize,
	            boolean needCompress) throws Exception {
	        File file = new File(imgPath);
	        if (!file.exists()) {
	            System.err.println(""+imgPath+"   该文件不存在！");
	            return;
	        }
	        Image src = ImageIO.read(new File(imgPath));
	        int width = src.getWidth(null);
	        int height = src.getHeight(null);
	        if (needCompress) { // 压缩LOGO
	            if (width > WIDTH) {
	                width = WIDTH;
	            }
	            if (height > HEIGHT) {
	                height = HEIGHT;
	            }
	            Image image = src.getScaledInstance(width, height,
	                    Image.SCALE_SMOOTH);
	            BufferedImage tag = new BufferedImage(width, height,
	                    BufferedImage.TYPE_INT_RGB);
	            Graphics g = tag.getGraphics();
	            g.drawImage(image, 0, 0, null); // 绘制缩小后的图
	            g.dispose();
	            src = image;
	        }
	        // 插入LOGO
	        Graphics2D graph = source.createGraphics();
	        int x = (imgSize - width) / 2;
	        int y = (imgSize - height) / 2;
	        graph.drawImage(src, x, y, width, height, null);
	        Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
	        graph.setStroke(new BasicStroke(3f));
	        graph.draw(shape);
	        graph.dispose();
	    }

	
	/**
	 * 解析二维码（QRCode）
	 * 
	 * @param imgPath
	 *            图片路径
	 * @return
	 */
	public static String decoderQRCode(String imgPath) {
		// QRCode 二维码图片的文件
		File imageFile = new File(imgPath);
		BufferedImage bufImg = null;
		String content = null;
		try {
			bufImg = ImageIO.read(imageFile);
			QRCodeDecoder decoder = new QRCodeDecoder();
			content = new String(decoder.decode(new QRImage(bufImg)), "utf-8");
		} catch (IOException e) {
			log.error("decoderQRCode->解析二维码异常"+e);
			e.printStackTrace();
		} catch (DecodingFailedException dfe) {
			log.error("decoderQRCode->解析二维码异常"+dfe);
			dfe.printStackTrace();
		}
		return content;
	}

	/**
	 * 解析二维码（QRCode）
	 * 
	 * @param input
	 *            输入流
	 * @return
	 */
	public static String decoderQRCode(InputStream input) {
		BufferedImage bufImg = null;
		String content = null;
		try {
			bufImg = ImageIO.read(input);
			QRCodeDecoder decoder = new QRCodeDecoder();
			content = new String(decoder.decode(new QRImage(bufImg)), "utf-8");
		} catch (IOException e) {
			log.error("decoderQRCode-> 解析二维码异常"+e);
			e.printStackTrace();
		} catch (DecodingFailedException dfe) {
			log.error("qRCodeCommon-> 解析二维码异常"+dfe);
			dfe.printStackTrace();
		}
		return content;
	}
	/**
	 * 解析带图片的二维码（QRCode）
	 * 
	 * @param path    文件路径
	 * @return
	 */
	public static String decode(String path) throws Exception {
		 	File file = new File(path);
	        BufferedImage image;
	        image = ImageIO.read(file);
	        if (image == null) {
	            return null;
	        }
	        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(
	                image);
	        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
	        Result result;
	        Hashtable<DecodeHintType, String> hints = new Hashtable<DecodeHintType, String>();
	        hints.put(DecodeHintType.CHARACTER_SET,"utf-8");
	        result = new MultiFormatReader().decode(bitmap, hints);
	        String resultStr = result.getText();
	        return resultStr;
	    }
	
	public static void main(String[] args) throws Exception {
//		String snum = "MonfS3+eebEUqXZn+5OZAw==";
//		String sb = snum.replaceAll("\\+", "@");
//		System.out.println(sb);
//		System.out.println(sb.replaceAll("@", "\\+"));
//		System.out.println(decode("E:/46478866.jpg"));
//		encoderQRCode("http://192.168.1.239:8080/MSLIFE/cardOrder/toOrder?num=","MonfS3+eebEUqXZn+5OZAw==","F:/二维码/","jpg","wc",5,"E://yellow.jpg","F:/卡单二维码/new1.jpg");
		encoderQRCode("weixin://wxpay/bizpayurl?pr=FbL9cYb", "F:/二维码/111.jpg", "jpg", 5);
	}
}
