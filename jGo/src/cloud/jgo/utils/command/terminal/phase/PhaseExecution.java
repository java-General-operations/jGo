package cloud.jgo.utils.command.terminal.phase;

import cloud.jgo.utils.command.Sharer;
import cloud.jgo.utils.command.Sharer.Type;
import cloud.jgo.utils.command.execution.SharedExecution;

/**
 * @author Martire91 - <https://github.com/wasp91>
 * @version 1.0.0
 */
public abstract class PhaseExecution extends SharedExecution{

	private static final long serialVersionUID = 1L;
	private LocalPhaseTerminal phaseTerminal;
	
	public PhaseExecution(LocalPhaseTerminal t) {
		// TODO Auto-generated constructor stub
		this.phaseTerminal = t ;
	}
	
	/**
	 * @return the phaseTerminal
	 */
	public LocalPhaseTerminal getPhaseTerminal() {
		return phaseTerminal;
	}
	
	/* (non-Javadoc)
	 * @see cloud.jgo.utils.command.execution.SharedExecution#sharedExec(cloud.jgo.utils.command.Sharer)
	 */
	@Override
	protected Object sharedExec(Sharer sharer) {
		if (sharer!=null) {
			if (sharer.getSharerType().equals(Type.PHASE)) {
				Object result = sharedExec(((Phase)sharer));
				this.phaseTerminal.currentPhase = ((Phase)sharer);
			}
		}
		return null ;
	}
	
	protected abstract Object sharedExec(Phase phase);
}
