package com.springmvc.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.springmvc.pojo.WxCardGroupon;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequestUtil {

    //创建卡券
    public static String getResponse(String url, String content) {
        String line = "";
        String message = "";
        String returnData = "";
        BufferedReader bufferedReader = null;

        try {
            URL urlObject = new URL(url);
            HttpURLConnection urlConn = (HttpURLConnection) urlObject.openConnection();
            urlConn.setDoOutput(true);
            /*设定禁用缓存*/
            urlConn.setRequestProperty("Cache-Control", "no-cache");
            /*维持长连接*/
            urlConn.setRequestProperty("Connection", "Keep-Alive");
            /*设置字符集*/
            urlConn.setRequestProperty("Charset", "UTF-8");
            /*设定输出格式为json*/
            urlConn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            /*设置使用POST的方式发送*/
            urlConn.setRequestMethod("POST");
            /*设置不使用缓存*/
            urlConn.setUseCaches(false);
            /*设置容许输出*/
            urlConn.setDoOutput(true);
            /*设置容许输入*/
            urlConn.setDoInput(true);
            urlConn.connect();

            OutputStreamWriter outStreamWriter = new OutputStreamWriter(urlConn.getOutputStream(),"UTF-8");
            outStreamWriter.write(content);
            outStreamWriter.flush();
            outStreamWriter.close();

            /*若post失败*/
            if((urlConn.getResponseCode() != 200)){
                returnData = "{\"jsonStrStatus\":0,\"processResults\":[]}";
                message = "发送POST失败！"+ "code="+urlConn.getResponseCode() + "," + "失败消息："+ urlConn.getResponseMessage();
                // 定义BufferedReader输入流来读取URL的响应
                InputStream errorStream = urlConn.getErrorStream();

                if(errorStream != null)
                {
                    InputStreamReader inputStreamReader = new InputStreamReader(errorStream,"utf-8");
                    bufferedReader = new BufferedReader(inputStreamReader);

                    while ((line = bufferedReader.readLine()) != null) {
                        message += line;
                    }
                    inputStreamReader.close();
                }
                errorStream.close();
                System.out.println("发送失败！错误信息为："+message);
            }else{
                // 定义BufferedReader输入流来读取URL的响应
                InputStream inputStream = urlConn.getInputStream();

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"utf-8");
                bufferedReader = new BufferedReader(inputStreamReader);

                while ((line = bufferedReader.readLine()) != null) {
                    message += line;
                }
                returnData = message;
                inputStream.close();
                inputStreamReader.close();
                System.out.println("发送POST成功！返回内容为：" + returnData);
                return returnData;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return returnData;
        }
    }
}
