package com.zhangzm.concurrency.module11;

/**
 * @author zhangzm
 * @date 2018/4/26 15:20
 */
public class ThreadException {
	private static final int A = 0;
	private static final int B = 0;

	public static void main(String[] args) {
		//线程中的异常只能捕获，不能由线程中往外抛出异常
		//但是一些运行时异常会直接报出来，并且外面无法获得这个异常，当出现运行时异常，线程出现异常就停止了
		Thread t = new Thread(() -> {

			try {
				Thread.sleep(5_000);
				int result = A / B;
				System.out.println(result);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		});
		//
		t.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				System.out.println(e);
				System.out.println(t);
			}
		});
		t.start();
	}
}
