package org.apm.carteiraprofissional.utils;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EnviarEmail {
	public static final String FROMEMAIL = "pctjose@gmail.com";
	public static final String SENHA = "jorge3";

	public static void sendEmail(final String username, final String password,
			String recepientEmail, String title, String sms) {
		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(recepientEmail));
			message.setSubject(title);
			message.setText(sms);

			Transport.send(message);			

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	public static void sendEmail(String recepientEmail, String title, String sms) {
		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(FROMEMAIL, SENHA);
					}
				});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(FROMEMAIL));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(recepientEmail));
			message.setSubject(title);
			message.setText(sms);

			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	public static void sendEmail(String recepientEmail, String title,
			String sms, String sourceAttacheFile) throws Exception {
		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(FROMEMAIL, SENHA);
					}
				});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(FROMEMAIL));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(recepientEmail));
			message.setSubject(title);

			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText(sms);

			Multipart multiPartSMS = new MimeMultipart();

			multiPartSMS.addBodyPart(messageBodyPart);

			messageBodyPart = new MimeBodyPart();

			File attach = new File(sourceAttacheFile);

			DataSource dataSource = new FileDataSource(attach);
			messageBodyPart.setDataHandler(new DataHandler(dataSource));
			messageBodyPart.setFileName(attach.getName());

			multiPartSMS.addBodyPart(messageBodyPart);

			message.setContent(multiPartSMS);

			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
