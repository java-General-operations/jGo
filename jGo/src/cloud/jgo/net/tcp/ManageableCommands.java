package cloud.jgo.net.tcp;
// version 1.0.8

import cloud.jgo.utils.command.RemoteCommand;

public interface ManageableCommands {
	// ottiene un comando registrato nel Client o Server
	public abstract RemoteCommand getCMD(String onlyCommand);
}
