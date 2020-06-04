package com.example.demo.compent;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.StringReader;
import java.security.PublicKey;

public class XmlSignUtil {

    public static final String TEST_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC+DjjKuBYNScrhp7/+yf/vqsZbyWAMuvLZ76rv9wLA3XTCY1Cb3xxm7xvux/OzhzDlQdURRrEhjjsVdP9Bx0Ig5yfGyDec/4Z50dcRgqsu17QU/m2WXHEwFd4yBAGomWXzy/q7+8JJ9YoTraSDfKhfV3qWx7/vBzqk0QNzZ3AgFwSMiP5Azz8K2GhkHWxbVLlji9+/oRL5yj13a8P9bbtIrdUB/PZ+Fk3klxhBL9Vxii0YmX96nwVuJgkmgyiRCwmLhIQBFdL7H63cNgUi9UoCsFC6lV4ErEcHCUFsLqggukKPmTlr4KSnFvf9bZoIYC9I7XBWcM+KDGTSdxeGpXbvAgMBAAECggEABIJr4It7oncUxEPpr071rqcbq8PcbpDlADzKjoUK4K6gfZhDql8h2mNkA0dlReY4R8hHGPDXdRdd2YV8JQBoVkWF0RahEy2Q8EUFWFoEW8ksca8TxJSO7vgl3IPx0iFJpP47BcjUdFLKIutk0uXbTN/Tfc5hhHdkcdKvxUY4B9rZYSNI5+YdIGjJj/nBWNGBndIqKnLfs2AhpCqPkADC3xo73fxiM/77TPB7RiaFeK9jk80kLAwaZeOKArCIY7YSpnyhqvPi4kO9vxTeGGHONhN+mfAsMG94AY83vZ6qhUiZZuofslbUt7beOw2PTmzGi8jXDI8k6WVHKqNnwYt/+QKBgQD/fT8sG3oJyRpvfhc/gPM+ovYGL3MH+6B3Gtq32K+ZyAmtoHnfLFkaa1bOLUEklvr2xViFTU4NlUyEWNE1jGW1JFC9AhU3JUnVKmiG/BSURH8neYsNUQ/yKOmjRyTvQmELp2Qb7h46ZWM6kWOzrJtiXMvkoAjWwgO/4YOxEazJdQKBgQC+b3zZW/yh/cOTCAzfN8AR/luSq9TJ7D8CZxHVOpXRL3YP950SUWEfuyOby4ddhFDwU3b6+BhtOtClMrBKO2Q9wbPVjVD5plGlhhMaLiNJmbFL63hEazrMOE3UVbOY8EYBJyyeUguqiYyRJkUbiVubRsdbFLUbGai8qWg5BvTOUwKBgEFOUoeDvn4h2ZAGOwsQexzXquuJ1W2E9E99ncrAqKI2b8Lh8kUJoP0P0vCAwNYJgbzyVN4+FGWEdDqgOVnmuVjEH58wmRuvfF/wpydZ6Ci+GYKNnu2Yeur7aj1CQj6mSQghkYVSKIfkwqiF4WZcCJvr/HJENf4vOaYijvcD/ZbBAoGBAKoZFyFnIq7m5cvtAuJW/76SveSyiuyZkmZo/erB25PvmrsEZ043VlNrapD8KLsFNu6S/tGIzPiz8i28qu6DQjRPUnxLL6ruPjtlGKbn0ykomM7BUrl6Nhi3qf0hV7wh0cWx4g7AJh97oQz9a/j+pc56WBMo2eOM9cUeZDOb3Qp1AoGAQwB8ndcxkt4UCfC+87Rpy5yrxVuaVKDZfTIy4qpuLrHRqCIHvAl41EnJTE8MPIemXhdTmPeLmGWe7KjGDo0F0GPRqBMVKrLrCnM52FUx/Wp5P2h8ZVXvm7TR5/b3jhCb7qC0gjey+HunyEn78H9Vxty4XzcSlF0zu8uVUSgwIJ8=";

    /**
     * 网商银行测试环境公钥 RSA
     */
//    public static String bank_RSA_publicKey  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDOb4B1dnwONcW0RoJMa0IOq3O6jiqnTGLUpxEw2xJg+c7wsb6DBy5CAoR0w2ZjZ/BjKxGIQ+DoDg3NsHJeyuEjNF0/Ro/R5xVpFC5z4cBVSC2/gddz4a1EoGDJewML/Iv0yIw7ylB86++h23nRd079c5S9RZXurBfnLW2Srhqk2QIDAQAB";
    public static String bank_RSA_publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDOb4B1dnwONcW0RoJMa0IOq3O6jiqnTGLUpxEw2xJg+c7wsb6DBy5CAoR0w2ZjZ/BjKxGIQ+DoDg3NsHJeyuEjNF0/Ro/R5xVpFC5z4cBVSC2/gddz4a1EoGDJewML/Iv0yIw7ylB86++h23nRd079c5S9RZXurBfnLW2Srhqk2QIDAQAB";

    private static String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvg44yrgWDUnK4ae//sn/76rGW8lgDLry2e+q7/cCwN10wmNQm98cZu8b7sfzs4cw5UHVEUaxIY47FXT/QcdCIOcnxsg3nP+GedHXEYKrLte0FP5tllxxMBXeMgQBqJll88v6u/vCSfWKE62kg3yoX1d6lse/7wc6pNEDc2dwIBcEjIj+QM8/CthoZB1sW1S5Y4vfv6ES+co9d2vD/W27SK3VAfz2fhZN5JcYQS/VcYotGJl/ep8FbiYJJoMokQsJi4SEARXS+x+t3DYFIvVKArBQupVeBKxHBwlBbC6oILpCj5k5a+Ckpxb3/W2aCGAvSO1wVnDPigxk0ncXhqV27wIDAQAB";

    /**
     * 验来自网商得报文 - XML
     *
     * @throws Exception
     */
    public static boolean verify(String xmlContent) throws Exception {
        Document doc = parseDocumentByString(xmlContent);
        PublicKey publicKey = SignatureUtils.getPublicKey(bank_RSA_publicKey);

        return SignatureUtils.verifyXmlElement(publicKey, doc);
    }

    /**
     * 自签自验专用 - XML
     *
     * @throws Exception
     */
    public static boolean verifyFromYourSelf(String xmlContent) throws Exception {
        Document doc = parseDocumentByString(xmlContent);
        PublicKey publicKey = SignatureUtils.getPublicKey(PUBLIC_KEY);

        return SignatureUtils.verifyXmlElement(publicKey, doc);
    }


    //String Xml 转 Dom节点

    public static Document parseDocumentByString(String xmlContent) throws SAXException,
            IOException, Exception {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);// 否则无法识别namespace
        return factory.newDocumentBuilder().parse(new InputSource(new StringReader(xmlContent)));
    }


}
