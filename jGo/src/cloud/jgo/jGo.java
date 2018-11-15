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
/**
 * @author Martire91
 * @see £#_A
 * @see £#_S
 * @see £#_W
 * @version 1.0.5
   <!--<link rel='styleSheet' href='https://www.jgo.cloud/docStyle.css'> -->
    <!--Author : *** Marco Martire *** -->  
   <h1 style='color: #282828;'>jGo<span style='color: green;'>.cloud</span></h1>
   <img id='logo'src='https://www.jgo.cloud/imgs/logo.png' alt='logo jgo' width='50px' height='50px'><br>
   <strong>Description :</strong><br> This class facilitates all the difficult tasks
   For example,In a code line, you take a screenshot.<br>
   JGO is very powerful and it follows the jquery policy :<br>
   <em><u>(Write a little and get a lot)</u></em><br>
   <strong>J</strong> - <em>Java</em><br>
   <strong>G</strong> - <em>General</em><br>
   <strong>O</strong> - <em>Operations</em><br><br>
   <strong>1 Example</strong>  -  <em class='explanations'>Prints "Welcome in JGO", executes a screenshot with a final function and finally emits an acoustic signal :</em> : <br>
    public static void main(<em class='type'>String</em>[]args){<br>
    <em class='type'>£</em>._O("Welcome in JGO").<em class='method'>screenshot</em>(new <em class='type'>£Func</em>() {<br>
    <strong style='color:darkgray'>@Override</strong><br>
    public Object <em style='#303030'>function</em>(<em class='type'>Object</em> e) {<br>
    <em class='type'>£</em>.<em class='method'>alert</em>("Hello World");<br>
    return null ;<br>
    }}).<em class='method'>beep</em>().<em class='method'>beep</em>();<br>
    &nbsp;}
    <br><br>
    <strong>2 Example</strong>  -  <em class='explanations'>Emits an acoustic signal and opens the windows cmd for 3 times and opens windows notepad :</em><br>
    <em class='type'>£</em>.<em class='method'>beep</em>().<em class='method'>openCmd</em>(3).<em class='method'>openNotepad</em>();
    <br><br>
    <strong>3 Example</strong>  -  <em class='explanations'>Slide panel :</em><br>
    <em class='type'>£</em>._<span style='color: #006200;'>S</span>.<em class='method'>slide</em>(panel,Effect.<span style='color: #006200;'>VERTICAL</span>,Effect.<span style='color: #006200;'>FAST</span>);
    <br><br>
    <strong>4 Example</strong>  -  <em class='explanations'>Moves two times the frame :</em><br>
    <em class='type'>£</em>._<em style='color:#282828;'>S</em>.<em class='method'>moveJFrame</em>(frm, 0, 0,<span style='color: #006200;'>Effect</span>.SLOW).<em class='method'>moveJFrame</em>(frm, 300, 300, Effect.<span style='color: #006200;'>FAST</span>);
 */
public final class jGo extends £{
	/**
	 * The jGo access point.
	 * It is equivalent to {@link £}
	 */
}
