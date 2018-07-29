package com.springmvc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author Binary Wang
 */
@Configuration
public class WxMpConfig {

    @Value("#{wxProperties.wx_token}")
    private String token;

    @Value("#{wxProperties.wx_appid}")
    private String appid;

    @Value("#{wxProperties.wx_appsecret}")
    private String appsecret;

    @Value("#{wxProperties.wx_aeskey}")
    private String aesKey;

    @Value("#{wxProperties.wx_host}")
    private String host;

    @Value("#{wxProperties.wx_mch_id}")
    private String mchId;

    @Value("#{wxProperties.db_password}")
    private String db_password;

    @Value("#{wxProperties.wx_pay_appId}")
    private String payAppId;

    @Value("#{wxProperties.wx_key}")
    private String key;

    @Value("#{wxProperties.wx_cret_path}")
    private String certPath;

    @Value("#{wxProperties.wx_state}")
    private String wx_state;

    @Value("#{wxProperties.access_token_url}")
    private String access_token_url;

    private String nonceStr;

    private String wxPackage;

    private String signType =  "MD5";

    private String paySign;

    private String timeStamp;

    private String result;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }

    public String getAesKey() {
        return aesKey;
    }

    public void setAesKey(String aesKey) {
        this.aesKey = aesKey;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getDb_password() {
        return db_password;
    }

    public void setDb_password(String db_password) {
        this.db_password = db_password;
    }

    public String getPayAppId() {
        return payAppId;
    }

    public void setPayAppId(String payAppId) {
        this.payAppId = payAppId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCertPath() {
        return certPath;
    }

    public void setCertPath(String certPath) {
        this.certPath = certPath;
    }

    public String getWx_state() {
        return wx_state;
    }

    public void setWx_state(String wx_state) {
        this.wx_state = wx_state;
    }

    public String getAccess_token_url() {
        return access_token_url;
    }

    public void setAccess_token_url(String access_token_url) {
        this.access_token_url = access_token_url;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getWxPackage() {
        return wxPackage;
    }

    public void setWxPackage(String wxPackage) {
        this.wxPackage = wxPackage;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getPaySign() {
        return paySign;
    }

    public void setPaySign(String paySign) {
        this.paySign = paySign;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}


