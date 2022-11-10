package com.bcafinance.fundamental.day4;
/*
@Author wiary a.k.a. Arya
ITDP 7
---------------------------------------
| Created with:IntelliJ IDEA          |
| Version: 2022.2.3 (Ultimate Edition)|
| Build id: #IU-222.4345.14           |
---------------------------------------
Created on 10/11/2022 - 13:58
Last Modified on 10/11/2022 - 13:58
Version 1.0
*/

public class Soal1 {
    public static void main(String[] args) {

        //deklarasi
        int[] intArr = {3,8,5,2,1,9,10};
        int intB = 5;
        boolean isFound = true;
        StringBuilder sBuild = new StringBuilder();

        //blok a
        for (int i = 0; i < intArr.length; i++){
            if (intB==intArr[i]) {
                System.out.println(sBuild.append("Angka ").append(intB).append(" sudah ditemukan, pada index ke ").append(i).toString());
                isFound = false;
            }
            }
        if (isFound){
                System.out.println(sBuild.append("Angka ").append(intB).append(" yang anda cari tidak ditemukan!").toString());
        }



    }
}
