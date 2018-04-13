package com.zhangzm.concurrency.module7.bank_sync_method;

/**
 * @author zhangzm
 * @date 2018/3/28 12:24
 */
public class TicketWindowRunnable implements Runnable{

	private Integer index = 1;

	private final int max = 500;

	public void run() {
		while (true) {
			call();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public synchronized boolean call() {
		if(index > max){
			return true;
		}
		System.out.println(Thread.currentThread().getName() + "当前号码是：" + index++);
		return false;
	}
}
