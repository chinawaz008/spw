package com.spw.elife.boot;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 
 * spring容器加载监听器
 * <p/>
 * 如果需要在初始化spring容器的时候做一些个性化定制操作,如开启一个线程处理队列任务<br>
 * 请在web.xml中配置该监听器用来取代spring框架的原生的ContextLoaderListener<br>
 * 
 * @author Administrator
 *
 */
public class StandardContextLoaderListener extends AbstractContextLoaderListener {

	private static ApplicationContext ctx;

	private String[] bootstrap_names = null;

	@Override
	public void onInitialized(WebApplicationContext event) {

		System.out.println("初始化数据开始......");		
		ctx = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
		bootstrap_names = ctx.getBeanNamesForType(Bootstrap.class);
		for (String name : bootstrap_names) {
			Bootstrap bootstrap = ctx.getBean(name, Bootstrap.class);
			bootstrap.init();
		}
	}

	@Override
	public void onDestroyed() {
		System.out.println("正在关闭应用......");
		Bootstrap bootstrap = null;
		for (String name : bootstrap_names) {
			bootstrap = ctx.getBean(name, Bootstrap.class);
			bootstrap.destroy();
		}
		System.out.println("应用已经关闭......");
	}
}
