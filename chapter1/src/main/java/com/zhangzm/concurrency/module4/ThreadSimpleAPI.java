package com.zhangzm.concurrency.module4;

import java.util.Optional;

/**
 * @author zhangzm
 * @date 2018/4/2 15:06
 */
public class ThreadSimpleAPI {

	public static void main(String[] args) {
		Thread t1 = new Thread(() -> {
			Optional.of("Hello").ifPresent(System.out::println);
			try {
				Thread.sleep(1_000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		},"t1");

		Optional.of(t1.getName()).ifPresent(System.out::println);
		Optional.of(t1.getId()).ifPresent(System.out::println);
		Optional.of(t1.getPriority()).ifPresent(System.out::println);
	}
}
