package com.avinash.login;
import java.io.Serializable;

public class LoginVerification implements Serializable {
	    
	    /**
	 * 
	 */
	private static final long serialVersionUID = -2611715573102868820L;
		private String userid ;
	    private String password;
		
	    
		public LoginVerification(String userid, String password) {
			super();
			
			this.userid = userid;
			this.password = password;
		}
		
		public String getUserId() {
			return userid;
		}

		public String getPassword() {
			return password;
		}
}
