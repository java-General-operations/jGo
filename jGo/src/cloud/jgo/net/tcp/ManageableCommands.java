package cloud.jgo.net.tcp;
// version 1.0.8

import cloud.jgo.utils.command.RemoteCommand;

public interface ManageableCommands {
	public abstract RemoteCommand getCMD(String onlyCommand);
	public abstract boolean isCMD(String onlyCommand);
}
