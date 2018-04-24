package com.zhangzm.concurrency.module9;

import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;

/**
 * @author zhangzm
 * @date 2018/4/13 16:45
 *
 * 生产者消费者模型1
 */
public class ProduceConsumerVersion3 {

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
				LOCK.notifyAll();
			}
		}
	}

	private void consumer(){
		synchronized (LOCK){
			if(isProduced){
				System.out.println("C->"+i); //消费者消费i
				isProduced = false;
				LOCK.notifyAll();
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
		ProduceConsumerVersion3 pc = new ProduceConsumerVersion3();

		//多个生产者  多个消费者会出现问题：并不是死锁，而是全部放弃执行权。
		//p1生产，p1wait,p2wait，c1消费，c2wait
		//c1消费过后唤醒wait中的,线程,唤醒了c2,c2发现没有可以消费的，进入wait，c1也发现没有可以消费的，进入wait
		//此时p1,p2,c1,c2全部进入wait状态。

		//问题出在notify不确定唤醒的为哪个wait锁的线程，各个jvm的策略不同。可能为fifo或者其他策略。
		Stream.of("P1","P2","P3","P4").forEach(n ->
		new Thread(()->{
			while (true){
				pc.produce();
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		},n).start());

		Stream.of("C1","C2","C3","C4").forEach(n ->
			new Thread(()->{
				while (true){
					pc.consumer();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			},n).start()
		);
	}

}
