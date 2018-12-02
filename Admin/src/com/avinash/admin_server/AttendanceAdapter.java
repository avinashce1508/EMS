package com.avinash.admin_server;

import java.io.Serializable;

public class AttendanceAdapter implements Serializable{

	private static final long serialVersionUID = 5918721167374130837L;
	private String empID;

	
	public AttendanceAdapter(String empID) {
		
		this.empID = empID;
		
	}

	public String getEmpID() {
		return empID;
	}

}
