package com.zhangzm.concurrency.module2.exaple_bank;

/**
 * @author zhangzm
 * @date 2018/3/28 12:15
 */
public class Bank {

	public static void main(String[] args) {
		TicketWindow window1 = new TicketWindow("一号柜台");
		window1.start();
		TicketWindow window2 = new TicketWindow("二号柜台");
		window2.start();
		TicketWindow window3 = new TicketWindow("三号柜台");
		window3.start();
	}
}
