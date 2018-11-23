/**
 * JGO - A pure Java library,
 * its purpose is to make life easier for the programmer.
 *
 * J - Java
 * G - General
 * O - Operations
 *
 * URL Software : https://www.jgo.cloud/
 * URL Documentation : https://www.jgo.cloud/docs/
 *
 * Copyright © 2018 - Marco Martire (www.jgo.cloud)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the MIT License.
 *
 * You may obtain a copy of License at :
 * https://www.jgo.cloud/LICENSE.txt
 *
 * To collaborate on this project, you need to do it from the software site.
 * 
 */
package cloud.jgo;
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipEntry;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.xml.bind.JAXBException;
import cloud.jgo.SMTPHosts.SMTPEntry;
import cloud.jgo.downloads.Download;
import cloud.jgo.downloads.DownloadWorker;
import cloud.jgo.file_manager.JFileManager;
import cloud.jgo.file_manager.JFileView;
import cloud.jgo.io.jon.JON;
import cloud.jgo.jjdom.JjDom;
import cloud.jgo.jjdom.function;
import cloud.jgo.net.Configuration;
import cloud.jgo.net.Server;
import cloud.jgo.net.ServerTypes;
import cloud.jgo.net.factorys.ServersFactory;
import cloud.jgo.net.tcp.TCPServer;
import cloud.jgo.net.tcp.TCPServerTypes;
import cloud.jgo.net.tcp.http.HTTPServer;
import cloud.jgo.net.tcp.http.HTTPServerConfiguration;
import cloud.jgo.net.tcp.http.headers.Header;
import cloud.jgo.net.tcp.http.jor.JOR;
import cloud.jgo.net.tcp.http.jor.JORServer;
import cloud.jgo.net.tcp.login.TCPLoginServer;
import cloud.jgo.registry.WinRegistry;
import cloud.jgo.utils.command.terminal.LinuxTerminal;
import cloud.jgo.utils.command.terminal.LocalTerminal;
import cloud.jgo.utils.command.terminal.WinTerminal;
import cloud.jgo.utils.command.terminal.phase.LocalPhaseTerminal;
/**
 * @author Martire91
 * @see £#_A
 * @see £#_S
 * @see £#_W
 * @version 1.0.5
   <!--<link rel='styleSheet' href='https://www.jgo.cloud/docStyle.css'> --><br>
    <!--Author : *** Marco Martire *** -->  
     <img id='logo'src='https://www.jgo.cloud/wp-content/uploads/2018/11/jgo2.png' alt='logo jgo' style='float: left;margin-right:15px;'><br>
   <h1 style='color: #282828;'>jGo<strong style='color: green;'>.cloud</strong></h1>
   <em>java General operations</em><br><br><br>
   <strong>Description :</strong><br> This class facilitates all the difficult tasks
   For example,In a code line, you take a screenshot, and do other general operations. 
   the calls to the methods can be concatenated, so that different operations can be performed in the same programmatic instruction.
   jGo was born to facilitate the life of the java programmer, besides it provides different technologies like {@link JjDom} and {@link JON} for example.
   jGo also allows you to go to specific phases for specific programming +, for example if our application is a JFrame, we can find the general methods in the _S phase, object of the SwingUtils class
   Moreover jGo contains 3 phases for specific programming, for example if our application is a {@link JFrame}, we will find the general Swing methods in the {@link £#_S} phase, static constant of type {@link SwingUtils}.
   However the WebUtils and AndroidUtils phases are still empty. The goal is to create a jar package comprising a wide range of general methods available to the user, so as to facilitate the life of the programmer, increase the performance of the program, and not less important, save a lot of time in terms of writing of the code.
   Moreover jGo provides very powerful objects, for example it is possible to create very powerful servers, for the moment servers whose protocol must be tcp, or an extension like http.
   Study the following server creation packages well:<br>
   <ul>
   <li><a href='#'>cloud.jgo.net</a> - contains all important interfaces, including the {@link Server} interface</li>
   <li><a href='#'>cloud.jgo.net.tcp</a> - contains all the tcp classes needed to implement a TCP server - {@link TCPServer}
   <li><a href='#'>cloud.jgo.net.http</a> - contains all the http classes needed to implement a HTTP server - {@link HTTPServer}
   <li><a href='#'>cloud.jgo.net.http.jor</a> - contains all the classes necessary for the implementation of a server with JOR technology - {@link JORServer}
   </ul>
   Or we can implement a terminal thanks to the objects contained in the package <a href='#'>cloud.jgo.utils.command</a> and <a href='#'>cloud.jgo.utils.command.terminal</a>. 
   Then we have {@link JjDom}, a technology that allows us to create an HTML document and write <a href='https://jquery.com/'>jquery</a> instructions in java. Furthermore we can of course write the css code necessary for our document.
   To use jGo, read the <a href='https://www.jgo.cloud/cookbook'>cookbook</a> and consult the website at <a href='https://www.jgo.cloud/'>https://www.jgo.cloud/</a>. 
   <br>
   <em>Have fun !</em>
   <br><br>
   JGO is very powerful and it follows the <a href='https://jquery.com/'>jquery</a> policy :<br><br>
   <em><u><a href='https://medium.com/laboratoria-how-to/write-less-do-more-e049d0824f4'>(Write a little and get a lot)</a></u></em><br><br>
   <strong>j</strong> - <em>java</em><br><br>
   <strong>G</strong> - <em>General</em><br><br>
   <strong>o</strong> - <em>operations</em><br><br>
   <hr>
   <strong>1 Example-</strong>This instruction prints in the console "hello world" and "hello world 2",<br>also increases and decreases twice a counter:<br><br>
<div class="cm_source">
<code class="cm_n_n_n_0">1 | &pound;._O(</code><code class="cm_n_n_n_2A00FF">&quot;Hello&nbsp;world&quot;</code><code class="cm_n_n_n_0">).increment().decrement()<br>
  2 |&nbsp;  ._O(</code><code class="cm_n_n_n_2A00FF">&quot;Hello&nbsp;World&nbsp;2&quot;</code><code class="cm_n_n_n_0">).increment().decrement();</code>
</div>
   <br>
   <strong>2 Example -</strong>
   Opens the terminal, opens the notepad and emits an acoustic signal,<br>also prints in the console "Hello world":<br><br>
<div class="cm_source">
<code class="cm_n_n_n_0">1 | &pound;.openTerminal().openNotepad().beep()._O(</code><code class="cm_n_n_n_2A00FF">&quot;Hello&nbsp;World&quot;</code><code class="cm_n_n_n_0">);</code>
</div>
<br>
 <strong>3 Example -</strong>This instruction creates a file, a folder,<br>and prints hello world using two alert windows:<br><br>
<div class="cm_source">
<code class="cm_n_n_n_0">1 | &pound;.createFile(</code><code class="cm_n_n_n_2A00FF">&quot;hello&nbsp;world&quot;</code><code class="cm_n_n_n_0">).md().alert(</code><code class="cm_n_n_n_2A00FF">&quot;Hello&quot;</code><code class="cm_n_n_n_0">).alert(</code><code class="cm_n_n_n_2A00FF">&quot;World&quot;</code><code class="cm_n_n_n_0">);</code>
</div>
<br>
<strong>4 Example (Iteration with jGo) - </strong>Simple use of the "each" method of jGo:<br><br>
<div class="cm_source">
<code class="cm_n_n_n_0">1 &nbsp;| String[]arr&nbsp;=&nbsp;{</code><code class="cm_n_n_n_2A00FF">&quot;Alan&quot;</code><code class="cm_n_n_n_0">,</code><code class="cm_n_n_n_2A00FF">&quot;Peter&quot;</code><code class="cm_n_n_n_0">,</code><code class="cm_n_n_n_2A00FF">&quot;Lucas&quot;</code><code class="cm_n_n_n_0">};<br>
  2 &nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;<br>
  3 &nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&pound;.each(arr,</code><code class="cm_b_n_n_7F0055">new</code><code class="cm_n_n_n_0">&nbsp;&pound;Func()&nbsp;{<br>
  4 &nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
  5 &nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;@Override<br>
  6 &nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="cm_b_n_n_7F0055">public</code><code class="cm_n_n_n_0">&nbsp;Object&nbsp;function(Object&nbsp;e)&nbsp;{<br>
  7 &nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
  8 &nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;System.out.println(</code><code class="cm_n_n_n_2A00FF">&quot;Name:&quot;</code><code class="cm_n_n_n_0">+e);<br>
  9 &nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
 10 |&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="cm_b_n_n_7F0055">return</code><code class="cm_n_n_n_0">&nbsp;</code><code class="cm_b_n_n_7F0055">true</code><code class="cm_n_n_n_0">&nbsp;;<br>
 11 |&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br>
 12 |&nbsp;&nbsp;&nbsp;&nbsp;});</code>
</div><br>
<strong>5 Example - </strong>Obviously more complex instructions can be combined <br>
Increment the counter twice, create a file, iterate the array using the "each" method,
at the end it queries on google, decreases the value of the counter, and takes its value:<br><br>
<div class="cm_source">
<code class='cm_n_n_n_0'>1 &nbsp;|</code><code class="cm_b_n_n_7F0055"> int</code><code class="cm_n_n_n_0">&nbsp;counterValue&nbsp;=&nbsp;<br>
  2 &nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
  3 &nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&pound;.increment().increment().createFile(</code><code class="cm_n_n_n_2A00FF">&quot;test.json&quot;</code><code class="cm_n_n_n_0">)<br>
  4 &nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;.each(arr,</code><code class="cm_b_n_n_7F0055">new</code><code class="cm_n_n_n_0">&nbsp;&pound;Func()&nbsp;{<br>
  5 &nbsp;|
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
  6 &nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;@Override<br>
  7 &nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="cm_b_n_n_7F0055">public</code><code class="cm_n_n_n_0">&nbsp;Object&nbsp;function(Object&nbsp;e)&nbsp;{<br>
  8 &nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
  9 &nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&pound;._O(</code><code class="cm_n_n_n_2A00FF">&quot;Name&nbsp;:&quot;</code><code class="cm_n_n_n_0">+e);<br>
  10 |&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
  11 |&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="cm_b_n_n_7F0055">return</code><code class="cm_n_n_n_0">&nbsp;</code><code class="cm_b_n_n_7F0055">true</code><code class="cm_n_n_n_0">&nbsp;;<br>
  12 |&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br>
  13 |&nbsp;&nbsp;&nbsp;&nbsp;}).executeGoogleQuery(</code><code class="cm_n_n_n_2A00FF">&quot;jGo&quot;</code><code class="cm_n_n_n_0">).decrement().value();</code>
</div>
<br>
<strong>In jGo there are 3 phases :</strong><br><br>
<ul>
<li>{@link #_A} - {@link AndroidUtils} - (<code>cloud.jgo.£.AndroidUtils</code>) - <em style="color: #303030;">&nbsp;for <a href='https://it.wikipedia.org/wiki/Android'>android</a> programming</em></li>
<li>{@link #_S} - {@link SwingUtils} - (<code>cloud.jgo.£.SwingUtils</code>) - <em style="color: #303030;">&nbsp;for <a href='https://it.wikipedia.org/wiki/Swing_(Java)'>swing</a> programming</em></li>
<li>{@link #_W} - {@link WebUtils} - (<code>cloud.jgo.£.WebUtils</code>) - <em style="color: #303030;">&nbsp;for <a href='https://it.wikipedia.org/wiki/World_Wide_Web'>web</a> programming</em></li>
</ul><br>
For example, let's see how to apply some swing methods :<br><br>
<div class="cm_source">
<code class="cm_n_n_n_0">1 | &pound;.<strong style='color: blue;'>_S</strong>.hide(jframe,Effect.<strong style='color: blue;'>SLOW</strong>).show(jframe,&nbsp;Effect.<strong style='color: blue;'>SLOW</strong>);</code>
</div>
<br>
We can associate a click event to the button :<br><br>
<div class="cm_source">
<code class="cm_n_n_n_0">1 &nbsp;| &pound;.<strong class='costants'>_S</strong>.click(button,</code><code class="cm_b_n_n_7F0055">new</code><code class="cm_n_n_n_0">&nbsp;&pound;Func()&nbsp;{<br>
2 &nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
3 &nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;@Override<br>
4 &nbsp;| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="cm_b_n_n_7F0055">public</code><code class="cm_n_n_n_0">&nbsp;Object&nbsp;function(Object&nbsp;e)&nbsp;{<br>
5 &nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
6 &nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
7 &nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="cm_b_n_n_7F0055">return</code><code class="cm_n_n_n_0">&nbsp;&pound;.alert(</code><code class="cm_n_n_n_2A00FF">&quot;Hello&nbsp;world&quot;</code><code class="cm_n_n_n_0">);<br>
8 &nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
9 &nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br>
10 |&nbsp;&nbsp;&nbsp;&nbsp;});</code>
</div><br>
<strong>In any Phase we find ourselves, we can return to the access point using the "home ()" method :</strong><br><br>
<div class="cm_source">
<code class="cm_n_n_n_0">1 | &pound;.<strong style='color: blue;'>_S</strong>.applyEffect(Effect.<strong style='color: blue;'>CRAZY</strong>,jframe,jframe2,jframe3)<br>
2 |&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;.home().alert(</code><code class="cm_n_n_n_2A00FF">&quot;Returned&quot;</code><code class="cm_n_n_n_0">);</code>
</div>
<br>
<strong>Now let's move on to the jGo technologies :</strong><br><br>
<ul>
<li>{@link JON} - <em style='color:  #00593B;'>Java Object Notation</em> - (<code>cloud.jgo.io.jon</code>)</li>
<li>{@link JOR} - <em style='color:  #00593B;'>Java Object Representation</em> - (<code>cloud.jgo.io.jon</code>)</li>
<li>{@link JjDom} - <em style='color:  #00593B;'>Java <a href='#'>jQuery</a> Dom</em> - (<code>cloud.jgo.io.jon</code>)</li>
</ul>
<br>
<em>Visit the jGo website</em> : <a href='https://www.jgo.cloud/'>https://www.jgo.cloud/</a>
 */
public class £{
	/**
	 * 
	 * @author Martire91<br>
	 * This enum contains all kinds of effects that can be applied
	 * 
	 */
	public static enum Effect {
		SLOW,REALLY_SLOW,FAST,IMPERCEPTIBLE,REALLY_FAST,SUPER_FAST,
		VERTICAL,HORIZONTAL,CRAZY,TRANSPARENCY,MAXIMIZED,MINIMIZED
		// continuare da qui facendo la costante super fast
	}
	/**
	 * The constant represents a textual space
	 */
	public static String SPACE = " ";
	
	/**
	 * The constant Windows CMD
	 */
	public final static WinTerminal CMD = new WinTerminal() ;
	/**
	 * The constant Linux SHELL
	 */
	public final static LinuxTerminal SHELL = new LinuxTerminal();
	/**
	 * The JGO format
	 */
	public final static String FORMAT_JGO = ".jgo";
	/**
	 * The server types
	 */
	public final static ServerTypes SERVER_TYPES = new ServerTypes();
	/**
	 * The tcp server types
	 */
	public final static TCPServerTypes TCP_SERVER_TYPES = new TCPServerTypes();
	/**
	 * The download object
	 */
	private static final Download download = new Download();
	/**
	 * The windows registry object
	 */
	public final static WinRegistry WinRegistry = null ;
	/**
	 * The mex type : INFO
	 */
	public static final String CONSOLE_MEX_INFO = "INFORMATION";
	/**
	 * The mex type : WARNING
	 */
	public static final String CONSOLE_MEX_WARNING = "WARNING";
	/**
	 * The mex type : ERROR
	 */
	public static final String CONSOLE_MEX_ERROR = "ERROR";
	private static final JFileManager fileManager = new JFileManager();
	public static Encrypts encrypt = null ;
	private static Recorder recorder = null ;
	private static MoveFrame move_frame = null ;
	private static int counterScreenShot = 0 ;
	private static RandomAccessFile access = null ;
	private static FileChannel channel = null ;
	/**
	 * java.util.Arrays
	 */
	public final static Arrays ARR = null ;
	/**
	 * java.util.Collections
	 */
	public final static Collections COLL = null ;
	
