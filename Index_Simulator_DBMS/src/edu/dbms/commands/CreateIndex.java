package edu.dbms.commands;

import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.dbms.data.FileToTable;
import edu.dbms.data.Table;
import edu.dbms.data.TableToIndexFile;
import edu.dbms.processData.ComplementColumn;
import edu.dbms.processData.PaddingColumns;
import edu.dbms.processData.StableSort;

public class CreateIndex {

	private String indexName;
	private String tableName;
	
	private String[] columnsIndexes;	// e.g: 1a,2d,3a 
	private String[] columnNoOrders;	// e.g: 1,2,3
	private int noOfOrders;
	
	private HashMap<String, String> columnsSortOrdersMap;		// e.g : {1 - a, 2 - d, 3 - a}
	
	final String INDEXNAME_PATTERN="(t[1-9])(x[1-3])";
	final String INDEXORDER_PATTERN="([1-3])([a,d,A,D])";
	

	public void execute() throws IOException, InvalidInputException{

		MainIndex mainIndex=MainIndex.getInstance();
		if(!mainIndex.contains(getIndexName())){
			Table table=FileToTable.convert(Constants.TABLE_DIRECTORY+getTableName()+".tab",getTableName());
			
			ComplementColumn comp=new ComplementColumn(table.getMetaData());
			comp.complement(table.getRows(), this);
			
			StableSort.sort(table.getRows(), getColumnNoOrders());
			
			PaddingColumns pad=new PaddingColumns(table.getMetaData());
			pad.padding(table);
			
			TableToIndexFile.convert(table,getIndexName());
			
			table.printTable();
			
			mainIndex.insert(getIndexName(), getColumnIndexes(), table.getMetaData().getNoOfColumns());
			mainIndex.dumpToFile();
		}
		else{
			throw new InvalidInputException();
		}
	}
	public void setIndexName(String indexName) throws InvalidInputException {
		Pattern p=Pattern.compile(INDEXNAME_PATTERN);
		Matcher m=p.matcher(indexName);
		if(m.find()){
			this.indexName=m.group(0);
			tableName=m.group(1);
		}
		else{
			throw new InvalidInputException();
		}
	}
	public void setIndexOrders(String indexOrders) throws InvalidInputException{
		columnsIndexes=indexOrders.split(",");
		noOfOrders=columnsIndexes.length;
		columnNoOrders=new String[columnsIndexes.length];
		columnsSortOrdersMap=new HashMap<String, String>();
		
		int i=0;
		Pattern p=Pattern.compile(INDEXORDER_PATTERN);
		for(String indx : columnsIndexes){
			
			Matcher m=p.matcher(indx);
			if(m.find()){
				columnNoOrders[i]=m.group(1);
				if(!columnsSortOrdersMap.containsKey(columnNoOrders[i]))
					columnsSortOrdersMap.put(columnNoOrders[i],m.group(2));
				else{
					throw new InvalidInputException();
				}
				i++;
			}
			else{
				throw new InvalidInputException();
			}
		}
	}
	
	// e.g : t1x1
	public String getIndexName(){
		return indexName;
	}
	// e.g : t1
	public String getTableName(){
		return tableName;
	}
	
	// e.g: Return "d" for colindex = 2. (2d)
	public String getColumnsortOrder(String colIndx){
		return columnsSortOrdersMap.get(colIndx);
	}
	// e.g: {1,2,3} for {1a,2d,3a} 
	public int[] getColumnNoOrders(){
		int[] colOrders=new int[columnNoOrders.length];
		int i=0;
		for(String col:columnNoOrders){
			colOrders[i]=Integer.parseInt(col)-1;
			i++;
		}
		return colOrders;
	}
	// e.g {1a,2d,3a}
	public String[] getColumnIndexes(){
		return columnsIndexes;
	}
	// e.g 3 for {1a,2d,3a}
	public int getNoOfOrders(){
		return noOfOrders;
	}
}
