package com.springmvc.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.springmvc.pojo.WxCardGroupon;
import com.springmvc.util.HttpRequestUtil;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

//创建卡券
public class CardsCreateService {

    private final static String CREAT_CARD_URL = "https://api.weixin.qq.com/card/create?access_token=";

    private final static String CARD = "card";

    private final static String GROUPON = "GROUPON";

    private final static String BASEINFO = "base_info";

    private final static String ADVANCEDINFO = "advanced_info";

    //上传logo方法，执行完成，保存返回的logo url
    public String uploadLogo(String filePath, String accessToken) {
        try {

            // 上传文件请求路径
            String action = "http://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token="
                    + accessToken + "&type=image";

            URL url = new URL(action);
            String result = null;
            File file = new File(filePath);
            if (!file.exists() || !file.isFile()) {
                throw new IOException("上传的文件不存在");
            }
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST"); // 以Post方式提交表单，默认get方式
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setUseCaches(false); // post方式不能使用缓存
            // 设置请求头信息
            con.setRequestProperty("Connection", "Keep-Alive");
            con.setRequestProperty("Charset", "UTF-8");
            // 设置边界
            String BOUNDARY = "----------" + System.currentTimeMillis();
            con.setRequestProperty("Content-Type", "multipart/form-data; boundary="
                    + BOUNDARY);

            // 请求正文信息
            // 第一部分：
            StringBuilder sb = new StringBuilder();
            sb.append("--"); // 必须多两道线
            sb.append(BOUNDARY);
            sb.append("\r\n");
            sb.append("Content-Disposition: form-data;name=\"file\";filename=\""
                    + file.getName() + "\"\r\n");
            sb.append("Content-Type:application/octet-stream\r\n\r\n");
            byte[] head = sb.toString().getBytes("utf-8");
            // 获得输出流
            OutputStream out = new DataOutputStream(con.getOutputStream());

            // 输出表头
            out.write(head);
            // 文件正文部分
            // 把文件已流文件的方式 推入到url中
            DataInputStream in = new DataInputStream(new FileInputStream(file));
            int bytes = 0;
            byte[] bufferOut = new byte[1024];
            while ((bytes = in.read(bufferOut)) != -1) {
                out.write(bufferOut, 0, bytes);
            }
            in.close();

            // 结尾部分
            byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线
            out.write(foot);
            out.flush();
            out.close();
            StringBuffer buffer = new StringBuffer();
            BufferedReader reader = null;

            try {
                // 定义BufferedReader输入流来读取URL的响应
                reader = new BufferedReader(new InputStreamReader(con
                        .getInputStream()));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                if (result == null) {
                    result = buffer.toString();
                }
            } catch (IOException e) {
                System.out.println("发送POST请求出现异常！" + e);
                e.printStackTrace();
                throw new IOException("数据读取异常");
            } finally {
                if (reader != null) {
                    reader.close();
                }
            }
            return result;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    //创建卡券
    public String createCardToWexin(String logoUrl, String baseUrl, String content, String accessToken,List<String> filePathList) {
        JSONObject jsonCardInfo = JSON.parseObject(content);
        JSONObject jsonObjectCARD = jsonCardInfo.getJSONObject(CARD);
        JSONObject jsonObjectGROUPON = jsonObjectCARD.getJSONObject(GROUPON);
        JSONObject jsonObjectBaseInfo = jsonObjectGROUPON.getJSONObject(BASEINFO);
        JSONObject jsonObjectAdvancedInfo = jsonObjectGROUPON.getJSONObject(ADVANCEDINFO);
        jsonObjectBaseInfo.put("logo_url", logoUrl);
        WxCardGroupon wxGroupon = new WxCardGroupon();
        wxGroupon.setWxGrouponBaseInfo(jsonObjectBaseInfo, wxGroupon.getBaseInfo());
        wxGroupon.setWxGrouponAdvancedInfo(jsonObjectAdvancedInfo, wxGroupon.getAdvancedInfo(),baseUrl, filePathList);
        wxGroupon.setDealDetail(jsonObjectCARD.getString("deal_detail"));
        String returnData = "";
        System.out.println(wxGroupon.toJsonString());
        returnData = HttpRequestUtil.getResponse(CREAT_CARD_URL + accessToken, wxGroupon.toJsonString());

        JSONObject jsonReturnData = JSON.parseObject(returnData);
        String cardID = "";
        cardID = jsonReturnData.getString("card_id");
        return cardID;
    }

}
