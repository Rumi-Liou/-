package com.practice.springsecondphrasepractice.service;

import com.practice.springsecondphrasepractice.controller.dto.request.ASE256Request;
import com.practice.springsecondphrasepractice.controller.dto.response.ASE256Response;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import static org.aspectj.apache.bcel.ConstantsInitializer.initialize;

@Service
public class ASE256Service {
    //ECB加密
    public ASE256Response ECBEncrypt(String sKey, ASE256Request request) throws Exception {
        initialize();
        if (sKey == null) {
            System.out.print("Key为空null");
            return null;
        }
        // 判断Key是否为16位
        if (sKey.length() != 32) {
            System.out.print("Key长度不是32位");
            return null;
        }
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        final SecretKeySpec secretKey = new SecretKeySpec(sKey.getBytes(), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        final String encryptedString = Base64.encodeBase64String(cipher.doFinal(request.getRequest().getBytes()));
        ASE256Response response = new ASE256Response();
        response.setResponse(encryptedString);
        return response;
    }


    // ECB解密
    public ASE256Response ECBDecrypt(String sKey, ASE256Request request) throws Exception {
        ASE256Response response = new ASE256Response();
        // 判断Key是否正确
//        if (sKey == null) {
//
//            return new ASE256Response("key為空");
//        }
//        // 判断Key是否为16位
//        if (sKey.length() != 32) {
//            return new ASE256Response("Key長度不是16位");
//        }
        try {
            String encrypted = request.getRequest().replace(' ', '+');
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            final SecretKeySpec secretKey = new SecretKeySpec(sKey.getBytes(), "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            final String decryptedString = new String(cipher.doFinal(Base64.decodeBase64(encrypted)));
            response.setResponse(decryptedString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    public ASE256Response CBCEncrypt(String sKey, ASE256Request request) throws Exception {
        final byte[] KEY_VI = "2021121018304400".getBytes();
        ASE256Response response = new ASE256Response();

//            SecretKey secretKey = new SecretKeySpec(sKey.getBytes(), "AES");
//            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
////            cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, secretKey, new javax.crypto.spec.IvParameterSpec(KEY_VI));
//            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(KEY_VI));
//            // 獲取加密內容的位元組陣列(這裡要設定為utf-8)不然內容中如果有中文和英文混合中文就會解密為亂碼
//            byte[] byteEncode = request.getRequest().getBytes(StandardCharsets.UTF_8);
//
//            // 根據密碼器的初始化方式加密
//            byte[] byteAES = cipher.doFinal(byteEncode);
//
//            // 將加密後的資料轉換為字串
//            response.setResponse(new Base64().encodeToString(byteAES));
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        final SecretKeySpec secretKey = new SecretKeySpec(sKey.getBytes(), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(KEY_VI));
        final String encryptedString = Base64.encodeBase64String(cipher.doFinal(request.getRequest().getBytes()));
//        ASE256Response response = new ASE256Response();
        response.setResponse(encryptedString);
        return response;
//        return response;
    }

    public ASE256Response getCBCDecrypt(String sKey, ASE256Request request) {
        ASE256Response response = new ASE256Response();
        final byte[] KEY_VI = "2021121018304400".getBytes();
        try {
            String encrypted = request.getRequest().replace(' ', '+');
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            final SecretKeySpec secretKey = new SecretKeySpec(sKey.getBytes(), "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(KEY_VI));
            final String decryptedString = new String(cipher.doFinal(Base64.decodeBase64(encrypted)));
            response.setResponse(decryptedString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

}
