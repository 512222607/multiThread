package com.zhangzm.concurrency.module7.bank_sync_method;

/**
 * @author zhangzm
 * @date 2018/4/4 16:16
 */
public class TicketWindowWork {

	private Thread ticketWindowWork;

	//窗口叫号是否已完成
	private Boolean flag = false;

	//窗口名
	private String name;

	public TicketWindowWork(String name) {
		this.name = name;
	}

	public void doCall(Runnable task) {
		ticketWindowWork = new Thread(() -> {
			Thread worker = new Thread(task,name);
			worker.setDaemon(true);
			worker.start();
			try {
				worker.join();
				flag = true;
			} catch (InterruptedException e) {
				System.out.println(name+"停止叫号!");
//				e.printStackTrace();
			}
		});
		ticketWindowWork.start();
	}

	public void shutDown(long million) {
		long currentTime = System.currentTimeMillis();
		while (!flag) {
			if (System.currentTimeMillis() - currentTime > million) {
				System.out.println(name+"等待叫号系统叫完所有号超时!");
				ticketWindowWork.interrupt();
				break;
			}
			try {
				ticketWindowWork.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		flag = false;
	}
}
