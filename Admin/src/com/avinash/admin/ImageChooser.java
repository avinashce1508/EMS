package com.avinash.admin;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImageChooser {
    private String imagePath;
	public String getImagePath() {
		JFileChooser filechooser = new JFileChooser();
		//filechooser.setCurrentDirectory(new File(System.getProperty("/avinash/home")));
		FileNameExtensionFilter filterext = new FileNameExtensionFilter("*.images" , "png" , "jpeg" ,"jpg");
		
		filechooser.addChoosableFileFilter(filterext);
		filechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int returnVal = filechooser.showOpenDialog(null);
	    
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			
			File file = filechooser.getSelectedFile();
			
			 imagePath = file.getPath();
	}
   return (imagePath);
  }
}