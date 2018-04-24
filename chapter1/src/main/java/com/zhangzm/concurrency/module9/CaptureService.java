package com.zhangzm.concurrency.module9;

import java.security.PrivateKey;
import java.util.*;

/**
 * @author zhangzm
 * @date 2018/4/18 15:45
 */
public class CaptureService {

	private final static LinkedList<control> CONTROLS = new LinkedList<>();
	private static int MAX_WORKER = 5;

	public static void main(String[] args) {

		List<Thread> worker = new ArrayList<>();
		Arrays.asList("m1", "m2", "m3", "m4", "m5", "m6", "m7", "m8", "m9", "m10").stream().map(CaptureService::createCaptureThread).forEach(n -> {
			n.start();
			worker.add(n);
		});

		worker.stream().forEach(t -> {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		Optional.ofNullable("所有的采集工作都已完成！").ifPresent(m->System.out.println(m));
	}

	private static Thread createCaptureThread(String name){
		return new Thread(()->{

			Optional.ofNullable("采集线程"+Thread.currentThread().getName()+"开始采集数据").ifPresent(System.out::println);
			synchronized (CONTROLS){
				//有问题的判断方式
				if(CONTROLS.size() >= MAX_WORKER){
					try {
						CONTROLS.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}else{
					CONTROLS.add(new control());
				}

				//正确的判断方式
//				while(CONTROLS.size() >= MAX_WORKER){
//					try {
//						CONTROLS.wait();
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//
//				CONTROLS.add(new control());
			}

			Optional.of("采集线程"+Thread.currentThread().getName()+"正在采集数据...").ifPresent(System.out::println);
			try {
				Thread.sleep(10_000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			synchronized (CONTROLS) {
				Optional.of("采集线程"+Thread.currentThread().getName()+"采集完成").ifPresent(System.out::println);
				CONTROLS.removeFirst();
				CONTROLS.notifyAll();
			}

		},name);
	}

	private static class control{

	}
}

