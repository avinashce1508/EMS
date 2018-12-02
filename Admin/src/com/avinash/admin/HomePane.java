package com.avinash.admin;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.avinash.dimen.Dimen;

public class HomePane extends JPanel {

	/**
	 * Create the panel.
	 */
	public HomePane() {
		setLayout(null);
		setSize((Dimen.SCREEN_WIDTH-200), (Dimen.SCREEN_HEIGHT-120));
		setBackground(Color.WHITE);
		setVisible(true);
		
	    JLabel background = new JLabel();	
	    background.setLayout(null);
	    background.setBounds(0 , 0 ,(Dimen.SCREEN_WIDTH-200), (Dimen.SCREEN_HEIGHT-100) );
	    add(background);
	    background.setIcon(new ImageIcon("/home/avinash/Projects/Admin/images/rsz_employee.png"));
	}

}
