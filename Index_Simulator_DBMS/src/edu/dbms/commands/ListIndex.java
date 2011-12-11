package edu.dbms.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ListIndex {
	
	private String tableName;

	final String TABLENAME_PATTERN="t[1-9]";
	
	public void execute(){
		MainIndex mainIndex=MainIndex.getInstance();
		mainIndex.printIndexByTableName(tableName);
	}
	public void setTableName(String tableName) throws InvalidInputException{
		Pattern p=Pattern.compile(TABLENAME_PATTERN);
		Matcher m=p.matcher(tableName);
		if(m.find()){
			this.tableName=tableName;
		}
		else{
			System.out.println(tableName);
			throw new InvalidInputException();
		}
		
	}
}
