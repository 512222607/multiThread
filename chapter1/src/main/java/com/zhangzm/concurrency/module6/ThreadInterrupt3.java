package com.zhangzm.concurrency.module6;

/**
 * @author zhangzm
 * @date 2018/4/3 16:14
 */
public class ThreadInterrupt3 {

	/**
	 * t1线程一直存在  t1.join后main线程一直等待t1  t2打断t1  异常不在main线程中抛出
	 * @param args
	 */
	public static void main(String[] args) {
		Thread t1 = new Thread(() -> {
			while (true) {
				if (Thread.currentThread().isInterrupted()) {
					break;
				}
			}
		});
		t1.start();
//		Thread main = Thread.currentThread();
		Thread t2 = new Thread(() ->{
			try {
				Thread.sleep(1000);
				t1.interrupt();
				System.out.println("interrupt main");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		t2.start();
		try {
			t1.join();//join表示main线程等待t1线程执行完才会运行后面的代码  让main等待，所以打断t1，t1是死循环并不会结束这个线程，所以main方法并没有被中断，还是在等待t1  join方法是封装的wait方法
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("main线程等待t1线程执行完成后需要做的操作");
	}
}
