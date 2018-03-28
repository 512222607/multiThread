package com.zhangzm.concurrency.module2.exanple_calculate;

/**
 * @author zhangzm
 * @date 2018/3/28 12:52
 *
 * 策略模式  对应Thread中Runnable接口的设计思想
 * CalculatorStrategy 对应runnable接口 定义了个人税率计算的算法
 *
 * simpleCalculatorStrategy 对应runnable接口的实现类，写了税率计算算法的详细实现
 *
 * TaxCalacultors Thread类，其中定义了明确的
 *
 */
public class TaxCalculatorMain {

	public static void main(String[] args) {
		TaxCalacultors calacultors = new TaxCalacultors(10000d,2000d);

		CalculatorStrategy strategy = new simpleCalculatorStrategy();

		calacultors.setCalculatorStrategy(strategy);

		System.out.println(calacultors.calcTax());

		TaxCalacultors calacultors1 = new TaxCalacultors(20000d,4000d){
			@Override
			public double calculate(double salary, double bonus) {
				System.out.println("相当于写一个Thread的子类，覆盖run方法");
				return salary*0.1 + bonus *0.15;
			}
		};

		System.out.println(calacultors1.calcTax());

		//使用java8
		TaxCalacultors calacultors3 = new TaxCalacultors(10000d,2000d);

		calacultors.setCalculatorStrategy((s,b) -> { return s*0.1 + b *0.15;});

		System.out.println(calacultors.calcTax());
	}
}
