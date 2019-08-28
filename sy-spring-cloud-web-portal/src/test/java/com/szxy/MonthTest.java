package com.szxy;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @Auther:zwer
 * @Date:2019/8/28 21:14
 * @Description:com.szxy
 * @Version:1.0
 **/
public class MonthTest {

    @Test
    public void testCalendar(){
        //获取当前日历日期
        Calendar calendar = new GregorianCalendar();
        String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
        System.out.println("当前日期："+dateStr);
        String date = getNextCalendar(3);
        System.out.println("往后推 3 个月日期："+date);
        String date2 = getPreCalendar(-2);
        System.out.println("往前推 2 个月的日期："+date2);
    }

    public static String getNextCalendar(Integer n){
        //获取当前日历日期
        Calendar calendar = new GregorianCalendar();
        //往后推 n 个月
        calendar.add(Calendar.MONTH,n);
        //将往后推的一个月的日期转为字符串
        String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
        return dateStr;
    }

    public static String getPreCalendar(Integer n){
        //获取当前日历日期
        Calendar calendar = new GregorianCalendar();
        //往前推 n 个月
        calendar.roll(Calendar.MONTH,n);
        //将前推的一个月的日期转为字符串
        String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
        return dateStr;
    }



}
