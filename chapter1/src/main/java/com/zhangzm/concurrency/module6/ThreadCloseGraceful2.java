package com.zhangzm.concurrency.module6;

import java.util.Optional;

/**
 * @author zhangzm
 * @date 2018/4/3 17:05
 */
public class ThreadCloseGraceful2 {

	private static class Worker extends Thread {
		@Override
		public void run() {
//			while (true) {
//				try {
//					Thread.sleep(1_000);
//				} catch (InterruptedException e) {
//					break;//return是执行完就退出  break是后续还有操作
//				}
//			}
//
//			System.out.println("后续操作!");
			int i = 0;
			while (true) {
				//执行某些操作
				System.out.println("执行某些操作开始");
				Optional.of(++i).ifPresent(System.out::println);
				System.out.println("执行某些操作结束");
				if(Thread.interrupted()){
					break;
				}
			}
		}

		public void shutdown(){
			this.interrupt();
		}
	}

	public static void main(String[] args) {
		Worker worker = new Worker();
		worker.start();

		//主线程等待子线程执行一段时间后停止子线程
		try {
			Thread.sleep(10_000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		worker.shutdown();
	}
}
