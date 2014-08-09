package org.apm.carteiraprofissional.utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EnviarEmail {
	private static final String FROMEMAIL="pctjose@gmail.com";
	private static final String SENHA="jorge3";
	
	public static void sendEmail(final String username,final String password,String recepientEmail,String title,String sms){
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

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
	}
	
	public static void sendEmail(String recepientEmail,String title,String sms){
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
            message.setFrom(new InternetAddress(SENHA));
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(recepientEmail));
            message.setSubject(title);
            message.setText(sms);

            Transport.send(message);           

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
	}
}
