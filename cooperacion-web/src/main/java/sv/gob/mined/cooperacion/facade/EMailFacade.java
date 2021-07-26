/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.cooperacion.facade;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
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

/**
 *
 * @author misanchez
 */
@Stateless
@LocalBean
public class EMailFacade {

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Boolean enviarMail(InternetAddress[] to, InternetAddress[] cc, String remitente,
            String titulo, String mensaje, Session mailSession) {
        try {
            MimeMessage m = new MimeMessage(mailSession);
            Address from = new InternetAddress(remitente);

            m.setFrom(from);
            m.setRecipients(Message.RecipientType.TO, to);
            if (cc != null) {
                m.setRecipients(Message.RecipientType.CC, cc);
            }

            BodyPart messageBodyPart1 = new MimeBodyPart();

            messageBodyPart1.setContent(mensaje, "text/html; charset=utf-8");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart1);

            m.setContent(multipart);
            m.setSubject(titulo, "UTF-8");

            Transport.send(m);
            return true;
        } catch (MessagingException ex) {
            Logger.getLogger(EMailFacade.class.getName()).log(Level.WARNING, "Error en el envio de correo a: {0}", new Object[]{to});
            Logger.getLogger(EMailFacade.class.getName()).log(Level.WARNING, "Error", ex);
            return false;
        }
    }

    public void enviarMailDeError(String subject, String message, String usuario, Session mailSession) {
        try {
            MimeMessage m = new MimeMessage(mailSession);
            Address from = new InternetAddress(usuario);
            m.setSubject(subject, "UTF-8");

            m.setFrom(from);
            m.setRecipients(Message.RecipientType.TO, "miguel.sanchez@mined.gob.sv");
            m.setSentDate(new java.util.Date());
            m.setText(message, "UTF-8", "html");
            Transport.send(m);
        } catch (MessagingException ex) {
            Logger.getLogger(EMailFacade.class.getName()).log(Level.SEVERE, "Error en el envio de correo");
        }
    }

    public void enviarMailDeConfirmacion(String subject, String message, String remitente, Session mailSession) {
        try {
            MimeMessage m = new MimeMessage(mailSession);
            Address from = new InternetAddress(remitente);
            m.setSubject(subject, "UTF-8");

            m.setFrom(from);

            Address[] lstDestinatarios = new Address[]{new InternetAddress("miguel.sanchez@mined.gob.sv")};

            m.setRecipients(Message.RecipientType.TO, remitente);
            m.setRecipients(Message.RecipientType.BCC, lstDestinatarios);

            m.setSentDate(new java.util.Date());
            m.setText(message, "UTF-8", "html");
            Transport.send(m);
        } catch (MessagingException ex) {
            Logger.getLogger(EMailFacade.class.getName()).log(Level.SEVERE, "Error en el envio de correo");
        }
    }

    public Session getMailSessionOffice(Session mailSession, String remitente, String password) {
        if (mailSession == null) {
            Properties configEmail = new Properties();

            configEmail.put("mail.smtp.auth", "true");
            configEmail.put("mail.smtp.starttls.enable", "true");

            configEmail.put("mail.smtp.host", "smtp.office365.com");
            configEmail.put("mail.smtp.port", "587");

            configEmail.put("mail.user", remitente);
            configEmail.put("mail.user.pass", password);
            configEmail.put("mail.from", remitente);

            mailSession = Session.getInstance(configEmail, new Authenticator() {

                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(remitente, password);
                }
            });
        }

        return mailSession;
    }

    public Session getMailSessionMined(Session mailSession, String dominio, String password, String remitente) {
        if (mailSession == null) {
            Properties configEmail = new Properties();

            configEmail.put("mail.smtp.auth", "true");
            configEmail.put("mail.smtp.starttls.enable", "false");

            configEmail.put("mail.smtp.host", "svr2k13mail01.mined.gob.sv");
            configEmail.put("mail.smtp.port", "2525");

            configEmail.put("mail.user", "MINED\\" + dominio);
            configEmail.put("mail.user.pass", password);
            configEmail.put("mail.from", remitente);

            mailSession = Session.getInstance(configEmail, new Authenticator() {

                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("MINED\\" + dominio, password);
                }
            });
        }

        return mailSession;
    }
}
