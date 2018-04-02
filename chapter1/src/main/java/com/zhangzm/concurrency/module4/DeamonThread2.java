package com.zhangzm.concurrency.module4;

/**
 * @author zhangzm
 * @date 2018/4/2 14:45
 */
public class DeamonThread2 {

	public static void main(String[] args) {
		Thread thread = new Thread(() -> {

			Thread thread1 = new Thread(() -> {
				try {
					System.out.println("thread内部创建的守护线程thread1启动。");
					Thread.sleep(100_000);//休眠100秒
					System.out.println("thread内部创建的守护线程thread1休眠结束。");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});

			thread1.setDaemon(true);
			thread1.start();

			try {
				Thread.sleep(1_000);
				System.out.println("thread休眠一秒结束");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		});

//		thread.setDaemon(true);

		thread.start();


	}
}
