package com.zhangzm.concurrency.module2.exanple_calculate;

/**
 * @author zhangzm
 * @date 2018/3/28 12:50
 */
public class simpleCalculatorStrategy implements CalculatorStrategy{

	private final static double SALARY_RATE = 0.1;
	private final static double bonus_RATE = 0.15;

	public double calculate(double salary, double bonus) {
		return salary*SALARY_RATE + bonus *bonus_RATE;
	}
}
