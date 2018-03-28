package com.zhangzm.concurrency.module1;

/**
 * @author zhangzm
 * @date 2018/3/28 11:42
 *
 * 仿照Thread类中的start方法的实现
 *
 * start方法中调用start0()-native方法,native方法表示为本地方法，本地方法多与平台有关。
 *
 * start0中会调用run方法
 *
 * start方法类似于下面的println方法
 *
 * run方法类似于warpPrint方法
 */
public class TempleteMethod {

	public final void println(String message) {
		System.out.println("######################");
		warpPrint(message);
		System.out.println("######################");
	}

	protected void warpPrint(String message) {

	}

	public static void main(String[] args) {
		TempleteMethod t1 = new TempleteMethod() {

			@Override
			protected void warpPrint(String message) {
				System.out.println("*" + message + "*");
			}
		};

		t1.println("hello");

		TempleteMethod t2 = new TempleteMethod() {

			@Override
			protected void warpPrint(String message) {
				System.out.println("+" + message + "+");
			}
		};

		t2.println("helloooo");
	}
}
