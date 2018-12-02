package com.avinash.admin;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.MatteBorder;

import com.avinash.admin_server.EmployeeInfoUpload;
import com.avinash.dimen.Dimen;
import com.avinash.login.Acknowledgement;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

public class AddEmployeePane extends JPanel {
	private JTextField tfFname, tfLname;
	private JLabel lblfname, lbllname, lblAddress, lblBirthDate, lblPersonalDetails, lblDepartmentDetails;
	private JLabel lblGender, lblContactNo, lblDeptId, lbldeptname, lblJoinDate, lblDesignation;
	private JTextField tfaddress, tfdept_id, tfDeptname, tfdesignation;

	private JFormattedTextField contact_no;
	private JRadioButton rdbtnMale, rdbtnFemale, rdbtnOther;
	private JDateChooser dc_birth_date, dc_join_date;
	private JButton btnSubmit, btnReset;
	private JPanel personalPane, deptPane;
	private ObjectOutputStream object_os;
	private ObjectInputStream object_is;
	private JLabel invalid_message;
	private JLabel lblLoginCredential;
	private JPanel loginPane;
	private JLabel lblemp_id;
	private JTextField tfemp_id;
	private JLabel lblpassword;
	private JTextField tfpassword;
	private JLabel lblSalaryDetails;
	private JPanel salaryPane;
	private JLabel lblbasic_salary;
	private JTextField tfbasic_salary;
	private JLabel lblUpload;
	private JLabel lblFilePath;
	private JButton btnUpload;
	private ImageChooser ic;
	private String path;
	private Date date_of_birth;

	public AddEmployeePane(ObjectOutputStream object_os, ObjectInputStream object_is) {

		this.object_os = object_os;
		this.object_is = object_is;
		create_AddEmployeePanel();
	}

