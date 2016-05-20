/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pl.helper;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 *
 * @author theba
 */
@Service
public class EmailHelper {

    @Autowired
    private JavaMailSender mailSender;

    public void send(String toAddress, String subject, String msgBody) {
        send(toAddress, "abcmcuser.1@gmail.com", subject, msgBody);
    }
    
    public void send(String toAddress, String fromAddress, String subject, String msgBody) {

        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
            mimeMessage.setContent(msgBody, "text/html");
            helper.setTo(toAddress);
            helper.setSubject(subject);
            helper.setFrom(fromAddress);
            /*
            SimpleMailMessage crunchifyMsg = new SimpleMailMessage();
            crunchifyMsg.setFrom(fromAddress);
            crunchifyMsg.setTo(toAddress);
            crunchifyMsg.setSubject(subject);
            crunchifyMsg.setText(msgBody);
            */
            mailSender.send(mimeMessage);
        } catch (MessagingException ex) {
            Logger.getLogger(EmailHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
