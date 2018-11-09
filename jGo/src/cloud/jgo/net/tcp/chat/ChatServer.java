package cloud.jgo.net.tcp.chat;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import cloud.jgo.net.ServerType;
import cloud.jgo.net.handlers.Handler;
import cloud.jgo.net.tcp.DefaultSocket;
import cloud.jgo.net.tcp.DefaultTCPServer;
import cloud.jgo.net.tcp.NoReadingSourceException;
import cloud.jgo.net.tcp.TCPHandlerConnection;
import cloud.jgo.net.tcp.TCPServer;
import cloud.jgo.net.tcp.TCPServerConfiguration;
import cloud.jgo.net.tcp.TCPServerTypes;

public class ChatServer extends DefaultTCPServer{
	
	static {
		TYPE_SERVER = TCPServerTypes.TYPE_CHAT_TCP;
	}
	private int typeNotifier = NotifierServerSide.TYPE_UDP ; // default

	public int getTypeNotifier() {
		return typeNotifier;
	}

	public void setTypeNotifier(int typeNotifier) {
		this.typeNotifier = typeNotifier;
	}

	public ChatServer() {
	}
	
	public static List<Socket> getSockets() {
		return sockets;
	}

	private static Chat chat = new Chat();
	private static List<Socket>sockets = new ArrayList<>();

	
	public static Chat getChat() {
		return chat;
	}
	
	@Override
	protected ServerType getType() {
		// TODO Auto-generated method stub
		return TCPServerTypes.TYPE_CHAT_TCP ;
	}


	@Override
	public void impl(Handler handler) {
		sockets.add(((TCPHandlerConnection)handler).getSocket());
		// attivo il thread 
		handler.startSession();
	}

}
