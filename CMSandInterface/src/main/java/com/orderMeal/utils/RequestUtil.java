package com.orderMeal.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

public class RequestUtil {
    /**
     * 接收字符串为utf8格式
     * @param req HTTP请求对象
     * @param reqStr 请求得前台对象名
     * @return 获得的数据
     */
    public String requestUtf8(HttpServletRequest req, String reqStr){
        try {
            String str = new String(req.getParameter(reqStr).getBytes("iso-8859-1"),"utf8");
            return str;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
