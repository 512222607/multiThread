package com.zhangzm.concurrency.module2.exaple_bank;

/**
 * @author zhangzm
 * @date 2018/3/28 12:26
 *
 * 使用runnable接口的方式 即可以将多线程处理的业务与县城类分离开来，如runnable接口中的总号码数是一个runnable实现类共享的，这样避免了使用static的方式（使用static方式会导致被static修饰的变量的声明周期过长）
 */
public class BankRunnable {

	public static void main(String[] args) {
		final TicketWindowRunnable ticketWindowRunnable = new TicketWindowRunnable();

		Thread t1 = new Thread(ticketWindowRunnable,"一号窗口");

		Thread t2 = new Thread(ticketWindowRunnable,"二号窗口");

		Thread t3 = new Thread(ticketWindowRunnable, "三号窗口");

		t1.start();
		t2.start();
		t3.start();

		//使用java8

//		final TicketWindowRunnable ticketWindowRunnable = new TicketWindowRunnable();
		Runnable ticketWindowRunnable1 = () -> {
			int index = 1;
			final int max = 50;
			while (index <= max) {
				System.out.println(Thread.currentThread()+"当前号码是："+index++);
			}
		};
		Thread t4 = new Thread(ticketWindowRunnable1,"4号窗口");

		Thread t5 = new Thread(ticketWindowRunnable1,"5号窗口");

		Thread t6 = new Thread(ticketWindowRunnable1, "6号窗口");

		t4.start();
		t5.start();
		t6.start();
	}
}
