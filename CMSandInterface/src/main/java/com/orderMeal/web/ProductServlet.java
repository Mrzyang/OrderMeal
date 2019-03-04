package com.orderMeal.web;

import com.orderMeal.domain.Product;
import com.orderMeal.service.ProductService;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ProductServlet", urlPatterns={"/admin/product"})
public class ProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String type = request.getParameter("type");
        ProductService as = new ProductService();

        if("add".equals(type)){

            System.out.println("调用添加产品函数");
            int flag = 0;
            System.out.println("flag = " + flag);
            String productType = request.getParameter("productType");
            String productName = request.getParameter("productName");
            String productSign = request.getParameter("productSign");
            System.out.println("productName = " + productName + "   productType = " + productType);
            if(as.findProductByName(productName,null)==-1){
                request.setAttribute("nameMistake","已经添加过该菜品了！！");
                flag++;
            }
            if(productName.equals("") || productName.equals(null)){
                request.setAttribute("nameMistake","菜品名不能为空！！");
                flag++;
            }
            if(!"1".equals(productType) && !"2".equals(productType) && !"3".equals(productType) && !"4".equals(productType))
            {
                request.setAttribute("typeMistake","请选择合法类型");
                flag++;
            }
            System.out.println("flag = " + flag);
            if(flag>0){
                request.setAttribute("productType",productType);
                request.setAttribute("productName",productName);
                request.getRequestDispatcher("/WEB-INF/view/BackEnd/jsp/addProduct.jsp").forward(request,response);
                return;
            }
            if(as.addProduct(productName,productType)==-1){
                request.setAttribute("addMistake","对不起，添加出现错误");
            }else{
                request.setAttribute("addMistake","恭喜你，添加成功!!");
            }

            request.getRequestDispatcher("/WEB-INF/view/BackEnd/jsp/addProduct.jsp").forward(request,response);
            return;
        }
        if("addUI".equals(type)){
            System.out.println("调用添加产品函数入口");
            request.getRequestDispatcher("/WEB-INF/view/BackEnd/jsp/addProduct.jsp").forward(request,response);
            return;
        }
        if("delete".equals(type)){
            Map map=new HashMap();
            map.put("status",0);
            map.put("data","删除失败!");
            System.out.println("调用删除产品函数");
            String productId = request.getParameter("productId");
            String pageNum = request.getParameter("pageNum");
            if (productId == null) {
                System.out.println("请选择要删除的菜品");
                map.put("date","请选择要删除的菜品");
            }
            int a = as.deleteProduct(productId);
            if(a==-1){
                System.out.println("请选择合法的删除对象进行操作");
            }else {
                System.out.println("删除成功！！");
                map.put("pageNum",pageNum);
                map.put("status",1);
                map.put("data","删除成功!");
            }
            JSONObject msg= JSONObject.fromObject(map);
            System.out.println("========================" + msg);
            response.setCharacterEncoding("UTF-8");
            PrintWriter out=response.getWriter();
            out.println(msg);
            return;
            //request.getRequestDispatcher("/admin/product").forward(request,response);
        }
        if("update".equals(type)){
            Map map=new HashMap();
            map.put("status",0);
            map.put("data","更新失败!");
            System.out.println("调用修改产品函数");
            int flag = 0;
            String productId = request.getParameter("productId");
            String productType = request.getParameter("productType");
            String productName = request.getParameter("productName");
            String productSign = request.getParameter("productSign");
            String pageNum = request.getParameter("pageNum");
            request.setAttribute("pageNum",pageNum);
            if(productName!=null&&as.findProductByName(productName,productId)==-1){
                map.put("data","已经添加过该菜品了！！");
                flag++;
            }
            if(!productType.equals("1") && !productType.equals("2") && !productType.equals("3") && !productType.equals("4") && !productType.equals(""))
            {
                map.put("data","请选择合法类型");
                System.out.println("productType = " + productType);
                System.out.println("请选择合法类型");
                flag++;
            }
            if(!productSign.equals("1") && !productSign.equals("2") && !productSign.equals("")){
                map.put("data","请选择正确的菜品状态");
                flag++;
            }
            int a = -1;
            if (flag == 0) {
                a = as.updateProduct(productId, productName, productType, productSign);
            }
            if(a!=-1){
                map.put("pageNum",pageNum);
                map.put("status",1);
                map.put("data","更新成功!");
            }
            JSONObject msg= JSONObject.fromObject(map);
            System.out.println(msg);
            response.setCharacterEncoding("UTF-8");
            PrintWriter out=response.getWriter();
            out.println(msg);
            return;
        }
        if("updateUI".equals(type)){
            System.out.println("调用修改产品函数入口");
            Product product = new Product();
            product.setProductId(Integer.parseInt(request.getParameter("productId")));
            product.setProductName(request.getParameter("productName"));
            product.setProductType(request.getParameter("productType"));
            request.setAttribute("product",product);
            request.getRequestDispatcher("/WEB-INF/view/BackEnd/jsp/updateProduct.jsp").forward(request,response);
            return;
        }
        else{
            System.out.println("调用产品表单显示函数");
            String page = request.getParameter("pageNum");
            System.out.println("page ="+ page);
            if(page==null||page==""){
                page = (String)request.getAttribute("pageNum");
                System.out.println("page ="+ page);
            }
            String keyword = request.getParameter("keyword");
            System.out.println(" keyword = "+keyword);
            int total = as.getProductTotalPage(keyword);
            if(total==-1){
                System.out.println("查询出错");
                request.setAttribute("message","查询出错");
                request.getRequestDispatcher("/WEB-INF/view/BackEnd/jsp/productList.jsp").forward(request,response);
            }

            List<Product> list = as.getProductPage(page,keyword);


            if(page==null){
                request.setAttribute("pageNum",1);
            }else{
                request.setAttribute("pageNum",page);
            }
            request.setAttribute("totalPage",total);
            request.setAttribute("keyword",keyword);
            request.setAttribute("list",list);
            request.getRequestDispatcher("/WEB-INF/view/BackEnd/jsp/productList.jsp").forward(request,response);
            return;
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
