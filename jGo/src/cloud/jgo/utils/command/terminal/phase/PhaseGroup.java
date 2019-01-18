package cloud.jgo.utils.command.terminal.phase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cloud.jgo.utils.command.terminal.phase.LocalPhaseTerminal.LocalPhase;

// version 1.0.9
public class PhaseGroup implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String getType() {
		return type;
	}

	public String getTarget() {
		return target;
	}

	private List<Phase> groups;
	private String type;
	private String target;

	public PhaseGroup(Phase... phases) {
		this.type = null ;
		this.target = null ;
		for (int i = 0; i < phases.length; i++) {
			((LocalPhase)phases[i]).setMembershipGroup(this);
		}
		groups = Arrays.asList(phases);
	}

	public PhaseGroup(String type, Phase... phases) {
		this.type = type;
		this.target = null ;
		for (int i = 0; i < phases.length; i++) {
			((LocalPhase)phases[i]).setMembershipGroup(this);
		}
		groups = Arrays.asList(phases);
	}

	public PhaseGroup(String type, String target, Phase... phases) {
		this.type = type;
		this.target = target;
		for (int i = 0; i < phases.length; i++) {
			((LocalPhase)phases[i]).setMembershipGroup(this);
		}
		this.groups = Arrays.asList(phases);
	}

	public PhaseGroup() {
		// TODO Auto-generated constructor stub
		this.groups = new ArrayList<>();
		this.type = null;
		this.target = null;
	}
	
	public Phase ph(String phaseName) {
		Phase ph = null ;
		for(Phase phase:groups) {
			if (phase.phaseName().equals(phaseName)) {
				ph = phase ;
				break;
			}
		}
		return ph ;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.type ;
	}

}
