package com.avinash.admin_server;
import java.io.Serializable;

public class DeleteRows implements Serializable {

	
	private static final long serialVersionUID = -278884608053498870L;
	private String emp_id;
	public DeleteRows(String emp_id) {
	   this.emp_id = emp_id;	
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
