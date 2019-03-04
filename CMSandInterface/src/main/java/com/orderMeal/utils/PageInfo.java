package com.orderMeal.utils;

public class PageInfo {

    //得到分页的数据信息
    public static int[] getPageInfo(int page, int total, int size){

        int p[] = new int[2];
        p[0] = page * size - size;
        if(total<p[0]+size){
            p[1] = total - p[0];
        }else {
            p[1] = size;
        }
        return p;
    }

}
