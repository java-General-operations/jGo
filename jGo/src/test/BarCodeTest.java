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
	
	// scrivi
	j£.writeBarcode("9191919", "png", BarcodeFormat.QR_CODE,"ean_8.png",300,100);
	// leggi
	String barCodeText = j£.readBarcode("ean_8.png");
	
	System.out.println(barCodeText);
	
}
}
