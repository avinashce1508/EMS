package com.avinash.employee;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.file.Path;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import com.avinash.dimen.Dimen;
import com.avinash.employee_server.Department;
import com.avinash.employee_server.EmployeeInfo;

public class EmployeeDetails extends JFrame {

	private Socket socket = null;
	private ObjectInputStream object_is = null;
	private ObjectOutputStream object_os = null;
	private JPanel contentPane , headerPane , panel , maincontent;
	private JLabel logo_image , lblHeader;
	private JButton btnHome , btnLeaveApplication , btnLogOut;
	private JLabel lblDesignation;
	private JLabel lblDoj;
	private JLabel lblDeptName;
	private JLabel lblDeptId;
	private JLabel lblContactNo;
	private JLabel lblDob;
	private JLabel lblSex;
	private JLabel lblAddress;
	private JLabel lblName;
	private JLabel lblEmpId;
	private JButton btnGetSalary;
	private JLabel lblPhoto;
	private EmployeeInfo empinfo;
  
	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public EmployeeDetails(Socket socket, ObjectOutputStream object_os, ObjectInputStream object_is) throws IOException {
		this.socket = socket;
		this.object_is = object_is;
		this.object_os = object_os;
		create_New_Frame();
	}
	public void create_New_Frame() throws IOException {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0 ,0 , Dimen.SCREEN_WIDTH , Dimen.SCREEN_HEIGHT);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.setBackground(Color.WHITE);
		contentPane.setAutoscrolls(true);
		setVisible(true);
		setContentPane(contentPane);
		
		headerPane = new JPanel();
		headerPane.setBounds(0 ,  0 , Dimen.SCREEN_WIDTH , 120);
		headerPane.setBackground(Color.DARK_GRAY);
		headerPane.setLayout(null);
		contentPane.add(headerPane);
		
		logo_image = new JLabel("");
		logo_image.setBounds(20 , 10 , 100 , 100);
		headerPane.add(logo_image);
		logo_image.setIcon(new ImageIcon("/home/avinash/Projects/Employee/images/comlogo.png"));
		
		lblHeader = new JLabel("<HTML><CENTER><B><FONT>EMPLOYEE PROFILE</FONT></B></CENTER>	</HTML>");
		lblHeader.setBounds(540 , 35 , 400 , 50 );
		lblHeader.setFont(new Font("Arial" , Font.BOLD , 25));
		lblHeader.setForeground(new Color(255, 191, 0));
		headerPane.add(lblHeader);
		
		btnHome = new JButton("<HTML><B><FONT>HOME</FONT></B></HTML>");
		btnHome.setBounds(1000 , 50 , 100 , 30);
		btnHome.setFocusable(false);
		btnHome.setBorder(null);
		btnHome.setBackground(Color.DARK_GRAY);
		btnHome.setForeground(new Color(255, 191, 0));
		btnHome.setFocusPainted(false);
		headerPane.add(btnHome);
		
