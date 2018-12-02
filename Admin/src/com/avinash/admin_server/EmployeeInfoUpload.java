package com.avinash.admin_server;
import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class EmployeeInfoUpload implements Serializable {
	
	
	private static final long serialVersionUID = -3042869824120063565L;
	
	private String fname;
	private String lname;
	private String gender;
	private String dob;
	private String address;
	private Long contact_no;
	private String dept_id;
	private String dept_name;
	private String doj;
	private String designation;
	private String emp_id;
	private String password;
	private int basic_salary;
	private byte[] byte_array_of_images;
	
	public EmployeeInfoUpload(String fname, String lname, String gender, String dob, String address, Long contact_no, 
			String dept_id, String dept_name, String doj, String designation, String emp_id, String password , int basic_salary , byte[] byte_array) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.gender = gender;
		this.dob = dob;
		this.address = address;
		this.contact_no = contact_no;
		this.dept_id = dept_id;
		this.dept_name = dept_name;
		this.doj = doj;
		this.designation = designation;
		this.emp_id = emp_id;
		this.password = password;
		this.basic_salary = basic_salary;
		this.byte_array_of_images = byte_array;
	}
	public byte[] getByte_array_of_images() {
		return byte_array_of_images;
	}
	public void setByte_array_of_images(byte[] byte_array_of_images) {
		this.byte_array_of_images = byte_array_of_images;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public int getBasic_salary() {
		return basic_salary;
	}
	public void setBasic_salary(int basic_salary) {
		this.basic_salary = basic_salary;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String getDoj() {
		return doj;
	}
	public void setDoj(String doj) {
		this.doj = doj;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
	