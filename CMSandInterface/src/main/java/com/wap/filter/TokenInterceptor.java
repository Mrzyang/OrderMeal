package com.wap.filter;

import com.google.gson.Gson;
import com.orderMeal.domain.Customer;
import com.wap.utils.ResponseData;
import net.sf.json.JSONObject;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebFilter(filterName = "TokenInterceptor")
public class TokenInterceptor implements Filter {
    public FilterConfig config;
    public void destroy() {
        this.config = null;
    }
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        //解决编码以及请求头的问题
        HttpServletResponse hresponse=(HttpServletResponse) response;
        hresponse.setHeader("Access-Control-Allow-Origin", "*");
        hresponse.setHeader("Access-Control-Allow-Headers", "Content-Type,Content-Length, Authorization, Accept,X-Requested-With,X-Token,X-LoginId");
        hresponse.setHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
        hresponse.setHeader("X-Powered-By", "Jetty");
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");

        HttpServletRequest hrequest = (HttpServletRequest)request;
        HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper((HttpServletResponse) response);
        String logonStrings = config.getInitParameter("logonStrings");        // 不需要过滤的api


        String redirectPath = hrequest.getContextPath() + config.getInitParameter("redirectPath");// 没有登录会跳转的错误api地址
        String disabletestfilter = config.getInitParameter("disabletestfilter");// 过滤器是否有效
        if (disabletestfilter.toUpperCase().equals("Y")) {    // 过滤无效
            chain.doFilter(request, response);
            return;
        }
        String[] logonList = logonStrings.split(";");

        if (this.isContains(hrequest.getRequestURI(), logonList)) {// 对不进行过滤
            chain.doFilter(request, response);
            return;
        }

        //此处是前台将token和loginId 放到了headers里面，后来来获取并处理
        HttpServletRequest resq=(HttpServletRequest)request;
        String token = resq.getHeader("X-Token");


        String loginId = resq.getHeader("X-LoginId");
        ResponseData responseData = ResponseData.ok();
        if(null != token) {
            Customer customer = JWT.unsign(token, Customer.class);
            System.out.println(customer);
            System.out.println(loginId);

            //解密token后的loginId与用户传来的loginId不一致，一般都是token过期
            if(customer!=null&&null != loginId && null != customer.getCustomerId()) {
                if(loginId.equals(customer.getCustomerId())) {
                    //return true;
                    //HttpSession session=((HttpServletRequest) request).getSession();
                    System.out.println("两者是相等的");
                    request.setAttribute("customerId",loginId);
                }
                else{
                    responseData = ResponseData.forbidden();
                    responseMessage((HttpServletResponse) response, response.getWriter(), responseData);
                   // return false;
                   // wrapper.sendRedirect(redirectPath);
                }
            }
            else
            {
                responseData = ResponseData.forbidden();
                responseMessage((HttpServletResponse) response, response.getWriter(), responseData);
               // return false;
                //wrapper.sendRedirect(redirectPath);
            }
        }
        else
        {
            responseData = ResponseData.forbidden();
            responseMessage((HttpServletResponse) response, response.getWriter(), responseData);
            //return false;
           // wrapper.sendRedirect(redirectPath);
        }
        chain.doFilter(request, response);
    }

    //请求不通过，返回错误信息给客户端
    private void responseMessage(HttpServletResponse response, PrintWriter out, ResponseData responseData) {
        responseData = ResponseData.forbidden();
        response.setContentType("application/json; charset=utf-8");
        Gson gson=new Gson();
        String json = gson.toJson(responseData);
        out.print(json);
        out.flush();
        out.close();
    }
    public void init(FilterConfig config) throws ServletException {
        this.config = config;
    }
    public static boolean isContains(String container, String[] regx) {
        boolean result = false;

        for (int i = 0; i < regx.length; i++) {
            if (container.indexOf(regx[i]) != -1) {
                return true;
            }
        }
        return result;
    }
}
