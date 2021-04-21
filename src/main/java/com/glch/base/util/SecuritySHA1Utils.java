package com.glch.base.util;

import sun.misc.BASE64Encoder;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Base64;

public class SecuritySHA1Utils {

    /**
     * @Comment SHA1实现
     * @Author Ron
     * @Date 2017年9月13日 下午3:30:36
     * @return
     */
    public static byte[] shaEncode(String inStr) throws Exception {
        MessageDigest sha = null;
        try {
            sha = MessageDigest.getInstance("SHA1");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return null;
        }

        byte[] byteArray = inStr.getBytes("UTF-8");
        byte[] md5Bytes = sha.digest(byteArray);
        /*StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();*/
        return md5Bytes;
    }

    public static void main(String args[]) throws Exception {
//        String str = new String("20200709152357234800fe00bc8fe4dddod4632f1110db68d" + "fEqNCco3Yq9h5ZUglD3CZJT4lBs=");
//        String str = new String("20200709152357234800fe00bc8fe4dddod4632f1110db68d" + "123456");
        String str = new String("123456");
        System.out.println("原始：" + str);
        System.out.println("SHA后：" + shaEncode(str));
        byte[] bytes = shaEncode(str);
        //Base64 加密
        BASE64Encoder encoder = new BASE64Encoder();
        String encoded = encoder.encode(bytes);
        System.out.println("密码加密后：" + encoded);

        String tokenAndPwd = encoded + "20200709152942415e1233e7fb31o47e9o829f51o99641045";

        byte[] bytes2 = shaEncode(tokenAndPwd);
        //Base64 加密
        BASE64Encoder encoder2 = new BASE64Encoder();
        String encoded2 = encoder2.encode(bytes2);
        System.out.println("最终加密后：" + encoded2);
    }



}
