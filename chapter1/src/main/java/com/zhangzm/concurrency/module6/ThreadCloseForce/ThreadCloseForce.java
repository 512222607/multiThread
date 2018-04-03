package com.zhangzm.concurrency.module6.ThreadCloseForce;

import java.util.Optional;

/**
 * @author zhangzm
 * @date 2018/4/3 17:47
 */
public class ThreadCloseForce {

	public static void main(String[] args) {
		ThreadService worker = new ThreadService();
		worker.execute(() ->{
			while(true){
				//建立一个长连接，或者加载一个非常大的文件
			}
		});
		worker.shutdown(10_000);//10秒后任务任务超时,停止任务
	}
}
