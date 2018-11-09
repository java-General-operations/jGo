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
package cloud.jgo.utils.command.terminal;
/**
 * This is a support class
 */
import java.io.InputStream;
import java.io.OutputStream;
public class SyncPipe implements Runnable{
	private  InputStream in = null ;
	private OutputStream out = null;
public SyncPipe(InputStream inputStream, OutputStream outputstream) {
      in = inputStream;
      out = outputstream;
  }
  public void run() {
      try
      {
          byte[] buffer = new byte[1024];
          for (int length = 0; (length = in.read(buffer)) != -1; )
          {
              out.write(buffer, 0, length);
          }
      }
      catch (Exception e)
      {
          e.printStackTrace();
      }
  }
}