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
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.avinash.admin_server.AttendanceAdapter;
import com.avinash.admin_server.AttendanceTable;
import com.avinash.dimen.Dimen;
import com.avinash.login.Acknowledgement;

public class AttendancePane extends JPanel {
	   
	    private ObjectOutputStream object_os;
		private ObjectInputStream object_is;
		private JTable table;
		private JButton btnSubmit;
		protected Vector<AttendanceAdapter> list;

		public AttendancePane(ObjectOutputStream object_os , ObjectInputStream object_is) {
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
	            "Emp ID", " Employee Name"  , "Dept ID " , "Department Name" , "Designation" , "Attendance"};
	         
	              
	        final Class[] columnClass = new Class[] {
	            String.class, String.class, String.class , String.class , String.class , Boolean.class    
	        };
	        //create table model with data
	        DefaultTableModel model = new DefaultTableModel(null, columns) {
	            @Override
	            public boolean isCellEditable(int row, int column)
	            {
	            	if(column == 5) {
	            		return true;
	            	}else {
	            		return false;
	            	}
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
	         table.getColumnModel().getColumn(2).setPreferredWidth(150);
	         table.getColumnModel().getColumn(3).setPreferredWidth(198);
	         table.getColumnModel().getColumn(4).setPreferredWidth(200);
	         table.getColumnModel().getColumn(5).setPreferredWidth(150);
	          
	                  
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
	        
	        try {
	        get_Employee_Data_From_Server(); 
	        }catch(Exception e) {
	        	e.printStackTrace();
	        }
	        btnSubmit = new JButton("Submit");
	        btnSubmit.setBounds(450, 480 , 200 , 35);
	        btnSubmit.setBorder(null);
	        btnSubmit.setBackground(new Color(230, 0, 57));
	        btnSubmit.setForeground(Color.WHITE);
	        add(btnSubmit);
	        
	        btnSubmit.addMouseListener(new MouseAdapter() {
	 			public void mouseEntered(MouseEvent e) {
	 				btnSubmit.setBackground(new Color(179, 0, 45));
	 			}
	 		
	 			public void mouseExited(MouseEvent e) {
	 				btnSubmit.setBackground(new Color(230, 0, 57));
	 			}
	 					
	 		}); // end of Submit button mouse listener
	        
	       btnSubmit.addActionListener(new ActionListener() {
	         	public void actionPerformed(ActionEvent e) {
	         		
	         	try {
					btnSubmit_On_Click_Action_Performed();
				} catch (IOException | ClassNotFoundException e1) {
					
					e1.printStackTrace();
				}         		
	       
	         	}
	       });
	       
	       model.addTableModelListener(new TableModelListener() {

	   		@Override
	   		public void tableChanged(TableModelEvent event) {
	   			
	   			list  = new Vector<AttendanceAdapter>(10 , 5);
	   			for(int i = 0; i < table.getRowCount() ; i++ )
	   			  {
	   			    Boolean checked = Boolean.valueOf(table.getValueAt(i, 5).toString());
	   			   
	   			  	if(checked) {
	   			      		
	   			    	String empID = table.getModel().getValueAt(i, 0).toString();
	   			      	AttendanceAdapter attendanceAdapter = new AttendanceAdapter(empID);
	   			    	list.add(attendanceAdapter);
	   			    }else {
	   			           		
	   			       	}
	   			 }
	   		
	   	   }
	   	});  
	    
	}
  
	public void btnSubmit_On_Click_Action_Performed() throws IOException, ClassNotFoundException {
		
      	 try {
     			object_os.writeObject(list);
     		} catch (IOException e) {
     			
     			e.printStackTrace();
     	    }
		
		
		Acknowledgement ack = (Acknowledgement)object_is.readObject();
		
		if(ack.getFlag() == 1) {
			JOptionPane.showMessageDialog(null, "Attendance updated successfully","Attedance Update",JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(null, "Attendance is not updated ","Attedance Update",JOptionPane.ERROR_MESSAGE);

		}
	}
	
	private void get_Employee_Data_From_Server() throws ClassNotFoundException, IOException {
		 DefaultTableModel model = (DefaultTableModel)table.getModel();
    	 Object[] row = new Object[6];
    	 ArrayList<AttendanceTable> list = new ArrayList<AttendanceTable>();
    	 list = (ArrayList<AttendanceTable>)object_is.readObject();
		
		 
        for(int i = 0 ; i < list.size() ; i++){
     	   row[0] = list.get(i).getEmp_id();
     	   row[1] = list.get(i).getFname() + " " + list.get(i).getLname();
     	   row[2] = list.get(i).getDept_id();
     	   row[3] = list.get(i).getDept_name();
     	   row[4] = list.get(i).getDesignation();
     	   row[5] = list.get(i).isChecked();
     	   model.addRow(row);
        }
		
		
		
	}
}