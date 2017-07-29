package com.spw.elife.boot;

/**
 * 
 * 默认的Bootstrap
 * <p/>
 * 对Bootstrap接口的默认实现,主要是承担系统的初始化和关闭操作<br>
 * 
 * @author Administrator
 *
 */
public class BootstrapTask implements Bootstrap {

	@Override
	public void init() {
		System.out.println("---------------------BootTask启动---------------------");
	}

	@Override
	public void destroy() {
		System.out.println("---------------------BootTask关闭---------------------");
	}
}