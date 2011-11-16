package edu.dbms.commands;

public class ListAllIndex {

	public void execute(){
		MainIndex mainIndex=MainIndex.getInstance();
		mainIndex.printAllIndex();
	}
}
