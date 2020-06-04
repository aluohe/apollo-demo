package com.example.demo.compent;

import com.sun.org.apache.xml.internal.security.signature.XMLSignature;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.security.PrivateKey;
import java.text.SimpleDateFormat;
import java.util.*;

import static sun.rmi.transport.TransportConstants.Version;

public class DomCreateResponse {

    public static DocumentBuilder getDocumentBuilder() {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return db;
    }



    //各类回调通知响应
    public static String  requestcreateXml(Map notifyUrl) throws Exception{

        DocumentBuilder db = getDocumentBuilder();
        Document document = db.newDocument();

        Element root = document.createElement("document");

        Element full = document.createElement("response");
        full.setAttribute("id","response");

        Element head = document.createElement("head");



        Iterator<Map.Entry<String, String>> entries = notifyUrl.entrySet().iterator();
        while (entries.hasNext()) {

            Map.Entry<String, String> entry = entries.next();


            Element name = document.createElement(entry.getKey());

            if (entry.getKey().equals("ReqTime")){
                name = document.createElement("RespTime");
            }

            if(entry.getKey().equals("ReqTimeZone")){
                name = document.createElement("RespTimeZone");
            }

            name.setTextContent(entry.getValue());



            head.appendChild(name);

        }


        Element body = document.createElement("body");

        Element  RespInfo = document.createElement("RespInfo");

        Element ResultStatus = document.createElement("ResultStatus");
        ResultStatus.setTextContent("S");
        RespInfo.appendChild(ResultStatus);

        Element ResultCode = document.createElement("ResultCode");
        ResultCode.setTextContent("0000");
        RespInfo.appendChild(ResultCode);

        Element ResultMsg = document.createElement("ResultMsg");
        ResultMsg.setTextContent("成功");
        RespInfo.appendChild(ResultMsg);

        body.appendChild(RespInfo);

        full.appendChild(head);
        full.appendChild(body);
        root.appendChild(full);

        document.appendChild(root);

        PrivateKey privateKey = SignatureUtils.getPrivateKey(XmlSignUtil.TEST_PRIVATE_KEY);

        String xmlResult = SignatureUtils.signXmlElement(privateKey, document, "response",
                XMLSignature.ALGO_ID_SIGNATURE_RSA_SHA256, 2);

        return xmlResult;
    }

    //各类回调通知响应
    public static String requestcreateXml(String function,String Version,TreeMap<String,String > bodyMap) throws Exception {

        DocumentBuilder db = getDocumentBuilder();
        Document document = db.newDocument();

        Element root = document.createElement("document");

        Element full = document.createElement("response");
        full.setAttribute("id", "response");



        Element head = createHeadElement(function,Version,document);


//        Element head = document.createElement("head");

/*
        Iterator<Map.Entry<String, String>> entries = notifyUrl.entrySet().iterator();
        while (entries.hasNext()) {

            Map.Entry<String, String> entry = entries.next();


            Element name = document.createElement(entry.getKey());

            if (entry.getKey().equals("ReqTime")) {
                name = document.createElement("RespTime");
            }

            if (entry.getKey().equals("ReqTimeZone")) {
                name = document.createElement("RespTimeZone");
            }

            name.setTextContent(entry.getValue());


            head.appendChild(name);

        }*/


//        Element body = createBodyElement(bodyMap,document);


        Element body = document.createElement("body");

        Element RespInfo = document.createElement("RespInfo");

        Element ResultStatus = document.createElement("ResultStatus");
        ResultStatus.setTextContent("S");
        RespInfo.appendChild(ResultStatus);

        Element ResultCode = document.createElement("ResultCode");
        ResultCode.setTextContent("0000");
        RespInfo.appendChild(ResultCode);

        Element ResultMsg = document.createElement("ResultMsg");
        ResultMsg.setTextContent("成功");
        RespInfo.appendChild(ResultMsg);

        body.appendChild(RespInfo);

        full.appendChild(head);
        full.appendChild(body);
        root.appendChild(full);

        document.appendChild(root);

        PrivateKey privateKey = SignatureUtils.getPrivateKey(XmlSignUtil.TEST_PRIVATE_KEY);

        String xmlResult = SignatureUtils.signXmlElement(privateKey, document, "response",
                XMLSignature.ALGO_ID_SIGNATURE_RSA_SHA256, 2);

        return xmlResult;
    }

    public static Element createBodyElement (TreeMap<String,String > bodyMap,Document document){

        Element body = document.createElement("body");

        Iterator<Map.Entry<String, String>> entries = bodyMap.entrySet().iterator();

        while (entries.hasNext()) {

            Map.Entry<String, String> entry = entries.next();

            Element name = document.createElement(entry.getKey());

            if(entry.getKey().toString().equals("ReqTime")){
                name.setTextContent(entry.getValue());
            }else{
                name.setTextContent(entry.getValue());
            }

            body.appendChild(name);

        }
        return  body;

    }

    public static Element createHeadElement(String function,String Version,Document document){


        Element head = document.createElement("head");

        Element version =document.createElement("Version");
        version.setTextContent(Version);

        Element appid = document.createElement("Appid");
        appid.setTextContent("2020040800000532");

        Element Function = document.createElement("Function");
        Function.setTextContent(function);

        Element reqTime = document.createElement("RespTime");
        SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMDDhhmmss");
        reqTime.setTextContent(sdf.format(new Date()));

        Element respTimeZone = document.createElement("RespTimeZone");
        respTimeZone.setTextContent("UTC+8");


        Element reserve = document.createElement("Reserve");
        reserve.setTextContent("");

        Element reqMsgId = document.createElement("ReqMsgId");
        reqMsgId.setTextContent(UUID.randomUUID().toString().replace("-",""));

        Element inputCharset = document.createElement("InputCharset");
        inputCharset.setTextContent("UTF-8");


        Element signType = document.createElement("SignType");
        signType.setTextContent("RSA");

        head.appendChild(version);
        head.appendChild(appid);
        head.appendChild(Function);
        head.appendChild(reqTime);
        head.appendChild(respTimeZone);
        head.appendChild(reqMsgId);
        head.appendChild(reserve);
        head.appendChild(inputCharset);
        head.appendChild(signType);

        return head;
    }


    public static void main(String[] args) {

        try {
            HashMap<String, String> map = new HashMap<>();
            map.put("Version", "1.0.0");
            map.put("Appid", "2020040800000532");
            map.put("Function", "1.0.0");

            SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMDDhhmmss");
            map.put("RespTime", sdf.format(new Date()));
            map.put("RespTimeZone", "UTC+8");
            map.put("InputCharset", "UTF-8");
            map.put("SignType", "RSA");
            map.put("Reserve", "");
            map.put("ReqMsgId", UUID.randomUUID().toString().replaceAll("-", ""));


//            String xml = DomCreateResponse.requestcreateXml(map);
//            System.out.println(xml);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
