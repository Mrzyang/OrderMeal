package com.wap.dao;

import com.orderMeal.dao.GoodsDao;
import com.orderMeal.domain.Customer;
import com.orderMeal.domain.Goods;
import com.orderMeal.utils.JdbcUtil;
import com.wap.utils.TimeUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: OrderMeal
 * @description: 下单流程的dao层操作
 * @author: Mr.Zhang
 * @create: 2018-06-12 17:12
 **/
public class OrderMealApiDao {
    // 1.判断支付密码  2.判断账户余额  3.判断SQL是否执行成功
    public int doOrderMeal(String customerId, String customerPassword,double totalMoney,int[] goodIdArray, int[] dayNoArray, int[] mealTimeNoArray) {    //下单成功 1  失败 0  密码错误 2
        UserApiDao userApiDao = new UserApiDao();
        Customer customer = userApiDao.getCustomerById(Integer.parseInt(customerId));
        if(!customer.getCustomerPassword().equals(customerPassword))
            return 2;   //密码输入错误  直接返回2
        if (customer.getCustomerAccount()<totalMoney)
            return 0; //账户余额不足    直接返回0

        GoodsDao goodsDao = new GoodsDao();

        int schoolId= Integer.parseInt(customer.getSchoolId());
        String customerGrade=customer.getCustomerGrade();
        String customerClass=customer.getCustomerClass();
        String customerNum=customer.getCustomerNum();

        Date nowDate=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //设置时间格式
        String now = sdf.format(nowDate);
        int result=0;           //执行结果
        int result2=0;
        int result3=0;
        String priceSet="";     //18,15,15,18  一周消费集合

        int length=goodIdArray.length;
        Connection conn=null;
        PreparedStatement psmt=null;
        String sql="insert into orders values (null,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            conn= JdbcUtil.getConnection();
            psmt=conn.prepareStatement(sql);

            //对orders表进行插入操作
            for(int i=0;i<length;i++){
                int goodId=goodIdArray[i];
                Goods goods=goodsDao.findGoodsById(String.valueOf(goodId));
                psmt.setInt(1,goodIdArray[i]);
                psmt.setInt(2,schoolId);
                psmt.setInt(3, Integer.parseInt(customerGrade));
                psmt.setInt(4, Integer.parseInt(customerClass));
                psmt.setInt(5, Integer.parseInt(customerId));
                psmt.setString(6,customerNum);
                psmt.setDouble(7,goods.getGoodsPrice());

                priceSet+=goods.getGoodsPrice()+",";

                psmt.setString(8,goods.getProductNames());
                psmt.setString(9, TimeUtils.getDateAfterMenu(dayNoArray[i]-1));
                psmt.setInt(10,getMealTime(mealTimeNoArray[i]));
                psmt.setString(11,now);
                psmt.setInt(12,2);
                result=psmt.executeUpdate();
            }

            //对order4week表进行插入操作
            priceSet=priceSet.substring(0,priceSet.length()-1);
            sql="insert into order4week values (null,?,?,?,?,?,?,?)";
            psmt=conn.prepareStatement(sql);
            psmt.setInt(1, Integer.parseInt(customerId));
            psmt.setString(2,customer.getCustomerName());
            psmt.setInt(3, Integer.parseInt(customer.getSchoolId()));
            psmt.setInt(4, Integer.parseInt(customerGrade));
            psmt.setInt(5, Integer.parseInt(customerClass));
            psmt.setString(6,TimeUtils.getDateAfterMenu(0));
            psmt.setString(7,priceSet);
            result2=psmt.executeUpdate();

            //交易的结果是 账户余额减少
            sql="update customers set customer_account=customer_account-? where customer_id=?";
            psmt=conn.prepareStatement(sql);
            psmt.setDouble(1,totalMoney);
            System.out.println("dao层---总共费用是"+totalMoney);
            psmt.setInt(2, Integer.parseInt(customerId));
            result3=psmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.release(psmt,conn);
        }
        if(result==1&&result2==1&&result3==1){
            return 1;      //下单成功  返回1
        }else {
            return -1;     //执行sql出错 返回-1
        }
    }


    public int getMealTime(int mealTimeNo) {  //午餐--1 ，晚餐--2
        if (mealTimeNo == 1 || mealTimeNo == 0)
            return 1;
        else
            return 2;
    }
}
