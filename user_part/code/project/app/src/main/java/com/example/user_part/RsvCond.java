package com.example.user_part;

public class RsvCond {

    private String checkin_date; //체크인 날짜
    private String checkout_date; //체크아웃 날짜
    private int stayNight; //머무는 일수
    private int num; //인원수
    private String location; //시/도
    private String dlocation; //세부위치

    public RsvCond(){
    }

    public int getStayNight() {
        return stayNight;
    }

    public void setStayNight(int stayNight) {
        this.stayNight = stayNight;
    }

    public String getCheckin_date() {
        return checkin_date;
    }

    public void setCheckin_date(String checkin_date) {
        this.checkin_date = checkin_date;
    }

    public String getCheckout_date() {
        return checkout_date;
    }

    public void setCheckout_date(String checkout_date) {
        this.checkout_date = checkout_date;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDlocation() {
        return dlocation;
    }

    public void setDlocation(String dlocation) {
        this.dlocation = dlocation;
    }
}
