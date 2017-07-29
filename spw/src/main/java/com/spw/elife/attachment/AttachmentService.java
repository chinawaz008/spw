/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spw.elife.attachment;

import java.io.File;
import java.util.Calendar;
import java.util.Iterator;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.spw.elife.common.TestProperties;
import com.spw.elife.util.Utils;

@Service
public class AttachmentService {

    private final Pattern schema = Pattern.compile("^(http|https|ftp)://.*");
    private final Pattern imgsrc = Pattern.compile("(src=\")([a-zA-Z0-9/\\._-]+)(\")");
    @Value("#{settings['file.host']}")
    private String host;
    @Value("#{settings['file.upload']}")
    private String _uploadRoot;
    private String root;
    @Value("#{settings['file.download']}")
    private String _download;
    private String namespace;

    @Autowired
    private ServletContext context;

    /**
     * 根据当前时间创建文件路径和网络路径.
     * <p>
     * 返回的文件是按照系统日期和UUID组织的目录，但是并不保证该目录实际存在。</p>
     * <p>
     * 返回的网络路径是上述文件的相对URL写法,总是以“{@code /}”开始但不以“{@code /}”结束。</p>
     *
     * @param fileName
     * @return
     */
    public PathInfo createPath(String fileName) {
    	TestProperties tx = new TestProperties();
    	String uaddress=tx.uploadAddress();
		String imageShow=tx.imageShow();
		
        String uuid = UUID.randomUUID().toString();
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1;
        int day = now.get(Calendar.DATE);
        StringBuilder filepath = new StringBuilder();
        filepath.append(uaddress);
        filepath.append(File.separatorChar).append(year);
        filepath.append(File.separatorChar).append(month);
        filepath.append(File.separatorChar).append(day);
        filepath.append(File.separatorChar).append(fileName).append("-").append(uuid);
        StringBuilder webpath = new StringBuilder();
        webpath.append(imageShow);
        webpath.append("/").append(year);
        webpath.append("/").append(month);
        webpath.append("/").append(day);
        webpath.append("/").append(fileName).append("-").append(uuid);
        System.out.println(filepath.toString()+"===============filepath.toString()");
        System.out.println(webpath.toString()+"===============webpath.toString()");
        return new PathInfo(new File(filepath.toString()), webpath.toString());
    }

    public PathInfo createUploadFile(String filePathAndName) {
        StringBuilder filePath = new StringBuilder();
        StringBuilder webpath = new StringBuilder();
        filePathAndName = filePathAndName.replace('/', File.separatorChar);
        filePath.append(getRoot());
        webpath.append(getNamespace());
        if (!filePathAndName.startsWith(File.separator)) {
            filePath.append(File.separatorChar);
            webpath.append("/");
        }
        filePath.append(filePathAndName);
        webpath.append(filePathAndName.replace(File.separatorChar, '/'));
        return new PathInfo(new File(filePath.toString()), webpath.toString());
    }

    /**
     * 根据附件路径获取附件文件信息.
     *
     * @param url 文件路径. 数据库中保存的相对路径。
     * @return 附件文件信息
     */
    public PathInfo parseAttachment(String url) {
        String host = getStaticResourceHost();
        if (url.startsWith(host)) {
            int length = host.length();
            String realUrl = url.substring(length);
            return parseAttachment(realUrl);
        } else if (schema.matcher(url).matches()) {
            return new PathInfo(null, url);
        } else {
            StringBuilder filePath = new StringBuilder();
            StringBuilder webpath = new StringBuilder();
            String filePathAndName = url.replace('/', File.separatorChar);

            filePath.append(context.getRealPath("/"));
            if (!filePathAndName.startsWith(File.separator)) {
                filePath.append(File.separatorChar);
                webpath.append("/");
            }
            filePath.append(filePathAndName);
            webpath.append(url);

            File file = new File(filePath.toString());
            File dictory = file.getParentFile();
            if (!dictory.exists()) {
                dictory.mkdirs();
            }
            return new PathInfo(file, webpath.toString());
        }
    }

    private String getRoot() {
        if (root == null) {
            if (_uploadRoot.charAt(0) == '.') {
                root = context.getRealPath("/") + _uploadRoot.substring(1);
            } else {
                root = _uploadRoot;
            }
        }
        return root;
    }

    private String getNamespace() {
        if (namespace == null) {
            namespace = _download;
        }
        return namespace;
    }

    /**
     * 获取资源服务器地址.
     */
    public String getStaticResourceHost() {
        if (StringUtils.isBlank(host)) {
            HttpServletRequest request = Utils.currentRequest();
            return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/" + request.getContextPath();
        }
        if (host.startsWith("./")) {
            HttpServletRequest request = Utils.currentRequest();
            return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + host.substring(1);
        }
        return host;
    }

    public void clean() {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DATE, -1);
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1;
        int day = now.get(Calendar.DATE);

        PathInfo attachment = parseAttachment(getNamespace() + "/" + year + "/" + month + "/" + day);
        Iterator<PathInfo> childrenIterator = attachment.childrenIterator();
        
        while(childrenIterator.hasNext()){
            PathInfo pathInfo = childrenIterator.next();
            System.out.println(pathInfo.getFile());
            //TODO 删除附件
        }
    }
}
