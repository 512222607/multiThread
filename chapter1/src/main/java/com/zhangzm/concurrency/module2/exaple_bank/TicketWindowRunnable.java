package com.zhangzm.concurrency.module2.exaple_bank;

/**
 * @author zhangzm
 * @date 2018/3/28 12:24
 */
public class TicketWindowRunnable implements Runnable{

	private Integer index = 1;

	private final int max = 50;

	public void run() {
		synchronized (index) {
			while (index <= max) {
				System.out.println(Thread.currentThread()+"当前号码是："+index++);
			}
		}
	}
}
