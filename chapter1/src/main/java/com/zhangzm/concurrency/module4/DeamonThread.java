package com.zhangzm.concurrency.module4;

/**
 * @author zhangzm
 * @date 2018/4/2 14:15
 */
public class DeamonThread {

	/**
	 * 假设main方法启动的mian线程是A与B之间建立通信的长连接线程，则t线程的作用为检测连接是否正常存在
	 * @param args
	 */
	public static void main(String[] args) {
		Thread t = new Thread(() -> {
				while (true){
					System.out.println("为main方法做心跳检测，是否为守护线程："+Thread.currentThread().isDaemon());
					try {
						Thread.sleep(1_000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
		},"第一个线程");

		t.setDaemon(true); //将t设置为守护线程        这样设置后，main线程结束，无论t休眠多久，都会结束t线程
		t.start();//启动后t状态变为runnable状态 -> running -> dead | block 状态。

		try {
			Thread.sleep(10_000);//连接建立10秒
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(Thread.currentThread().getName());
	}
}
