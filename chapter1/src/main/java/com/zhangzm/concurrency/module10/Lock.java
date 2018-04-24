package com.zhangzm.concurrency.module10;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.TimeoutException;

/**
 * @author zhangzm
 * @date 2018/4/19 15:25
 */
public interface Lock {

	//接口中所有的东西都是public的

	class TimeOutException extends Exception{

		public TimeOutException(String message){
			super(message);
		}

	}

	/**
	 * 获取锁的方法
	 * @throws InterruptedException
	 */
	void lock() throws InterruptedException;

	/**
	 * 等待多久还没有获取锁 就打断，退出来。
	 * @param million
	 * @throws InterruptedException
	 * @throws TimeOutException
	 */
	void lock(long million) throws InterruptedException, TimeOutException, TimeoutException;

	/**
	 * 释放锁的方法
	 */
	void unlock();

	/**
	 * 用于观察当前有多少个线程为了争抢这个锁阻塞住了
	 * @return 返回阻塞的集合
	 */
	Collection<Thread> getBlockThread();

	/**
	 * 返回阻塞数量
	 * @return
	 */
	int getBlockedSize();
}
