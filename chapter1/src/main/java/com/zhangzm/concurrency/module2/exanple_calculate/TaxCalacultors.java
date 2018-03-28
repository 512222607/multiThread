package com.zhangzm.concurrency.module2.exanple_calculate;

/**
 * @author zhangzm
 * @date 2018/3/28 12:45
 */
public class TaxCalacultors implements  CalculatorStrategy{

	private double salary;
	private double bonus;

	private CalculatorStrategy calculatorStrategy;

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}

	public CalculatorStrategy getCalculatorStrategy() {
		return calculatorStrategy;
	}

	public void setCalculatorStrategy(CalculatorStrategy calculatorStrategy) {
		this.calculatorStrategy = calculatorStrategy;
	}

	public TaxCalacultors(double salary, double bonus) {
		this.salary = salary;
		this.bonus = bonus;
	}

	protected double calcTax(){
		return calculate(salary,bonus);
	}

	public double calculate(double salary, double bonus) {
		if (calculatorStrategy != null) {
			return calculatorStrategy.calculate(salary,bonus);
		}
		return 0d;
	}
}
