package com.zhangzm.concurrency.module1;

/**
 * @author zhangzm
 * @date 2018/3/27 12:44
 */
public class TryConcurrency {

	public static void main(String[] args) {

		//内部类
		Thread t1 = new Thread("a") {
			@Override
			public void run() {
				readFromDateBase();
			}
		};
		t1.start();


		//匿名内部类
		new Thread("read-db"){
			@Override
			public void run() {
				readFromDateBase();
			}
		}.start();

		new Thread("write-file") {
			@Override
			public void run() {
				writeDataToFile();
			}
		}.start();
//		readFromDateBase();
//		writeDataToFile();
	}

	private static void readFromDateBase (){
		//从数据库读数据
		try {
			println("Begin read data from db.");
			Thread.sleep(1000 * 10L);
			println("Read date done and start handle it.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		println("The date handle finish and successfully");
	}

	private static  void writeDataToFile(){
		try {
			println("Begin write data to file.");
			Thread.sleep(2000 * 10L);
			println("write date done and start handle it.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		println("The date handle finish and successfully");
	}

	private static void println(String str){
		System.out.println(str);
	}

}
