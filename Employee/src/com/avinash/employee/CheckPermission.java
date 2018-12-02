package com.avinash.employee;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.avinash.leave.LeavePermission;

public class CheckPermission {
	private LeavePermission leave;
	public CheckPermission(ObjectOutputStream object_os , ObjectInputStream object_is) throws IOException, ClassNotFoundException {
		
		object_os.writeObject(new String("status"));
	    leave = (LeavePermission)object_is.readObject();
	 
	}
	public LeavePermission getLeave() {
		return leave;
	}
	
	

}
