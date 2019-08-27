package com.szxy;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Auther:zwer
 * @Date:2019/8/26 17:40
 * @Description:com.szxy
 * @Version:1.0
 **/
public class SimpleDateTset {

    @Test
    public void testDate(){
        Date date = new Date();
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }

}
