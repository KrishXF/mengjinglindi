package com.springmvc.pojo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

public class WxCardAdvancedInfo {
    JSONObject m_data;

    public WxCardAdvancedInfo()
    {
        m_data = new JSONObject();
        m_data.put("use_condition", new JSONObject());
        m_data.put("abstract", new JSONObject());
        m_data.put("time_limit", new JSONObject());
        m_data.put("text_image_list", new JSONArray());
    }

    public String toJsonString()
    {
        return m_data.toString();
    }

    public String toString()
    {
        return toJsonString();
    }

    public JSONObject getAbstractObject()
    {
        return m_data.getJSONObject("abstract");
    }

    public void setAbstractInfo(String baseDescription)
    {
        if(!StringUtils.isEmpty(baseDescription)){
            getAbstractObject().put("abstract", baseDescription);
        }
    }

    public String getBaseDescription()
    {
        return getAbstractObject().getString("abstract");
    }

    public void setIconUrl(String iconUrl)
    {
        if(!StringUtils.isEmpty(iconUrl)){
            getAbstractObject().put("icon_url_list", iconUrl);
        }
    }

    public String getIconUrl()
    {
        return getAbstractObject().getString("icon_url_list");
    }

    public JSONArray getTextImageList(){
        return m_data.getJSONArray("text_image_list");
    }

    public void setTextImageList(String image_url, String text){
        if(!StringUtils.isEmpty(image_url)){
            JSONObject imageList = new JSONObject();
            imageList.put("image_url",image_url);
            imageList.put("text",text);
            getTextImageList().add(imageList);
        }
    }

    public JSONObject getUseCondition()
    {
        return m_data.getJSONObject("use_condition");
    }

    public void setAcceptCategory(String accept_category)
    {
        if(!StringUtils.isEmpty(accept_category)){
            getUseCondition().put("accept_category", accept_category);
        }
    }

    public String getAcceptCategory()
    {
        return getUseCondition().getString("accept_category");
    }

    public void setRejectCategory(String reject_category)
    {
        if(!StringUtils.isEmpty(reject_category)){
            getUseCondition().put("reject_category", reject_category);
        }
    }

    public String getRejectCategory()
    {
        return getUseCondition().getString("reject_category");
    }

    public void setLeastCost(String least_cost)
    {
        if(!StringUtils.isEmpty(least_cost)){
            m_data.put("least_cost", new JSONObject());
        }
    }

    public String getLeastCost()
    {
        return m_data.getString("least_cost");
    }

    public void setCanUseWithOtherDiscount(Boolean can_use_with_other_discount)
    {
        m_data.put("can_use_with_other_discount", can_use_with_other_discount);
    }

    public Boolean getCanUseWithOtherDiscount()
    {
        return m_data.getBoolean("can_use_with_other_discount");
    }

    public void setOjectUseFor(String object_use_for)
    {
        if(!StringUtils.isEmpty(object_use_for)){
            m_data.put("object_use_for", new JSONObject());
        }
    }

    public String getOjectUseFor()
    {
        return m_data.getString("object_use_for");
    }

}
