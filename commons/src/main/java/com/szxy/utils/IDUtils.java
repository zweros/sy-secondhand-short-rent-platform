package com.szxy.utils;

import com.sun.xml.internal.bind.v2.model.core.ID;

import java.util.Random;
import java.util.UUID;

/**
 * 各种id生成策略
 * @version 1.0
 */
public class IDUtils {

	/**
	 * 图片名生成
	 */
	public static String genImageName() {
		//取当前时间的长整形值包含毫秒
		long millis = System.currentTimeMillis();
		//long millis = System.nanoTime();
		//加上三位随机数
		Random random = new Random();
		int end3 = random.nextInt(999);
		//如果不足三位前面补0
		String str = millis + String.format("%03d", end3);
		return str;
	}
	
	/**
	 * 商品id生成
	 */
	public static Integer genItemId() {
		//取当前时间的长整形值包含毫秒
		long millis = System.currentTimeMillis();
		//long millis = System.nanoTime();
		//加上两位随机数
		Random random = new Random();
		int end2 = random.nextInt(99);
		//如果不足两位前面补0
		String str = millis + String.format("%02d", end2);
		Integer id = Integer.valueOf(str.substring(8));
		return id;
	}

/*	public static void main(String[] args) {
		Integer id = IDUtils.genItemId();
		System.out.println(id);
	}*/
}
