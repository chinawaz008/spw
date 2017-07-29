package com.spw.elife.boot;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;

/**
 * 
 * 抽象加载器
 * 
 * @author Administrator
 *
 */
public abstract class AbstractContextLoaderListener implements ServletContextListener {

	protected ContextLoaderListener contextLoaderListener = new ContextLoaderListener();

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		this.contextLoaderListener.contextDestroyed(event);
		
		/** 个性化关闭操作 **/
		onDestroyed();
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		System.out.println("开始初始化 Spring root WebApplicationContext");
		long startTime = System.currentTimeMillis();
		this.contextLoaderListener.contextInitialized(event);
		long elapsedTime = System.currentTimeMillis() - startTime;
		System.out.println("Root WebApplicationContext: initialization completed in " + elapsedTime + " 毫秒!");
		WebApplicationContext context = ContextLoaderListener.getCurrentWebApplicationContext();
		
		/** 个性化初始化操作 **/
		onInitialized(context);
	}

	public abstract void onInitialized(WebApplicationContext context);

	public abstract void onDestroyed();
}
