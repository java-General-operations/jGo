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
	
	
	j£.openWebcam();
	
	j£.capture("test.png");
	
	j£.capture("test2.png");
	
	j£.closeWebcam();
	
	// riapro la webcam
	
	j£.openWebcam();
	
	j£.getJFrameWebcam("Ciao", null, true);
}
}
