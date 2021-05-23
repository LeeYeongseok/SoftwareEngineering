package com.example.user_part;

public class RsvCond {

    private String date;
    private int num;
    private String location;
    private String dlocation;

    public RsvCond(){
    }

    void setDate(String d){
        date = d;
    } String getDate(){
        return date;
    }

    void setNum(int n){
        num = n;
    } int getNum(){
        return num;
    }

    void setLoc(String loc){
        location = loc;
    } String getLoc(){
        return location;
    }

    void setdLoc(String dloc){
        dlocation = dloc;
    } String getdLoc(){
        return dlocation;
    }
}
