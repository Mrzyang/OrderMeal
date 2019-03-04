package com.orderMeal.service;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExportExcelService {
    public static final String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");
    public void export(String jsonStr, String sheaders, String fileName, String title, HttpServletResponse response, HttpServletRequest request,int type){

        try {
            JSONArray jsonArray = new JSONArray(jsonStr);
            String[] headers;
            headers = sheaders.substring(0,sheaders.length()-1).split(",");
            System.out.println(sheaders.substring(0,sheaders.length()-1));
            int iSize = jsonArray.length();
            List<List> list = new ArrayList<List>();
            for(int i = 0;i<iSize;i++){
                List<String> line = new ArrayList<String>();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Iterator iterator = jsonObject.keys();
                String value = null;
                int j=0;
                while(iterator.hasNext()){
                    iterator.next();
                    value = jsonObject.getString(headers[j]);
                    //表格内容
                    line.add(value);
                    j++;
                    System.out.println(value);
                }
                list.add(line);
            }

            for(List<String> line:list){
                for(String s:line){
                    System.out.print(s+"\t");
                }
                System.out.println();
            }
            String docsPath = request.getSession().getServletContext().getRealPath("exportExcel");

            File file = new File(docsPath,fileName);//查找文件目录是否存在
            if(!file.exists()){//如果不存在就先创建文件夹及文件
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            //文件路径
            String filePath = docsPath + FILE_SEPARATOR + fileName;
            System.out.println(filePath);
            OutputStream out = new FileOutputStream(filePath);
            exportExcel(title,headers, list, out,type);
            out.close();
            JOptionPane.showMessageDialog(null, "导出成功!");
            System.out.println("excel导出成功！");

            //下载
            download(filePath, response);

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFound");
            // TODO: handle exception
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IO");
            // TODO: handle exception
            e.printStackTrace();
        }
    }
    private void exportExcel(String title, String[] headers,List<List> list, OutputStream out,int type) {
        System.out.println(type+"ddddd===="+title);
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet(title);
        // 设置表格默认列宽度为15个字节

        HSSFCellStyle style = workbook.createCellStyle();
        CellRangeAddress[] date = new CellRangeAddress[7];
        CellRangeAddress[] day = new CellRangeAddress[7];


        switch (type){
            case 1:mergedCell_1(sheet);break;
            case 2:mergedCell_2(sheet);break;
            case 3:mergedCell_3(sheet);break;
            case 4:break;
            default:break;
        }

        // 生成一个样式
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 生成一个字体
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);
        // 生成并设置另一个样式
        HSSFCellStyle style2 = workbook.createCellStyle();
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 生成另一个字体
        HSSFFont font2 = workbook.createFont();
        font2.setColor(HSSFColor.BLACK.index);
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        // 把字体应用到当前的样式
        style2.setFont(font2);
        // 声明一个画图的顶级管理器
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
        // 定义注释的大小和位置,详见文档
        HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,
                0, 0, 0, (short) 4, 2, (short) 6, 5));
        // 设置注释内容
        comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));
        // 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
        comment.setAuthor("leno");
        // 产生表格标题行
        HSSFRow row = sheet.createRow(0);
        for (short i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        // 遍历集合数据，产生数据行
        for(int i=0;i<list.size();i++){
            List<String> line = list.get(i);
            row = sheet.createRow(i+1);
            for(int j=0;j<line.size();j++){
                HSSFCell cell = row.createCell(j);
                cell.setCellStyle(style2);
                String value = line.get(j);
                try {
                    // 判断值的类型后进行强制类型转换
                    String textValue = null;
                    // 其它数据类型都当作字符串简单处理
                    textValue = value.toString();
                    // 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
                    if (textValue != null) {
                        HSSFRichTextString richString = new HSSFRichTextString(textValue);
                        HSSFFont font3 = workbook.createFont();
                        font3.setColor(HSSFColor.BLUE.index);
                        richString.applyFont(font3);
                        cell.setCellValue(richString);
                    }
                } catch (SecurityException e) {
                    e.printStackTrace();
                } finally {
                    // 清理资源
                }
            }

        }

        try {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 合并单元格的样式1
     */
    private void mergedCell_1(HSSFSheet sheet){
        sheet.setDefaultColumnWidth((short) 15);
        CellRangeAddress[] date = new CellRangeAddress[7];
        CellRangeAddress[] day = new CellRangeAddress[7];
        for(int i=0;i<date.length;i++){
            date[i] = new CellRangeAddress(4*i+1,4*(i+1),0,0);
            day[i] = new CellRangeAddress(4*i+1,4*(i+1),1,1);
            sheet.addMergedRegion(date[i]);
            sheet.addMergedRegion(day[i]);
        }
        CellRangeAddress[] mealTime = new CellRangeAddress[12];
        for(int i=0;i<12;i++){
            mealTime[i] = new CellRangeAddress(2*i+1,2*(i+1),2,2);
            sheet.addMergedRegion(mealTime[i]);
        }
/*        CellRangeAddress[] mealTime = new CellRangeAddress[12];
        CellRangeAddress[] mainMeal = new CellRangeAddress[12];//主荤
        CellRangeAddress[] withMeat1 = new CellRangeAddress[12];//配荤1
        CellRangeAddress[] vagetable = new CellRangeAddress[12];//素菜
        CellRangeAddress[] drinkAndFruit = new CellRangeAddress[12];//饮品水果
        for(int i=0;i<12;i++){
            mealTime[i] = new CellRangeAddress(2*i+1,2*(i+1),2,2);
            mainMeal[i] = new CellRangeAddress(2*i+1,2*(i+1),4,4);
            withMeat1[i] = new CellRangeAddress(2*i+1,2*(i+1),5,5);
            vagetable[i] = new CellRangeAddress(2*i+1,2*(i+1),6,6);
            drinkAndFruit[i] = new CellRangeAddress(2*i+1,2*(i+1),8,8);
            sheet.addMergedRegion(mealTime[i]);
            sheet.addMergedRegion(mainMeal[i]);
            sheet.addMergedRegion(withMeat1[i]);
            sheet.addMergedRegion(vagetable[i]);
            sheet.addMergedRegion(drinkAndFruit[i]);
        }*/
    }
    private void mergedCell_2(HSSFSheet sheet){
        sheet.setDefaultColumnWidth((short) 8);
        CellRangeAddress name = new CellRangeAddress(0,1,0,0);
        sheet.addMergedRegion(name);
        CellRangeAddress[] day = new CellRangeAddress[7];
        for(int i=0;i<7;i++){
            day[i] = new CellRangeAddress(1,1,2*i+1,2*i+2);
            sheet.addMergedRegion(day[i]);
        }
        CellRangeAddress sum = new CellRangeAddress(0,1,15,15);
        sheet.addMergedRegion(sum);
    }
    private void mergedCell_3(HSSFSheet sheet) {
        sheet.setDefaultColumnWidth((short) 15);
        CellRangeAddress week = new CellRangeAddress(0,1,0,0);
        sheet.addMergedRegion(week);
        CellRangeAddress sum = new CellRangeAddress(0,1,5,5);
        CellRangeAddress sumMoney = new CellRangeAddress(0,1,6,6);
        sheet.addMergedRegion(sum);
        sheet.addMergedRegion(sumMoney);
    }

    private void download(String path, HttpServletResponse response) {
        try {
            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 取得文件名。
            String filename = file.getName();
            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename="+ new String(filename.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/vnd.ms-excel;charset=utf8");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
