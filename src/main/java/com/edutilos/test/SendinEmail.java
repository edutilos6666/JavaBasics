package com.edutilos.test;


import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class SendinEmail {
    public static void main(String[] args) {
          try {
              m1();
          } catch(MessagingException ex) {
              ex.printStackTrace();
          }
    }


    private static void m1() throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");



        final String user = "edutilosaghayev@gmail.com";
        final String pass = "edutilos1991";

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });


        //sending simple MimeMessage
        MimeMessage msg = new MimeMessage(session);
        String from = "edutilosaghayev@gmail.com";
        String to = "edutilosaghayev@yahoo.com";
        String subject= "Testing javamail";
        msg.setFrom(new InternetAddress(from));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        msg.setSubject(subject);
        //msg.setText(text);
        Multipart multipart = new MimeMultipart();
        String filename = "foo.txt";
        BodyPart part =  new MimeBodyPart();
        part.setContent("<h1>Hello world</h1>", "text/html");
        multipart.addBodyPart(part);
        part = new MimeBodyPart();
        part.setFileName(filename);
        part.setDataHandler(new DataHandler(new FileDataSource(filename)));
        multipart.addBodyPart(part);
        msg.setContent(multipart);
        Transport.send(msg);
        System.out.println("Message was sent successfully.");
    }
}
