//package moodstoreframework.moodstoreframeworkthirdparty.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.mail.MailSender;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//@Configuration
//public class EmailConfig {
//
//    /**
//     * 配置邮件发送器
//     * @return
//     */
//    @Bean
//    public MailSender mailSender() {
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        mailSender.setHost("qq.com");//指定用来发送Email的邮件服务器主机名
//        mailSender.setPort(25);//默认端口，标准的SMTP端口
//        mailSender.setUsername("1832311632@qq.com");//用户名
//        mailSender.setPassword("xuexinuli.");//密码
//        return mailSender;
//    }
//
//}