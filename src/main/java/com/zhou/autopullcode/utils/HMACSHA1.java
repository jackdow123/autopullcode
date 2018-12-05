package com.zhou.autopullcode.utils;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


public class HMACSHA1 {

    private static final String HMAC_SHA1 = "HmacSHA1";

    /**
     * 生成签名数据
     *
     * @param data 待加密的数据
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     */
    public static String getSignature(String secret, String data) throws Exception {

        Mac sha1_HMAC = Mac.getInstance(HMAC_SHA1);
        SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes("UTF-8"), HMAC_SHA1);
        sha1_HMAC.init(secret_key);

        return Hex.encodeHexString(sha1_HMAC.doFinal(data.getBytes("UTF-8")));
    }
}