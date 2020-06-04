package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.compent.DomCreateResponse;
import com.example.demo.compent.XmlSignUtil;
import com.example.demo.compent.XmlToMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author aluohe
 * @className NotifiyController
 * @projectName demo
 * @date 2020/4/14 9:24
 * @description 消息通知控制器
 * @modified_by
 * @version: 1.0.0
 */
@RestController
@RequestMapping(value = "/notify")
public class NotifiyController {

    /**
     * Kyb打款通知地址	http://1.80.88.220:8088/notify/kyb
     * 来帐汇入通知回调地址  【商户来账汇入】	http://1.80.88.220:8088/notify/prod/in
     * 分账通知回调地址  【商户订单分账】	http://1.80.88.220:8088/notify/prod/accounts
     * 分账退回通知地址  【交易撤销/退回】	http://1.80.88.220:8088/notify/accounts/rollback
     * 支付退回通知地址  【交易撤销/退回】	http://1.80.88.220:8088/notify/pay/rollback
     * 提现结果通知回调地址  【商户提现部分】	http://1.80.88.220:8088/notify/prod/draw
     * 非订单补贴结果通知回调地址  【商户非订单补贴】	http://1.80.88.220:8088/notify/prod/no-order
     * 预付推进结果通知接口地址【合并支付】	http://1.80.88.220:8088/notify/pay-merge
     * 电子业务凭证申请结果通知【电子回单部分】	http://1.80.88.220:8088/notify/electronic/receipt
     */
    @PostMapping(value = "/kyb")
    public void kyb(HttpServletRequest request, HttpServletResponse response) {

        BufferedReader bufferedReader = null;
        StringBuffer buffer = new StringBuffer("");

        try {
            ServletInputStream inputStream = request.getInputStream();

            InputStreamReader reader = new InputStreamReader(inputStream, "utf-8");

            bufferedReader = new BufferedReader(reader);

            String str = "";
            while ((str = bufferedReader.readLine()) != null) {
                System.out.println(str);
                buffer.append(str).append(System.getProperty("line.separator"));
            }

//            bufferedReader.close();

//            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        String string = buffer.toString();

        try {
            TreeMap<String, String> documentMap = XmlToMap.DocumentMap(string);

            String function = documentMap.get("Function");

           /* HashMap<String, String> map = new HashMap<>();
            map.put("Version", "1.0.0");
            map.put("Appid", "2020040800000532");
            map.put("Function", function);

            SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMDDhhmmss");
            map.put("RespTime", sdf.format(new Date()));
            map.put("RespTimeZone", "UTC+8");
            map.put("InputCharset", "UTF-8");
            map.put("SignType", "RSA");
            map.put("Reserve", "");
            map.put("ReqMsgId", UUID.randomUUID().toString().replaceAll("-", ""));
*/

            String xml = DomCreateResponse.requestcreateXml(function, "1.0.0", new TreeMap<>());

            //打印请求报文
            System.out.println(xml);

            //开始对请求报文进行签名验证(自签自验环节)
            boolean responseVerify = XmlSignUtil.verifyFromYourSelf(xml);

            //自签自验结果
            System.out.println("验签结果：" + responseVerify);

            response.setCharacterEncoding("utf-8");
//            response.setHeader("Content-type","application/xml;charset=UTF-8");
            response.setContentType("application/xml;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.write(xml);


            System.out.println(xml);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("llll");
    }


    /***
     * 自我测试使用方法：
     * 工具选用postman工具 params 选body  raw ->xml
     * 检查head请求头 Content-Type 是否为 application/xml
     * 选用 post 点击发送 即可
     * */

    @PostMapping(value = "/notice", produces = {"application/json;charset=UTF-8"})
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