		btnHome.addMouseListener(new MouseAdapter() {
			
			public void mousePressed(MouseEvent e) {
				btnHome.setBackground(new Color(255, 191, 0));
			}
			public void mouseEntered(MouseEvent e) {
				btnHome.setBackground(new Color(255, 191, 0));
				btnHome.setForeground(Color.white);
			}
			public void mouseExited(MouseEvent e) {
				btnHome.setBackground(Color.DARK_GRAY);
				btnHome.setForeground(new Color(255, 191, 0));
			}
			public void mouseClicked(MouseEvent e) {
				btnHome.setBackground(new Color(255, 191, 0));
			}
		});
		btnHome.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				btnHome_OnClicked_Action_Performed();
			}
		});
		
		btnLeaveApplication = new JButton("<HTML><CENTER><B><FONT>LEAVE</FONT></B></CENTER></HTML>");
		btnLeaveApplication.setBounds(1110 , 50 , 100 , 30);
		btnLeaveApplication.setFocusable(false);
		btnLeaveApplication.setBorder(null);
		btnLeaveApplication.setBackground(Color.DARK_GRAY);
		btnLeaveApplication.setForeground(new Color(255, 191, 0));
		headerPane.add(btnLeaveApplication);
		
		btnLeaveApplication.addMouseListener(new MouseAdapter() {
			
			public void mouseEntered(MouseEvent e) {
				btnLeaveApplication.setBackground(new Color(255, 191, 0));
				btnLeaveApplication.setForeground(Color.white);
			}
			public void mouseExited(MouseEvent e) {
				btnLeaveApplication.setBackground(Color.DARK_GRAY);
				btnLeaveApplication.setForeground(new Color(255, 191, 0));
			}
		});
		
		btnLeaveApplication.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					btnLeaveApplication_OnClicked_Action_Performed();
				} catch (ClassNotFoundException | IOException e) {
					 
					e.printStackTrace();
				} 
				 
			}
		});
		
		btnLogOut =  new JButton("<HTML><CENTER><B><FONT>LOGOUT</FONT></B></CENTER></HTML>");
		btnLogOut.setBounds(1220 , 50 , 100 , 30);
		btnLogOut.setFocusable(false);
		btnLogOut.setBorder(null);
		btnLogOut.setBackground(Color.DARK_GRAY);
		btnLogOut.setForeground(new Color(255, 191, 0));
		headerPane.add(btnLogOut);
		
		btnLogOut.addMouseListener(new MouseAdapter() {
			
			public void mouseEntered(MouseEvent e) {
				btnLogOut.setBackground(new Color(255, 191, 0));
				btnLogOut.setForeground(Color.white);
			}
			public void mouseExited(MouseEvent e) {
				btnLogOut.setBackground(Color.DARK_GRAY);
				btnLogOut.setForeground(new Color(255, 191, 0));
			}
		});
		
		btnLogOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				 try {
					btnLogOut_OnClicked_Action_Performed();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
		});
		
		maincontent = new JPanel();
		maincontent.setLayout(null);
		maincontent.setBounds(0 , 120 , Dimen.SCREEN_WIDTH , Dimen.SCREEN_HEIGHT-125);
		maincontent.setBackground(Color.WHITE);
		contentPane.add(maincontent);
		// adding components to panel 
		
		 panel = new JPanel();
		 panel.setBounds(233, 30, 900, 450);
		 panel.setBorder(new MatteBorder(1 ,1 ,1 , 1, Color.gray));
		 panel.setBackground(new Color(1.0f , 1.0f , 1.0f , 0.6f ));
		// panel.setOpaque(false);
		 maincontent.add(panel);
		 panel.setLayout(null);
		 
		 
		 // personal information
		 

		 lblPhoto = new JLabel("");
		 lblPhoto.setBounds(735, 45, 100, 100);
		 lblPhoto.setBorder(new MatteBorder(1 , 1 ,1 , 1, Color.gray));
		 panel.add(lblPhoto);
		 
		 lblEmpId = new JLabel("Emp ID :");
		 lblEmpId.setBounds(50, 50, 199, 15);
		 panel.add(lblEmpId);
		 
		 lblName = new JLabel("Name  :");
		 lblName.setBounds(50, 80, 210, 15);
		 panel.add(lblName);
		 
		 lblAddress = new JLabel("Address :");
		 lblAddress.setBounds(50, 140, 199, 15);
		 panel.add(lblAddress);
		
		 lblSex = new JLabel("sex :");
		 lblSex.setBounds(50, 110, 256, 15);
		 panel.add(lblSex);
		 
		 lblDob = new JLabel("DOB :");
		 lblDob.setBounds(250, 110, 215, 15);
		 panel.add(lblDob);
		 
		 lblContactNo = new JLabel("Contact No :");
		 lblContactNo.setBounds(50, 170, 222, 15);
		 panel.add(lblContactNo);
		 
		 // department information 
		 
		 lblDeptId = new JLabel("Dept ID :");
		 lblDeptId.setBounds(50, 200, 187, 15);
		 panel.add(lblDeptId);
		 
		 lblDeptName = new JLabel("Dept Name :");
		 lblDeptName.setBounds(50, 230, 222, 15);
		 panel.add(lblDeptName);
		 

		 lblDoj = new JLabel("DOJ :");
		 lblDoj.setBounds(50, 260, 199, 15);
		 panel.add(lblDoj);
		 
		 lblDesignation = new JLabel("Designation : ");
		 lblDesignation.setBounds(250, 260, 222, 15);
		 panel.add(lblDesignation);
		 
		 btnGetSalary = new JButton("<HTML><B>VIEW SALARY</B></HTML>");
		 btnGetSalary.setBounds(310, 342, 300, 35);
		 btnGetSalary.setFocusable(false);
		 btnGetSalary.setBackground(new Color(230, 0, 57));
		 btnGetSalary.setBorder(null);
		 btnGetSalary.setForeground(Color.white);
		 panel.add(btnGetSalary);
		 
		 btnGetSalary.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					btnGetSalary.setBackground(new Color(179, 0, 45));
				}
				public void mouseExited(MouseEvent e) {
					btnGetSalary.setBackground(new Color(230, 0, 57));
				}
				
			}); // end of Login button mouse listener
			
		 btnGetSalary.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		
		 		btnGetSalary_OnClicked_Action_Performed();
		 	}
		 });
		 
		get_Data_From_Server();
		
	}
 
