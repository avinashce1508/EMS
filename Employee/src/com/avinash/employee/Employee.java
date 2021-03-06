package com.avinash.employee;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.avinash.dimen.Dimen;
import com.avinash.login.Acknowledgement;
import com.avinash.login.LoginVerification;

/**
 * <h1>Login</h1> This class is for the login page for employee
 * 
 * @author avinash
 * 
 */
public class Employee extends JFrame {

	private JPanel contentPane, loginpane;
	private JLabel background, lblUserid, lblPassword, lblInvalidMessage;
	private JTextField tfUserid;
	private JPasswordField pfpassword;
	private JSeparator userseparator, passseparator;
	private JButton btnLogin, btnForgetPassword;
	private JCheckBox cbxShowPassword;
	private static Socket socket;

	private final static String hostName = "127.0.0.1";
	private final static int portNumber = 2337;
	private static ObjectOutputStream object_os;
	private static ObjectInputStream object_is;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {

					try {
						// create the socket with hostName and specified port
						socket = new Socket(hostName, portNumber);
					} catch (UnknownHostException uhe) {
						JOptionPane.showMessageDialog(null, "Host is unreachable ");
						return;
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, "Server Down ");
						return;
					}
					// creates the ObjectOutput and ObjectInput streams
					object_os = new ObjectOutputStream(socket.getOutputStream());
					object_is = new ObjectInputStream(socket.getInputStream());

					Employee frame = new Employee();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Employee() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				try {
					object_os.flush();
					object_os.close();
					object_is.close();
					socket.close();
					System.out.println("socket closed successfully");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		setBounds(0, 0, Dimen.SCREEN_WIDTH, Dimen.SCREEN_HEIGHT);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.setBounds(0, 0, Dimen.SCREEN_WIDTH, Dimen.SCREEN_HEIGHT);
		setContentPane(contentPane);

		loginpane = new JPanel();
		loginpane.setBounds((Dimen.SCREEN_WIDTH / 2 - 300), (Dimen.SCREEN_HEIGHT / 2 - 200), 600, 400);
		loginpane.setBackground(Color.WHITE);
		loginpane.setVisible(true);
		contentPane.add(loginpane);
		loginpane.setLayout(null);

		btnLogin = new JButton("<HTML><CENTER><B><FONT >LOGIN</FONT></B></CENTER></HTML>");
		btnLogin.setBounds(100, 304, 400, 35);
		btnLogin.setBackground(new Color(230, 0, 57));
		btnLogin.setFocusable(false);
		btnLogin.setForeground(Color.WHITE);
		getRootPane().setDefaultButton(btnLogin);

		btnLogin.setBorder(null);
		loginpane.add(btnLogin);

		btnLogin.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				btnLogin.setBackground(new Color(179, 0, 45));
			}

