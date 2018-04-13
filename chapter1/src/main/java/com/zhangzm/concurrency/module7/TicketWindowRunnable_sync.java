package com.zhangzm.concurrency.module7;

/**
 * @author zhangzm
 * @date 2018/3/28 12:24
 */
public class TicketWindowRunnable_sync implements Runnable{

	private Integer index = 1;

	private final int max = 500;

	private final Object MONITOR = new Object();

	public void run() {
		while (true) {
			synchronized (MONITOR) {
				if(index > max){
					break;
				}
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread() + "当前号码是：" + index++);
			}
		}
	}
}
