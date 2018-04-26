package com.zhangzm.concurrency.module11;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author zhangzm
 * @date 2018/4/26 15:47
 */
public class Test2 {

	public void test(){
		Arrays.asList(Thread.currentThread().getStackTrace()).stream().filter(e -> !e.isNativeMethod()).forEach(e-> Optional.of(e.getClassName() + ":" + e.getMethodName() + ":" + e.getLineNumber() ).ifPresent(System.out::println));
	}
}
