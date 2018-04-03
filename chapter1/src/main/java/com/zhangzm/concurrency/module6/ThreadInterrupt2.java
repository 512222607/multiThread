package com.zhangzm.concurrency.module6;

/**
 * @author zhangzm
 * @date 2018/4/3 15:55
 */
public class ThreadInterrupt2 {

	private static final Object MONITOR = new Object();

	public static void main(String[] args) {
		Thread t = new Thread(){
			@Override
			public void run() {
				while (true) {
					synchronized (MONITOR) {
						try {
							MONITOR.wait(1000);
						} catch (InterruptedException e) {
							System.out.println(isInterrupted());
							e.printStackTrace();
						}
					}
				}
			}
		};

		t.start();

		t.interrupt();
	}

}
