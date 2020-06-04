package com.example.demo.compent;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.sun.org.apache.xml.internal.security.signature.XMLSignature;
import com.sun.org.apache.xml.internal.security.transforms.Transforms;
import com.sun.org.apache.xml.internal.security.utils.Constants;
import com.sun.org.apache.xml.internal.security.utils.XMLUtils;
import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.ByteArrayOutputStream;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class SignatureUtils {
    static {
        com.sun.org.apache.xml.internal.security.Init.init();
    }


    /**
     * 根据String获取私钥 - RSA
     *
     * @param encodePrivateKey
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static PrivateKey getPrivateKey(String encodePrivateKey)
            throws NoSuchAlgorithmException,
            InvalidKeySpecException {
        byte[] privateKeyBytes = Base64.decode(encodePrivateKey);
        KeyFactory kf = KeyFactory.getInstance("RSA"); // or "EC" or whatever
        PrivateKey privateKey = kf.generatePrivate(new PKCS8EncodedKeySpec(privateKeyBytes));
        return privateKey;
    }


    /**
     * 根据String获取公钥 - RSA
     *
     * @param encodePublicKey
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static PublicKey getPublicKey(String encodePublicKey) throws NoSuchAlgorithmException,
            InvalidKeySpecException {
        byte[] publicKeyKeyBytes = Base64.decode(encodePublicKey);
        KeyFactory kf = KeyFactory.getInstance("RSA"); // or "EC" or whatever
        PublicKey publicKey = kf.generatePublic(new X509EncodedKeySpec(publicKeyKeyBytes));
        return publicKey;
    }


    /**
     * 验证XML签名
     *
     * @param publicKey 公钥数据 X509编码
     * @param xmlDocument XML内容byte
     * @return 签名验证结果 boolean
     * @throws Exception the exception
     */
    public static boolean verifyXmlElement(PublicKey publicKey, Document xmlDocument)
            throws Exception {
        NodeList signatureNodes = xmlDocument.getElementsByTagNameNS(Constants.SignatureSpecNS,
                "Signature");
        if (signatureNodes == null || signatureNodes.getLength() < 1) {
            throw new Exception("Signature element not found!");
        }

        Element signElement = (Element) signatureNodes.item(0);
        if (signElement == null) {
            throw new Exception("Signature element  not found");
        }

        XMLSignature signature = new XMLSignature(signElement, "");
        return signature.checkSignatureValue(publicKey);
    }




    /**
     * XML签名
     *
     * @param privateKey 私钥数据，PKCS#8编码格式
     * @param xmlDocument XML文件内容， UTF-8编码
     * @param elementTagName 续签签名的Tag名称
     * @param algorithm 签名算法 {@link XMLSignature} 支持下列算法
     * <ul>
     * <li>XMLSignature.ALGO_ID_SIGNATURE_RSA</li>
     * <li>XMLSignature.ALGO_ID_SIGNATURE_RSA_SHA1</li>
     * <li>XMLSignature.ALGO_ID_SIGNATURE_RSA_SHA256</li>
     * <li>XMLSignature.ALGO_ID_SIGNATURE_RSA_SHA384</li>
     * <li>XMLSignature.ALGO_ID_SIGNATURE_RSA_SHA512</li>
     * </ul>
     * @param signatureAppendMode 签名节点的附加模式
     * {@link }
     * <ul>
     * <li>作为子节点： XmlSignatureAppendMode.AS_CHILDREN</li>
     * <li>作为兄弟节点：XmlSignatureAppendMode.AS_BROTHER</li>
     * </ul>
     * @return 签名后的文档 string
     * @throws Exception the exception
     */
    public static String signXmlElement(PrivateKey privateKey, Document xmlDocument,
                                        String elementTagName, String algorithm,
                                        int signatureAppendMode) throws Exception {
        XMLSignature xmlSignature = new XMLSignature(xmlDocument, xmlDocument.getDocumentURI(),
                algorithm);

        NodeList nodeList = xmlDocument.getElementsByTagName(elementTagName);
        if (nodeList == null || nodeList.getLength() - 1 < 0) {
            throw new Exception("Document element with tag name " + elementTagName + " not fount");
        }

        Node elementNode = nodeList.item(0);
        if (elementNode == null) {
            throw new Exception("Document element with tag name " + elementTagName + " not fount");
        }

        elementNode.appendChild(xmlSignature.getElement());
        if (signatureAppendMode == XmlSignatureAppendMode.AS_CHILDREN) {
            elementNode.appendChild(xmlSignature.getElement());
        } else if (signatureAppendMode == XmlSignatureAppendMode.AS_BROTHER) {
            elementNode.getParentNode().appendChild(xmlSignature.getElement());
        } else {
            throw new IllegalArgumentException("Illegal Append Mode");
        }

        Transforms transforms = new Transforms(xmlDocument);
        transforms.addTransform(Transforms.TRANSFORM_ENVELOPED_SIGNATURE);
        xmlSignature.addDocument("", transforms, Constants.ALGO_ID_DIGEST_SHA1);

        xmlSignature.sign(privateKey);

        ByteArrayOutputStream os = null;
        try {
            os = new ByteArrayOutputStream();
            XMLUtils.outputDOM(xmlDocument, os);
            return os.toString("UTF-8");
        } finally {
            IOUtils.closeQuietly(os);
        }
    }




}
