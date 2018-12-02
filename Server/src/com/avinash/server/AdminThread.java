package com.avinash.server;


import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import com.avinash.admin_server.AttendanceAdapter;
import com.avinash.admin_server.AttendanceTable;
import com.avinash.admin_server.DeleteRows;
import com.avinash.admin_server.EditInfo;
import com.avinash.admin_server.EmployeeInfoUpload;
import com.avinash.admin_server.EmployeeTable;
import com.avinash.leave.LeaveLetter;
import com.avinash.leave.LeavePermission;
import com.avinash.login.Acknowledgement;
import com.avinash.login.LoginVerification;


public class AdminThread implements Runnable {


	private Connection connection = null ;
	private ObjectOutputStream object_os = null;
	private ObjectInputStream object_is = null;
	private static int noOfRows;



	public AdminThread(Connection connection, ObjectOutputStream object_os, ObjectInputStream object_is) {
		this.connection = connection;
		this.object_os = object_os;
		this.object_is = object_is; 
	}
	@Override
	public void run() {
		LoginVerification verify_user = null;
		try {


			while(true) {
				Object object = object_is.readObject();
				if(object instanceof LoginVerification) {

					verify_user = (LoginVerification)object;

					if(verify_user.getUserId().equals("avinash") && verify_user.getPassword().equals("admin")) {

						Acknowledgement ack = new Acknowledgement(1);
						object_os.writeObject(ack);
					}else {
						Acknowledgement ack = new Acknowledgement(0);
						object_os.writeObject(ack);

					}


				}else if(object instanceof java.lang.String && object.equals("Attendance")) {

					PreparedStatement pstadd = connection.prepareStatement("INSERT INTO attendance VALUES(? , ? , ?");
					Statement statement_attend = connection.createStatement();
					ResultSet rsemp_attend = statement_attend.executeQuery("SELECT emp_id , emp_fname , emp_lname , dep_id , dept_name , designation From employeeView ");

					ArrayList<AttendanceTable> list_attend = new ArrayList<AttendanceTable>();
					while(rsemp_attend.next()) {
						AttendanceTable attendEmp = new  AttendanceTable(rsemp_attend.getString("emp_id") , rsemp_attend.getString("emp_fname") , rsemp_attend.getString("emp_lname"),rsemp_attend.getString("dep_id")  , rsemp_attend.getString("dept_name") , rsemp_attend.getString("designation") , false);
						pstadd.setString(1, rsemp_attend.getString("emp_id"));
						pstadd.setInt(2, 0);
						pstadd.setInt(3, 500);
						list_attend.add(attendEmp);
					}

					object_os.writeObject(list_attend);

				}else if(object instanceof java.lang.String && object.equals("Employee")) {

					Statement statement_employee = connection.createStatement();
					ResultSet rsemp_info = statement_employee.executeQuery("SELECT * From employeeView ");

					ArrayList<EmployeeTable> list_emp = new ArrayList<EmployeeTable>();
					while(rsemp_info.next()) {

						EmployeeTable emp = new  EmployeeTable(rsemp_info.getString("emp_id") , rsemp_info.getString("emp_fname") , rsemp_info.getString("emp_lname"),rsemp_info.getLong("contact_no") , rsemp_info.getString("dep_id")  , rsemp_info.getString("dept_name") , rsemp_info.getString("designation") , rsemp_info.getDate("doj"));
						list_emp.add(emp);
					}

					object_os.writeObject(list_emp);  


				}else if(object instanceof EmployeeInfoUpload){


					EmployeeInfoUpload empupload = (EmployeeInfoUpload)object;

					PreparedStatement pstemp = connection.prepareStatement("INSERT INTO employee VALUES( ? , ? , ? ,? , ? , ? , ? ,? )");
					pstemp.setString(1, empupload.getEmp_id());
					pstemp.setString(2, empupload.getFname());
					pstemp.setString(3, empupload.getLname());
					pstemp.setDate(4, Date.valueOf(empupload.getDob()));
					pstemp.setString(5, empupload.getGender());
					pstemp.setString(6, empupload.getAddress());
					pstemp.setLong(7, empupload.getContact_no());
					FileInputStream fis;

					File imgfile = new File("Image.txt");
					Path path=Paths.get(imgfile.getAbsolutePath());
					Files.write(path,empupload.getByte_array_of_images());
					System.out.println(empupload.getByte_array_of_images());
					fis=new FileInputStream(imgfile);
					pstemp.setBinaryStream(8, fis);

					int rowsUpdatedOf_employee = pstemp.executeUpdate();
					System.out.println(rowsUpdatedOf_employee);

					PreparedStatement pstdept = connection.prepareStatement("INSERT INTO department VALUES(? , ? , ? , ? , ? )");
					pstdept.setString(1, empupload.getEmp_id());
					pstdept.setString(2, empupload.getDept_id());
					pstdept.setString(3, empupload.getDept_name());
					pstdept.setDate(4, Date.valueOf(empupload.getDoj()) );
					pstdept.setString(5, empupload.getDesignation());

					int rowsUpdatedOf_department =pstdept.executeUpdate();


					PreparedStatement pstlogin = connection.prepareStatement("INSERT INTO login VALUES(?  , ? )");
					pstlogin.setString(1, empupload.getEmp_id());
					pstlogin.setString(2, empupload.getPassword());

					int rowsUpdatedOf_login =pstlogin.executeUpdate();

					PreparedStatement pstattendance = connection.prepareStatement("INSERT INTO attendance VALUES(?  , ?  , ?)");
					pstattendance.setString(1, empupload.getEmp_id());
					pstattendance.setInt(2 , 0);
					pstattendance.setInt(3, empupload.getBasic_salary());

					int rowsUpdatedOf_attendance =pstattendance.executeUpdate();


					if(rowsUpdatedOf_employee >0 && rowsUpdatedOf_department >0 && rowsUpdatedOf_login > 0 && rowsUpdatedOf_attendance > 0) {

						Acknowledgement ack = new Acknowledgement(1);
						object_os.writeObject(ack);
					}else {

						PreparedStatement pstdeletelogin = connection.prepareStatement("DELETE FROM login WHERE emp_id = ?");
						PreparedStatement pstdeleteEmployee = connection.prepareStatement("DELETE FROM employee WHERE emp_id = ?");
						PreparedStatement pstdeletedept = connection.prepareStatement("DELETE FROM employee WHERE emp_id = ?");
						PreparedStatement pstdeleteattendance = connection.prepareStatement("DELETE FROM attendance WHERE emp_id = ?");

						pstdeletedept.setString(1, empupload.getEmp_id());
						pstdeleteEmployee.setString(1, empupload.getEmp_id());
						pstdeletelogin.setString(1, empupload.getEmp_id());
						pstdeleteattendance.setString(1, empupload.getEmp_id());

						pstdeletedept.executeUpdate();
						pstdeleteEmployee.executeUpdate();
						pstdeletelogin.executeUpdate();
						pstdeleteattendance.executeUpdate();

						Acknowledgement ack = new Acknowledgement(0);
						object_os.writeObject(ack);
					}

				}else if(object instanceof java.util.Vector){

					Vector<AttendanceAdapter> list =(Vector<AttendanceAdapter>) object;

					PreparedStatement pstupdate = connection.prepareStatement("UPDATE attendance SET total_days  = ? WHERE emp_id = ? ");
					PreparedStatement pstselect = connection.prepareStatement("SELECT total_days FROM attendance WHERE emp_id = ?");

					for(int i = 0 ; i < list.size() ; i++) {

						pstselect.setString(1, list.get(i).getEmpID());
						ResultSet rs = pstselect.executeQuery();

						while(rs.next()) {

							int countForDays = rs.getInt("total_days");
							pstupdate.setInt(1, countForDays+1);
							pstupdate.setString(2, list.get(i).getEmpID());
							noOfRows = pstupdate.executeUpdate();

						}

						if(noOfRows > 0) {

							Acknowledgement ack = new Acknowledgement(1);
							object_os.writeObject(ack);

						}else {
							Acknowledgement ack = new Acknowledgement(0);
							object_os.writeObject(ack);
						}
					}
				}else if(object instanceof DeleteRows){

					DeleteRows deleteEntry = (DeleteRows)object;
					PreparedStatement pst_delete = connection.prepareStatement("DELETE FROM department WHERE emp_id = ?");
					PreparedStatement pst_delete1 = connection.prepareStatement("DELETE FROM attendance WHERE emp_id = ?");
					PreparedStatement pst_delete2 = connection.prepareStatement("DELETE FROM login WHERE emp_id = ?");
					PreparedStatement pst_delete3 = connection.prepareStatement("DELETE FROM employee WHERE emp_id = ?");

					pst_delete.setString(1,deleteEntry.getEmp_id());
					pst_delete1.setString(1,deleteEntry.getEmp_id());
					pst_delete2.setString(1,deleteEntry.getEmp_id());
					pst_delete3.setString(1,deleteEntry.getEmp_id());
					pst_delete.executeUpdate();
					pst_delete1.executeUpdate();
					pst_delete2.executeUpdate();
					noOfRows  = pst_delete3.executeUpdate();
					System.out.println(noOfRows);
					if(noOfRows > 0) {

						Acknowledgement ack = new Acknowledgement(1);
						object_os.writeObject(ack);

					}else {
						Acknowledgement ack = new Acknowledgement(0);
						object_os.writeObject(ack);
					}
				}else if(object instanceof EditInfo) {

					EditInfo edit_emp_info = (EditInfo)object;
					System.out.println(edit_emp_info.getClass().getName());
					PreparedStatement pst_edit_emp_info = connection.prepareStatement("UPDATE employee SET emp_id = ? , emp_fname = ? , emp_lname = ? , contact_no = ? ");

					pst_edit_emp_info.setString(1, edit_emp_info.getEmp_id());
					pst_edit_emp_info.setString(2, edit_emp_info.getEmp_name().substring(0, edit_emp_info.getEmp_name().indexOf(' ')));
					pst_edit_emp_info.setString(3, edit_emp_info.getEmp_name().substring(edit_emp_info.getEmp_name().indexOf(' ')));
					pst_edit_emp_info.setLong(4, edit_emp_info.getCotact_no());

					int noOfRowsUpdatedOfEmployee = pst_edit_emp_info.executeUpdate();


					PreparedStatement pst_edit_dept_info = connection.prepareStatement("UPDATE department SET emp_id = ? , dep_id = ? , dept_name = ? , designation = ? ");

					pst_edit_dept_info.setString(1, edit_emp_info.getEmp_id());
					pst_edit_dept_info.setString(2, edit_emp_info.getEmp_dept());
					pst_edit_dept_info.setString(3, edit_emp_info.getEmp_dept_name());
					pst_edit_dept_info.setString(4, edit_emp_info.getEmp_designation());

					int noOfRowsUpdatedOfDepartment = pst_edit_dept_info.executeUpdate();

					if(noOfRowsUpdatedOfEmployee > 0 && noOfRowsUpdatedOfDepartment > 0) {
						Acknowledgement ack = new Acknowledgement(1);
						object_os.writeObject(ack);
					}else {
						Acknowledgement ack = new Acknowledgement(0);
						object_os.writeObject(ack);
					}

				}else if(object instanceof java.lang.String && object.equals("LeaveInfo")) {


					Statement statement_leave = connection.createStatement();
					ResultSet rsemp_leave = statement_leave.executeQuery("SELECT emp_id , emp_name ,leave_type , start_date , end_date , reason  FROM leaveLetter");

					ArrayList<LeaveLetter> list_leave = new ArrayList<LeaveLetter>();
					while(rsemp_leave.next()) {
						LeaveLetter leave= new LeaveLetter(rsemp_leave.getString("emp_id"), rsemp_leave.getString("emp_name") ,rsemp_leave.getString("leave_type"),
								String.valueOf(rsemp_leave.getDate("start_date")),String.valueOf(rsemp_leave.getDate("end_date")),rsemp_leave.getString("reason"));
						list_leave.add(leave);
					}

					object_os.writeObject(list_leave);

				}else if(object instanceof LeavePermission) {

					PreparedStatement pstinsert = connection.prepareStatement("INSERT INTO leavePermit VALUES(? ,? )");

					LeavePermission leave_permit = (LeavePermission)object;

					pstinsert.setString(1,leave_permit.getEmp_id());
					pstinsert.setString(2,leave_permit.getStatus());

					int noOfRows = pstinsert.executeUpdate();
					if(noOfRows > 0) {
						Acknowledgement ack = new Acknowledgement(1);
						object_os.writeObject(ack);
					}else {
						Acknowledgement ack = new Acknowledgement(0);
						object_os.writeObject(ack);
					}
				}					
			}	       
		}catch(Exception e) {
			e.printStackTrace();
			return;
		}	
	}
}
