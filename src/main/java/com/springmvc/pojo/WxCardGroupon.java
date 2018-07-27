package com.springmvc.pojo;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.alibaba.fastjson.JSONObject;

/**
 *
 * @author jackylian
 */
public class WxCardGroupon extends WxCard
{

    public WxCardGroupon()
    {
        init("GROUPON");
    }
    
    public void setDealDetail(String detail)
    {
        m_data.put("deal_detail", detail);
    }

    public String getDefaultDetail()
    {
        return m_data.getString("default_detail");
    }

    public void getWxGrouponInfo(JSONObject baseInfo, WxCardBaseInfo cashBaseInfo){
        cashBaseInfo.setLogoUrl(baseInfo.getString("logo_url"));
        cashBaseInfo.setBrandName(baseInfo.getString("brand_name"));
        cashBaseInfo.setCodeType(baseInfo.getString("code_type"));
        cashBaseInfo.setTitle(baseInfo.getString("title"));
        cashBaseInfo.setColor(baseInfo.getString("color"));
        cashBaseInfo.setNotice(baseInfo.getString("notice"));
        cashBaseInfo.setDescription(baseInfo.getString("description"));
        cashBaseInfo.setDateInfoTimeRange(baseInfo.getJSONObject("date_info").getLong("begin_timestamp"),baseInfo.getJSONObject("date_info").getLong("end_timestamp"));
        cashBaseInfo.setQuantity(baseInfo.getJSONObject("sku").getInteger("quantity"));
        cashBaseInfo.setGetLimit(baseInfo.getInteger("get_limit"));
        cashBaseInfo.setUseCustomCode(baseInfo.getBoolean("use_custom_code"));
        cashBaseInfo.setBindOpenid(baseInfo.getBoolean("bind_openid"));
        cashBaseInfo.setCanShare(baseInfo.getBoolean("can_share"));
        cashBaseInfo.setCanGiveFriend(baseInfo.getBoolean("can_give_friend"));
        cashBaseInfo.setCenterTitle(baseInfo.getString("center_title"));
        cashBaseInfo.setCenterUrl(baseInfo.getString("center_url"));
        cashBaseInfo.setCenterSubTitle(baseInfo.getString("center_sub_title"));
        cashBaseInfo.setCustomUrlName(baseInfo.getString("custom_url_name"));
        cashBaseInfo.setCustomUrl(baseInfo.getString("custom_url"));
        cashBaseInfo.setCustomUrlSubTitle(baseInfo.getString("custom_url_sub_title"));
        System.out.println(cashBaseInfo.toJsonString());
    }
}
