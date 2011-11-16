package edu.dbms.data;

import java.io.DataInputStream;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import edu.dbms.commands.Constants;

// Load Config in Memory.
// Singleton
public class ColumnCodes {

	private static ColumnCodes instance=null;
	private static Properties property;
	
	private ColumnCodes(){
		
	}
	public static ColumnCodes getInstance(){
		if(instance==null){
			instance=new ColumnCodes();
			loadFile(Constants.CONFIG_DIRECTORY+"column.properties");
		}
		return instance;
	}
	public static void loadFile(String filePath){
		 
			FileInputStream file;
	        DataInputStream in;
	        try
	        {
	            file = new FileInputStream(filePath);
	            in = new DataInputStream(file);
	            
	            property = new java.util.Properties();
	            property.load (in);
	            in.close ();
	        }
	        catch (IOException ex)
	        {
	        	ex.printStackTrace();
	        }
	}
	public Properties getProperties(){
		return property;
	}
	/*
	public static void main(String[] args){
		ColumnConfig obj=new ColumnConfig();
		obj.loadFile("column.properties");
	}*/
}
