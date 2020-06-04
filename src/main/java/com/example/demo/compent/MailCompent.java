package com.example.demo.compent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @author aluohe
 * @className MailCompent
 * @projectName demo
 * @date 2020/3/18 21:41
 * @description 邮件配置类
 * @modified_by
 * @version: 1.0.0
 */
@Component
public class MailCompent {

    @Autowired
    private JavaMailSender javaMailSender;

    public void send() {

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("送测单-v1-aluohe_by :" + new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime()));
        mailMessage.setText("ahh");
        mailMessage.setTo("414173560@qq.com");
        mailMessage.setFrom("gaobei@deyuanqiche.com");
        javaMailSender.send(mailMessage);
    }

    @Autowired
    private TemplateEngine engine;

    @Autowired
    private AsynTest asynTest;

    @Autowired
    private ApplicationContext applicationContext;

    public void sendV2() {

        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setSubject("送测单");

            Context context = new Context();
            Map<String, Object> map = new HashMap<>();

//            map.put("title", "邮件标题");
//            map.put("content", "邮件正文内容");
            map.put("name", "aluohe");
            map.put("platform", "web");
            map.put("test_time", new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime()));
            map.put("online_time", new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime()));
            map.put("emergency", "一般");
            map.put("test_result", "success");
            map.put("test_content", "verify");
            map.put("change_range", "no problem");

            context.setVariables(map);
            String mail = engine.process("mailV2", context);
            helper.setText(mail, true);
            helper.setTo("aluohe@126.com");
//            helper.setTo(new String[]{"", ""});

            helper.setFrom("gaobei@deyuanqiche.com");

            FileSystemResource file = new FileSystemResource("C:\\Users\\86152\\Pictures\\Camera Roll\\06.jpg");
            helper.addAttachment("ahh.jpg", file);

            asynTest.testAsyn();
//            testAsyn();
            MailCompent bean = (MailCompent) applicationContext.getBean(MailCompent.class);
            bean.testAsyn();
            javaMailSender.send(mimeMessage);
            System.out.println("结束");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Async
    public void testAsyn() {

        try {
            Thread.sleep(10000);
            System.out.println("异步执行任务v3");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}