package com.zhangzm.concurrency.module7;

import java.util.concurrent.locks.Lock;

/**
 * @author zhangzm
 * @date 2018/4/4 15:57
 */
public class SynchronizedTest {

	private final static Object LOCK = new Object();

	public static void main(String[] args) {
		Runnable runnable = () -> {
			synchronized (LOCK) {
				try {
					Thread.sleep(10_000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};

		Thread t1 = new Thread(runnable);
		Thread t2 = new Thread(runnable);
		Thread t3 = new Thread(runnable);
		//此时只有一个线程获得LOCK 另外两个会阻塞
		t1.start();
		t2.start();
		t3.start();

	}
}
