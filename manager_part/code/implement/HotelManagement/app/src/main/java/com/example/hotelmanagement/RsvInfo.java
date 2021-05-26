package com.example.hotelmanagement;

public class RsvInfo {

    private int Rsv_Num;
    private String iTime;
    private String oTime;
    private boolean meal;
    private String checkIn_date;
    private String checkOut_date;
    private int NumOfPeople;

    public int getRsv_Num(){return Rsv_Num;}
    public void setRsv_Num(int Rsv_Num){this.Rsv_Num=Rsv_Num;}
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

}
