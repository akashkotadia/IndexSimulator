package edu.dbms.data;


public class Table {

	private TableMetaData meta;
	private Row[] rows;
	private int rowCounter;
	private String tableName;
	
	public Table(String tableName){
		meta=new TableMetaData();
		this.tableName=tableName;
	}
	public void create(String allColumnTypes,String noOfRecords){
		meta.setColumnsMeta(allColumnTypes.split(" "));
		meta.setNoOfRecords(Integer.parseInt(noOfRecords));
		rows=new Row[meta.getNoOfRecords()];
		rowCounter=0;
	}
	public void insert(String rowData){
		rows[rowCounter]=new Row(meta.getNoOfColumns(),rowCounter);
		rows[rowCounter].insert(rowData.split(" "));
		rowCounter++;
	}
	public String getValue(int row,int col){
		return rows[row].getValue(col);
	}
	public Row getRow(int rowId){
		return rows[rowId];
	}
	public TableMetaData getMetaData(){
		return meta;
	}
	public Row[] getRows(){
		return rows;
	}
	public void printTable(){
		for(Row r:rows){
			for(int i=0;i<meta.getNoOfColumns();i++){
				System.out.print(r.getValue(i)+"|");
			}
			System.out.println();
		}
	}
	public String getTableName() {
		return tableName;
	}
}
