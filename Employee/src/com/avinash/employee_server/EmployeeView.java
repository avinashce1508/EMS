package com.avinash.employee_server;
import java.io.Serializable;


public class EmployeeView implements Serializable {

	private static final long serialVersionUID = 2140487492184152246L;
	private String emp_id;
	private String fname;
	private String lname;
	private String dep_id;
	private String dept_name;
	private String doj;
	private String designation;
	
	
	public EmployeeView(String emp_id, String fname, String lname, String dep_id, String dept_name, String doj,String designation ) {
		super();
		this.emp_id = emp_id;
		this.fname = fname;
		this.lname = lname;
		this.dep_id = dep_id;
		this.dept_name = dept_name;
		this.doj = doj;
		this.designation = designation;
		
	}

	public String getEmp_id() {
		return emp_id;
	}

	public String getFname() {
		return fname;
	}

	public String getLname() {
		return lname;
	}

	public String getDep_id() {
		return dep_id;
	}

	public String getDept_name() {
		return dept_name;
	}

	public String getDoj() {
		return doj;
	}

	public String getDesignation() {
		return designation;
	}
	
	

}
