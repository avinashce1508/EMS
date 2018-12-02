package com.avinash.employee_server;
import java.io.Serializable;
import java.sql.Date;

public class Department implements Serializable{
	
	
	private static final long serialVersionUID = -2282591802760811302L;
	private String dept_id;
	private String dept_name ;
	private String designation;
	private Date doj;
	
	public Department(String dept_id, String dept_name, String designation, Date doj) {
		super();
		this.dept_id = dept_id;
		this.dept_name = dept_name;
		this.designation = designation;
		this.doj = doj;
	}

	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Date getDoj() {
		return doj;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}
}
