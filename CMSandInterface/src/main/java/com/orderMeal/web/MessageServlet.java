package com.orderMeal.web;

import com.orderMeal.domain.Message;
import com.orderMeal.service.MessageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet(name = "MessageServlet",urlPatterns = {"/admin/message"})
public class MessageServlet extends HttpServlet {
    public MessageService messageService;

    @Override
    public void init() throws ServletException {
        super.init();
        messageService=new MessageService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type=request.getParameter("type");
        if(type.equals("show")){
            //当前页数
            String page=request.getParameter("page");
            //每显示的记录数
            Map messagesInfo=messageService.getAllMessages(Integer.parseInt(page));
            List<Message> messages= (List<Message>) messagesInfo.get("messages");
            int totalPages= (int) messagesInfo.get("totalPages");
            request.setAttribute("totalPages",totalPages);
            request.setAttribute("messages",messages);
            request.setAttribute("page",page);
            System.out.println("当前页数："+page);
            System.out.println("总页数："+totalPages);
            request.getRequestDispatcher("/WEB-INF/view/BackEnd/jsp/messagesList.jsp").forward(request,response);
        }

        if(type.equals("delete")){
            int id= Integer.parseInt(request.getParameter("id"));
            String data=messageService.deleteMessageById(id);
            PrintWriter  pw=response.getWriter();
            pw.print(data);
        }

        if(type.equals("batchDel")){
            String idstr=request.getParameter("idStr");
            String[] ids=idstr.split(",");
            System.out.println("ids的长度="+ids.length);
            int[] idArray=new int[ids.length];
            for(int i=0;i<idArray.length;i++){
                idArray[i]= Integer.parseInt(ids[i]);
            }
            String data=messageService.batchDeleteMessages(idArray);
            PrintWriter  pw=response.getWriter();
            pw.print(data);
        }
        if(type.equals("deal")){
            int id= Integer.parseInt(request.getParameter("id"));
            String data=messageService.dealMessage(id);
            PrintWriter  pw=response.getWriter();
            pw.print(data);
        }

        if (type.equals("detail")){
            int  id= Integer.parseInt(request.getParameter("id"));
            Message message=messageService.getMessageById(id);
            request.setAttribute("message",message);
            request.getRequestDispatcher("/WEB-INF/view/BackEnd/jsp/messageDetail.jsp").forward(request,response);
        }
    }
}
