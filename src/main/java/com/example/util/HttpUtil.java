package com.example.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by WangShiXiang on 2017/3/9.
 * http请求的一些类
 */
public class HttpUtil {
    /**
     * 发送一个简单的get请求
     * @param path
     * @return
     */
    public static String  getDataByInternet(String path){

        try {
            URL url=new URL(path.trim());
            //打开连接
            HttpURLConnection urlConnection=(HttpURLConnection)url.openConnection();
            if(200==urlConnection.getResponseCode()){
                //得到输入流
                InputStream in=urlConnection.getInputStream();
                ByteArrayOutputStream baos=new ByteArrayOutputStream();
                byte[] buffer=new byte[1024];
                int len=0;
                while(-1 !=(len=in.read(buffer))){
                    baos.write(buffer,0,len);
                    baos.flush();
                }
                return baos.toString("utf-8");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;

    }

    /**
     * 发送一个简单的post请求
     * @param path
     * @param post
     * @return
     */
    public static String postDownloadJson(String path,String post){
        URL url=null;
        try {
            url=new URL(path);
            HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            //发送post请求设置如下两行
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            //获取输出流
            PrintWriter printWriter=new PrintWriter(httpURLConnection.getOutputStream());
            //发出请求的数据
            printWriter.write(post);
            //flush输出缓冲流
            printWriter.flush();
            //开始获取数据
            BufferedInputStream bis=new BufferedInputStream(httpURLConnection.getInputStream());
            ByteArrayOutputStream bos=new ByteArrayOutputStream();
            int len;
            byte[] arr=new byte[1024];
            while ((len=bis.read(arr))!=-1){
                bos.write(arr,0,len);
                bos.flush();
            }
            bos.close();
            return bos.toString("utf-8");
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 根据内容类型判断文件扩展名
     * @param contentType 内容类型
     * @return
     */
    public static String getFileEndWitsh(String contentType) {
        String fileEndWitsh = "";
        if ("image/jpeg".equals(contentType))
            fileEndWitsh = ".jpg";
        else if ("audio/mpeg".equals(contentType))
            fileEndWitsh = ".mp3";
        else if ("audio/amr".equals(contentType))
            fileEndWitsh = ".amr";
        else if ("video/mp4".equals(contentType))
            fileEndWitsh = ".mp4";
        else if ("video/mpeg4".equals(contentType))
            fileEndWitsh = ".mp4";
        return fileEndWitsh;
    }
}
