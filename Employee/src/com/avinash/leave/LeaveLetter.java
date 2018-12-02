package com.avinash.leave;

import java.io.Serializable;

public class LeaveLetter implements Serializable {

	private static final long serialVersionUID = -54493822149896313L;
	private String emp_id;
	private String name;
	private String leave_type;
	private String start_date;
	private String end_date;
	private String reason;

	public LeaveLetter(String emp_id, String name, String leave_type, String start_date, String end_date, String reason ) {
		super();
		this.emp_id = emp_id;
		this.name = name;
		this.leave_type = leave_type;
		this.start_date = start_date;
		this.end_date = end_date;
		this.reason = reason;
		
	}
	
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLeave_type() {
		return leave_type;
	}
	public void setLeave_type(String leave_type) {
		this.leave_type = leave_type;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
