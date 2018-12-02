package com.avinash.employee;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import com.avinash.employee_server.Attendance;
import com.avinash.employee_server.EmployeeView;

public class SalaryDetails extends JFrame {

	private JPanel contentPane;
	private JLabel lbllogo;
	private JLabel lblCompanyName;
	private JPanel headerPane;
	private JLabel lblPayslip;
	private JPanel panel;
	
	private JLabel lblEmpId;
	private JLabel lblSetEmpId;
	private JLabel lblName;
	private JLabel lblSetDesignation;
	private JLabel lblSetDeptName;
	private JLabel lblDeptName;
	private JLabel lblSetDeptId;
	private JLabel lblDeptId;
	private JLabel lblSetName;
	private JLabel lblDesignation;
	private JLabel lblDoj;
	private JLabel lblSetDoj;
	private JLabel lblWorkingDays;
	private JLabel lblSetWorkingDays;
	private JLabel lblPayableDays;
	private JLabel lblSetPayableDays;
	private JLabel lblEarning;
	private JLabel lblEarnrupees;
	private JLabel lblBasicSalary;
	private JLabel lblSetBasicSalary;
	private JLabel lblDa;
	private JLabel lblSetDa;
	private JLabel lblHra;
	private JLabel lblSetHra;
	private JLabel lblTotalEarning;
	private JLabel lblSetTotalEarnings;
	private JLabel lblDeduction;
	private JLabel lblDeductedRupees;
	private JLabel lblPf;
	private JLabel lblSetPf;
	private JLabel lblIncomeTax;
	private JLabel lblSetIncomeTax;
	private JLabel lblTotalDeduction;
	private JLabel lblSetTotalDeduction;
	private JLabel lblTotalSalary;

