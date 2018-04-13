package com.zhangzm.concurrency.module7;

/**
 * @author zhangzm
 * @date 2018/3/28 12:26
 *
 *
 *
 */
public class BankRunnable {

	/**
	 * 运行会发现出现可能出现501  502  已经超出最大值
	 * @param args
	 */
	public static void main(String[] args) {
		/**
		 * 有问题，未使用synchronized关键字的 会超出号码最大值
		 */
		final TicketWindowRunnable ticketWindowRunnable = new TicketWindowRunnable();

		Thread t1 = new Thread(ticketWindowRunnable,"一号窗口");

		Thread t2 = new Thread(ticketWindowRunnable,"二号窗口");

		Thread t3 = new Thread(ticketWindowRunnable, "三号窗口");

		t1.start();
		t2.start();
		t3.start();

		try {//保证t1 t2 t3先执行完 再执行t4 t5 t6
			t1.join();
			t2.join();
			t3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("************使用sync关键字");

		final TicketWindowRunnable_sync ticketWindowRunnableSync = new TicketWindowRunnable_sync();
		Thread t4 = new Thread(ticketWindowRunnableSync,"四号窗口");
		Thread t5 = new Thread(ticketWindowRunnableSync,"五号窗口");
		Thread t6 = new Thread(ticketWindowRunnableSync,"六号窗口");
		t4.start();
		t5.start();
		t6.start();
	}
}
