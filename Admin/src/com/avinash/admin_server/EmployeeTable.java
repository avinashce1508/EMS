package com.avinash.admin_server;
import java.io.Serializable;
import java.sql.Date;

public class EmployeeTable implements Serializable {

	
	private static final long serialVersionUID = 7762848742688270549L;
	private String emp_id;
	private String fname;
	private String lname;
	private Long contact_no;
	private String dept_id;
	private String dept_name;
	private String designation;
	private Date doj;
	
	public EmployeeTable(String emp_id, String fname, String lname, Long contact_no, String dept_id, String dept_name,
			String designation, Date doj) {
		super();
		this.emp_id = emp_id;
		this.fname = fname;
		this.lname = lname;
		this.contact_no = contact_no;
		this.dept_id = dept_id;
		this.dept_name = dept_name;
		this.designation = designation;
		this.doj = doj;
	}

	public String getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
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

	public Long getContact_no() {
		return contact_no;
	}

	public void setContact_no(Long contact_no) {
		this.contact_no = contact_no;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
