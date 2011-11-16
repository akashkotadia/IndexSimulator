package edu.dbms.processData;

import edu.dbms.commands.CreateIndex;
import edu.dbms.data.Row;
import edu.dbms.data.TableMetaData;

public class ComplementColumn {

	public TableMetaData meta;
	public ComplementColumn(TableMetaData meta){
		this.meta=meta;
	}
	public void complement(Row[] rows,CreateIndex command){
		for(int colIndex=0;colIndex<command.getNoOfOrders();colIndex++){
			if(command.getColumnsortOrder(String.valueOf(colIndex+1)).equals("d")){
				if(meta.getColumnsMeta(colIndex).getDataType().equals("String")){
					complementStrings(rows, colIndex);
				}
				else if(meta.getColumnsMeta(colIndex).getDataType().equals("Integer")){
					complementIntegers(rows, colIndex);
				}
			}
		}
	}
	public void complementIntegers(Row[] rows,int colIndex){
		for(Row r:rows){
			r.setValue(colIndex, String.valueOf(9999999999L-Long.parseLong(r.getValue(colIndex))));
		}
	}
	public void complementStrings(Row[] rows,int colIndex){
		char[] array;
		for(Row r:rows){
			array=new char[r.getValue(colIndex).length()];
			int i=0;
			for(char c:r.getValue(colIndex).toCharArray()){
				array[i]=(char)('z'-c+'a');
				i++;
			}
			r.setValue(colIndex, String.valueOf(array));
		}
	}
}
