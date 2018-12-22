package test;

import org.fusesource.jansi.AnsiConsole;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;

import java.util.Scanner;

import org.fusesource.jansi.Ansi.Color;

import cloud.jgo.j£;

public class GeneralTest {
@SuppressWarnings("static-access")
public static void main(String[] args) {
	
	
	
	
	j£.openWebcam().capture("Ciao.png").capture("test.png").capture("test2.png").closeWebcam();
	
	
	System.out.println("Istruzione jGo eseguita @");
	
}
}
