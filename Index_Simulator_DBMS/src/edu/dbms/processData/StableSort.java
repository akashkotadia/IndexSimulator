package edu.dbms.processData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.dbms.data.Row;


public class StableSort {

	public static void sort(Row[] rows,int[] columnOrders){
		
		// Convert To List
		List<Row> rowList=new ArrayList<Row>();
		for(Row r:rows){
			rowList.add(r);
		}
		
		// Sort List
		Collections.sort(rowList,new RowComparator(columnOrders));
		
		// Back to Array
		for(int i=0;i<rows.length;i++){
			rows[i]=rowList.get(i);
		}
	}
	
	/* Testing...
	public static void main(String[] args) throws IOException{
		Table t=FileToTable.convert(Constants.TABLE_DIRECTORY+"t1.tab", "t1");
		t.printTable();
		System.out.println("=============================================");
		int[] columnOrders={0,1,2};
		StableSort.sort(t.getRows(), columnOrders);
		t.printTable();
	}*/
}
