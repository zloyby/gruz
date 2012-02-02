package by.zloy.util;

import by.zloy.document.Document;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
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
import java.io.IOException;
import java.util.Properties;

public class SendMailSSLUtil {

    public static void send(Document attachment, String subject) throws MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                 new javax.mail.Authenticator() {
                     protected PasswordAuthentication getPasswordAuthentication() {
                         return new PasswordAuthentication(
                                 PropertiesUtil.getProperty("auth.mail"),
                                 PropertiesUtil.getProperty("auth.password"));
                     }
                 });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(PropertiesUtil.getProperty("auth.mail")));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(PropertiesUtil.getProperty("kindle.mail")));
        message.setSubject(subject);

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        DataSource source = new FileDataSource(attachment.getFile());
        messageBodyPart.setDataHandler(new DataHandler(source));

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        message.setContent(multipart);

        Transport.send(message);
    }
}
