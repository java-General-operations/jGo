package test;

import java.util.ArrayList;
import java.util.List;

import cloud.jgo.utils.command.LocalCommand;
import cloud.jgo.utils.command.Parameter;
import cloud.jgo.utils.command.Sharer;
import cloud.jgo.utils.command.Sharer.Type;
import cloud.jgo.utils.command.execution.SharedExecution;

public class MySharedExecution extends SharedExecution{

	@Override
	public Object sharedExec(Sharer sharer) {
		if (sharer.getSharerType()==Type.COMMAND) {
			LocalCommand command = (LocalCommand) sharer ;
			
			String inputValue = null ;
			
			inputValue = command.getInputValue();
			
			if (command.getCommand().equals("p")) {
				
				return "esecuzione di p:"+inputValue;
			}
			else if(command.getCommand().equals("p2")) {
				
				return "esecuzione di p2:"+inputValue;
			}
			
			
		}
		else {
			// PARAMETER
			Parameter p = (Parameter) sharer;
			System.out.println("esecuzione del param:"+p.getParam()+" - inputValue:"+p.getInputValue()+" - parent:"+p.getParent().getCommand());
			
		}
		return null ;
	}

}
