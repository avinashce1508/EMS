package com.avinash.login;
import java.io.Serializable;

public class Acknowledgement implements Serializable{
 
	private static final long serialVersionUID = 2440678207069813529L;
	private int flag;
	 
	public Acknowledgement(int flag) {
		this.flag = flag;
	}
	
	public int getFlag() {
		return flag;
	}
}