private void btnHome_OnClicked_Action_Performed() {
	  
	  maincontent.removeAll();
	  maincontent.add(panel);
	  maincontent.repaint();
	  maincontent.revalidate();
	  
  }
  
  private void btnLeaveApplication_OnClicked_Action_Performed() throws ClassNotFoundException, IOException {
	  
	  maincontent.removeAll();
	  maincontent.add( new LeaveApplication(object_os , object_is,empinfo.getEmp_id(),empinfo.getEmp_fname() , empinfo.getEmp_lname()));
	  maincontent.repaint();
	  maincontent.revalidate();
	  
  }
  private void btnLogOut_OnClicked_Action_Performed() throws IOException {
	 
	   dispose();
  }
  
  private void btnGetSalary_OnClicked_Action_Performed() {
	 
	   new SalaryDetails( object_os , object_is);
	  
  }
  private void get_Data_From_Server() throws IOException {
	  
	  // sending string over the socket
	  object_os.writeObject(new String("Employee Details"));
	 
	  try {
		   
		      	 empinfo = (EmployeeInfo)object_is.readObject();
		     
		         lblEmpId.setText("Emp ID : " + empinfo.getEmp_id());
		         lblName.setText("Name : " + empinfo.getEmp_fname() + " " + empinfo.getEmp_lname());
		         lblAddress.setText("Address : " + empinfo.getAddress());
		         lblSex.setText("Sex : " + empinfo.getSex());
		         lblDob.setText("DOB : " + String.valueOf(empinfo.getDob()));
		         lblContactNo.setText("Contact No : " + String.valueOf(empinfo.getCotact_no()));
		         
		         File emp_photo = new File("photo.txt");
		         FileOutputStream fos = new FileOutputStream(emp_photo);
		         System.out.println(empinfo.getByte_array_of_image());
		         fos.write(empinfo.getByte_array_of_image(), 0 , empinfo.getByte_array_of_image().length);
		         System.out.println(emp_photo.getAbsolutePath());
		         
		         lblPhoto.setIcon(new ImageIcon(emp_photo.getAbsolutePath()));
		        
		     
		        Department deptinfo = (Department)object_is.readObject();
			 
		        lblDeptId.setText("Dept ID : " + deptinfo.getDept_id());
		        lblDeptName.setText("Dept Name : " + deptinfo.getDept_name());
		        lblDoj.setText("DOJ :" + deptinfo.getDoj());
		        lblDesignation.setText("Designation : " + deptinfo.getDesignation());
			
		     
				
	} catch (ClassNotFoundException | IOException  e) {
		
		e.printStackTrace();
	}
    	
  }
    
    
    
    
}