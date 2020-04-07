package cn.xhzren.drama.netty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class TestRsa {

    static Logger logger = LoggerFactory.getLogger(TestRsa.class);
    public static void main (String[] args) throws Exception {
        //前端登录--返回公钥
        Map<String, String> keyMap = RSAUtils.createKeys(512);
        String  publicKey = keyMap.get("publicKey");
        String  privateKey = keyMap.get("privateKey");
        String str = "{\"uid\":\"1223334\",\"pwd\":\"asd\"}";
        //公钥加密
        String encodedData = RSAUtils.publicEncrypt(str, RSAUtils.getPublicKey(publicKey));
        System.out.println("密文：\r\n" + encodedData);
        //私钥解密
        String decodedData = RSAUtils.privateDecrypt(encodedData, RSAUtils.getPrivateKey(privateKey));
        System.out.println("解密后文字: \r\n" + decodedData);
    }
}