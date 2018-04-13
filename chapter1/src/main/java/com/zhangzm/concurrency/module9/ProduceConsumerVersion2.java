package com.zhangzm.concurrency.module9;

import java.util.concurrent.locks.Lock;

/**
 * @author zhangzm
 * @date 2018/4/13 16:45
 *
 * 生产者消费者模型1
 */
public class ProduceConsumerVersion2 {

	private int i = 1;

	private final Object LOCK = new Object();

	private volatile boolean isProduced = false;

	private void produce(){
		synchronized (LOCK){
			if(isProduced){
				try {
					LOCK.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}else{
				i++;
				System.out.println("P->"+i);//生产者 生产i++
				isProduced = true;
				LOCK.notify();
			}
		}
	}

	private void consumer(){
		synchronized (LOCK){
			if(isProduced){
				System.out.println("C->"+i); //消费者消费i
				isProduced = false;
				LOCK.notify();
			}else{
				try {
					LOCK.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}
	}

	public static void main(String[] args) {
		ProduceConsumerVersion2 pc = new ProduceConsumerVersion2();

		new Thread(()->{
			while (true){
				pc.produce();
			}
		},"P").start();

		new Thread(()->{
			while (true){
				pc.consumer();
			}
		},"C").start();
	}

}
