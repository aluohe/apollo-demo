package com.example.demo.controller;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.example.demo.manager.SysGvRelationService;
import com.example.demo.manager.SysVehicleHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApolloConfigController {
    @Autowired
    SysGvRelationService sysGvRelationService;
    @Autowired
    SysVehicleHistoryService historyService;

    @ApolloConfig
    private Config config;

//    @Value("${sofa.alipay.glmapper.name}")
//    private String name;

//    @Value("${apollo.meta}")
//    private String meta;

//    @RequestMapping("/apollo")
//    public String getConfig() {
//        return name;
//    }


    @RequestMapping("/apollo/config")
    public String getName() {
        Config config = ConfigService.getAppConfig();
        String property = config.getProperty("sofa.alipay.glmapper.name", "kll");
        System.out.println(property);
        return property;
    }


//    @RequestMapping("/apollo/meta")
//    public String getMeta() {
//        return meta;
//    }

    @PostMapping("/syn/group-vehicle")
    public String synGV() {
        sysGvRelationService.addOriginVehicle();
        return "success";
    }

    @PostMapping("/handle-repeat")
    public String handleRepeat() {
        historyService.handleHistory();
        return "success";
    }


    @Value("${kamo.cloud.paypass}")
    private String payPass;

    @GetMapping(value = "/pay-pass/enums")
    public String enums() {
        return payPass;
    }

}