package edu.dbms.commands;

import java.io.File;
import java.io.IOException;

public class DropIndex {

	private String indexName;
	public DropIndex(String indexName){
		this.indexName=indexName;
	}
	public void execute() throws InvalidInputException, IOException{
		MainIndex mainIndex=MainIndex.getInstance();
		mainIndex.delete(indexName);
		mainIndex.dumpToFile();
		
		File f=new File(Constants.INDEX_DIRECTORY+indexName+".idx");
		f.delete();
	}
}
