package com.example.hotelmanagement;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Controller implements Serializable {
    DBConnection db = new DBConnection();
    RoomMenu menu;
    ArrayList<String>  IDList;
    ArrayList<RsvInfo> RsvList;
    ArrayList<RoomInfo> RoomList;

    boolean val;
    String ID;
    //int menu;
    //RoomInfo room;

    public Controller() {
        IDList = new ArrayList<String>();
        RsvList = new ArrayList<RsvInfo>();
        RoomList = new ArrayList<RoomInfo>();
    }

    public boolean IsValid(String ID) {
        // 호텔 ID가 유효한가?
        boolean val = false;
        ArrayList<String> IDList = new ArrayList();
        IDList = db.getIDList();

        for(String str : IDList) {
            System.out.println("Input ID: "+ID+" :: Saved ID: "+str);
            if(ID.equals(str)){
                val=true;
                break;
            }
        }
        return val;
    }

    public void setMenu(int menuNum, RoomInfo roomInfo) {
        // 메뉴 1: add, 2: delete, 3: modify
        menu = new RoomMenu(menuNum, roomInfo);
    }

    public ArrayList<RsvInfo> checkReservation() {
        // 예약 정보 보겠다 선택
        RsvList=db.getRsvList();
        System.out.println("\nReservation ID: ");
        for(RsvInfo one_rsv : RsvList)
            System.out.println(one_rsv.getRsv_Num());

        return RsvList;
    }
    public RsvInfo chooseReservation(int r) {
        // 특정 예약 정보에 대한 선택 [ 1. r이 index면? 2. r이 방 번호면? ]
        // 1. r이 index인 경우
        //return this.RsvList.get(r);

        // 2. r이 방 번호인 경우
        RsvInfo out_rsv=null;
        RsvList=db.getRsvList();

        for(RsvInfo one_rsv : RsvList){
            if(one_rsv.getRsv_Num()==r) {
                out_rsv=one_rsv;
                System.out.println("Reservation number: "+out_rsv.getRsv_Num());
                System.out.println("Check-In date: "+out_rsv.getCheckIn_date());
            }
        }
        // 예약 결재 관한 버튼 누를 시 작동하도록 함수를 따로 분류

        return out_rsv;
    }
    public void acceptOrReject(int index, boolean d) { //이름 후보 rsvDecision?
        // 예약 수락or거부 정보 전달 (DB 안의 함수 사용)
        // 사용 시 해당 예약 리스트의 인덱스도 입력해야함!!!!!!! (+ 필요하면 방 번호로도 가능)
        ArrayList<RsvInfo> rsvlist = db.getRsvList();
        rsvlist.get(index).setDecision(d);

        db.Rsv_Record(rsvlist);
    }
}
