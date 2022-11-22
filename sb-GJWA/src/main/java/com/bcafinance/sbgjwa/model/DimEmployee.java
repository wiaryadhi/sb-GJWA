package com.bcafinance.sbgjwa.model;

/*
@Author wiary a.k.a. Arya
ITDP 7
---------------------------------------
| Created with:IntelliJ IDEA          |
| Version: 2022.2.3 (Ultimate Edition)|
| Build id: #IU-222.4345.14           |
---------------------------------------
Created on 22/11/2022 - 13:48
Last Modified on 22/11/2022 - 13:48
Version 1.0
*/

public class DimEmployee {

    private int EmployeeKey;
    private String FirstName;
    private String Lastname;
    private int NameStyle;
    private int CurrentFlag;
    private int SalesPersonFlag;

    public DimEmployee(int EmployeeKey, String FirstName, String LastName, int CurrentFlag, int NameStyle, int SalesPersonFlag){
        this.EmployeeKey = EmployeeKey;
        this.FirstName = FirstName;
        this.Lastname = LastName;
        this.CurrentFlag = CurrentFlag;
        this.NameStyle = NameStyle;
        this.SalesPersonFlag = SalesPersonFlag;
    }

    public DimEmployee(){
    }

    public DimEmployee(String firstName, String lastname, int nameStyle, int currentFlag, int salesPersonFlag) {
        FirstName = firstName;
        Lastname = lastname;
        NameStyle = nameStyle;
        CurrentFlag = currentFlag;
        SalesPersonFlag = salesPersonFlag;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }


    public int getNameStyle() {
        return NameStyle;
    }
    public void setNameStyle(int nameStyle) {
        NameStyle = nameStyle;
    }


    public int getCurrentFlag() {
        return CurrentFlag;
    }
    public void setCurrentFlag(int currentFlag) {
        CurrentFlag = currentFlag;
    }


    public int getSalesPersonFlag() {
        return SalesPersonFlag;
    }
    public void setSalesPersonFlag(int salesPersonFlag) {
        SalesPersonFlag = salesPersonFlag;
    }


    public int getEmployeeKey() {
        return EmployeeKey;
    }
    public void setEmployeeKey(int employeeKey) {
        this.EmployeeKey = employeeKey;
    }


    public String getFirstName() {
        return FirstName;
    }
    public void setFirstName(String firstName) {
        FirstName = firstName;
    }
}
