package com.avinash.admin_server;
import java.io.Serializable;

public class EditInfo implements Serializable {

	private static final long serialVersionUID = -1095354196147887090L;
	private String emp_id;
	private String emp_name;
	private long cotact_no;
	private String emp_dept;
	private String emp_dept_name;
	private String emp_designation;
	
	public EditInfo(String emp_id, String emp_name, long cotact_no, String emp_dept, String emp_dept_name,
			String emp_designation) {
		super();
		this.emp_id = emp_id;
		this.emp_name = emp_name;
		this.cotact_no = cotact_no;
		this.emp_dept = emp_dept;
		this.emp_dept_name = emp_dept_name;
		this.emp_designation = emp_designation;
	}

	public String getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

	public long getCotact_no() {
		return cotact_no;
	}

	public void setCotact_no(long cotact_no) {
		this.cotact_no = cotact_no;
	}

	public String getEmp_dept() {
		return emp_dept;
	}

	public void setEmp_dept(String emp_dept) {
		this.emp_dept = emp_dept;
	}

	public String getEmp_dept_name() {
		return emp_dept_name;
	}

	public void setEmp_dept_name(String emp_dept_name) {
		this.emp_dept_name = emp_dept_name;
	}

	public String getEmp_designation() {
		return emp_designation;
	}

	public void setEmp_designation(String emp_designation) {
		this.emp_designation = emp_designation;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

