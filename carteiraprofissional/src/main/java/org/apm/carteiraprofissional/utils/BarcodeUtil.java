package org.apm.carteiraprofissional.utils;

import java.io.File;
import java.io.FileOutputStream;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;


public class BarcodeUtil {
	
	public static void encodePDF417(String content) throws Exception {

        //File PDF417File = new File(content + ".png");
		
		BitMatrix bitmatrix;
		
		//Writer writer=new PDF417Writer();
		
		MultiFormatWriter writer = new MultiFormatWriter();
		
		bitmatrix = writer.encode(content, BarcodeFormat.PDF_417, 80, 150);
		
		
        
        File PDF417File = new File("C:\\barcode\\PDF_4171.png");
        
        
        MatrixToImageWriter.writeToStream(bitmatrix, "png", new FileOutputStream(PDF417File));
        
        
       
        
        

    }

}
