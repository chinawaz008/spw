package com.spw.elife.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * @author Administrator
 * 
 */
public class TestProperties{
	
	public static void main(String args[]) {
		TestProperties tx = new TestProperties();
		String uaddress=tx.uploadAddress();
		String imageShow=tx.imageShow();
		System.out.println(tx.resultvalue("lifeurl"));
		
		String kaddress=tx.kindeditorAddress();
		String kshow=tx.kindeditorShow();
		//System.out.println(kaddress);
	}
	
	public String uploadAddress(){
		InputStream is = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("configuration.properties"); // 加载线程文件成为流
		Properties prop = new Properties();
		String value="";
		try {
			prop.load(is);// 直接转换为对象
			value = prop.getProperty("upload.address");
		} catch (IOException ex) {

		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {

				}
			}
		}
		return value;
	}
	
	public String imageShow(){
		InputStream is = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("configuration.properties"); // 加载线程文件成为流
		Properties prop = new Properties();
		String value="";
		try {
			prop.load(is);// 直接转换为对象
			value = prop.getProperty("image.show");
		} catch (IOException ex) {

		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {

				}
			}
		}
		return value;
	}
	
	public String kindeditorAddress(){
		InputStream is = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("configuration.properties"); // 加载线程文件成为流
		Properties prop = new Properties();
		String value="";
		try {
			prop.load(is);// 直接转换为对象
			value = prop.getProperty("kindeditor.address");
		} catch (IOException ex) {

		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {

				}
			}
		}
		return value;
	}
	
	public String kindeditorShow(){
		InputStream is = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("configuration.properties"); // 加载线程文件成为流
		Properties prop = new Properties();
		String value="";
		try {
			prop.load(is);// 直接转换为对象
			value = prop.getProperty("kindeditor.show");
		} catch (IOException ex) {

		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {

				}
			}
		}
		return value;
	}
	
	public String resultvalue(String key){
		InputStream is = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("configuration.properties"); // 加载线程文件成为流
		Properties prop = new Properties();
		String value="";
		try {
			prop.load(is);// 直接转换为对象
			value = prop.getProperty(key);
		} catch (IOException ex) {

		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {

				}
			}
		}
		return value;
	}
}
