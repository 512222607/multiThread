package com.zhangzm.concurrency.module7.bank_sync_method;

import javax.swing.*;

/**
 * @author zhangzm
 * @date 2018/3/28 12:26
 *
 *
 *
 */
public class BankRunnable {

	public static void main(String[] args) {
		TicketWindowWork window1 = new TicketWindowWork("一号窗口");
		TicketWindowWork window2 = new TicketWindowWork("二号窗口");
		TicketWindowWork window3 = new TicketWindowWork("三号窗口");
		TicketWindowRunnable task = new TicketWindowRunnable();

		window1.doCall(task);
		window2.doCall(task);
		window3.doCall(task);

		window1.shutDown(9_000);
		window2.shutDown(5_000);
		window3.shutDown(11_000);
	}
}
