package com.lovecws.mumu.common.sms;

import com.lovecws.mumu.common.sms.exception.SMSException;
import com.lovecws.mumu.common.sms.service.JPushSMSService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/22.
 */
public class JPushSMSServiceTest {

    @Test
    public void test() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-common-sms.xml");
        context.start();

        JPushSMSService jPushSMSService = context.getBean(JPushSMSService.class);
        try {
            String sendSMS = jPushSMSService.sendSMS("18971336061");
            System.out.println(sendSMS);
        } catch (SMSException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testWithTemplate() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-common-sms.xml");
        context.start();

        JPushSMSService jPushSMSService = context.getBean(JPushSMSService.class);
        try {
            Map<String,String> map=new HashMap<String,String>();
            map.put("product","永喆集团二期项目投资基金");
            String sendSMS = jPushSMSService.sendSMSWithTemplate("18971336061","102578",map);
            System.out.println(sendSMS);
        } catch (SMSException e) {
            e.printStackTrace();
        }
    }
}
