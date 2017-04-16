package com.example.util;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by WangShiXiang on 2017/4/9.
 * 短信发送类
 */
public class SendShortMessage {

    public static int send(String text,String[] phone){
        int count=0;
        try {
            URL url=new URL("http://utf8.sms.webchinese.cn");
            HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded;charset=utf-8");
            httpURLConnection.connect();

            DataOutputStream outputStream=new DataOutputStream(httpURLConnection.getOutputStream());
            StringBuffer stringBuffer=new StringBuffer();
            for (String str:phone){
                stringBuffer.append(str).append(",");
            }
            String str=stringBuffer.toString();
            str=str.substring(0,str.length()-1);
            str=URLEncoder.encode(str,"utf-8");
            //http://utf8.sms.webchinese.cn/?Uid=本站用户名&Key=接口安全秘钥&smsMob=手机号码&smsText=验证码:8888
            String content="Uid="+ URLEncoder.encode("17862901361abc","utf-8")+"&Key="+URLEncoder.encode("38f54c3f7658482f3126","utf-8")+"&smsMob="+str+"&smsText="+URLEncoder.encode(text,"utf-8");
            outputStream.writeBytes(content);
            outputStream.flush();
            outputStream.close();

            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String line;
            StringBuffer sb=new StringBuffer();
            while ((line=bufferedReader.readLine())!=null){
                System.out.println(line);
                sb.append(line);
            }
            bufferedReader.close();
            httpURLConnection.disconnect();
            try {
                count=new Integer(sb.toString());
            }catch (Exception e){
                count=-1;
                System.out.println(sb.toString());
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        return count;
    }

//    public static void main(String[] args){
//        String[] phone=new String[]{"17862901361","17862901383"};
//        String text="验证码1020";
//        send(text,phone);
//    }
}
