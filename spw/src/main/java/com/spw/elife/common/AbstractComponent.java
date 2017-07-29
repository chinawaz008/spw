package com.spw.elife.common;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.spw.elife.attachment.AttachmentCleaner;
import com.spw.elife.attachment.PathInfo;
import com.spw.elife.common.exception.UpfileFailedException;
import com.spw.elife.util.CnToSpell;
import com.spw.elife.util.Constant;

@Component
public abstract class AbstractComponent {

    private final Pattern schema = Pattern.compile("^(http|https|ftp)://.*");

    @Value("#{settings['resource.host']}")
    private String _resourceHost;
    @Value("#{settings['resource.path']}")
    private String _resourcePath;

    @Resource
    protected MessageSource messageSource;
    @Resource
    private AttachmentCleaner attachmentCleaner;

    public String getDefaultMessage() {
        return messageSource.getMessage("error.default", null, null);
    }

    /**
     * 判断某资源是否是外部资源.
     */
    protected boolean isInternetResource(String resource) {
        return schema.matcher(resource).matches();
    }

    /**
     * 获取资源服务器地址.
     */
    public String getResourceHost() {
        if (StringUtils.isBlank(_resourceHost)) {
            HttpServletRequest request = currentRequest();
            return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        }
        if (_resourceHost.startsWith("..")) {
            HttpServletRequest request = currentRequest();
            return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + _resourceHost.substring(2);
        }
        if (_resourceHost.startsWith("./")) {
            HttpServletRequest request = currentRequest();
            return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + _resourceHost.substring(1);
        }
        return _resourceHost;
    }

    /**
     * 获取资源服务器文件路径.
     */
    public String getResourceFilepath() {
        if (StringUtils.isBlank(_resourcePath)) {
            HttpServletRequest request = currentRequest();
            return ((HttpSession) request).getServletContext().getRealPath("/");
        }
        if (_resourcePath.startsWith("." + File.separator)) {
            HttpServletRequest request = currentRequest();
            return ((HttpSession) request).getServletContext().getRealPath("/") + _resourcePath.substring(1);
        }

        if (_resourcePath.startsWith("..")) {
            HttpServletRequest request = currentRequest();
            String realPath = ((HttpSession) request).getServletContext().getRealPath("/");
            int index = realPath.lastIndexOf(File.separatorChar, realPath.length() - 2);
            String parentPath = realPath.substring(0, index);
            return parentPath + _resourcePath.substring(2);
        }
        return _resourcePath;
    }

    /**
     * 将数据库中保存的{@code 资源值}转化成资源URL.
     *
     * @param resource {@code 资源值}
     * @return 资源URL或{@code null}
     */
    protected String toResourceUrl(String resource) {
        if (StringUtils.isBlank(resource)) {
            return null;
        }
        if (schema.matcher(resource).matches()) {
            return resource;
        } else {
            return getResourceHost() + resource;
        }
    }

    /**
     * 将数据库中保存的多个{@code 资源值}转化成资源URL数组.
     *
     * @param resource 多个{@code 资源值}，用英文逗号（,）分隔.
     * @return 资源URL数组或空数组.
     */
    protected String[] toMultipleResourceUrl(String resources) {
        if (StringUtils.isBlank(resources)) {
            return new String[0];
        }
        String[] split = resources.split(",");
        for (int i = 0; i < split.length; i++) {
            String resource = split[i];
            if (!schema.matcher(resource).matches()) {
                split[i] = getResourceHost() + resource;
            }
        }
        return split;
    }

