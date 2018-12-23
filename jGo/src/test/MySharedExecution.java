package test;

import java.util.ArrayList;
import java.util.List;

import cloud.jgo.utils.command.LocalCommand;
import cloud.jgo.utils.command.Sharer;
import cloud.jgo.utils.command.Sharer.Type;
import cloud.jgo.utils.command.execution.SharedExecution;

public class MySharedExecution extends SharedExecution{

	@Override
	public Object sharedExec(Sharer sharer) {
		if (sharer.getSharerType()==Type.COMMAND) {
			
			// qui controllo di che comando si tratta 
			
			if (((LocalCommand)sharer).getCommand().equals("p")) {
				System.out.println("Esecuzione di p ...");
			}
			else if(((LocalCommand)sharer).getCommand().equals("p2")){
				System.out.println("Esecuzione di p2 ...");
			}
			
		}
		return null ;
	}

}
