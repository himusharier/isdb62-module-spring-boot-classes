package com.himusharier.mailsystem.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Objects;
import java.util.Properties;

import com.himusharier.mailsystem.utils.GmailServiceUtil;
import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.Multipart;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMultipart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.google.api.client.util.Base64;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;

import jakarta.mail.Message.RecipientType;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EmailService {

	@Autowired
	private GmailServiceUtil gmailServiceUtil;

	public void sendEmail(String to, String subject, String bodyText)
			throws MessagingException, IOException, GeneralSecurityException {
		// Get Gmail service
		Gmail service = gmailServiceUtil.getGmailService();

		// Create email content
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		MimeMessage email = new MimeMessage(session);

		email.setFrom(new InternetAddress("me")); // "me" is a special value for authorized user
		email.addRecipient(RecipientType.TO, new InternetAddress(to));
		email.setSubject(subject);
		email.setText(bodyText);

		// Encode and send the email
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		email.writeTo(buffer);
		byte[] bytes = buffer.toByteArray();
		String encodedEmail = Base64.encodeBase64URLSafeString(bytes);

		Message message = new Message();
		message.setRaw(encodedEmail);

		// Send the message
		service.users().messages().send("me", message).execute();
	}

	public void sendEmailWithAttachment(String to, String subject, String body, MultipartFile file)
			throws MessagingException, IOException, GeneralSecurityException {

		Gmail service = gmailServiceUtil.getGmailService();

		// Create the email content
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		MimeMessage email = new MimeMessage(session);
		email.setFrom(new InternetAddress("me"));
		email.addRecipient(RecipientType.TO, new InternetAddress(to));
		email.setSubject(subject);

		// Create multipart message
		MimeMultipart multipart = new MimeMultipart();

		// Body part
		MimeBodyPart bodyPart = new MimeBodyPart();
		bodyPart.setText(body);
		multipart.addBodyPart(bodyPart);

		// Attachment part
		MimeBodyPart attachmentPart = new MimeBodyPart();
		attachmentPart.setFileName(file.getOriginalFilename());
		attachmentPart.setContent(file.getBytes(), file.getContentType());
		multipart.addBodyPart(attachmentPart);

		// Set content to email
		email.setContent(multipart);

		// Convert to Gmail Message
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		email.writeTo(buffer);
		byte[] rawMessage = buffer.toByteArray();
		String encodedEmail = Base64.encodeBase64URLSafeString(rawMessage);

		Message message = new Message();
		message.setRaw(encodedEmail);

		service.users().messages().send("me", message).execute();
	}

	public void sendEmailWithAttachmentByParvesSir(String to, String subject, String bodyText, MultipartFile attachment)
			throws MessagingException, IOException, GeneralSecurityException {
		// Get Gmail service
		Gmail service = gmailServiceUtil.getGmailService();

		// Create email content
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		MimeMessage email = new MimeMessage(session);

		email.setFrom(new InternetAddress("me")); // "me" is a special value for authorized user
		email.addRecipient(RecipientType.TO, new InternetAddress(to));
		email.setSubject(subject);

		// Create multipart message
		Multipart multipart = new MimeMultipart();

		// Text part
		MimeBodyPart textPart = new MimeBodyPart();
		textPart.setText(bodyText);
		multipart.addBodyPart(textPart);

		// Attachment part
		MimeBodyPart attachmentPart = new MimeBodyPart();
		// Convert MultipartFile to File
		File convFile = convertMultiPartToFile(attachment);
		DataSource source = new FileDataSource(convFile);
		attachmentPart.setDataHandler(new DataHandler(source));
		attachmentPart.setFileName(attachment.getOriginalFilename());
		multipart.addBodyPart(attachmentPart);

		// Set the content
		email.setContent(multipart);

		// Encode and send the email
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		email.writeTo(buffer);
		byte[] bytes = buffer.toByteArray();
		String encodedEmail = Base64.encodeBase64URLSafeString(bytes);
		Message message = new Message();
		message.setRaw(encodedEmail);

		// Send the message
		service.users().messages().send("me", message).execute();

		// Delete the temporary file
		convFile.delete();
	}

	private File convertMultiPartToFile(MultipartFile file) throws IOException {
		File convFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}

}