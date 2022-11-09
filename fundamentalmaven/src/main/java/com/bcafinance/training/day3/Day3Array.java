package com.bcafinance.training.day3;

/*
@Author wiary a.k.a. Arya
ITDP 7
------------------------------------
| Created with:                    |
| Eclipse Version: 2022-09 (4.25.0)|
| Build id: 20220908-1902          |
------------------------------------
Created on 09-11-2022 15:06:56
Last Modified on 09-11-2022 15:06:56
Version 1.0
*/

public class Day3Array {
	
	public static void main(String[] args) {
		
		//contoh untuk tipe data char
		char[] charY = {'1', '2', '3'}; //charY diisi data 3 seperti mengisi  3 slot
		char[] charX = new char[3]; //charX disini artinya object charX sudah punya slot 3 dari awal
		charX[2]='F'; //mengisi char F di index ke 2 pada charX
		
		System.out.println(charY);
		
		//contoh untuk double
		double[] doP = new double[4]; //doP sudah diset memiliki 4 slot
		doP[2]=1.9; //set index ke 2dari doP
		
		System.out.println(doP[3]); //outputnya adalah 0.0 karena jika belum di set, maka akan diberikan nilai default
		
		
		//Contoh Array 2 dimensi
		String [][] strS = new String[2][3]; //2 artinya 2 baris(menurun), 3 artinya 3kolom(mendatar)
	
		//mengisi array 2 dimensi dengan hardcode
		strS[0][0]="AB";
		strS[0][1]="CD";
		strS[0][2]="EF";
		strS[1][0]="GH";
		strS[1][1]="IJ";
		strS[1][2]="KL";
		
		//CONTOH SOAL
		StringBuilder sBuild = new StringBuilder();
		
		for(int i=0;i<strS.length;i++) { 
			
			for(int j=0;j<strS[i].length;j++) {
				
				if(j==strS[i].length-1) {
					sBuild.append(strS[i][j]).append("\n").toString();
				}else {
					sBuild.append(strS[i][j]).append(",").toString();
				}
	
			}
			
		}
		
		System.out.println(sBuild);
			
	}

}
