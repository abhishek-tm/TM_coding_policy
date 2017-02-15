package com.example.email;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;

/**
 * SendEmail class is used for sending email by setting all the required
 * parameters. The email method takes the parameters and starts the email
 * session and then sets all the required data to be sent in the email.
 * 
 */

public class SendEmail {

	/**
	 * The email method is used for getting the Email session.
	 * 
	 * @param uname,
	 *            stores UserName or Email id for login.
	 * @param pass,
	 *            stores password for UserName.
	 * @param socketPort,
	 *            stores socket port used for sending Email.
	 * @param socketClass,
	 *            stores socket class used for sending Email.
	 * @param authEmail,
	 *            stores authorization value true or false.
	 * @param emailHost,
	 *            stores Email Host.
	 * @param emailPort,
	 *            stores Email Port.
	 * @param builder,
	 *            stores
	 * @param to,
	 *            stores
	 * @param cc,
	 *            stores
	 * @param bcc,
	 *            stores
	 * @param attachment,
	 *            stores
	 * @param sub,
	 *            stores
	 * @param msgEmail,
	 *            stores
	 * @param msgFromStatus,
	 *            stores
	 * @param from,
	 *            stores
	 * @param msgTo,
	 *            stores
	 * @param printStream,
	 *            stores
	 * @return
	 */
	public static boolean email(String uname, String pass, String socketPort, String socketClass, String authEmail,
			String emailHost, String emailPort, StringBuilder builder, String[] to, String[] cc, String[] bcc,
			String attachment, String sub, String msgEmail, String msgFromStatus, String from, String msgTo,
			PrintStream printStream) {

		Session session = getSession(uname, pass, authEmail, emailHost, emailPort, socketPort, socketClass, builder,
				printStream);

		if (session != null) {

			boolean flag = sendEmail(session, to, cc, bcc, attachment, sub, msgEmail, builder, msgFromStatus, from,
					msgTo, printStream);

			if (flag) {
				return true;
			}

		}

		return false;
	}

	/**
	 * SendEmail method is used for sending email after establishing the
	 * session.
	 * 
	 * @param session
	 * @param to
	 * @param cc
	 * @param bcc
	 * @param attachment
	 * @param sub
	 * @param msgEmail
	 * @param builder
	 * @param msgFromStatus
	 * @param from
	 * @param msgTo
	 * @param printStream
	 * @return
	 */
	public static boolean sendEmail(Session session, String[] to, String[] cc, String[] bcc, String attachment,
			String sub, String msgEmail, StringBuilder builder, String msgFromStatus, String from, String msgTo,
			PrintStream printStream) {

		try {

			String toEmail[] = to;
			String ccEmail[] = cc;
			String bccEmail[] = bcc;
			String attachments = attachment;
			String subject = sub;
			String message = msgEmail;

			String email_log = "TO :" + toEmail + " " + "CC :" + ccEmail + " " + "BCC :" + bccEmail + " " + "subject :"
					+ subject + " " + "message :" + message + " " + "attachmentName :" + attachments;

			// maintaining information is the log file data send email
			builder.append("email :- " + email_log + "\n");

			MimeMessage msg = new MimeMessage(session);
			// set message headers
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");
			// set message from address
			msg.setFrom(new InternetAddress(from, msgFromStatus));
			// set message from address setReplyTo
			msg.setReplyTo(InternetAddress.parse(msgTo, false));
			// set Subject using class MimeMessage object reference msg
			msg.setSubject(subject, "UTF-8");
			// set setSentDate using class MimeMessage object reference msg
			msg.setSentDate(new Date());

			msg.setText(message);

			// Create the message body part
			BodyPart messageBodyPart = new MimeBodyPart();
			// Fill the message
			// messageBodyPart.setText(message);
			messageBodyPart.setContent(message, "text/html");

			// Create a multipart message for attachment
			Multipart multipart = new MimeMultipart();
			// checking attachment data attachments != null.
			if (attachments != null) {
				String[] splits = attachments.split(",");
				for (String attachment_name : splits) {
					MimeBodyPart attachPart = new MimeBodyPart();
					try {
						attachPart.attachFile(attachment_name.trim());
						System.out.println(attachment_name);
					} catch (IOException ex) {
						builder.append(ex.getMessage());
					}
					multipart.addBodyPart(attachPart);
				}
			}
			// sets the multi-part as e-mail's content
			msg.setContent(multipart);
			// Set text message part
			multipart.addBodyPart(messageBodyPart);
			// Send Email only to email ids
			InternetAddress[] addressTo = new InternetAddress[toEmail.length];
			for (int i = 0; i < toEmail.length; i++) {
				addressTo[i] = new InternetAddress(toEmail[i]);
			}
			msg.setRecipients(Message.RecipientType.TO, addressTo);
			// Send Email only cc email ids
			InternetAddress[] addressCC = new InternetAddress[ccEmail.length];
			for (int i = 0; i < ccEmail.length; i++) {
				addressCC[i] = new InternetAddress(ccEmail[i]);
			}
			msg.setRecipients(Message.RecipientType.CC, addressCC);
			// Send Email only BCC email ids
			InternetAddress[] addressBCC = new InternetAddress[bccEmail.length];
			for (int i = 0; i < bccEmail.length; i++) {
				addressBCC[i] = new InternetAddress(bccEmail[i]);
			}
			msg.setRecipients(RecipientType.BCC, addressBCC);
			// builder.append("Message is ready"+ "\n");

			/**
			 * Transport class using to send function default SMTP Protocol for
			 * sending email with MimeMessage
			 */
			try {
				Transport.send(msg);
				System.out.println("Email sent");
				builder.append("Email Sent Successfully" + "\n");
			} catch (Exception e) {
				e.printStackTrace();
			}

			return true;
		} catch (Exception e) {

			if (printStream != null) {
				e.printStackTrace(printStream);
			}

			builder.append(e.getMessage() + "\n");

			return false;
		}
	}

	/**
	 * 
	 * @param uname
	 * @param pass
	 * @param authEmail
	 * @param emailHost
	 * @param emailPort
	 * @param socketPort
	 * @param socketClass
	 * @param builder
	 * @param printStream
	 * @return
	 */
	public static Session getSession(String uname, String pass, String authEmail, String emailHost, String emailPort,
			String socketPort, String socketClass, StringBuilder builder, PrintStream printStream) {

		try {

			final String username = uname;
			final String password = pass;
			final String auth = authEmail;
			final String host = emailHost;
			final String port = emailPort;
			final String sockePort = socketPort;
			final String sockeClass = socketClass;

			System.out.println("Email Session Details:-  " + username + "/" + password + "/" + auth + "/" + host + "/"
					+ port + "/" + sockeClass + "/" + sockePort + "\n");

			Properties props = new Properties();

			props.put("mail.smtp.host", host);
            props.put("mail.debug", "true");
            props.put("mail.smtp.ssl.trust", host);
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.auth", "true");


			if (sockePort == null || sockeClass == null) {

			} else {
				props.put("mail.smtp.socketFactory.port", sockePort);
				props.put("mail.smtp.socketFactory.class", sockeClass);
			}

			Authenticator authent = new Authenticator() {
                //override the getPasswordAuthentication method
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            };
            
            Session session = Session.getInstance(props, authent);
			

			return session;
		} catch (Exception e) {

			if (printStream != null) {
				e.printStackTrace(printStream);
			}

			builder.append(e.getMessage() + "\n");
			return null;
		}
	}

}
