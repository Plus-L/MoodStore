package moodstoreframework.moodstoreframeworkthirdparty.controller;

import com.kci.moodstore.framework.common.result.CommonResult;
import moodstoreframework.moodstoreframeworkthirdparty.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sms")
public class SmsController {
    @Autowired
    private SmsService smsService;

     @PostMapping("/send")
     public CommonResult sendSms(){
         smsService.sendSms();
         System.out.println("短信发送成功");
         return CommonResult.success(null);
     }

}
