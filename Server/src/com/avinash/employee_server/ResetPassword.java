package com.avinash.employee_server;
import java.io.Serializable;

public class ResetPassword implements Serializable{
	
	private static final long serialVersionUID = 4757972764919807795L;
	private String userId;
	private String password;
	private String confirmPassword;
	
	public ResetPassword(String userId, String password, String confirmPassword) {
		super();
		this.userId = userId;
		this.password = password;
		this.confirmPassword = confirmPassword;
	}

	public String getUserId() {
		return userId;
	}

	public String getPassword() {
		return password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}
	
   
}
