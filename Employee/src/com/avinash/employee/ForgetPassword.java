package com.avinash.employee;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.UIManager;

import com.avinash.dimen.Dimen;
import com.avinash.employee_server.ResetPassword;
import com.avinash.login.Acknowledgement;

public class ForgetPassword extends JFrame {

	private JTextField tfuserid;
	private JLabel lbluserid ,lblPassword , lblConfirmPassword;
	private JPasswordField pfpassword , pfConfirmPassword;
	private JSeparator userseparator , passseparator , cpassseparator;
	private JButton btnSubmit;
	private ObjectOutputStream object_os;
	private ObjectInputStream object_is;
	private JLabel lblInvalidMessage;
	
	public ForgetPassword(ObjectOutputStream object_os , ObjectInputStream object_is) {
		this.object_os = object_os;
		this.object_is = object_is;
		create_ForgetPassword_Panel();
	}

	/**
	 * Create the frame.
	 */
	public void create_ForgetPassword_Panel() {
		
		getContentPane().setBackground(Color.WHITE);
		setBounds((Dimen.SCREEN_WIDTH/2-300) , (Dimen.SCREEN_HEIGHT/2-200), 600, 400);
		
		getContentPane().setLayout(null);
		setVisible(true);
		setTitle("Forget Password");
		
		
		lbluserid = new JLabel("USER ID");
		lbluserid.setBounds(100, 50, 132, 15);
		getContentPane().add(lbluserid);
		
		tfuserid = new JTextField();
		tfuserid.setBounds(100, 70, 400, 25);
		tfuserid.setBorder(null);
		tfuserid.setFont( new Font("Arial" , Font.BOLD , 15) );
		tfuserid.setMargin(new Insets(5 , 15 , 5 ,15));
		getContentPane().add(tfuserid);
		tfuserid.setColumns(10);
		
		userseparator = new JSeparator();
		userseparator.setBounds(100, 95, 400, 2);
		getContentPane().add(userseparator);
		
		tfuserid.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
			
				userseparator.setForeground(new Color(255, 26, 83 ));
			}
			public void mouseExited(MouseEvent e) {
				userseparator.setForeground(UIManager.getColor(userseparator));
			}
		});// end of mouseListener for username
		
		lblPassword = new JLabel("PASSWORD");
		lblPassword.setBounds(100, 115, 132, 15);
		getContentPane().add(lblPassword);
		
		pfpassword = new JPasswordField();
		pfpassword.setColumns(10);
		pfpassword.setBounds(100, 135, 400, 25);
		pfpassword.setFont(new Font("Arial" , Font.BOLD , 15));
		pfpassword.setMargin(new Insets(5 , 15 , 5 ,15));
		pfpassword.setBorder(null);
		getContentPane().add(pfpassword);
		
			
		passseparator = new JSeparator();
		passseparator.setBounds(100, 160, 400, 2);
		getContentPane().add(passseparator);
		
		pfpassword.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				passseparator.setForeground(new Color(255, 26, 83 ));
			}
			public void mouseExited(MouseEvent e) {
				passseparator.setForeground(UIManager.getColor(passseparator));
			}
		});// end of mouseListener for password
	
		
		lblConfirmPassword = new JLabel("CONFIRM PASSWORD");
		lblConfirmPassword.setBounds(100, 180, 200, 15);
		getContentPane().add(lblConfirmPassword);
		
		pfConfirmPassword = new JPasswordField();
		pfConfirmPassword.setColumns(10);
		pfConfirmPassword.setBorder(null);
		pfConfirmPassword.setBounds(100, 200, 400, 25);
		pfConfirmPassword.setFont(new Font("Arial" , Font.BOLD , 15));
		pfConfirmPassword.setMargin(new Insets(5 , 15 , 5 ,15));
		getContentPane().add(pfConfirmPassword);
		
		cpassseparator = new JSeparator();
		cpassseparator.setBounds(100, 225, 400, 2);
		getContentPane().add(cpassseparator);
		
		pfConfirmPassword.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				cpassseparator.setForeground(new Color(255, 26, 83 ));
			}
			public void mouseExited(MouseEvent e) {
				cpassseparator.setForeground(UIManager.getColor(cpassseparator));
			}
		}); // end of mouseListener for Confirm password
	
		 lblInvalidMessage = new JLabel("");
		 lblInvalidMessage.setBounds(100 , 250 , 400 , 20);
		 getContentPane().add(lblInvalidMessage);
		
		btnSubmit = new JButton("<HTML><CENTER><B><FONT >SUBMIT</FONT></B></CENTER></HTML>");
		btnSubmit.setBounds(100, 290, 400, 35);
		btnSubmit.setBackground(new Color(230, 0, 57));
		btnSubmit.setFocusable(false);
		btnSubmit.setBorder(null);
		btnSubmit.setForeground(Color.WHITE);
		getContentPane().add(btnSubmit);
		
		

		btnSubmit.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				btnSubmit.setBackground(new Color(179, 0, 45));
			}
			public void mouseExited(MouseEvent e) {
				btnSubmit.setBackground(new Color(230, 0, 57));
			}
			
		}); // end of mouseListener for Submit button
		
		btnSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			
				try {
					btnSubmit_OnClicked_Action_Performed();
				} catch (IOException | ClassNotFoundException e1) {
					
					e1.printStackTrace();
				}
				setVisible(false);
				
				
			}
		}); // end of action listener for Submit button

	}// end of ForgetPassword Constructor
	
	public void btnSubmit_OnClicked_Action_Performed() throws IOException, ClassNotFoundException {
		
		String userid = tfuserid.getText().trim();
		char[] pass = pfpassword.getPassword();
		String password = String.valueOf(pass);
		char[] cpass = pfConfirmPassword.getPassword();
		String confirmPassword = String.valueOf(cpass);
		
		if(userid.equals("") || password.equals("") || confirmPassword.equals("")) {
			 lblInvalidMessage.setText("<HTML><B><FONT COLOR = RED >Fill the empty fields..</FONT></B></HTML>");
			 lblInvalidMessage.setVisible(true);
			 
			 Timer t = new Timer(2000, new ActionListener() {

		            @Override
		            public void actionPerformed(ActionEvent e) {
		                lblInvalidMessage.setText(null);
		            }
		        }); // end of Timer action listener
			 
			    t.setRepeats(false);

		}else if(!password.equals(confirmPassword)) {
			 lblInvalidMessage.setText("<HTML><B><FONT COLOR = RED >Password does not match</FONT></B></HTML>");
			 lblInvalidMessage.setVisible(true);
			 
			 Timer t = new Timer(2000, new ActionListener() {

		            @Override
		            public void actionPerformed(ActionEvent e) {
		                lblInvalidMessage.setText(null);
		            }
		        }); // end of Timer action listener
			 
			    t.setRepeats(false);
			    pfpassword.setText(null);
			    pfConfirmPassword.setText(null);
		}else {
			System.out.println("in else block");
			object_os.writeObject(new String("Employee"));
			ResetPassword reset_password = new ResetPassword(userid , password , confirmPassword);
			object_os.writeObject(reset_password);
			System.out.println("object written successfully");
		}
		
		Acknowledgement ack = (Acknowledgement)object_is.readObject();
		
		if(ack.getFlag() == 1) {
			JOptionPane.showMessageDialog(null, "Password updated successfully", "Reset Password", JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(null, "Password is not updated successfully", "Reset Password", JOptionPane.ERROR_MESSAGE);

		}
				
		
		
	}// end of btnSubmit_OnClicked_Action_Performed()

}// end of class ForgetPassword