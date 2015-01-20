package org.apm.carteiraprofissional.view.requisicao;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import org.apache.log4j.Logger;
import org.apm.carteiraprofissional.Requisicao;
import org.apm.carteiraprofissional.Requisitante;
import org.apm.carteiraprofissional.service.RequisicaoService;
import org.apm.carteiraprofissional.service.RequisitanteService;
import org.apm.carteiraprofissional.utils.PathUtils;
import org.apm.carteiraprofissional.utils.UtilizadorUtils;
import org.imgscalr.Scalr;
import org.zkoss.bind.BindContext;
import org.zkoss.bind.annotation.AfterCompose;
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
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;

public class CompletarRequisiaoVM extends SelectorComposer<Component> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Requisicao requisicao;
	
	//private Utilizador logedInUser;

	private static Logger log = Logger.getLogger(CompletarRequisiaoVM.class);

	@Wire
	private org.zkoss.image.Image userImage;

	@WireVariable
	protected RequisicaoService requisicaoService;

	@WireVariable
	protected RequisitanteService requisitanteService;

	@Wire
	private Window frmCompletarRequisicao;

	public org.zkoss.image.Image getUserImage() {
		return userImage;
	}

	public void setUserImage(org.zkoss.image.Image userImage) {
		this.userImage = userImage;
	}

	public Window getFrmCompletarRequisicao() {
		return frmCompletarRequisicao;
	}

	public void setFrmCompletarRequisicao(Window frmCompletarRequisicao) {
		this.frmCompletarRequisicao = frmCompletarRequisicao;
	}

	@AfterCompose
	@NotifyChange("userImage")
	public void initSetup(@ContextParam(ContextType.VIEW) Component view)
			throws IOException {
		Selectors.wireComponents(view, this, false);

		requisicao = (Requisicao) Sessions.getCurrent().getAttribute(
				"requisicao");
		
		//logedInUser= (Utilizador)Sessions.getCurrent().getAttribute("utilizador");

		if (requisicao != null) {
			if (requisicao.isCompleta()) {
				InputStream in = new ByteArrayInputStream(requisicao
						.getRequisitante().getFoto());
				BufferedImage oldImage = ImageIO.read(in);
				userImage = Images.encode("foto.png", oldImage);
			}
		}
	}

	@Command
	@NotifyChange("userImage")
	public void getWebCam() throws IOException, InterruptedException {
		Webcam webcam = Webcam.getDefault();
		WebcamPanel panel = new WebcamPanel(webcam);
		panel.setFillArea(true);
		JFrame window = new JFrame("FOTO");
		window.add(panel);
		window.setResizable(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setVisible(true);
		Thread.sleep(15000);
		BufferedImage image = webcam.getImage();
		userImage = Images.encode("Foto.png", image);
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
			log.info("Evento do Upload de Imagem: " + upEvent.getName());
		}
		if (upEvent != null) {
			Media media = upEvent.getMedia();
			int lengthofImage = media.getByteData().length;
			if (media instanceof Image) {
				if (lengthofImage > 500 * 1024) {
					Clients.showNotification("Seleccione uma imagem menor de 500kb");
					return;
				} else {
					userImage = (AImage) media;
				}
			} else {
				Clients.showNotification("O ficheiro seleccionado não é uma imagem válida");
			}
		} else {
			Clients.showNotification("Dificuldades em accionar o evento de upload de imagem... Tente via webcan");
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

			foto = Scalr.resize(foto, Scalr.Method.SPEED, Scalr.Mode.FIT_EXACT,
					118, 136, Scalr.OP_ANTIALIAS);

			Requisitante requisitante = requisicao.getRequisitante();

			userImage = Images.encode("foto.png", foto);

			requisitante.setFoto(userImage.getByteData());			
			requisitante.setDataAlteracao(new Date());
			requisitante.setAlteradoPor(UtilizadorUtils.getLogedUser());

			requisicao.setCompleta(true);
		

			requisitanteService.saveRequisitante(requisitante);
			requisicaoService.saveRequisicao(requisicao);

			//String dataDirPath = PathUtils.getWebInfPath() + "/data/"
			//		+ this.requisicao.getNumeroRequisicao();
			

			String dataDirPath = PathUtils.getEnvDataDir() + "/datareal/"
					+ this.requisicao.getNumeroRequisicao();

			File dataDir = new File(dataDirPath);
			if (dataDir.exists()) {
				ImageIO.write(foto, "PNG", new File(dataDirPath + "/"
						+ this.requisicao.getNumeroRequisicao() + ".png"));
			} else {
				if (dataDir.mkdir()) {
					ImageIO.write(foto, "PNG", new File(dataDirPath + "/"
							+ this.requisicao.getNumeroRequisicao() + ".png"));
				}
			}

			frmCompletarRequisicao.detach();

			Clients.showNotification("Foto do requisitante actualizada com sucesso");

		}

	}

	@Command
	public void cancelar() {
		frmCompletarRequisicao.detach();
	}

}
