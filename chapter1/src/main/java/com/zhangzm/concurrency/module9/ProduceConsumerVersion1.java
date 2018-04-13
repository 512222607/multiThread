package com.zhangzm.concurrency.module9;

import java.util.concurrent.locks.Lock;

/**
 * @author zhangzm
 * @date 2018/4/13 16:45
 *
 * 生产者消费者模型1
 */
public class ProduceConsumerVersion1 {

	private int i = 1;

	private final Object LOCK = new Object();

	private void produce(){
		synchronized (LOCK){
			System.out.println("P->"+(i++));//生产者 生产i++
		}
	}

	private void consumer(){
		synchronized (LOCK){
			System.out.println("C->"+i); //消费者消费i
		}
	}

	public static void main(String[] args) {
		ProduceConsumerVersion1 pc = new ProduceConsumerVersion1();

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
