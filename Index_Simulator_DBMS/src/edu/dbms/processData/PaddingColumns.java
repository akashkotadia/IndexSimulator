package edu.dbms.processData;


import edu.dbms.data.Row;
import edu.dbms.data.Table;
import edu.dbms.data.TableMetaData;

public class PaddingColumns {

	final String STRING_PADDER=" ";
	final String INTEGER_PADDER="0";
	private TableMetaData meta;
	public PaddingColumns(TableMetaData meta){
		this.meta=meta;
	}
	public void padding(Table table){
		Row[] rows=table.getRows();
		int maxLength;
		String pad="";
		for(int col=0;col<meta.getNoOfColumns();col++){
			maxLength=meta.getColumnsMeta(col).getMaxLength();
			
			if(meta.getColumnsMeta(col).getDataType().equals("String")){
				for(Row r:rows){
					pad="";
					
					for(int i=0;i<(maxLength-r.getValue(col).length());i++){
						pad+=" ";
					}
					r.setValue(col, r.getValue(col)+pad);
				}	
			}
			else if(meta.getColumnsMeta(col).getDataType().equals("Integer")){
				for(Row r:rows){
					pad="";
					for(int i=0;i<maxLength-r.getValue(col).length();i++){
						pad+="0";
					}
					r.setValue(col, pad+r.getValue(col));
				}		
			}
		}
		
	}
}
