/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Email;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author cmeehan
 */
public class WeeklyEmail {

    private static final String SEND_TO = "connor.meehan@us.kline.com", FROM = "RQSReporting@us.kline.com", USERNAME = "", PASSWORD = "", HOST = "smtp01.us.kline.com", PORT = "25", FILE_PATH = "C:/Users/cmeehan/Desktop/Weekly Report.PDF";

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("mail.smtp.host", HOST);
        props.put("mail.smtp.port", PORT);
        Session session = Session.getDefaultInstance(props, null);
        String messageBody = "This one should have the attachment";
        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(FROM, "RQS Reporting"));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(SEND_TO));
            msg.setSubject("RQS Weekly Quoting Data");
            msg.setSentDate(new Date());
            
            // creates message part
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(messageBody, "text/html");
            
            // creates multi-part
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            
            // adds attachments
            MimeBodyPart attachPart = new MimeBodyPart();
            attachPart.attachFile(FILE_PATH);
            multipart.addBodyPart(attachPart);
            
            msg.setContent(multipart);
                    

            Transport.send(msg);
            System.out.println("Success");
        } catch (MessagingException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
