package com.springmvc.config;

import com.github.wxpay.sdk.WXPayConfig;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class MyWXPayConfig implements WXPayConfig {
    private byte[] certData;
    private static MyWXPayConfig INSTANCE;
    private WxMpConfig config;

    private MyWXPayConfig(WxMpConfig config) throws Exception{
        this.config = config;
        String certPath = config.getCertPath();
        File file = new File(certPath);
        InputStream certStream = new FileInputStream(file);
        this.certData = new byte[(int) file.length()];
        certStream.read(this.certData);
        certStream.close();
    }

    public static MyWXPayConfig getInstance(WxMpConfig config) throws Exception{
        if (INSTANCE == null) {
            synchronized (MyWXPayConfig.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MyWXPayConfig(config);
                }
            }
        }
        return INSTANCE;
    }

    public String getAppID() {
        return this.config.getPayAppId();
    }

    public String getMchID() {
        return this.config.getMchId();
    }

    public String getKey() {
        return this.config.getKey();
    }

    public InputStream getCertStream() {
        ByteArrayInputStream certBis;
        certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }


    public int getHttpConnectTimeoutMs() {
        return 2000;
    }

    public int getHttpReadTimeoutMs() {
        return 10000;
    }

   // IWXPayDomain getWXPayDomain() {
    //    return WXPayDomainSimpleImpl.instance();
    //}

    public String getPrimaryDomain() {
        return "api.mch.weixin.qq.com";
    }

    public String getAlternateDomain() {
        return "api2.mch.weixin.qq.com";
    }

    //@Override
    public int getReportWorkerNum() {
        return 1;
    }

    //@Override
    public int getReportBatchSize() {
        return 2;
    }
}
