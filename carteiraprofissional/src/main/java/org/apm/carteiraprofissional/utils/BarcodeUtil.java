package org.apm.carteiraprofissional.utils;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

public class BarcodeUtil {

	public static BufferedImage encodePDF417(String content) throws Exception {

		BitMatrix bitmatrix;

		MultiFormatWriter writer = new MultiFormatWriter();

		bitmatrix = writer.encode(content, BarcodeFormat.PDF_417, 80, 40);

		BufferedImage imagem = MatrixToImageWriter.toBufferedImage(bitmatrix);

		return imagem;

	}
	
	
	public static BufferedImage encodeCode128(String content) throws Exception {

		BitMatrix bitmatrix;

		MultiFormatWriter writer = new MultiFormatWriter();

		bitmatrix = writer.encode(content, BarcodeFormat.CODE_128, 80, 40);

		BufferedImage imagem = MatrixToImageWriter.toBufferedImage(bitmatrix);

		return imagem;

	}
	
	

	public static String decodeBarcode(BufferedImage imagem) throws Exception {
		if (imagem == null)
			throw new IllegalArgumentException(
					"Não foi possivel descodificar a imagem do PDF417");
		LuminanceSource source = new BufferedImageLuminanceSource(imagem);
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
		MultiFormatReader reader = new MultiFormatReader();
		Result resultado = reader.decode(bitmap);
		return String.valueOf(resultado.getText());

	}

	public static String decodeBarcode(String sourceImage) throws Exception {
		BufferedImage buff = ImageIO.read(new File(sourceImage));

		LuminanceSource source = new BufferedImageLuminanceSource(buff);
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
		MultiFormatReader reader = new MultiFormatReader();
		Result resultado = reader.decode(bitmap);
		return String.valueOf(resultado.getText());
	}

}
