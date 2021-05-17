/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Connection.MyConnection;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.Properties;

/**
 *
 * @author Maryem
 */
public class SendingMail {

    public SendingMail() {
    }

//    public void SendEmail(User u,String code) {
//        try {
//            String requete = " INSERT INTO verifcode (id_user, code)"
//                    + "VALUES (?, ?)";
//            PreparedStatement pst
//                    = new MyConnection().cn.prepareStatement(requete);
//
//            pst.setInt(1, u.getId());
//            pst.setString(2, code);
//
//            System.out.println("code " + code + " ");
//            String host = "smtp.gmail.com";
//            String user = "teachmepidev@gmail.com";
//            String pass = "teachmepidev";
//            String to = u.getEmail();
//            String from = "teachmepidev@gmail.com";
//            String messageText = "This is confirmation number for your expert programming account. \nPlease insert this number to activate your account : " + code + " \nThanks for being one of us ";
//            String subject = " Email de Confirmation ";
//            boolean sessionDebug = true;
//
//            Properties props = System.getProperties();
//
//            props.put("mail.smtp.starttls.enable", "true");
//            props.put("mail.smtp.host", host);
//            props.put("mail.smtp.port", "587");
//            props.put("mail.smtp.auth", "true");
//            props.put("mail.smtp.starttls.required", "true");
//
//            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
//            Session mailSession = Session.getDefaultInstance(props, null);
//            mailSession.setDebug(sessionDebug);
//            Message msg = new MimeMessage(mailSession);
//            msg.setFrom(new InternetAddress(from));
//            InternetAddress[] address = {new InternetAddress(to)};
//            msg.setRecipients(Message.RecipientType.TO, address);
//            msg.setSubject(subject);
//            msg.setSentDate(new Date());
//            msg.setText(messageText);
//
//            javax.mail.Transport transport = mailSession.getTransport("smtp");
//            transport.connect(host, user, pass);
//            transport.sendMessage(msg, msg.getAllRecipients());
//            transport.close();
//            System.out.println("message sent successfully");
//        } catch (SQLException ex) {
//            Logger.getLogger(SendingMail.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (MessagingException ex) {
//            System.out.println(ex);
//        }
//    }
//    
    /**
     * Utility method to send simple HTML email
     *
     * @param u
     * @param code
     */
    public static void sendEmail(User u,int id, String code,String role) {
        try {
            String requete = " INSERT INTO verifcode (id_user, code,role)"
                    + "VALUES (?, ?, ?)";
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);

            pst.setInt(1, id);
            pst.setString(2, code);
            pst.setString(3, role);

            final String fromEmail = "teachmepidev@gmail.com"; //requires valid gmail id
            final String password = "Esprit123"; // correct password for gmail id

            Authenticator auth = new Authenticator() {
                //override the getPasswordAuthentication method
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            };

            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
            props.put("mail.smtp.port", "587"); //TLS Port
            props.put("mail.smtp.auth", "true"); //enable authentication
            props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

            Session session = Session.getInstance(props, auth);
            MimeMessage msg = new MimeMessage(session);

            //set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress(fromEmail, "NoReply-JD"));

            msg.setReplyTo(InternetAddress.parse(u.getEmail(), false));

            msg.setSubject("Teach Me : Email Confirmation", "UTF-8");

            msg.setText("This is confirmation number for your expert programming account. \nPlease insert this number to activate your account : " + code + " \nThanks for being one of us ", "UTF-8");

            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(u.getEmail(), false));
            Transport.send(msg);
            pst.executeUpdate();
            System.out.println("Email Sent Successfully!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
