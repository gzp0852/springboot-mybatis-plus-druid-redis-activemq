/*
package com.wistronits.aml.commons.mp;

import java.util.Date;
import java.util.Random;

*/
/**
 *
 * @author gzp
 * @date 2018/9/19 10:49
 *//*

public class RandomCode {
	public static void main(String[] args) {
		Date time=new Date();
		String date=time.toLocaleString();
		System.out.println(date);
		date=date.substring(0,date.indexOf(" "));
		System.out.println(date);
		//验证码
		//随机生成6位 Math.random()生成0-1之间的double类型的数字,Math.random()*9生成0-8之间
		System.out.println((int)((Math.random()*9+1)*100000)+"=1");//1
		Random r = new Random();
		//eg:0.9708724409898095生成0-1之间的随机数
		double str= r.nextDouble();
		System.out.println(str*9);
		System.out.println((int)((str*9+1)*100000)+"=2");//2

		//int x = r.nextInt(999999);
		//System.out.println(x);//35528,有可能生成5位,需判断
		int x = 0;
		while(true){
			x = r.nextInt(999999);
			if(x > 99999){
				break;
			}
			else {
				continue;
			}
		}
		System.out.println(x+"=3");//3

		System.out.println("随机数为:" + getRandNum(1,999999));//4
	}

	public static int getRandNum(int min, int max) {
		int randNum = min + (int)(Math.random() * ((max - min) + 1));
		return randNum;
	}
}
*/