	private ObjectOutputStream object_os = null;
	private ObjectInputStream object_is = null;
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SalaryDetails frame = new SalaryDetails();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/
	public SalaryDetails( ObjectOutputStream object_os, ObjectInputStream object_is) {
	  
	  this.object_is = object_is;
	  this.object_os = object_os;
	  create_Frame_For_Salary(); 
	}
	

	
	/**
	 * Create the frame.
	 */
	public void create_Frame_For_Salary() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 50, 1024, 768);
		setResizable(false);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		
	    headerPane = new JPanel();
		headerPane.setBounds(0 , 0 , 1024 , 125);
		headerPane.setLayout(null);
		headerPane.setBackground(Color.DARK_GRAY);
		contentPane.add(headerPane);
		
		lbllogo = new JLabel("");
		lbllogo.setBounds(20, 10, 100, 100);
		//lbllogo.setBorder(new MatteBorder(1 ,1 ,1 ,1 , Color.gray));
		headerPane.add(lbllogo);
		lbllogo.setIcon(new ImageIcon("/home/avinash/Projects/Employee/images/comlogo.png"));
		
		lblCompanyName = new JLabel("<HTML><B>SumanBa Technofield pvt.Ltd.<br>Pandavnagri , Shivajinagar<br>Sinnar -422103</B></HTML>");
		lblCompanyName.setBounds(700 , 20 , 300 , 60);
		lblCompanyName.setFont(new Font("Arial" , Font.BOLD , 15));
		lblCompanyName.setForeground(new Color(255, 191, 0));
		headerPane.add(lblCompanyName);
		
		lblPayslip = new JLabel("PAYSLIP FOR THE MONTH OF SEPT-2018 ");
		lblPayslip.setBounds(210, 215, 600 , 30);
		lblPayslip.setFont(new Font("Arial" , Font.BOLD , 25));
		lblPayslip.setForeground(new Color(100, 47, 253 ));
		contentPane.add(lblPayslip);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0 , 125 , 1024 , 3);
		contentPane.add(separator);
		
		panel = new JPanel();
		contentPane.add(panel);
		panel.setBackground(Color.WHITE);
		panel.setBounds(12, 170, 1000, 460);
	    panel.setBorder(new MatteBorder(1 ,1 ,1 ,1 , Color.gray));
		panel.setLayout(null);
		
	
		lblEmpId = new JLabel(" Employee ID ");
		lblEmpId.setBounds(50, 120, 200, 25);
		lblEmpId.setBorder(new MatteBorder(1 ,1 ,1 ,1 , Color.gray));
		panel.add(lblEmpId);
		
		lblSetEmpId = new JLabel("");
		lblSetEmpId.setBounds(251, 120, 200, 25);
		lblSetEmpId.setBorder(new MatteBorder(1 ,1 ,1 ,1 , Color.gray));
		panel.add(lblSetEmpId);
		
	    lblName = new JLabel(" Employee Name  ");
		lblName.setBounds(452, 120, 200, 25);
		lblName.setBorder(new MatteBorder(1 ,1 ,1 ,1 , Color.gray));
		panel.add(lblName);
		
		 lblSetName = new JLabel("");
		lblSetName.setBounds(653, 120, 200, 25);
		lblSetName.setBorder(new MatteBorder(1 ,1 ,1 ,1 , Color.gray));
		panel.add(lblSetName);
		
		 lblDeptId = new JLabel(" Department ID ");
		lblDeptId.setBounds(50, 146, 200, 25);
		lblDeptId.setBorder(new MatteBorder(1 ,1 ,1 ,1 , Color.gray));
		panel.add(lblDeptId);
		
		 lblSetDeptId = new JLabel("");
		lblSetDeptId.setBounds(251, 146, 200, 25);
		lblSetDeptId.setBorder(new MatteBorder(1 ,1 ,1 ,1 , Color.gray));
		panel.add(lblSetDeptId);
		
		 lblDeptName = new JLabel(" Deparment Name ");
		lblDeptName.setBounds(452, 146, 200 , 25);
		lblDeptName.setBorder(new MatteBorder(1 ,1 ,1 ,1 , Color.gray));
		panel.add(lblDeptName);
		
		 lblSetDeptName = new JLabel("");
		lblSetDeptName.setBounds(653, 146, 200 , 25);
		lblSetDeptName.setBorder(new MatteBorder(1 ,1 ,1 ,1 , Color.gray));
		panel.add(lblSetDeptName);
		
		 lblDesignation = new JLabel(" Designation  ");
		lblDesignation.setBounds(50, 172, 200 , 25);
		lblDesignation.setBorder(new MatteBorder(1 ,1 ,1 ,1 , Color.gray));
		panel.add(lblDesignation);
		
		 lblSetDesignation = new JLabel("");
		lblSetDesignation.setBounds(251, 172, 200, 25);
		lblSetDesignation.setBorder(new MatteBorder(1 ,1 ,1 ,1 , Color.gray));
		panel.add(lblSetDesignation);
		
		 lblDoj = new JLabel(" Date Of Joining  ");
		lblDoj.setBounds(452, 172, 200 , 25);
		lblDoj.setBorder(new MatteBorder(1 ,1 ,1 ,1 , Color.gray));
		panel.add(lblDoj);
		
		lblSetDoj = new JLabel("");
		lblSetDoj.setBounds(653, 172, 200, 25);
		lblSetDoj.setBorder(new MatteBorder(1 ,1 ,1 ,1 , Color.gray));
		panel.add(lblSetDoj);
		
		 lblWorkingDays = new JLabel(" Working Days ");
		lblWorkingDays.setBounds(50, 198, 200, 25);
		lblWorkingDays.setBorder(new MatteBorder(1 ,1 ,1 ,1 , Color.gray));
		panel.add(lblWorkingDays);
		
		lblSetWorkingDays = new JLabel("<html><center>30</center></html>");
		lblSetWorkingDays.setBounds(251, 198, 200, 25);
		lblSetWorkingDays.setBorder(new MatteBorder(1 ,1 ,1 ,1 , Color.gray));
		panel.add(lblSetWorkingDays);
		
		lblPayableDays = new JLabel(" Payable Days ");
		lblPayableDays.setBounds(452, 198, 200, 25);
		lblPayableDays.setBorder(new MatteBorder(1 ,1 ,1 ,1 , Color.gray));
		panel.add(lblPayableDays);
		
		lblSetPayableDays = new JLabel("");
		lblSetPayableDays.setBounds(653, 198, 200, 25);
		lblSetPayableDays.setBorder(new MatteBorder(1 ,1 ,1 ,1 , Color.gray));
		panel.add(lblSetPayableDays);
		
		
		// earning 
		
		lblEarning = new JLabel(" Earnings  ");
		lblEarning.setBounds(50, 245, 200, 25);
		lblEarning.setBorder(new MatteBorder(1 ,1 ,1 ,1 , Color.gray));
		panel.add(lblEarning);
		
		lblEarnrupees = new JLabel(" Rs ");
		lblEarnrupees.setBounds(251, 245, 200, 25);
		lblEarnrupees.setBorder(new MatteBorder(1 ,1 ,1 ,1 , Color.gray));
		panel.add(lblEarnrupees);
		
		lblBasicSalary = new JLabel(" Basic ");
		lblBasicSalary.setBounds(50, 271, 200, 25);
		lblBasicSalary.setBorder(new MatteBorder(1 ,1 ,1 ,1 , Color.gray));
		panel.add(lblBasicSalary);
		
		 lblSetBasicSalary = new JLabel("");
		lblSetBasicSalary.setBounds(251, 271, 200, 25);
		lblSetBasicSalary.setBorder(new MatteBorder(1 ,1 ,1 ,1 , Color.gray));
		panel.add(lblSetBasicSalary);
		
		lblDa = new JLabel(" Daily Allowance ");
		lblDa.setBounds(50, 297, 200, 25);
		lblDa.setBorder(new MatteBorder(1 ,1 ,1 ,1 , Color.gray));
		panel.add(lblDa);
		
		 lblSetDa = new JLabel(" ");
		lblSetDa.setBounds(251, 297, 200, 25);
		lblSetDa.setBorder(new MatteBorder(1 ,1 ,1 ,1 , Color.gray));
		panel.add(lblSetDa);
		
		 lblHra = new JLabel(" House Rent Allowance");
		lblHra.setBounds(50, 323, 200, 25);
		lblHra.setBorder(new MatteBorder(1 ,1 ,1 ,1 , Color.gray));
		panel.add(lblHra);
		
		lblSetHra = new JLabel("");
		lblSetHra.setBounds(251, 323, 200, 25);
		lblSetHra.setBorder(new MatteBorder(1 ,1 ,1 ,1 , Color.gray));
		panel.add(lblSetHra);
		
		 lblTotalEarning = new JLabel(" Total Earnings " );
		lblTotalEarning.setBounds(50, 349, 200 , 25);
		lblTotalEarning.setBorder(new MatteBorder(1 ,1 ,1 ,1 , Color.gray));
		panel.add(lblTotalEarning);
		
		 lblSetTotalEarnings = new JLabel(" ");
		lblSetTotalEarnings.setBounds(251, 349 , 200, 25);
		lblSetTotalEarnings.setBorder(new MatteBorder(1 ,1 ,1 ,1 , Color.gray));
		panel.add(lblSetTotalEarnings);
		
		
		
		// Deduction
		
		lblDeduction = new JLabel(" Deduction ");
		lblDeduction.setBounds(452, 245, 200, 25);
		lblDeduction.setBorder(new MatteBorder(1 ,1 ,1 ,1 , Color.gray));
		panel.add(lblDeduction);
		
		lblDeductedRupees = new JLabel(" Rs ");
		lblDeductedRupees.setBounds(653, 245, 200, 25);
		lblDeductedRupees.setBorder(new MatteBorder(1 ,1 ,1 ,1 , Color.gray));
		panel.add(lblDeductedRupees);
		
		lblPf = new JLabel(" Provident Fund ");
		lblPf.setBounds(452, 271, 200, 25);
		lblPf.setBorder(new MatteBorder(1 ,1 ,1 ,1 , Color.gray));
		panel.add(lblPf);
		
		lblSetPf = new JLabel(" ");
		lblSetPf.setBounds(653, 271, 200, 25);
		lblSetPf.setBorder(new MatteBorder(1 ,1 ,1 ,1 , Color.gray));
		panel.add(lblSetPf);
		
		lblIncomeTax = new JLabel(" Income Tax ");
		lblIncomeTax.setBounds(452, 297, 200 , 25);
		lblIncomeTax.setBorder(new MatteBorder(1 ,1 ,1 ,1 , Color.gray));
		panel.add(lblIncomeTax);
		
		lblSetIncomeTax = new JLabel(" ");
		lblSetIncomeTax.setBounds(653, 297, 200, 25);
		lblSetIncomeTax.setBorder(new MatteBorder(1 ,1 ,1 ,1 , Color.gray));
		panel.add(lblSetIncomeTax);
		
		lblTotalDeduction = new JLabel(" Total Deduction ");
		lblTotalDeduction.setBounds(452, 349, 200 , 25);
		lblTotalDeduction.setBorder(new MatteBorder(1 ,1 ,1 ,1 , Color.gray));
		panel.add(lblTotalDeduction);
		
		lblSetTotalDeduction = new JLabel(" ");
		lblSetTotalDeduction.setBounds(653, 349, 200, 25);
		lblSetTotalDeduction.setBorder(new MatteBorder(1 ,1 ,1 ,1 , Color.gray));
		panel.add(lblSetTotalDeduction);
		
		lblTotalSalary = new JLabel(" Net Salary ");
		lblTotalSalary.setBounds(50, 400, 804, 25);
		
		panel.add(lblTotalSalary);
		
		try {
			get_Data_From_Server();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

 private void get_Data_From_Server() throws IOException {
	 object_os.writeObject(new String("Salary Details"));
	
  try {
	   	  
	     EmployeeView empview = (EmployeeView)object_is.readObject();
	     
	     lblSetEmpId.setText( empview.getEmp_id());
		 lblSetName.setText( empview.getFname() + " " + empview.getLname());
		  
	     lblSetDeptId.setText( empview.getDep_id());
		 lblSetDeptName.setText(empview.getDept_name());
		 lblSetDoj.setText( empview.getDoj() );
		 lblSetDesignation.setText( empview.getDesignation());
		 
		 Attendance attendance =(Attendance)object_is.readObject();
		 
		 lblSetPayableDays.setText(" " + attendance.getTotal_days());
		 
		 lblSetBasicSalary.setText("" + attendance.getBasic_Salary());
		 lblSetHra.setText(" " + 0.10*attendance.getTotal_days()*attendance.getBasic_Salary());
		 lblSetDa.setText(" " + 0.15*attendance.getTotal_days()*attendance.getBasic_Salary());
	     lblSetTotalEarnings.setText(" " + 1.25*attendance.getTotal_days()*attendance.getBasic_Salary());
	     
	     lblSetPf.setText(" " + 0.12*(1.25*attendance.getTotal_days()*attendance.getBasic_Salary()));
	     lblSetIncomeTax.setText(" " + 0.02*(1.25*attendance.getTotal_days()*attendance.getBasic_Salary()));
	     lblSetTotalDeduction.setText(" " +(int)(0.14*(1.25*attendance.getTotal_days()*attendance.getBasic_Salary())) ); 
	     
	     lblTotalSalary.setText("Net Salary is " + 0.86*(1.25*attendance.getTotal_days()*attendance.getBasic_Salary()));
		 
			
			
  		} catch (ClassNotFoundException | IOException e) {
	
  				e.printStackTrace();
  		}
 
 	}
 
}