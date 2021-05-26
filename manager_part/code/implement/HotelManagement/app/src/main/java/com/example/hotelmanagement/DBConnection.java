package com.example.hotelmanagement;

import java.util.ArrayList;

public class DBConnection {

    private RoomInfo room;
    private ArrayList<String> IDList = new ArrayList<String>();
    private ArrayList<RsvInfo> RsvList = new ArrayList<RsvInfo>();
    ArrayList<Integer> reject_RsvNum = new ArrayList<Integer>();
    ArrayList<Integer> accept_RsvNum = new ArrayList<Integer>();

    public DBConnection() {
        for(int i=0;i<10;i++)
        {
            IDList.add(String.valueOf(i));
        }

        /*System.out.println("hotel ID: ");
        for(String str : IDList)
            System.out.println(str);*/

    }
    public ArrayList<String> getIDList(){
        return IDList;
    }

    public ArrayList<RsvInfo> getRsvList(){
        for(int i=0;i<10;i++){
            RsvInfo one_rsv = new RsvInfo();
            one_rsv.setRsv_Num(i*100);
            one_rsv.setCheckIn_date("May."+String.valueOf(i));
            RsvList.add(one_rsv);
        }
        return RsvList;
    }

    public void Rsv_Record(RsvInfo rsvInfo) {
        for (RsvInfo one_rsv : RsvList) {
            if (one_rsv.getDecision() == false)
                reject_RsvNum.add(one_rsv.getRsv_Num()); // 거절된 예약에 대한 리스트 작성
            else if (one_rsv.getDecision() == true)
                accept_RsvNum.add(one_rsv.getRsv_Num()); // 승인된 예약에 대한 리스트 작성
        }
        // 예약에 대한 수락or거절에 대해 DB에 전달 ~~??
        System.out.println("Reject Reservation: ");
        for (Integer one_rsvNum : reject_RsvNum) System.out.println(one_rsvNum);
        System.out.println("\nAccept Reservation: ");
        for (Integer one_rsvNum : accept_RsvNum) System.out.println(one_rsvNum);
    }

    public void Room_Record(RoomInfo roomInfo) {
        // 입력 혹은 수정된 방 정보에 대한 정보 전달

        room = roomInfo;
    }
    public void Room_delete(int roomNum) {

    }

}