package com.spw.elife.boot;

/**
 * 
 * 抽象的Bootstrap接口
 * <p/>
 * 当系统启动或者关闭需要做一些初始化操作或者关闭操作的时候<br>
 * 请实现Bootstrap接口,并实现其init()和destroy()方法<br>
 * 请参考BootstrapTask<br>
 * 
 * @author Administrator
 *
 */
public interface Bootstrap {
	
	public abstract void init();

	public abstract void destroy();
}
