package com.zhangzm.concurrency.module7.syncTest;

/**
 * @author zhangzm
 * @date 2018/4/13 15:48
 */
public class SynchronizedObject {

	public static void main(String[] args) {
		SyncObject syncObject1 = new SyncObject();
		SyncObject syncObject2 = new SyncObject();

		lockObject obj1 = new lockObject("对象1");
		lockObject obj2 = new lockObject("对象2");

		//t1,t2线程，因为需要访问同一个对象，且对这个对象加锁，因此同一个对象同时只能被t1，t2中的一个访问。
		new Thread("T1"){
			@Override
			public void run() {
				syncObject1.m1(obj1);
			}
		}.start();

		new Thread("T2"){
			@Override
			public void run() {
				syncObject2.m1(obj1);
			}
		}.start();

		//t3，t4线程，因传入的加锁对象不同，因此可以并发执行。
//		new Thread("T3"){
//			@Override
//			public void run() {
//				syncObject1.m1(obj1);
//			}
//		}.start();
//
//		new Thread("T4"){
//			@Override
//			public void run() {
//				syncObject1.m1(obj2);
//			}
//		}.start();

	}

}

class SyncObject{

	public void m1(lockObject obj){
		synchronized (obj){
			System.out.println(Thread.currentThread().getName()+"调用："+obj.getName());
			try {
				Thread.sleep(10_000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class lockObject{

	private String name;

	public lockObject(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
