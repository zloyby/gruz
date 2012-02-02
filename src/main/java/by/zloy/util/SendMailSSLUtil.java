package by.zloy.util;

import by.zloy.document.Document;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

import java.io.IOException;

public class SendMailSSLUtil {

    public static void send(Document document, String subject) throws EmailException, IOException {
        MultiPartEmail email = new MultiPartEmail();
        email.setSubject(subject);
        email.setFrom(PropertiesUtil.getProperty("auth.mail"));
        email.addTo(PropertiesUtil.getProperty("kindle.mail"));
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(465);
        email.setSSL(true);
        email.setAuthenticator(new DefaultAuthenticator(PropertiesUtil.getProperty("auth.mail"),
                                                        PropertiesUtil.getProperty("auth.password")));

        EmailAttachment attachment = new EmailAttachment();
        attachment.setPath(document.getFile().getPath());
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setDescription(document.getFile().getName());
        attachment.setName(document.getFile().getName());
        email.attach(attachment);

        email.send();
    }
}
