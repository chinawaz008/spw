/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spw.elife.attachment.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.spw.elife.attachment.AttachmentService;
import com.spw.elife.attachment.PathInfo;
import com.spw.elife.common.AbstractController;
import com.spw.elife.common.AjaxResponse;
import com.spw.elife.util.CnToSpell;

/**
 * 文件上传.
 *
 * @author lip
 */
@Controller
public class AttachmentController  extends AbstractController{

    private final Logger logger = LoggerFactory.getLogger(AttachmentController.class);
    @Resource
    private AttachmentService attachmentService;
    
    
    @RequestMapping(value = "/ajax/upfile", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public AjaxResponse upfile(@RequestParam("upfile") MultipartFile file) {
        AjaxResponse response = new AjaxResponse();
        if (file.isEmpty()) {
            response.setCode(HttpStatus.NO_CONTENT.value());
            response.setMessage("没有上传任何文件");
            return response;
        }
        String fileOriginalFilename = CnToSpell.getFullSpell(file.getOriginalFilename());
        PathInfo pathInfo = attachmentService.createPath(fileOriginalFilename);
        File directory = pathInfo.getFile();
        String webPath = pathInfo.getUrl();
        if (!directory.exists()) {
            directory.mkdirs();
        }

        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
            File repositoryFile = new File(directory, fileOriginalFilename);
            repositoryFile.createNewFile();
            FileUtils.copyInputStreamToFile(inputStream, repositoryFile);
            String path =this.getClass().getClassLoader().getResource("").getPath();
            path = URLDecoder.decode(path.substring(0,path.length()-17)+"/static/images/watermark.png","UTF-8");
            ImageUtil.waterMarkImageByIcon(path, repositoryFile.toString(), repositoryFile.toString(), 0, 0,0, 0.4f);
            response.setValue(webPath + "/" + fileOriginalFilename);
            response.setSize(repositoryFile.length());
            return response;
        } catch (IOException e) {
        	logger.warn("ajax/upfile-> 上传异常", e);
            response.setCode(HttpStatus.SERVICE_UNAVAILABLE.value());
            response.setMessage(e.getMessage());
            return response;
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ex) {
                logger.warn("未知的错误", ex);
            }
        }
    }
}
