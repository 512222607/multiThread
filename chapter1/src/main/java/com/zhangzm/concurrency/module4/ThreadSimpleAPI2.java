package com.zhangzm.concurrency.module4;

import java.util.Optional;

/**
 * @author zhangzm
 * @date 2018/4/2 15:27
 */
public class ThreadSimpleAPI2 {

	public static void main(String[] args) {
		Thread t1 = new Thread(() -> {
			for (int i = 0; i < 1000; i++) {
				Optional.of(Thread.currentThread().getName()+"-index"+i).ifPresent(System.out::println);
			}
		});
		t1.setPriority(Thread.MAX_PRIORITY);

		Thread t2 = new Thread(() -> {
			for (int i = 0; i < 1000; i++) {
				Optional.of(Thread.currentThread().getName()+"-index"+i).ifPresent(System.out::println);
			}
		});
		t2.setPriority(Thread.NORM_PRIORITY);

		Thread t3 = new Thread(() -> {
			for (int i = 0; i < 1000; i++) {
				Optional.of(Thread.currentThread().getName()+"-index"+i).ifPresent(System.out::println);
			}
		});
		t3.setPriority(Thread.MIN_PRIORITY);

		t1.start();
		t2.start();
		t3.start();

	}
}
