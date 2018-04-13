package com.zhangzm.concurrency.module7.syncTest;

/**
 * @author zhangzm
 * @date 2018/4/13 14:59
 */
public class SynchronizedThid {

	public static void main(String[] args) {
		ThisLock thisLock = new ThisLock();
		new Thread("T1"){
			@Override
			public void run() {
				thisLock.m1();
			}
		}.start();

		new Thread("T2"){
			@Override
			public void run() {
				thisLock.m2();
			}
		}.start();

		new Thread("T3"){
			@Override
			public void run() {
				thisLock.m3();
			}
		}.start();
	}


}

/**
 * 验证方法级别的synchronized关键字的锁的作用范围
 *
 * 方法上的synchronized锁的是this
 *
 * 即t3的this锁与t1 t2的同步方法是相同的作用，即表明同步方法是锁的当前类的实现
 *
 */
class ThisLock{

	public synchronized void m1(){

		try {
			System.out.println("m1方法，调用线程："+Thread.currentThread().getName());
			Thread.sleep(10_000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public synchronized void m2(){

		try {
			System.out.println("m2方法，调用线程："+Thread.currentThread().getName());
			Thread.sleep(10_000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void m3(){
		synchronized (this){
			try {
				System.out.println("m3方法，调用线程："+Thread.currentThread().getName());
				Thread.sleep(10_000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
