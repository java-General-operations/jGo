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
package cloud.jgo.jjdom.jquery;
/**
 * 
 * @author Martire91<br>
 *	This interface represents the jquery event
 */
public interface Event {
	public abstract void preventdefault();
	public abstract void stopPropagation ();
	public abstract void stopImmediatePropagation ();
	public abstract EventType getEventType();
	/**
	 * 
	 * @author Martire91<br>
	 * This interface represents the jquery event type
	 */
	public static enum EventType{
		CLICK,
		DBL_CLICK,
		SUBMIT,
		MOUSE_ENTER,
		MOUSE_LEAVE,
		MOUSE_OVER,
		MOUSE_OUT
	}
}
