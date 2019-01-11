package cloud.jgo.utils.command.annotations;

public interface Configurable{
	// serve per configurare
	public abstract String getTarget();
	public abstract Class<?extends Configurable> getTargetType();
	public abstract boolean isCompleted();
}
