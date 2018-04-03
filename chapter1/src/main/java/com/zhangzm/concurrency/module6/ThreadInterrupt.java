package com.zhangzm.concurrency.module6;

import java.util.Optional;

/**
 * @author zhangzm
 * @date 2018/4/3 15:41
 */
public class ThreadInterrupt {

	public static void main(String[] args) {
		Thread t = new Thread(() -> {
			while (true){
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					Optional.of("收到打断信号>>"+Thread.currentThread().isInterrupted()).ifPresent(System.out::println);
					e.printStackTrace();
				}
			}
		});

		t.start();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(t.isInterrupted());
		t.interrupt();
		System.out.println(t.isInterrupted());
	}

}
