/**
 * hpx.com Inc.
 * Copyright (c) 2012-YEARAll Rights Reserved.
 */
package cn.hpx.exchange.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**  
 * jwt认证相关类
 * @author qlj  
 * @version $Id: JWTUtil.java, v 0.1 2018-05-16 17:36 legend Exp $$  
 */
public class JWTUtil {

    /**
     * 日志对象
     */
    private static final Logger logger= Logger.getLogger(JWTUtil.class);

    /**
     * jwt加密 生成token
     * @param source  源
     * @param pubicKey  公钥
     * @return
     */
    public static String jwtEncrypt(String source,String pubicKey) throws Exception {
        if(StringUtils.isEmpty(source)){
            return "";
        }
       byte[] bytes = RSAUtils.encryptByPublicKey(source.getBytes(), pubicKey);
       return new String(bytes);
    }

    /**
     * 指定公钥生成token
     * @param object
     * @param publicKey
     * @return
     */
    public String getToken(JSONObject object,String publicKey) throws Exception {
        String token = jwtEncrypt(object.toJSONString(), publicKey);
        object.put("token", token);
        return new BASE64Encoder().encode(object.toJSONString().getBytes());
    }

    /**
     *  解析token
     * @param source
     * @param privateKey
     * @return
     */
    public static String jwtDecrypt(String source,String privateKey){
        if(StringUtils.isEmpty(source)){
            return "";
        }
        try {
            byte[] bytes = RSAUtils.decryptByPrivateKey(source.getBytes(), privateKey);
            return new String(bytes);
        }catch (Exception e){
            logger.error("jwt  token解析失败");
        }
        return "";
    }


    /**
     * 解析token串，并返回token对象
     * @param token
     * @param privateKey
     * @return
     * @throws IOException
     */
    public static JSONObject decryptToken(String token,String privateKey) throws IOException {
        String tokenStr=new String(new BASE64Decoder().decodeBuffer(token));
        JSONObject tokenO=JSONObject.parseObject(tokenStr);
        if(!tokenO.isEmpty()){
            String decryptToken = jwtDecrypt(tokenO.getString("token"), privateKey);
            tokenO.put("token",JSONObject.parseObject(decryptToken));
        }
        return tokenO;
    }
}