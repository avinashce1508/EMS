package com.avinash.admin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.avinash.dimen.Dimen;
import com.avinash.leave.LeaveLetter;
import com.avinash.leave.LeavePermission;
import com.avinash.login.Acknowledgement;

public class LeaveInboxPane extends JPanel {

	    private ObjectOutputStream object_os;
		private ObjectInputStream object_is;
		private JTable table;
		private JButton btnReject;
		private JButton btnAccept;
		private DefaultTableModel model;
		
		public LeaveInboxPane(ObjectOutputStream object_os , ObjectInputStream object_is) {
	    	  this.object_os = object_os;
	    	  this.object_is = object_is;
	    	  create_Attendance_Table();
	      }
		public void create_Attendance_Table() {
				setLayout(null);
				setSize((Dimen.SCREEN_WIDTH-200), (Dimen.SCREEN_HEIGHT-120));
				setBackground(Color.WHITE);
			 //headers for the table
	        String[] columns = new String[] {
	            "Emp ID", "Employee Name"  , "Leave Type" , "Start date" , "End date" , "Reason" };
	         
	              
	        final Class[] columnClass = new Class[] {
	            String.class, String.class, String.class , String.class , String.class , String.class   
	        };
	        //create table model with data
	           model = new DefaultTableModel(null, columns) {
	            @Override
	            public boolean isCellEditable(int row, int column)
	            {
	                return false;
	            }
	            @Override
	            public Class<?> getColumnClass(int columnIndex)
	            {
	                return columnClass[columnIndex];
	            }           
	           
	        };
	         
	         table = new JTable(model);
	       
	        
	         table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	         
	         table.getColumnModel().getColumn(0).setPreferredWidth(100);
	         table.getColumnModel().getColumn(1).setPreferredWidth(199);
	         table.getColumnModel().getColumn(2).setPreferredWidth(100);
	         table.getColumnModel().getColumn(3).setPreferredWidth(150);
	         table.getColumnModel().getColumn(4).setPreferredWidth(150);
	         table.getColumnModel().getColumn(5).setPreferredWidth(298);
	         
	                  
	         JScrollPane scrollPane = new JScrollPane(table);
	         scrollPane.setBounds(70, 50, 1000, 400);
	         add(scrollPane);
	         table.setPreferredScrollableViewportSize(new Dimension((1000), (400)));
	        
	        // make the alignment of text to the center of the column
	        
	        DefaultTableCellRenderer cell_text_center_renderer = new DefaultTableCellRenderer();
	        cell_text_center_renderer.setHorizontalAlignment(JLabel.CENTER);
	        table.setDefaultRenderer(String.class, cell_text_center_renderer);
	        setLayout(null);
	        setVisible(true);
	        
	        btnReject = new JButton("REJECT");
	        btnReject.setBounds(600, 480 , 200 , 35);
	        btnReject.setBorder(null);
	        btnReject.setFocusable(false);
	        btnReject.setBackground(new Color(230, 0, 57));
	        btnReject.setForeground(Color.WHITE);
	        add(btnReject);
	        
	        btnReject.addMouseListener(new MouseAdapter() {
	 			public void mouseEntered(MouseEvent e) {
	 				btnReject.setBackground(new Color(179, 0, 45));
	 			}
	 		
	 			public void mouseExited(MouseEvent e) {
	 				btnReject.setBackground(new Color(230, 0, 57));
	 			}
	 					
	 		}); // end of Reject button mouse listener
	        
	        btnReject.addActionListener(new ActionListener() {
	         	public void actionPerformed(ActionEvent e) {
	         		
	         
					try {
						btnReject_On_Click_Action_Performed();
					} catch (IOException | ClassNotFoundException e1) {
						
						e1.printStackTrace();
					}
				
	         	}
	       });
	       
	        btnAccept = new JButton("ACCEPT");
	        btnAccept.setBounds(300, 480 , 200 , 35);
	        btnAccept.setBorder(null);
	        btnAccept.setFocusable(false);
	        btnAccept.setBackground(new Color(230, 0, 57));
	        btnAccept.setForeground(Color.WHITE);
	        add(btnAccept);
	        
	        btnAccept.addMouseListener(new MouseAdapter() {
	 			public void mouseEntered(MouseEvent e) {
	 				btnAccept.setBackground(new Color(179, 0, 45));
	 			}
	 		
	 			public void mouseExited(MouseEvent e) {
	 				btnAccept.setBackground(new Color(230, 0, 57));
	 			}
	 					
	 		}); // end of Accept button mouse listener
	        
	        btnAccept.addActionListener(new ActionListener() {
	         	public void actionPerformed(ActionEvent e) {
	         		
	         	   try {
					btnAccept_On_Click_Action_Performed();
				} catch (IOException | ClassNotFoundException e1) {
					
					e1.printStackTrace();
				}
			
	         	}

				
	       });
	        try {
	        get_Employee_Data_From_Server(); 
	        }catch(Exception e) {
	        	e.printStackTrace();
	        }
}
		
