/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Email;

import Dates.ReportingDates;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
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

    
    private static final String SEND_TO = "Connor.Meehan@us.kline.com", FROM = "RQSReporting@us.kline.com", HOST = "smtp01.us.kline.com", PORT = "25", FILE_PATH = "C:\\Weekly Reports\\Week "+new ReportingDates().reportPeriod()+" Quoting Report.pdf";

   public WeeklyEmail(){
       sendEmail();
   }
    
    private void sendEmail(){
        Properties props = new Properties();
        props.put("mail.smtp.host", HOST);
        props.put("mail.smtp.port", PORT);
        Session session = Session.getDefaultInstance(props, null);
        String messageBody = "<p><b>All:</b></p> <p style='text-indent:40px'> Please see the attached report for the RQS quoting activity in Week "+new ReportingDates().reportPeriod()+". Please direct any questions or comments you have to the <a href='mailto:kam-roro-sales@us.kline.com'>KAM RORO Sales Team</a>.</p><p style='text-align:right; margin-right:50px'><b>Sincerely,</b><br>RQS Data Admin</p><p><b><u>Note:</u></b> Do not respond to this email as it is a dead address. Address all questions and comments to the attention of KAM RORO SALES (<a href='mailto:kam-roro-sales@us.kline.com'>kam-roro-sales@us.kline.com</a>)</p>";
        try {
            Address[] SEND_TO_GROUP = new Address[]{new InternetAddress("Connor.Meehan@us.kline.com"), new InternetAddress("Homer.Crane@us.kline.com"), new InternetAddress("Tyler.Molinaro@us.kline.com")};
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(FROM, "RQS Reporting"));
            msg.addRecipients(Message.RecipientType.BCC, SEND_TO_GROUP);
            msg.setSubject("RQS Weekly Report");
            msg.setSentDate(new Date());
            
            // creates message part
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(messageBody,"text/html");
            
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
