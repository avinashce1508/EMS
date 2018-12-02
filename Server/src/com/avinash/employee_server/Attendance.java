package com.avinash.employee_server;
import java.io.Serializable;

public class Attendance implements Serializable{
	
	private static final long serialVersionUID = -2592050333268948735L;
	private int basic_salary;
	private int total_days;
	
	public Attendance( int total_days , int basic_salary) {
		super();
		this.basic_salary = basic_salary;
		this.total_days = total_days;
	}

	public int getBasic_Salary() {
		return basic_salary;
	}

	public void setBasic_Salary(int basic_salary) {
		this.basic_salary = basic_salary;
	}

	public int getTotal_days() {
		return total_days;
	}

	public void setTotal_days(int total_days) {
		this.total_days = total_days;
	}
	

}
