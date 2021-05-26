package com.example.hotelmanagement;

import java.util.ArrayList;

public class Controller {
    DBConnection db = new DBConnection();
    RoomMenu menu;
    ArrayList<String> IDList;
    ArrayList<RsvInfo> RsvList;
    boolean val;
    String ID;
    //int menu;
    //RoomInfo room;

    public Controller() {
        IDList = new ArrayList<String>();

        //room = new RoomInfo();
    }

    public void getIDList() {
        // DB로부터 호텔 ID 정보 받기
        IDList = db.getIDList();
    }
    public boolean IsValid(String ID) {
        // 호텔 ID가 유효한가?
        boolean val = false;

        getIDList();

        for(String str : IDList) {
            if(ID==str){
                val=true;
                break;
            }
        }
        return val;
    }
    private void setMenu(int menuNum, RoomInfo roomInfo) {
        // 메뉴 1: add, 2: delete, 3: modify
        menu = new RoomMenu(menuNum, roomInfo);
    }

    private void checkReservation() {
        // 예약 정보 보겠다 선택
        RsvList=db.getRsv();
        for(RsvInfo rsv : RsvList) {
            System.out.println(rsv.getRsv_Num());
        }
    }
    private void chooseReservation(int r) {
        // 특정 예약 정보에 대한 선택
        for(RsvInfo rsv : RsvList) {
            if(rsv.getRsv_Num()==r){
                //임시
                System.out.println("Reservation number: "+rsv.getRsv_Num());
                System.out.println("checkIn date: " + rsv.getCheckIn_date()+" / "+"checkIn time: " + rsv.getiTime());
                System.out.println("checkOut date: " + rsv.getCheckOut_date()+" / "+"checkOut time: "+ rsv.getiTime());
                System.out.println("meal: "+rsv.getMeal());
                System.out.println("number of people: "+rsv.getNumOfPeople());
            }
        }
    }
    private void acceptOrReject(boolean d) {
        // 예약 수락or거부 정보 전달 (DB 안의 함수 사용)
        db.Rsv_Record(d);
    }

}
