package com.zhangzm.concurrency.module11;

/**
 * @author zhangzm
 * @date 2018/4/26 15:54
 */
public class TestMain {
	//Thread.currentThread().getStackTrace() API的作用，与抛出异常时打印的异常堆栈是一样的。
	public static void main(String[] args) {
		new Test1().test();
	}
}
