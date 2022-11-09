package com.bcafinance.training.day3;

import java.util.Scanner;

/*
@Author wiary a.k.a. Arya
ITDP 7
------------------------------------
| Created with:                    |
| Eclipse Version: 2022-09 (4.25.0)|
| Build id: 20220908-1902          |
------------------------------------
Created on 09-11-2022 11:39:12
Last Modified on 09-11-2022 11:39:12
Version 1.0
*/

public class Day3Input {
	
	public static void main(String[] args) {
		
		StringBuilder sBuild = new StringBuilder();
		
		int panjang, lebar;
		
		Scanner sn = new Scanner(System.in);
		
		System.out.print("Panjangnya :");
		panjang = sn.nextInt();
		
		System.out.print("Lebarnya : ");
		lebar = sn.nextInt();
		
//		System.out.println("Panjangnya adalah : " + panjang);
//		System.out.println("Lebarnya adalah : " + lebar);
//		
		System.out.println(sBuild.append("Panjangnya adalah : ").append(panjang).toString());
		sBuild.setLength(0);
		System.out.println(sBuild.append("Lebarnya adalah : ").append(lebar).toString());
		
		
	}
	
	
	
	

}
