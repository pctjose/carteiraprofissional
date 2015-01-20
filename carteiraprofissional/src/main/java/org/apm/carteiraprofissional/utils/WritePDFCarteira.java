package org.apm.carteiraprofissional.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.apm.carteiraprofissional.Carteira;
import org.apm.carteiraprofissional.Requisitante;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.AcroFields;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;

public class WritePDFCarteira {

	private static Logger log = Logger.getLogger(WritePDFCarteira.class);
	public static byte[] produzirPDFCarteira(Carteira carteira,
			Requisitante requisitante) throws IOException, DocumentException {

		//String dir = PathUtils.getWebInfPath() + "/data/"
		//		+ carteira.getRequisicao().getNumeroRequisicao();
		
		
		String dir = PathUtils.getEnvDataDir() + "/datareal/"
				+ carteira.getRequisicao().getNumeroRequisicao();
		

		//PdfReader reader = new PdfReader(PathUtils.getWebInfPath()
		//		+ "/pdftemp/CARTAODATA.pdf");
		File pdfTamplate = new File(PathUtils.getEnvDataDir()
				+ "/pdftemp/CARTAODATA.pdf");
								
			InputStream in = new FileInputStream(pdfTamplate);
			
			PdfReader reader = new PdfReader(in);
			
			PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dir
					+ "/" + carteira.getNumeroCarteira() + ".pdf"));
			fillForm(stamper.getAcroFields(), carteira, requisitante);
			stamper.close();

			
			File pdf = new File(dir + "/" + carteira.getNumeroCarteira() + ".pdf");
			FileInputStream pdfTemplate = new FileInputStream(pdf);

			byte[] conteudo = new byte[(int) pdf.length()];

			pdfTemplate.read(conteudo);

			pdfTemplate.close();

			return conteudo;	
		
	}

	private static void fillForm(AcroFields form, Carteira carteira,
			Requisitante requisitante) throws IOException, DocumentException {
		form.setField("txtNumeroCarteira", carteira.getNumeroCarteira());
		form.setField("txtApelido", carteira.getRequisicao().getRequisitante()
				.getApelido());
		form.setField("txtNome", carteira.getRequisicao().getRequisitante()
				.getNome());

		form.setField(
				"txtDataNascimento",
				DateUtil.ptDate(carteira.getRequisicao().getRequisitante()
						.getDataNascimento()));

		form.setField("txtSexo", carteira.getRequisicao().getRequisitante()
				.getSexo());

		if (requisitante.getCategoria() != null)
			form.setField("txtXCategoria", requisitante.getCategoria()
					.getDesignacao());

		form.setField("txtDataEmissao",
				DateUtil.ptDate(carteira.getDataEmissao()));
		form.setField("txtDataValidade",
				DateUtil.ptDate(carteira.getDataValidade()));

		form.setField("txtDoc", carteira.getRequisicao().getRequisitante()
				.getNumeroDoc());

		form.setField("txtTipoDoc", carteira.getRequisicao().getRequisitante()
				.getTipoDoc().getDesignacao());

	}

}
