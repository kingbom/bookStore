package bookstore;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class MailUtilAuth {
	
	  private static final String SMTP_HOST_NAME = "smtp.gmail.com";
	  private static final String SMTP_AUTH_USER = "booksrus393";
	  private static final String SMTP_AUTH_PWD  = "jhu605.782";
	  private static final String CC_ADDRESS = "mark@the-bucks.org";

	  public static void sendMail(String to, String from,
            String subject, String body, boolean bodyIsHTML)
            throws MessagingException {
    	
            // 1 - get a mail session
            Properties props = new Properties();
            props.put("mail.transport.protocol", "smtps");
            props.put("mail.smtps.host", SMTP_HOST_NAME);
            props.put("mail.smtps.port", 465);
            props.put("mail.smtps.auth", "true");
            Session session = Session.getDefaultInstance(props);
            session.setDebug(true);
            // 2 - create a message
            Message message = new MimeMessage(session);
            message.setSubject(subject);
            if (bodyIsHTML)
                message.setContent(body, "text/html");
            else
                message.setText(body);
            // 3 - address the message
            Address fromAddress = new InternetAddress(from);
            Address toAddress = new InternetAddress(to);
            Address ccAddress = new InternetAddress(CC_ADDRESS);
            message.setFrom(fromAddress);
            message.setRecipient(Message.RecipientType.TO, toAddress);
            message.setRecipient(Message.RecipientType.CC, ccAddress);
         // 4 - send the message
            Transport transport = session.getTransport();
            transport.connect(SMTP_HOST_NAME, SMTP_AUTH_USER, SMTP_AUTH_PWD);
            transport.sendMessage(
                message, message.getAllRecipients());
            transport.close();
            }
}
