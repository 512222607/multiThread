package com.zhangzm.concurrency.module10;

/**
 * @author zhangzm
 * @date 2018/4/24 17:01
 */
public class SynchronizedProblem {

	public static void main(String[] args) throws InterruptedException {

		Thread t1 = new Thread(() -> {
			SynchronizedProblem.run();
		},"t1");
		t1.start();

		Thread.sleep(1000);

		Thread t2 = new Thread(() -> {
			SynchronizedProblem.run();
		},"t2");
		t2.start();

		Thread.sleep(2_000);
		t2.interrupt();
		System.out.println(t2.isInterrupted());
	}

	private synchronized static void run(){

		System.out.println(Thread.currentThread().getName());
		while (true){

		}

	}
}
