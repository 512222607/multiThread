package com.zhangzm.concurrency.module10;

import javax.swing.text.html.Option;
import java.net.CookieHandler;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

/**
 * @author zhangzm
 * @date 2018/4/19 15:33
 */
public class BooleanLock implements Lock {

	/**
	 * 属性值为true表示这个锁已经被拿走了
	 * 属性值为false表示这个锁目前是空闲的
	 */
	private boolean initValue;

	private Collection<Thread> blockedThreadCollection = new ArrayList<>();

	private Thread currentThread;

	public BooleanLock() {
		this.initValue = false;
	}

	@Override
	public synchronized void lock() throws InterruptedException {
		while (initValue){
			blockedThreadCollection.add(Thread.currentThread());
			this.wait();
		}
		blockedThreadCollection.remove(Thread.currentThread());
		this.initValue = true;
		this.currentThread = Thread.currentThread();//保存当前获取锁的线程。
	}

	@Override
	public synchronized void lock(long mills) throws InterruptedException, TimeoutException {

		if(mills <= 0){
			lock();
		}

		long hasRemaining = mills;
		long endTime = System.currentTimeMillis() + mills;
		while (initValue){
			if(hasRemaining <= 0){
				blockedThreadCollection.remove(Thread.currentThread());
				throw new TimeoutException(Thread.currentThread()+"Time out");
			}
			blockedThreadCollection.add(Thread.currentThread());
			this.wait(mills);
			hasRemaining = endTime - System.currentTimeMillis();
		}
		this.initValue = true;
		this.currentThread = Thread.currentThread();
	}

	@Override
	public synchronized void unlock() {
		//只有获取锁的这个线程才能够释放这个锁
		if(Thread.currentThread() == this.currentThread){
			//释放这个锁的标记
			this.initValue = false;
			Optional.ofNullable(Thread.currentThread() + "release the lock monitor.").ifPresent(System.out::println);
			this.notifyAll();
		}
	}

	@Override
	public Collection<Thread> getBlockThread() {
		return Collections.unmodifiableCollection(this.blockedThreadCollection);
	}

	@Override
	public int getBlockedSize() {
		return this.blockedThreadCollection.size();
	}
}
