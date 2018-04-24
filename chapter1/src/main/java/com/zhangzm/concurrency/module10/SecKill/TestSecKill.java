package com.zhangzm.concurrency.module10.SecKill;

import com.zhangzm.concurrency.module10.Lock;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zhangzm
 * @date 2018/4/19 15:58
 */
public class TestSecKill {

	public static void main(String[] args) {
		goods goods = new goods();
		goods.addGoods(Stream.of("a","b","c","d","e","f","g").collect(Collectors.toList()));
		SecKillLock secKillLock = new SecKillLock();

		Stream.of("p1", "p2", "p3", "p4", "p5", "p6", "p7", "p8", "p9", "p10", "p11", "p12", "p13", "p14", "p15").forEach(n->{
			TestSecKill.waitToSeckill(secKillLock,n,goods);
		});

		Optional.ofNullable(secKillLock.waitPeople).ifPresent(n->{
			n.stream().forEach(m->{
				System.out.println(m.getName());
			});
		});

		try {
			Thread.sleep(5_000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public static Thread waitToSeckill(SecKillLock secKillLock,String name,goods g){
		return new Thread(()->{
			while (!secKillLock.isStart){
				synchronized (secKillLock){
					try {
						secKillLock.waitPeople.add(Thread.currentThread());
						secKillLock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
//
//			synchronized (goods)
		},name);
	}

}

class goods{

	Collection<String> goodList;

	public goods() {
		this.goodList = new ArrayList<>();
	}

	public synchronized void addGoods(Collection<String> goods){
		this.goodList.addAll(goods);
	}

}

class SecKillLock{

	Collection<Thread> waitPeople = new ArrayList<>();

	public boolean isStart;

	public SecKillLock() {
		isStart = false;
	}

	public synchronized void LockStart(){
		isStart = true;
	}
}
