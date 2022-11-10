package com.bcafinance.fundamental.day4;
/*
@Author wiary a.k.a. Arya
ITDP 7
---------------------------------------
| Created with:IntelliJ IDEA          |
| Version: 2022.2.3 (Ultimate Edition)|
| Build id: #IU-222.4345.14           |
---------------------------------------
Created on 10/11/2022 - 14:36
Last Modified on 10/11/2022 - 14:36
Version 1.0
*/

public class Soal2 {
    public static void main(String[] args) {
        int keyword = 100;
        int[] val = {66, 77, 80, 84, 88, 99, 100};
        int low = 0;
        int high = val.length - 1;
        int med = 0;
        boolean status = false;


        while (low <= high) {
            med = (low + high) / 2;
            if (keyword==val[med]){
                System.out.println(keyword + " FOUNDED in position " + (med+1) + " and INDEX " + med);
                status = true;
                break;
            } else {
                if (keyword>val[med]){
                    low = med+1;
                } else {
                    high = med-1;
                }
            }
        }  if (status==false) {
            System.out.println(keyword + " NOT FOUND!!");
        }


        }
    }
