package com.avinash.employee;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JInternalFrame.JDesktopIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.MatteBorder;

import com.avinash.dimen.Dimen;
import com.avinash.leave.LeaveLetter;
import com.avinash.login.Acknowledgement;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

public class LeaveApplication extends JPanel {

	private JButton btnSubmit;
	private JLabel lblApplicationForLeave;
	private ObjectOutputStream object_os;
	private ObjectInputStream object_is;
	private JTextField tfemp_id;
	private JLabel lblEmpId;
	private JLabel lblname;
	private JTextField tfname;
	private JPanel leavePane;
	private JComboBox cbleave_type;
	private JLabel lblleaveType;
	private JLabel lblStartDate;
	private JDateChooser dc_start_date;
	private JLabel lblEndDate;
	private JDateChooser dc_end_date;
	private JLabel lblReason;
	private JTextField tfreason;
	private JLabel lblStatus;
	private JLabel invalid_input;
	private JButton btnterm_and_conditions;
	String empid;
	String emp_fname;
	String emp_lname;
	private Date startDate;
	private Date endDate;
	private JLabel invalid_message;
	
	public LeaveApplication(ObjectOutputStream object_os , ObjectInputStream object_is,String empid,String emp_fname , String emp_lname) throws ClassNotFoundException, IOException {
		 this.object_os = object_os;
		 this.object_is = object_is;
		 this.empid = empid;
		 this.emp_fname = emp_fname;
		 this.emp_lname = emp_lname;
		 create_LeaveApplication_Panel();
	 }
	public void create_LeaveApplication_Panel() throws ClassNotFoundException, IOException {
		setBounds(0 , 0 , Dimen.SCREEN_WIDTH , Dimen.SCREEN_HEIGHT-125 );
		setLayout(null);
		
		lblApplicationForLeave = new JLabel("Application For Leave");
		lblApplicationForLeave.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 25));
		lblApplicationForLeave.setBounds(542, 24, 400, 30);
		add(lblApplicationForLeave);
		
		leavePane = new JPanel();
		leavePane.setBounds((Dimen.SCREEN_WIDTH/2-300) , 100 , 600 , 400);
		leavePane.setBackground(Color.WHITE);
		leavePane.setLayout(null);
		leavePane.setBorder(new MatteBorder(1 , 1 , 1 ,1 , Color.gray));
		add(leavePane);
		
		lblEmpId = new JLabel("Emp id");
		lblEmpId.setBounds(100 , 50 , 100 , 25);
		leavePane.add(lblEmpId);
		
		tfemp_id = new JTextField();
		tfemp_id.setText(empid);
		tfemp_id.setEditable(false);
		tfemp_id.setBounds(220 , 50 , 200 , 25);
		tfemp_id.setColumns(10);
		leavePane.add(tfemp_id);
		
		lblname = new JLabel("Name");
		lblname.setBounds(100 , 85 , 100 , 25);
		leavePane.add(lblname);
		
		tfname = new JTextField();
		tfname.setText(emp_fname + " " + emp_lname);
		tfname.setEditable(false);
		tfname.setBounds(220 , 85 , 200 , 25);
		tfname.setColumns(10);
		leavePane.add(tfname);
		
		lblleaveType = new JLabel("Leave Type");
		lblleaveType.setBounds(100 , 120 , 100 , 25);
		leavePane.add(lblleaveType);
		
		
		cbleave_type = new JComboBox();
		cbleave_type.setBounds(220 , 120 , 218 , 25);
		leavePane.add(cbleave_type);
		
		cbleave_type.addItem("<Leave Type>");
		cbleave_type.addItem("Paid Leave");
		cbleave_type.addItem("Casual Leave");
		cbleave_type.addItem("Sick Leave");
		
		invalid_input = new JLabel("");
		invalid_input.setBounds(220 , 150 , 200 , 10);
		
		lblStartDate = new JLabel("Start Date");
		lblStartDate.setBounds(100 , 155 , 100 , 25);
		leavePane.add(lblStartDate);
		
	    dc_start_date = new JDateChooser();
	    dc_start_date.setBounds(220 , 155 , 222 , 25);
	    dc_start_date.setBackground(Color.white);
	    leavePane.add(dc_start_date);
	    dc_start_date.setDateFormatString("yyyy-MM-dd");
	    dc_start_date.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JCalendar calender = new JCalendar();
				
				
			}
		});
	    
	    lblEndDate = new JLabel("End Date");
	    lblEndDate.setBounds(100 , 190 , 100 , 25);
		leavePane.add(lblEndDate);
		
	    dc_end_date = new JDateChooser();
	    dc_end_date.setBounds(220 , 190 , 222 , 25);
	    dc_end_date.setBackground(Color.white);
	    leavePane.add(dc_end_date);
	    dc_end_date.setDateFormatString("yyyy-MM-dd");
	    dc_end_date.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JCalendar calender = new JCalendar();
				
				
			}
		});
		
	    lblReason = new JLabel("Reason");
	    lblReason.setBounds(100 , 225 , 100 , 25);
		leavePane.add(lblReason);
		
		tfreason = new JTextField();
		tfreason.setEditable(true);
		tfreason.setBounds(220 , 225 , 200 , 25);
		tfreason.setColumns(10);
		leavePane.add(tfreason);
		
		btnterm_and_conditions = new JButton("<HTML><FONT COLOR = RED>*</FONT><FONT>Agree Terms and Conditions</FONT></HTML>");
		btnterm_and_conditions.setBackground(null);
		btnterm_and_conditions.setBorder(null);
		btnterm_and_conditions.setHorizontalAlignment(SwingConstants.LEFT);
	//	btnterm_and_conditions.setHorizontalTextPosition(SwingConstants.LEFT);
		btnterm_and_conditions.setBounds(100 , 260 , 400 , 20);
		btnterm_and_conditions.setFocusable(false);
		leavePane.add(btnterm_and_conditions);
		
		btnterm_and_conditions.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
			String terms = "<HTML>1)An employee is entitled to take leave accumulated in an annual leave cycle in terms of subsection (2) on consecutive days.<br> "
						+ "2)An employer must grant annual leave not later than six months after the end of the annual leave cycle.<br>"
						+ "3)An employer may not require or permit an employee to work for the employer during any period of annual leave.<br>"
						+ "4)An employer must pay an employee leave payment before the beginning of the period of leave; or by agreement, on the employee's usual pay day.<br>"
						+ "5)During every sick leave cycle, an employee is entitled to an amount of paid sick leave equal to the number of days the employee would normally work during a period of six weeks<br>.</HTML>";
			
				JOptionPane.showMessageDialog(null, terms , "terms and conditions", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		invalid_message = new JLabel("");
		invalid_message.setBounds(293, 290, 600, 15);
		add(invalid_message);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(200, 330 , 200, 35);
		btnSubmit.setFocusable(false);
		btnSubmit.setBackground(new Color(230, 0, 57));
		btnSubmit.setForeground(Color.WHITE);
		btnSubmit.setBorder(null);
		leavePane.add(btnSubmit);
		
		lblStatus = new JLabel("");
		lblStatus.setBounds(100 , 15 , 400 , 20);
		lblStatus.setForeground(Color.blue);
		leavePane.add(lblStatus);
		
		CheckPermission check = new CheckPermission(object_os, object_is);
		lblStatus.setText("Previous leave application is " + check.getLeave().getStatus());
		
		
		
		btnSubmit.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				btnSubmit.setBackground(new Color(179, 0, 45));
			}
			public void mouseExited(MouseEvent e) {
				btnSubmit.setBackground(new Color(230, 0, 57));
			}
			
		});
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
				try {
					btnSubmit_OnClicked_Action_Performed();
				} catch (IOException | ClassNotFoundException e) {
					
					e.printStackTrace();
				}
			}
		});
		
		

	}
	
	public void btnSubmit_OnClicked_Action_Performed() throws IOException, ClassNotFoundException {

		String emp_id = tfemp_id.getText().toString().trim();
		String name = tfname.getText().toString().trim();
		String leave_type = null;
		if(cbleave_type.getSelectedItem().equals("<Leave Type>")) {
		   invalid_input.setText("Please select valid input");
		   
			 Timer t = new Timer(2000, new ActionListener() {

		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	invalid_input.setText(null);
		            }
		        }); // end of Timer action listener
			 
			    t.setRepeats(false);
		}else {
		 leave_type = (String)cbleave_type.getSelectedItem();
		}
		String start_date = ((JTextField)dc_start_date.getDateEditor().getUiComponent()).getText().trim();
		String end_date = ((JTextField)dc_end_date.getDateEditor().getUiComponent()).getText().trim();
		try {
			startDate = (new SimpleDateFormat("yyyy-MM-dd")).parse(start_date);
			endDate = (new SimpleDateFormat("yyyy-MM-dd")).parse(end_date);

		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		long numberOfDays = (endDate.getTime() - startDate.getTime()) / 86400000;
		if (numberOfDays < 0) {
			invalid_message.setText("<HTML><FONT COLOR = RED> Please , fill the valid date </FONT></HTML>");

			Timer t = new Timer(2000, new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					invalid_message.setText(null);
				}
			}); // end of Timer action listener
			t.start();
			t.setRepeats(false);
			return;
		}
		
		String reason = tfreason.getText().toString().trim();
		LeaveLetter leave_letter = new LeaveLetter(emp_id, name, leave_type, start_date, end_date, reason);
		
		object_os.writeObject(leave_letter);
		
		Acknowledgement ack = (Acknowledgement)object_is.readObject();
		
		if(ack.getFlag() == 1) {
			lblStatus.setText("");
			JOptionPane.showMessageDialog(null, "Data is successfully submitted", "Leave Info",JOptionPane.INFORMATION_MESSAGE);
			cbleave_type.setSelectedItem("<Leave Type>");
			dc_start_date.setCalendar(null);
			dc_end_date.setCalendar(null);
			tfreason.setText("");
		
		}else {
     		JOptionPane.showMessageDialog(null, "Error in submiting data", "Leave Info",JOptionPane.ERROR_MESSAGE);
     	}
		
		
		
	}
}


