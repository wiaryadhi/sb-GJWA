package com.bcafinance.gjwajavafundamental.ujian1;
import java.util.Random;
/*
@Author wiary a.k.a. Arya
ITDP 7
---------------------------------------
| Created with:IntelliJ IDEA          |
| Version: 2022.2.3 (Ultimate Edition)|
| Build id: #IU-222.4345.14           |
---------------------------------------
Created on 11/11/2022 - 13:54
Last Modified on 11/11/2022 - 13:54
Version 1.0
*/

public class SoalDua {
    public static void main(String[] args) {

        //inisialisasi
        Random rand = new Random();
        String strVok = "aiueo"; //0 vokal
        String strKon = "bcdfghjklmnpqrstvwxyz";// 1 konsonan

        char[] charVok = strVok.toCharArray();
        char[] charKon = strKon.toCharArray();

        int v = rand.nextInt(2);
        String strTampung = "";
        char charA;

        //perulangan dengan minimal 12
        for (int i = 0; i < rand.nextInt(11, 40); i++) {
            if (i < 7) {
                if (v == 0) {
                    charA = charVok[rand.nextInt(4)]; //randon index vokal
                    v = 1;
                } else {
                    charA = charKon[rand.nextInt(21)]; //random index konsonan
                    v = 0;
                }
                if (i < 1) {
                    charA = Character.toUpperCase(charA); //set char agar bisa menggunakan function
                }

                strTampung += charA;

            } else {
                if (v == 0) {
                    charA = charVok[rand.nextInt(4)];
                    strTampung += charA;
                } else {
                    charA = charKon[rand.nextInt(21)];
                    strTampung += charA;
                }

            }
        }
        System.out.println(strTampung);
       // System.out.println(strTampung.length());

    }
}
