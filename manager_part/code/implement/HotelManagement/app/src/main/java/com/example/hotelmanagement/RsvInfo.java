package com.example.hotelmanagement;

public class RsvInfo {

    private int Rsv_Num=0; //예약번호
    private int Room_Num =0; //예약된 방 번호
    private String iTime=null; //체크인 시간
    private String oTime=null; //체크아웃 시간
    private boolean meal=false; //식사 여부
    private String checkIn_date=null; //체크인 날짜
    private String checkOut_date=null; //체크아웃 날짜
    private int NumOfPeople=0; //예약 인원
    private boolean d=false; //예약 승인 혹은 거절에 대한 여부

    public int getRsv_Num(){return Rsv_Num;}
    public void setRsv_Num(int Rsv_Num){this.Rsv_Num=Rsv_Num;}
    public int getRoom_Num(){return Room_Num;}
    public void setRoom_Num(int Room_Num){this.Room_Num=Room_Num;}
    public String getiTime(){return iTime;}
    public void setiTime(String itime){this.iTime=itime;}
    public String getoTime(){return oTime;}
    public void setoTime(String otime){this.oTime=otime;}
    public boolean getMeal(){return meal;}
    public void setMeal(boolean meal){this.meal=meal;}
    public String getCheckIn_date(){return checkIn_date;}
    public void setCheckIn_date(String checkIn_date){this.checkIn_date=checkIn_date;}
    public String getCheckOut_date(){return checkOut_date;}
    public void setCheckOut_date(String checkOut_date){this.checkOut_date=checkOut_date;}
    public int getNumOfPeople(){return NumOfPeople;}
    public void setNumOfPeople(int NumOfPeople){this.NumOfPeople=NumOfPeople;}
    public boolean getDecision(){return d;}
    public void setDecision(boolean d){this.d=d;}
}

