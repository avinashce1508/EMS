package com.avinash.server;


import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.avinash.employee_server.Attendance;
import com.avinash.employee_server.Department;
import com.avinash.employee_server.EmployeeInfo;
import com.avinash.employee_server.EmployeeView;
import com.avinash.employee_server.ResetPassword;
import com.avinash.leave.LeaveLetter;
import com.avinash.leave.LeavePermission;
import com.avinash.login.Acknowledgement;
import com.avinash.login.LoginVerification;

public class EmployeeThread implements Runnable{
    
	private Connection connection = null;
    private ObjectOutputStream object_os = null;
    private ObjectInputStream object_is = null;
    public EmployeeThread( Connection connection , ObjectOutputStream object_os ,ObjectInputStream object_is ) {
    	this.connection = connection;
    	this.object_os  = object_os;
    	this.object_is = object_is;
    }// end of constructor
    
	public void run() {
		 try {
			    LoginVerification verify_user = null;
			    ResetPassword update_password = null;
			 	
			 		try {
			 			   while(true) {
			 				  // reading the object passed over the socket
				 			    Object object = object_is.readObject();
				 			    if(object instanceof LoginVerification) {
				 				    verify_user = (LoginVerification)object;
				 					PreparedStatement statement = connection.prepareStatement("SELECT * FROM login WHERE emp_id = ? AND password = ?");
					 				statement.setString(1, verify_user.getUserId());
					 				statement.setString(2, verify_user.getPassword());
					 				ResultSet resultSet = statement.executeQuery();
					 				
					 				if(resultSet.next()) {
					 					Acknowledgement ack = new Acknowledgement(1);
					 					object_os.writeObject(ack);
					 					object_os.reset();
					 				}else {
					 					Acknowledgement ack = new Acknowledgement(0);
					 					object_os.writeObject(ack);
					 					object_os.reset();
					 				}
				 			    }else if(object instanceof ResetPassword) {
				 			    	
				 			    	update_password = (ResetPassword)object;
				 			    	
				 			    	PreparedStatement statement = connection.prepareStatement("UPDATE login SET password = ? WHERE emp_id = ?");
					 				statement.setString(1,update_password.getPassword() );
					 				statement.setString(2, update_password.getUserId());
					 				
					 				int RowsUpdated = statement.executeUpdate();
					 				
					 				if(RowsUpdated > 0 ) {
					 					Acknowledgement ack = new Acknowledgement(1);
					 					object_os.writeObject(ack);
					 					object_os.reset();
					 				}else {
					 					Acknowledgement ack = new Acknowledgement(0);
					 					object_os.writeObject(ack);
					 					object_os.reset();
					 				}
				 			    	
				 			    }else if (object instanceof java.lang.String && object.equals("Employee Details")) {
				 			    	PreparedStatement pstemp = connection.prepareStatement("SELECT * FROM employee WHERE emp_id = ?");
					 				pstemp.setString(1 , verify_user.getUserId());
					 				
					 				ResultSet rsemp = pstemp.executeQuery();
					 				
					 				while(rsemp.next())
					 				{
					 		          
					 		          InputStream is = rsemp.getBinaryStream("emp_photo");
					 		          System.out.println(is);
					 		          byte[] byte_array_of_image = new byte[is.available()];
					 		          is.read(byte_array_of_image);	
					 		          System.out.println(byte_array_of_image);
					 				  EmployeeInfo empinfo = new EmployeeInfo(rsemp.getString("emp_id") , rsemp.getString("emp_fname"), rsemp.getString("emp_lname") ,rsemp.getDate("dob"), rsemp.getString("sex"),rsemp.getString("address"),rsemp.getLong("contact_no") , byte_array_of_image );
					 				  object_os.writeObject(empinfo);
					 				  object_os.reset();
					 				}
					 				
					 				PreparedStatement pstdept = connection.prepareStatement("SELECT * FROM department WHERE emp_id = ?");
					 				pstdept.setString(1 , verify_user.getUserId());
					 				
					 				ResultSet rsdept = pstdept.executeQuery();
					 				
					 				if(rsdept.next())
					 				{
					 				  Department deptinfo = new Department(rsdept.getString("dep_id") , rsdept.getString("dept_name") , rsdept.getString("designation")  , rsdept.getDate("doj"));
					 				  object_os.writeObject(deptinfo);
					 				 object_os.reset();
					 				}
		                            
				 			    }else if (object instanceof java.lang.String && object.equals("Salary Details")) {
				 			    	
					 				PreparedStatement pstemp_view = connection.prepareStatement("SELECT * FROM employeeView WHERE emp_id = ?");
					 				pstemp_view.setString(1, verify_user.getUserId());
					 				
					 				ResultSet rsemp_view = pstemp_view.executeQuery();
					 				
					 				if(rsemp_view.next()) {
					 					
					 					EmployeeView empview = new EmployeeView(rsemp_view.getString("emp_id") , rsemp_view.getString("emp_fname"), rsemp_view.getString("emp_lname") , rsemp_view.getString("dep_id") , rsemp_view.getString("dept_name"),String.valueOf(rsemp_view.getDate("doj")) , rsemp_view.getString("designation"));
					 					object_os.writeObject(empview);
					 										 					
					 				}
					 				
					 				PreparedStatement pstattend = connection.prepareStatement("SELECT * FROM attendance WHERE emp_id = ?");
					 				pstattend.setString(1, verify_user.getUserId());
					 				
					 				ResultSet rsattend = pstattend.executeQuery();
					 				
					 				if(rsattend.next()) {
					 					
					 					Attendance attendance = new Attendance(rsattend.getInt("total_days") , rsattend.getInt("basic_salary"));
					 					object_os.writeObject(attendance);
					 					//object_os.reset();
					 				}
					
				 			    }else if(object instanceof LeaveLetter) {
				 			        
				 			    	LeaveLetter leave_letter = (LeaveLetter)object;
				 			    	
				 			    	PreparedStatement pstleave = connection.prepareStatement("INSERT INTO leaveLetter VALUES(? , ? , ? , ? , ? , ? )");
				 			    	
				 			    	pstleave.setString(1, leave_letter.getEmp_id()); 
				 			    	pstleave.setString(2, leave_letter.getName()); 
				 			    	pstleave.setString(3, leave_letter.getLeave_type()); 
				 			    	pstleave.setDate(4, Date.valueOf(leave_letter.getStart_date())); 
				 			    	pstleave.setDate(5, Date.valueOf(leave_letter.getEnd_date())); 
				 			    	pstleave.setString(6, leave_letter.getReason()); 
				 			    	
				 			    	 
				 			    	int noOfRows = pstleave.executeUpdate();
				 			    	if(noOfRows > 0) {
				 			    		Acknowledgement ack = new Acknowledgement(1);
				 			    		object_os.writeObject(ack);
				 			    		object_os.reset();
				 			    	}else {
				 			    		Acknowledgement ack = new Acknowledgement(0);
				 			    		object_os.writeObject(ack);
				 			    		object_os.reset();
				 			    	}
				 			    	
				 			    }else if(object instanceof java.lang.String && object.equals("status")) {
				 			    	
				 			    	PreparedStatement pstleave = connection.prepareStatement("SELECT * FROM leavePermit WHERE emp_id = ?");
					 				pstleave.setString(1, verify_user.getUserId());
					 				ResultSet rs = pstleave.executeQuery(); 
					 				String emp_id = null;
					 				String status = null;
				 			    	if(rs.next()) {
					 				  emp_id = rs.getString("emp_id");
				 			    	  status =  rs.getString("permit");
				 			    	}
				 			    	LeavePermission leave_permit = new LeavePermission(emp_id, status);
				 			    	object_os.writeObject(leave_permit);
				 			    	
				 			    }
				 			    
			 			   }
			 	
			 			}catch(ClassNotFoundException cnfe) {
			 					cnfe.printStackTrace();
			 			}catch(SQLException sqle) {
			 				sqle.printStackTrace();
			 			}
			 		
		 }catch(IOException e) {
			 
			return;
			 
		 }
		
		
	}//end of run method
	
}// end of class MultiThreadedServer

