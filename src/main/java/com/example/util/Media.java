package com.example.util;

import com.example.entity.media.WXMedia;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Created by WangShiXiang on 2017/3/12.
 * 微信多媒体文件的上传下载
 */
public class Media
{
    /**
     * 用来上传多媒体文件
     * @param accessToken 微信所有请求的唯一凭证
     * @param type 文件的类型
     * @param mediaFileUrl 资源所在的url
     * @return
     */
    public static WXMedia uploadMedia(String accessToken, String type, String mediaFileUrl){
        WXMedia wxMedia=null;
        String uploadMediaUrl="https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
        uploadMediaUrl=uploadMediaUrl.replace("ACCESS_TOKEN",accessToken).replace("TYPE",type);
        //定义数据分隔符
        String boundary="------------7da2e536604c8";
        try {
            URL uploadUrl=new URL(uploadMediaUrl);
            HttpURLConnection uploadConn=(HttpURLConnection)uploadUrl.openConnection();
            uploadConn.setDoOutput(true);
            uploadConn.setDoInput(true);
            uploadConn.setRequestMethod("POST");
            //设置请求头
            uploadConn.setRequestProperty("Content-Type","multipart/form-data;boundary="+boundary);
            //获取文件上传的输出流
            OutputStream outputStream=uploadConn.getOutputStream();

            URL mediaUrl=new URL(mediaFileUrl);
            HttpURLConnection mediaConn=(HttpURLConnection)mediaUrl.openConnection();
            mediaConn.setDoOutput(true);
            mediaConn.setRequestMethod("GET");
            //从请求头中获取内容类型
            String contentType=mediaConn.getHeaderField("Content-Type");
            //根据内容类型判断文件扩展名
            String fileExt=HttpUtil.getFileEndWitsh(contentType);
            //请求体开始
            outputStream.write(("--"+boundary+"\r\n").getBytes());
            outputStream.write(String.format("Content-Disposition: form-data;name=\"media\";filename=\"file1%s\"\r\n",fileExt).getBytes());
            outputStream.write(String.format("Content-Type: %s\r\n\r\n",contentType).getBytes());
            //获取媒体文件的输入流（读文件）
            BufferedInputStream bis=new BufferedInputStream(mediaConn.getInputStream());
            byte[] buf=new byte[1024*8];
            int size=0;
            while ((size=bis.read(buf))!=-1){
                outputStream.write(buf,0,size);
            }
            outputStream.write(("\r\n--"+boundary+"--\r\n").getBytes());
            outputStream.close();
            bis.close();
            mediaConn.disconnect();
            //获取文件上传的输入流（从微信服务器读取）
            InputStream inputStream=uploadConn.getInputStream();
            InputStreamReader inputStreamReader=new InputStreamReader(inputStream,"utf-8");
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            StringBuffer buffer=new StringBuffer();
            String str=null;
            while ((str=bufferedReader.readLine())!=null){
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            uploadConn.disconnect();

            System.out.print(buffer.toString());
            Map map=JsonUtil.jsonToMap(buffer.toString());
            if (map.get("type")!=null){
                wxMedia=new WXMedia(map.get("type").toString(),map.get("media_id").toString(),map.get("created_at").toString());
            }else {
                return null;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return wxMedia;
    }

    /**
     * 用来下载多媒体文件
     * @param accessToken
     * @param mediaId
     * @param savePath
     * @return
     */
    public static String downloadMedia(String accessToken,String mediaId,String savePath){
        String filePath=null;
        String requestUrl="https://api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
        requestUrl=requestUrl.replace("ACCESS_TOKEN",accessToken).replace("MEDIA_ID",mediaId);
        try {
            URL url=new URL(requestUrl);
            HttpURLConnection conn=(HttpURLConnection)url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            if(!savePath.endsWith("/")){
                savePath+="/";
            }
            //根据内容的类型获取扩展名
            String fileExt=HttpUtil.getFileEndWitsh(conn.getHeaderField("Content-Type"));
            System.out.println(conn.getHeaderField("Content-Type"));
            filePath=savePath+mediaId+fileExt;
            BufferedInputStream bufferedInputStream=new BufferedInputStream(conn.getInputStream());
            FileOutputStream outputStream=new FileOutputStream(filePath);
            byte[] buf=new byte[1024*8];
            int size=0;
            while ((size=bufferedInputStream.read(buf))!=-1){
                outputStream.write(buf,0,size);
            }
            outputStream.close();
            bufferedInputStream.close();

            conn.disconnect();

        }catch (Exception e){
            e.printStackTrace();
        }
        return filePath;
    }

    /**
     * 用来上传其他类型的永久文件
     * @param accessToken
     * @param type
     * @param mediaFileUrl
     * @return
     */
    public static String uploadForeverMedia(String accessToken,String type,String mediaFileUrl){
        String ForeverMedia="";
        String uploadMediaUrl="https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN&type=TYPE";
        uploadMediaUrl=uploadMediaUrl.replace("ACCESS_TOKEN",accessToken).replace("TYPE",type);
        //定义数据分割字符串
        String boundary="\"------------7da2e536604c8";
        try {
            URL uploadUrl=new URL(uploadMediaUrl);
            HttpURLConnection uploadContent=(HttpURLConnection) uploadUrl.openConnection();
            uploadContent.setDoOutput(true);
            uploadContent.setDoInput(true);
            uploadContent.setRequestMethod("POST");
            //设置请求头Content-Type
            uploadContent.setRequestProperty("Content-Type","multipart/form-data;boundary="+boundary);
            //获取文件上传的输出流（往微信服务器写数据）
            OutputStream outputStream=uploadContent.getOutputStream();

            URL mediaUrl=new URL(mediaFileUrl);
            HttpURLConnection mediaConn=(HttpURLConnection)mediaUrl.openConnection();
            mediaConn.setDoOutput(true);
            mediaConn.setRequestMethod("GET");
            //从请求头中获取内容类型
            String contentType=mediaConn.getHeaderField("Content-Type");
            //根据内容类型判断文件扩展名
            String fileExt=HttpUtil.getFileEndWitsh(contentType);
            //请求体开始
            outputStream.write(("--"+boundary+"\r\n").getBytes());
            outputStream.write(String.format("Content-Disposition: form-data;name=\"media\";filename=\"file1%s\"\r\n",fileExt).getBytes());
            outputStream.write(String.format("Content-Type: %s\r\n\r\n",contentType).getBytes());
            //获取每日文件的输入流（读取文件）
            BufferedInputStream bis=new BufferedInputStream(mediaConn.getInputStream());
            byte[] buf=new byte[1024*8];
            int size=0;
            while ((size=bis.read(buf))!=-1){
                //将媒体文件写入到输出流，即发送到微信的服务器
                outputStream.write(buf,0,size);
            }
            //请求体结束
            outputStream.write(("\r\n--"+boundary+"--\r\n").getBytes());
            outputStream.close();
            bis.close();
            mediaConn.disconnect();
            //获取文件上传输入流（从微信服务器读取数据）
            InputStream inputStream=uploadContent.getInputStream();
            InputStreamReader inputStreamReader=new InputStreamReader(inputStream,"utf-8");
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            StringBuffer buffer=new StringBuffer();
            String str=null;
            while ((str=bufferedReader.readLine())!=null){
                buffer.append(str);
            }
            bufferedReader.close();
            inputStream.close();

            inputStream.close();
            inputStream=null;
            uploadContent.disconnect();

            Map map=JsonUtil.jsonToMap(buffer.toString());
            if(map.get("media_id")!=null){
                ForeverMedia=buffer.toString();
            }


        }catch (Exception e){
            e.printStackTrace();
        }
        return ForeverMedia;
    }

    /**
     * 用来上传图文消息里的图片
     * @param accessToken
     * @param mediaFileUrl
     * @return
     */
    public static String uploadImg(String accessToken,String mediaFileUrl){
        String uploadMediaUrl="https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=ACCESS_TOKEN";
        uploadMediaUrl=uploadMediaUrl.replace("ACCESS_TOKEN",accessToken);
        String boundary="\"------------7da2e536604c8";
        try {
            URL uploadUrl=new URL(uploadMediaUrl);
            HttpURLConnection uploadContent=(HttpURLConnection) uploadUrl.openConnection();
            uploadContent.setDoOutput(true);
            uploadContent.setDoInput(true);
            uploadContent.setRequestMethod("POST");
            //设置请求头Content-Type
            uploadContent.setRequestProperty("Content-Type","multipart/form-data;boundary="+boundary);
            //获取文件上传的输出流（往微信服务器写数据）
            OutputStream outputStream=uploadContent.getOutputStream();

            URL mediaUrl=new URL(mediaFileUrl);
            HttpURLConnection mediaConn=(HttpURLConnection)mediaUrl.openConnection();
            mediaConn.setDoOutput(true);
            mediaConn.setRequestMethod("GET");
            //从请求头中获取内容类型
            String contentType=mediaConn.getHeaderField("Content-Type");
            //根据内容类型判断文件扩展名
            String fileExt=HttpUtil.getFileEndWitsh(contentType);
            //请求体开始
            outputStream.write(("--"+boundary+"\r\n").getBytes());
            outputStream.write(String.format("Content-Disposition: form-data;name=\"media\";filename=\"file1%s\"\r\n",fileExt).getBytes());
            outputStream.write(String.format("Content-Type: %s\r\n\r\n",contentType).getBytes());
            //获取每日文件的输入流（读取文件）
            BufferedInputStream bis=new BufferedInputStream(mediaConn.getInputStream());
            byte[] buf=new byte[1024*8];
            int size=0;
            while ((size=bis.read(buf))!=-1){
                //将媒体文件写入到输出流，即发送到微信的服务器
                outputStream.write(buf,0,size);
            }
            //请求体结束
            outputStream.write(("\r\n--"+boundary+"--\r\n").getBytes());
            outputStream.close();
            bis.close();
            mediaConn.disconnect();
            //获取文件上传输入流（从微信服务器读取数据）
            InputStream inputStream=uploadContent.getInputStream();
            InputStreamReader inputStreamReader=new InputStreamReader(inputStream,"utf-8");
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            StringBuffer buffer=new StringBuffer();
            String str=null;
            while ((str=bufferedReader.readLine())!=null){
                buffer.append(str);
            }
            bufferedReader.close();
            inputStream.close();

            inputStream.close();
            inputStream=null;
            uploadContent.disconnect();
            System.out.println("测试:"+buffer.toString());

        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }


    public static void main(String[] args){
        String url="http://pic.58pic.com/58pic/15/61/10/06j58PICWiw_1024.jpg";
        String access_token="xfUuz21vz2Vvulbhsh2zfPkoWS-XhMgLoKtzR2uSzOMiGvbeJ5ojQaJEg6VaAIzUZhddeFo2DmWiViqVIplOkq-o7e_0t37TyikQO1P3YLUo4i_F6mjBSJBTTSBRZ2UWSHNjAGAETZ";
//        uploadImg(access_token,url);
        String result= uploadForeverMedia(access_token,"image","http://pic.58pic.com/58pic/15/61/10/06j58PICWiw_1024.jpg");
        System.out.println(result);

    }
//"http:\/\/mmbiz.qpic.cn\/mmbiz_jpg\/kibiaLzwGVAUlFW8ic445zp9IQibuCF1ntn4p0XUicQkbBh8DTnrRUsDmZX3sg96rFggOsoUJU0euHQPskYInvNpZgg\/0
   //http:\/\/mmbiz.qpic.cn\/mmbiz_jpg\/kibiaLzwGVAUlFW8ic445zp9IQibuCF1ntn4p0XUicQkbBh8DTnrRUsDmZX3sg96rFggOsoUJU0euHQPskYInvNpZgg\/0
    //"http:\/\/mmbiz.qpic.cn\/mmbiz_jpg\/kibiaLzwGVAUlFW8ic445zp9IQibuCF1ntn4W2KvE6XXcg41z1wicb7sibz2hzoHicrpMUpCTF3jlkhvPd3BzZVIrEI7A\/0"
}
