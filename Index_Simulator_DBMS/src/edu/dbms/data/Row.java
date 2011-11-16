package edu.dbms.data;

// Represent single row: rowID + aggregator of columns
public class Row {

	private int rowId;
	private Column[] cols;

	public Row(int noOfColumns,int rowCounter){
		cols=new Column[noOfColumns];
		this.rowId=rowCounter+1;
	}

	public int getRowId() {
		return rowId;
	}
	
	public void setValue(int colIndex,String value){
		cols[colIndex]=new Column();
		cols[colIndex].setValue(value);
	}
	public void insert(String[] values){
		if(values.length==cols.length){
			int i=0;
			for(String v:values){
				cols[i]=new Column();
				cols[i].setValue(v);
				i++;
			}
		}
	}
	public String getValue(int colIndex){
		return cols[colIndex].getValue();
	}
}
