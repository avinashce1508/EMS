package com.avinash.admin;
import java.awt.Color;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import com.avinash.dimen.Dimen;

public class Administrator extends JFrame {

	private JPanel contentPane , headerPane , sidePane , maincontentPane;
	private JButton btnHome , btnEmployee ,btnAddEmployee ,  btnAttendance , btnLeavePermission , btnLogOut;
    private Socket socket = null;
    private ObjectInputStream object_is = null;
    private ObjectOutputStream object_os = null;
	private JLabel logo_image;
	private JLabel lblHeader;
	
	public Administrator(Socket socket, ObjectOutputStream object_os, ObjectInputStream object_is) {
		  this.socket = socket;
		  this.object_os = object_os;
		  this.object_is = object_is;
		  create_Frame_For_Administrator();
	}
	/**
	 * Create the frame.
	 */
	public void  create_Frame_For_Administrator() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, Dimen.SCREEN_WIDTH, Dimen.SCREEN_HEIGHT);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setVisible(true);
		setContentPane(contentPane);
	
	    headerPane = new JPanel();
	    headerPane.setLayout(null);
	    headerPane.setBounds(0 , 0 , Dimen.SCREEN_WIDTH , 100);
	    headerPane.setBackground(Color.DARK_GRAY);
	    contentPane.add(headerPane);
	    
	    logo_image = new JLabel("");
		logo_image.setBounds(20 , 1 , 100 , 100);
		headerPane.add(logo_image);
		logo_image.setIcon(new ImageIcon("/home/avinash/Projects/Employee/images/comlogo.png"));
		

		lblHeader = new JLabel("<HTML><CENTER><B><FONT>ADMINISTRATOR</FONT></B></CENTER>	</HTML>");
		lblHeader.setBounds(540 , 35 , 400 , 50 );
		lblHeader.setFont(new Font("Arial" , Font.BOLD , 25));
		lblHeader.setForeground(new Color(255, 191, 0));
		headerPane.add(lblHeader);
		
	    sidePane = new JPanel();
	    sidePane.setLayout(new GridLayout(15 , 0));
	    sidePane.setBackground(Color.WHITE);
	    sidePane.setBounds(0 , 100 , 200 , Dimen.SCREEN_HEIGHT-120);
	    sidePane.setBorder(new MatteBorder(1 , 1 ,1 , 1 , Color.GRAY));
	    contentPane.add(sidePane);
	    
	    maincontentPane = new JPanel();
	    maincontentPane.setLayout(null);
	    maincontentPane.setBackground(Color.white);
	    maincontentPane.add(new HomePane());
	    maincontentPane.setBounds(200 , 100 , Dimen.SCREEN_WIDTH-200 , Dimen.SCREEN_HEIGHT-120);
	    contentPane.add(maincontentPane);
	    
	    btnHome = new JButton("<HTML><CENTER><B><FONT >HOME</FONT></B></CENTER></HTML>");
	    btnHome.setBounds(0, 120 , 200, 35);
	    btnHome.setBackground(new Color(230, 0, 57));
	    btnHome.setFocusable(false);
	    btnHome.setForeground(Color.WHITE);
	    btnHome.setBorder(new MatteBorder(1 , 1 , 1 , 1 , Color.WHITE));
		sidePane.add(btnHome);
		
		btnHome.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				btnHome.setBackground(new Color(179, 0, 45));
			}
			public void mouseExited(MouseEvent e) {
				btnHome.setBackground(new Color(230, 0, 57));
			}
			
		}); // end of Home button mouse listener
		
	 btnHome.addActionListener(new ActionListener() {

	   @Override
	    public void actionPerformed(ActionEvent event) {
				
	      btnHome_OnClick_Action_Performed();
				
		}
			
	  });// end of Home button action listener
		
      btnEmployee = new JButton("<HTML><CENTER><B><FONT >EMPLOYEE</FONT></B></CENTER></HTML>");
      btnEmployee.setBounds(0, 155 , 200, 35);
      btnEmployee.setBackground(new Color(230, 0, 57));
      btnEmployee.setFocusable(false);
      btnEmployee.setForeground(Color.WHITE);
      btnEmployee.setBorder(new MatteBorder(1 , 1 , 1 , 1 , Color.WHITE));
	  sidePane.add(btnEmployee);
			
	  btnEmployee.addMouseListener(new MouseAdapter() {
		  public void mouseEntered(MouseEvent e) {
			
			  btnEmployee.setBackground(new Color(179, 0, 45));
		  
		  }
				
		  public void mouseExited(MouseEvent e) {
			
			  btnEmployee.setBackground(new Color(230, 0, 57));
	    
		  }
				
	   }); // end of Home button mouse listener
			
	   btnEmployee.addActionListener(new ActionListener() {

			@Override
		    public void actionPerformed(ActionEvent event) {
				try {	
	    		btnEmployee_OnClick_Action_Performed();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
				
		});// end of Home button action listener
			
		btnAddEmployee = new JButton("<HTML><CENTER><B><FONT >ADD EMPLOYEE</FONT></B></CENTER></HTML>");
		btnAddEmployee.setBounds(0, 190 , 200, 35);
		btnAddEmployee.setBackground(new Color(230, 0, 57));
		btnAddEmployee.setFocusable(false);
		btnAddEmployee.setForeground(Color.WHITE);
		btnAddEmployee.setBorder(new MatteBorder(1 , 1 , 1 , 1 , Color.WHITE));
		sidePane.add(btnAddEmployee);
				
		btnAddEmployee.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				btnAddEmployee.setBackground(new Color(179, 0, 45));
			}
		
			public void mouseExited(MouseEvent e) {
				btnAddEmployee.setBackground(new Color(230, 0, 57));
			}
					
		}); // end of add employee button mouse listener
				
		btnAddEmployee.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
						
				btnAddEmployee_OnClick_Action_Performed();
						
			}
					
		});// end of add employee button action listener
		 
		btnAttendance = new JButton("<HTML><CENTER><B><FONT >ATTENDANCE</FONT></B></CENTER></HTML>");
		btnAttendance.setBounds(0, 225 , 200, 35);
		btnAttendance.setBackground(new Color(230, 0, 57));
		btnAttendance.setFocusable(false);
		btnAttendance.setForeground(Color.WHITE);
		btnAttendance.setBorder(new MatteBorder(1 , 1 , 1 , 1 , Color.WHITE));
		sidePane.add(btnAttendance);
					
		btnAttendance.addMouseListener(new MouseAdapter() {
			
			public void mouseEntered(MouseEvent e) {
				btnAttendance.setBackground(new Color(179, 0, 45));
			}
			public void mouseExited(MouseEvent e) {
				btnAttendance.setBackground(new Color(230, 0, 57));
			}
						
		}); // end of attendance button mouse listener
					
		btnAttendance.addActionListener(new ActionListener() {

						@Override
		   public void actionPerformed(ActionEvent event) {
				try {			
					btnAttendance_OnClick_Action_Performed();
				}catch(Exception e ) {
					e.printStackTrace();
				}
			}
						
		});// end of attendance button action listener
		
		btnLeavePermission = new JButton("<HTML><CENTER><B><FONT >LEAVE INBOX</FONT></B></CENTER></HTML>");
		btnLeavePermission.setBounds(0, 260 , 200, 35);
		btnLeavePermission.setBackground(new Color(230, 0, 57));
		btnLeavePermission.setFocusable(false);
		btnLeavePermission.setForeground(Color.WHITE);
		btnLeavePermission.setBorder(new MatteBorder(1 , 1 , 2 , 1 , Color.WHITE));
		sidePane.add(btnLeavePermission);
						
		btnLeavePermission.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				btnLeavePermission.setBackground(new Color(179, 0, 45));
			}
			public void mouseExited(MouseEvent e) {
				btnLeavePermission.setBackground(new Color(230, 0, 57));
			}
							
		}); // end of attendance button mouse listener
						
		btnLeavePermission.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
								
		    	try {
					btnLeavePermission_OnClick_Action_Performed();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
								
			}
							
		});// end of leave inbox button action listener
	    
		btnLogOut =  new JButton("<HTML><CENTER><B><FONT>LOGOUT</FONT></B></CENTER></HTML>");
		btnLogOut.setBounds(1230 , 40 , 100 , 30);
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
	  }
	
    private void btnHome_OnClick_Action_Performed() {
    	
    	maincontentPane.removeAll();
    	maincontentPane.add(new HomePane());
    	maincontentPane.repaint();
    	maincontentPane.revalidate();
    	
    }
    
    private void btnEmployee_OnClick_Action_Performed() throws IOException {
    	 object_os.writeObject(new String("Employee"));
    	 maincontentPane.removeAll();
 	     maincontentPane.add(new EmployeePane(object_os , object_is));
 	     maincontentPane.repaint();
 	     maincontentPane.revalidate();
    }
    
    public void btnAddEmployee_OnClick_Action_Performed() {
 	     maincontentPane.removeAll();
 	     maincontentPane.add(new AddEmployeePane(object_os , object_is));
 	     maincontentPane.repaint();
 	     maincontentPane.revalidate();
    }
    	
    private void btnAttendance_OnClick_Action_Performed() throws IOException {
    	 object_os.writeObject(new String("Attendance"));
    	 maincontentPane.removeAll();
 	     maincontentPane.add(new AttendancePane( object_os , object_is));
 	     maincontentPane.repaint();
 	     maincontentPane.revalidate();
    }
    private void btnLeavePermission_OnClick_Action_Performed() throws IOException {
    	 object_os.writeObject(new String("LeaveInfo"));
    	 maincontentPane.removeAll();
 	     maincontentPane.add(new LeaveInboxPane( object_os , object_is));
 	     maincontentPane.repaint();
 	     maincontentPane.revalidate();
    }
    
    private void btnLogOut_OnClicked_Action_Performed() throws IOException {
    	dispose();
	}
    
   
}
