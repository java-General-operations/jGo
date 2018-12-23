package cloud.jgo.utils.command.execution;

import java.util.ArrayList;
import java.util.List;

import cloud.jgo.utils.command.Sharer;

public abstract class SharedExecution extends Execution{
	
	public abstract Object sharedExec(Sharer sharer);
	
	private Sharer currentSharer = null ;
	
	public void setCurrentSharer(Sharer currentSharer) {
		this.currentSharer = currentSharer;
	}
	
	public Sharer getCurrentSharer() {
		return this.currentSharer;
	}

	// non si usa questo metodo, ma il nuovo
	@Override
	public Object exec() {
		return sharedExec(getCurrentSharer());
	}
}