		private void get_Employee_Data_From_Server() throws ClassNotFoundException, IOException {
			
			
			 DefaultTableModel model = (DefaultTableModel)table.getModel();
	    	 Object[] row = new Object[6];
	    	 ArrayList<LeaveLetter> list = new ArrayList<LeaveLetter>();
	    	 list = (ArrayList<LeaveLetter>)object_is.readObject();
			
	        for(int i = 0 ; i < list.size() ; i++){
	     	   row[0] = list.get(i).getEmp_id();
	     	   row[1] = list.get(i).getName();
	     	   row[2] = list.get(i).getLeave_type();
	     	   row[3] = list.get(i).getStart_date();
	     	   row[4] = list.get(i).getEnd_date();
	     	   row[5] = list.get(i).getReason();
	       	   model.addRow(row);
	        }
	       
			
		}
		
		protected void btnReject_On_Click_Action_Performed() throws IOException, ClassNotFoundException {
			if(table.getRowCount() == 0) {
	        	 JOptionPane.showMessageDialog(null, "<HTML><FONT COLOR = RED>No entries in the table</FONT></HTML>" ,"" ,JOptionPane.ERROR_MESSAGE);
	         }else if(table.getSelectedRow() == -1) {
	        	 JOptionPane.showMessageDialog(null, "<HTML><FONT COLOR = RED>please , select the record</FONT></HTML>" , "" ,JOptionPane.ERROR_MESSAGE);
	        	 
	         }else {
	        	 	String empID = table.getModel().getValueAt(table.getSelectedRow(), 0).toString();
	        	 	LeavePermission leave_permit = new LeavePermission(empID , "Rejected");
	        	 	object_os.writeObject(leave_permit);
	        	 	
	        	 	Acknowledgement ack = (Acknowledgement)object_is.readObject();
	    		    if(ack.getFlag() ==1) {
	    		    	JOptionPane.showMessageDialog(null, "Response sent successfully","Response", JOptionPane.INFORMATION_MESSAGE);
	    		    }else {
	    		    	JOptionPane.showMessageDialog(null, "Response not sent ","Response", JOptionPane.ERROR_MESSAGE);
	    		    }
	         } 	
		    
		}

		protected void btnAccept_On_Click_Action_Performed() throws IOException, ClassNotFoundException{
			
			if(table.getRowCount() == 0) {
	        	 JOptionPane.showMessageDialog(null, "<HTML><FONT COLOR = RED>No entries in the table</FONT></HTML>" ,"" ,JOptionPane.ERROR_MESSAGE);
	         }else if(table.getSelectedRow() == -1) {
	        	 JOptionPane.showMessageDialog(null, "<HTML><FONT COLOR = RED>please , select the record</FONT></HTML>" , "" ,JOptionPane.ERROR_MESSAGE);
	        	 
	         }else {
	        	 	String empID = table.getModel().getValueAt(table.getSelectedRow(), 0).toString();
	        	 	LeavePermission leave_permit = new LeavePermission(empID , "Accepted");
	        	 	object_os.writeObject(leave_permit);
	        	 	
	        	 	Acknowledgement ack = (Acknowledgement)object_is.readObject();
	        	 	System.out.println("after reading acknowledgement");
	     		    if(ack.getFlag() ==1) {
	     		    	JOptionPane.showMessageDialog(null, "Response sent successfully","Response", JOptionPane.INFORMATION_MESSAGE);
	     		    }else {
	     		    	JOptionPane.showMessageDialog(null, "Response not sent ","Response", JOptionPane.ERROR_MESSAGE);
	     		    }
	         }
		   
		    
		}
}		
