package com.zhangzm.concurrency.module3;

/**
 * @author zhangzm
 * @date 2018/3/29 16:18
 */
public class CreateThread {

	public static void main(String[] args) {
		Thread t = new Thread();
		t.start();
		System.out.println(t.getName());

		Thread t1 = new Thread("传入名字的构造方法");
		t1.start();
		System.out.println(t1.getName());

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName());
			}
		});
		t2.start();

		Thread t3 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName());
			}
		},"传入名字及runnable实现类的构造方法");
		t3.start();

	}
}
