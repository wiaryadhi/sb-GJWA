package com.bcafinance.training.day3;

/*
@Author wiary a.k.a. Arya
ITDP 7
------------------------------------
| Created with:                    |
| Eclipse Version: 2022-09 (4.25.0)|
| Build id: 20220908-1902          |
------------------------------------
Created on 09-11-2022 09:52:32
Last Modified on 09-11-2022 09:52:32
Version 1.0
*/

public class Day3String {

	public static void main(String[] args) {
		
		//untuk membuktikan hasil string di-append dengan integer
		System.out.println("4" + 4);
		
		//best practice jika ingin menggabungkan String
		String strGb = "abcd";
		String strGb2 = "efgh";
		String strGb3 = "ijkl";
		int strGb4 = 4;
		System.out.println(strGb.concat(strGb2).concat(strGb3)); //contoh bestpractice concat String
		System.out.println(strGb + strGb2 + strGb3); //harus dihindari penenulisan sperti ini karena memberatkan performance
		
		//contoh membuat String sebagai object
		String strK = new String("OP");
		
		//contoh inisiasi String sebagai variabel
		String strK2 = "OP";

		//best practice/standar penulisan string dengan tidak menggunakan tanda ==
		if (strK.equals("OP")) {
			System.out.println("true");
		} else {
			System.out.println("false");
		}

		//hal yang harus dihindari dalam penulisan String karena tidak sesuai standarisasi
		if (strK2 == "OP") {
			System.out.println("true");
		} else {
			System.out.println("false");
		}
		
		
		//menggunakan String Builder sebagai append
		StringBuilder sBuild = new StringBuilder(); //inisiasi String builder dengan nama sBuild
		
		StringBuilder strApp = sBuild.append(strGb4).append(strGb3).append(strGb2).append(strGb); //membuat variabel untuk menampung string builder
		
		System.out.println(sBuild.append(strGb4).append(strGb3).append(strGb2).append(strGb)); //bisa langsung pakai
		System.out.println(strApp); //bisa dengan memanggil variabelnya
		
		
		//String pada java cukup unik karena bisa diisi function
		String strFc = "Tes String dengan variabel";
		"Tes String tanpa inisiasi variabel".length(); // tinggal di sysout
		
		System.out.println(strFc.length());
		System.out.println("Tes String tanpa inisiasi variabel".length());
	
		
		//untuk akses suatu karakter dari String bis amenggunakan charAt
		String strNama = "Gede Jaya Widhi Aryadi";
		char charHuruf = strNama.charAt(5); //ditampung dulu didalam char karena tipe data method charAt adalah char
		System.out.println(charHuruf);
		
		//Substring digunakan untuk memotong string
		String strNamaPendek = "wiaryadhi";
		System.out.println(strNamaPendek.substring(3, 7));// parameter1 dimulai dari index ke-0, parameter2 dimulai dari index 0 = 1
		System.out.println(sBuild.append(strNamaPendek.substring(3)).append("arya"));// coba aplikasi append sbuilder dengan substring
		
		
	}
}

