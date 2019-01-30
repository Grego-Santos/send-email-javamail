/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author italo
 */
public class Email {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        enviarEmail("[Teste] Título do email", "<h1>Envio de email por meio de API Java</h1><br><strong>Com marcação de hipertexto.</strong>", "email destinatário");
        
    }
    
    public static boolean enviarEmail(String tituloDoEmail, String conteudoDoEmail, String emailDestinatario) {
        
        String sEmailRemetente = "seu email";
        String sSenhaEmailRemetente = "sua senha";
        boolean enviou = false;
        
        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.ssl.enable", false); // caso precise usar autenticação ssl
        props.put("mail.smtp.host", "email-ssl.com.br");
        props.put("mail.smtp.port", "465");
        
        System.out.println("1");
        
        Session sesConexaoEstabelecida = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sEmailRemetente, sSenhaEmailRemetente);
            }
        });
        System.out.println("2");
        try {
            
            Message mMessage = new MimeMessage(sesConexaoEstabelecida);
            mMessage.setFrom(new InternetAddress(sEmailRemetente));
            mMessage.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(emailDestinatario));//u will send to
            mMessage.setSubject(tituloDoEmail);
            mMessage.setText("conteúdo do email");
            mMessage.setContent(conteudoDoEmail, "text/html"); // conteúdo com HTML
            System.out.println("3");
            Transport.send(mMessage);
            enviou = true;
            System.out.println("enviado");
        } catch (MessagingException e) {
            System.out.println(e);
        }
        return enviou;
    }
    
}
