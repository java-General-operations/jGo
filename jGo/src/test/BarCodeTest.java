package test;

import java.io.File;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;

import cloud.jgo.j£;
import cloud.jgo.£;

/**
 * @author Martire91 - https://github.com/wasp91
 * @version 1.0.0
 */
public class BarCodeTest {
@SuppressWarnings("static-access")
public static void main(String[] args) {
	
	
	j£.writeBarcode("ciao", "gif", BarcodeFormat.QR_CODE,"ciao.gif",400,300);
	System.out.println("Bene");
	
}
}
