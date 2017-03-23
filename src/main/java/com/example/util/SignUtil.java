package com.example.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Created by WangShiXiang on 2017/3/8.
 * 对微信接入进行验证
 */
public class SignUtil {
    private static String token="123456";
    public static boolean checkSignature(String signature,String timestamp,String nonce){
        String[] arr=new String[]{token,timestamp,nonce};
        Arrays.sort(arr);
        StringBuilder sb=new StringBuilder();
        String result=null;
        for(String str:arr){
            sb.append(str);
        }
        String strList=sb.toString();
        MessageDigest messageDigest=null;
        try {
            messageDigest=MessageDigest.getInstance("SHA-1");
            //将三个字符串进行sha-1加密
            messageDigest.update(strList.getBytes());
            StringBuffer tmp=new StringBuffer();
            for (byte b:messageDigest.digest()){
                tmp.append(String.format("%02X",b));
            }
            result=tmp.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return signature.equalsIgnoreCase(result)? true:false;
    }
}
