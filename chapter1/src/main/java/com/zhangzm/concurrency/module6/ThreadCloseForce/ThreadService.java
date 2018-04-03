package com.zhangzm.concurrency.module6.ThreadCloseForce;

/**
 * @author zhangzm
 * @date 2018/4/3 17:56
 */
public class ThreadService {
	//采用守护线程的方式执行

	/**
	 * 这是一个执行线程，在执行线程中创建一个守护线程用来完成用户最终需要执行的操作
	 */
	private Thread executeThread;

	/**
	 * 标志守护线程是否执行完成
	 */
	private boolean finish = false;


	public void execute(Runnable task){
		executeThread = new Thread(){
			@Override
			public void run() {
				Thread runner = new Thread(task); //执行线程中创建守护线程，用来完成操作
				runner.setDaemon(true);
				runner.start();
				try {
					runner.join();//等待守护线程执行的工作完成
					finish = true;//守护线程执行完成后才会运行到这一句，然后设置守护线程完成的标志
				} catch (InterruptedException e) {
					System.out.println("强制结束任务！");
				}
			}
		};

		executeThread.start();//启动执行线程
	}

	public void shutdown(long millis) {//参数可为超时时间，就是我等待多久，还未执行完成，就强制退出
		long currentTime = System.currentTimeMillis();
		while (!finish){//当守护线程没有执行完成的情况下，才会需要使用强制退出
			if(System.currentTimeMillis() - currentTime > millis){
				System.out.println("等待任务结束超时!");
				executeThread.interrupt();
				break;
			}
			//当没有超时 就短暂的休眠一下
			try {
				executeThread.sleep(1);
			} catch (InterruptedException e) {
				System.out.println("执行线程被打断");
				e.printStackTrace();
			}
		}
		finish = false;
	}
}
