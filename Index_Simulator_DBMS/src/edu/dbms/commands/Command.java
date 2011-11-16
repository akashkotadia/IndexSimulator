package edu.dbms.commands;

import java.io.IOException;

public class Command {

	private String commandName;
	
	public Command(String commandName){
		this.commandName=commandName;
	}
	public String getCommandName(){
		return commandName;
	}
	
	public static void main(String args[]) throws IOException, InvalidInputException{
		
		String commandName=args[0];
		MainIndex mainIndex=MainIndex.getInstance();
		try{
			if(commandName.toLowerCase().equals("c")){
				if(args.length==3){
					CreateIndex command=new CreateIndex();
					command.setIndexName(args[1]);
					command.setIndexOrders(args[2]);
					command.execute();
				}
				else{
					throw new InvalidInputException();
				}
			}
			else if(commandName.toLowerCase().equals("l")){
				if(args.length==1){
					ListAllIndex command=new ListAllIndex();
					command.execute();
				}
				else{
					throw new InvalidInputException();
				}
			}
			else if(commandName.toLowerCase().equals("d")){
				if(args.length==2){
					DropIndex commmand=new DropIndex(args[1]);
					commmand.execute();
				}
				else{
					throw new InvalidInputException();
				}
			}
		}
		catch(InvalidInputException ex){
			System.out.println("Invalid Input!");
			ex.printStackTrace();
		}
		catch(Exception ex){
			System.out.println("Unknown Exception!");
			ex.printStackTrace();
		}
				
	}
}