	/**
	 * Create the panel.
	 */
	public void create_AddEmployeePanel() {

		setLayout(null);
		setSize((Dimen.SCREEN_WIDTH - 200), (Dimen.SCREEN_HEIGHT - 120));
		setBackground(Color.WHITE);

		lblPersonalDetails = new JLabel("Personal Details");
		lblPersonalDetails.setBounds(((Dimen.SCREEN_WIDTH - 200) / 2 - 500), 20, 197, 15);
		add(lblPersonalDetails);

		personalPane = new JPanel();
		personalPane.setBounds(((Dimen.SCREEN_WIDTH - 200) / 2 - 500), 40, 580, 260);
		personalPane.setBorder(new MatteBorder(1, 1, 1, 1, Color.GRAY));
		personalPane.setBackground(Color.white);
		personalPane.setLayout(null);
		add(personalPane);

		lblfname = new JLabel("Fname");
		lblfname.setBounds(102, 25, 70, 25);
		personalPane.add(lblfname);

		tfFname = new JTextField();
		tfFname.setBounds(171, 25, 130, 25);
		tfFname.setColumns(10);
		personalPane.add(tfFname);

		lbllname = new JLabel("Lname");
		lbllname.setBounds(323, 25, 70, 25);
		personalPane.add(lbllname);

		tfLname = new JTextField();
		tfLname.setBounds(392, 25, 130, 25);
		tfLname.setColumns(10);
		personalPane.add(tfLname);

		lblGender = new JLabel("Gender");
		lblGender.setBounds(102, 60, 70, 25);
		personalPane.add(lblGender);

		rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setBounds(171, 60, 70, 25);
		rdbtnMale.setBackground(Color.WHITE);
		personalPane.add(rdbtnMale);

		rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setBounds(243, 60, 89, 25);
		rdbtnFemale.setBackground(Color.WHITE);
		personalPane.add(rdbtnFemale);

		rdbtnOther = new JRadioButton("other");
		rdbtnOther.setBounds(331, 60, 70, 25);
		rdbtnOther.setBackground(Color.WHITE);
		personalPane.add(rdbtnOther);

		rdbtnMale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				rdbtnFemale.setSelected(false);
				rdbtnOther.setSelected(false);

			}
		});
		rdbtnFemale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				rdbtnMale.setSelected(false);
				rdbtnOther.setSelected(false);
			}
		});
		rdbtnOther.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				rdbtnMale.setSelected(false);
				rdbtnFemale.setSelected(false);

			}
		});

		lblBirthDate = new JLabel("Birth Date");
		lblBirthDate.setBounds(102, 95, 102, 25);
		personalPane.add(lblBirthDate);

		dc_birth_date = new JDateChooser();
		dc_birth_date.setBounds(205, 95, 150, 25);
		dc_birth_date.setBackground(Color.white);
		dc_birth_date.setDateFormatString("yyyy-MM-dd");
		personalPane.add(dc_birth_date);

		dc_birth_date.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JCalendar calender = new JCalendar();

			}
		});

		lblAddress = new JLabel("Address");
		lblAddress.setBounds(102, 130, 70, 45);
		personalPane.add(lblAddress);

		tfaddress = new JTextField();
		tfaddress.setColumns(30);
		tfaddress.setBounds(205, 130, 250, 45);
		personalPane.add(tfaddress);

		lblContactNo = new JLabel("Contact No");
		lblContactNo.setBounds(102, 185, 102, 25);
		personalPane.add(lblContactNo);

		contact_no = new JFormattedTextField();
		contact_no.setColumns(10);
		contact_no.setBounds(205, 185, 150, 25);
		personalPane.add(contact_no);

		lblUpload = new JLabel("upload ");
		lblUpload.setBounds(110, 220, 80, 25);
		personalPane.add(lblUpload);

		lblFilePath = new JLabel("");
		lblFilePath.setBounds(200, 220, 334, 20);
		personalPane.add(lblFilePath);

		btnUpload = new JButton("", new ImageIcon("/home/avinash/Projects/Admin/images/upload_20.png"));
		btnUpload.setBackground(new Color(230, 0, 57));
		btnUpload.setBorder(null);
		btnUpload.setFocusable(false);
		btnUpload.setBounds(454, 220, 70, 20);
		personalPane.add(btnUpload);

		btnUpload.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				ic = new ImageChooser();
				path = ic.getImagePath();
				lblFilePath.setText(path);

			}
		});

		// department panel
		lblDepartmentDetails = new JLabel("Department Details");
		lblDepartmentDetails.setBounds(((Dimen.SCREEN_WIDTH - 200) / 2 - 500), 310, 225, 15);
		add(lblDepartmentDetails);

		deptPane = new JPanel();
		deptPane.setBounds(((Dimen.SCREEN_WIDTH - 200) / 2 - 500), 330, 580, 170);
		deptPane.setBorder(new MatteBorder(1, 1, 1, 1, Color.GRAY));
		deptPane.setLayout(null);
		deptPane.setBackground(Color.WHITE);
		add(deptPane);

		lblDeptId = new JLabel("Dept id");
		lblDeptId.setBounds(102, 25, 70, 25);
		deptPane.add(lblDeptId);

		tfdept_id = new JTextField();
		tfdept_id.setBounds(200, 25, 130, 25);
		tfdept_id.setColumns(10);
		deptPane.add(tfdept_id);

		lbldeptname = new JLabel("Dept name");
		lbldeptname.setBounds(102, 60, 100, 25);
		deptPane.add(lbldeptname);

		tfDeptname = new JTextField();
		tfDeptname.setBounds(200, 60, 130, 25);
		tfDeptname.setColumns(10);
		deptPane.add(tfDeptname);

		lblJoinDate = new JLabel("Joining Date");
		lblJoinDate.setBounds(102, 95, 102, 25);
		deptPane.add(lblJoinDate);

		dc_join_date = new JDateChooser();
		dc_join_date.setBounds(200, 95, 150, 25);
		dc_join_date.setBackground(Color.WHITE);
		dc_join_date.setForeground(Color.WHITE);
		dc_join_date.setDateFormatString("yyyy-MM-dd");
		deptPane.add(dc_join_date);

		dc_join_date.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JCalendar calender = new JCalendar();

			}
		});

		lblDesignation = new JLabel("Designation");
		lblDesignation.setBounds(102, 130, 100, 25);
		deptPane.add(lblDesignation);

		tfdesignation = new JTextField();
		tfdesignation.setBounds(200, 130, 130, 25);
		tfdesignation.setColumns(10);
		deptPane.add(tfdesignation);

		invalid_message = new JLabel("");
		invalid_message.setBounds(293, 520, 600, 15);
		add(invalid_message);

		// login credential panel
		lblLoginCredential = new JLabel("Login credential");
		lblLoginCredential.setBounds(((Dimen.SCREEN_WIDTH - 200) / 2 + 137), 20, 150, 15);
		add(lblLoginCredential);

		loginPane = new JPanel();
		loginPane.setBounds(((Dimen.SCREEN_WIDTH - 200) / 2 + 137), 40, 350, 225);
		loginPane.setBorder(new MatteBorder(1, 1, 1, 1, Color.GRAY));
		loginPane.setLayout(null);
		loginPane.setBackground(Color.WHITE);
		add(loginPane);

		lblemp_id = new JLabel("Emp ID ");
		lblemp_id.setBounds(50, 80, 80, 25);
		loginPane.add(lblemp_id);

		tfemp_id = new JTextField();
		tfemp_id.setBounds(150, 80, 140, 25);
		tfemp_id.setColumns(10);
		loginPane.add(tfemp_id);

		lblpassword = new JLabel("Password");
		lblpassword.setBounds(50, 130, 70, 25);
		loginPane.add(lblpassword);

		tfpassword = new JTextField();
		tfpassword.setBounds(150, 130, 140, 25);
		tfpassword.setColumns(10);
		loginPane.add(tfpassword);

		lblSalaryDetails = new JLabel("Salary Details");
		lblSalaryDetails.setBounds(((Dimen.SCREEN_WIDTH - 200) / 2 + 137), 282, 225, 15);
		add(lblSalaryDetails);

		salaryPane = new JPanel();
		salaryPane.setBounds(((Dimen.SCREEN_WIDTH - 200) / 2 + 137), 301, 350, 100);
		salaryPane.setBorder(new MatteBorder(1, 1, 1, 1, Color.GRAY));
		salaryPane.setLayout(null);
		salaryPane.setBackground(Color.WHITE);
		add(salaryPane);

		lblbasic_salary = new JLabel("Basic Salary");
		lblbasic_salary.setBounds(30, 40, 100, 25);
		salaryPane.add(lblbasic_salary);

		tfbasic_salary = new JTextField();
		tfbasic_salary.setBounds(150, 40, 140, 25);
		tfbasic_salary.setColumns(10);
		salaryPane.add(tfbasic_salary);

		btnSubmit = new JButton("SUBMIT");
		btnSubmit.setBounds(((Dimen.SCREEN_WIDTH - 200) / 2 + 177), 465, 117, 35);
		btnSubmit.setBackground(new Color(230, 0, 57));
		btnSubmit.setForeground(Color.white);
		btnSubmit.setFocusable(false);
		btnSubmit.setBorder(null);
		add(btnSubmit);

		btnSubmit.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				btnSubmit.setBackground(new Color(179, 0, 45));
			}

			public void mouseExited(MouseEvent e) {
				btnSubmit.setBackground(new Color(230, 0, 57));
			}

		});
		btnSubmit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					btnSubmit_OnClicked_Action_Performed();
				} catch (IOException | ClassNotFoundException e1) {

					e1.printStackTrace();
				}
			}
		});

		btnReset = new JButton("RESET");
		btnReset.setBounds(((Dimen.SCREEN_WIDTH - 200) / 2 + 330), 465, 117, 35);
		btnReset.setBackground(new Color(230, 0, 57));
		btnReset.setForeground(Color.WHITE);
		btnReset.setFocusable(false);
		btnReset.setBorder(null);
		add(btnReset);

		btnReset.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				btnReset.setBackground(new Color(179, 0, 45));
			}

			public void mouseExited(MouseEvent e) {
				btnReset.setBackground(new Color(230, 0, 57));
			}

		});
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				btnReset_OnClicked_Action_Performed();

			}
		});

	}

	private void btnSubmit_OnClicked_Action_Performed() throws IOException, ClassNotFoundException {

		if (tfFname.getText().equals("") || tfLname.getText().equals("")
				|| ((JTextField) dc_birth_date.getDateEditor().getUiComponent()).getText().toString().equals("")
				|| tfaddress.getText().equals("") || contact_no.getText().equals("")) {

			invalid_message
					.setText("<HTML><FONT COLOR = RED> Please , fill the all field in personal info</FONT></HTML>");

			Timer t = new Timer(2000, new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					invalid_message.setText(null);
				}
			}); // end of Timer action listener
			t.start();
			t.setRepeats(false);
			return;

		} else if (tfdept_id.getText().equals("") || tfDeptname.getText().equals("")
				|| ((JTextField) dc_join_date.getDateEditor().getUiComponent()).getText().toString().equals("")
				|| tfdesignation.getText().equals("")) {
			invalid_message
					.setText("<HTML><FONT COLOR = RED> Please , fill the all field in department info</FONT></HTML>");

			Timer t = new Timer(2000, new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					invalid_message.setText(null);
				}
			}); // end of Timer action listener
			t.start();
			t.setRepeats(false);
			return;
		} else if (tfemp_id.getText().trim().equals("") || tfpassword.getText().trim().equals("")) {
			invalid_message.setText(
					"<HTML><FONT COLOR = RED> Please , fill the all field in login credential info</FONT></HTML>");

			Timer t = new Timer(2000, new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					invalid_message.setText(null);
				}
			}); // end of Timer action listener
			t.start();
			t.setRepeats(false);
			return;
		} else if (tfbasic_salary.getText().trim().equals("")) {
			invalid_message
					.setText("<HTML><FONT COLOR = RED> Please , fill the all field in salary details</FONT></HTML>");

			Timer t = new Timer(2000, new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					invalid_message.setText(null);
				}
			}); // end of Timer action listener
			t.start();
			t.setRepeats(false);
			return;

		} else {
			String emp_fname = tfFname.getText().trim();
			String emp_lname = tfLname.getText().trim();
			String gender = null;
			if (rdbtnMale.isSelected()) {
				gender = "Male";
			} else if (rdbtnFemale.isSelected()) {
				gender = "Female";
			} else {
				gender = "Other";
			}
			String dob = ((JTextField) dc_birth_date.getDateEditor().getUiComponent()).getText().toString();

			try {
				date_of_birth = (new SimpleDateFormat("yyyy-MM-dd")).parse(dob);

			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			Date currentDate = new Date();
			long numberOfDays = (currentDate.getTime() - date_of_birth.getTime()) / 86400000;
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
			} else if (numberOfDays / 365 < 20) {
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

			String address = tfaddress.getText().trim();
			long contact_No = 0;

			if (contact_no.getText().trim().length() < 10 || contact_no.getText().trim().length() > 10) {
				invalid_message
						.setText("<HTML><FONT COLOR = RED> Please , fill the correct mobile number</FONT></HTML>");

				Timer t = new Timer(2000, new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						invalid_message.setText(null);
					}
				}); // end of Timer action listener
				t.start();
				t.setRepeats(false);
				return;
			} else if (Integer.parseInt(contact_no.getText().trim().substring(0, 2)) < 70) {
				invalid_message
						.setText("<HTML><FONT COLOR = RED> Please , fill the correct mobile number</FONT></HTML>");

				Timer t = new Timer(2000, new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						invalid_message.setText(null);
					}
				}); // end of Timer action listener
				t.start();
				t.setRepeats(false);
				return;
			} else {
				contact_No = Long.parseLong(contact_no.getText().trim());
			}

			String dept_id = tfdept_id.getText().trim();
			String dept_name = tfDeptname.getText().trim();
			String doj = ((JTextField) dc_join_date.getDateEditor().getUiComponent()).getText().toString();
			String designation = tfdesignation.getText().trim();

			String emp_id = tfemp_id.getText().trim();
			String password = tfpassword.getText().trim();
			String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}";
			if (!password.matches(pattern)) {
				invalid_message
						.setText("<HTML><FONT COLOR = RED> Please ,password must contain atleast number,lowercase,uppercase and symbols </FONT></HTML>");

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
			int basic_salary = Integer.parseInt(tfbasic_salary.getText().trim());

			File file = new File(path);
			byte[] byte_array_of_images = null;
			try {
				FileInputStream fis = new FileInputStream(file);
				byte_array_of_images = new byte[100000];
				fis.read(byte_array_of_images, 0, byte_array_of_images.length);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			EmployeeInfoUpload emp_info_upload = new EmployeeInfoUpload(emp_fname, emp_lname, gender, dob, address,
					contact_No, dept_id, dept_name, doj, designation, emp_id, password, basic_salary,
					byte_array_of_images);

			object_os.writeObject(emp_info_upload);
			System.out.println("after writing emp_info upload");
		}
		Acknowledgement ack = (Acknowledgement) object_is.readObject();

		if (ack.getFlag() == 1) {
			JOptionPane.showMessageDialog(null, "Employee information is successfully added", "Add Employee",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Employee information is not added", "Add Employee",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void btnReset_OnClicked_Action_Performed() {

		tfFname.setText("");
		tfaddress.setText("");
		tfLname.setText("");
		contact_no.setText("");
		dc_birth_date.setCalendar(null);

		if (rdbtnMale.isSelected() || rdbtnFemale.isSelected() || rdbtnOther.isSelected()) {
			rdbtnMale.setSelected(false);
			rdbtnFemale.setSelected(false);
			rdbtnOther.setSelected(false);
		}

		// department panel
		tfdept_id.setText("");
		tfDeptname.setText("");
		tfdesignation.setText("");
		dc_join_date.setCalendar(null);

		// login panel
		tfemp_id.setText(null);
		tfpassword.setText(null);

	}
}
