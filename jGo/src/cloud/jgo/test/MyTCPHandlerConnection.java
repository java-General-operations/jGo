package cloud.jgo.test;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.io.IOException;

import cloud.jgo.net.tcp.TCPHandlerConnection;

/**
 * @author Martire91 - <https://github.com/wasp91>
 * @version 1.0.0
 */
public class MyTCPHandlerConnection extends TCPHandlerConnection{

	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see cloud.jgo.net.handlers.HandlerConnection#manage()
	 */
	@Override
	public void manage()
			throws ClassNotFoundException, IOException, InterruptedException, HeadlessException, AWTException {
		System.out.println(receive());
	}

}
