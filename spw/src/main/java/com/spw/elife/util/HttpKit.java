package com.spw.elife.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.lang3.StringUtils;


/**
 * @author user
 * 
 */
public class HttpKit {

	private static final String DEFAULT_CHARSET = "UTF-8";

	 /**
     * 发送Get请求
     * @param url
     * @return
     */
	public static String get(String url) {
		StringBuffer bufferRes = null;
		try {
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象  
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL urlGet = new URL(url);
			HttpsURLConnection http = (HttpsURLConnection) urlGet
					.openConnection();
			// 连接超时
			http.setConnectTimeout(25000);
			// 读取超时 --服务器响应比较慢，增大时间
			http.setReadTimeout(25000);
			http.setRequestMethod("GET");
			http.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			http.setSSLSocketFactory(ssf);
			http.setDoOutput(true);
			http.setDoInput(true);
			http.connect();

			InputStream in = http.getInputStream();
			BufferedReader read = new BufferedReader(new InputStreamReader(in,
					DEFAULT_CHARSET));
			String valueString = null;
			bufferRes = new StringBuffer();
			while ((valueString = read.readLine()) != null) {
				bufferRes.append(valueString);
			}
			in.close();
			if (http != null) {
				// 关闭连接
				http.disconnect();
			}
			return bufferRes.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 发送Get请求
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	public static String get(String url, Map<String, String> params) {
		return get(initParams(url, params));
	}

	/**
	 * 发送Post请求
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	public static String post(String url, String params) {
		StringBuffer bufferRes = null;
		try {
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象  
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL urlGet = new URL(url);
			HttpsURLConnection http = (HttpsURLConnection) urlGet
					.openConnection();
			// 连接超时
			http.setConnectTimeout(25000);
			// 读取超时 --服务器响应比较慢，增大时间
			http.setReadTimeout(25000);
			http.setRequestMethod("POST");
			http.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			http.setSSLSocketFactory(ssf);
			http.setDoOutput(true);
			http.setDoInput(true);
			http.connect();

			OutputStream out = http.getOutputStream();
			out.write(params.getBytes("UTF-8"));
			out.flush();
			out.close();

			InputStream in = http.getInputStream();
			BufferedReader read = new BufferedReader(new InputStreamReader(in,
					DEFAULT_CHARSET));
			String valueString = null;
			bufferRes = new StringBuffer();
			while ((valueString = read.readLine()) != null) {
				bufferRes.append(valueString);
			}
			in.close();
			if (http != null) {
				 // 关闭连接
				http.disconnect();
			}
			return bufferRes.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	private static String initParams(String url, Map<String, String> params) {
		if (null == params || params.isEmpty()) {
			return url;
		}
		StringBuilder sb = new StringBuilder(url);
		if (url.indexOf("?") == -1) {
			sb.append("?");
		} else {
			sb.append("&");
		}
		boolean first = true;
		for (Entry<String, String> entry : params.entrySet()) {
			if (first) {
				first = false;
			} else {
				sb.append("&");
			}
			String key = entry.getKey();
			String value = entry.getValue();
			sb.append(key).append("=");
			if (StringUtils.isNotEmpty(value)) {
				try {
					sb.append(URLEncoder.encode(value, DEFAULT_CHARSET));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}
	
    public static String readContentFromGet(String url) throws IOException {
        // 拼凑get请求的URL字串，使用URLEncoder.encode对特殊和不可见字符进行编码
        URL getUrl = new URL(url);
        // 根据拼凑的URL，打开连接，URL.openConnection()函数会根据 URL的类型，返回不同的URLConnection子类的对象，在这里我们的URL是一个http，因此它实际上返回的是HttpURLConnection
        HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
        // 建立与服务器的连接，并未发送数据
        connection.connect();
        // 发送数据到服务器并使用Reader读取返回的数据
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String lines="";
        String str="";
        while ((lines = reader.readLine()) != null) {
        		str=lines;
                System.out.println(lines);
        }
        reader.close();
        connection.disconnect();
        return str;
 } 
}

/**
 * 证书管理
 */
class MyX509TrustManager implements X509TrustManager {

	public X509Certificate[] getAcceptedIssuers() {
		return null;
	}

	public void checkClientTrusted(X509Certificate[] chain, String authType)
			throws CertificateException {
	}

	public void checkServerTrusted(X509Certificate[] chain, String authType)
			throws CertificateException {
	}
}
