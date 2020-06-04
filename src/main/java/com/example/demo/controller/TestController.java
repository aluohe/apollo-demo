package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author aluohe
 * @className TestController
 * @projectName demo
 * @date 2020/4/26 15:58
 * @description
 * @modified_by
 * @version:
 */
@RestController
public class TestController {



    @PostMapping(value = "/payment/v1/callback/west-pay", produces = {"application/json;charset=UTF-8"})
    public String notice(@RequestBody String data) throws Exception {

        //对来自网商得报文做签名验证
//        boolean result = XmlSignUtil.verify(data);
        //验签结果
//        System.out.println(result);

        //开始对响应回执进行组装
        /***
         * 1 采用String解析成Document 获取 head 节点内容
         * 2 将节点内容封装成Map
         * 3 开始组装报文
         * */
        /*TreeMap<String, String> map = XmlToMap.DocumentMap(data);

        //响应回执生成(报文组装步骤)
        String response = DomCreateResponse.requestcreateXml(map);

        //开始对响应回执进行签名验证(自签自验环节)
        boolean responseVerify = XmlSignUtil.verifyFromYourSelf(response);

        //验证结果
        System.out.println(responseVerify);

        //打印回执
        System.out.println(response);

        //发送回执
        return response;*/

        System.out.println(data);
        Map<String,String> map = JSONObject.parseObject(data, Map.class);

        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "\t" + entry.getValue());
        }
        return data;

    }
}