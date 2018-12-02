package com.avinash.admin_server;

import java.io.Serializable;

public class AttendanceTable implements Serializable{

	private static final long serialVersionUID = -4932112491658103942L;
	private String Emp_id;
	private String fname;
	private String lname;
	private String dept_id;
	private String dept_name;
	private String designation;
	private boolean checked ;
	
	public AttendanceTable(String emp_id, String fname, String lname, String dept_id, String dept_name,
			String designation, boolean checked) {
		super();
		this.Emp_id = emp_id;
		this.fname = fname;
		this.lname = lname;
		this.dept_id = dept_id;
		this.dept_name = dept_name;
		this.designation = designation;
		this.checked = checked;
	}

	public String getEmp_id() {
		return Emp_id;
	}

	public void setEmp_id(String emp_id) {
		Emp_id = emp_id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
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

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
	

}
