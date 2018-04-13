package com.zhangzm.concurrency.module7.syncTest;

/**
 * @author zhangzm
 * @date 2018/4/13 15:19
 *
 * 1、该类的静态块与静态方法之间只能同时有一个线程调用
 *
 * 2、该类的所有实现类，同时只能有一个线程调用该类的实现类的方法（与this锁不同，this锁同时可以有多个线程调用不同的实例的相同方法）
 */
public class SynchronizedStatic {

	public static void main(String[] args) {
		StaticClass staticClass1 = new StaticClass();
		StaticClass staticClass2 = new StaticClass();

		new Thread("T1"){
			@Override
			public void run() {
				StaticClass.m1();
			}
		}.start();

		new Thread("T2"){
			@Override
			public void run() {
				StaticClass.m2();
			}
		}.start();

		new Thread("T3"){
			@Override
			public void run() {
				staticClass1.m3();
			}
		}.start();

		new Thread("T4"){
			@Override
			public void run() {
				staticClass2.m3();
			}
		}.start();
	}
}

/**
 * 初始化的时候静态方法和静态块都需要初始化，使用这个来证明静态方法的方法锁是class锁
 * class锁的作用就是 所有class的实例同时只能有一个线程调用该类的实例中的非静态方法或者该类的静态方法
 */
class StaticClass{
	static {
		synchronized (StaticClass.class) {
			try {
				System.out.println("静态代码块，调用线程："+Thread.currentThread().getName());
				Thread.sleep(10_000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public synchronized static void m1(){
		try {
			System.out.println("m1方法，调用线程："+Thread.currentThread().getName());
			Thread.sleep(10_000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized static void m2(){
		try {
			System.out.println("m2方法，调用线程："+Thread.currentThread().getName());
			Thread.sleep(10_000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void m3(){
		synchronized (StaticClass.class){
			try {
				System.out.println("m3方法，调用线程："+Thread.currentThread().getName());
				Thread.sleep(10_000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}