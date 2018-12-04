package cloud.jgo.utils.command;
// version 1.0.7
public interface InputSettable {
	public abstract String getInputValue();
	public abstract void setInputValue(String inputValue);
	public abstract boolean hasInputValueExploitable();
	public abstract void setInputValueExploitable(boolean exploitable);
}
