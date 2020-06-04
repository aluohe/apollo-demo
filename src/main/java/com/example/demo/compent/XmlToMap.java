package com.example.demo.compent;

import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.*;

public class XmlToMap {

    public static TreeMap<String, String> DocumentMap(String xml) throws Exception {
        TreeMap<String, String> map = new TreeMap();
        Document document = XmlSignUtil.parseDocumentByString(xml);
        handleNode(map, document, "head");
        handleNode(map, document, "body");


        return map;
    }

    private static void handleNode(TreeMap<String, String> map, Document document, String node) {
        Node head = document.getElementsByTagName(node).item(0);

        NodeList childNodes = head.getChildNodes();

        for (int i = 0; i < childNodes.getLength(); i++) {

            if (childNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                map.put(childNodes.item(i).getNodeName(), childNodes.item(i).getTextContent());
            }

        }
    }

    //解析报文
    public static Map<String, Object> parseReceive(String request) throws DocumentException {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        org.dom4j.Document docStyle = DocumentHelper.parseText(request);
        List<Element> headList = docStyle.getRootElement().element("response").element("head").elements();
        Map<String, Object> headMap = parseHead(headList);

        List<Element> bodyList = docStyle.getRootElement().element("response").element("body").elements();
        Map<String, Object> bodyMap = parseBody(bodyList);


        returnMap.putAll(headMap);
        returnMap.putAll(bodyMap);
        return returnMap;
    }

    //解析报文头
    private static Map<String, Object> parseHead(List<Element> list) throws DocumentException {
        Map<String, Object> headMap = new HashMap<String, Object>();
        for (int i = 0; i < list.size(); i++) {
            Element e = list.get(i);
            if (e.getText() != null && !"".equals(e.getText()))
                headMap.put(e.getName(), e.getText());
        }
        return headMap;
    }

    //解析报文体
    private static Map<String, Object> parseBody(List<Element> list) throws DocumentException {
        Map<String, Object> bodyMap = new HashMap<String, Object>();
        for (int i = 0; i < list.size(); i++) {
            Element e = list.get(i);
            //包含子元素
            if (e.elements().size() != 0) {
                //重复标签合并成list
                if (bodyMap.containsKey(e.getName())) {
                    Object o = bodyMap.get(e.getName());
                    if (o instanceof ArrayList) {
                        ArrayList<Object> oList = (ArrayList<Object>) o;
                        oList.add(parseBody(e.elements()));
                    } else {
                        ArrayList<Object> oList = new ArrayList<Object>();
                        oList.add(o);
                        oList.add(parseBody(e.elements()));
                        bodyMap.put(e.getName(), oList);
                    }
                } else {
                    bodyMap.put(e.getName(), parseBody(e.elements()));
                }
                //无子元素且非空
            } else if (e.getText() != null && !"".equals(e.getText())) {
                //重复标签合并成list
                if (bodyMap.containsKey(e.getName())) {
                    Object o = bodyMap.get(e.getName());
                    if (o instanceof ArrayList) {
                        ArrayList<Object> oList = (ArrayList<Object>) o;
                        oList.add(e.getText());
                    } else {
                        ArrayList<Object> oList = new ArrayList<Object>();
                        oList.add(o);
                        oList.add(e.getText());
                        bodyMap.put(e.getName(), oList);
                    }
                } else {
                    bodyMap.put(e.getName(), e.getText());
                }
            }
        }
        return bodyMap;
    }
}
