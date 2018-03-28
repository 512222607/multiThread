package com.zhangzm.concurrency.module2.exaple_bank;

/**
 * @author zhangzm
 * @date 2018/3/28 12:12
 *
 *
 * 模拟银行叫号
 */
public class TicketWindow extends Thread{

	private String windowName;

	private final static int max = 50;

	private static int index = 1;

	public TicketWindow(String windowName){
		this.windowName = windowName;
	}

	@Override
	public void run() {
		while (index <= max) {
			System.out.println(windowName+"当前号码是："+index++);
		}
	}
}
