[![Maven Central](https://img.shields.io/maven-central/v/cloud.jgo/jGo.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22cloud.jgo%22%20AND%20a:%22jGo%22)
[![Javadocs](https://www.javadoc.io/badge/cloud.jgo/jGo.svg?)](https://www.javadoc.io/doc/cloud.jgo/jGo)
<html>
   <h1><img id='logo'src='https://www.jgo.cloud/jgo2/' alt='logo jgo'></h1>
   <h2>java General operations</h2>
   jGo-Website:<a href='https://www.jgo.cloud/'>https://www.jgo.cloud/</a><br>
   <strong>Description :</strong><br> This class facilitates all the difficult tasks
   For example,In a code line, you take a screenshot.<br>
   JGO is very powerful and it follows the jquery policy :<br>
   <em><u>(Write a little and get a lot)</u></em><br>
   <strong>J</strong> - <em>Java</em><br>
   <strong>G</strong> - <em>General</em><br>
   <strong>O</strong> - <em>Operations</em><br>
   To learn more, check the <a href='https://www.jgo.cloud/'>website</a> and its <a href='https://www.jgo.cloud/docs/'>documentation</a>.
   <img src="https://www.jgo.cloud/imgs/code.png" width="500px" height="300px">
   <hr></hr>
   <strong>1 Example</strong>  -  <em class='explanations'>Prints "Welcome in JGO", executes a screenshot with a final function and finally emits an acoustic signal </em> : <br>
   <pre>
   <code> 
    public static void main(<em class='type'>String</em>[]args){<br>
    <em class='type'>£</em>._O("Welcome in JGO").<em class='method'>screenshot</em>(new <em class='type'>£Func</em>() {<br>
    <strong style='color:darkgray'>@Override</strong><br>
    public Object <em style='#303030'>function</em>(<em class='type'>Object</em> e) {<br>
    <em class='type'>£</em>.<em class='method'>alert</em>("Hello World");<br>
    return null ;<br>
    }}).<em class='method'>beep</em>().<em class='method'>beep</em>();<br>
    &nbsp;}
    </code>
    </pre>
    <br><br>
    <strong>2 Example</strong>  -  <em class='explanations'>Emits an acoustic signal and opens the windows cmd for 3 times and opens windows notepad :</em><br>
    <pre>
    <code>
    <em class='type'>£</em>.<em class='method'>beep</em>().<em class='method'>openCmd</em>(3).<em class='method'>openNotepad</em>();
   </code> 
    </pre>
    <br><br>
    <strong>3 Example</strong>  -  <em class='explanations'>Slide panel :</em><br>
   <pre>
   <code> 
    <em class='type'>£</em>._<font color='#006200'>S</font>.<em class='method'>slide</em>(panel,Effect.<font color='#006200'>VERTICAL</font>,Effect.<font color='#006200'>FAST</font>);
    </code>
    </pre>
    <br><br>
    <strong>4 Example</strong>  -  <em class='explanations'>Moves two times the frame :</em><br>
    <pre>
    <code>
    <em class='type'>£</em>._<em style='color:#282828'>S</em>.<em class='method'>moveJFrame</em>(frm, 0, 0,<font color='#006200'>Effect</font>.SLOW).<em class='method'>moveJFrame</em>(frm, 300, 300, Effect.<font color='#006200'>FAST</font>);
    </code>
    </pre>
<br>
Also thanks to jGo you can use jquery in the java code :
<h2>jGo/<a href='https://www.jgo.cloud/jjdom/'>JjDom</a> - Java jQuery Dom</h2>
<pre>
<code>
$("#container p").hide("slow").show("slow");
</code>
</pre>
<pre>
<code>
$("p,a,strong").slideToggle(3000);
</code>
</pre>
<pre>
<code>
$("#btn").on("click",new jQueryfunction(){<br><br>
jquery("p").toggle("slow");<br><br>
});
</code>
</pre>
<hr></hr>
<h2>Technologies</h2>
<ul>
<li><a href="https://www.jgo.cloud/jon/">JON</a> - Java Object Notation (<a href='#'><code>cloud.jgo.io.jon</code></a>)</li>
<li><a href="https://www.jgo.cloud/jor/">JOR</a> - Java Object Representation (<a href='#'><code>cloud.jgo.net.tcp.http.jor</code></a>)</li>
<li><a href="https://www.jgo.cloud/jjdom/">JjDom</a> - Java jQuery Dom (<a href='#'><code>cloud.jgo.jjdom</code></a>)</li>
</ul>
<hr></hr>
<h2>Phases</h2>
<ul>
<li><a href='https://www.jgo.cloud/_a'>£._A</a> - AndroidUtils <em>(<a href='#'><code>cloud.jgo.£.AndroidUtils</code></a>)</em></li>
<li><a href='https://www.jgo.cloud/_s'>£._S</a> - SwingUtils<em>(<a href='#'><code>cloud.jgo.£.SwingUtils</code></a>)</em></li>
<li><a href='https://www.jgo.cloud/_w'>£._W</a> - WebUtils<em>(<a href='#'><code>cloud.jgo.£.WebUtils</code></a>)</em></li>
</ul>
<hr></hr>
<h2>Dependencies and Artifacts</h2>
To add jGo to your project, include the following dependency in your pom.xml :<br><br>
<pre>
<code>
&lt;dependency&gt;
    &lt;groupId&gt;cloud.jgo&lt;/groupId&gt;
    &lt;artifactId&gt;jGo&lt;/artifactId&gt;
    &lt;version&gt;1.0.6&lt;/version&gt;
&lt;/dependency&gt;
</code>
</pre>
<hr></hr>
<h2>Copyright and License</h2>
Copyright © 2018 - Marco Martire (www.jgo.cloud)
This program is free software: you can redistribute it and/or modify
it under the terms of the MIT License.
You may obtain a copy of License at :
https://www.jgo.cloud/LICENSE.txt
To collaborate on this project, you need to do it from the software site.
<hr></hr>
<h2>Open source</h2>
Open source
jGo è un progetto open source distribuito sotto la licenza liberale del MIT . Il codice sorgente è disponibile su GitHub .
<hr></hr>
<h2>Start using</h2>
<ol>
<li><a href='https://www.jgo.cloud/releases/'>download </a>the latest jGo version</li>
<li>Read the <a href='https://www.jgo.cloud/cookbook/'>cookbook</a></li>
<li><strong>Have fun with jGo !</strong></li>
</ol>
<hr></hr>
<h2>Help</h2>
For questions and info contact:
<ul>
<li>Staff :<a href='#'>staff@jgo.cloud</a></li>
<li>Author :<a href='#'>author@jgo.cloud</a></li>
</ul>
<hr></hr>
Original source from
https://www.jgo.cloud/ of<br>
Marco Martire
</html>