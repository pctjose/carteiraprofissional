package org.apm.carteiraprofissional.utils;

import java.io.File;

import org.dom4j.rule.Mode;

public class BarcodeUtil {
	
	public static void encodePDF417(String content) {

        //File PDF417File = new File(content + ".png");
        
        File PDF417File = new File("C:\\barcode\\PDF_4171.png");
        
       /*EncodeConfig encodeConfig =
              new EncodeConfig.Builder().createDirectories(Boolean.TRUE).
              withCharactersMode(Mode.ALPHANUMERIC).build();
        
       BarcodeEngine.encode(PDF417File, content, BarcodeFormat.PDF_417, 10, 2, encodeConfig);
        */
        

    }

}
