package com.zhangzm.concurrency.module5;

import java.util.Optional;

/**
 * @author zhangzm
 * @date 2018/4/2 16:27
 */
public class ThreadJoinDemo {

	/**
	 * 模拟一个需求，使用3个线程分别对3台机器的数据进行采集，当都采集完成后将花费的时间和结果保存到数据库。
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		long startTime = System.currentTimeMillis();

		Thread t1 = new Thread(new CaptureRunnable("machine1",10_000));
		Thread t2 = new Thread(new CaptureRunnable("machine2",15_000));
		Thread t3 = new Thread(new CaptureRunnable("machine3",5_000));

		t1.start();
		t2.start();
		t3.start();

		t1.join();
		t2.join();
		t3.join();

		long finishTime = System.currentTimeMillis();

		Optional.of(finishTime-startTime).ifPresent(a->System.out.println("将采集的数据保存到数据库,采集共花费:"+a));
	}

}

class CaptureRunnable implements Runnable{

	private String machineName;

	/**
	 *模拟这个线程执行操作所花费的时间
	 */
	private long dotime;

	public CaptureRunnable(String machineName, long dotime) {
		this.machineName = machineName;
		this.dotime = dotime;
	}

	@Override
	public void run() {
		//do the really things
		//模拟实际运行花费时间
		try {
			Thread.sleep(dotime);
			System.out.println(machineName+"数据采集完成！");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String getReasult(){
		return machineName+"finished!";
	}
}