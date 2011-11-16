package edu.dbms.data;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import edu.dbms.commands.Constants;


public class TableToIndexFile {

	public static void convert(Table table,String indexName) throws IOException{
		
		  FileWriter fstream = new FileWriter(Constants.INDEX_DIRECTORY+indexName+".idx");
		  BufferedWriter out = new BufferedWriter(fstream);
		  for(Row r:table.getRows()){
			  out.write(String.valueOf(r.getRowId()));
			  out.write(" '");
			  for(int i=0;i<table.getMetaData().getNoOfColumns();i++){
				  out.write(r.getValue(i));
			  }
			  out.write("'\n");
		  }
		  out.close();
	}
}
