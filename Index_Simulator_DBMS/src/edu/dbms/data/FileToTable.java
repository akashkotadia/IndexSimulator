package edu.dbms.data;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import edu.dbms.commands.CreateIndex;
import edu.dbms.commands.InvalidInputException;


public class FileToTable {

	public static Table convert(String filePath,String tableName) throws IOException{
		
			FileInputStream fis=new FileInputStream(filePath);
			DataInputStream ds=new DataInputStream(fis);
			BufferedReader br=new BufferedReader(new InputStreamReader(ds));
			
			Table table=new Table(tableName);
			table.create(br.readLine(), br.readLine());
			
			String line=null;
			while((line=br.readLine())!=null){
				table.insert(line);
			}
			return table;
	}
	public static void main(String args[]) throws IOException, InvalidInputException{
		
		CreateIndex com=new CreateIndex();
		com.setIndexName("t1x1");
		com.setIndexOrders("1d,2a,3d");
		com.execute();
		
		// To Do
		// List all Index
		// Drop Index, Also delete file of index
		// Design Lookup
	}
}
