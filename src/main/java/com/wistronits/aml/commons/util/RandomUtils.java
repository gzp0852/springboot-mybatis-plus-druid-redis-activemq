package com.wistronits.aml.commons.util;

import java.util.Date;
import java.util.Random;

/**
 * @author gzp
 * @date 2018/9/19 10:54
 */
public class RandomUtils {

	public static String random6() {
		// 第一种----------
		// return (int)((Math.random()*9+1)*100000);
		// 第二种----------
		// Random r = new Random();
		// eg:0.9708724409898095生成0-1之间的随机数
		// double str= r.nextDouble();
		// return (int)((str*9+1)*100000);
		// 第三种----------
		// int x = 0;
		// while(true){
		// x = r.nextInt(999999);
		// if(x > 99999){
		// break;
		// }
		// else {
		// continue;
		// }
		// }
		// return x;
		return getRandNum(1, 999999) + "";
	}

	public static int getRandNum(int min, int max) {
		int randNum = min + (int) (Math.random() * ((max - min) + 1));
		return randNum;
	}
}
