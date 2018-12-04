package com.zhou.autopullcode.utils;

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
    public static String getSignature(String data) throws Exception {
        byte[] key = "test".getBytes();
        SecretKeySpec signingKey = new SecretKeySpec(key, HMAC_SHA1);
        Mac mac = Mac.getInstance(HMAC_SHA1);
        mac.init(signingKey);
        byte[] rawHmac = mac.doFinal(data.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : rawHmac) {
            sb.append(byteToHexString(b));
        }
        return sb.toString();
    }

    private static String byteToHexString(byte ib) {
        char[] Digit = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
        };
        char[] ob = new char[2];
        ob[0] = Digit[(ib >>> 4) & 0X0f];
        ob[1] = Digit[ib & 0X0F];
        String s = new String(ob);
        return s;
    }

    public static void main(String[] args) throws Exception {
        String s = HMACSHA1.getSignature("{\n" +
                "  \"zen\": \"Coding！ 让开发更简单\",\n" +
                "  \"hook_id\": 95592,\n" +
                "  \"hook\": {\n" +
                "    \"id\": 95592,\n" +
                "    \"name\": \"web\",\n" +
                "    \"type\": \"Repository\",\n" +
                "    \"active\": true,\n" +
                "    \"events\": [\n" +
                "      \"push\",\n" +
                "      \"merge request\"\n" +
                "    ],\n" +
                "    \"config\": {\n" +
                "      \"content_type\": \"json\",\n" +
                "      \"secret\": \"********\",\n" +
                "      \"url\": \"http://47.106.219.31/other\"\n" +
                "    },\n" +
                "    \"created_at\": 1543917543000,\n" +
                "    \"updated_at\": 1543923875000\n" +
                "  },\n" +
                "  \"sender\": {\n" +
                "    \"id\": 1950302,\n" +
                "    \"login\": \"zhou_2018\",\n" +
                "    \"avatar_url\": \"https://coding.net/static/fruit_avatar/Fruit-13.png\",\n" +
                "    \"url\": \"https://coding.net/api/user/key/zhou_2018\",\n" +
                "    \"html_url\": \"https://coding.net/u/zhou_2018\",\n" +
                "    \"name\": \"zhou_2018\",\n" +
                "    \"name_pinyin\": \"\"\n" +
                "  },\n" +
                "  \"repository\": {\n" +
                "    \"id\": 3938796,\n" +
                "    \"name\": \"geneworks\",\n" +
                "    \"full_name\": \"mework/geneworks\",\n" +
                "    \"owner\": {\n" +
                "      \"id\": 184440,\n" +
                "      \"login\": \"mework\",\n" +
                "      \"avatar_url\": \"https://dev.tencent.com/static/fruit_avatar/Fruit-14.png\",\n" +
                "      \"url\": \"https://qcloud.coding.net/api/user/key/mework\",\n" +
                "      \"html_url\": \"https://qcloud.coding.net/u/mework\",\n" +
                "      \"name\": \"mework\",\n" +
                "      \"name_pinyin\": \"|mework|mework\"\n" +
                "    },\n" +
                "    \"private\": true,\n" +
                "    \"html_url\": \"<a href='https://qcloud.coding.net/u/mework/p/geneworks' target='_blank'>geneworks</a>\",\n" +
                "    \"description\": \"geneworks\",\n" +
                "    \"fork\": false,\n" +
                "    \"url\": \"https://qcloud.coding.net/api/user/mework/project/geneworks\",\n" +
                "    \"created_at\": 1543643522000,\n" +
                "    \"updated_at\": 1543643522000,\n" +
                "    \"clone_url\": \"https://git.dev.tencent.com/mework/geneworks.git\",\n" +
                "    \"ssh_url\": \"git@git.dev.tencent.com:mework/geneworks.git\",\n" +
                "    \"default_branch\": \"master\"\n" +
                "  }\n" +
                "}");
        System.out.println(s);
        if (s.equals("2319fcb6d60dd30119c1e0ff9e25e1a6cc86d72f")) {
            System.out.println(true);
        }
    }
}