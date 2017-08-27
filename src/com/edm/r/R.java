package com.edm.r;

import org.rosuda.JRI.Rengine;

public class R {

	private static Rengine rEngine = null;

	// 使用线程局部变量，保证一个线程只有一个Rengine
	private static final ThreadLocal threadLocal = new ThreadLocal();

	// 得到Rengine，把Rengine放入到线程局部变量中，然后返回Rengine
	public static Rengine getRengine() {
		rEngine = (Rengine) threadLocal.get();
		if (rEngine == null) {
			rEngine = new Rengine(null, false, null);
			if (!rEngine.waitForR()) {
				System.out.println("com.edm.r.R中的getRengine方法中R加载校验失败！");
				return null;
			}
			threadLocal.set(rEngine);
		}
		return rEngine;
	}

	// 在线程局部变量中取出Rengine，然后关闭Rengine，并把线程局部变量制空
	public static void closeRengine() {
		Rengine rEngine = (Rengine) threadLocal.get();
		if (rEngine != null) {
			rEngine.end();
		}
		threadLocal.set(null);
	}
}
