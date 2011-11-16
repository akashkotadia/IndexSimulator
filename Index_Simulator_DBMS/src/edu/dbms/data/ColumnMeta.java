package edu.dbms.data;

// Meta Data For single Column.
public class ColumnMeta {

	private String colName;
	private String dataType;
	private int maxLength;
	
	public ColumnMeta(String colName){
		this.colName=colName;
		setParams();
	}
	public void setParams(){
		String params=(String)ColumnCodes.getInstance().getProperties().get(colName);
		dataType=params.split("-")[0];
		maxLength=Integer.parseInt(params.split("-")[1]);
	}
	public String getDataType(){
		return dataType;
	}
	public int getMaxLength(){
		return maxLength;
	}
}
