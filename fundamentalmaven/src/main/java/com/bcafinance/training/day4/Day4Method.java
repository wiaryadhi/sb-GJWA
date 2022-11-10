package com.bcafinance.training.day4;
/*
@Author wiary a.k.a. Arya
ITDP 7
---------------------------------------
| Created with:IntelliJ IDEA          |
| Version: 2022.2.3 (Ultimate Edition)|
| Build id: #IU-222.4345.14           |
---------------------------------------
Created on 10/11/2022 - 10:43
Last Modified on 10/11/2022 - 10:43
Version 1.0
*/

public class Day4Method {

    public static float convertCelcius(float tempC){
        return (float)  (((tempC * 9.0f) / 5.0f) + 32.0);
    }

    public static void main(String[] args) {

        System.out.println(convertCelcius(10));

    }


}

