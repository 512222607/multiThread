package com.zhangzm.concurrency.module3;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhangzm
 * @date 2018/3/29 16:42
 */
public class CreateThread2 {

	public static void main(String[] args) {
		Thread t = new Thread(()->{
			try {
				Thread.sleep(300L);//为了让线程存活时间边长
			}catch (Exception e){

			}
		});
		t.start();
		System.out.println(t.getThreadGroup().getName());
		System.out.println(Thread.currentThread().getName());//这个是main线程的

		//调用ThreadGroup的API可以获取一些信息，如下：
		ThreadGroup tg = t.getThreadGroup();//t和main线程的ThreadGroup一致（如果初始化的时候未指定ThreadGroup）
		/**
		 *
		 */
		System.out.println(tg.activeCount());

		/**
		 * 枚举当前的活动线程到入参的Thread[]中
		 */
		Thread[] activeThread = new Thread[Thread.currentThread().getThreadGroup().activeCount()];
		tg.enumerate(activeThread);
		for (Thread thread : activeThread){
			System.out.println(thread);
		}
		System.out.println("******");
		//使用java8遍历
		Arrays.asList(activeThread).forEach(System.out::println);

		/**
		 * java8 :: 语句测试
		 */
		String[] strings = new String[5];
		strings[0] = "0";
		strings[1] = "1";
		strings[2] = "2";
		strings[3] = "3";
		strings[4] = "4";
		Arrays.asList(strings).forEach(System.out::println);

		List<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.forEach(a->System.out.println(a));
	}
}
