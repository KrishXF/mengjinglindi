package com.springmvc.pojo;

import com.alibaba.fastjson.JSONObject;

public class WxCardAdvancedInfo {
    JSONObject m_data;

    public WxCardAdvancedInfo()
    {
        m_data = new JSONObject();
        m_data.put("abstract", new JSONObject());
    }

    public String toJsonString()
    {
        return m_data.toString();
    }

    public String toString()
    {
        return toJsonString();
    }

    public JSONObject getAbstractInfo()
    {
        return m_data.getJSONObject("abstract");
    }

    public void setBaseDescription(String baseDescription)
    {
        getAbstractInfo().put("abstract", baseDescription);
    }

    public String getBaseDescription()
    {
        return getAbstractInfo().getString("abstract");
    }

    public void setIconUrl(String iconUrl)
    {
        getAbstractInfo().put("icon_url_list", iconUrl);
    }

    public String getIconUrl()
    {
        return getAbstractInfo().getString("icon_url_list");
    }
}
