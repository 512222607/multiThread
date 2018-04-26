package com.zhangzm.concurrency.module10.HookTest;

/**
 * @author zhangzm
 * @date 2018/4/26 14:52
 */
public class ExitCapture {

	public static void main(String[] args) {
		//注入一个runtime的结束钩子，帮我们在程序出问题的时候释放一些资源和通知admin
		Runtime.getRuntime().addShutdownHook(new Thread(()->{

			System.out.println("The application will be exit...");
			notifyAndRelease();
		}));

		int i = 0;
		while (true) {
			try {
				Thread.sleep(1_000L);
				System.out.println("I'm working ...");
			} catch (InterruptedException e) {
				//
			}
			//模拟程序出现问题。
			i++;
			if(i > 20){
				throw new RuntimeException("Error");
			}

		}

	}

	private static void notifyAndRelease() {

		System.out.println("notify to the admin.");
		try {
			Thread.sleep(1_000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("will release resources(socket,file,connection)");
		try {
			Thread.sleep(1_000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("release resources！");
	}


}
