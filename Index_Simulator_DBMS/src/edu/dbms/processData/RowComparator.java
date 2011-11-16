package edu.dbms.processData;

import java.util.Comparator;

import edu.dbms.data.Row;

public class RowComparator implements Comparator<Row> {

	private int[] columnOrders;
	public RowComparator(int[] columnOrders){
		this.columnOrders=columnOrders;
	}
	
	@Override
	public int compare(Row row1, Row row2) {
		for(int colIndex=0;colIndex<columnOrders.length;colIndex++){
			
			if(row1.getValue(columnOrders[colIndex]).compareTo(row2.getValue(columnOrders[colIndex]))==0)
				continue;
			
			return row1.getValue(columnOrders[colIndex]).compareTo(row2.getValue(columnOrders[colIndex]));
		}
		return 0;
	}

}
