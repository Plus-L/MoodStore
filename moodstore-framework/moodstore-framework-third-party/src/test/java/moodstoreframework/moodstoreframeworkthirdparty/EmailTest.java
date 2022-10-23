package moodstoreframework.moodstoreframeworkthirdparty;

import moodstoreframework.moodstoreframeworkthirdparty.config.EmailConfig;
import moodstoreframework.moodstoreframeworkthirdparty.service.SmsService;
import moodstoreframework.moodstoreframeworkthirdparty.service.impl.SmsServiceImpl;
import moodstoreframework.moodstoreframeworkthirdparty.util.HttpUtils;
import org.apache.http.HttpResponse;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
public class EmailTest {

//    @Autowired
//    private JavaMailSender javaMailSender;
    @Autowired
    private JavaMailSender javaMailSender;

    @Test
    public void sendSimpleEmail0(){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        // 发件人
        simpleMailMessage.setFrom("1832311632@qq.com");
        // 收件人
        simpleMailMessage.setTo("1832311632@qq.com");
        // 邮件主题
        simpleMailMessage.setSubject(null);
        // 邮件内容
        simpleMailMessage.setText(null);
        javaMailSender.send(simpleMailMessage);

    }

//    @Test
//    public void sendSimpleMail() {
//        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//        // 发件人
//        simpleMailMessage.setFrom("1832311632@qq.com");
//        // 收件人
//        simpleMailMessage.setTo("1832311632@qq.com");
//        // 邮件主题
//        simpleMailMessage.setSubject("test");
//        // 邮件内容
//        simpleMailMessage.setText("hello world");
//        javaMailSender.send(simpleMailMessage);
//    }
    @Test
    public void sendSms(){
        String host = "https://dfsns.market.alicloudapi.com";
        String path = "/data/send_sms";
        String method = "POST";
        String appcode = "7f63dd6249ce4c00ac7770255b963003";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("content", "code:1001");
        bodys.put("phone_number", "13875612518");
        bodys.put("template_id", "TPL_0000");


        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