	/**
	 * Equivalent to java.lang.Math
	 */
	public static Math MT = null ;
	static{
		Constructor<?> constructor = Math.class.getDeclaredConstructors()[0];
	    constructor.setAccessible(true);
	    try {
			MT = (Math) constructor.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Useful instance to find smtp hosts for sending emails
	 */
	public final static SMTPHosts SMTP_HOSTS = SMTPHosts.getInstance();
	private static FileLock lockFile = null ;
	private static int valueMemorized = 0; // questo qui entra in gioco solo quando viene invocato il metodo mark(),e prende memorizza il value attuale
	/**
	 * Path Home 
	 */
	public final static String HOME_PATH = FileSystemView.getFileSystemView().getHomeDirectory().getPath()+File.separator;
	/**
	 * Path local disk Windows
	 */
	public final static String LOCAL_DISK_PATH = "C:"+File.separator;
	/**
	 * Path User
	 */
	public final static String USER_PATH = System.getProperty("user.home")+File.separator;
	/**
	 *Path Documents Windows
	 */
	public final static String DOCUMENTS_PATH = FileSystemView.getFileSystemView().getDefaultDirectory().getPath()+File.separator;
	
	/**
	 * The system file separator
	 */
	public final static String SEPARATOR = File.separator ;
	public static final String FILE_MODE_READ = "r";
	public static final String FILE_MODE_WRITE = "w";
	public static final String FILE_MODE_DELETE = "d";
	public static final String FILE_MODE_APPEND = "a";
	public static final String FILE_MODE_RENAME = "rn";
	public static final String FILE_MODE_CREATE = "c";
	
	/**
	 * Technology JON - Java Object Notation
	 */
	public static JON JON ;
	

	// programming type :
	
	/**
	 * <strong>Swing</strong> <em>Methods</em>
	 */
	public final static SwingUtils _S =  new SwingUtils();
	
	/**
	 *  <strong>Android</strong> <em>Methods</em> -  <em style='color:red'>Under development</em>
	 */
	public final static AndroidUtils _A = new AndroidUtils();
	
	/**
	 *  <strong>Web</strong> <em>Methods</em> -  <em style='color:red'>Under development</em>
	 */
	public final static WebUtils _W = new WebUtils();
	// nuovi metodi - version 1.0.5
	/**
	 * This method iterates the array as if it were a loop
	 * @param arr the array
	 * @param func the function that you want to perform at each iteration
	 * @param <T> the array type
	 * @return the jGo access point
	 */
	public static <T> £ each(T[]arr,£Func func){
		for (int i = 0; i < arr.length; i++) {
			Boolean continue_ = (Boolean) func.function(arr[i]);
			if (!continue_) {
				break ;
			}
		}
		return instance ;
	}
	
	// version 1.0.6
	/**
	 * This method iterates the hashtable as if it were a loop
	 * @param hashtable the hashtable
	 * @param func the function that you want to perform at each iteration
	 * @return the jGo access point
	 */
	public static <T> £ each(Hashtable<T,T>hashtable,£Func func){
		Iterator<Entry<T,T>>iterator = hashtable.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<T, T> entry = (Map.Entry<T, T>) iterator.next();
			boolean continue_ = (boolean) func.function(entry);
			if (!continue_) {
				break ;
			}
		}
		return instance ;
	}
	
	
	// version 1.0.6
	/**
	 * This method iterates the hashtable as if it were a loop
	 * @param hashtable the hashtable
	 * @param func the function that you want to perform at each iteration
	 * @return the jGo access point
	 */
	public static <T> £ each2(Hashtable<Object,Object>hashtable,£Func func){
		Iterator<Entry<Object,Object>>iterator = hashtable.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Object, Object> entry = iterator.next();
			boolean continue_ = (boolean) func.function(entry);
			if (!continue_) {
				break ;
			}
		}
		return instance ;
	}
	
	
	// version 1.0.6
		/**
		 * This method iterates the hashtable as if it were a loop
		 * @param hashtable the hashtable
		 * @param func the function that you want to perform at each iteration
		 * @return the jGo access point
		 */
		public static <T> £ each3(Hashtable<String,Object>hashtable,£Func func){
			Iterator<Entry<String,Object>>iterator = hashtable.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<String, Object> entry = iterator.next();
				boolean continue_ = (boolean) func.function(entry);
				if (!continue_) {
					break ;
				}
			}
			return instance ;
		}
	/**
	 * This method iterates the array as if it were a loop.<br>
	 * It also allows you to specify an index to start from
	 * @param arr the array
	 * @param func the function that you want to perform at each iteration
	 * @param initialIndex start index
	 * @param <T> the array type
	 * @return the jGo access point
	 */
	public static <T> £ each(T[]arr,£Func func,int initialIndex){
		for (int i = initialIndex; i < arr.length; i++) {
			Boolean continue_ = (Boolean) func.function(arr[i]);
			if (!continue_) {
				break ;
			}
		}
		return instance ;
	}
	/**
	 * If you pass this constant to the {@link £#each(Object[], String, £Func)}<br>
	 * - {@link £#each(List, String, £Func)} methods, <br>
	 * you access the index that is iterating
	 */
	public static final String EACH_INDEX = "index";
	/**
	 * If you pass this constant to the {@link £#each(Object[], String, £Func)}<br>
	 * - {@link £#each(List, String, £Func)} methods, <br>
	 * you access the object that is iterating
	 */
	public static final String EACH_OBJECT = "object";
	// valori concessi : index | object
	/**
	 * This method iterates the array as if it were a loop.<br>
	 * It also allows you to specify a constant that indicates<br>
	 * what type of element you want to access each iteration.<br>
	 * Granted values :<br>
	 * <ul>
	 * <li>{@link £#EACH_OBJECT} - Has access to the object that is iterating</li>
	 * <li>{@link £#EACH_INDEX} - Has access to the index that is iterating</li>
	 * </ul>
	 * <br>
	 * @param arr the array
	 * @param returnType the return type
	 * @param func the function that you want to perform at each iteration
	 * @param <T> the array type
	 * @return jGo access point
	 */
	public static <T> £ each(T[]arr,final String returnType,£Func func){
		switch(returnType){
		
		case EACH_OBJECT:
			
			return each(arr, func);
			
		case EACH_INDEX:
			for (int i = 0; i < arr.length; i++) {
				Boolean continue_ = (Boolean) func.function(new Integer(i));
				if (!continue_) {
					break ;
				}
			}
			break ;
		case EACH_OBJECT+EACH_INDEX:
			for (int i = 0; i < arr.length; i++) {
				Boolean continue_ = (Boolean) func.function(new Object[]{arr[i],new Integer(i)});
				if (!continue_) {
					break ;
				}
			}
			break ;
		case EACH_INDEX+EACH_OBJECT:
			for (int i = 0; i < arr.length; i++) {
				Boolean continue_ = (Boolean) func.function(new Object[]{arr[i],new Integer(i)});
				if (!continue_) {
					break ;
				}
			}
			break ;
		}
		return instance ;
	}
	/**
	 * This method iterates the list as if it were an iterator.<br>
	 * @param list the list
	 * @param func the function that you want to perform at each iteration
	 * @param <T> the list type
	 * @return jGo access point
	 */
	public static <T> £ each(List<T>list,£Func func){
		Iterator<T>iterator = list.iterator();
		while (iterator.hasNext()) {
			Object object = (Object) iterator.next();
			Boolean continue_=(Boolean) func.function(object);
			if (!continue_) {
				break ;
			}
		}
		return instance ;
	}
	/**
	 * This method iterates the list as if it were an iterator.<br>
	 * It also allows you to specify an index to start from
	 * @param list the list
	 * @param initialIndex start index
	 * @param func the function that you want to perform at each iteration
	 * @param <T> the list type
	 * @return jGo access point
	 */
	public static <T> £ each(List<T>list,int initialIndex,£Func func){
		for (int i = initialIndex; i < list.size(); i++) {
			T t = list.get(i);
			Boolean continue_=(Boolean) func.function(t);
			if (!continue_) {
				break ;
			}
		}
		return instance ;
	}
	/**
	 * This method iterates the list as if it were an iterator.<br>
	 *  It also allows you to specify a constant that indicates<br>
	 * what type of element you want to access each iteration.<br>
	 * Granted values :<br>
	 * <ul>
	 * <li>{@link £#EACH_OBJECT} - Has access to the object that is iterating</li>
	 * <li>{@link £#EACH_INDEX} - Has access to the index that is iterating</li>
	 * </ul>
	 * <br>
	 * @param list the list
	 * @param returnType the return type
	 * @param func the function that you want to perform at each iteration
	 * @param <T> the list type
	 * @return jGo access point
	 */
	public static <T> £ each(List<T>list,final String returnType,£Func func){
		switch(returnType){
		
		case EACH_OBJECT:
			
			return each(list, func);
			
		case EACH_INDEX:
			for (int i = 0; i < list.size(); i++) {
				Boolean continue_ = (Boolean) func.function(new Integer(i));
				if (!continue_) {
					break ;
				}
			}
			break ;
		case EACH_OBJECT+EACH_INDEX:
			for (int i = 0; i < list.size(); i++) {
				Boolean continue_ = (Boolean) func.function(new Object[]{list.get(i),new Integer(i)});
				if (!continue_) {
					break ;
				}
			}
			break ;
		case EACH_INDEX+EACH_OBJECT:
			for (int i = 0; i < list.size(); i++) {
				Boolean continue_ = (Boolean) func.function(new Object[]{list.get(i),new Integer(i)});
				if (!continue_) {
					break ;
				}
			}
			break ;
		}
		return instance ;
	}
	// version 1.0.2
	/**
	 * This method transforms an array into a list
	 * @param objects the objects array
	 * @return the list
	 */
	public static List<Object>list(Object[]objects){
		return Arrays.asList(objects);
	}
	// version 1.0.2
	/**
	 * This method transforms a list into an array
	 * @param list the objects list
	 * @return the array
	 */
	public static Object[]array(List<Object>list){
		return list.toArray();
	}

	

	/**
	 * 
	  Equivalent to JOptionPane.showMessagedialog(null,text);
	 * @param text the text you want to print
	 * @return the home instance
	 */
	public static £ alert(String text){
		JOptionPane.showMessageDialog(null,text);
		return instance ;
	}
	
	/**
	 * Equivalent to JOptionPane.showMessagedialog(null,text);
	 * @param text the alert text
	 * @param title the alert title
	 * @param messageType the alert type
	 * @param icon the alert icon
	 * @return the home instance
	 */
	public static £ alert(String text,String title,int messageType,ImageIcon icon){
		JOptionPane.showMessageDialog(null, text, title, messageType, icon);
		return instance ;
	}
	
	/**
	 * Escape on the string
	 * @param text the text
	 * @return the "text"
	 */
    public static String escp(String text){
    	return (text = "\""+text+"\"").trim();
    }
    
    /**
     * This method is equivalent to "System.exit(0);":
     * force the closure of the program in execution.
     * @return the home instance
     */
    public static £ forceClosingProgram(){
    	System.exit(0);
    	return instance ;
    }
    
    /**
     * This method executes the last instructions and then quits the program
     * @param function the instructions to be executed
     * @return the home instance
     */
    public static £ executeLastInstructions(£Func function){
    	function.function(null);
    	return forceClosingProgram();
    }
    
    
    /**
     * This method executes the last instructions and then quits the program
     * @param function the instructions to be executed
     * @param paramFunction the parameter to be passed to the function
     * @return the home instance
     */
    public static £ executeLastInstructions(£Func function,Object paramFunction){
    	function.function(paramFunction);
    	return forceClosingProgram();
    }
    // version 1.0.1
    /**
     * This method checks if the number is even
     * @param n the number
     * @return the flag
     */
    public static boolean isEven(int n){
    	if ((n%2)==0)return true ;
    	else return false ;	
    }
    // version 1.0.1
    /**
     * This method only gets the even elements of the array
     * @param ns the int array
     * @return the even numbers
     */
    public static int[]getArrayOfEvenNumbers(int[]ns){
    	boolean found = false ;
    	List<Integer>list = new ArrayList<>();
    	for (int i = 0; i < ns.length; i++) {
			if (isEven(ns[i])) {
				found = true ;
				list.add(ns[i]);
			}
		}
    	int[]arr = null;
    	if (found) {
			arr = new int[list.size()];
			for (int i = 0; i < list.size(); i++) {
				arr[i] = list.get(i);
			}
		}
    	return arr ;
    }
    // version 1.0.1
    /**
     * This method only gets the odd elements of the array
     * @param ns the int array
     * @return the odd numbers
     */
    public static int[]getArrayOfOddNumbers(int[]ns){
    	boolean found = false ;
    	List<Integer>list = new ArrayList<>();
    	for (int i = 0; i < ns.length; i++) {
			if (!isEven(ns[i])) {
				found = true ;
				list.add(ns[i]);
			}
		}
    	int[]arr = null;
    	if (found) {
			arr = new int[list.size()];
			for (int i = 0; i < list.size(); i++) {
				arr[i] = list.get(i);
			}
		}
    	return arr ;
    }
	/**
	 * Equivalent to JColorChooser
	 * @param initialColor the initial color
	 * @return the selected color
	 */
	public static Color chooseColor(Color initialColor){
		JColorChooser choose = new JColorChooser(initialColor);
		Color color = choose.showDialog(null, "Choose Color", initialColor);
		return color ;
	}
	/**
	 * This method encrypts the text with AES algorithm
	 * @param text the text you want to encrypt
	 * @param key the key you want to use to encrypt
	 * @return the encrypted string
	 */
	public static String AES_e(String text,Key key){
		
		try {
			text = getEncrypt().crypta(text,key);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return text ;
	}
	/**
	 * This method downloads a jar file from the internet
	 * @param url_ jar file url
	 * @return the jar file
	 */
	public static JarFile downloadJarFile(final String url_){
		//http://www.penalistaware.com/downloads/imageProgramm.jar
		URL url=null;
		try {
			url = new URL("jar:"+url_+"!/");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JarURLConnection conn=null;
		try {
			conn = (JarURLConnection) url.openConnection();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		// scarico il file 
		JarFile file=null;
		try {
			file = conn.getJarFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// mi creo un nuovo file jar 
		JarOutputStream out=null;
		try {
			out = new JarOutputStream(new FileOutputStream(new java.io.File("copy.jar")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// a questo punto, faccio iterare le iscrizioni del jarFile 
		Enumeration<JarEntry>entries = file.entries();
		while (entries.hasMoreElements()) {
			JarEntry jarEntry = (JarEntry) entries.nextElement();
			// controllo se si tratta di una cartella
			try {
				out.putNextEntry(jarEntry);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// a questo punto prendo i bytes di questo entry 
			
			BufferedInputStream input=null;
			try {
				input = new BufferedInputStream(file.getInputStream(jarEntry));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int leggi ;
			try {
				while ((leggi = input.read())!=-1) {
					out.write(leggi);
					out.flush();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// una volta che abbiamo scritto il buffer di bytes chiudo la iscrizione
			try {
				out.closeEntry();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// chiudo l'outputstream 
		try {
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return file ;
	}
	
	/**
	 * This method downloads a jar file from the internet
	 * @param url_ jar file url
	 * @return the jar file
	 */
	public static £ downloadJar(final String url_){
		//http://www.penalistaware.com/downloads/imageProgramm.jar
		URL url=null;
		try {
			url = new URL("jar:"+url_+"!/");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JarURLConnection conn=null;
		try {
			conn = (JarURLConnection) url.openConnection();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		// scarico il file 
		JarFile file=null;
		try {
			file = conn.getJarFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// mi creo un nuovo file jar 
		JarOutputStream out=null;
		try {
			out = new JarOutputStream(new FileOutputStream(new java.io.File("copy.jar")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// a questo punto, faccio iterare le iscrizioni del jarFile 
		Enumeration<JarEntry>entries = file.entries();
		while (entries.hasMoreElements()) {
			JarEntry jarEntry = (JarEntry) entries.nextElement();
			// controllo se si tratta di una cartella
			try {
				out.putNextEntry(jarEntry);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// a questo punto prendo i bytes di questo entry 
			
			BufferedInputStream input=null;
			try {
				input = new BufferedInputStream(file.getInputStream(jarEntry));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int leggi ;
			try {
				while ((leggi = input.read())!=-1) {
					out.write(leggi);
					out.flush();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// una volta che abbiamo scritto il buffer di bytes chiudo la iscrizione
			try {
				out.closeEntry();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// chiudo l'outputstream 
		try {
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return instance ;
	}
	/**
	 * This method encrypts the text with AES algorithm
	 * @param text the text you want to encrypt
	 * @return the encrypted string
	 */	
	public static String AES_e(String text){
		try {
			text = getEncrypt().crypta(text,encrypt.getKey());
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return text ;
	}

	/**
	 * this method decrypts an encrypted text with the specified key
	 * @param encryptedText the encrypted text
	 * @param key the key used
	 * @return the decrypted string
	 */
	public static String AES_d(String encryptedText,Key key){
		try {
			encryptedText = getEncrypt().decrypt(encryptedText,key.getEncoded());
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch blocko
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return encryptedText ;
	}
	
	/**
	 * this method decrypts a file with AES algorithm 
	 * @param encryptedFile the encrypted file
	 * @return the decrypted file
	 */
	public static cloud.jgo.io.File AES_d(cloud.jgo.io.File encryptedFile){
		
		try {
			try {
				encryptedFile = getEncrypt().decryptaFileExecutable(encryptedFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return encryptedFile ;
	}
	
	/**
	 * This file encrypts a file with aes algorithm
	 * @param file The file you want to encrypt
	 * @return the home instance
	 */
	public static £ AES_e(cloud.jgo.io.File file){
		try {
			getEncrypt().cryptaFileExecutable(file);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return instance ;
	}
	

	/**
	 * this method decrypts an encrypted text
	 * @param encryptedText the encrypted text
	 * @return the decrypted string
	 */
	public static String AES_d(String encryptedText){
		try {
			encryptedText = getEncrypt().decrypt(encryptedText,encrypt.getKey().getEncoded());
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return encryptedText ;
	}
	
	
	/**
	 * This method creates a terminal to be customized.
	 * @return the created terminal
	 */
	public static LocalTerminal createCustomTerminal(){
		return new LocalTerminal();
	}
	
	
	/**
	 * This method creates a phase terminal.
	 * @return the created terminal
	 */
	public static LocalPhaseTerminal createPhaseTerminal(){
		return new LocalPhaseTerminal();
	}
	
	/**
	 * this method creates a server that can be accessed with the login
	 * @param servPassword the server password
	 * @param servUser the user password
	 * @return the login server
	 */
	public static cloud.jgo.net.tcp.login.TCPLoginServer serv(String servPassword,String servUser){
		TCPLoginServer server = new TCPLoginServer();
		server.setUsername(servUser);
		server.setPassword(servPassword);
		return server ;
	}
	
	/**
	 * This method creates a server
	 * @param lport the server port
	 * @param serverType the server type
	 * @return the server
	 */
	public static cloud.jgo.net.Server serv(int lport,int serverType){
		
		Server server = ServersFactory.getInstance().createServer(serverType,lport);
		
		return server ;
	}

	/**
	 * This method creates a server through a configuration
	 * @param config the server configuration
	 * @return the server
	 */
	public static cloud.jgo.net.Server serv(Configuration config){
		ServersFactory factory = ServersFactory.getInstance();
		Server server=null;
		try {
			server = factory.createServer(config);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return server ;
	}
	
	/**
	 * This method is more specific, create a tcpserver
	 * @return the tcpserver
	 */
	public static cloud.jgo.net.tcp.TCPServer createTCPServer(){
		ServersFactory factory = ServersFactory.getInstance();
		TCPServer server = (TCPServer)factory.createServer(ServerTypes.TYPE_TCP.VALUE,TCPServer.DEFAULT_PORT);
		return server ;
	}
	
	
	
	/**
	 * This method is more specific, create a httpserver
	 * @return the http server
	 */
	public static cloud.jgo.net.tcp.http.HTTPServer createHTTPServer(){
		ServersFactory factory = ServersFactory.getInstance();
		HTTPServer server = (HTTPServer)factory.createServer(TCPServerTypes.TYPE_HTTP.VALUE,80);
		return server ;
	}
	// version : 1.0.1
	/**
	 * 
	 * This method creates a JOR server
	 * @return the jor server
	 */
	public static cloud.jgo.net.tcp.http.jor.JORServer createJORServer(){
		return new JORServer();
	}
	
	/**
	 * This method creates a JOR server
	 * @param conf the server configuration
	 * @return the jor server
	 */
	public static cloud.jgo.net.tcp.http.jor.JORServer createJORServer(HTTPServerConfiguration conf){
		JORServer serv = createJORServer();
		serv.configure(conf);
		return serv ;
	}
	
	/**
	 * it is equivalent to "\n"
	 * @return the new line
	 */
	public static String newLine(){
		return "\n";
	}
	
	/**
	 * <p>This method hides a file <strong>(WINDOWS)</strong></p>
	 * @param file the file you want to hide
	 * @return the home instance
	 */
	public static £ hide(cloud.jgo.io.File file){
		final String codeBat = "attrib +h ";
		try {
			£.CMD.command(codeBat+file.getPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return instance ;
	}
	
	/**
	 * <p>This method shows a file <strong>(WINDOWS)</strong></p>
	 * @param file the file you want to show
	 * @return the home instance
	 */
	public static £ show(cloud.jgo.io.File file){
		final String codeBat = "attrib -h ";
		try {
			£.CMD.command(codeBat+file.getPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return instance ;
	}
	
	
	
	/**
	 * This method returns the operating system type
	 * @return the type
	 */
	public static String OS_type(){
		return SystemProperties().getProperty("os.name");
	}
	
	/**
	 * This method returns the operating system version
	 * @return the type
	 */
	public static String OS_version(){
		return SystemProperties().getProperty("os.version");
	}
	
	/**
	 * <p>(OUTPUT)</p>
	 * Is equivalent to system.out.print()
	 * @param text the text you want to print
	 * @return the home instance
	 */
	public static £ _O(String text){
		£.outPrint(text);
		return instance ;
	}
	
	/**
	 * <p>(OUTPUT)</p>
	 * Is equivalent to system.out.println()
	 * @param obj the object you want to print
	 * @return the home instance
	 */
	public static £ _O(Object obj){
		return £.outPrintln(obj.toString());
	}
	
	
	/**
	 * 
	 * @param texts the texts you want to println
	 * @return the home instance
	 */
	public static £ _O(String...texts){
		
		for (int i = 0; i < texts.length; i++) {
			£.out().println(texts[i]);
		}

		return instance;
	}
	
	/**
	 * This method creates a nicer frame for console messages
	 * @param text the text you want to print
	 * @param lenghtFrame the length of the frame
	 * @param frameSymbol the character of the frame
	 * @return the frame
	 */
    public static String frame(String text,int lenghtFrame,char frameSymbol){
		StringBuffer buffer = new StringBuffer();
    	for (int i = 0; i < lenghtFrame; i++) {
			buffer.append(frameSymbol);
		}
    	
    	buffer.append(£.newLine()+text+£.newLine());
    	
    	for (int i = 0; i <lenghtFrame; i++) {
    		buffer.append(frameSymbol);
		}
    	return buffer.toString();
	}
	
	/**
	 * <p>(OUTPUT)</p>
	 * Is equivalent to system.out.println ()
	 * @param objects the text you want to print
	 * @return the home instance
	 */
	public static £ _O(List<Object>objects){
		for (int i = 0; i < objects.size(); i++) {
			£.outPrintln(objects.get(i).toString());
		}
		return instance ;
	}
	
	/**
	 * <p>(OUTPUT)</p>
	 * Is equivalent to system.out.println ()
	 * @param array the text you want to print
	 * @return the home instance
	 */
	public static £ _O(Object[]array){
		System.out.println(Arrays.toString(array));
		return instance ;
	}
	
	
	/** <p>(INPUT)</p>
	 *  Is equivalent to scanner.nextLine()
	 * @return the input text
	 */
	public static String  _I(){
		return nextLine();
	}
	
	
	/**
	 * it is equivalent to "\n"
	 * @return the new line
	 */
	public static £ _NL(){
		System.out.println();
		return instance ;
	}
	
	/**
	 * it is equivalent to "\r\n"
	 * @return the new line
	 */
	public static £ _CRLF(){
		System.out.println("\r\n");
		return instance ;
	}
	
	
	/**
	 * 
	 * @author Martire91<br>
	 * This class contains all the methods inherent to <strong>Android</strong>.
	 * <p style='color:red'>It can not be used yet as it is under development</p>
	 */
	public static class AndroidUtils extends Home{}
	
	/**
	 * 
	 * @author Martire91<br>
	 * This class contains all the methods inherent to <STRONG>WEB</STRONG>.
	 * <p style='color:red'>It can not be used yet as it is under development</p>
	 */
	public static class WebUtils extends Home{
		// esiste solo una instanza di questa classe
		private WebUtils() {}
		/**
		 * this method creates an html file with the base code
		 * @param pathDestination the destinatio file path
		 * @param pageTitle The html page title
		 * @return the file html created
		 */
		public static cloud.jgo.io.File createBasicHtml(String pathDestination,String pageTitle){
			final String htmlCode = "<!DOCTYPE html>"+Header.CRLF+
			    "<html>"+Header.CRLF+
			     "<head>"+Header.CRLF+
				   "<title>"+pageTitle+"</title>"+Header.CRLF+
					 "</head>"+Header.CRLF+
						"<body>"+Header.CRLF+"<h1>"+pageTitle+"</h1>+"+Header.CRLF+"</body>"+Header.CRLF+
						  "</html>";
			try {
				£.createFile(pathDestination).writeFile(new File(pathDestination),false,new String[]{htmlCode});
			} catch (HeadlessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return new cloud.jgo.io.File(pathDestination);
		}
		
		public static WebUtils createBasicHtml2(String pathDestination,String pageTitle){
			final String htmlCode = "<!DOCTYPE html>"+Header.CRLF+
			    "<html>"+Header.CRLF+
			     "<head>"+Header.CRLF+
				   "<title>"+pageTitle+"</title>"+Header.CRLF+
					 "</head>"+Header.CRLF+
						"<body>"+Header.CRLF+"<h1>"+pageTitle+"</h1>+"+Header.CRLF+"</body>"+Header.CRLF+
						  "</html>";
			try {
				£.createFile(pathDestination).writeFile(new File(pathDestination),false,new String[]{htmlCode});
			} catch (HeadlessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return _W;
		}
		
		/**
		 * Jjdom
		 */
		public static JjDom JjDom = null ;
	}
	/**
	 @author Marco91<br> 
	 */
	 static class Resizer extends Thread{
			private Effect resizingEffect;
			private Effect effect = null ;
			private JFrame frame;
			private int currentWidth,currentHeight = 0 ;
			private int maximumWidth;
			private int maximumHeight;
			public Resizer(Effect resizingEffect,JFrame jFram,int maximumWidth,int maximumHeight,Effect fastEffect) {
				// TODO Auto-generated constructor stub
				this.resizingEffect = resizingEffect;
				this.effect = fastEffect;
				this.frame = jFram ;
				this.currentWidth = this.frame.getWidth();
				this.currentHeight = this.frame.getHeight();
				this.maximumWidth = maximumWidth;
				this.maximumHeight = maximumHeight ;
				start();
			}
			@Override
			public void run() {
				switch(resizingEffect){
				case MAXIMIZED:
					while (this.currentWidth!=this.maximumWidth && this.currentHeight!=this.maximumHeight) {
						this.currentWidth = this.currentWidth+1 ;
						this.currentHeight =this.currentHeight+1 ;
						this.frame.setSize(this.currentWidth,this.currentHeight);
						switch(effect){
						case REALLY_SLOW :
							try {
								Thread.sleep(100);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						break ;
						
						case SLOW:
							try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							break ;
							
						case REALLY_FAST:
							try {
								Thread.sleep(3);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							break ;
							
						case FAST :
							try {
								Thread.sleep(5);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							break ;
							
						case SUPER_FAST:
							try {
								Thread.sleep(1);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							break ;
							
						case IMPERCEPTIBLE :
							// nothing
							break ;
						
						}
						
					}
					break ;
					
				case MINIMIZED :
					while (this.currentWidth!=maximumWidth && this.currentHeight!=maximumHeight) {
						this.currentWidth = this.currentWidth-1 ;
						this.currentHeight =this.currentHeight-1 ;
						this.frame.setSize(this.currentWidth,this.currentHeight);
						switch(resizingEffect){
						case REALLY_SLOW :
							try {
								Thread.sleep(100);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						break ;
						
						case SLOW:
							try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							break ;
							
						case REALLY_FAST:
							try {
								Thread.sleep(3);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							break ;
							
						case FAST :
							try {
								Thread.sleep(5);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							break ;
							
						case SUPER_FAST:
							try {
								Thread.sleep(1);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							break ;
							
						case IMPERCEPTIBLE :
							// nothing
							break ;
						
						}
					}
					break ;
					
					
				}
			}
		}
	/**
	 * 
	 * @author Martire91<br>
     This class contains all the methods inherent to <STRONG>Swing</STRONG>
	 *
	 */
	public static class SwingUtils extends Home{
		public static SwingUtils test(){
			System.out.println("Test superato @");
			return _S ;
		}
		public static class SimpleTreeIconRenderer extends DefaultTreeCellRenderer{

			private JTree tree ;
			private String percorsoCartellaCheContieneCartellaPrincipale ;
			
			public SimpleTreeIconRenderer(String path,JTree tree) {
				// TODO Auto-generated constructor stub
			this.percorsoCartellaCheContieneCartellaPrincipale = path;
			this.tree = tree;
			
			}
			
			@Override
			public Component getTreeCellRendererComponent(JTree arg0, Object arg1, boolean arg2, boolean arg3, boolean arg4,
					int arg5, boolean arg6) {
				// TODO Auto-generated method stub
				super.getTreeCellRendererComponent(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
				
				DefaultMutableTreeNode nodoInQuestione = (DefaultMutableTreeNode)arg1;
				TreePath pathNode = new TreePath(nodoInQuestione.getUserObjectPath());
				String[]pathString = new String[nodoInQuestione.getUserObjectPath().length];
				// qui elimino gli spazi iniziali e finali di ogni pezzo del path
				for (int i = 0; i < pathString.length; i++) {
					pathString[i]=nodoInQuestione.getUserObjectPath()[i].toString().trim()+"\\";
				}
				// vedo se l'ultimo elemento è un file,in tal caso sostituisco lo slash finale
				String lastElement = pathString[pathString.length-1];
				if(lastElement.contains(".")==true){
					lastElement = lastElement.replace("\\", "");
					pathString[pathString.length-1]=lastElement;
				}
				
				
				// qui ottengo la stringa 
				String pathDaModificare = "";
				for (int i = 0; i < pathString.length; i++) {
					if(i ==0){
						pathDaModificare = pathString[i];
					}
					else{
						pathDaModificare = pathDaModificare+pathString[i];
					}
				}
				
				String pathOriginal = percorsoCartellaCheContieneCartellaPrincipale+pathDaModificare;
				File file = new File(pathOriginal);
				if(file.exists()){
							Icon icon = FileSystemView.getFileSystemView().getSystemIcon(file);
							this.tree.setToolTipText("File Local :"+file.getName());
							setIcon(icon);	
				}
				return this ;
			}
			
			
			
			
			
		}
		
		/**
		 * This method is very useful;<br>
		 * Download the file and get its progress in the progressbar. 
		 * @param progress the progressBar you want to use
		 * @param url the url to download
		 * @param fileNameDestination the destination path file
		 * @return the download worker
		 */
		public static DownloadWorker getProgressDownloadWorker(JProgressBar progress, String url, String fileNameDestination){
			return new cloud.jgo.downloads.DownloadWorker(progress, url, fileNameDestination);
		}
		
		
		
		public static class ResizeHorizontallyPanel extends JPanel implements MouseListener,MouseMotionListener{
			
			private JComponent child ;
			private boolean drag = false ;
			private JFrame application;
			
			public ResizeHorizontallyPanel(JComponent child,JFrame application) {
				this.child = child ;
				this.setLayout(new BorderLayout());
				this.add(this.child, BorderLayout.CENTER);
				this.application = application;
				this.child.addMouseListener(ResizeHorizontallyPanel.this);
				this.child.addMouseMotionListener(ResizeHorizontallyPanel.this);
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				if (drag) {
					
					if(e.getX()>20){
						this.setPreferredSize(new Dimension(e.getX(),this.getPreferredSize().height));
						this.revalidate();
						this.repaint();
					}
				}
				
			}

			@Override
			public void mouseMoved(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if(e.getX()==this.getPreferredSize().width || e.getX()==this.getPreferredSize().width-1 || e.getX()==this.getPreferredSize().width+1
						|| e.getX()==this.getPreferredSize().width+2 || e.getX()==this.getPreferredSize().width-2){
							application.setCursor(Cursor.E_RESIZE_CURSOR);
						}
						else{
							application.setCursor(Cursor.DEFAULT_CURSOR);
						}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				application.setCursor(Cursor.DEFAULT_CURSOR);
			}

			@Override
			public void mousePressed(MouseEvent e) {

				if(e.getX()==this.getPreferredSize().width || e.getX()==this.getPreferredSize().width-1 || e.getX()==this.getPreferredSize().width+1
				  || e.getX()==this.getPreferredSize().width+2 || e.getX()==this.getPreferredSize().width-2){
					application.setCursor(Cursor.E_RESIZE_CURSOR);
					drag = true ;
					
				}
				else{
					drag = false ;
					
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
			}
		}

		
		/**
		 * This method is very interesting,
		 * assigns the relative icon to each tree file.
		 * It is called after the method: representsFolderInTree();
		 * @param path the folder path
		 * @param tree the tree
		 * @return the file icon renderer
		 */
		public static SimpleTreeIconRenderer FileIconRenderer(String path, JTree tree){
			return new £.SwingUtils.SimpleTreeIconRenderer(path, tree);
		}
		
		
		/**
		 * 
		 * @param frame The frame you want to use
		 * @param rootPaneDecoration the JRootPane decoration you want to set
		 * @return the instance home
		 */
		public static JFrame setRootPaneDecoration(JFrame frame,int rootPaneDecoration){
			frame.setUndecorated(true);
			frame.getRootPane().setWindowDecorationStyle(rootPaneDecoration);
			return frame ;
		}
		
		/**
		 * This method sets the frame size according to the size of the screen
		 * @param frame the frame
		 * @return the SwingUtils home
		 */
		public static SwingUtils setSizeBasedOnTheScreen(JFrame frame){
			final Dimension screen_dim = £.getScreenSize();
			int correctWidth = (int)screen_dim.width/2;
			int correctHeight = (int)screen_dim.height/2;
			frame.setSize(correctWidth, correctHeight);
			return instance ;
		}
		

		/**
		 * This method is very interesting, it represents a folder in the JTree
		 * @param dir the folder that you want to represent
		 * @param root_ the tree node root
		 * @param tree the tree
		 * @return the SwingUtils home
		 */
		public static SwingUtils representsFolderInTree(File dir,DefaultMutableTreeNode root_,JTree tree)
		{   
		        DefaultMutableTreeNode newdir = new DefaultMutableTreeNode();    

		        File[] files = dir.listFiles();  //creates array of file type for all the files found

		        for (File file : files)
		            {
		                        if(file == null)
		                        {
		                            System.out.println("File == null");
		                            continue;
		                        }
		                if (file.isDirectory())
		                {
		                   

		                    if (file.listFiles()==null)
		                    {
		                       
		                        continue;
		                    }

		                    
		                    DefaultTreeModel model =(DefaultTreeModel) tree.getModel();

		                  
		                    DefaultMutableTreeNode root=(DefaultMutableTreeNode) model.getRoot();

		                   
		                    newdir = new DefaultMutableTreeNode(file.getName());

		                    
		                    root_.add(newdir);

		                   
		                    model.reload();

		                   
		                    representsFolderInTree(file,newdir,tree);
		                }
		                else
		                {
		                 
		                    DefaultTreeModel model =(DefaultTreeModel) tree.getModel();

		                  
		                    DefaultMutableTreeNode selectednode = root_;

		                    DefaultMutableTreeNode newfile =new DefaultMutableTreeNode(file.getName());
		                    model.insertNodeInto(newfile, selectednode, selectednode.getChildCount());

		                    //refresh the model to show the changes
		                    model.reload();

		                }

		            } 
		        return instance ;
		    }
		
		
		/**
		 * This method manually resizes the panel.<br>
		 * @param child the scrollpane
		 * @param application the frame
		 * @return the resize panel
		 */
		public static ResizeHorizontallyPanel getResizableHorizontallyPanel(JScrollPane child, JFrame application){
			return new ResizeHorizontallyPanel(child, application);
		}
		
		/**
		 * This method manually resizes the panel.<br>
		 * @param child the scrollpane
		 * @param application the frame
		 * @return the resize panel
		 */
		public static ResizeHorizontallyPanel getResizableHorizontallyPanel(JComponent child, JFrame application){
			return new ResizeHorizontallyPanel(child, application);
		}
		
		/**
	       * 
			This method creates a frame and opens it in a thread
	       * @param title the frame title
	       * @param width the frame width
	       * @param height the frame height
	       * @param icon the frame icon
	       * @param visibility the frame visibility
	       * @return the frame created
	       */
	      public static JFrame createFrame(String title,int width,int height,ImageIcon icon,boolean visibility){
	    	  JFrame frame = new JFrame(title);
	    	  frame.setSize(width, height);
	    	  if(icon!=null){
	    		  frame.setIconImage(icon.getImage());
	    	  }
	    	  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	    	  frame.setLocationRelativeTo(null);
	    	  // init frame e contentpane
	    	  JPanel panel = new JPanel();
	    	  panel.setLayout(new BorderLayout());
	    	  frame.setContentPane(panel);
	    	 if(visibility==true){
	    		 SwingUtilities.invokeLater(new Runnable() {
	    				
	    				@Override
	    				public void run() {
	    				try {
	    					frame.setVisible(visibility);
	    			        frame.show();
	    				} catch (Exception e) {
	    					// TODO: handle exception
	    					e.printStackTrace();
	    				}
	    					
	    				}
	    			}); 
	    		
	    	 }
	    	 return frame ;
	    	
	      }
	      
	      
	      
	      /**
	       * 
			This method creates a frame and opens it in a thread.
			Set the dimensions according to the screen size.
	       * @param title the frame title
	       * @param icon the frame icon
	       * @param visibility the frame visibility
	       * @return the frame created
	       */
	      @SuppressWarnings("static-access")
		public static JFrame createFrame(String title,ImageIcon icon,boolean visibility){
	    	  JFrame frame = new JFrame(title);
	    	  £._S.setSizeBasedOnTheScreen(frame);
	    	  if(icon!=null){
	    		  frame.setIconImage(icon.getImage());
	    	  }
	    	  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	  // init frame e contentpane
	    	  JPanel panel = new JPanel();
	    	  panel.setLayout(new BorderLayout());
	    	  frame.setContentPane(panel);
	    	 if(visibility==true){
	    		 SwingUtilities.invokeLater(new Runnable() {
	    				
	    				@Override
	    				public void run() {
	    				try {
	    					frame.setVisible(visibility);
	    			        frame.show();
	    				} catch (Exception e) {
	    					// TODO: handle exception
	    					e.printStackTrace();
	    				}
	    					
	    				}
	    			}); 
	    		
	    	 }
	    	 return frame ;
	      }
		
	      
	      /**
	       * This method is a search function
	       * @param combo The combobox you want to use
	       * @param items The combobox items
	       * @param showElements if true the items are always visible
	       * @return The an advanced search listener
	       */
		public static KeyHelpSearchListener createHelpSearchListener(JComboBox combo,String[]items,boolean showElements){
			  
			  return new £.KeyHelpSearchListener(combo, items,showElements);		  
		  }
		
		
		private static SwingUtils instance = new SwingUtils();
		
		/**
		 * This method adds an action listener to this button
		 * @param btn The button to which you want to associate this event
		 * @param func A function that will be performed at the click of the button
		 * @return the SwingUtils home
		 */
		public static SwingUtils click(JButton btn,£Func func){
			
			btn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					
					func.function(e);
					
				}
			});
			
			return instance ;
		}
		
		
		/**
		 * This method adds an action listener to these buttons
		 * @param btns The buttons to which you want to associate this event
		 * @param func A function that will be performed at the click of the button
		 * @return the SwingUtils home
		 */
		public static SwingUtils click(£Func func,JButton...btns){
			
			for (int i = 0; i < btns.length; i++) {
				btns[i].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						func.function(e);
					}
				});
			}
			return instance ;
		}
		
		
		
		/**
		 * Select Toggle
		 * @param comp the component that will be selected and deselected
		 * @param backgroundSelectColor the selected color of the background
		 * @param foregroundSelectColor the selected color of the foreground
		 * @return the SwingUtils home
		 */
		public static SwingUtils selectToggle(JComponent comp,Color backgroundSelectColor,Color foregroundSelectColor){
			Color originalBackColor = comp.getBackground();
			Color originalForeColor = comp.getForeground();
			comp.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					comp.setBackground(backgroundSelectColor);
					comp.setForeground(foregroundSelectColor);	
				}
				@Override
				public void mouseExited(MouseEvent e) {
					comp.setBackground(originalBackColor);
					comp.setForeground(originalForeColor);
				}
			});
			return instance ;
		}
		
		/**
		 * Select Toggle
		 * @param comp the component that will be selected and deselected
		 * @param backgroundSelectColor the selected color of the background
		 * @return the SwingUtils home
		 */
		public static SwingUtils selectToggle(JComponent comp,Color backgroundSelectColor){
			Color originalBackColor = comp.getBackground();
			Color originalForeColor = comp.getForeground();
			comp.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					comp.setBackground(backgroundSelectColor);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					comp.setBackground(originalBackColor);
				}
			});
			return instance ;
		}
		
		
		/**
		 * This method maximizes the frame.
		 * The supported effects are all speed effects.<br>
		 * The effects allowed are the following:<br>
		 * <ul>
		 * <li>{@link Effect#SLOW}</li> 
		 * <li>{@link Effect#REALLY_SLOW}</li> 
		 * <li>{@link Effect#FAST}</li> 
		 * <li>{@link Effect#REALLY_FAST}</li> 
		 * <li>{@link Effect#SUPER_FAST}</li> 
		 * <li>{@link Effect#IMPERCEPTIBLE}</li> 
		 * </ul>
		 * <br>
		 * @param speedEffect a speed effect
		 * @param frame the frame
		 * @param maximum_width destination width
		 * @param maximum_height destination height
		 * @return the SwingUtils instance
		 */
		public static SwingUtils maximizes(Effect speedEffect,JFrame frame,final int maximum_width,final int maximum_height){
			Resizer resizer = new Resizer(Effect.MAXIMIZED,frame,maximum_width,maximum_height,speedEffect);
		    try {
				resizer.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    return instance ;
		}
		
		/**
		 * This method minimizes the frame.
		 * The supported effects are all speed effects.
		 * The effects allowed are the following:<br>
		 * <ul>
		 * <li>{@link Effect#SLOW}</li> 
		 * <li>{@link Effect#REALLY_SLOW}</li> 
		 * <li>{@link Effect#FAST}</li> 
		 * <li>{@link Effect#REALLY_FAST}</li> 
		 * <li>{@link Effect#SUPER_FAST}</li> 
		 * <li>{@link Effect#IMPERCEPTIBLE}</li> 
		 * </ul>
		 * <br>
		 * @param speedEffect a speed effect
		 * @param frame the frame
		 * @param destination_width destination width
		 * @param destination_height destination height
		 * @return the SwingUtils instance
		 */
		public static SwingUtils minimizes(Effect speedEffect,JFrame frame,final int destination_width,final int destination_height){
			Resizer resizer = new Resizer(Effect.MINIMIZED,frame,destination_width,destination_height,speedEffect);
		    try {
				resizer.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    return instance ;
		}
		
		
		/**
		 * This method moves the frame <br>
		 * <p style='color:red'>The method is fine only if it is not used: setLocationRelativeTo(null);</p>
		 * <p>The supported effects are all speed effects.</p>
		 * The effects allowed are the following:<br>
		 * <ul>
		 * <li>{@link Effect#SLOW}</li> 
		 * <li>{@link Effect#REALLY_SLOW}</li> 
		 * <li>{@link Effect#FAST}</li> 
		 * <li>{@link Effect#REALLY_FAST}</li> 
		 * <li>{@link Effect#SUPER_FAST}</li> 
		 * <li>{@link Effect#IMPERCEPTIBLE}</li> 
		 * </ul>
		 * <br>
		 * @param frame the frame you want to move
		 * @param x the destination coordinate x
		 * @param y the destination coordinate y
		 * @param effect a speed effect
		 * @param func the final function
		 * @return the SwingUtils home
		 */
		public static SwingUtils moveJFrame(JFrame frame,int x,int y,Effect effect,£Func func)
		{
			move_frame = new MoveFrame(frame, x, y, effect);
				
			move_frame.start();	
			
			try {
				move_frame.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.err.println("Thread "+move_frame.getName()+" interrupted #");
			}	
			func.function(null);
			return instance ;
			}
		
		
		/**
		 * This method moves the frame <br>
		 * <p style='color:red'>The method is fine only if it is not used: setLocationRelativeTo(null);</p>
		 * <p>The supported effects are all speed effects.</p>
		 * The effects allowed are the following:<br>
		 * <ul>
		 * <li>{@link Effect#SLOW}</li> 
		 * <li>{@link Effect#REALLY_SLOW}</li> 
		 * <li>{@link Effect#FAST}</li> 
		 * <li>{@link Effect#REALLY_FAST}</li> 
		 * <li>{@link Effect#SUPER_FAST}</li> 
		 * <li>{@link Effect#IMPERCEPTIBLE}</li> 
		 * </ul>
		 * <br>
		 * @param frame the frame you want to move
		 * @param x the destination coordinate x
		 * @param y the destination coordinate y
		 * @param effect a speed effect
		 * @return the SwingUtils home
		 */
		public static SwingUtils moveJFrame(JFrame frame,int x,int y,Effect effect){
			move_frame = new MoveFrame(frame, x, y, effect);
				
			move_frame.start();	
			
			try {
				move_frame.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.err.println("Thread "+move_frame.getName()+" interrupted #");
			}	
			return instance ;
			}
		
		
		private static SwingUtils moveJFrame_(JFrame frame,int x,int y,Effect effect){
			move_frame = new MoveFrame(frame, x, y, effect);
				
			move_frame.start();	
		
			return instance ;
			}
		/**
		 * This method assumes a default behavior.
		 * It is the method that decides the speed of the effects<br>
		 * <p style='color:red'>CAUTION - if you use the "crazy" effect, you enter in an infinite cicle</p>
		 * <p>The supported effects are : (CRAZY,MAXIMIZED,MINIMIZED,TRANSPARENCY)</p>
		 * The effects allowed are the following:<br>
		 * <ul>
		 * <li>{@link Effect#CRAZY}</li> 
		 * <li>{@link Effect#MAXIMIZED}</li> 
		 * <li>{@link Effect#MINIMIZED}</li> 
		 * <li>{@link Effect#TRANSPARENCY}</li> 
		 * </ul>
		 * <br>
		 * @param effect a speed effect
		 * @param frames the frames
		 * @return the SwingUtils home
		 */
		@SuppressWarnings("static-access")
		public static SwingUtils applyEffect(£.Effect effect,JFrame...frames){
			Dimension dim = £.getScreenSize();
			 int maximum_width = dim.width;
			 int maximum_height = dim.height;
			switch(effect){
			 case CRAZY:
			   
				 while(true){
						for (JFrame jFram : frames) {
							£._S.moveJFrame_(jFram,£.generateIntRandom(maximum_width),£.generateIntRandom(maximum_height),Effect.IMPERCEPTIBLE);
						}
						try {
							Thread.sleep(800);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
			 case MAXIMIZED:
				
				 
				 for (JFrame jFram : frames) {
						// qui mi serve un oggetto thread che gli passo un frame e lo gestisce
					 Resizer resizer = new £.Resizer(Effect.MAXIMIZED, jFram, maximum_width, maximum_height,Effect.SUPER_FAST);
				     try {
						resizer.join();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
				 }
				 
				 break ;
				 
				 
			 case MINIMIZED:
				 
				 for (JFrame jFram : frames) {
						// qui mi serve un oggetto thread che gli passo un frame e lo gestisce
					 Resizer resizer = new £.Resizer(Effect.MINIMIZED,jFram, maximum_width, maximum_height,Effect.SUPER_FAST);
				             try {
								resizer.join();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}	
				 }
				 
				 break ;
				 
			 case TRANSPARENCY:
				 
				 for (JFrame jFrame : frames) {
					ApplyTransparency(jFrame, JRootPane.FILE_CHOOSER_DIALOG);
				
				 }
				 
				 break ;
				 default :
					 
					 // do l'eccezzione 
					 
				try {
					throw new NotSupportedEffectException() ;
				} catch (NotSupportedEffectException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					 
					 break ;
			}
			return instance;
		}
		
		/**
		 * This method shows the frame
		 * The effects allowed are the following:<br>
		 * <ul>
		 * <li>{@link Effect#SLOW}</li> 
		 * <li>{@link Effect#REALLY_SLOW}</li> 
		 * <li>{@link Effect#FAST}</li> 
		 * <li>{@link Effect#REALLY_FAST}</li> 
		 * <li>{@link Effect#SUPER_FAST}</li> 
		 * <li>{@link Effect#IMPERCEPTIBLE}</li> 
		 * </ul>
		 * <br>
		 * @param jFram the frame
		 * @param effect a speed effect
		 * @return the SwingUtils instance
		 */
		public static SwingUtils show(JFrame jFram,Effect effect){
			int x,y = 0 ;
			int originalX,originalY;
			originalX = jFram.getX();
			originalY = jFram.getY();
			if(!jFram.isVisible()){
				
				jFram.setLocationRelativeTo(null);
				
				jFram.setLocationRelativeTo(jFram);
			
				x = jFram.getX();
				y = jFram.getY();
				
				// riporto il frame ai suoi valori originali 
				
				jFram.setLocation(originalX, originalY);
				
				// adesso devo mostrare il frame e accompagnarlo a centro schermo
				
				jFram.setVisible(true);
				
				moveJFrame(jFram,x,y,effect);
			}
			
			return instance ;
		}
		
		
		/**
		 * This method shows the frame
		 * The effects allowed are the following:<br>
		 * <ul>
		 * <li>{@link Effect#SLOW}</li> 
		 * <li>{@link Effect#REALLY_SLOW}</li> 
		 * <li>{@link Effect#FAST}</li> 
		 * <li>{@link Effect#REALLY_FAST}</li> 
		 * <li>{@link Effect#SUPER_FAST}</li> 
		 * <li>{@link Effect#IMPERCEPTIBLE}</li> 
		 * </ul>
		 * <br>
		 * @param jFram the frame
		 * @param effect a speed effect
		 * @param func the final function
		 * @return the SwingUtils instance
		 */
		public static SwingUtils show(JFrame jFram,Effect effect,£Func func){
			int x,y = 0 ;
			int originalX,originalY;
			originalX = jFram.getX();
			originalY = jFram.getY();
			if(!jFram.isVisible()){
				
				jFram.setLocationRelativeTo(null);
				
				jFram.setLocationRelativeTo(jFram);
			
				x = jFram.getX();
				y = jFram.getY();
				
				// riporto il frame ai suoi valori originali 
				
				jFram.setLocation(originalX, originalY);
				
				// adesso devo mostrare il frame e accompagnarlo a centro schermo
				
				jFram.setVisible(true);
				
				moveJFrame(jFram,x,y,effect,func);
			}
			
			return instance ;
		}
		
		
		/**
		 * This is the opposite of show.
		 * Hides the frame to the coordinates x = 0, x = 0.
		 * The effects allowed are the following:<br>
		 * <ul>
		 * <li>{@link Effect#SLOW}</li> 
		 * <li>{@link Effect#REALLY_SLOW}</li> 
		 * <li>{@link Effect#FAST}</li> 
		 * <li>{@link Effect#REALLY_FAST}</li> 
		 * <li>{@link Effect#SUPER_FAST}</li> 
		 * <li>{@link Effect#IMPERCEPTIBLE}</li> 
		 * </ul>
		 * <br>
		 * @param jFram the frame
		 * @param effect a speed effect
		 * @param func the final function
		 * @return the SwingUtils instance
		 */
		public static SwingUtils hide(JFrame jFram,Effect effect,£Func func){
			final int x = 0 ;
			final int y = 0 ;
			moveJFrame(jFram,x,y, effect,func);
			
			// finito il metodo nascondo il frame 
			
			jFram.setVisible(false);
			
			return instance ;
		}
		
		
		/**
		 * This is the opposite of show.
		 * Hides the frame to the coordinates x = 0, x = 0.
		 * The effects allowed are the following:<br>
		 * <ul>
		 * <li>{@link Effect#SLOW}</li> 
		 * <li>{@link Effect#REALLY_SLOW}</li> 
		 * <li>{@link Effect#FAST}</li> 
		 * <li>{@link Effect#REALLY_FAST}</li> 
		 * <li>{@link Effect#SUPER_FAST}</li> 
		 * <li>{@link Effect#IMPERCEPTIBLE}</li> 
		 * </ul>
		 * <br>
		 * @param jFram the frame
		 * @param effect a speed effect
		 * @return the SwingUtils instance
		 */
		public static SwingUtils hide(JFrame jFram,Effect effect){
			final int x = 0 ;
			final int y = 0 ;
			moveJFrame(jFram,x,y, effect);
			
			// finito il metodo nascondo il frame 
			
			jFram.setVisible(false);
			
			return instance ;
		}
		/**
		 * This method makes the frame transparent
		 * @param frame The frame that you want to make transparent
		 * @param rootPaneDecoration the rootpane decoration
		 * @return the swingUtils home
		 */
		public static SwingUtils ApplyTransparency(javax.swing.JFrame frame,int rootPaneDecoration){
			frame.setUndecorated(true);
	    	frame.setOpacity(0.8f);
	    	frame.getContentPane().setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
	    	frame.getRootPane().setWindowDecorationStyle(rootPaneDecoration);
	    	// devo sviluppare questo metodo che serve per applicare
	    	// la trasparenza al frame
	    	// eppoi devo fare qualche metodo che personalizza
	    	//8f
	     	return instance;
	    }
		
		/**
		 * This method changes the background color
		 * @param comp the component to which you want to change the color
		 * @param color the color you want to apply
		 * @return the swingUtils home
		 */
		public static SwingUtils bg_Color(Component comp,Color color){
			comp.setBackground(color);
			return instance ;
		}
		
		/**
		 * This method changes the foreground color
		 * @param comp the component to which you want to change the color
		 * @param color the color you want to apply
		 * @return the swingUtils home
		 */
		public static SwingUtils fg_Color(Component comp,Color color){
			comp.setForeground(color);
			return instance ;
		}
		
		
		
		// attenzion perchè quetsi metodi
		// richiedono che il componente che ospita il componente che si passa
		// a questo metodo,abbia un ABSOLUTE_LAYOUT
		
		// questo metodo non return il thread
		/**
		 * This method performs the slide effect on a component.
		 * The effects allowed are the following:<br>
		 * <ul>
		 * <li>{@link Effect#SLOW}</li> 
		 * <li>{@link Effect#REALLY_SLOW}</li> 
		 * <li>{@link Effect#FAST}</li> 
		 * <li>{@link Effect#REALLY_FAST}</li> 
		 * <li>{@link Effect#SUPER_FAST}</li> 
		 * <li>{@link Effect#IMPERCEPTIBLE}</li> 
		 * </ul>
		 * <br>
		 * @param comp the component that acts as a slide
		 * @param slideAlign alignment effect
		 * @param effect a speed effect
		 * @return the swingUtils home
		 */
		public static SwingUtils slide(JComponent comp,Effect slideAlign,Effect effect) {
			SlideToggle toggle = new SlideToggle(comp,slideAlign, effect);
			new java.lang.Thread(toggle).start();
			return instance ;
		}
		// di default è la verticale
		// interessantinissimo
		// gli passi il componente che funge da slide
		private static SlideToggle toggle = null ;
		/**
		 * This method is interesting, returns a toggle button.
		 * The effects allowed are the following:<br>
		 * <ul>
		 * <li>{@link Effect#HORIZONTAL}</li> 
		 * <li>{@link Effect#VERTICAL}</li> 
		 * </ul>
		 * <br>
		 * @param comp comp the component that acts as a slide
		 * @param slideAlign alignment effect
		 * @param effect a speed effect
		 * @return the toggle button
		 */
		public static JButton getSlideButton(JComponent comp,Effect slideAlign,Effect effect){
			JButton button = new JButton("Slide Toggle");
			button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(toggle==null){
						toggle = new SlideToggle(comp,slideAlign, effect);
						toggle.setToggleButton(button);
						// attivo il thread 
					    new java.lang.Thread(toggle).start();
					}
					else{
						if(!toggle.inRunning){
							toggle = new SlideToggle(comp,slideAlign, effect);
							toggle.setToggleButton(button);
							// running a true 
							new java.lang.Thread(toggle).start();
						}
					}
				}
			});
			return button ;
		}	
	}
	
	
	
	
	
	/*
	 * Methods for simple threads
	 * Tutti i metodi che lavorando con i threads 
	 * li avviano,non vi è nemmeno un metodo che lascia all'utente la scelta di quando
	 * avviare il thread .Questi schemi sono stati creati per facilitare la gestione di quesi threads
	 * usa e getta,quei thread semplici che ci servono solo per rendere quella parte del programma indipendente
	 */
	
	/**
	 * This method creates a thread
	 * @param runnable the runnable object
	 * @return the home instance
	 */
	public static £ th(Runnable runnable){
		new java.lang.Thread(runnable).start();;
		return instance ;
	}
	
	
	/**
	 * this method creates a thread
	 * @param func the function that you want to run in thread
	 * @return the home instance
	 */
	public static £ th(£Func func){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				func.function(this);
			}
		}).start();
		return instance;
	}
	
	/**
	 * this method creates a thread
	 * @param runnable the runnable object
	 * @return the thread
	 */
	public static Thread thr(Runnable runnable){
		Thread t = new Thread(runnable);
		t.start();
		return t ;
	}
	
	
	/**
	 * this method runs threads one at a time
	 * @param threads the threads that you want to run
	 * @return the home instance
	 */
	public static £ OneAtATime(Thread...threads){
		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				/*
				 
				JGO Auto-generated catch block
				Author : £ wasp91 £
				Date 20 feb 2018
				
				*/
				e.printStackTrace();
			} 
		}
		return instance ;
	}
	
    /**
     * this method creates a thread
     * @param runnable the runnable object
     * @param threadName the thread name
     * @return the home instance
     */
	public static £ thr(Runnable runnable,String threadName){
		Thread t = new Thread(runnable);
		if(threadName!=null){
			t.setName(threadName);
		}
		t.start();
		return instance ;
	}

	/**
	 * this method sets a file as auto run - <strong>Windows method</strong>
	 * @param file the executable file
	 * @param valueName the registry value
	 * @return true if everything went right
	 */
	public static boolean setAutoRunFile(cloud.jgo.io.File file,String valueName){
		if(file.exists()){
			if(!file.isDirectory()){
				
			// prendo il path del file 
			String filePath = file.getPath();
				
		try {
			WinRegistry.writeStringValue(WinRegistry.HKEY_CURRENT_USER,WinRegistry.DEFAULT_PATH_FOR_AUTORUN,valueName,file.getPath());
		} catch (IllegalArgumentException e) {
			/*
			 
			JGO Auto-generated catch block
			Author : £ wasp91 £
			Date 07 feb 2018
			
			*/
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			/*
			 
			JGO Auto-generated catch block
			Author : £ wasp91 £
			Date 07 feb 2018
			
			*/
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			/*
			 
			JGO Auto-generated catch block
			Author : £ wasp91 £
			Date 07 feb 2018
			
			*/
			e.printStackTrace();
		}
			return true ;	
			}
			else{
				return false ;
			}
		}
		else{
			return false ;
		}
		
		
	}

	/**
	 * This method writes a byte to a file
	 * @param file the file you want to write to
	 * @param b the byte that you want to write
	 * @param append if true, appends the byte
	 * @return the home instance
	 */
	public static £ writeByte(cloud.jgo.io.File file,byte b,boolean append){
		FileOutputStream fos=null;
		try {
			fos = new FileOutputStream(file,append);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fos.write(new byte[]{b});
			fos.flush();
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return instance;
	}
	
	/**
	 * This method writes a bytes to a file
	 * @param file the file you want to write to
	 * @param buffer the byte that you want to write
	 * @param append if true, appends the byte
	 * @return the home instance
	 */
	public static £ writeBytes(cloud.jgo.io.File file,byte[]buffer,boolean append){
		FileOutputStream fos=null;
		try {
			fos = new FileOutputStream(file,append);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fos.write(buffer);
			fos.flush();
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return instance;
	}
	
	
	/**
	 * This method converts the character to a string
	 * @param charat the character you want to convert
	 * @return the character in the form of a string
	 */
	public static String getString(char charat){
		return Character.toString(charat);
	}
	
	/**
	 * This method plays a wav audio file
	 * @param soundFile the file you want to play
	 * @return the home instance
	 */
	public static £ playWavSound(cloud.jgo.io.File soundFile) {
		AudioInputStream audioStream = null ;
		AudioFormat audioFormat = null ;
		SourceDataLine sourceLine = null ;
	        
	        try {
				audioStream = AudioSystem.getAudioInputStream(soundFile);
			} catch (UnsupportedAudioFileException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

	        audioFormat = audioStream.getFormat();

	        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
	        try {
	            sourceLine = (SourceDataLine) AudioSystem.getLine(info);
	            sourceLine.open(audioFormat);
	        } catch (LineUnavailableException e) {
	            e.printStackTrace();
	            System.exit(1);
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.exit(1);
	        }

	        sourceLine.start();

	        int nBytesRead = 0;
	        byte[] abData = new byte[128000];
	        while (nBytesRead != -1) {
	            try {
	                nBytesRead = audioStream.read(abData, 0, abData.length);
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	            if (nBytesRead >= 0) {
	                
	                int nBytesWritten = sourceLine.write(abData, 0, nBytesRead);
	            }
	        }
	        sourceLine.drain();
	        sourceLine.close();
	        return instance;
	}
	
	
	/* N E W S         -       JGO
	 * 
	 * News : help search in Combobox
	 * 
	 * 
	 * Quindi basta creare un frame
	 * ,aggiungere una combobox
	 * e una volta creata la combobox
	 * aggiungere alla combobox questo listener particolare
	 * ,Facciamo un piccolo test. Ultima cosa non meno importante
	 * questo listener va aggiunto all'editor della combobox.
	 * Questo è un algoritmo bello pronto e funzionante
	 * ,l'utente per usufruire di questo meccanismo non si deve
	 * preoccupare di niente,pensa a tutto JGO
	 * Poi una cosa simile devo farla per la rappresentazione delle cartella
	 * nel defaultTreeNode,una cosa similissima a questa qui
	 * 
	 * 
	 * 
	 * N.B. 
	 * 
	 * Ultima cosa da aggiungere a questo meccanismo è 
	 * un boolean che indica se l'array deve essere visibile nel combobox
	 * oppure no
	 */
	
	  static class KeyHelpSearchListener implements KeyListener{
		
		private JComboBox comboBox = null;

		private String text = null ;
		
		private String[]items = null ;
		
		private boolean showElements = false ;


		public KeyHelpSearchListener(JComboBox combobox,String[]items,boolean showElements) {
			/*
			 
			JGO Auto-generated constructor stub
			Author : £ wasp91 £
			Date 29 dic 2017
			
			*/
			this.showElements = showElements ;
			this.comboBox  = combobox ;
			if(!this.comboBox.isEditable()){
				this.comboBox.setEditable(true);
			}
			this.items = items;
		
		    // qui verifico se si vogliono vedere gli elementi nella combobox 
			
			if(this.showElements==true){
				for (int i = 0; i < items.length; i++) {
					this.comboBox.addItem(items[i]);
				}
				// qui svuoto il textField
				this.comboBox.getEditor().setItem("");
			}
		}
		
		
		// facciamo il metodo statico qui
		public static void check(JComboBox combo,String text,String[]array){
			ArrayList<String>list = new ArrayList<>();
			for (int i = 0; i < array.length; i++) {
				if(array[i].toLowerCase().startsWith(text.toLowerCase())){
					// qui si controlla se la lista ha gia un elemento uguale a quello che si vuole aggiungere
					
						// quindi la lista se non ha quell'elemento ,lo aggiunge
						list.add(array[i]);
				}
				else{
					// qui devo eliminare l'array [i] dal combobox
					int indexOfObject = ((DefaultComboBoxModel)combo.getModel()).getIndexOf(array[i]);
					if(indexOfObject>-1){
						// elimino l'elemento dal combobox 
						combo.removeItemAt(indexOfObject);
						// apro di nuovo la tenda cosi da aggiornarla 
						combo.hidePopup();
						combo.showPopup();
						
						//queste due chiamate
						//praticamente aggiornano la combobox
					}
				}
			}
			for (int i = 0; i < list.size(); i++) {
				
				// qui prima di aggiungere al combobox devo controllare che l'elemento non ci sia gia
				
				
				int indexOfObject = ((DefaultComboBoxModel)combo.getModel()).getIndexOf(list.get(i));
				
				
				if(indexOfObject==-1){ // molto importante ,questo controllo
					// vuol dire che non c'è 
					// nel combobox
					combo.addItem(list.get(i));
				}
				
			}
			
			// quando riempiamo la comobox dobbiamo aprirla
			combo.showPopup();
			
			// qui ricarico il tutto 
			combo.revalidate();
			
			
			// qui dobbiamo settare il primo elemento con il testo ricevuto
			
			// questa istruzione è da valutare
			combo.getEditor().setItem(text);
		}
		
		
		@Override
		public void keyReleased(KeyEvent e) {
			text = comboBox.getEditor().getItem().toString();
			if(e.getKeyCode()!=KeyEvent.VK_BACK_SPACE){
				// okok qui abbiamo ottenuto il testo
				check(comboBox, text, items);
			}
			else{
				
				// qui stiamo cancellando ,quindi possiamo cancellare 
				// gli elementi dei combobox
				
				comboBox.removeAllItems();
				// qui nascondo la tenda
			    comboBox.hidePopup();
				
				if(text!=null && !text.isEmpty()){
					// cancellare da qui in poi nell'if
					check(comboBox, text, items);
				}	
				else{
					// reinserisco tutti gli elementi nel combobox
					if(this.showElements==true){
						for (int i = 0; i < items.length; i++) {
							this.comboBox.addItem(items[i]);
						}
						// svuoto la textField del combobox
						this.comboBox.getEditor().setItem("");
					}
					
				}
			}
		}


		@Override
		public void keyPressed(KeyEvent arg0) {
			/*
			 
			JGO Auto-generated method stub
			Author : £ wasp91 £
			Date 29 dic 2017
			
			*/
			
		}


		@Override
		public void keyTyped(KeyEvent arg0) {
			/*
			 
			JGO Auto-generated method stub
			Author : £ wasp91 £
			Date 29 dic 2017
			
			*/
			
		}
		
		
	}
	
	/**
	 * 
	 * @author Martire91
	 * <br>
	 * this class contains all the internet<br>
	 * addresses associated with this machine
	 *
	 */
	public static class INTERNET_ADDRESSES extends Home{
		/**
		 * This method opens the graphical interface of the router
		 * @return the home instance
		 */
		public static £ openTheRouterControlPanel(){
			String httpAddress=null;
			httpAddress = HTTP_ROUTER_IP();
			try {
				Desktop.getDesktop().browse(new URI(httpAddress));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return home();
		}
		
		/**
		 * This method returns the http router address
		 * @return the http router address
		 */
		public static String HTTP_ROUTER_IP(){
			String routerURL  = null ; 
				try {
	                Process process = Runtime.getRuntime().exec("ipconfig");
	                try (BufferedReader bufferedReader = new BufferedReader(
	                        new InputStreamReader(process.getInputStream()))) {

	                    String line;
	                    while ((line = bufferedReader.readLine()) != null) {
	                        if (line.trim().startsWith("Default Gateway")||line.trim().contains("Gateway")) {
	                            String ip = line.substring(line.indexOf(":") + 1).trim();
	                            routerURL = String.format("http://%s", ip);
	                        }
	                    }
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
			return routerURL ;
		}
		
		
		/**
		 * This method returns the http router address
		 * @return  the router address
		 */
		public static String ROUTER_IP(){
			String router  = null ; 
				try {
					 
	                Process process = Runtime.getRuntime().exec("ipconfig");

	                try (BufferedReader bufferedReader = new BufferedReader(
	                        new InputStreamReader(process.getInputStream()))) {

	                    String line;
	                    while ((line = bufferedReader.readLine()) != null) {
	                        if (line.trim().startsWith("Default Gateway")||line.trim().contains("Gateway")) {
	                            router = line.substring(line.indexOf(":") + 1).trim();
	                            
	                        }
	                    }
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
			return router ;
		}
		
		/**
		 * This method returns the public IP
		 * @return the public address IP
		 */
		public static String PUBLIC_IP(){
			String publicIp = null ;
			if(£.connectedToInternet()){
				 URL whatismyip=null;
				try {
					whatismyip = new URL("http://checkip.amazonaws.com");
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			     BufferedReader in = null;
			     try {
			         try {
						in = new BufferedReader(new InputStreamReader(
						         whatismyip.openStream()));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			        try {
						publicIp = in.readLine();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			     } finally {
			         if (in != null) {
			             try {
			                 in.close();
			             } catch (IOException e) {
			                 e.printStackTrace();
			             }
			         }
			     }
			}
			return publicIp ;
		}
		
		/**
		 * This methos returns the hostname
		 * @return the hostname
		 */
		public static String HOSTNAME(){
			
			
			try {
				return InetAddress.getLocalHost().getHostName();
			} catch (Exception e) {
				/*
				 
				JGO Auto-generated catch block
				Author : £ wasp91 £
				Date 27 nov 2017
				
				*/
				return null ;
			}
		}
		
		
		
		/**
		 * This method returns the local IP
		 * @return the local IP
		 */
		public static String LOCAL_IP(){
			String hostAddress = null ;
			try {
				hostAddress = InetAddress.getLocalHost().getHostAddress();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return  hostAddress ;
		}
		
		/**
		 * This methods returns the inetAddress
		 * @return the inetAddress
		 */
		public static InetAddress ADDRESS(){
			InetAddress inet = null ;
			try {
				 inet 
				 =  InetAddress.getLocalHost();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return inet ;
		}
		
	}
	
	
	
	/*
	 * Questo metodo può essere convertito in 
	 * 
	 *Tutti i tipi di array primitive
	 *wrapper,String,StringBuffer ma no in oggetti personali
	 *Poi devo fare un metodo che viceversa prende un array di tipi wrapper e primitivi 
	 *e lo riporta ad array di stringhe
	 */
	public static <T> T parse(String[]array,Class<?>arrayClass){
		
		if(array.length>0){
			if(!arrayClass.isInstance(array) && !arrayClass.isInstance(array[0])){
			
				// verifico bene il tipo 
				
				if(arrayClass.getSimpleName().equals("int[]")){
					int[]arrays = new int[array.length];
					for (int i = 0; i < array.length; i++) {
						int value = Integer.parseInt(array[i]);
						arrays[i] = value ;
					}
					return (T) arrays;
				}
				else if(arrayClass.getSimpleName().equals("double[]")){
					double[]arrays = new double[array.length];
					for (int i = 0; i < array.length; i++) {
						double value = Double.parseDouble(array[i]);
						arrays[i] = value ;
					}
					return (T) arrays;
				}
				else if(arrayClass.getSimpleName().equals("float[]")){
					float[]arrays = new float[array.length];
					for (int i = 0; i < array.length; i++) {
						float value = Float.parseFloat(array[i]);
						arrays[i] = value ;
					}
					return (T) arrays;
				}
				else if(arrayClass.getSimpleName().equals("long[]")){
					long[]arrays = new long[array.length];
					for (int i = 0; i < array.length; i++) {
						long value = Long.parseLong(array[i]);
						arrays[i] = value ;
					}
					return (T) arrays;
				}
				else if(arrayClass.getSimpleName().equals("boolean[]")){
				   boolean[]arrays = new boolean[array.length];
					for (int i = 0; i < array.length; i++) {
						boolean value = Boolean.parseBoolean(array[i]);
						arrays[i] = value ;
					}
					return (T) arrays;
				}
				else if(arrayClass.getSimpleName().equals("byte[]")){
					byte[]arrays = new byte[array.length];
					for (int i = 0; i < array.length; i++) {
						Byte value = Byte.parseByte(array[i]);
						arrays[i] = value ;
					}
					return (T) arrays;
				}
				else if(arrayClass.getSimpleName().equals("char[]")){
					char[]charat = new char[array.length];
					for (int i = 0; i < array.length; i++) {
						String current = array[i];
						charat[i] = current.charAt(0);
					}
					return (T)charat ;
				}
				else if(arrayClass.getSimpleName().equals("Integer[]")){
					Integer[]arrays = new Integer[array.length];
					for (int i = 0; i < array.length; i++) {
						Integer value = Integer.parseInt(array[i]);
						arrays[i] = value ;
					}
					return (T) arrays;
				}
				else if(arrayClass.getSimpleName().equals("Double[]")){
					Double[]arrays = new Double[array.length];
					for (int i = 0; i < array.length; i++) {
						Double value = Double.parseDouble(array[i]);
						arrays[i] = value ;
					}
					return (T) arrays;
				}
				else if(arrayClass.getSimpleName().equals("Float[]")){
					Float[]arrays = new Float[array.length];
					for (int i = 0; i < array.length; i++) {
						Float value = Float.parseFloat(array[i]);
						arrays[i] = value ;
					}
					return (T) arrays;
				}
				else if(arrayClass.getSimpleName().equals("Byte[]")){
					Byte[]arrays = new Byte[array.length];
					for (int i = 0; i < array.length; i++) {
						Byte value = Byte.parseByte(array[i]);
						arrays[i] = value ;
					}
					return (T) arrays;
				}
				else if(arrayClass.getSimpleName().equals("Boolean[]")){
					Boolean[]arrays = new Boolean[array.length];
					for (int i = 0; i < array.length; i++) {
						Boolean value = Boolean.parseBoolean(array[i]);
						arrays[i] = value ;
					}
					return (T) arrays;
				}
				else if(arrayClass.getSimpleName().equals("Long[]")){
					Long[]arrays = new Long[array.length];
					for (int i = 0; i < array.length; i++) {
						Long value = Long.parseLong(array[i]);
						arrays[i] = value ;
					}
					return (T) arrays;
				}
				else if(arrayClass.getSimpleName().equals("StringBuffer[]")){
					StringBuffer[]arrays = new StringBuffer[array.length];
					for (int i = 0; i < array.length; i++) {
						arrays[i] = new StringBuffer(array[i]);
					}
					return (T) arrays;
				}
				else if(arrayClass.getSimpleName().equals("Character[]")){
					
					Character []charat = new Character[array.length];
					for (int i = 0; i < charat.length; i++) {
						String current =array [i];
						charat[i] = new Character(current.charAt(0));
					}
					return (T)charat ;
				}
				
			}
			else{
				System.out.println("Formato di conversione non valido");
				// qui è come se volesse dire che abbiamo passato un oggetto array String e lo vogliamo 
				// convertire in array di stringhe,quindi una cosa stupida
				return null ;
			}
		}
		return null;
	}

	/**
	 * (Windows)
	 * <p>This method change the user password</p><br>
	 * <p style='color:red'>Not recommended for the moment</p>
	 * @param newPassw The new password
	 * @return The home instance
	 */
	public static £ changeUserPassword(String newPassw){
		//cmd.executeCommand("@net user %UserName% "+password);
		
		// poi qui fare un controllo in base al sistema
		
		try {
			CMD.command("@net user %UserName% "+newPassw);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return instance;
	}
	
	/**
	 * This method executes a command in the windows cmd
	 * @param cmd_command the command you want to execute
	 * @return the home instance
	 */
	public static £ exec(String cmd_command){
		
		try {
			CMD.command(cmd_command);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return instance ;
	}
	
	
	
	
	/**
	 * This method creates an object
	 * @param <T> the type
	 * @param class_ la object class
	 * @return the object created
	 */
	public static <T> T newInstance(Class<?extends Object> class_){
		Object obj = null ;
		try {
		obj = (T) class_.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (T) obj ;
	}
	
	
	/*
	 * Metodi per serializzare 
	 */
	
	
	/**
	 * 
	 * This method serializes an object.
	 * However, the object must implement the jgoserializable interface.
	 * @param obj The object that wants to serialize
	 * @param fileName The name of the destination file
	 * @return the serialized file
	 */
	public static cloud.jgo.io.File serializes(Object obj,String fileName){
			FileOutputStream fos=null;
			try {
				fos = new FileOutputStream(new File(fileName));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ObjectOutputStream objOut=null;
			try {
				objOut = new ObjectOutputStream(fos);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				objOut.writeObject(obj);
				objOut.flush();
				objOut.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return new cloud.jgo.io.File(fileName);
		}
	
	/**
	 * 
	 * This method serializes an object.
	 * However, the object must implement the jgoserializable interface.
	 * @param obj The object that wants to serialize
	 * @param file The destination file
	 * @return the serialized file
	 */
	public static £ serializes(Object obj,cloud.jgo.io.File file){
			FileOutputStream fos=null;
			try {
				fos = new FileOutputStream(file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ObjectOutputStream objOut=null;
			try {
				objOut = new ObjectOutputStream(fos);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				objOut.writeObject(obj);
				objOut.flush();
				objOut.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return instance;
		}
	
	
	
	/**
	 * This method serializes an object.
	 * @param obj The object that wants to serialize
	 * @param fileName The name of the destination file
	 * @param func The function that you want to perform after serialization
	 * @return the home instance
	 */
	public static £ serializes(Object obj,String fileName,£Func func){
			FileOutputStream fos=null;
			try {
				fos = new FileOutputStream(new File(fileName));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ObjectOutputStream objOut=null;
			try {
				objOut = new ObjectOutputStream(fos);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				objOut.writeObject(obj);
				objOut.flush();
				objOut.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			func.function(new File(fileName));
		
		return instance ;
	}
	
	
	
	/**
	 * This method deserializes an object.
	 * @param serFile the serialized file
	 * @param <T> the type
	 * @return the deserialized object
	 */
	public static <T> T deserializes(cloud.jgo.io.File serFile) {
		FileInputStream fis=null;
		try {
			fis = new FileInputStream(serFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObjectInputStream inObj=null;
		try {
			inObj = new ObjectInputStream(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Object object=null;
		try {
			object = inObj.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			inObj.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (T)object;
	}
	
	
//	/**
//	 * This method deserializes an object.
//	 * @param serFile the serialized file
//	 * @return the deserialized object
//	 * @throws IOException
//	 * @throws ClassNotFoundException
//	 */
	
	/**
	 * 
	 * @param serFile the serialized file
	 * @param func The function that you want to perform after deserialization
	 * @return the home instance
	 */
	public static £ deserializes(cloud.jgo.io.File serFile,£Func func){
		FileInputStream fis=null;
		try {
			fis = new FileInputStream(serFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObjectInputStream inObj=null;
		try {
			inObj = new ObjectInputStream(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Object object=null;
		try {
			object = inObj.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			inObj.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		func.function(object);
		return instance ;
	}
	
	
	
	/**
	 * This method creates an Object
	 * @param class_ the class object
	 * @param <T> the type
	 * @return the object created
	 */
	public static <T> Object createObject(Class<T> class_) {
		Object obj = null ;
		try {
		obj =(T)class_.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj ;
	}
	
	/**
	 * This method creates an Object
	 * @param className the class object
	 * @return the object created
	 */
	public static Object createObject(String className){
		Class class_=null;
		Object obj =  null ;
		try {
			class_ = Class.forName(className);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			 obj = class_.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj ;
	}
	
	/**
	 * Check if the object is an instance of the class
	 * @param <T> the type
	 * @param class_ the class
	 * @param obj the object
	 * @return true if is an instance of class
	 */
	public static <T> boolean isAInstanceOf(Class<T>class_,Object obj){
		return class_.isInstance(obj);
	}
	
	/**
	 * This method creates an array
	 * @param <T> the type
	 * @param class_ the array class
	 * @param length the array length
	 * @return the array
	 */
	public static <T> Object createArray(Class<T>class_,int length){
		return (T)Array.newInstance(class_,length);
	}
	
	/**
	 * records the current value of the counter
	 * @return the home instance
	 */
	public static £ mark(){
		// prendo il valore del value
		
		valueMemorized = value();
		return instance;
	}
	
	/**
	 * Restore the value registered with "mark();"
	 * @return the home instance
	 */
	public static £ reset(){
		generalCount = valueMemorized ;
		return instance;
	}
	
	
	/**
	 * this method checks if you are connected to the internet
	 * @return true if connected to internet
	 */
	public static boolean connectedToInternet(){
		boolean value = false ;
		Enumeration<NetworkInterface> interfaces=null;
		try {
			interfaces = NetworkInterface.getNetworkInterfaces();
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while (interfaces.hasMoreElements()) {
		  NetworkInterface interf = interfaces.nextElement();
		  try {
			if (interf.isUp() && !interf.isLoopback()) {
			    value = true ;
			}
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return value ;	
	}
	
	
	// un altra buona catteristica

	private static int generalCount = 0 ;
	
	/**
	 * This method increases the counter
	 * @return the home instance
	 */
	public static £ increment(){
		generalCount++ ;
		return instance;
	}
	/**
	 * This method decreases the counter
	 * @return the home instance
	 */
	public static £ decrement(){
		generalCount-- ;
		return instance;
	}
	
	/**
	 * 
	 This method resets the counter to 0
	 * @return The home instance
	 */
	public static £ clears(){
		generalCount = 0 ;
		return instance;
	}
	
	/**
	 * This method returns the value of the counter
	 * @return the counter value
	 */
	public static int value(){
		return generalCount ;
	}
	
	/**
	 * This method executes a google query
	 * @param query the query you want to run
	 * @return the home instance
	 */
	public static £ executeGoogleQuery_2(String query){
		query = query.replaceAll(" ","+");
		try {
			Desktop.getDesktop().browse(new URL("http://www.google.com?#q="+query).toURI());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return instance;
	}
	
	/**
	 * This method executes a google query
	 * @param query the query you want to run
	 * @return the home instance
	 */
	public static £ executeGoogleQuery(String query){
		query = query.replaceAll(" ","+");
     try {
		£.CMD.command("start www.google.com?#q="+query);
	} catch (IOException e) {
		/*
		 
		JGO Auto-generated catch block
		Author : £ wasp91 £
		Date 29 dic 2017
		
		*/
		e.printStackTrace();
	} catch (InterruptedException e) {
		/*
		 
		JGO Auto-generated catch block
		Author : £ wasp91 £
		Date 29 dic 2017
		
		*/
		e.printStackTrace();
	}
     return instance;
		
	}
	/**
	 * This method converts from text to decimals
	 * @param text the text to convert
	 * @return the decimals list
	 */
	public static List<Integer> convertFromStringToDecimals(String text){
		char[]charat = text.toCharArray();
		List<Integer>decimalList = new ArrayList<>();
		for (int i = 0; i < charat.length; i++) {
			int decimal = (int)charat[i];
			decimalList.add(decimal);
		}
		
		return decimalList ;
	}
	
	
	/**
	 * This method blocks a file
	 * @param file the file you want to block
	 * @return the fileLock
	 */
	public static FileLock lock_file(cloud.jgo.io.File file){
		access = null ;
		try {
			access= new RandomAccessFile(file, "rw");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		channel = access.getChannel();
		
	    try {
			lockFile = channel.lock();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lockFile;
	}
	
	/**
	 * This method releases the file previously locked with method "lock_file();"
	 * @param lockFile the locked file
	 */
	public static void release_file(FileLock lockFile) {
		try {
			lockFile.release();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * This method converts from string to binary
	 * @param text the text to convert
	 * @return the binary text
	 */
	public static String convertFromStringToBinary(String text){
		StringBuffer binary = new StringBuffer();
		List<Integer>listDecimals = £.convertFromStringToDecimals(text);
		Iterator<Integer>iterator = listDecimals.iterator();
		while(iterator.hasNext()){
			int currentDecimal = iterator.next();
			String currentBinary=£.getBit(currentDecimal);
			binary.append(currentBinary+"\n");
		}
		return binary.toString() ;
	}
	
	
	/**
	 * 
     This method converts from binary text to alphanumeric text
	 * @param binary the binary text
	 * @return the converted string
	 */
	public static String convertFromBitToString(String binary){
		StringBuffer text = new StringBuffer();
		String[]split = binary.split("\n");
		int somma = 0;
		
		for (int i = 0; i < split.length; i++) {
			
			String flussoBit = new StringBuffer(split[i]).reverse().toString();
			
			// suddividiamo il flusso in caratteri
			char[]charat = flussoBit.toCharArray();
			
			// adesso abbiamo gia fatto la numerazione dei bit 
			
			// iteriamo i bit
			for (int j = 0; j < charat.length; j++) {
				// ottengo il bit
				String bitText = charat[j]+"";
				int bit = Integer.parseInt(bitText);
				// passiamo alla somma delle potenze
				double moltiplicazione = Math.pow(2, j)*bit;
				somma =(int) (somma+moltiplicazione);
				
			}
			
//			 qui prendo il carattere
			char charact = (char)somma;
			text.append(charact);
//			JOptionPane.showMessageDialog(null,"Ecco :"+somma);
//			JOptionPane.showMessageDialog(null,"Ascii :"+charact);
			somma
			= 0 ;
		}
		return text.toString();
	}
	
	/**
	 * This method converts a decimal to bit
	 * @param decimal the decimal you want to convert
	 * @return the bit in the form of a string
	 */
	public static String getBit(int decimal){
		int resto = 0 ;
		StringBuffer binaryCode = new StringBuffer();
		int ultimoQuoziente = 0 ;
		while(decimal>0){
			
			resto = decimal % 2 ; // questo mi dice se c'è il resto
//			System.out.println("Si sta dividendo "+decimal+" per due");
			decimal = decimal / 2 ;
			
			if(decimal == 1){
				ultimoQuoziente
				=
				decimal ;
			}
			binaryCode.append(resto);	
		}
		resto = ultimoQuoziente / 2 ;
		// aggiungo l'ultimo resto
		binaryCode.append(resto);
		
		// stampo il code binary
	return binaryCode.reverse().toString();
	}
	
	/**
	 * Get a fileChannel based on a file
	 * @param file the file
	 * @return the channel file
	 */
	public static FileChannel getFileChannel(File file){
		RandomAccessFile access=null;
		try {
			access = new RandomAccessFile(file, "rw");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FileChannel channel =  access.getChannel() ;
		return channel ;
	}
	
	
	/**
	 * This method divides the buffer based on the sign
	 * @param sign the sign that divides the buffer
	 * @param buffer the buffer that you want to divide
	 * @return a buffers list
	 */
	public static List<ByteBuffer>splitBuffer(String sign,ByteBuffer buffer){
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		while(buffer.hasRemaining()){
			out.write(buffer.get());
		}
		try {
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[]bytes = out.toByteArray();
		String text = new String(bytes);
		
		String[]split = text.split(sign);
		List<ByteBuffer>list = new ArrayList<>();
		for (int i = 0; i < split.length; i++) {
			byte[]bytesCurrent = split[i].getBytes();
			list.add(ByteBuffer.wrap(bytesCurrent));
		}
		
		return list ;
	}
	
	/*
	 * Anche questo metodo viene utilizzato per i buffer allocati
	 * e ritorna la fetta desiderata del buffer.L'utente ,una volta ottenuta
	 * la fetta potrà iterare i byte tramite il classico metodo hasRemaining()
	 * in un ciclo while
	 * @param offset
	 * @param buffer
	 * @return
	 */
	/**
	 * This method slices a buffer
	 * @param offset from where you want to slice
	 * @param buffer the buffer you want to slice
	 * @return the sliced buffer
	 */
	public static ByteBuffer sliceBuffer(int offset,ByteBuffer buffer){
		
		// il buffer viene impostato in modalità lettura
		
		buffer.flip();
		
		// impostiamo la posizione dalla quale vogliamo affettare il buffer
		buffer.position(offset);
		
		ByteBuffer slicedBuffer = buffer.slice();
		
		return slicedBuffer ;
		
	}
	
	/**
	 * 
	   This method divides the buffer.
	 * Set the buffer in read mode
	 * @param for_ for how many times you want to divide
	 * @param buffer the buffer that you want to divide
	 * @return the buffers list
	 */
	public static List<ByteBuffer>bufferDividers(int for_,ByteBuffer buffer){
		
		List<ByteBuffer>buffers = new ArrayList<>();
		
		// poniamo in modalità lettura
		
		buffer.flip(); // pos = 0 ,limit =  numeroDiByteScritti
		
		int limit = buffer.limit();
		double division = limit/for_;
		
		// la pos è uguale a 0
		
		int count = 0 ;
		ByteBuffer current = ByteBuffer.allocate(((int)division));
		while(buffer.hasRemaining()){
			count++ ;

			current.put((byte)buffer.get());
			
			System.out.println("Si è inserito :"+((char)current.get(current.position()-1)));

		    
			if(count == for_){
				
				// qui aggiungiamo il buffer alla lista 
				
				buffers.add(current);
				
				// resettiamo il cont a 0
				
				count = 0 ;
				
				// resistanziamo l'oggetto
				current = ByteBuffer.allocate(((int)division));
			}
		}
		
		// qui prima di ritornare la lista ,li poniamo tutti in modalità lettura
		
		for (int i = 0; i < buffers.size(); i++) {
			buffers.get(i).flip();
		}
		
	return buffers ;
		
	}
	
	/**
	 * This method writes an input buffer
	 * @param capacity la capacità del buffer
	 * @param exit la parola chiave che una volta digitata da input ci farà uscire
	 * @return Il buffer scritto
	 */
	public static ByteBuffer writeBufferFromInput(int capacity,String exit){
		ByteBuffer buffer = ByteBuffer.allocate(capacity);
		String line ;
		while(!(line = new Scanner(System.in).nextLine()).equalsIgnoreCase(exit)){
			byte[]bytes = line.getBytes();
			System.out.println("Inserted bytes #");
			buffer.put(bytes);
		}
		return buffer ;
	}
	/**
	 * 
	 * @author Martire91
	 * <br>
	 * <strong>This inner class deals with some checks on the main class.</strong> 
	 */
	public static class Check{
		/**
		 * the current version
		 */
		private static String currentVersion = "1.0.0"; 
		public final static cloud.jgo.io.File JGO_ROOT_FOLDER = new cloud.jgo.io.File(System.getProperty("user.home")+"/"+"jgo_association");
		public final static cloud.jgo.io.File JGO_FOLDER = new cloud.jgo.io.File(JGO_ROOT_FOLDER,"JGo");
		
		/**
		 * This method prints the JGO banner
		 */
		public static void printBanner(){
			StringBuffer buffer = new StringBuffer();
				char[]charat = new char[80];
				for (int i = 0; i < charat.length; i++) {
					charat[i] = '£';
					buffer.append(charat[i]);
				}
				System.err.println(buffer.toString());
				System.err.println("\t\t\t\tJ G O ©");
				System.err.println(buffer.toString());
				try {
					£.pause(1);
				} catch (HeadlessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.err.println("[*] JGO ActiVe - Version:"+Check.currentVersion+" - https://www.jgo.com/ - "+new Date());
				try {
					£.pause(2);
				} catch (HeadlessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.err.println("[*] Java General Operations  - Author:FRXWasp91");
				try {
					£.pause(1);
				} catch (HeadlessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.err.println("[*] WEBSITE : https://www.jgo.com/");
		}
		}
	
	/**
	 * 
	This method converts an object to xml.
	 * @param type The object class
	 * @param fileName The file name without format
	 * @param obj the object to convert
	 * @return The XML file
	 */
  public static cloud.jgo.io.File convertFromObjectToXML(Class type,String fileName,Object obj) {
	cloud.jgo.io.File file  = null ;
	  try {
		file = XMLConverter.convertFromObjectInXML(type, fileName, obj);
	} catch (JAXBException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  return file ;
  }
  
  
  /**
   * This method converts from XML to Object
   * @param file the XML file
   * @param type the object class
   * @return the object
   */
  public static Object convertFromXMLToObject(cloud.jgo.io.File file,Class type){
	  Object obj = null ;
	  try {
		obj = XMLConverter.convertFromXMLInObject(file, type);
	} catch (JAXBException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  return obj ;
  }
	

  /**
   * This method extracts the matrix from the buffer
   * @param buffer the buffer
   * @return the bytes matrix
   */
  public static byte[]getMatrixFromBuffer(ByteBuffer buffer){
	 byte[]matrix = new byte[buffer.remaining()];
	 ByteBuffer buffer_= buffer.get(matrix, 0, matrix.length);
	 return matrix = buffer_.array();
  }
  
  /**
   * This method gets a buffer from a file
   * @param file the file
   * @return the file buffer
   */
  public static ByteBuffer getBuffer(cloud.jgo.io.File file){
	  RandomAccessFile access =null ;
	  try {
		access = new RandomAccessFile(file,"rw");
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  FileChannel channel = access.getChannel();
	  ByteBuffer buffer = null ;
	  try {
		buffer = ByteBuffer.allocate((int)channel.size());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  try {
		channel.read(buffer);
		channel.close();
		  access.close();  
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	  return buffer ;
  }
  
  /**
   * This method gets a file from a buffer.
   * @param file the file from which the buffer is to be obtained
   * @param bufferCapacity the buffer capacity
   * @param printBytes if true prints the bytes
   * @return the file buffer
   */
  public static ByteBuffer getBuffer(cloud.jgo.io.File file,int bufferCapacity,boolean printBytes){
	  RandomAccessFile access=null;
	try {
		access = new RandomAccessFile(file,"rw");
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  FileChannel channel = access.getChannel();
	  ByteBuffer buffer = ByteBuffer.allocate(bufferCapacity);
	  int leggi ;
	  try {
		while ((leggi = channel.read(buffer))!=-1) {
			   if(printBytes){
				   // stampo i byte
				   
				   // imposto come lettura
				   
				   buffer.flip();
				   while (buffer.hasRemaining()) {
					£.outPrintln("Current bytes :"+(char)buffer.get());
				}
				   
				   // imposto per scrittura
				   
				   buffer.clear();
			   }
		  }
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  // chiudo il canale
	  try {
		channel.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  try {
		access.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	return buffer ;  
  }
  
  
	private static String getId() {
		return id;
	}

	private static String id = "jo_"+new Random().nextInt(10)+""+new Random().nextInt(10)+""+new Random().nextInt(10);
	
	public static Encrypts getEncrypt() {
		return encrypt;
	}
	/**
	 * This method returns the system properties
	 * @return the system properties
	 */
    public static Properties SystemProperties(){
		return System.getProperties();
    	
    }
    
    /**
     * Returns the home folder
     * @return the home folder
     */
    public static java.io.File getHomeDirectory(){
    	
    	return FileSystemView.getFileSystemView().getHomeDirectory();
    }
    
    /**
     * Returns the documents folder
     * @return the documents folder
     */
    public static java.io.File getDocumentsDirectory(){
    	
    	return FileSystemView.getFileSystemView().getDefaultDirectory();	
    }
    
    
    
    
    /**
     * Returns the root list folders
     * @return the root list folders
     */
    public static java.io.File[] getListRootsDirectory(){
    
    	return File.listRoots() ;
    
    }
    
    
	/**
	 * This method writes the system properties to a file
	 * @param fileName the file name
	 * @param authorFileName the comment
	 * @return the home instance
	 */
	public static £ SystemProperties(String fileName,String authorFileName){
		File file = new File(fileName);
		FileOutputStream fos=null;
		try {
			fos = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Properties props = System.getProperties();
		try {
			props.store(fos,authorFileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return instance;
		
	}
	
	
	/**
	 * This method returns the file format
	 * @param file the file from which you want to take the format
	 * @return the format
	 */
	public static String extractFormatFromFile(java.io.File file){
		String format = null ;
		if(!file.isDirectory()){
			
		
			if(file.getName().contains(".")){
				String[]split = file.getName().split("\\.");
				
				
				
				if(split.length == 2){
					
					
					format = split[1]; // return il formato senza punto
				}
				else{
					
					format = split[split.length-1]; // return ultimo elemento
				}	
			}
			
			
			
		}
		
		return
				 format  ;
	}
	
	
	/**
	 * This method returns the file_name format
	 * @param nameFile the file_name from which you want to take the format
	 * @return the format
	 */
	public static String extractFormatFromFileName(String nameFile){
		
		
		
		String format = null ;
		
			
		
			if(nameFile.contains(".")){
				String[]split = nameFile.split("\\.");
				
				
				
				if(split.length == 2){
					
					
					format = split[1]; // return il formato senza punto
				}
				else{
					
					format = split[split.length-1]; // return ultimo elemento
				}	
			}
			
			
			
		
		
		return
				 format  ;
		
	}
	
	
	/**
	 * This method gets the file icon
	 * @param pathname the file name
	 * @return the file icon
	 */
	public static Icon getIconFromFile(String pathname){
		
		cloud.jgo.io.File file = new cloud.jgo.io.File(pathname);
		
		return file.getIcon();
	}
	
   /**
    * This method gets the file icon	
    * @param pathname the file name
    * @return teh file icon
    */
   public static Icon getImageIconFromFile(String pathname){
		
		cloud.jgo.io.File file = new cloud.jgo.io.File(pathname);
		
		return file.getImageIcon();
	}
	

	private static int sec =0;
	
	
	/**
	 * Equivalent to System.out.print()
	 * @param text the text you want to print
	 * @return the home instance
	 */
	public static £ outPrint(String text){
		System.out.print(text);
		return instance;
	}
	
	
	/**
	 * This method gets a characters array from a byte buffer
	 * @param buffer the bytes buffer
	 * @return the charats array
	 */
	public static char[]getCharatFromBytes(byte[]buffer){
		char[]charat = new char[buffer.length];
		for (int i = 0; i < charat.length; i++) {
			charat[i] = (char)buffer[i];
		}
		return charat ;
	}
	
	/**
	 * This method records from the microphone and saves the registration  thing on a file
	 * @param minutes the registration minute
	 * @param fileNameWithoutFormat the path to the file without specifying the format
	 * @param openFile se impostato su true, apre il file al termine della registrazione
	 * @return the home instance
	 */
	public static £ recordFromMicrophone(int minutes,String fileNameWithoutFormat,boolean openFile){
		recorder = new Recorder(minutes,fileNameWithoutFormat,openFile);
		
		 Thread stopper = new Thread(new Runnable() {
		        public void run() {
		            try {
		                Thread.sleep(recorder.getMinute());
		            } catch (InterruptedException ex) {
		                ex.printStackTrace();
		            }
		            try {
						recorder.end();
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        }
		    });

		    stopper.start();
		    recorder.startRegistration();
		    return instance ;
	}
	
	/**
	 * This method records from the microphone and saves the registration  thing on a file
	 * @param minutes registration duration
	 * @param fileNameWithoutFormat the path to the file without specifying the format
	 * @param openFile se impostato su true, apre il file al termine della registrazione
	 * @param func the function that you want to perform at the end of the recording
	 * @return the home instance
	 */
	public static £ recordFromMicrophone(int minutes,String fileNameWithoutFormat,boolean openFile,£Func func){
		recorder = new Recorder(minutes,fileNameWithoutFormat,openFile);
		
		 Thread stopper = new Thread(new Runnable() {
		        public void run() {
		            try {
		                Thread.sleep(recorder.getMinute());
		            } catch (InterruptedException ex) {
		                ex.printStackTrace();
		            }
		            try {
						recorder.end();
						func.function(null);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        }
		    });

		    stopper.start();
		    recorder.startRegistration();
		    return instance ;
	}
	
	/**
	 * 
	 this method compresses the folder
	 * @param folder the folder you want to compress
	 * @param archiveName The archive name
	 * @return the home instance
	 */
	public static £ compressFolder(File folder,String archiveName){
		Compressor compressor = null ;
		try {
			compressor = new Compressor(archiveName,folder);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		compressor.compressFolder(compressor.getFile().listFiles(),new ZipEntry(folder.getName()));
		try {
			compressor.getZipOut().flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			compressor.getZipOut().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return instance;
	}
	
	/**
	 * This method opens the file manager
	 * @param props the properties we want to associate with this filemanager
	 * @param mode the way we want to use
	 * @return the selected file
	 */
	public static File openFileManager(Properties props,int mode){
		fileManager.setProps(props);
		File file=fileManager.setVisible(true,mode,null);
	    return file ;
	}
	
	/**
	 * This method opens the file manager
	 * @param mode the way we want to use
	 * @return the selected file
	 */
	public static File openFileManager(int mode){
		File file=fileManager.setVisible(true,mode,null);
	    return file ;
	}
	
	/**
	 * This method converts from string to file
	 * @param text the text to convert
	 * @param pathDestination the destination file name
	 * @return the file
	 */
	public static java.io.File convertFromStringToFile(String text,String pathDestination){
		BufferedWriter writer=null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(pathDestination))));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		char[]charat = text.toCharArray();
			for (int i = 0; i < charat.length; i++) {
				if (charat[i]=='\n') {
					try {
						writer.newLine();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				try {
					writer.write(charat[i]);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		try {
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return new File(pathDestination);
		
	}
	
	
	/**
	 * This method converts a file to string
	 * @param file from
	 * @return the file text
	 */
	public static java.lang.String convertFromFileToString(java.io.File file){
		BufferedReader reader=null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String leggi ;
		StringBuffer buffer = new StringBuffer();
		try {
			while ((leggi = reader.readLine())!=null) {
				buffer.append(leggi+"\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String text = buffer.toString();
	    return text ;
	}
	
	/**
	 * This method writes the properties to a file
	 * @param pathDestination the file name
	 * @param keys the keys
	 * @param values the values
	 * @param comment the comment
	 * @return the properties file
	 */
	public static File writePropertiesFile(String pathDestination,String[]keys,String[]values,String comment){
		Properties props = new Properties();
		BufferedWriter writer=null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pathDestination)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			props.store(writer	,comment);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < keys.length; i++) {
			for (int j = 0; j < values.length; j++) {
				String row = keys[i]+":"+values[i];
				try {
					writer.write(row);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					writer.newLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break ;
			}
		}
		try {
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new File(pathDestination);
	}
	
	/**
	 * This method gets a byte buffer from a file
	 * @param file the file from which the buffer is to be obtained
	 * @return the bytes buffer
	 */
	public static byte[] getByteFrom(File file){
		FileInputStream fis=null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] buffer=null;
		try {
			buffer = new byte[fis.available()];
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fis.read(buffer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   try {
		fis.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   return buffer ;
	}
	
	
	/**
	 * This method gets a byte buffer from an image
	 * @param image the image from which the buffer is to be obtained
	 * @return the bytes buffer
	 */
	public static byte[]getByteFrom(BufferedImage image){
		ByteArrayOutputStream fos = new ByteArrayOutputStream();
		try {
			ImageIO.write(image, "PNG",fos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[]buffer = fos.toByteArray();
		try {
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return buffer ;
	}
	
	/**
	 * This method gets a byte buffers from a string[]array
	 * @param array the string array
	 * @return the buffers
	 */
	public static ArrayList<byte[]> getByteFrom(String[]array){
	ArrayList<byte[]>bytesMatrix = new ArrayList<>();
	for (int i = 0; i < array.length; i++) {
		String element = array[i];
		byte[]buffer = element.getBytes();
		bytesMatrix.add(buffer);
	}
		return bytesMatrix ;
	}
	
	/**
	 * This method gets a byte buffers from a arrayList
	 * @param list the string array
	 * @return the buffers
	 */
	public static ArrayList<byte[]> getByteFrom(ArrayList<String>list){
		ArrayList<byte[]>bytesMatrix = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			String element = list.get(i);
			byte[]buffer = element.getBytes();
			bytesMatrix.add(buffer);
		}
			return bytesMatrix ;
		}
	
	
	/**
	 * This method creates a folder
	 * @param folderName the folder name
	 * @return true if the folder was created
	 */
	public static boolean md(String folderName){
		File folder = new File(folderName);
		return folder.mkdir();
	}
	
	/**
	 * This method creates a folder
	 * @param folderName the folder name
	 * @return true if the folder was created
	 */
	public static £ _md(String folderName){
		File folder = new File(folderName);
		folder.mkdir();
		return instance ;
	}
	
	/**
	 * This method creates a folder to Desktop
	 * @return the home instance
	 */
	public static £ md(){
		File folder = new File(£.getHomeDirectory(),"Folder_"+new Random().nextInt(1000)+new Random().nextInt(1000)+new Random().nextInt(1000));
		boolean created = folder.mkdir();
		System.out.println("Folder "+folder.getPath()+" created :"+created);
		return instance;
	}
	
	
	/**
	 * This method returns the screen size
	 * @return the screen dimension
	 */
	public static Dimension  getScreenSize(){
		return Toolkit.getDefaultToolkit().getScreenSize();
	}
	
	/**
	 * This method copys a text to clipboard
	 * @param text the text you want to copy
	 * @return the home instance
	 */
	public static £ copyToClipboard(String text){
		StringSelection selection = new StringSelection(text);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(selection, selection);
		return instance ;
	}
	
	
	/**
	 * This method runs a time interval.
	 * @param millisec the unit of time measurement that you want to use
	 * @param output the output you want to print
	 * @return the home instance
	 */
	public static £ interval(int millisec,String output){
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				£.out().println(output);
			}
		};timer.schedule(task, 0,millisec);
		return instance;
	}
	
	
	/**
	 * This method runs a time interval.
	 * @param millisec the unit of time measurement that you want to use
	 * @param func the function that you want to perform at the interval
	 * @return the home instance
	 */
	public static £ interval(int millisec,£Func func){
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				func.function(null);
			}
		};timer.schedule(task, 0,millisec);
		return instance;
	}
	
	/**
	 * This method runs a time interval.
	 * @param millisec the unit of time measurement that you want to use
	 * @param secRunning the seconds you want to keep this timer alive
	 * @param startDelay the delay with which you want to start this timer
	 * @param intervalFunc the function that you want to perform at the interval
	 * @param endFunc the function that you want to perform at the end
	 * @return the home instance
	 */
	public static £ interval(int millisec,int secRunning,int startDelay,£Func intervalFunc,£Func endFunc){
		sec = secRunning ;
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			public void run() {
				sec--;
				if(sec<=0){
					timer.cancel();
					endFunc.function(null);
				}
				else{
					intervalFunc.function(null);	
				}
				
			}
		};timer.schedule(task,startDelay,millisec);
		return instance;
	}
	
	
	
	/**
	 * This method runs a time interval.
	 * @param millisec the unit of time measurement that you want to use
	 * @param output the output you want to print
	 * @param outputEnd the output you want to print at the end
	 * @param secRunning the seconds you want to keep this timer alive
	 * @return the home instance
	 */
	public static £ interval(int millisec,String output,String outputEnd,int secRunning){
		sec = secRunning ;
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			public void run() {
				sec--;
				if(sec<=0){
					timer.cancel();
					£.out().println(outputEnd);
				}	
				else{
					£.out().println(output);
				}
			}
		};timer.schedule(task, 0,millisec);
		return instance;
	}
	
	/**
	 * This method runs a time interval.
	 * @param millisec the unit of time measurement that you want to use
	 * @param listener the listener you want to use
	 * @return the home instance
	 */
	public static £ interval(int millisec,ActionListener listener){
		javax.swing.Timer timer = new javax.swing.Timer(millisec, listener);
		timer.start();
		System.out.println("Waiting for events ...");
		return instance;
	}
	
	/**
	 * Equivalent to System.out.println();
	 * @param text the text you want to print
	 * @return the home instance
	 */
	public static £ outPrintln(String text){
		System.out.println(text);
		return instance;
	}
	
	/**
	 * Equivalent to System.err.println();
	 * @param text the text you want to print
	 * @return the home instance
	 */
	public static £ errPrintln(String text){
		System.err.println(text);
		return instance;
	}
	/**
	 * This method prints an array
	 * @param args the array
	 * @return the home instance
	 */
	public static £ printArray(String[]args){
		for (int i = 0; i < args.length; i++) {
			System.out.println((i+1)+")"+args[i]);
		}
		return instance;
	}
	
	/**
	 * This method opens a URL on the browser
	 * @param url the url you want to open
	 * @return the home instance
	 */
	public static £ openUrl(String url){
		try {
			Desktop.getDesktop().browse(new URI(url));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return instance;
	}
	private String name ;
	protected static £ instance= null ;
	// esiste solo una instanza della classe
	// che si inizializza in questo blocco statico
	static{
		try {
			instance = getInstance();
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * unico costruttore è privato
	 */
	protected £(){
		// nothing ...
	}
	protected static £ getInstance() throws HeadlessException, AWTException, IOException{
		if(instance == null){
			instance = new £();
			encrypt =new Encrypts(Encrypts.TEXT_KEY_DEFAULT,"jo_3434");
		}
		else{
			if (instance instanceof j£) {
				instance = new £();
			}
		}
		return instance ;
	}
	/**
	 * This method executes a screenshot.
	 * @param PNGFileName the file path
	 * @return the home instance
	 */
		public static boolean screenshot(String PNGFileName){
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			Rectangle rec = new Rectangle(dim);
			BufferedImage image=null;
			try {
				image = new Robot().createScreenCapture(rec);
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				ImageIO.write(image, "PNG", new File(PNGFileName));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(new File(PNGFileName).exists()){
				return true ;
			}
			else{
				return false ;
			}   
		}
		
		/**
		 * This method executes a screenshot.
		 * @param func the function that you want to run after the screenshot
		 * @return the home instance
		 */
		public static £ screenshot(£Func func){
			 String nameFile = "Screenshot.png" ;
			if(counterScreenShot > 0){
				nameFile = "Screenshot_"+counterScreenShot+".png";
			}			
		final File desktop = £.getHomeDirectory();
		String pathDesktop = desktop.getPath();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle rec = new Rectangle(dim);
		BufferedImage image=null;
		try {
			image = new Robot().createScreenCapture(rec);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ImageIO.write(image, "PNG", new File(pathDesktop+"\\"+nameFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// qui incremento la var statica counter
		counterScreenShot++ ;
		func.function(null);
		return instance;
		}
		
		/**
		 * This method executes a screenshot.
		 * @param PNGFileName the file path
		 * @param func the function you want to perform after shooting
		 * @return the home instance
		 */
		public static £ screenshot(String PNGFileName,£Func func){			
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle rec = new Rectangle(dim);
		BufferedImage image=null;
		try {
			image = new Robot().createScreenCapture(rec);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ImageIO.write(image, "PNG", new File(PNGFileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// qui incremento la var statica counter
		func.function(null);
		return instance;
		}
		
		
		
		
		/**
		 * This method executes a screenshot.
		 * @param PNGFileName the file path
		 * @param open if true, the image is opened
		 * @return the home instance
		 */
      public static boolean screenshot(String PNGFileName,boolean open){
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			Rectangle rec = new Rectangle(dim);
			BufferedImage image = null;
			try {
				image = new Robot().createScreenCapture(rec);
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				ImageIO.write(image, "PNG", new File(PNGFileName));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(new File(PNGFileName).exists()){
				try {
					Desktop.getDesktop().open(new File(PNGFileName));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return true ;
			}
			else{
				return false ;
			}
      }
      
      /**
       * This method emits an acoustic signal.
       * @return the home instance
       */
      @SuppressWarnings("static-access")
      public static £ beep(){ 
    	  Toolkit.getDefaultToolkit().beep();
    	  
    	  try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  return instance ;
      }
   
      /**
       * This method emits an acoustic signal.
       * @param times How many times do you want to play
       * @return the home instance
       */
      public static £ beep(int times){
    	  for (int i = 0; i < times; i++) {
			beep();
    	  }
    	 return instance;
      }
      
      /**
       * This method opens the regedit <strong>(WINDOWS)</strong>
       * @param times the times you want to open
       * @return the home instance
       */
      public static £ openRegedit(int times){
    	  for (int i = 0; i <times; i++) {
			openRegedit();
		}
    	  return instance;
      }
      
      /**
       * This method opens the regedit <strong>(WINDOWS)</strong>
       * @return the home instance
       */
      public static £ openRegedit(){
    	  try {
			£.CMD.command("regedit");
		} catch (IOException e) {
			/*
			 
			JGO Auto-generated catch block
			Author : £ wasp91 £
			Date 06 gen 2018
			
			*/
			e.printStackTrace();
		} catch (InterruptedException e) {
			/*
			 
			JGO Auto-generated catch block
			Author : £ wasp91 £
			Date 06 gen 2018
			
			*/
			e.printStackTrace();
		} 
    	  return instance;
      }
      
      /**
       * This method opens the windows services <strong>(WINDOWS)</strong>
       * @param times the times you want to open
       * @return the home instance
       */
      public static £ openServices(int times){
    	  for (int i = 0; i < times; i++) {
			openServices();
		}
    	return instance;
      }
      
      /**
       * This method opens the windows services <strong>(WINDOWS)</strong>
       * @return the home instance
       */
      public static £ openServices(){
    	  Runtime runtime = Runtime.getRuntime();
    	  Process process=null;
		try {
			process = runtime.exec("C:\\Windows\\System32\\cmd.exe");
		} catch (IOException e1) {
			/*
			 
			JGO Auto-generated catch block
			Author : £ wasp91 £
			Date 06 gen 2018
			
			*/
			e1.printStackTrace();
		}
    	  byte[]buffer = "services.msc\r\n".getBytes();
    	  OutputStream out = process.getOutputStream();
    	  try {
			out.write(buffer);
		} catch (IOException e1) {
			/*
			 
			JGO Auto-generated catch block
			Author : £ wasp91 £
			Date 06 gen 2018
			
			*/
			e1.printStackTrace();
		}
    	  try {
			out.flush();
		} catch (IOException e1) {
			/*
			 
			JGO Auto-generated catch block
			Author : £ wasp91 £
			Date 06 gen 2018
			
			*/
			e1.printStackTrace();
		}
    	  try {
			out.close();
		} catch (IOException e1) {
			/*
			 
			JGO Auto-generated catch block
			Author : £ wasp91 £
			Date 06 gen 2018
			
			*/
			e1.printStackTrace();
		}
    	  try {
			process.waitFor();
		} catch (InterruptedException e1) {
			/*
			 
			JGO Auto-generated catch block
			Author : £ wasp91 £
			Date 06 gen 2018
			
			*/
			e1.printStackTrace();
		}
		return instance;
      }
       
      
      /**
       * This method prints a string matrix
       * @param matrix the matrix
       * @return the home instance
       */
      public static £ printMatrix(String[][]matrix){
    	  for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.println(matrix[i][j]);
			}
		}
    	  return instance;
      }
      
      /**
       * This method returns the string byte buffer
       * @param text the text from which you want to get the buffer
       * @return the bytes buffer
       */
      public static byte[] getBytesFrom(String text){
    	  return text.getBytes();
      }
      
      
      /**
       * Equivalent to Thread.sleep ();
       * @param sec the seconds for which you want to pause
       * @return the home instance
       */
      public static £ pause(int sec){
    	  sec = 1000 * sec ;
    	  try {
			Thread.sleep(sec);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  return instance;
      }
      
      /**
       * Equivalent to Thread.sleep ();
       * @param sec the seconds for which you want to pause
       * @param func the function that you want to perform at the end of the pause
       * @return the home instance
       */
      public static £ pause(int sec,£Func func){
    	  sec = 1000 * sec ;
    	  try {
			Thread.sleep(sec);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  func.function(null);
    	  
    	  return instance;
      }
      
      /**
       * Equivalent to Thread.sleep ();
       * @param minutes the min for which you want to pause
       * @param startFunc the function that you want to perform at the start of the pause
       * @param endFunc the function that you want to perform at the end of the pause
       * @return the home instance
       */
      public static £ pause(int minutes,£Func startFunc,£Func endFunc){
    	 startFunc.function(null);
    	  minutes = 60000 * minutes ;
    	  try {
			Thread.sleep(minutes);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  endFunc.function(null);
    	  return instance;
      }
      
      /**
       * This method downloads a resource
       * @param url the resource url
       * @param download_OK string download - positive
       * @param download_NO string download - negative
       * @param pathDestination destination path
       * @return the home instance
       */
      public static £ download(String url,String download_OK,String download_NO, String pathDestination){
    	// questo metodo chiama un thread
    	  try {
			download.setUrl(new URL(url));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  download.setDownloadNO(download_NO);
    	  download.setDownloadOK(download_OK);
    	  download.setPathDestination(pathDestination);
    	  download.start();
    	  return instance;
      }
      
      
      /**
       * This method downloads an image
       * @param url the image url
       * @param pathDestination the destination path file
       * @param imageFormat the image format
       * @return the home instance
       */
      public static £ downloadImage(String url,String pathDestination,String imageFormat){
    	  
    	  try {
			BufferedImage image = ImageIO.read(new URL(url));
			ImageIO.write(image,imageFormat,new File(pathDestination));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  return instance;
      }
      
      /**
       * Equivalent to scanner.nextLine(System.in);
       * @return the input string
       */
      public static String nextLine(){
  		Scanner scanner = new Scanner(System.in);
  		return scanner.nextLine();
  	}
      
      /**
       * This method initializes an array from input
       * @param dimArray the array dim
       * @return the array
       */
      public static String[] initArrayFromInput(int dimArray){
    	  
    	  String[]array = new String[dimArray];
    	  for (int i = 0; i < array.length; i++) {
    		  £.outPrint("Insert the "+(i+1)+" element :");
			String el=£.nextLine();
			array[i] = el ;
		}
    	  return array ;
    	  
      }
      
     
      /**
       * this method imports a file via jfilechooser
       * @return the imported file
       */
      public static File importFile(){
    	  JFileChooser choose = new JFileChooser();
    	  choose.setCurrentDirectory(£.getHomeDirectory());
    	  choose.setApproveButtonText("open");
    	  choose.setDialogTitle("Import file");
    	  choose.setFileView(new JFileView());
    	  int result=choose.showOpenDialog(null);
    	  File file = null ;
    	  if (result == JFileChooser.APPROVE_OPTION) {
			return file = choose.getSelectedFile();
		}
    	  else{
    		  return null ;
    	  }
      }
      
      
      /**
       * this method imports a files via jfilechooser
       * @return the imported files
       */
      public static File[]importFiles(){
    	  JFileChooser choose = new JFileChooser();
    	  choose.setCurrentDirectory(£.getHomeDirectory());
    	  choose.setMultiSelectionEnabled(true);
    	  choose.setApproveButtonText("open");
    	  choose.setDialogTitle("Import file");
    	  choose.setFileView(new JFileView());
    	  int result=choose.showOpenDialog(null);
    	  if (result == JFileChooser.APPROVE_OPTION) {
			return choose.getSelectedFiles();
		}
    	  else{
    		  return null ;
    	  }
      }
      
      
      // adesso qui mi serve minimo un metodo che mi permetta di salvare un file 
      
      /**
       * This method saves a file via the jfilechooser
       * @return the home instance
       */
      public static £ saveFile(){
    	  JFileChooser choose = new JFileChooser();
    	  choose.setCurrentDirectory(£.getHomeDirectory());
    	  choose.setMultiSelectionEnabled(true);
    	  choose.setApproveButtonText("open");
    	  choose.setDialogTitle("Import file");
    	  choose.setFileView(new JFileView());
    	  int result=choose.showSaveDialog(null);
    	  if (result == JFileChooser.APPROVE_OPTION) {
			File savedFile = choose.getSelectedFile();
			// qui creo il file 
			try {
				savedFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }
    	  else{
    		  choose.setVisible(false);
    	  }
    	  return instance ;
      }
      
      
      /**
       * This method writes a file
       * @param file the file you want to write
       * @param append if true appends the new text
       * @param text the text
       * @return the home instance
       */
      public static £ writeFile(File file,boolean append,String[]text){
    	  BufferedWriter writer=null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,append)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  for (int i = 0; i < text.length; i++) {
			try {
				writer.write(text[i]);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				writer.newLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    	  try {
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  return instance;
      }
      
      /**
       * This method removes a file
       * @param file the file to be removed
       * @return the home instance
       */
      public static £ deleteFile(File file){
    	  boolean result=file.delete();
    	  if(result != true){
    		  file.deleteOnExit();
    	  }
    	  return instance;
      }
      
      
     
      /**
       * Questo metodo genera una stringa casuale
       * @param length the string length
       * @return the random string
       */
      public static String generateStringRandom(int length){
    	  final char[]charat = 
{'a','b','c','d','e','f','g','h','i','l','m','n','o','p','q','r','s','t','u','v','z','y','k'};
    	  Random random = new Random();
          final int MAXIMUM = charat.length;
          char[]charatString = new char[length];
          for (int i = 0; i < charatString.length; i++) {
			charatString[i] = charat[random.nextInt(MAXIMUM)];
		}
    	  return new String(charatString);
      } 
      
      public static int generateIntRandom(final int maximum){
    	  return new Random().nextInt(maximum);
      }
      
      
      
      /**
       * This method searches for a file
       * @param folder the folder in which to search
       * @param nameFile the file name
       * @param open if it is true, open the file after finding it
       * @return the home instance
       */
      public static £ searchFile(File folder,String nameFile,boolean open){
    	  boolean trovato = false ;
    	  String pathTrovato = null;
    	  for (int i = 0; i < folder.listFiles().length; i++) {
			if(folder.listFiles()[i].isDirectory()){
				searchFile(folder.listFiles()[i],nameFile,open);
			}
			else{
				if (nameFile.toLowerCase().equals(folder.listFiles()[i].getName().toLowerCase())) {
					trovato = true ;
					pathTrovato = folder.listFiles()[i].getPath() ;
					System.out.println("File trovato :"+pathTrovato);
					if( open == true ){
						try {
							Desktop.getDesktop().open(new File(pathTrovato));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					break ;
				}
				
			}
		} 
    	  return instance;
      }
      
      /**
       * This method prints a neater mex in the console.
       * Types of granted messages:<br>
       * <ul>
       * <li>{@link £#CONSOLE_MEX_INFO} - For information messagges</li>
       * <li>{@link £#CONSOLE_MEX_WARNING} - For warning messagges</li>
       * <li>{@link £#CONSOLE_MEX_ERROR} - For error messagges</li>
       * </ul>
       * <br>
       * @param text The mex text
       * @param symbolFrame the frame symbol
       * @param length the frame length
       * @param type the mex type
       * @return the home instance
       */
      public static £ consoleMex(String text,char symbolFrame,int length,final String type){
    	  System.out.println(new Date().toString().toUpperCase()+" - Mex Type :"+type);
    	  for (int i = 0; i < length; i++) {
			System.out.print(symbolFrame);
		}
    	  System.out.print("\n"+text+"\n");
    	  
    	  for (int i = 0; i < length; i++) {
  			System.out.print(symbolFrame);
  		}
    	  System.out.println("\n");
    	  return instance;
      }
      
      /**
       * This method opens the cd rom
       * @return the home instance
       */
      public static £ openCdPlayer(){
    	
    	  /*
    	   
Set oWMP = CreateObject("WMPlayer.OCX.7")
Set colCDROMs =oWMP.cdromCollection
if colCDROMs.Count >= 1 then
for i = o to colCDROMs.Count - 1
colCDROMs.Item(i).Eject
colCDROMs.Item(i).Eject
Next' cdrom
End if
    	   
    	   
    	   */
    	  
    	  
    	  InputStream in = £.class.getResourceAsStream("/config/x.vbs");
    	  BufferedReader reader = new BufferedReader(new InputStreamReader(in));
    	  File file = new File(System.getProperty("user.home")+"/jgo_open_cd.vbs");
    	  BufferedWriter writer=null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	  String leggi ;
    	  try {
			while ((leggi = reader.readLine())!=null) {
				writer.write(leggi);
				writer.newLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  try {
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  
    	  // apro il file 
    	  
    	  £.executeProgram(file);
    	  
    	  // imposto il file che alla fin del programma deve essere cancellato
    	  
    	  file.deleteOnExit();
    	  
    	  try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	   
    	  return instance;
      }
      
      /**
       * This method opens the cd-rom.
       * @param message The message you want to print after opening
       * @return the home instance
       */
      public static £ openCdPlayer(String message){
    	  
    	  InputStream in = £.class.getResourceAsStream("/config/x.vbs");
    	  BufferedReader reader = new BufferedReader(new InputStreamReader(in));
    	  File file = new File(System.getProperty("user.home")+"/jgo_open_cd.vbs");
    	  BufferedWriter writer=null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	  String leggi ;
    	  try {
			while ((leggi = reader.readLine())!=null) {
				writer.write(leggi);
				writer.newLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  try {
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  
    	  // apro il file 
    	  
    	  £.executeProgram(file);
    	  
    	  // imposto il file che alla fin del programma deve essere cancellato
    	  
    	  file.deleteOnExit();
    	  
    	  JOptionPane.showMessageDialog(null,message,"Information",JOptionPane.INFORMATION_MESSAGE);
    	  return instance ;
    	   
      }
      
      
      /**
       * This method opens the cd-rom.
       * @param func the function you want to perform after opening
       * @return the home instance
       */
      public static £ openCdPlayer(£Func func){
    	  InputStream in = £.class.getResourceAsStream("/config/x.vbs");
    	  BufferedReader reader = new BufferedReader(new InputStreamReader(in));
    	  File file = new File(System.getProperty("user.home")+"/jgo_open_cd.vbs");
    	  BufferedWriter writer=null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  String leggi ;
    	  try {
			while ((leggi = reader.readLine())!=null) {
				writer.write(leggi);
				writer.newLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  try {
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  
    	  // apro il file 
    	  
    	  £.executeProgram(file);
    	  
    	  // imposto il file che alla fin del programma deve essere cancellato
    	  
    	  file.deleteOnExit();
    	  
    	 func.function(null);
    	 return instance ;
    	   
      }
      
      
     /**
      * This method opens facebook
      * @return the home instance
      */
     public static £ openFacebook(){
    	try {
		£.openUrl("https://www.facebook.com/");
		} catch (HeadlessException e) {
			/*
			 
			JGO Auto-generated catch block
			Author : £ wasp91 £
			Date 13 gen 2018
			
			*/
			e.printStackTrace();
		}
    	 return instance;
     }
     
     
     /**
      * This method creates a file
      * @param fileName the file name
      * @return the home instance
      */
     public static £ createFile(String fileName){
    	 cloud.jgo.io.File file = new cloud.jgo.io.File(fileName);
    	 return instance;
     }
     
     /**
      * This method creates a file
      * @param parent the folder in which to create the file
      * @param fileName the file name
      * @return the home instance
      */
     public static cloud.jgo.io.File createFile(File parent,String fileName){
    	 cloud.jgo.io.File file = new cloud.jgo.io.File(parent,fileName);
    	 return file ;
     }
      
      
     /**
      * This method runs a program
      * @param file the executable file
      * @return the home instance
      */
      public static £ executeProgram(File file){
    	  if (Desktop.isDesktopSupported()) {
    		  try {
    				Desktop.getDesktop().open(file);
    				 return instance;
    		  } catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    				return null ;
    		  }
    	  }
    	  else{
    		  return null ;
    	  }
      }
      
      
      
     
      
      /**
       * This method copies a file
       * @param from The file to be copied
       * @param destination the destination file
       * @return if it returns true then the file has been copied.
       */
      public static boolean copyFile(File from,File destination){
    	 if (from.exists()) {
    		 FileInputStream fis = null ;
    		 try {
				fis = new FileInputStream(from);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       	  byte[] buffer=null;
		try {
			buffer = new byte[fis.available()];
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       	  try {
			fis.read(buffer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       	  try {
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       	  FileOutputStream fos = null ;
       	  try {
			fos = new FileOutputStream(destination);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       	  try {
			fos.write(buffer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       	  try {
			fos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       	  try {
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       	  return true ;
		}
    	 else {
			return false ;
		}  
      }
      
      /**
       * It is obvious to say that the files must be simple text files
       * @param toFile the destination file
       * @param fromFiles the files you want to copy
       * @return the home instance
       */
      public static £ copyFiles(cloud.jgo.io.File toFile,cloud.jgo.io.File...fromFiles){
    	  BufferedWriter writer=null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(toFile, true)));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	  for (int i = 0; i < fromFiles.length; i++) {
			File current = fromFiles[i];
			String text=null;
			try {
				text = £.readFile(current);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				writer.write(text);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				writer.newLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    	  try {
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
          return instance;
      }
      
     
      
      /**
       * this method creates a file
       * @param filePath the file path
       * @return the file instance
       */
      public static cloud.jgo.io.File fl(String filePath){
    	  cloud.jgo.io.File file = new cloud.jgo.io.File(filePath);
    	  return file ;
      }
      /**
       * This method creates a file
       * @param parent the folder parent
       * @param onlyFileName file name
       * @return the file instance
       */
      public static cloud.jgo.io.File fl_(cloud.jgo.io.File parent,String onlyFileName){
    	  return new cloud.jgo.io.File(parent, onlyFileName);
      }
      
      /**
       * Equivalent to System.setOut(PrintStream out);
       * @param out output source
       */
      public static void setOut(PrintStream out){
    	  System.setOut(out);
      }
      
      /**
       * Equivalent to System.out;
       * @return system output source
       */
      public static PrintStream out(){
    	  return System.out ;
      }
      
      /**
       * Equivalent to System.setIn(InputStream in);
       * @param in input source
       */
      public static void setIn(InputStream in){
    	  System.setIn(in);
      }
      
      
      /**
       * Equivalent to System.in;
       * @return system input source
       */
      public static InputStream in(){
    	  return System.in ;
      }
      
      /**
       * Equivalent to System.setErr(PrintStream err);
       * @param err error output source
       */
      public static void setErr(PrintStream err){
    	  System.setErr(err);
      }
      
      /**
       * Equivalent to System.err
       * @return system error output source
       */
      public static PrintStream err(){
    	  return System.err ;
      }
     
      
      /**
       * This method creates an file
       * @param dir the directory
       * @param childDirName the child directory
       * @param fileName the file name
       * @return the file
       */
      public static cloud.jgo.io.File fl(File dir,String childDirName,String fileName){
    	
    	  cloud.jgo.io.File file = new cloud.jgo.io.File(dir.getPath()+File.separator+childDirName+File.separator+fileName);
    	  
    	  return file ;
      }
      
      /**
       * This method works with the files
       * @param file the file you want to work on
       * @param fileMode the way you want to use
       * @param text this parameter is for the "writer" | "append" mode. Is the text you want to write
       * @param newFileName this parameter is for the "rename" mode. The new file name. 
       * @param pathFile this parameter is for the "create" mode. The file path
       * @return the home instance.
       */
      public static £ fl(cloud.jgo.io.File file,final String fileMode,String text,String newFileName,String pathFile){
    	  switch(fileMode){
    	  
    	  case FILE_MODE_DELETE:
    		  
    		  // provo a eliminare il file 
    		  
    		 boolean deleted =  file.delete();
    		 
    		 if(deleted == false){
    			 // imposto il file di eliminazione alla chiusura del programma
    			 file.deleteOnExit();
    		 }
    		  
    		  break ;
    	  case FILE_MODE_WRITE:
    		  
    		  // scrivo il file 
    		  try {
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
				if(text !=null){
					try {
						writer.write(text);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				// chiudo lo stream di output 
				try {
					writer.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		  
    		  break ;
    		  
    	  case FILE_MODE_READ:


    		  try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
				String leggi ;
				try {
					while((leggi = reader.readLine())!=null){
						System.out.println(leggi);
					}
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		  
    		  
    		  break ;
    		  
    	  case FILE_MODE_APPEND :
    		  
    		  try {
  				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,true)));
  				if(text !=null){
  					try {
  						writer.write(text);
  					} catch (IOException e) {
  						// TODO Auto-generated catch block
  						e.printStackTrace();
  					}
  				}
  				// chiudo lo stream di output 
  				try {
  					writer.flush();
  				} catch (IOException e) {
  					// TODO Auto-generated catch block
  					e.printStackTrace();
  				}
  				try {
  					writer.close();
  				} catch (IOException e) {
  					// TODO Auto-generated catch block
  					e.printStackTrace();
  				}
  			} catch (FileNotFoundException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			} 
    		  
    		  break ;
    	  case FILE_MODE_RENAME :
    		  
    		  // qui devo solo muovere il file 
    		  // questo modalità funziona solo se si è passato come parametro newFileName
    		  if(newFileName!=null){
    			  £.copyFile(file,new cloud.jgo.io.File(file.getParentFile(),newFileName));		
    		  }
    		  
    		  break ;
    	  case FILE_MODE_CREATE :
    		  
    		  if (pathFile!=null) {
				
    			  
    			  if (!pathFile.isEmpty()) {
				   cloud.jgo.io.File newFile = new cloud.jgo.io.File(pathFile);	
				   try {
					newFile.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}  
			}
    		  break ;
    	  }
    	  return instance ;
      }
      
      /**
       * this method creates an file instance relative to the desktop
       * @param onlyFileName only the file name
       * @return the file instance
       */
      public static cloud.jgo.io.File fl_Desktop(String onlyFileName){
    	  return new cloud.jgo.io.File(£.getHomeDirectory(), onlyFileName);
      }
      
      /**
       * this method creates an file instance relative to the local disk C:\\
       * @param onlyFileName only the file name
       * @return the file instance
       */
      public static cloud.jgo.io.File fl_LocalDisk(String onlyFileName){
    	  return new cloud.jgo.io.File(new File(£.LOCAL_DISK_PATH),onlyFileName);
      }
      
      /**
       * this method creates an file instance relative to the desktop
       * @param onlyFileName only the file name
       * @return the file instance
       */
      public static cloud.jgo.io.File fl_Documents(String onlyFileName){
    	  return new cloud.jgo.io.File(£.getDocumentsDirectory(), onlyFileName);
      }
      
      
      
      
      /*APPROFONDIMENTI :
       * https://docs.oracle.com/javase/7/docs/api/java/nio/channels/ReadableByteChannel.html#read(java.nio.ByteBuffer)
       * https://docs.oracle.com/javase/7/docs/api/java/nio/channels/FileChannel.html#read(java.nio.ByteBuffer)
       * @param from
       * @param destination
       * @param bufferCapacity
       * @return
       * @throws IOException
       */
      public static boolean copyFileChannelWithCheckState(File from,File destination,int bufferCapacity) throws IOException{
     	 boolean copied = false ;
      	FileInputStream in = new FileInputStream(from);
        	FileOutputStream out = new FileOutputStream(destination);
        	FileChannel inChannel = in.getChannel();
        	FileChannel outChannel = out.getChannel();
      	  
      	// qui ci sarà un byteBuffer
      	ByteBuffer buffer = ByteBuffer.allocate(bufferCapacity);  
      	// ovviamente il buffer deve avere come minimo di capacità almeno i byte del file che vogliamo copiare
      	
      	int leggi ;
      	
      	// qui dobbiamo dire finchè leggi nel buffer ,scrivi nel canale di uscita
      	while(true){
      		buffer.clear();
      		leggi = inChannel.read(buffer);
      	    if(leggi == -1){
      	    	copied = true ;
      	    	break ;
      	    }
      	    else if(leggi == 0){
      	    	// qui vuol dire che nel buffer non c'è più spazio
      	    	// ma questa parte è da escludere che possa essere eseguita se c'è il metodo
      	    	//clear all'inizio del ciclo,è giusto per ricordarmi di come funziona il tutto
      	    	copied = false ;
      	    	break ;
      	    }
      	   
      	   // imposto per la lettura
      		buffer.flip();
      		
      		// scrivo nel canale di uscita
      		
      		outChannel.write(buffer);
      		
      		/*
      		 * ===========================================================
      		 * 						Conclusioni
      		 * ===========================================================
      		 * 
      		 * Il buffer viene riempito e svuotato in ogni iterazione del ciclo
      		 * ma il canale di uscita viene solo riempito con ogni riempimento del buffer
      		 * solo questo è il concetto da capire.In tanto c'è da dire una cosa
      		 * quando noi scriviamo il buffer sul canale,c'è da ca dire che il canale prende 
      		 * comunque la capacity del buffer . Quando chiamiamo
      		 * il metodo FLIP il limite rappresenta la capacity a differenza di quando lo chiamiamo
      		 * dopo aver aggiunto direttamente noi byte nel buffer.Perchè quando noi chiamiamo 
      		 * flip() in un buffer dopo averci aggiunto a mano dei byte il limit diventa il numero di byte
      		 * scritti.Invece essendo che qui è il canale di input che riempie il buffer,flip ha un effetto
      		 * parzialmente diverso.Adesso ritorniamo alla famosa domanda ,perchè se non c'è CLEAR 
      		 * il metodo va all'infinito.
      		 * 
      		 * SE non chiamiamo clear ,il buffer non viene svuotato e quindi la posizione 
      		 * del buffer invece di diventare 0 resta quanto la capacity e cioè in questo caso 1024
      		 * quindi quando si passa alla prossima iterazione del ciclo e quindi alla prossima
      		 * lettura di bytes dal canale di input nel buffer,in realtà non viene scritto più niente nel buffer
      		 * perchè essendo che la pos e uguale alla capacità ,non vi entra più niente.
      		 * 
      		 * E quindi  ci siamo
      		 * 
      		 * ========================================================================================
      		 * FRASE COPIATA DALLA DOCUMENTAZIONE che mi ha risolto l'enigma anche se l'avevo intuito :
      		 * ========================================================================================
      		 * 
      		 * Si tenta di leggere fino a r byte dal canale, dove r è il numero di byte rimasti nel buffer, cioè dst.remaining () , nel momento in cui questo metodo viene richiamato.
      		 *  
      		 * CONCLUSIONE FINALE :
      		 * 
      		 * Quando si legge da un canale in un buffer,si tenta di leggere da esso il numero di byte rimasti nel buffer.
      		 * 
      		 * Quindi a noi da 0 perchè il buffer non avendo chiamato clear rimane come posizione sulla capacità
      		 * e infatti se noi chiamiamo il metodo remaining() ci da 0,perchè non c'è più alcun byte rimasto.
      		 * e tutto compacia.
      		 * 
      		 * quindi il metodo read del channel si preoccupa più di quanti byte ci vanno nel buffer che di quanto ve ne siano
      		 * nel canale stesso.
      		 * 
      		 * Fine :
      		 * 
      		 * Il metodo read del channel legge una sequenza di byte pari alla disponibilità dei byte del buffer.
      		 * ( - 1) se i byte del canale vengono letti tutti
      		 * (  0 ) se nel buffer non c'è più disponibilità ci da i suoi remaining() cioè 0
      		 *
      		 * RITORNARE POI SULL ARGOMENTO E PRENDERE APPUNTI
      		 * 
      		 * 
      		 
      		 
      		 */
  	
     		
      	}
      	
      	// chiudo in tanto i canali e i loro streams
      	
      	// prima quello che ha scritto sul canale di destinazione
      	
      	outChannel.close();
      	out.close();
      	
      	// quello di input
      	
      	inChannel.close();
      	in.close();
      	
       // chiudo appunto tutta la situazione 
      	return copied ;
      	
        }
      private static class Recorder {
 		 private int minute ;
 		    File wavFile = null ;
 		    private boolean open = false ;
 		    AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;
 		    TargetDataLine line;
 		    AudioFormat getFormat() {
 		        float sampleRate = 16000;
 		        int sampleSizeInBits = 8;
 		        int channels = 2;
 		        boolean signed = true;
 		        boolean bigEndian = true;
 		        AudioFormat format = new AudioFormat(sampleRate, sampleSizeInBits,channels, signed, bigEndian);
 		        return format;
 		    }
 		    public void startRegistration() {
 		        try {
 		            AudioFormat format = getFormat();
 		            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
 		            if (!AudioSystem.isLineSupported(info)){
 		                System.exit(0);
 		            }
 		            line = (TargetDataLine) AudioSystem.getLine(info);
 		            line.open(format);
 		            line.start();
 		            AudioInputStream ais = new AudioInputStream(line); 
 		            System.out.println("Registration in progress ...");
 		            AudioSystem.write(ais, fileType, wavFile);
 		        } catch (LineUnavailableException ex) {
 		            ex.printStackTrace();
 		        } catch (IOException ioe) {
 		            ioe.printStackTrace();
 		        }
 		    }
 		 
 		    public int getMinute() {
 				return minute;
 			}

 			public void setMinute(int minute) {
 				this.minute = minute;
 			}

 			public File getWavFile() {
 				return wavFile;
 			}

 			public void setWavFile(File wavFile) {
 				this.wavFile = wavFile;
 			}
 		    public void end() throws IOException {
 		        line.stop();
 		        line.close();
 		        System.out.println("Registration terminated #");
 		        if(open==true){
 		        	Desktop.getDesktop().open(wavFile);
 		        }
 		        
 		    }
 		    public Recorder(int minute,String onlyNameFile,boolean openFile) {
 				
 		    	this.minute = 60000 * minute;
 		        this.wavFile = new File(onlyNameFile+".wav");
 		        this.open = openFile;
 		    	
 		    }
 		}
      // con buffer di dimensione del file from
      /**
       * This method copies a channel file
       * @param from the file you want to copy
       * @param destination the destination file
       * @return the home instance
       */
      public static £ copyFileChannel(File from,File destination){
    	FileInputStream in = null ;
    	try {
			in = new FileInputStream(from);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	FileOutputStream out = null ;
    	try {
			out =new FileOutputStream(destination);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	FileChannel inChannel = in.getChannel();
    	FileChannel outChannel = out.getChannel();
    	
    	// o il canale di lettura e quello di scrittura
    	
    	// creo il buffer
    	
    	ByteBuffer buffer = null ;;
		try {
			buffer = ByteBuffer.allocate((int)inChannel.size());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 
    	 // leggo nel buffer dal canale di entrata 
		int value  ;
    	try {
			value =inChannel.read(buffer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	// quindi la pos del buffer dopo la lettura dal canale e uguale alla capacità
    	// in quanto abbiamo dato la dimensione del file al buffer
    	
    	// imposto il buffer per la scrittura 
    	
    	buffer.flip();
    	
    	// scrivo nel canale dal buffer 
    	
    	try {
			outChannel.write(buffer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	// chiudo il canale di uscita 
    	
    	try {
			outChannel.close();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	try {
			inChannel.close();
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return instance;
      }
      
      
      /**
       * This method copies a channel file
       * @param from the file you want to copy
       * @param destination the destination file
       * @param pos the position to copy from
       * @return the home instance
       */
      public static £ copyFileChannelFromPosition(File from,File destination,int pos){
      	FileInputStream in=null;
		try {
			in = new FileInputStream(from);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      	FileOutputStream out=null;
		try {
			out = new FileOutputStream(destination);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      	FileChannel inChannel = in.getChannel();
      	FileChannel outChannel = out.getChannel();
      	
      	// o il canale di lettura e quello di scrittura
      	
      	// creo il buffer
      	
      	ByteBuffer buffer=null;
		try {
			buffer = ByteBuffer.allocate((int)inChannel.size());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      	 
      	 // leggo nel buffer dal canale di entrata 
      
      	try {
			inChannel.read(buffer,pos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      	
      	buffer.flip();
      	
      	try {
			outChannel.write(buffer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      	
      	try {
			outChannel.close();
			inChannel.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      
      	try {
			in.close();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      	
      	return instance;
        }
        
      
      
      
      /**
       * This method copies a folder
       * @param srcFolder the folder you want to copy
       * @param destFolder the destination folder
       * @return the home instance
       */
      public static £ copyFolder(File srcFolder,File destFolder){
    	  FoldersCopier copier = new FoldersCopier(srcFolder);
    	  copier.setDestinationFolderMain(destFolder);
    	  copier.start(); 
    	  return instance;
      }
      

      /**
       * This method removes a folder
       * @param folder the folder to delete
       * @return the home instance
       */
      public static £ removeFolder(File folder) {
    	  File files[] = folder.listFiles();
    	  for (int i = 0; i < files.length ; i++) {
    		  if(files[i].isDirectory()){
    			  
    			  /*
    			   *is a Folder
    			   */
    			  removeFolder(files[i]);
    			  
    			  // dopo si elimina la cartella 
    			  
    			  File folderParent = files[i].getParentFile();
    			  files[i].delete();
    			  folderParent.delete();
    			 
//    			  System.out.println("Eliminata la cartella :"+files[i].getName());
    		  }
    		  else{
    			  
    			  /*
    			   * is a file
    			   */
    			  
    			  files[i].delete();
    			  
//    			  System.out.println("Eliminato il file :"+files[i].getName());	  
    		  }
		}
    	 return instance;
    	  
      }
      
      
     /**
      * This method copies a folder
      * @param folder the folder you want to copy
      * @param destinationFolder the destination folder
      */
      public static void copyFolder_2(File folder,File destinationFolder){
          // prima cosa devo creare la cartella
    	  File dir = new File(destinationFolder, folder.getName());
    	   // creo la cartella 
    	  dir.mkdir();
//    	  System.out.println("cartella "+dir.getName()+" creata @");
    	  // cartella creata a questo punto prendo i files della cartella di from
    	  
    	  File[]filesFolder = folder.listFiles();
    	  
    	  
    	  for (int i = 0; i < filesFolder.length; i++) {
//			System.out.println("Sta iterando il file "+filesFolder[i].getName());
    	  if(filesFolder[i].isDirectory()){
//    		  System.out.println("E' una cartella");
    		  // is a folder 
    		  copyFolder_2(filesFolder[i],dir);
    	  }
    	  else{
//    		  System.out.println("E' un file");
    		  copyFile(filesFolder[i],new File(dir,filesFolder[i].getName()));
    	  }
    	  }
      }
      
      
       /**
        * This method moves only a folder files
        * @param folder the folder
        * @param destinationFolder the destination folder
        * @return the home instance
        */
      public static £ moveOnlyFolderFiles(File folder,File destinationFolder) {
    	
    	  // prima cosa devo creare la cartella
    	  
    	  File dir = new File(destinationFolder, folder.getName());
    	  
    	   // creo la cartella 
    	  
    	  dir.mkdir();
//    	  System.out.println("cartella "+dir.getName()+" creata @");
    	  // cartella creata a questo punto prendo i files della cartella di from
    	  
    	  File[]filesFolder = folder.listFiles();
    	  
    	  
    	  for (int i = 0; i < filesFolder.length; i++) {
//			System.out.println("Sta iterando il file "+filesFolder[i].getName());
    	  if(filesFolder[i].isDirectory()){
//    		  System.out.println("E' una cartella");
    		  // is a folder 
    		  moveOnlyFolderFiles(filesFolder[i],dir);
    	  }
    	  else{
    		 // System.out.println("E' un file");
    		  copyFile(filesFolder[i],new File(dir,filesFolder[i].getName()));
    		  
    		  // dopo averlo copiato che rilasciamo la risorse
    		  // lo elimino
    		 boolean deleted =  filesFolder[i].delete();
    		 
    		 //System.out.println("File "+filesFolder[i].getName()+" deleted :"+deleted);
    	  }
    	  
    	  }
		return instance;
    	  
      }  
      
      /**
       * This method moves a folder
       * @param folder the folder you want to move
       * @param destinationFolder the destination folder
       * @return the home instance
       */
      public static £ moveFolder(File folder,File destinationFolder) {
	  // prima cosa devo creare la cartella
	  
	  File dir = new File(destinationFolder, folder.getName());
	  
	   // creo la cartella 
	  
	  dir.mkdir();
	  //System.out.println("cartella "+dir.getName()+" creata @");
	  // cartella creata a questo punto prendo i files della cartella di from
	  
	  File[]filesFolder = folder.listFiles();
	  
	  
	  for (int i = 0; i < filesFolder.length; i++) {
		//System.out.println("Sta iterando il file "+filesFolder[i].getName());
	  if(filesFolder[i].isDirectory()){
		  //System.out.println("E' una cartella");
		  // is a folder 
		  moveFolder(filesFolder[i],dir);
		  
	  }
	  else{
		  //System.out.println("E' un file");
		  copyFile(filesFolder[i],new File(dir,filesFolder[i].getName()));
		  
		  // dopo averlo copiato che rilasciamo la risorse
		  // lo elimino
		 boolean deleted =  filesFolder[i].delete();
		 
		// System.out.println("File "+filesFolder[i].getName()+" deleted :"+deleted);
	  }
	  
	  }
	  //System.err.println("Sono fuori dal ciclo #");
	  // ora pensiamo all'eliminazione delle cartelle 
	  //System.out.println("Eliminazione cartelle in corso ...");
	 
	  try {
		removeFolder(folder);
	} catch (HeadlessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	  return instance;
	
      }
      
      
      /**
       * 
       * @param from file da spostare
       * @param destination file di destinazione
       * @return true se il file viene correttamente spostato
       */
      public static boolean moveFile(File from,File destination){
    	  
    	 if (from.exists()) {
    		 FileInputStream fis = null ;
    		 try {
				fis = new FileInputStream(from);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       	  byte[]buffer = null ;
       	  try {
			buffer = new byte[fis.available()];
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       	  try {
			fis.read(buffer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       	  try {
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       	  FileOutputStream fos = null ;
       	  try {
			fos = new FileOutputStream(destination);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       	  try {
			fos.write(buffer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       	  
       	  try {
			fos.flush();
			 fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       	 boolean result=from.delete();
       	  if (result!=true) {
       		 // qui devo comunicare che l'operazione verrà completata alla chiusura del programma
       		  // perchè il file è impegnato in qualche altra operazione
   			from.deleteOnExit();
   		}
       	  return true ;
		}
    	 else{
    		 return false ;
    	 }
      }
      
      /**
       * Questo metodo legge il file che gli passiamo e ne restituisce il testo
       * @param file file da leggere
       * @throws IOException 1 eccezzione
       * @return il testo del file
       */
      public static String readFile(File file) throws IOException{
    	  BufferedReader
    	  reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
    	  String leggi ;
    	  StringBuffer buffer = new StringBuffer();
    	  while ((leggi = reader.readLine())!=null) {
			buffer.append(leggi+"\n");
		}
    	  reader.close();
    	  return buffer.toString();
      }
      
      
      /**
       * This method opens the system terminal
       * @return the home instance
       */
      public static £ openTerminal(){
    	  String osName = OS_type();
    	  if (osName.startsWith("Windows")) {
			// is windows
    		  Runtime runtime = Runtime.getRuntime();
        	  Process p=null;
    		try {
    			p = runtime.exec("C:\\Windows\\System32\\cmd.exe");
    		} catch (IOException e) {
    			/*
    			 
    			JGO Auto-generated catch block
    			Author : £ wasp91 £
    			Date 23 dic 2017
    			
    			*/
    			e.printStackTrace();
    		}
        	  String command = "start\r\n";
        	  OutputStream out = p.getOutputStream();
        	  try {
    			out.write(command.getBytes());
    		} catch (IOException e) {
    			/*
    			 
    			JGO Auto-generated catch block
    			Author : £ wasp91 £
    			Date 23 dic 2017
    			
    			*/
    			e.printStackTrace();
    		}
        	  try {
    			out.flush();
    		} catch (IOException e) {
    			/*
    			 
    			JGO Auto-generated catch block
    			Author : £ wasp91 £
    			Date 23 dic 2017
    			
    			*/
    			e.printStackTrace();
    		}
        	  try {
    			out.close();
    		} catch (IOException e) {
    			/*
    			 
    			JGO Auto-generated catch block
    			Author : £ wasp91 £
    			Date 23 dic 2017
    			
    			*/
    			e.printStackTrace();
    		}
        	  try {
    			p.waitFor();
    		} catch (InterruptedException e) {
    			/*
    			 
    			JGO Auto-generated catch block
    			Author : £ wasp91 £
    			Date 23 dic 2017
    			
    			*/
    			e.printStackTrace();
    		}
    	  }
    	  else if(osName.startsWith("Linux")){
    		  // is linux
    		  	 String cmd_1 = "cd /";
    	    	 String cmd_2 = "cd ..";
    	    	 String cmd_3 = "cd bin";
    	    	 String cmd_last = "xterm";
    	    	 try {
    	    		 £.SHELL.commands(cmd_1,cmd_2,cmd_3,cmd_last);
    			} catch (InterruptedException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    	  }
    	  else{
    		  // mac
    	  }
    	  return instance;
      }
      
      /**
       * This method opens the system terminal
       * @param times the times you want to open
       * @return the home instance
       */
      public static £ openTerminal(int times){
      for (int i = 0; i < times; i++) {
    	  openTerminal();
      } 
      return instance ;
      }
      
      /**
       * This method opens the system notepad
       * @return the home instance
       */
      public static £ openNotepad(){
    	  if (OS_type().startsWith("Windows")) {
			// windows
    		  ProcessBuilder builder = new ProcessBuilder("notepad.exe");
    	    	 try {
    				builder.start();
    			} catch (IOException e) {
    				/*
    				 
    				JGO Auto-generated catch block
    				Author : £ wasp91 £
    				Date 23 dic 2017
    				
    				*/
    				e.printStackTrace();
    			}
    	  }
    	  else if(OS_type().startsWith("Linux")){
    		  // linux : open gedit
    		  try {
				£.SHELL.command("gedit");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	  }
    	  else{
    		  // mac
    	  }
    	 return instance;
      }
      
      /**
       * This method opens the system notepad
       * @param times the times you want to open
       * @return the home instance
       */
      public static £ openNotepad(int times){
    	  for (int i = 0; i < times; i++) {
    		  openNotepad();
    	  }  
    	  return instance;
      }
      public static class FoldersCopier extends Thread{
    		private int leggi ;
    		@Override
    		public void run() {
    				try {
    					copyFolder(fromFolder.listFiles(),destinationFolder);
    				} catch (IOException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    		}
    		private File destinationFolderMain; // quindi questa è cartella che contiene la cartella copiata
    		private File destinationFolder ; // questa è la cartella copiata
    		private File fromFolder ; // questa è la cartella da copiare
    		public FoldersCopier(File from) {
    			this.fromFolder = from ;
    		}
    		public File getDestinationFolderMain() {
    			return destinationFolderMain;
    		}
    		public void setDestinationFolderMain(File destinationFolderMain) {
    			this.destinationFolderMain = destinationFolderMain ;
    			this.destinationFolder = new File(this.destinationFolderMain.getPath()+"\\"+this.fromFolder.getName());
    		    this.destinationFolder.mkdir();
    		}

    		public void copyFolder(File[]files,File dest) throws IOException{
    			
    			
    			for (int i = 0; i < files.length; i++) {
    				
    				
    				if(files[i].isDirectory()){
    				
    					// okok qui devo creare la
    					
    					File folder = new File(dest.getPath()+"\\"+files[i].getName());
    					// creo la cartella nella cartella di destinazione
    					destinationFolder = folder ;
    					destinationFolder.mkdir();
    					// gli passo folder anzichè la cartella di destinazione
    				   copyFolder(files[i].listFiles(),destinationFolder);
    				}
    				else{
    					FileOutputStream fos = new FileOutputStream(dest.getPath()+"\\"+files[i].getName());
    					FileInputStream fis = new FileInputStream(files[i]);
    					BufferedInputStream in = new BufferedInputStream(fis);
    					byte[]buffer = new byte[1024]; // okok finalmente ho risolto,la ricorsività era giusta era solo che sbagliavo a dare la dim al buffer
    					// dovevo dargli 1024 come size del buffer e non i byte disponibili di ogni file,infatti quando ho capito che il programma non si chiudeva
    					// ma rimaneva in runtime mi è venute il dubbio che forse l'errore consisteva in qualcosa di diverso della strutturazione 
    					// della ricorsività del metodo
    					int leggi ;
    					while ((leggi = in.read(buffer, 0, buffer.length))>-1) {
    						fos.write(buffer, 0, leggi);
    					}
    					fos.flush();
    					fos.close();
    					in.close();
    				}
    			}
    		}
    	}
      
      public static £ test(){
    	  System.out.println(instance.getClass().getSimpleName());
    	  return instance ;
      }
}
