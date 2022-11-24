package com.bcafinance.gjwajavafundamental.ujian1;
import java.util.Scanner;
/*
@Author wiary a.k.a. Arya
ITDP 7
---------------------------------------
| Created with:IntelliJ IDEA          |
| Version: 2022.2.3 (Ultimate Edition)|
| Build id: #IU-222.4345.14           |
---------------------------------------
Created on 11/11/2022 - 13:42
Last Modified on 11/11/2022 - 13:42
Version 1.0
*/

public class SoalSatu {
    public static void main(String[] args) {

        //deklarasi
        int intNumber = 0;
        int intTotal = 0;
        boolean isAgain = true;

        //inisialisasi scanner
        Scanner sn = new Scanner(System.in);

//        while (isAgain) { //loop selama kondisi true
//            try {
//                System.out.print("Masukkan angka sepuasnya: ");
//                intNumber = sn.nextInt();
//                intTotal += intNumber;
//
//            } catch (Exception e) {
//                System.out.println("masuk kesini");
//                System.out.println("Jumlah: " + intTotal);
//                isAgain = false;
//            }
//        }
//        sn.close();
        try {
            while (isAgain) { //loop selama kondisi true
                System.out.print("Masukkan angka sepuasnya: ");
                intNumber = sn.nextInt();
                intTotal += intNumber;
            }
        } catch (Exception e) {
            System.out.println("masuk kesini");
            System.out.println("Jumlah: " + intTotal);
//            System.out.println(e.getLocalizedMessage()+e.getMessage());
//            e.printStackTrace();
        }finally {
            sn.close();
        }


        }
    }
