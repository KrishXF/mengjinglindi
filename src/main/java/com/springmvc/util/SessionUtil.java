package com.springmvc.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Elegy on 2017/06/26.
 */
public class SessionUtil {
    public static void setParam(HttpServletRequest request, String paramName,Object paramValue){
        HttpSession session = request.getSession();
        session.setAttribute(paramName,paramValue);
    }

    public static Object getParam(HttpServletRequest request,String paramName){
        HttpSession session = request.getSession();
        Object paramValue = session.getAttribute(paramName);
        return paramValue;
    }

    public static void delParam(HttpServletRequest request, String paramName) {
        HttpSession session = request.getSession();
        session.removeAttribute(paramName);
    }
}
