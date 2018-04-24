package com.zhangzm.concurrency.module10;

import java.util.Optional;
import java.util.concurrent.TimeoutException;
import java.util.stream.Stream;

/**
 * @author zhangzm
 * @date 2018/4/19 15:41
 */
public class LockTest {

	public static void main(String[] args) {

		final BooleanLock booleanLock = new BooleanLock();

		Stream.of("T1","T2","T3").forEach(name -> {

			new Thread(()->{
				try {
					booleanLock.lock(1000L);
					Optional.ofNullable(Thread.currentThread().getName() + " have the lock monitor!").ifPresent(System.out::println);
					work();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (TimeoutException e) {
					System.out.println(e.getMessage());
				} finally {
					booleanLock.unlock();
				}
			},name).start();

		});

		Stream.of("T4","T5").forEach(name -> {

			new Thread(()->{
				try {
					booleanLock.lock(5000L);
					Optional.ofNullable(Thread.currentThread().getName() + " have the lock monitor!").ifPresent(System.out::println);
					work();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (TimeoutException e) {
					System.out.println(e.getMessage());
				} finally {
					booleanLock.unlock();
				}
			},name).start();

		});


		try {
			Thread.sleep(3_000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("打印阻塞的线程列表");
		Optional.ofNullable(booleanLock.getBlockThread()).ifPresent(n->{
			n.forEach( a -> System.out.println(a.getName()));
		});

	}

	private static void work(){
		try {
			Optional.ofNullable(Thread.currentThread().getName() + " is working...").ifPresent(System.out::println);
			Thread.sleep(10_000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
