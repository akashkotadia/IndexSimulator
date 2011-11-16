package edu.dbms.data;

// Meta Data For single Table : 
//	- No Of Rows
//  - No Of Columns
// 	- Meta for each Column

public class TableMetaData {
	
	private int noOfRows;
	private int noOfColumns;
	private ColumnMeta[] colMeta;
	
	public int getNoOfRecords() {
		return noOfRows;
	}

	public void setNoOfRecords(int noOfRecords) {
		this.noOfRows = noOfRecords;
	}

	public void setColumnsMeta(String[] colConfigs){
		colMeta=new ColumnMeta[colConfigs.length];
		int i=0;
		for(String col:colConfigs){
			colMeta[i]=new ColumnMeta(col);
			i++;
		}
		setNoOfColumns(colConfigs.length);
	}
	public ColumnMeta getColumnsMeta(int colIndex) {
		return colMeta[colIndex];
	}

	public int getNoOfColumns() {
		return noOfColumns;
	}

	public void setNoOfColumns(int noOfColumns) {
		this.noOfColumns = noOfColumns;
	}
}
