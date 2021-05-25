package com.example.user_part;

public class RsvCond {

    private String date; //날짜
    private int num; //인원수
    private String location; //시/도구
    private String dlocation; //

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
