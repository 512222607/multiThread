package com.zhangzm.concurrency.module5;

import java.util.stream.IntStream;

/**
 * @author zhangzm
 * @date 2018/4/2 15:46
 */
public class ThreadJoin {

	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(() -> {

			Thread t11 = new Thread(() -> {
				IntStream.range(0,1000).forEach(i->System.out.println(Thread.currentThread().getName()+"->"+i));
			},"t11");

			t11.start();
			try {
				t11.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			IntStream.range(0,1000).forEach(i->System.out.println(Thread.currentThread().getName()+"->"+i));

		},"t1");
		Thread t2 = new Thread(() -> {
			IntStream.range(0,1000).forEach(i->System.out.println(Thread.currentThread().getName()+"->"+i));
		},"t2");
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		//t1 t2 交替执行,但t1需要等待t11执行结束后才开始,于是输出结果是t11与t2交替输出，t11执行完，t1与t2交替执行，main方法等待t1 t2结束后执行
		IntStream.range(0,1000).forEach(i->System.out.println(Thread.currentThread().getName()+"->"+i));
	}
}
