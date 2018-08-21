/**
 * hpx.com Inc.
 * Copyright (c) 2012-YEARAll Rights Reserved.
 */
package cn.hpx.exchange.util;



import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**  
 * @author qlj  
 * @version $Id: RSAUtils.java, v 0.1 2018-05-14 13:38 legend Exp $$  
 */
public class RSAUtils {
    /**
     * 加密算法RSA
     */
    public static final String KEY_ALGORITHM = "RSA";

    /**
     * 签名算法
     */
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    /**
     * 获取公钥的key
     */
    private static final String PUBLIC_KEY = "RSAPublicKey";

    /**
     * 获取私钥的key
     */
    private static final String PRIVATE_KEY = "RSAPrivateKey";

    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    private static final  BASE64Decoder decoder= new BASE64Decoder();

    private static final  BASE64Encoder encoder= new BASE64Encoder();

    /**
     * <p>
     * 生成密钥对(公钥和私钥)
     * </p>
     *
     * @return
     * @throws Exception
     */
    public static Map<String, Object> genKeyPair() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(1024);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        Map<String, Object> keyMap = new HashMap<String, Object>(2);
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }

    /**
     * <p>
     * 用私钥对信息生成数字签名
     * </p>
     *
     * @param data 已加密数据
     * @param privateKey 私钥(BASE64编码)
     *
     * @return
     * @throws Exception
     */
    public static String sign(byte[] data, String privateKey) throws Exception {
        byte[] keyBytes = new BASE64Decoder().decodeBuffer(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(privateK);
        signature.update(data);
        return new BASE64Encoder().encode(signature.sign());
    }

    /**
     * <p>
     * 校验数字签名
     * </p>
     *
     * @param data 已加密数据
     * @param publicKey 公钥(BASE64编码)
     * @param sign 数字签名
     *
     * @return
     * @throws Exception
     *
     */
    public static boolean verify(byte[] data, String publicKey, String sign)
            throws Exception {
        byte[] keyBytes = decoder.decodeBuffer(publicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey publicK = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(publicK);
        signature.update(data);
        return signature.verify(decoder.decodeBuffer(sign));
    }

    /**
     * <P>
     * 私钥解密
     * </p>
     *
     * @param encryptedData 已加密数据
     * @param privateKey 私钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] encryptedData, String privateKey)
            throws Exception {
        byte[] keyBytes = decoder.decodeBuffer(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateK);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        // 对数据分段解密
        catDataDo(encryptedData, cipher, inputLen, out);
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }

    /**
     * 对数据进行分段处理
     * @param encryptedData
     * @param cipher
     * @param inputLen
     * @param out
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    private static void catDataDo(byte[] encryptedData, Cipher cipher, int inputLen, ByteArrayOutputStream out) throws IllegalBlockSizeException, BadPaddingException {
        int i = 0;
        int offSet = 0;
        byte[] cache;
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
    }

    /**
     * <p>
     * 公钥解密
     * </p>
     *
     * @param encryptedData 已加密数据
     * @param publicKey 公钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPublicKey(byte[] encryptedData, String publicKey)
            throws Exception {
        byte[] keyBytes = decoder.decodeBuffer(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicK);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        // 对数据分段解密
        catDataDo(encryptedData, cipher, inputLen, out);
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }

    /**
     * <p>
     * 公钥加密
     * </p>
     *
     * @param data 源数据
     * @param publicKey 公钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte[] data, String publicKey)
            throws Exception {
        byte[] keyBytes = decoder.decodeBuffer(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        // 对数据分段加密
        catDataDo(data, cipher, inputLen, out);
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }

    /**
     * <p>
     * 私钥加密
     * </p>
     *
     * @param data 源数据
     * @param privateKey 私钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPrivateKey(byte[] data, String privateKey)
            throws Exception {
        byte[] keyBytes = decoder.decodeBuffer(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        // 对数据分段加密
        catDataDo(data, cipher, inputLen, out);
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }

    /**
     * <p>
     * 获取私钥
     * </p>
     *
     * @param keyMap 密钥对
     * @return
     * @throws Exception
     */
    public static String getPrivateKey(Map<String, Object> keyMap)
            throws Exception {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return encoder.encode(key.getEncoded());
    }

    /**
     * <p>
     * 获取公钥
     * </p>
     *
     * @param keyMap 密钥对
     * @return
     * @throws Exception
     */
    public static String getPublicKey(Map<String, Object> keyMap)
            throws Exception {
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        return encoder.encode(key.getEncoded());
    }

    public static void main(String[] args){

        try {
//            Map<String, Object> keyMap = RSAUtils.genKeyPair();
//            String  publicKey = RSAUtils.getPublicKey(keyMap);
//            String privateKey = RSAUtils.getPrivateKey(keyMap);
//            System.err.println("公钥: \n\r" + publicKey);
//            System.err.println("私钥： \n\r" + privateKey);
//
//
//            String source = "ssssssssssss";
//            byte[] data = source.getBytes();
//            byte[] encodedData = encryptByPublicKey(data, publicKey);
//
//            System.out.println("加密后文字：\r\n" + new String(encodedData));
//
//            String encodedStr=encoder.encode(encodedData);

            String encodedStr="GbWaTLUNtmBVe+0K7ojvpnzMYmvsYtRFSb9Etc6X5GlcLtbgAV5WQc1PQhDflRB9grB0pxtujVEm\n" +
                    "pOaF0ckEqBm3M6qoI1sigwdi5rAIO5XV0LrBccKNIuWoua30ZaIbAAK6DoU2hRbiuSP+jxFjENXT\n" +
                    "AkBYZxJPzuA1CJD/BvE=";

            System.out.println("base64后文字：\r\n" + new String(encodedStr));

            byte[] encoded=encodedStr.getBytes();

            String privateKey="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJnLub/4va20SEDIRQ17o8iR5dh4\n" +
                    "U26HJRDm8HjzPOKNKISTDz86cBkilndDfqo8l0ZpU3YuY5gR7MAZLVrTkYUIluo+o6ZAm3n37Bfk\n" +
                    "ur0ES26cak4ZFtfHhERNrCZxtDbhmRe465Z0NfMOpLW09IICxuXCvWWNs2Uev5pzLlDrAgMBAAEC\n" +
                    "gYAFGXEuQVQgt1KR+1H20XurwdKqK8swZgC/n7inegExzdZjFvExeoGPA8uTZsSDZoDpj8J5r64T\n" +
                    "s7xy3iLX2R4BCw46Mz1EWtSG7V6R9eDKMCmX48bJxiMR6dz5uneYGLBnK6cDnxPVHbUJ5Fx/MZdX\n" +
                    "JzqGXv4ETKaMDoctMoGFIQJBAM2M0RANxVMp7uvuL4C6q1xPm+tnZW2UzQiuS5YLFJumiZ9NvBMy\n" +
                    "nWwkTHY5+OINQfEfgXJBRTyryV5OpR7BGBECQQC/ixHSo5ejeRyNwmAIQZAwbsyFk94Tby++2VF+\n" +
                    "VfVpI3zzVxtQBYaxV8tsMUyKgWALzSNXJ4Ex8jOZazfPznU7AkB4Gu4V6VYBsCDxLu4G1AxJFiNP\n" +
                    "O81iaG4dwmpcNAFIqdkEgpGAzjR5+gPLmTg6LfD4CabDsivYtbAmMOH+xGlhAkEAr8CFfAdLjl4E\n" +
                    "izZ9M1YlXats0mxZau+smauCETACXJSJhCa7EvPfIT5zkzSxDK2lWW7DLKc87n1UR0OQFIdWHQJB\n" +
                    "AIw+RcFQ02eAmkB+9/fS+kQEFWqgqXsoVc+nTEgmg4aRmjWy750A6yS8qTYfYNAbosB8qFjGpn3B\n" +
                    "LIB9xfPGM6s=";
            byte[] decoded = decoder.decodeBuffer(new String(encoded,"UTF-8"));
            byte[] decodedData = decryptByPrivateKey(decoded, privateKey);
            String target = new String(decodedData);
            System.out.println("解密后文字: \r\n" + target);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}