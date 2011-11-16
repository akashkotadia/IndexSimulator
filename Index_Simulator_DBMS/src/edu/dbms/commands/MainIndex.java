package edu.dbms.commands;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;


public class MainIndex {

	private static MainIndex instance=null;
	private static HashMap<String,ArrayList<String>> mainIndexTable=new HashMap<String,ArrayList<String>>();
	
	private MainIndex(){
		
	}
	public static MainIndex getInstance(){
		if(instance==null){
			instance=new MainIndex();
			loadFile(Constants.INDEX_DIRECTORY+"mainIndex.idx");
		}
		return instance;
	}
	public static void loadFile(String filePath){
        try
        {
        	FileInputStream fis=new FileInputStream(filePath);
			DataInputStream ds=new DataInputStream(fis);
			BufferedReader br=new BufferedReader(new InputStreamReader(ds));
            
            String line=null;
            while((line=br.readLine())!=null){
            	String[] array=line.split(" ");
            	ArrayList<String> tmpList=new ArrayList<String>();
            	for(int i=1;i<array.length;i++){
            		tmpList.add(array[i]);
            	}
            	mainIndexTable.put(array[0],tmpList);
            }
        }
        catch (IOException ex)
        {
        	ex.printStackTrace();
        }
	}
	public void insert(String indexName,String[] orders,int noOfColumns) throws InvalidInputException{
		if(mainIndexTable.containsKey(indexName)){
			throw new InvalidInputException();
		}
		else{
			ArrayList<String> tmpList=new ArrayList<String>();
			for(String o:orders){
				tmpList.add(o);
			}
			for(int i=tmpList.size();i<noOfColumns;i++){
				tmpList.add("-");
			}
			mainIndexTable.put(indexName, tmpList);
		}
	}
	public void delete(String indexName) throws InvalidInputException{
		if(!mainIndexTable.containsKey(indexName)){
			throw new InvalidInputException();
		}
		else{
			mainIndexTable.remove(indexName);
		}
	}
	public void printAllIndex(){
		
		for(String key:mainIndexTable.keySet()){
			System.out.print(key+" ");
			for(String s:mainIndexTable.get(key)){
				System.out.print(s+" ");
			}
			System.out.println();
		}
	}
	public void dumpToFile() throws IOException{
		  FileWriter fstream = new FileWriter(Constants.INDEX_DIRECTORY+"mainIndex.idx");
		  BufferedWriter out = new BufferedWriter(fstream);
		  
		  for(String key:mainIndexTable.keySet()){
			  out.write(key+" ");
			  for(String s:mainIndexTable.get(key)){
				  out.write(s+" ");
			  }
			  out.write("\n");
		  }
		  out.close();
	}
	public boolean contains(String indexName){
		return mainIndexTable.containsKey(indexName);
	}
}
