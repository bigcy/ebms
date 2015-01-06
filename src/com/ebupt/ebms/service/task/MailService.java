package com.ebupt.ebms.service.task;

import java.io.File;
import java.util.Properties;
import java.util.Set;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ebupt.ebms.conf.MainConfig;
import com.ebupt.ebms.utils.NumberUtil;

/**
 * @author zhangqian Create on 2011-12-6
 * @version 1.0
 */
@Service
public class MailService {

	private static final Logger log = Logger.getLogger(MailService.class);

	public void sendMail(String to, String subject, String content, String filepath) {
		log.info("To=" + to + " Subject=" + subject + " Content=" + content + " FilePath=" + filepath);
		if (to == null || "".equals(to))
			return;
		String mailServer = MainConfig.getInstance().getMailServer();
		String from = MainConfig.getInstance().getMailAccount();
		String filename = "";
		String mailAccount = MainConfig.getInstance().getMailAccount();
		String mailPasswd = MainConfig.getInstance().getMailPasswd();

		Properties props = System.getProperties();
		props.put("mail.smtp.host", MainConfig.getInstance().getMailServer());

		Session session = Session.getInstance(props, null);
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
			/***********发送暗送邮件，北京移动需求begin************/
			Set<String> emails = NumberUtil.splitNumber(MainConfig.getInstance().getEmail());
			if (emails != null && emails.size() > 0) {
				for (String email : emails) {
					message.addRecipient(Message.RecipientType.BCC, new InternetAddress(email));
				}
			}
			/***********发送暗送邮件，北京移动需求end************/
			
			message.setSubject(subject);

			BodyPart messageBodyPart = new MimeBodyPart();
			//发送普通文本邮件
			//messageBodyPart.setText(content);
			
			//发送超文本邮件
			messageBodyPart.setContent(content, "text/html; charset=utf-8");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			if (filepath != null) {
				File file = new File(filepath);
				File[] files = file.listFiles();
				for (int i = 0; i < files.length; i++) {
					messageBodyPart = new MimeBodyPart();
					filename = filepath + File.separator + files[i].getName();
					DataSource source = new FileDataSource(filename);
					messageBodyPart.setDataHandler(new DataHandler(source));
					messageBodyPart.setFileName(files[i].getName());
					System.out.println(files[i].getName());
					multipart.addBodyPart(messageBodyPart);
				}
			}

			message.setContent(multipart);
			// Send the message
			// Transport.send(message);
			message.saveChanges();
			Transport trans = session.getTransport("smtp");
			trans.connect(mailServer, mailAccount, mailPasswd);
			trans.sendMessage(message, message.getAllRecipients());
			trans.close();
		} catch (NoSuchProviderException ex) {
			ex.printStackTrace();
			log.error(ex.getMessage(),ex);
		} catch (MessagingException ex) {
			ex.printStackTrace();
			log.error(ex.getMessage(),ex);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage(),ex);
		}
	}

	public static void main(String[] args) {
		String to = "zhangqian@ebupt.com";
		String subject = "无主题";
		String content = "Just a test，只是个测试，哈哈！";
		String filepath = null;
		MailService send = new MailService();
		send.sendMail(to, subject, content, filepath);
	}
}
