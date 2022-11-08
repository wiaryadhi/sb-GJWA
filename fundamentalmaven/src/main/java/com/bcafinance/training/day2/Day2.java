package com.bcafinance.training.day2;
import java.util.Random;

/*
@Author wiary a.k.a. Arya
ITDP 7
------------------------------------
| Created with:                    |
| Eclipse Version: 2022-09 (4.25.0)|
| Build id: 20220908-1902          |
------------------------------------
Created on 08-11-2022 15:34:37
Last Modified on 08-11-2022 15:34:37
Version 1.0
*/

public class Day2 {
	public static void main(String[] args) {
		
		Random ran = new Random();
		int intX = 0;
		char chY;
		String strB = "";
		for (int i = 0;i<9;i++) {
			if(i<3) {
				chY = (char) ran.nextInt(65, 91);
				strB += chY;
			} else if(i<6) {
				chY = (char) ran.nextInt(97, 123);
				strB += chY;
			} else {
				chY = (char) ran.nextInt(48, 58);
				strB += chY;
			}
			
		}
		System.out.println(strB);
	}
	

}