    /**
     * 根据附件路径获取附件文件信息.
     *
     * @param url 文件路径. 数据库中保存的相对路径。
     * @return 附件文件信息
     */
    public PathInfo parseAttachment(String url) {
        if (url.startsWith(getResourceHost())) {
            int length = getResourceHost().length();
            String realUrl = url.substring(length);
            return parseAttachment(realUrl);
        } else if (schema.matcher(url).matches()) {
            return new PathInfo(null, url);
        } else {
            StringBuilder filePath = new StringBuilder();
            StringBuilder webpath = new StringBuilder();
            String filePathAndName = url.replace('/', File.separatorChar);

            filePath.append(getResourceFilepath());
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

    /**
     * 删除单个附件.
     *
     * @param url 单个{@code 资源值}
     */
    protected void removeAttachment(String url) {
        if (StringUtils.isBlank(url)) {
            return;
        }
        File file = parseAttachment(url).getFile();
        attachmentCleaner.put(file);
    }

    /**
     * 删除多个附件.
     *
     * @param url 单个{@code 资源值}
     */
    protected void removeMultipleAttachment(String urls) {
        if (StringUtils.isBlank(urls)) {
            return;
        }
        for (String url : urls.split(",")) {
            removeAttachment(url);
        }
    }

    /**
     * 上传多张附件.
     */
    public String[] upfile(MultipartFile[] multipartFiles, String namespace) throws IOException {
        if (multipartFiles == null || multipartFiles.length == 0) {
            return null;
        }
        ArrayList<String> urls = new ArrayList<String>();
        Set<String> filenames = new HashSet<String>();
        String path = namespace.endsWith("/") ? namespace + UUID.randomUUID() + "/" : namespace + "/" + UUID.randomUUID() + "/";
        for (int i = 0; i < multipartFiles.length; i++) {
            MultipartFile multipartFile = multipartFiles[i];
            if (multipartFile == null || multipartFile.isEmpty()) {
                continue;
            }
            String filename = CnToSpell.getFullSpell(multipartFile.getOriginalFilename());
            if (filenames.contains(filename)) {
                filename += ("-" + i);
            }
            filenames.add(filename);

            String url = path + filename;
            PathInfo attachment = parseAttachment(url);
            File file = attachment.getFile();

            File directory = file.getParentFile();
            if (!directory.exists()) {
                directory.mkdirs();
            }
            InputStream inputStream = null;
            try {
                inputStream = multipartFile.getInputStream();
                file.createNewFile();
                FileUtils.copyInputStreamToFile(inputStream, file);
                urls.add(attachment.getUrl());
            } finally {
                try {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (IOException ex) {
                    System.out.println("未知的错误"+Constant.getTrace(ex));
                }
            }
        }
        return urls.toArray(new String[0]);
    }

    /**
     * 上传单个附件.
     */
    public String upfile(MultipartFile multipartFile, String namespace) throws UpfileFailedException {
        if (multipartFile == null || multipartFile.isEmpty()) {
            return null;
        }
        String filename = CnToSpell.getFullSpell(multipartFile.getOriginalFilename());
        String url = namespace.endsWith("/") ? namespace + UUID.randomUUID() + "/" + filename : namespace + "/" + UUID.randomUUID() + "/" + filename;
        PathInfo attachment = parseAttachment(url);
        File file = attachment.getFile();

        File directory = file.getParentFile();
        if (!directory.exists()) {
            directory.mkdirs();
        }
        InputStream inputStream = null;
        try {
            inputStream = multipartFile.getInputStream();
            file.createNewFile();
            FileUtils.copyInputStreamToFile(inputStream, file);
            return attachment.getUrl();
        } catch (IOException ex) {
            //throw new UpfileFailedException("上传文件出错! namespace=" + namespace + ",file=" + multipartFile.getOriginalFilename());
        	System.out.println("上传文件出错! namespace=" + namespace + ",file=" + multipartFile.getOriginalFilename());
            return null;
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ex) {
            	System.out.println("未知的错误"+Constant.getTrace(ex));
            }
        }
    }

    /**
     * 获取当前Request对象.
     *
     * @return 当前Request对象或{@code null}.
     * @throws IllegalStateException 当前线程不是web请求抛出此异常.
     */
    protected HttpServletRequest currentRequest() throws IllegalStateException {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attrs == null) {
            throw new IllegalStateException("当前线程中不存在 Request 上下文");
        }
        return attrs.getRequest();
    }

    /**
     * 获取当前session对象. 若当前线程不是web请求或当前尚未创建{@code session}则返回{@code null}.
     *
     * @return 当前session对象或{@code null}.
     */
    protected HttpSession currentSession() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attrs == null) {
            return null;
        }
        return attrs.getRequest().getSession(false);
    }

}
