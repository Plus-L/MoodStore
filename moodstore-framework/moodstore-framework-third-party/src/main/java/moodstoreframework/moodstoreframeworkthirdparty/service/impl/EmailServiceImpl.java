//package moodstoreframework.moodstoreframeworkthirdparty.service.impl;
//
//import moodstoreframework.moodstoreframeworkthirdparty.service.EmailService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.core.io.FileSystemResource;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Service;
//
//import javax.mail.MessagingException;
//import javax.mail.internet.MimeMessage;
//import java.io.File;
//@Service
//public class EmailServiceImpl implements EmailService {
//    @Autowired
//    private JavaMailSender javaMailSender;
//    @Override
//    public void sendSimpleMail(String from, String to, String subject, String text) {
//        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//        // 发件人
//        simpleMailMessage.setFrom(from);
//        // 收件人
//        simpleMailMessage.setTo(to);
//        // 邮件主题
//        simpleMailMessage.setSubject(subject);
//        // 邮件内容
//        simpleMailMessage.setText(text);
//        javaMailSender.send(simpleMailMessage);
//    }
//}