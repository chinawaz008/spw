package com.spw.elife.attachment.web;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spw.elife.common.AbstractController;
import com.spw.elife.util.CnToSpell;
@SuppressWarnings("deprecation")
@Controller 
@RequestMapping
public class UploadController  extends AbstractController{
	@RequestMapping(value = "/upload/images", method = RequestMethod.POST)
	public String imports(@RequestParam("files") MultipartFile[] files,HttpServletRequest request,Model model){
		String path =null;
		try {
			  //判断file数组不能为空并且长度大于0  
	        if(files!=null&&files.length>0){  
	            //循环获取file数组中得文件  
	            for(int i = 0;i<files.length;i++){  
	                MultipartFile file = files[i];  
	                //保存文件  
	                path = saveFile(file,request,"upload.address");  
	            }  
	        }
	        if(path==null||path==""){
	        	model.addAttribute("path", "请选择文件");
				return "/public/public_imgurl";
	        }  
		} catch (Exception e) {
			model.addAttribute("path","系统异常");
			return "/public/public_imgurl";
		}
		//截断前面的地址

		String sdsd = "imgs_msgy";
		path = path.substring(path.indexOf(sdsd) + 9,path.length());
//		path = path.substring(12,path.length());
    	model.addAttribute("path", path);
		return "/public/public_imgurl";
	}
	
	
	@RequestMapping(value = "/upload/fastcar", method = RequestMethod.POST)
	public String fastcar(@RequestParam("files") MultipartFile[] files,HttpServletRequest request,Model model){
		String path =null;
		try {
			  //判断file数组不能为空并且长度大于0  
	        if(files!=null&&files.length>0){  
	            //循环获取file数组中得文件  
	            for(int i = 0;i<files.length;i++){  
	                MultipartFile file = files[i];  
	                //保存文件  
	                path = saveFile(file,request,"fastcar.address");  
	            }  
	        }
	        if(path==null||path==""){
	        	model.addAttribute("path", "请选择文件");
				return "/public/public_imgurl";
	        }  
		} catch (Exception e) {
			model.addAttribute("path","系统异常");
			return "/public/public_imgurl";
		}
		//截断前面的地址
		path = path.substring(12,path.length());
    	model.addAttribute("path", path);
		return "/public/public_imgurl";
	}
	
	@RequestMapping(value = "/upload/goods", method = RequestMethod.POST)
	public String goods(@RequestParam("files") MultipartFile[] files,HttpServletRequest request,Model model){
		String path =null;
		try {
			  //判断file数组不能为空并且长度大于0  
	        if(files!=null&&files.length>0){  
	            //循环获取file数组中得文件  
	            for(int i = 0;i<files.length;i++){  
	                MultipartFile file = files[i];  
	                //保存文件  
	                path = saveFile(file,request,"goods.address");  
	            }  
	        }
	        if(path==null||path==""){
	        	model.addAttribute("path", "请选择文件");
				return "/public/public_imgurl";
	        }  
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("path","系统异常");
			return "/public/public_imgurl";
		}
		//截断前面的地址
		path = path.substring(12,path.length());
    	model.addAttribute("path", path);
		return "/public/public_imgurl";
	}
	
	@RequestMapping(value = "/upload/insuranceArticle", method = RequestMethod.POST)
	public String insuranceArticle(@RequestParam("files") MultipartFile[] files,HttpServletRequest request,Model model){
		String path =null;
		try {
			  //判断file数组不能为空并且长度大于0  
	        if(files!=null&&files.length>0){  
	            //循环获取file数组中得文件  
	            for(int i = 0;i<files.length;i++){  
	                MultipartFile file = files[i];  
	                //保存文件  
	                path = saveFileInsurance(file,request,"kindeditor.address");  
	            }  
	        }
	        if(path==null||path==""){
	        	model.addAttribute("path", "请选择文件");
				return "/public/public_imgurl";
	        }  
		} catch (Exception e) {
			model.addAttribute("path","系统异常");
			return "/public/public_imgurl";
		}
		//截断前面的地址
		path = path.substring(12,path.length());
    	model.addAttribute("path", path);
		return "/public/public_imgurl";
	}
	
	
	@SuppressWarnings("resource")
	public void SubmitPost(String url,String filename,String filepath){  
        HttpClient httpclient = new DefaultHttpClient();  
        try {  
            HttpPost httppost = new HttpPost(url);  
            FileBody bin = new FileBody(new File(filename));  
            StringBody address = new StringBody(filepath,Charset.forName(HTTP.UTF_8));
            MultipartEntity reqEntity = new MultipartEntity();  
            reqEntity.addPart("url", address);//url为请求后台的普通参数属性   
            reqEntity.addPart("file", bin);//file为请求后台的File upload属性      
            httppost.setEntity(reqEntity);  
            HttpResponse response = httpclient.execute(httppost);  
            int statusCode = response.getStatusLine().getStatusCode();  
            if(statusCode == HttpStatus.SC_OK){  
                File file = new File("template.xlsx");//...
                //删除临时文件
                file.delete(); 
                HttpEntity resEntity = response.getEntity();  
                System.out.println(EntityUtils.toString(resEntity));//httpclient自带的工具类读取返回数据  
                System.out.println(resEntity.getContent());     
                EntityUtils.consume(resEntity);  
            }  
            } catch (IOException e) {  
                e.printStackTrace();  
            } finally {  
               httpclient.getConnectionManager().shutdown();   
            }  
     }  
      
