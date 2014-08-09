package org.apm.carteiraprofissional.view.requisicao;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import org.apache.log4j.Logger;
import org.apm.carteiraprofissional.Requisicao;
import org.apm.carteiraprofissional.Requisitante;
import org.apm.carteiraprofissional.service.RequisicaoService;
import org.apm.carteiraprofissional.service.RequisitanteService;
import org.imgscalr.Scalr;
import org.zkoss.bind.BindContext;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.image.AImage;
import org.zkoss.image.Image;
import org.zkoss.image.Images;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Messagebox;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;

public class CompletarRequisiaoVM extends SelectorComposer<Component> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Requisicao requisicao;

	private static Logger log = Logger.getLogger(CompletarRequisiaoVM.class);

	@Wire
	private org.zkoss.image.Image userImage;
	
	@WireVariable
	protected RequisicaoService requisicaoService;
	
	@WireVariable
	protected RequisitanteService requisitanteService;

	public org.zkoss.image.Image getUserImage() {
		return userImage;
	}

	public void setUserImage(org.zkoss.image.Image userImage) {
		this.userImage = userImage;
	}

	@Command
	@NotifyChange("userImage")
	public void getWebCam() throws IOException, InterruptedException {
		Webcam webcam = Webcam.getDefault();

		WebcamPanel panel = new WebcamPanel(webcam);

		panel.setFillArea(true);

		JFrame window = new JFrame("Test webcam panel");
		window.add(panel);
		window.setResizable(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setVisible(true);

		BufferedImage image = webcam.getImage();

		// ImageIO.write(image, "PNG", new File("/resources/teste.png"));

		userImage = Images.encode("Foto.png", image);
		// userImage=new AImage("",image);

		Thread.sleep(300);
		webcam.close();
		window.dispose();

	}

	@Command("upload")
	@NotifyChange("userImage")
	public void onImageUpload(
			@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) {
		UploadEvent upEvent = null;
		Object objUploadEvent = ctx.getTriggerEvent();
		if (objUploadEvent != null && (objUploadEvent instanceof UploadEvent)) {
			upEvent = (UploadEvent) objUploadEvent;
		}
		if (upEvent != null) {
			Media media = upEvent.getMedia();
			int lengthofImage = media.getByteData().length;
			if (media instanceof Image) {
				if (lengthofImage > 500 * 1024) {
					showInfo("Please Select a Image of size less than 500Kb.");
					return;
				} else {
					userImage = (AImage) media;// Initialize the bind object to
												// show image in zul page and
												// Notify it also

				}
			} else {
				showInfo("The selected File is not an image.");
			}
		} else {
			log.debug("Upload Event Is not Coming");
		}

	}

	protected void showInfo(String message) {
		Messagebox.show(message, "Alerta", Messagebox.OK,
				Messagebox.INFORMATION);
	}

	@Command
	@NotifyChange("userImage")
	public void salvar() throws IOException {
		if (userImage == null) {
			showInfo("Seleccione uma imagem ou tire a foto através do WebCam");
		} else {
			BufferedImage foto = ImageIO.read(userImage.getStreamData());
			
			requisicao = (Requisicao)Sessions.getCurrent().getAttribute("requisicao");

			foto = Scalr.resize(foto, Scalr.Method.SPEED, Scalr.Mode.FIT_TO_WIDTH,100, 100, Scalr.OP_ANTIALIAS);
			
			Requisitante requisitante = requisicao.getRequisitante();
			
			userImage = Images.encode("foto.png", foto);
			
			requisitante.setFoto(userImage.getByteData());
			requisitante.setUuid(UUID.randomUUID().toString());
			requisitanteService.saveRequisitante(requisitante);
			
			requisicao = requisicaoService.getRequisicaoByID(requisicao.getRequisicaoId());
			
			requisicao.setCompleta(true);
			requisicao.setAceite(true);
			requisicao.setJustificacaoAceitacao("É porque foi aceite");
			requisicao.setDataAceite(new Date());
			requisicaoService.saveRequisicao(requisicao);
	

			Clients.showNotification("Foto do requisitante actualizada com sucesso");

			ImageIO.write(foto, "png", new File("c://dev//teste1.png"));
			System.out.println(foto.getHeight()+" Completa: "+requisicao.getCompleta());
		}

	}

}
