package com.example.demo.controller;

import com.example.demo.compent.MailCompent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author aluohe
 * @className MailController
 * @projectName demo
 * @date 2020/3/20 13:42
 * @description
 * @modified_by
 * @version:
 */
@RestController
@RequestMapping(value = "/mail")
public class MailController {
    @Autowired
    MailCompent mailCompent;


    @GetMapping(value = "/send")
    public void send() {
        mailCompent.sendV2();
    }
}