			public void mouseExited(MouseEvent e) {
				btnLogin.setBackground(new Color(230, 0, 57));
			}

		}); // end of Login button mouse listener

		btnLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {

				btnLogin_OnClick_Action_Performed();

			}

		});// end of Login button action listener

		lblUserid = new JLabel("USER ID");
		lblUserid.setBounds(100, 83, 132, 15);
		loginpane.add(lblUserid);

		tfUserid = new JTextField();
		tfUserid.setBounds(100, 110, 400, 35);
		tfUserid.setColumns(10);
		tfUserid.setFont(new Font("Arial", Font.BOLD, 15));
		tfUserid.setMargin(new Insets(5, 15, 5, 15));
		// tfUserid.setUI(new );
		tfUserid.setBorder(null);
		loginpane.add(tfUserid);

		userseparator = new JSeparator();
		userseparator.setBounds(100, 145, 400, 2);

		loginpane.add(userseparator);

		tfUserid.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {

				userseparator.setForeground(new Color(255, 26, 83));
			}

			public void mouseExited(MouseEvent e) {
				userseparator.setForeground(UIManager.getColor(userseparator));
			}
		}); // end of mouse listener for userid field

		lblPassword = new JLabel("PASSWORD");
		lblPassword.setBounds(100, 169, 132, 15);
		loginpane.add(lblPassword);

		pfpassword = new JPasswordField();
		pfpassword.setColumns(10);
		pfpassword.setBorder(null);
		pfpassword.setFont(new Font("Arial", Font.BOLD, 15));
		pfpassword.setMargin(new Insets(5, 15, 5, 15));
		pfpassword.setBounds(100, 196, 400, 35);
		loginpane.add(pfpassword);

		pfpassword.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				passseparator.setForeground(new Color(255, 26, 83));
			}

			public void mouseExited(MouseEvent e) {
				passseparator.setForeground(UIManager.getColor(passseparator));
			}
		}); // end of mouse listener for password field

		passseparator = new JSeparator();
		passseparator.setBounds(100, 231, 400, 2);
		loginpane.add(passseparator);

		btnForgetPassword = new JButton("<HTML><CENTER><B><FONT >FORGET PASSWORD ?</FONT></B></CENTER></HTML>");
		btnForgetPassword.setBounds(330, 240, 200, 20);
		btnForgetPassword.setBackground(Color.WHITE);
		btnForgetPassword.setFocusable(false);
		btnForgetPassword.setBorderPainted(false);
		btnForgetPassword.setContentAreaFilled(false);
		btnForgetPassword.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {

				btnForgetPassword.setForeground(Color.RED);
			}

			public void mouseExited(MouseEvent e) {
				btnForgetPassword.setForeground(Color.BLACK);
			}
		}); // end of mouse listener for forget password

		loginpane.add(btnForgetPassword);

		btnForgetPassword.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {

				btnForgetPassword_OnClick_Action_Performed();

			}

		});// end of forget password button action listener

		cbxShowPassword = new JCheckBox("<HTML><CENTER><B><FONT >SHOW PASSWORD</FONT></B></CENTER></HTML>");
		cbxShowPassword.setBackground(Color.white);
		cbxShowPassword.setBounds(100, 240, 200, 20);
		cbxShowPassword.setFocusable(false);
		loginpane.add(cbxShowPassword);

		cbxShowPassword.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				cbxShowPassword_OnChecked_Action_Performed();
			}

		}); // end of cbxShowPassword action listener

		lblInvalidMessage = new JLabel("");
		lblInvalidMessage.setBounds(100, 270, 400, 20);
		loginpane.add(lblInvalidMessage);

		background = new JLabel();
		background.setBounds(0, 0, Dimen.SCREEN_WIDTH, Dimen.SCREEN_HEIGHT);
		background.setIcon(new ImageIcon("/home/avinash/Projects/Employee/images/back5.jpg"));
		contentPane.add(background);

	} // end of Employee Constructor

	public void btnLogin_OnClick_Action_Performed() {

		String userid = tfUserid.getText().trim();
		char[] pass = pfpassword.getPassword();
		String password = String.copyValueOf(pass);

		// Now we have to check the credentials are valid or not
		if (userid.equals("") || password.equals("")) {

			lblInvalidMessage.setText("<HTML><B><FONT COLOR = RED >Fill the empty fields..</FONT></B></HTML>");
			lblInvalidMessage.setVisible(true);

			Timer t = new Timer(2000, new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					lblInvalidMessage.setText(null);
				}
			}); // end of Timer action listener
			t.start();
			t.setRepeats(false);

		} else {
			try {
				Acknowledgement ack = null;

				LoginVerification verify_user = new LoginVerification(userid, password);
				object_os.writeObject(new String("Employee"));
				object_os.writeObject(verify_user);

				ack = (Acknowledgement) object_is.readObject();

				if (ack.getFlag() == 1) {

					new EmployeeDetails(socket, object_os, object_is);
					tfUserid.setText(null);
					pfpassword.setText(null);
				} else {
					JOptionPane.showMessageDialog(null, "login unsuccessful , please try again");
				} // end of if-else block

			} catch (IOException e) {
				// System.out.println("cannot bind the connection " + e.getMessage());
				e.printStackTrace();
				// System.exit(1);
			} catch (ClassNotFoundException cnfe) {
				cnfe.printStackTrace();
			}

		} // end of if-else block

	} // end of btnLogin_OnClick_Action_Performed method

	public void btnForgetPassword_OnClick_Action_Performed() {

		ForgetPassword forgetpassword = new ForgetPassword(object_os, object_is);

	} // end of btnForgetPassword_OnClick_Action_Performed method

	public void cbxShowPassword_OnChecked_Action_Performed() {

		if (cbxShowPassword.isSelected()) {
			pfpassword.setEchoChar((char) 0);
		} else {
			pfpassword.setEchoChar((char) '*');
		}

	}// end of cbxShowPassword_OnChecked_Action_Performed method
}