    /** 
     * @param args 
     */  
    public static void main(String[] args) {  
         UploadController httpPostArgumentTest2 = new UploadController();  
         httpPostArgumentTest2.SubmitPost("http://112.74.100.122:9316/carInsurance/upload/file","D://test//人员表.xlsx","/home/imgs_msgy/");
         //获取文件，在浏览器中输入http://112.74.100.122:9316/1912871484298575808.xlsx
    }  
    
    private String saveFile(MultipartFile file,HttpServletRequest request,String address) { 
		String filePath = null;
        // 判断文件是否为空  
        if (!file.isEmpty()) {  
            try {  
            	String basePath = getProp().getProperty(address);
            	SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
    			String date = sf.format(new Date());
                // 文件保存路径  
    			String fileOriginalFilename = CnToSpell.getFullSpell(file.getOriginalFilename());
                filePath = basePath +date+ fileOriginalFilename;  
                File filedir = new File(filePath);
    			if(!filedir.exists()){
    				filedir.mkdirs();
    			}
                // 转存文件  
                file.transferTo(new File(filePath));  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
      return filePath;  
    }
	
	
	

	@RequestMapping(value = "/upload/document", method = RequestMethod.POST)
	public String document(@RequestParam("files") MultipartFile[] files,HttpServletRequest request,Model model){
		String path =null;
		try {
			  //判断file数组不能为空并且长度大于0  
	        if(files!=null&&files.length>0){  
	            //循环获取file数组中得文件  
	            for(int i = 0;i<files.length;i++){  
	                MultipartFile file = files[i];  
	            	model.addAttribute("fileName", file.getOriginalFilename());
	                //保存文件  
	                path = saveFile2(file,request,"upload.address");  
	            }  
	        }
	        if(path==null||path==""){
	        	model.addAttribute("path", "请选择文件");
				return "/public/public_imgurl";
	        }  
		} catch (Exception e) {
			model.addAttribute("path","系统异常");
			return "/public/public_imgurl";
		}
		//截断前面的地址
		String sdsd = "imgs_msgy";
		path = path.substring(path.indexOf(sdsd) + 9,path.length());
    	model.addAttribute("path", path);
		return "/public/public_imgurl";
	}
	
	/**
	 * 文件上传
	 * 当设置dirName文件类型时进行文件类型check
	 * 可自定义上传文件类型 self-defining
	 * @param files
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/upload/model", method = RequestMethod.POST)
	public String model(@RequestParam("files") MultipartFile[] files,HttpServletRequest request,Model model){
		String path =null;
		// 定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "ogg,ape,mp3,wav,wma,wmv,mid");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");
		extMap.put("self-defining", request.getParameter("self-defining"));
		try {
			String address = request.getParameter("address");
			if(StringUtils.isEmpty(address)) {// 未设值时保存地址
				address = "upload.address";
			}
			String dirName = request.getParameter("dirName");;
			//判断file数组不能为空并且长度大于0  
			if(files!=null&&files.length>0){  
				//循环获取file数组中得文件  
				for(int i = 0;i<files.length;i++){  
					MultipartFile file = files[i];  
					String fileName = file.getOriginalFilename();
					model.addAttribute("fileName", fileName);
					if (StringUtils.isNotEmpty(dirName)) {// 判断文件类型
						String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
						if (!Arrays.<String> asList(extMap.get(dirName).split(",")).contains(fileExt)) {
							model.addAttribute("path", "上传文件类型不正确");
							model.addAttribute("result", "0");
							return "/public/public_imgurl";
						}
					}
					//保存文件  
					path = saveFile2(file,request,address);  
				}  
			}
			if(path==null||path==""){
				model.addAttribute("path", "请选择文件");
				model.addAttribute("result", "0");
				return "/public/public_imgurl";
			}  
			model.addAttribute("result", "1");
		} catch (Exception e) {
			model.addAttribute("path","系统异常");
			model.addAttribute("result", "0");
			return "/public/public_imgurl";
		}
		//截断前面的地址
		String sdsd = "imgs_msgy";
		path = path.substring(path.indexOf(sdsd) + 9,path.length());
		model.addAttribute("path", path);
		return "/public/public_imgurl";
	}	
	
	/**
	 * 文件上传 跨域
	 * 用ajax提交方式
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @throws IOException
	 */
	@RequestMapping(value = "/upload/model_new", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject model_new(HttpServletRequest request,HttpServletResponse response,Model model) throws IOException{
		String path =null;
		Map<String,Object> map = new HashMap<>();
		response.setContentType("text/html;charset=GBK");//解决中文乱码
		response.setCharacterEncoding("utf-8");  
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "*");
		JSONObject jo = new JSONObject();
		try {
			String address = request.getParameter("address");
			if(StringUtils.isEmpty(address)) {// 未设值时保存地址
				address = "upload.address";
			}
			
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile photo = multipartRequest.getFile("files"); 

			// 定义允许上传的文件扩展名
			HashMap<String, String> extMap = new HashMap<String, String>();
			extMap.put("image", "gif,jpg,jpeg,png,bmp");
			extMap.put("flash", "swf,flv");
			extMap.put("media", "ogg,ape,mp3,wav,wma,wmv,mid");
			extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");
			String fileName = photo.getOriginalFilename();
			String dirName = "image";
			// 检查扩展名
			String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
			if (!Arrays.<String> asList(extMap.get(dirName).split(",")).contains(fileExt)) {
				map.put("result", 0);
				map.put("message", "上传文件格式不正确" );
			}else {
				path = saveFile2(photo,request,address);  
				if(path==null||path==""){
					map.put("message", "请选择文件");
					map.put("result", 0);
				}
				else {
					//截断前面的地址
					String imgs_msgy = "imgs_msgy";
					path = path.substring(path.indexOf(imgs_msgy) + 9,path.length());
				}
				map.put("message", "");
				map.put("result", 1);
			}
		} catch (Exception e) {
			map.put("message", "系统异常");
			map.put("result", 0);
		}
		jo.accumulate("result", map.get("result"));
		jo.accumulate("message", map.get("message"));
		jo.accumulate("path", path);

		model.addAttribute("path", path);
//		response.getWriter().print(jo);
		
//		map.put("path", path);
//		PrintWriter out = response.getWriter();
//		JSONObject resultJSON = JSONObject.fromObject(map); //根据需要拼装json  
//		String jsonpCallback = request.getParameter("jsonpCallback");//客户端请求参数  
//		out.println(jsonpCallback+"("+resultJSON.toString(1,1)+")");//返回jsonp格式数据  
//		out.flush();
//		out.close();
		return jo;
	}	

	private String saveFile2(MultipartFile file,HttpServletRequest request,String address) { 
			String filePath = null;
	        // 判断文件是否为空  
	        if (!file.isEmpty()) {  
	            try {  
	            	String basePath = getProp().getProperty(address);
	            	SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
	    			String date = sf.format(new Date());
	                // 文件保存路径  
	    			String fileOriginalFilename = file.getOriginalFilename();
	                filePath = basePath +date+ fileOriginalFilename;  
	                File filedir = new File(filePath);
	    			if(!filedir.exists()){
	    				filedir.mkdirs();
	    			}
	                // 转存文件  
	                file.transferTo(new File(filePath));  
	            } catch (Exception e) {  
	                e.printStackTrace();  
	            }  
	        }  
	      return filePath;  
    }  
	
	private String saveFileInsurance(MultipartFile file,HttpServletRequest request,String address) { 
		String filePath = null;
        // 判断文件是否为空  
        if (!file.isEmpty()) {  
            try {  
            	String savePath = getProp().getProperty(address);
            	//检查目录
            	File uploadDir = new File(savePath);
            	if(!uploadDir .exists() && !uploadDir.isDirectory()){
            		uploadDir.mkdir();  
            	}
            	//创建文件夹
            	savePath += "file" + "/";
            	File saveDirFile = new File(savePath);
            	if (!saveDirFile.exists()) {
            		saveDirFile.mkdirs();
            	}
            	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            	String ymd = sdf.format(new Date());
            	savePath += ymd + "/";
            	File dirFile = new File(savePath);
            	if (!dirFile.exists()) {
            		dirFile.mkdirs();
            	}
            	
            	//检查扩展名
        		String fileExt = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1).toLowerCase();
            	//生成文件名称
            	SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
            	String newFileName = sf.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
            	
                filePath = savePath + newFileName;  
                File filedir = new File(filePath);
    			if(!filedir.exists()){
    				filedir.mkdirs();
    			}
                // 转存文件  
                file.transferTo(new File(filePath));  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
      return filePath;  
}  
	
	
	/**
	 * 删除文件方法
	 * @param address 文件路径前缀
	 * @param path 文件相对路径
	 * @return
	 */
	public String deleteFile(String address,String path) { 
		String filePath = null;
        try {  
        	String basePath = getProp().getProperty(address);
            // 文件保存路径  
            filePath = basePath + path ;
            File file = new File(filePath);
            file.delete();
        } catch (Exception e) {  
            e.printStackTrace();  
      }  
      return filePath;  
	}  
	
	/** 
     * 下载 
     *  
     * @author wangpeifa 
     * @param request 
     * @param response 
     * @return 
     * @throws Exception 
     */  
    @RequestMapping(value = "/download/file", method = RequestMethod.POST)  
    public void download(HttpServletRequest request,HttpServletResponse response) throws Exception {  
        try {
        	String fileName = request.getParameter("fileName");
        	if (StringUtils.isNotEmpty(fileName)) {
        		fileName = fileName.substring(fileName.lastIndexOf("/") + 1);
        	}
        	String address = request.getParameter("address");
        	String basePath = getProp().getProperty(address);
        	request.setCharacterEncoding("UTF-8");
        	BufferedInputStream bis = null;
        	BufferedOutputStream bos = null;
        	String downLoadPath = basePath + fileName;
        	
        	long fileLength = new File(downLoadPath).length();
//        	response.setHeader("Content-disposition", "attachment; filename="+ new String(fileName.getBytes("utf-8"),"ISO8859-1"));
        	response.addHeader("Content-Disposition", "inline; filename=\"" + new String(fileName.getBytes("utf-8"),"ISO8859-1") + "\"");  
        	response.setHeader("Content-Length", String.valueOf(fileLength));
        	response.setContentType("application/octet-stream;charset=UTF-8");
        	
        	bis = new BufferedInputStream(new FileInputStream(downLoadPath));
        	bos = new BufferedOutputStream(response.getOutputStream());
        	byte[] buff = new byte[2048];
        	int bytesRead;
        	while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
        		bos.write(buff, 0, bytesRead);
        	}
        	bis.close();
        	bos.close();
		} catch (Exception e) {
		}
    }
    
    
    
	/** 
     * 下载确认书 
     * @author wangpeifa 
     * @param request 
     * @param response 
     * @return 
     * @throws Exception 
     */  
    @RequestMapping(value = "/download/book", method = RequestMethod.POST)  
    public void downloadFile(HttpServletRequest request,HttpServletResponse response) throws Exception {  
        try {
        	String fileName = "book.docx";
        	if (StringUtils.isNotEmpty(fileName)) {
        		fileName = fileName.substring(fileName.lastIndexOf("/") + 1);
        	}
        	String basePath = getProp().getProperty("upload.address");
        	request.setCharacterEncoding("UTF-8");
        	BufferedInputStream bis = null;
        	BufferedOutputStream bos = null;
        	String downLoadPath = basePath + fileName;
        	
        	long fileLength = new File(downLoadPath).length();
//        	response.setHeader("Content-disposition", "attachment; filename="+ new String(fileName.getBytes("utf-8"),"ISO8859-1"));
        	response.addHeader("Content-Disposition", "inline; filename=\"" + new String(fileName.getBytes("utf-8"),"ISO8859-1") + "\"");  
        	response.setHeader("Content-Length", String.valueOf(fileLength));
        	response.setContentType("application/octet-stream;charset=UTF-8");
        	
        	bis = new BufferedInputStream(new FileInputStream(downLoadPath));
        	bos = new BufferedOutputStream(response.getOutputStream());
        	byte[] buff = new byte[2048];
        	int bytesRead;
        	while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
        		bos.write(buff, 0, bytesRead);
        	}
        	bis.close();
        	bos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
