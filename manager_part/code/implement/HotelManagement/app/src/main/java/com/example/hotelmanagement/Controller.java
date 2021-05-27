package com.example.hotelmanagement;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Controller implements Serializable {
    DBConnection db = new DBConnection();
    RoomMenu menu;
    ArrayList<String>  IDList;
    ArrayList<RsvInfo> RsvList;

    boolean val;
    String ID;
    //int menu;
    //RoomInfo room;

    public Controller() {
        IDList = new ArrayList<String>();
        //room = new RoomInfo();
        //getIDList();

        Scanner input = new Scanner(System.in);
        System.out.println("호텔 ID를 입력하세요(1~10)");
        ID = input.nextLine();
        System.out.println("Is it valid hotel ID? "+IsValid(ID));

        checkReservation();

        int r=0;
        System.out.println("원하는 예약 정보를 입력하세요(숫자입력)");
        r=input.nextInt();

        RsvInfo out_rsv = null;
        out_rsv=chooseReservation(r);
        System.out.println("Chosen reservation number: "+out_rsv.getRsv_Num());
        System.out.println("& Reservation Decision: "+out_rsv.getDecision());

    }

    public void getIDList() { //ArrayList<String> list
        // DB로부터 호텔 ID 정보 받기
        IDList = db.getIDList();
        System.out.println("hotel ID: ");
        for(String str : IDList)
            System.out.println(IDList.indexOf(str)+": "+str);
    }

    public boolean IsValid(String ID) {
        // 호텔 ID가 유효한가?
        boolean val = false;

        //ArrayList<String> list = new ArrayList();
        //getIDList(list);
        getIDList();

        for(String str : IDList) {
            System.out.println("Input ID: "+ID+" :: Saved ID: "+str);
            if(Objects.equals(ID, str)){
                val=true;
                break;
            }
        }
        return val;
    }
    private void setMenu(int menuNum, RoomInfo roomInfo) {
        // 메뉴 1: add, 2: delete, 3: modify

        menu = new RoomMenu(menuNum, roomInfo);


        /*RoomMenu roommenu = new RoomMenu();

        if(menu==1){
            roommenu.add(roomInfo);
        }
        else if(menu==2){
            roommenu.delete(roomInfo.getRsv_Num());
        }
        else if(menu==3){
            roommenu.modify(roomInfo);
        }*/
    }

    private void checkReservation() {
        // 예약 정보 보겠다 선택
        RsvList=db.getRsvList();
        System.out.println("\nReservation ID: ");
        for(RsvInfo one_rsv : RsvList)
            System.out.println(one_rsv.getRsv_Num());
    }
    private RsvInfo chooseReservation(int r) {
        // 특정 예약 정보에 대한 선택
        RsvInfo out_rsv=null;
        for(RsvInfo one_rsv : RsvList){
            if(one_rsv.getRsv_Num()==r) {
                out_rsv=one_rsv;
                System.out.println("Reservation number: "+out_rsv.getRsv_Num());
                System.out.println("Check-In date: "+out_rsv.getCheckIn_date());
            }
        }
        boolean d = false;
        // 예약 결재 관한 버튼 누를 시 작동하도록 함수를 따로 분류 - if문이나 action에서 사용
        acceptOrReject(out_rsv);
        System.out.println(out_rsv.getDecision());

        return out_rsv;
    }
    private void acceptOrReject(RsvInfo rsvInfo) { //이름 후보 rsvDecision?
        // 예약 수락or거부 정보 전달 (DB 안의 함수 사용)
        Scanner Decision = new Scanner(System.in);
        System.out.println("예약을 수락하시겠습니까?(true/false)");
        boolean d = Decision.nextBoolean();

        rsvInfo.setDecision(d);
    }

    public ArrayList<String> gIDList() { //ArrayList<String> list
        // DB로부터 호텔 ID 정보 받기
        return IDList;
    }

}
