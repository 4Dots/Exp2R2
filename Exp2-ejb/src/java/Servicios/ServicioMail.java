/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servicios;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author estudiante
 */
public class ServicioMail {
    
    public ServicioMail(){
        
    }
    
    public void mandarCorreo(String destino, String mensaje){
      // Recipient's email ID needs to be mentioned.
      String to = destino;

      // Sender's email ID needs to be mentioned
      String from = "sebastiansalas94@gmail.com";

      // Assuming you are sending email from localhost
      String host = "8080";

      // Get system properties
      Properties properties = System.getProperties();

      // Setup mail server
      properties.setProperty("mail.smtp.host", host);

      // Get the default Session object.
      Session session = Session.getDefaultInstance(properties);

      try{
         // Create a default MimeMessage object.
         MimeMessage message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));

         // Set Subject: header field
         message.setSubject("Tienes un nuevo bono!");

         // Now set the actual message
         message.setText(mensaje);

         // Send message
         Transport trans = session.getTransport("smtp");
            trans.connect(from, "sm940410");
            trans.send(message);
            trans.close();
         
         System.out.println("Sent message successfully....");
      }catch (MessagingException mex) {
         mex.printStackTrace();
      }
    }
    
    
    public void enviar(String destino, String mensaje){
        
         
        try{
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");         
            Session ses = Session.getDefaultInstance(props);  
            MimeMessage mim = new MimeMessage(ses);
            mim.setFrom(new InternetAddress("sebastiansalas94@gmail.com"));
            mim.setRecipient(Message.RecipientType.TO,new InternetAddress(destino));
            mim.setSubject("Tienes un nuevo bono!");
            mim.setText(mensaje);
            Transport.send(mim);
            
        }catch(MessagingException mex ){
            mex.printStackTrace();

        }
    
    }
    
}
