package com.avinash.employee_server;
import java.io.Serializable;
import java.sql.Date;

public class EmployeeInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 200198659902284800L;
	private String emp_id;
	private String emp_fname;
	private String emp_lname;
	private Date dob;
	private String sex;
	private String address;
	private long cotact_no;
    private byte[] byte_array_of_image;
    

	public EmployeeInfo(String emp_id, String emp_fname, String emp_lname, Date dob, String sex, String address,
			long cotact_no , byte[] byte_array_of_image) {
		super();
		this.emp_id = emp_id;
		this.emp_fname = emp_fname;
		this.emp_lname = emp_lname;
		this.dob = dob;
		this.sex = sex;
		this.address = address;
		this.cotact_no = cotact_no;
		this.byte_array_of_image = byte_array_of_image;
		
	}
	
	public byte[] getByte_array_of_image() {
		return byte_array_of_image;
	}

	public void setByte_array_of_image(byte[] byte_array_of_image) {
		this.byte_array_of_image = byte_array_of_image;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getEmp_id() {
		return emp_id;
	}


	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}


	public String getEmp_fname() {
		return emp_fname;
	}


	public void setEmp_fname(String emp_fname) {
		this.emp_fname = emp_fname;
	}


	public String getEmp_lname() {
		return emp_lname;
	}


	public void setEmp_lname(String emp_lname) {
		this.emp_lname = emp_lname;
	}


	public Date getDob() {
		return dob;
	}


	public void setDob(Date dob) {
		this.dob = dob;
	}


	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public long getCotact_no() {
		return cotact_no;
	}


	public void setCotact_no(long cotact_no) {
		this.cotact_no = cotact_no;
	}


	

}
