package com.barry.cloud.platform.common.encrypt.rsa;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.buf.HexUtils;

import java.util.Map;

/**
 * @author Tongshan.Han@partner.bmw.com
 * @Description:
 * @date 2018/9/3 14:18
 */
@Slf4j
public class RSATester {

    static String publicKey;
    static String privateKey;

    static {
        try {
            Map<String, Object> keyMap = RSAUtils.genKeyPair();
            publicKey = RSAUtils.getPublicKey(keyMap);
            privateKey = RSAUtils.getPrivateKey(keyMap);
            log.info("公钥: \n\r" + publicKey);
            log.info("私钥： \n\r" + privateKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        //test();
        testSign();
    }

    static void test() throws Exception {
        log.info("公钥加密——私钥解密");
        String source = "这是一行没有任何意义的文字，你看完了等于没看，不是吗？";
        log.info("\r加密前文字：\r\n" + source);
        byte[] data = source.getBytes();
        byte[] encodedData = RSAUtils.encryptByPublicKey(data, publicKey);
        log.info("加密后文字：\r\n" + new String(encodedData));
        byte[] decodedData = RSAUtils.decryptByPrivateKey(encodedData, privateKey);
        String target = new String(decodedData);
        log.info("解密后文字: \r\n" + target);
    }

    static void testSign() throws Exception {
        log.info("私钥加密——公钥解密");
        String source = "这是一行测试RSA数字签名的无意义文字";
        log.info("原文字：\r\n" + source);
        byte[] data = source.getBytes();
        byte[] encodedData = RSAUtils.encryptByPrivateKey(data, privateKey);
        //log.info("加密后：\r\n" + new String(encodedData));
        String hexstr = HexUtils.toHexString(encodedData);
        log.info("加密后：\r\n" + hexstr);
        //byte[] decodedData = RSAUtils.decryptByPublicKey(encodedData, publicKey);
        byte[] decodedData = RSAUtils.decryptByPublicKey(HexUtils.fromHexString(hexstr), publicKey);
        String target = new String(decodedData);
        log.info("解密后: \r\n" + target);
        log.info("私钥签名——公钥验证签名");
        String sign = RSAUtils.sign(encodedData, privateKey);
        log.info("签名:\r" + sign);
        boolean status = RSAUtils.verify(encodedData, publicKey, sign);
        log.info("验证结果:\r" + status);
    }

}

