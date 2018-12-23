package cloud.jgo.utils.command;

public interface Sharer {
	// indica elementi che possono condividere
	public abstract Type getSharerType();
	public static enum Type{COMMAND,PARAMETER}
}
