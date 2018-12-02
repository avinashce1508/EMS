package com.avinash.leave;
import java.io.Serializable;

public class LeavePermission implements Serializable {

	private static final long serialVersionUID = 4915369796743078952L;
	private String emp_id;
	private String status = "pending";
	
	public LeavePermission(String emp_id, String status) {
		super();
		this.emp_id = emp_id;
		this.status = status;
	}

	public String getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
