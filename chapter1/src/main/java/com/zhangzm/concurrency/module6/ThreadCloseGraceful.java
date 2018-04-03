package com.zhangzm.concurrency.module6;

import java.awt.image.VolatileImage;

/**
 * @author zhangzm
 * @date 2018/4/3 16:57
 */
public class ThreadCloseGraceful {

	/**
	 * 方式1  一个任务 重复做一个工作的  可采用开关的方式
	 */
	public static class Worker extends Thread {
		private volatile boolean start = true;

		@Override
		public void run() {
			while (start) {

			}
		}

		public void shutdown(){
			this.start = false;
		}
	}

	public static void main(String[] args) {
		Worker w = new Worker();
		w.start();

		try {
			Thread.sleep(10_000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		w.shutdown();
	}
}
