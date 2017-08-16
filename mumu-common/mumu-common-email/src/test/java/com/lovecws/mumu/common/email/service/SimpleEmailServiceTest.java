package com.lovecws.mumu.common.email.service;

import com.lovecws.mumu.common.email.exception.EmailException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2017/3/7.
 */
public class SimpleEmailServiceTest {
    public static void main(String[] args){
        ClassPathXmlApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:spring-common-email.xml");
        applicationContext.start();
        SimpleEmailService simpleEmailService=applicationContext.getBean(SimpleEmailService.class);
        try{
           boolean success=simpleEmailService.send("915827225@qq.com",null,"数据内容，你在哪里","数据内容，你在哪里111111");
            System.out.println(success);
        }catch (EmailException e) {
            e.printStackTrace();
        }
        applicationContext.stop();
    }
}
