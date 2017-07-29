/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spw.elife.attachment;

import java.io.File;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * 附件清洁工.
 *
 * @author Administrator
 */
@Service
public class AttachmentCleaner {

    private final Logger logger = LoggerFactory.getLogger(AttachmentCleaner.class);
    private final BlockingQueue<File> sharedQueue = new LinkedBlockingQueue<File>();
    private final Thread cleaner = new Thread(new Runnable() {

        public void run() {
            while (true) {
                try {
                    File file = sharedQueue.take();
                    if (!file.isDirectory()) {
                        file = file.getParentFile();
                    }
                    logger.debug("正在删除目录：{}", file.getAbsoluteFile());
                    FileUtils.deleteQuietly(file);
                    logger.debug("目录已删除！");
                } catch (Exception ex) {
                    logger.warn("删除文件出错", ex);
                }
            }
        }
    });

    @Resource
    private AttachmentService attachmentService;

    @PostConstruct
    public void init() {
        cleaner.start();
    }

    /**
     * 放入需要删除的文件.
     *
     * @param file 需要删除的文件
     */
    public void put(File file) {
        try {
            sharedQueue.put(file);
        } catch (InterruptedException ex) {
            logger.error("put -> 加入待删除的文件失败："+ex);
        }
    }

    @Scheduled(cron = "0 0 3 * * ?")
    public void clean() {
        logger.info("定时清理附件任务启动。。。");
        long t0 = System.currentTimeMillis();
        try {
            attachmentService.clean();
        } finally {
            long t1 = System.currentTimeMillis();
            logger.info("本次清理任务结束！ 耗时 {}ms.", t1 - t0);
        }
    }
}
