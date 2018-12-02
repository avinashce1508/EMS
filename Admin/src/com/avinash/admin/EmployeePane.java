package com.avinash.admin;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
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
import com.avinash.admin_server.DeleteRows;
import com.avinash.admin_server.EditInfo;
import com.avinash.admin_server.EmployeeTable;
import com.avinash.dimen.Dimen;
import com.avinash.login.Acknowledgement;



public class EmployeePane extends JPanel {
	 
	    private ObjectOutputStream object_os;
		private ObjectInputStream object_is;
		private JTable table;
		private JButton btnEdit;
		protected Vector<AttendanceAdapter> list;
		private JButton btnDelete;
		private DefaultTableModel model;
		private JLabel info_message;
	/**
	 * Create the panel.
	 */
	public EmployeePane(ObjectOutputStream object_os , ObjectInputStream object_is) {
	  this.object_os = object_os;
   	  this.object_is = object_is;
   	  create_Employee_Table();
	}
 public void create_Employee_Table() {
	setLayout(null);
	setSize((Dimen.SCREEN_WIDTH-200), (Dimen.SCREEN_HEIGHT-120));
	setBackground(Color.WHITE);
 //headers for the table
String[] columns = new String[] {
    "Emp ID", " Employee Name"  ,"Contact No" ,  "Dept ID " , "Department Name" ,"Date Of Joining", "Designation" };
 
      
final Class[] columnClass = new Class[] {
    String.class, String.class,Long.class , String.class , String.class , Date.class , String.class     
};
//create table model with data
 model = new DefaultTableModel(null, columns) {
    @Override
    public boolean isCellEditable(int row, int column)
    {
        return true;
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
 table.getColumnModel().getColumn(3).setPreferredWidth(100);
 table.getColumnModel().getColumn(4).setPreferredWidth(198);
 table.getColumnModel().getColumn(5).setPreferredWidth(100);
 table.getColumnModel().getColumn(6).setPreferredWidth(150);
 
  
          
 JScrollPane scrollPane = new JScrollPane(table);
 scrollPane.setBounds(70, 50, 1000, 400);
 add(scrollPane);
 table.setPreferredScrollableViewportSize(new Dimension((1000), (400)));

// make the alignment of text to the center of the column

 	DefaultTableCellRenderer cell_text_center_renderer = new DefaultTableCellRenderer();
 	cell_text_center_renderer.setHorizontalAlignment(JLabel.CENTER);
 	table.setDefaultRenderer(String.class, cell_text_center_renderer);

 	// set editable 
 	
 	setLayout(null);
 	setVisible(true);

 	 try {
 	    	get_Employee_Data_From_Server(); 
 	     }catch(Exception e) {
 			e.printStackTrace();
 		 }
 	 
 	    info_message = new JLabel("");
 	    info_message.setText("<HTML><FONT COLOR = BLUE >* Double click on cell to edit the information</FONT></HTML>");
 	    info_message.setFont(new Font("Arial" , Font.BOLD , 15));
		info_message.setBounds(70, 450 , 600 , 35);
		add(info_message);
		
 		btnEdit = new JButton("Edit");
 		btnEdit.setBounds(300, 500 , 200 , 35);
 		btnEdit.setBorder(null);
 		btnEdit.setFocusable(false);
 		btnEdit.setBackground(new Color(230, 0, 57));
 		btnEdit.setForeground(Color.WHITE);
 		add(btnEdit);

 		btnEdit.addMouseListener(new MouseAdapter() {
		public void mouseEntered(MouseEvent e) {
			btnEdit.setBackground(new Color(179, 0, 45));
		}
	
		public void mouseExited(MouseEvent e) {
			btnEdit.setBackground(new Color(230, 0, 57));
		}
				
	}); // end of Submit button mouse listener

		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btnEdit_On_Click_Action_Performed();
				} catch (IOException | ClassNotFoundException e1) {
					
					e1.printStackTrace();
				}         		
	}
});

		btnDelete = new JButton("Delete");
		btnDelete.setBounds(600, 500 , 200 , 35);
		btnDelete.setBorder(null);
		btnDelete.setFocusable(false);
		btnDelete.setBackground(new Color(230, 0, 57));
		btnDelete.setForeground(Color.WHITE);
 		add(btnDelete);

 		btnDelete.addMouseListener(new MouseAdapter() {
		public void mouseEntered(MouseEvent e) {
			btnDelete.setBackground(new Color(179, 0, 45));
		}
	
		public void mouseExited(MouseEvent e) {
			btnDelete.setBackground(new Color(230, 0, 57));
		}
				
	}); // end of Submit button mouse listener

 		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
 				try {
					btnDelete_On_Click_Action_Performed();
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

 	public void btnEdit_On_Click_Action_Performed() throws IOException, ClassNotFoundException {
 		
 		if(table.getRowCount() == 0) {
        	 JOptionPane.showMessageDialog(null, "<HTML><FONT COLOR = RED>No entries in the table</FONT></HTML>" ,"" ,JOptionPane.ERROR_MESSAGE);
         }else if(table.getSelectedRow() == -1) {
        	 JOptionPane.showMessageDialog(null, "<HTML><FONT COLOR = RED>please , select the record</FONT></HTML>" , "" ,JOptionPane.ERROR_MESSAGE);
        	 
         }else {
        	     
        	     Object id = table.getValueAt(table.getSelectedRow(), 0);
        	     Object name = table.getValueAt(table.getSelectedRow(), 1);
        	     Object contact_no = table.getValueAt(table.getSelectedRow(), 2);
        	     Object department = table.getValueAt(table.getSelectedRow(), 3);
        	     Object department_name = table.getValueAt(table.getSelectedRow(), 4);
        	     Object designation = table.getValueAt(table.getSelectedRow(), 6);
        	     
        	     EditInfo edit_emp_info = new EditInfo((String)id ,(String) name , (Long)contact_no ,(String) department , (String)department_name , (String)designation);
        	     object_os.writeObject(edit_emp_info);
        	     System.out.println("after writing object");
        	     Acknowledgement ack = (Acknowledgement)object_is.readObject();
         		 
         		 if(ack.getFlag() == 1) {
         			 JOptionPane.showMessageDialog(null, "Record is successfully updated ", "Upadate", JOptionPane.INFORMATION_MESSAGE);
         		 }else {
         			 JOptionPane.showMessageDialog(null, "Error in updating record ", "Update", JOptionPane.ERROR_MESSAGE);
         		 }
                 	      	    
         }		 
 		


 	}
 	
	public void btnDelete_On_Click_Action_Performed() throws IOException, ClassNotFoundException {
		
	    if(table.getRowCount() == 0) {
        	 JOptionPane.showMessageDialog(null, "<HTML><FONT COLOR = RED>No entries in the table</FONT></HTML>" ,"" ,JOptionPane.ERROR_MESSAGE);
         }else if(table.getSelectedRow() == -1) {
        	 JOptionPane.showMessageDialog(null, "<HTML><FONT COLOR = RED>please , select the record</FONT></HTML>" , "" ,JOptionPane.ERROR_MESSAGE);
        	 
         }else {
        	
         Object id = table.getValueAt(table.getSelectedRow(), 0);
         DeleteRows deleteEntry = new DeleteRows((String)id);
         model.removeRow(table.getSelectedRow());
         object_os.writeObject(deleteEntry);
 		 		 
 		 Acknowledgement ack = (Acknowledgement)object_is.readObject();
 		 
 		 if(ack.getFlag() == 1) {
 			 JOptionPane.showMessageDialog(null, "Record is successfully deleted ", "Delete Employee", JOptionPane.INFORMATION_MESSAGE);
 		 }else {
 			 JOptionPane.showMessageDialog(null, "Error in deleting record ", "Delete Employee", JOptionPane.ERROR_MESSAGE);
 		 }
        
 		
     }	
 }
	private void get_Employee_Data_From_Server() throws ClassNotFoundException, IOException {
			
			DefaultTableModel model = (DefaultTableModel)table.getModel();
			Object[] row = new Object[7];
			
			ArrayList<EmployeeTable> list = new ArrayList<EmployeeTable>();
			
			list = (ArrayList<EmployeeTable>)object_is.readObject();
			
			for(int i = 0 ; i < list.size() ; i++){	
				row[0] = list.get(i).getEmp_id();	
				row[1] = list.get(i).getFname() + " " + list.get(i).getLname();
				row[2] = list.get(i).getContact_no();
				row[3] = list.get(i).getDept_id();
				row[4] = list.get(i).getDept_name();
				row[5] = list.get(i).getDoj();
				row[6] = list.get(i).getDesignation();
				model.addRow(row);
			}

	}
}
