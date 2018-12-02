package com.avinash.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

class RunServer{
	
	protected ServerSocket serversock = null;
	private int portNumber = 2337;
	private Connection connection ;
	public void runServer() {
		JOptionPane.showMessageDialog(null, "Server is active","Server", JOptionPane.INFORMATION_MESSAGE);
	 try {	
	     serversock = new ServerSocket(portNumber);	
	   }catch(IOException e) {
		  System.out.println(e.getMessage());
	   }// end of try-catch block
	  
	 try {
		  
		     try {
		    	 // establish the connection with database
		    	Class.forName(JDBC.CLASSPATH);
				connection  = DriverManager.getConnection(JDBC.URL, JDBC.USERNAME, JDBC.PASSWORD);
			 
		     }catch(ClassNotFoundException cnfe) {
			    	
		    	 System.out.println("class not found " + cnfe.getMessage());
			 
		     }catch(SQLException sqle) {
			    	sqle.printStackTrace();
			 }
		while(true) {
		   
			// waiting for the client request and accept when it will come
		  	Socket clientsocket = serversock.accept();
		  
		    ObjectOutputStream object_os = new ObjectOutputStream(clientsocket.getOutputStream());
		 	ObjectInputStream object_is = new ObjectInputStream(clientsocket.getInputStream());
		 	
		 	String role = (String)object_is.readObject();
		 //initialize new thread to every incoming client
		   if(role.equals("Employee")){	
		 	   
			   Thread empThread = new Thread(new EmployeeThread( connection , object_os , object_is));
			   empThread.start();
			   
		   }else{
			   
			   Thread adminThread = new Thread(new AdminThread(connection , object_os , object_is));
			   adminThread.start();
		   }
		 }
	   }catch(IOException | ClassNotFoundException e) {
			 System.out.println(e.getMessage());
		}
	   finally {
			try {
			if(serversock!= null) {
				serversock.close();
			}
			}catch(IOException e) {
				e.getCause();
			}// end of try-catch block
		
	 }// end of finally block 
	  
	}// end of runServer method
	
}

public class Server {

	public static void main(String[] args) {
		
		RunServer run_server = new RunServer();
		run_server.runServer();
	}// end of main
	
}// end of class Server
