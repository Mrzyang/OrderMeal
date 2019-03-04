package com.orderMeal.web;

import com.orderMeal.domain.Goods;
import com.orderMeal.domain.Product;
import com.orderMeal.service.GoodsService;
import com.orderMeal.service.ProductService;
import com.orderMeal.utils.FileUpload;
import com.orderMeal.utils.GetID;
import com.sun.org.apache.xpath.internal.SourceTree;
import net.sf.json.JSONObject;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.util.SystemOutLogger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.util.*;

@WebServlet(name = "GoodsServlet", urlPatterns = {"/admin/goods"})
public class GoodsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String type = request.getParameter("type");
        System.out.println("type = " + type);
        GoodsService gs = new GoodsService();

        /*if("info".equals(type)){

            System.out.println("调用套餐详细信息显示函数");
            String goodsId = request.getParameter("goodsId");
            if(goodsId==null){
                System.out.println("对不起，您选择的套餐可能已下架");
                request.setAttribute("Mistake","对不起，您选择的套餐可能已经下架");
                request.getRequestDispatcher("").forward(request,response);
                return;
            }
            Goods goods = gs.findGoodsById(goodsId);

        }*/
        if ("add".equals(type)) {

            System.out.println("调用添加套餐函数");
            //错误队列
            List mistakeList = new ArrayList<String>();
            //保存取出的前台数据
            Map<String,String> valueMap = new HashMap();

            //向前台发送要进行选择的四种菜品
            request.setAttribute("productlist1", gs.getproduct(1));
            request.setAttribute("productlist2", gs.getproduct(2));
            request.setAttribute("productlist3", gs.getproduct(3));
            request.setAttribute("productlist4", gs.getproduct(4));

            //出错标记，每次出错+1
            int flag = 0;
            String path = null;

            //得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
            String savePath = this.getServletContext().getRealPath("/goods");

            //上传时生成的临时文件保存目录
            String tempPath = this.getServletContext().getRealPath("/temp");
            File tmpFile = new File(tempPath);

            if (!tmpFile.exists()) {
                //创建临时目录
                tmpFile.mkdir();
            }
            try {
                //使用Apache文件上传组件处理文件上传步骤：
                //1、创建一个DiskFileItemFactory工厂
                DiskFileItemFactory factory = new DiskFileItemFactory();
                //设置工厂的缓冲区的大小，当上传的文件大小超过缓冲区的大小时，就会生成一个临时文件存放到指定的临时目录当中。
                factory.setSizeThreshold(1024 * 100);//设置缓冲区的大小为100KB，如果不指定，那么缓冲区的大小默认是10KB
                //设置上传时生成的临时文件的保存目录
                factory.setRepository(tmpFile);
                //2、创建一个文件上传解析器
                ServletFileUpload upload = new ServletFileUpload(factory);
                //监听文件上传进度
                upload.setProgressListener(new ProgressListener() {
                    public void update(long pBytesRead, long pContentLength, int arg2) {
                        System.out.println("文件大小为：" + pContentLength + ",当前已处理：" + pBytesRead);
                        /*
                                                         文件大小为：14608,当前已处理：4096
                                                         文件大小为：14608,当前已处理：7367
                                                         文件大小为：14608,当前已处理：11419
                                                         文件大小为：14608,当前已处理：14608
                         */
                    }
                });
                //解决上传文件名的中文乱码
                upload.setHeaderEncoding("UTF-8");
                //3、判断提交上来的数据是否是上传表单的数据

                //设置上传单个文件的大小的最大值，目前是设置为1024*1024字节，也就是1MB
                upload.setFileSizeMax(1024 * 1024);
                //设置上传文件总量的最大值，最大值=同时上传的多个文件的大小的最大值的和，目前设置为10MB
                upload.setSizeMax(1024 * 1024 * 10);
                //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
                List<FileItem> list = upload.parseRequest(request);
                for (FileItem item : list) {
                    //如果fileitem中封装的是普通输入项的数据
                    if (item.isFormField()) {
                        String name = item.getFieldName();
                        //解决普通输入项的数据的中文乱码问题
                        String value = item.getString("UTF-8");
                        //value = new String(value.getBytes("iso8859-1"),"UTF-8");
                        valueMap.put(name,value);

                    } else {//如果fileitem中封装的是上传文件
                        //得到上传的文件名称，
                        String filename = item.getName();
                        if (filename == null || filename.trim().equals("")) {
                            continue;
                        }
                        //注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
                        //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
                        filename = filename.substring(filename.lastIndexOf("\\") + 1);
                        //得到上传文件的扩展名
                        String fileExtName = filename.substring(filename.lastIndexOf(".") + 1);
                        //如果需要限制上传的文件类型，那么可以通过文件的扩展名来判断上传的文件类型是否合法
                        System.out.println("上传的文件的扩展名是：" + fileExtName);
                        if(!"jpg".equals(filename) && !"png".equals(filename)){
                            System.out.println("上传的文件类型有误");
                        }

                        //获取item中的上传文件的输入流
                        InputStream in = item.getInputStream();
                        //得到文件保存的名称
                        String saveFilename = FileUpload.makeFileName(filename);
                        //得到文件的保存目录
                        String realSavePath = FileUpload.makePath(saveFilename, savePath);
                        path = realSavePath + "\\" + saveFilename;
                        System.out.println(path);
                        //创建一个文件输出流
                        FileOutputStream out = new FileOutputStream(realSavePath + "\\" + saveFilename);
                        //创建一个缓冲区
                        byte buffer[] = new byte[1024];
                        //判断输入流中的数据是否已经读完的标识
                        int len = 0;
                        //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
                        while ((len = in.read(buffer)) > 0) {
                            //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
                            out.write(buffer, 0, len);
                        }
                        //关闭输入流
                        in.close();
                        //关闭输出流
                        out.close();
                        //删除处理文件上传时生成的临时文件
                        //item.delete();
                        //message = "文件上传成功！";
                    }
                }
            } catch (FileUploadBase.FileSizeLimitExceededException e) {
                e.printStackTrace();
                System.out.println("单个文件超出最大值！！！");
                mistakeList.add("单个文件最大不能超过1MB！！！");
                flag++;
            } catch (FileUploadBase.SizeLimitExceededException e) {
                e.printStackTrace();
                System.out.println("上传文件的总的大小超出限制的最大值！！！");
                mistakeList.add("上传文件的总的大小不能超过10MB！！！");
                flag++;
            } catch (Exception e) {
                //message = "文件上传失败！";
                e.printStackTrace();
            }

            String goodsName = valueMap.get("goodsName");
            String goodsPrice = valueMap.get("goodsPrice");
            String product1 = valueMap.get("product1");
            String product2 = valueMap.get("product2");
            String product3 = valueMap.get("product3");
            String product4 = valueMap.get("product4");
            String product5 = valueMap.get("product5");

            if(goodsName == null || "".equals(goodsName)){
                mistakeList.add("套餐名不能为空");
                System.out.println("套餐名不能为空");
                flag++;
            }

            double price = -1;
            try {
                 price = Double.parseDouble(goodsPrice);
            }catch (Exception e){
                mistakeList.add("请输入合法的价格");
                System.out.println("请输入合法的价格");
                flag++;
            }

            if("-1".equals(product1) || "-1".equals(product2) || "-1".equals(product3) || "-1".equals(product4)){
                mistakeList.add("请至少选择前四中菜品");
                System.out.println("请至少选择前四种必选菜品");
                flag++;
            }

            //又错误发生
            if(flag>0){
                //向前台显示错误信息
                request.setAttribute("Mistake",mistakeList);
                //数据回显
                request.setAttribute("goodsName",goodsName);
                request.setAttribute("goodsPrice",goodsPrice);
                request.setAttribute("product1",product1);
                request.setAttribute("product2",product2);
                request.setAttribute("product3",product3);
                request.setAttribute("product4",product4);
                request.setAttribute("product5",product5);
                request.getRequestDispatcher("/WEB-INF/view/BackEnd/jsp/addGoods.jsp").forward(request,response);
                return;
            }

            //拼接product
            String product = product1 + "," + product2 + "," + product3 + "," + product4;
            if(!"-1".equals(product5)){
                product = product + "," + product5;
            }
            System.out.println("product = " + product);

            //截取地址
            String[] s = path.split("webapp");
            path = s[1].substring(1);

            //添加出错
            if(gs.addGoods(goodsName,price,product,path)==-1){
                mistakeList.add("对不起，添加出现错误");
                //向前台显示错误信息
                request.setAttribute("Mistake",mistakeList);
                //数据回显
                request.setAttribute("goodsName",goodsName);
                request.setAttribute("goodsPrice",goodsPrice);
                request.setAttribute("product1",product1);
                request.setAttribute("product2",product2);
                request.setAttribute("product3",product3);
                request.setAttribute("product4",product4);
                request.setAttribute("product5",product5);
                request.getRequestDispatcher("/WEB-INF/view/BackEnd/jsp/addGoods.jsp").forward(request,response);
                return;
            }else{
                mistakeList.add("添加成功");
                request.setAttribute("Mistake",mistakeList);
            }

            request.getRequestDispatcher("/WEB-INF/view/BackEnd/jsp/addGoods.jsp").forward(request,response);
            return;
        }
        if ("addUI".equals(type)) {
            System.out.println("调用添加产品函数入口");
            request.setAttribute("productlist1", gs.getproduct(1));
            request.setAttribute("productlist2", gs.getproduct(2));
            request.setAttribute("productlist3", gs.getproduct(3));
            request.setAttribute("productlist4", gs.getproduct(4));
            request.getRequestDispatcher("/WEB-INF/view/BackEnd/jsp/addGoods.jsp").forward(request, response);
            return;
        }
        if ("delete".equals(type)) {
            Map map = new HashMap();
            map.put("status", 0);
            map.put("data", "删除失败!");
            System.out.println("调用删除套餐函数");
            String goodsId = request.getParameter("goodsId");
            String pageNum = request.getParameter("pageNum");
            System.out.println("goodsId = " + goodsId + "   pageNum = " + pageNum);
            if (goodsId == null) {
                System.out.println("请选择要删除的套餐");
                map.put("date", "请选择要删除的套餐");
            }
            int a = gs.deleteGoods(goodsId);
            if (a == -1) {
                System.out.println("请选择合法的删除对象进行操作");
            } else {
                System.out.println("删除成功！！");
                map.put("pageNum", pageNum);
                map.put("status", 1);
                map.put("data", "删除成功!");
            }
            JSONObject msg = JSONObject.fromObject(map);
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.println(msg);
            return;
        }
        if ("update".equals(type)) {
            Map map = new HashMap();
            map.put("status", 0);
            map.put("data", "更新失败!");
            System.out.println("调用修改套餐函数");

            request.setAttribute("productlist1", gs.getproduct(1));
            request.setAttribute("productlist2", gs.getproduct(2));
            request.setAttribute("productlist3", gs.getproduct(3));
            request.setAttribute("productlist4", gs.getproduct(4));

            int flag = 0;
            double price = 0;
            //控制double的小数位
            DecimalFormat df = new DecimalFormat("0.00");
            String goodsId = null;
            String goodsName = null;
            String goodsPrice = null;
            String productName = "";
            String updateType = request.getParameter("updateType");
            System.out.println("updateType = " + updateType);


            if ("price".equals(updateType)) {
                System.out.println("修改价格");
                goodsId = request.getParameter("goodsId");
                goodsPrice = request.getParameter("goodsPrice");
                System.out.println("goodsPrice = " + goodsPrice);
            }

            if ("all".equals(updateType)) {
                System.out.println("修改所有");
                goodsId = request.getParameter("goodsId");
                goodsName = request.getParameter("goodsName");
                goodsPrice = request.getParameter("goodsPrice");
                ArrayList<String> l = new ArrayList();
                l.add(request.getParameter("product1"));
                l.add(request.getParameter("product2"));
                l.add(request.getParameter("product3"));
                l.add(request.getParameter("product4"));
                for (int i = 0; i < 4; i++) {
                    if (!l.get(i).equals("-1")) {
                        productName = productName + l.get(i) + ",";
                    }
                }
            }

            String pageNum = request.getParameter("pageNum");
            request.setAttribute("pageNum", pageNum);
            try {
                System.out.println("goodsPrice = --" + goodsPrice + "--");
                price = Double.parseDouble(goodsPrice);

            } catch (Exception e) {
                map.put("data", "输入价格不合法！！请输入0——100的整数，最多两位小数");
                System.out.println("输入价格不合法");
                flag++;
            }
            //将double四舍五入为两位小数
            price = Double.parseDouble(df.format(price));
            if (goodsName != null && gs.findGoodsByName(goodsName, goodsId) == -1) {
                map.put("data", "已经添加过该套餐了！！");
                flag++;
            }
            if (flag == 0) {
                if (gs.updateGoods(updateType, goodsId, goodsName, price, productName) != -1) {
                    map.put("pageNum", pageNum);
                    map.put("status", 1);
                    map.put("data", "更新成功!");
                }
            }
            JSONObject msg = JSONObject.fromObject(map);
            System.out.println(msg);
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.println(msg);
            return;
        }
        if ("updateUI".equals(type)) {
            System.out.println("调用修改套餐函数入口");

            request.setAttribute("productlist1", gs.getproduct(1));
            request.setAttribute("productlist2", gs.getproduct(2));
            request.setAttribute("productlist3", gs.getproduct(3));
            request.setAttribute("productlist4", gs.getproduct(4));

            Goods goods = new Goods();
            goods.setGoodsId(Integer.parseInt(request.getParameter("goodsId")));
            goods.setGoodsName(request.getParameter("goodsName"));
            goods.setGoodsPrice(Double.parseDouble(request.getParameter("goodsPrice")));
            request.setAttribute("goods", goods);

            String s = request.getParameter("products");
            String[] products = s.split(",");
            switch (products.length) {
                case 1:
                    request.setAttribute("product1", products[0]);
                    request.setAttribute("product2", "-2");
                    request.setAttribute("product3", "-2");
                    request.setAttribute("product4", "-2");
                    break;
                case 2:
                    request.setAttribute("product1", products[0]);
                    request.setAttribute("product2", products[1]);
                    request.setAttribute("product3", "-2");
                    request.setAttribute("product4", "-2");
                    break;
                case 3:
                    request.setAttribute("product1", products[0]);
                    request.setAttribute("product2", products[1]);
                    request.setAttribute("product3", products[2]);
                    request.setAttribute("product4", "-2");
                    break;
                case 4:
                    request.setAttribute("product1", products[0]);
                    request.setAttribute("product2", products[1]);
                    request.setAttribute("product3", products[2]);
                    request.setAttribute("product4", products[3]);
                    break;
            }

            request.getRequestDispatcher("/WEB-INF/view/BackEnd/jsp/updateGoods.jsp").forward(request, response);
            return;
        } else {
            System.out.println("调用套餐表单显示函数");
            String page = request.getParameter("pageNum");
            System.out.println("page =" + page);
            if (page == null || page == "") {
                page = (String) request.getAttribute("pageNum");
            }
            String keyword = request.getParameter("keyword");
            System.out.println(" keyword = " + keyword);
            int total = gs.getGoodsTotalPage(keyword);
            if (total == -1) {
                System.out.println("查询出错");
                request.setAttribute("message", "查询出错");
                request.getRequestDispatcher("/WEB-INF/view/BackEnd/jsp/goodsList.jsp").forward(request, response);
            }

            List<Goods> list = gs.getGoodsPage(page, keyword);


            if (page == null) {
                request.setAttribute("pageNum", 1);
            } else {
                request.setAttribute("pageNum", page);
            }
            request.setAttribute("totalPage", total);
            request.setAttribute("keyword", keyword);
            request.setAttribute("list", list);
            request.getRequestDispatcher("/WEB-INF/view/BackEnd/jsp/goodsList.jsp").forward(request, response);
            return;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
